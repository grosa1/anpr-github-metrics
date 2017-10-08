package it.unimol.anpr_github_metrics.github;


import com.jcabi.github.*;
import it.unimol.anpr_github_metrics.beans.*;
import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.User;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorImpl implements IssueExtractor {
    private static final SimpleDateFormat GH_DATE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private Github github;

    public void setGithub(Github github) {
        this.github = github;
    }

//    public Collection<User> getContributorsOld(Repository repository) throws GithubException {
//        if (repository.getContributors() == null) {
//            Repo remoteRepository = github.repos().get(new Coordinates.Simple(repository.getName()));
//
//            List<User> contributors = new ArrayList<>();
//            for (com.jcabi.github.User remoteUser : remoteRepository.collaborators().iterate()) {
//                User user;
//                try {
//                    user = loadUser(remoteUser.login());
//                } catch (IOException e) {
//                    throw new GithubException();
//                }
//
//                contributors.add(user);
//            }
//
//            repository.setContributors(contributors);
//        }
//
//        return repository.getContributors();
//    }

    public Collection<User> getContributors(Repository repository) throws GithubException {
        if (repository.getContributors() == null) {
            Repo remoteRepository = github.repos().get(new Coordinates.Simple(repository.getName()));

            Set<User> contributors = new HashSet<>();
            for (com.jcabi.github.RepoCommit remoteCommit : remoteRepository.commits().iterate(new HashMap<>())) {
                User user;
                try {
                    String login = remoteCommit.json().getJsonObject("author").getString("login");
                    user = loadUser(login);
                } catch (IOException e) {
                    throw new GithubException();
                }
                contributors.add(user);
            }
            repository.setContributors(contributors);
        }
        return repository.getContributors();
    }

    public Collection<Issue> getFixedIssues(Repository repository) throws GithubException {
        Collection<Issue> allIssues = this.getIssues(repository);

        return allIssues.stream().filter(Issue::isFixed).collect(Collectors.toList());
    }

    public Collection<Issue> getFixedIssues(User user, Repository repository) throws GithubException {
        Collection<Issue> allIssues = this.getIssues(repository);

        return allIssues.stream().filter(issue -> issue.isFixed() && issue.getAuthor().equals(user)).collect(Collectors.toList());
    }

    public Collection<Issue> getOpenIssues(Repository repository) throws GithubException {
        Collection<Issue> allIssues = this.getIssues(repository);

        return allIssues.stream().filter(issue -> !issue.isClosed()).collect(Collectors.toList());
    }

    public Collection<Issue> getOpenIssues(User user, Repository repository) throws GithubException {
        Collection<Issue> allIssues = this.getIssues(repository);

        return allIssues.stream().filter(issue -> !issue.isClosed() && issue.getAuthor().equals(user)).collect(Collectors.toList());
    }

    public Collection<Commit> getCommitsInvolvedInIssue(Issue issue) throws GithubException {
        Repo remoteRepository = github.repos().get(new Coordinates.Simple(issue.getRepository().getName()));

        com.jcabi.github.Issue remoteIssue = remoteRepository.issues().get(issue.getNumber());

        Collection<Commit> commits = new ArrayList<>();

        try {
            for (Event event : remoteIssue.events()) {
                if (event.json().getString("event").equals("closed")) {
                    String commitId = event.json().getString("commit_id");

                    Commit commit = this.loadCommit(remoteRepository, commitId);
                    commits.add(commit);
                }
            }
        } catch (IOException e) {
            throw new GithubException();
        }

        return commits;
    }

    @Override
    public Collection<Issue> getIssues(Repository repository) throws GithubException {
        if (repository.getIssues() == null) {
            List<Issue> issues = new ArrayList<>();
            Repo remoteRepository = github.repos().get(new Coordinates.Simple(repository.getName()));
            for (com.jcabi.github.Issue remoteIssues : remoteRepository.issues().iterate(new HashMap<>())) {
                try {
                    issues.add(loadIssue(remoteRepository, repository, remoteIssues.number()));
                } catch (IOException e) {
                    throw new GithubException();
                }
            }
            repository.setIssues(issues);
        }
        return repository.getIssues();
    }

    private Issue loadIssue(Repo remoteRepository, Repository repository, int number) throws IOException {
        com.jcabi.github.Issue remoteIssue = remoteRepository.issues().get(number);
        JsonObject issueJson = remoteIssue.json();

        Date lastClosedDate = null;
        Date lastReopenedDate = new Date(0);

        Date lastDuplicatedMarkDate = null;
        Date lastUnduplicatedMarkDate = new Date(0);

        boolean fixed = false;

        for (Event event : remoteIssue.events()) {
            switch (event.json().getString("event")) {
                case "closed":
                    Date newClosed = getMandatoryDate(event.json().getString("created_at"));
                    if (lastClosedDate == null || newClosed.compareTo(lastClosedDate) > 0)
                        lastClosedDate = newClosed;

                    String commitId = event.json().getString("commit_id");
                    if (commitId != null) {
                        fixed = true;
                    }

                    break;
                case "repoened":
                    Date newReopenedDate = getMandatoryDate(event.json().getString("created_at"));
                    if (newReopenedDate.compareTo(lastReopenedDate) > 0)
                        lastReopenedDate = newReopenedDate;
                    break;
                case "marked_as_duplicated":
                    Date newDuplicated = getMandatoryDate(event.json().getString("created_at"));
                    if (lastDuplicatedMarkDate == null || newDuplicated.compareTo(lastDuplicatedMarkDate) > 0)
                        lastDuplicatedMarkDate = newDuplicated;
                    break;
                case "unmarked_as_duplicated":
                    Date newUnduplicated = getMandatoryDate(event.json().getString("created_at"));
                    if (newUnduplicated.compareTo(lastUnduplicatedMarkDate) > 0)
                        lastUnduplicatedMarkDate = newUnduplicated;
                    break;
            }
        }

        Issue issue = new Issue();
        issue.setNumber(number);
        issue.setAuthor(loadUser(issueJson.getJsonObject("user").getString("login")));

        issue.setUrl(issueJson.getString("html_url"));
        issue.setTitle(issueJson.getString("title"));
        issue.setBody(issueJson.getString("body"));

        issue.setCreatedTime(getMandatoryDate(issueJson.getString("created_at")));
        issue.setUpdatedTime(getOptionalDate(issueJson.getString("updated_at")));
        if (!issueJson.isNull("closed_at"))
            issue.setClosedTime(getMandatoryDate(issueJson.getString("closed_at")));

        issue.setFixed(fixed);
        if (lastClosedDate != null && lastClosedDate.compareTo(lastReopenedDate) > 0) {
            issue.setClosed(true);
            assert issue.getClosedTime() != null;
        }

        issue.setLocked(issueJson.getBoolean("locked"));

        Collection<IssueComment> comments = new ArrayList<>();
        for (Comment remoteComment : remoteIssue.comments().iterate(issue.getCreatedTime())) {
            JsonObject commentJson = remoteComment.json();

            IssueComment comment = new IssueComment();
            comment.setAuthor(loadUser(commentJson.getJsonObject("user").getString("login")));
            comment.setBody(commentJson.getString("body"));
            comment.setCreatedTime(getMandatoryDate(commentJson.getString("created_at")));
            comment.setUpdatedTime(getMandatoryDate(commentJson.getString("updated_at")));
            comment.setUrl(commentJson.getString("html_url"));

            comment.setIssue(issue);

            comments.add(comment);
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

    private Commit loadCommit(Repo remoteRepository, String commitId) throws IOException {
        JsonObject commitJson = remoteRepository.commits().get(commitId).json();

        Collection<Commit.FileChange> changes = new ArrayList<>();
        JsonArray fileArray = commitJson.getJsonArray("files");
        for (JsonValue jsonValue : fileArray) {
            JsonObject fileJson = jsonValue.asJsonObject();
            Commit.FileChange fileChange = new Commit.FileChange();

            fileChange.setFileName(fileJson.getString("filename"));
            fileChange.setAddedLines(fileJson.getInt("additions"));
            fileChange.setRemovedLines(fileJson.getInt("deletions"));

            changes.add(fileChange);
        }

        Commit commit = new Commit();
        commit.setAuthor(loadUser(commitJson.getJsonObject("author").getString("login")));
        commit.setMessage(commitJson.getJsonObject("commit").getString("message"));
        commit.setChanges(changes);

        return commit;
    }

    private User loadUser(String userLogin) throws IOException {
        com.jcabi.github.User remoteUser = this.github.users().get(userLogin);
        User user = new User();
        user.setLogin(userLogin);
        user.setUrl(remoteUser.json().getString("html_url"));

        return user;
    }

    private Date getOptionalDate(String date) {
        if (date == null)
            return null;

        try {
            return GH_DATE.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date " + date + ". " + e.getMessage());
        }
    }

    private Date getMandatoryDate(String date) {
        assert date != null;

        try {
            return GH_DATE.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date " + date + ". " + e.getMessage());
        }
    }
}
