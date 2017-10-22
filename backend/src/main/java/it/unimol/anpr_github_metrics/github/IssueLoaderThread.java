package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.*;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.IssueComment;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.utils.DateUtils;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Stefano Dalla Palma
 */
public class IssueLoaderThread implements Callable<Issue> {

    private final Github github;
    private final Repository repository;
    private final com.jcabi.github.Issue remoteIssue;

    public IssueLoaderThread(Github github, Repository repository, com.jcabi.github.Issue remoteIssue) {
        this.github = github;
        this.repository = repository;
        this.remoteIssue = remoteIssue;
    }

    @Override
    public Issue call() throws Exception {
        return loadIssue(this.repository, this.remoteIssue);
    }



    private Issue loadIssue(Repository repository, com.jcabi.github.Issue remoteIssue) throws IOException, InterruptedException {
        JsonObject issueJson = remoteIssue.json();
        com.jcabi.github.Issue.Smart smartIssue = new com.jcabi.github.Issue.Smart(remoteIssue);

        Date lastClosedDate = null;
        Date lastReopenedDate = new Date(0);

        Date lastDuplicatedMarkDate = null;
        Date lastUnduplicatedMarkDate = new Date(0);

        boolean fixed = false;

        for (Event event : remoteIssue.events()) {
            switch (event.json().getString("event")) {
                case "closed":
                    Date newClosed = DateUtils.getMandatoryDate(event.json().getString("created_at"));
                    if (lastClosedDate == null || newClosed.compareTo(lastClosedDate) > 0)
                        lastClosedDate = newClosed;

                    if (!event.json().isNull("commit_id")) {
                        fixed = true;
                    }

                    break;
                case "reopened":
                    Date newReopenedDate = DateUtils.getMandatoryDate(event.json().getString("created_at"));
                    if (newReopenedDate.compareTo(lastReopenedDate) > 0)
                        lastReopenedDate = newReopenedDate;
                    break;
                case "marked_as_duplicated":
                    Date newDuplicated = DateUtils.getMandatoryDate(event.json().getString("created_at"));
                    if (lastDuplicatedMarkDate == null || newDuplicated.compareTo(lastDuplicatedMarkDate) > 0)
                        lastDuplicatedMarkDate = newDuplicated;
                    break;
                case "unmarked_as_duplicated":
                    Date newUnduplicated = DateUtils.getMandatoryDate(event.json().getString("created_at"));
                    if (newUnduplicated.compareTo(lastUnduplicatedMarkDate) > 0)
                        lastUnduplicatedMarkDate = newUnduplicated;
                    break;
            }
        }

        Issue issue = new Issue();
        issue.setNumber(remoteIssue.number());
        issue.setAuthor(loadUser(issueJson.getJsonObject("user").getString("login")));

        issue.setUrl(issueJson.getString("html_url"));
        issue.setTitle(issueJson.getString("title"));
        issue.setBody(issueJson.getString("body"));

        issue.setCreatedTime(DateUtils.getMandatoryDate(issueJson.getString("created_at")));
        issue.setUpdatedTime(DateUtils.getOptionalDate(issueJson.getString("updated_at")));
        if (!issueJson.isNull("closed_at"))
            issue.setClosedTime(DateUtils.getMandatoryDate(issueJson.getString("closed_at")));

        issue.setFixed(fixed);

        issue.setClosed(!smartIssue.isOpen());

        issue.setLocked(issueJson.getBoolean("locked"));


        ExecutorService executorService = Executors.newCachedThreadPool();
        Collection<Callable<IssueComment>> commentCallables = new ArrayList<>();

        Collection<IssueComment> comments = new ArrayList<>();
        for (Comment remoteComment : remoteIssue.comments().iterate(issue.getCreatedTime())) {
           commentCallables.add(new CommentLoaderThread(this.github, issue, remoteComment));
        }

        List<Future<IssueComment>> futures = null;
        futures = executorService.invokeAll(commentCallables);

        for(Future<IssueComment> future : futures){
            IssueComment comment = null;
            try {
                comment = future.get();
                comments.add(comment);
            } catch (InterruptedException | ExecutionException e) {
                // Ignore the comment
            }
        }


        issue.setComments(comments);
        issue.setRepository(repository);

        Collection<Issue.Label> labels = new HashSet<>();
        for (Label label : remoteIssue.labels().iterate()) {
            if (label.name().equalsIgnoreCase("bug")) {
                labels.add(Issue.Label.BUG);
            } else if (label.name().equalsIgnoreCase("enhancement")) {
                labels.add(Issue.Label.ENHANCEMENT);
            } else if (label.name().equalsIgnoreCase("help wanted")) {
                labels.add(Issue.Label.HELP);
            } else if (label.name().equalsIgnoreCase("question")) {
                labels.add(Issue.Label.QUESTION);
            } else if (label.name().equalsIgnoreCase("invalid")) {
                issue.setInvalid(true);
            }
        }
        issue.setLabels(labels);

        return issue;
    }

    private User loadUser(String userLogin) throws IOException {
        com.jcabi.github.User remoteUser = this.github.users().get(userLogin);
        User user = new User();
        user.setLogin(userLogin);
        user.setUrl(remoteUser.json().getString("html_url"));

        return user;
    }
}
