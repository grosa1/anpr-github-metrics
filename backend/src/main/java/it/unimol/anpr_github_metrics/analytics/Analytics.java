package it.unimol.anpr_github_metrics.analytics;

import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.IssueComment;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.GithubException;
import it.unimol.anpr_github_metrics.github.IssueExtractor;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;

import java.util.*;

/**
 * This class of services handles the mean first response time of an issue, i.e., the mean response time of the first comment of an issue
 * applications
 *
 * @author Code Warrior Team.
 */
public class Analytics {

    public long getMeanFirstResponseTime(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        long meanReponseTime = 0L;

        for (Issue issue : issues) {
            List<IssueComment> comments = new ArrayList<>(issue.getComments());
            comments.sort(Comparator.comparing(IssueComment::getCreatedTime));

            meanReponseTime += comments.get(0).getCreatedTime().getTime() - issue.getCreatedTime().getTime();
        }

        meanReponseTime /= issues.size();
        return meanReponseTime;
    }

    public HashMap<Issue, Long> getFirstResponseTimeDistribution(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        HashMap<Issue, Long> distribution = new HashMap<>();

        for (Issue issue : issues) {
            List<IssueComment> comments = new ArrayList<>(issue.getComments());
            comments.sort(Comparator.comparing(IssueComment::getCreatedTime));

            long firstResponseTime = comments.get(0).getCreatedTime().getTime() - issue.getCreatedTime().getTime();
            distribution.put(issue, firstResponseTime);
        }

        return distribution;
    }


    public long getMeanTicketClosingTime(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        long meanClosingTime = 0L;

        for (Issue issue : issues) {
            meanClosingTime += issue.getClosedTime().getTime() - issue.getCreatedTime().getTime();
        }

        meanClosingTime /= issues.size();
        return meanClosingTime;
    }

    public HashMap<Issue, Long> getTicketClosingTimeDistribution(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        HashMap<Issue, Long> distribution = new HashMap<>();

        for (Issue issue : issues) {

            long timeOpened = issue.getClosedTime().getTime() - issue.getCreatedTime().getTime();
            distribution.put(issue, timeOpened);
        }

        return distribution;
    }

    public int getNumberOfOpenIssues(String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        return issueFactory.getOpenIssues(repository).size();
    }


    public ArrayList<Issue> getOpenIssueWithoutComment (String repoName) throws GithubException {
        Repository repository = new Repository();
        repository.setName(repoName);
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        ArrayList<Issue> openIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getOpenIssues(repository))){
            if (issue.getComments().isEmpty()) {
                openIssue.add(issue);
            }
        }

        return openIssue;
    }

    public ArrayList<Issue> getOpenIssueWithoutLabel (String repoName) throws GithubException {
        Repository repository = new Repository();
        repository.setName(repoName);
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        ArrayList<Issue> openIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getOpenIssues(repository))){
            if (issue.getLabels().isEmpty()) {
                openIssue.add(issue);
            }
        }

        return openIssue;
    }

    public HashMap<Issue, Long> getTimeToLastComment (String repoName) throws GithubException {
        long actualDate = new Date().getTime();
        long lastCommentDate = 0;

        HashMap<Issue, Long> inactivityIssueTime = new HashMap<>();
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);
        ArrayList<Issue> issueList = (ArrayList<Issue>) issueFactory.getIssues(repository);

        for (Issue issue : issueList) {
            if (issue.getUpdatedTime() != null) {
                lastCommentDate = issue.getUpdatedTime().getTime();
                inactivityIssueTime.put(issue, actualDate - lastCommentDate);
            }
        }

        return inactivityIssueTime;
    }


    public ArrayList<Issue> getClosedIssueWithoutComment (String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> closedIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getClosedIssues(repository))) {
            if (issue.getComments().isEmpty()) {
                closedIssue.add(issue);
            }
        }

        return closedIssue;
    }

    public ArrayList<Issue> getFixedIssueWithoutComment (String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance();
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> closedIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getClosedIssues(repository))){
            if (issue.getComments().isEmpty()) {
                closedIssue.add(issue);
            }
        }

        return closedIssue;
    }
}
