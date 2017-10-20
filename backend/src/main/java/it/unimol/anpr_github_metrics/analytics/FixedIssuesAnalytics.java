package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.GithubException;
import it.unimol.anpr_github_metrics.github.IssueExtractor;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is about fixed issues analytics
 * @author Stefano Dalla Palma
 */
public class FixedIssuesAnalytics extends IssuesAnalytics {

    public FixedIssuesAnalytics(Github github) {
        super(github);
    }

    /**
     * This method gets the mean time between a ticket creation and its fix
     *
     * @param repoName the name of the repository to analyze
     * @return a long indicating the average interval between the creation of a ticket and its fix
     * @throws GithubException if an error in encountered with github api
     */
    public long getAverageFixingTime(String repoName) throws Exception {
        throw new Exception("Not implemented yet.");
    }


    /**
     * This method gets the distribution of the intervals between a ticket creation and its fix
     *
     * @param repoName the name of the repository to analyze
     * @return a HashMap indicating the interval between a ticket creation and its fix's date (value) associated to a ticket (key)
     * @throws GithubException if an error in encountered with github api
     */
    public HashMap<Issue, Long> getFixingTimeDistribution(String repoName) throws Exception {
        throw new Exception("Not implemented yet.");
    }


    /**
     * This method returns all fixed issues having comments
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of fixed issues that have at least one comment
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getCommentedFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }


    /**
     * This method returns all fixed issues having labels
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of fixed issues that have at least one label
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getLabeledFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }


    /**
     * This method returns the number of fixed issues having comments
     *
     * @param repoName the name of the repository to analyze
     * @return an integer representing the number of fixed issues that have at least one comment
     * @throws GithubException if an error in encountered with github api
     */
    public int getNumberOfCommentedFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }

    /**
     * This method returns the number of fixed issues
     *
     * @param repoName the name of the repository to analyze
     * @return an integer representing the number of fixed issues
     * @throws GithubException if an error in encountered with github api
     */
    public int getNumberOfFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }


    /**
     * This method returns the number of fixed issues having labels
     *
     * @param repoName the name of the repository to analyze
     * @return an integer representing the number of fixed issues that have at least one label
     * @throws GithubException if an error in encountered with github api
     */
    public int getNumberOfLabeledFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }


    /**
     * This method returns the number of fixed issues having no comments
     *
     * @param repoName the name of the repository to analyze
     * @return an integer representing the number of fixed issues that have no comments
     * @throws GithubException if an error in encountered with github api
     */
    public int getNumberOfUncommentedFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }


    /**
     * This method returns the number of fixed issues having no labels
     *
     * @param repoName the name of the repository to analyze
     * @return an integer representing the number of fixed issues that have no labels
     * @throws GithubException if an error in encountered with github api
     */
    public int getNumberOfUnlabeledFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }


    /**
     * This method returns all fixed issues having no comment
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of fixed issues that have no comment
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getUncommentedFixedIssues(String repoName) throws GithubException {
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


    /**
     * This method returns all the fixed issues having no labels
     *
     * @param repoName the name of the repository to analyze
     * @return an ArrayList of fixed issues that have no labels
     * @throws GithubException if an error in encountered with github api
     */
    public ArrayList<Issue> getUnlabeledFixedIssues(String repoName) throws Exception {
        throw new Exception("Not implemented yet");
    }
}
