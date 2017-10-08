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
        // TODO: Cambiare con RepositoryExtractorImpl.getRepository.getIssues();

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getFixedIssues(repository));
        issues.addAll(new ArrayList<>(issueFactory.getFixedIssues(repository)));

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
        // TODO: Cambiare con RepositoryExtractorImpl.getRepository.getIssues();

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getFixedIssues(repository));
        issues.addAll(new ArrayList<>(issueFactory.getFixedIssues(repository)));

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
        // TODO: Cambiare con RepositoryExtractorImpl.getRepository.getIssues();

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getFixedIssues(repository));
        issues.addAll(new ArrayList<>(issueFactory.getFixedIssues(repository)));

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
        // TODO: Cambiare con RepositoryExtractorImpl.getRepository.getIssues();

        ArrayList<Issue> issues = new ArrayList<>(issueFactory.getFixedIssues(repository));
        issues.addAll(new ArrayList<>(issueFactory.getFixedIssues(repository)));

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

        return issueFactory.getFixedIssues(repository).size();
    }
}
