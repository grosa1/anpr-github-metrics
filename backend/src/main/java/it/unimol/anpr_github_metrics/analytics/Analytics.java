package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.IssueComment;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.GithubException;
import it.unimol.anpr_github_metrics.github.IssueExtractor;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;

import java.util.*;

/**
 * This class implements the services' logic for analytics
 *
 * @author Code Warrior Team.
 */
public class Analytics {
    private final Github github;

    public Analytics(Github github) {
        this.github = github;
    }

    /**
     * This method gets the mean time between a ticket creation and the first comment
     *
     * @param repoName the name of the repository to analyze
     * @return a long indicating the average interval between the creation of a ticket and first comment expressed in milliseconds
     * @throws GithubException if an error in encountered with github api
     */
    public long getMeanFirstResponseTime(String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        long meanReponseTime = 0L;
        int total = 0;

        for (Issue issue : issues) {
            List<IssueComment> comments = new ArrayList<>(issue.getComments());
            comments.sort(Comparator.comparing(IssueComment::getCreatedTime));

            if (!comments.isEmpty()) {
                meanReponseTime += comments.get(0).getCreatedTime().getTime() - issue.getCreatedTime().getTime();
                total++;
            }
        }

        //meanReponseTime /= issues.isEmpty() ? 1 : issues.size();
        meanReponseTime /= total == 0 ? 1 : total;
        return meanReponseTime;
    }

    /**
     * This method gets the distribution of the intervals between a ticket creation and the first comment
     *
     * @param repoName the name of the repository to analyze
     * @return a HashMap indicating the interval between a ticket creation and the first comment date (value) associated to a ticket (key)
     * @throws GithubException if an error in encountered with github api
     */
    public HashMap<Issue, Long> getFirstResponseTimeDistribution(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        HashMap<Issue, Long> distribution = new HashMap<>();

        for (Issue issue : issues) {
            List<IssueComment> comments = new ArrayList<>(issue.getComments());
            comments.sort(Comparator.comparing(IssueComment::getCreatedTime));

            if (!comments.isEmpty()) {
                long firstResponseTime = comments.get(0).getCreatedTime().getTime() - issue.getCreatedTime().getTime();
                distribution.put(issue, firstResponseTime);
            }

        }

        return distribution;
    }


    /**
     * This method gets the mean time between a ticket creation and its closing
     *
     * @param repoName the name of the repository to analyze
     * @return a long indicating the average interval between the creation of a ticket and its closing
     * @throws GithubException if an error in encountered with github api
     */
    public long getMeanTicketClosingTime(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        long meanClosingTime = 0L;
        int total = 0;
        for (Issue issue : issues) {
            if (issue.getClosedTime() != null) {
                meanClosingTime += issue.getClosedTime().getTime() - issue.getCreatedTime().getTime();
                total++;
            }
        }

        meanClosingTime /= total == 0 ? 1 : total;
        return meanClosingTime;
    }

    /**
     * This method gets the distribution of the intervals between a ticket creation and its closing
     *
     * @param repoName the name of the repository to analyze
     * @return a HashMap indicating the interval between a ticket creation and its creation date (value) associated to a ticket (key)
     * @throws GithubException if an error in encountered with github api
     */
    public HashMap<Issue, Long> getTicketClosingTimeDistribution(String repoName) throws GithubException {

        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getIssues(repository));

        HashMap<Issue, Long> distribution = new HashMap<>();

        for (Issue issue : issues) {

            if (issue.getClosedTime() != null) {
                long timeOpened = issue.getClosedTime().getTime() - issue.getCreatedTime().getTime();
                distribution.put(issue, timeOpened);
            }
        }

        return distribution;
    }

    /**
     * This method returns the number of ticket currently open
     *
     * @param repoName the name of the repository to analyze
     * @return an integer representing the number of tickets open
     * @throws GithubException if an error in encountered with github api
     */
    public int getNumberOfOpenIssues(String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        Repository repository = new Repository();
        repository.setName(repoName);

        return issueFactory.getOpenIssues(repository).size();
    }


    /**
     * This method returns all the issues having no comment
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of open issues that have no comment
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getOpenIssueWithoutComment(String repoName) throws GithubException {
        Repository repository = new Repository();
        repository.setName(repoName);
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        ArrayList<Issue> openIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getOpenIssues(repository))) {
            if (issue.getComments().isEmpty()) {
                openIssue.add(issue);
            }
        }

        return openIssue;
    }

    /**
     * This method returns all the open issues having no label
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of open issues that have no label
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getOpenIssueWithoutLabel(String repoName) throws GithubException {
        Repository repository = new Repository();
        repository.setName(repoName);
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        ArrayList<Issue> openIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getOpenIssues(repository))) {
            if (issue.getLabels().isEmpty()) {
                openIssue.add(issue);
            }
        }

        return openIssue;
    }

    /**
     * This method returns a map of issues and the associated time from latest comment
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of open issues that have no comment
     * @throws GithubException if an error in encountered with github api
     */
    public HashMap<Issue, Long> getTimeToLastComment(String repoName) throws GithubException {
        long actualDate = new Date().getTime();
        long lastCommentDate = 0;

        HashMap<Issue, Long> inactivityIssueTime = new HashMap<>();
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
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


    /**
     * This method returns all closed issues having no comment
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of closed issues that have no comment
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getClosedIssueWithoutComment(String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
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

    /**
     * This method returns all fixed issues having no comment
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of fixed issues that have no comment
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getFixedIssueWithoutComment(String repoName) throws GithubException {
        IssueExtractor issueFactory = IssueExtractorFactory.getInstance(this.github);
        Repository repository = new Repository();
        repository.setName(repoName);

        ArrayList<Issue> fixedIssue = new ArrayList<>();

        for (Issue issue : new ArrayList<>(issueFactory.getFixedIssues(repository))) {
            if (issue.getComments().isEmpty()) {
                fixedIssue.add(issue);
            }
        }

        return fixedIssue;
    }
}
