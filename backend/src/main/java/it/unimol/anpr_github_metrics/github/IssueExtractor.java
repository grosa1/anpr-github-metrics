package it.unimol.anpr_github_metrics.github;

import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.User;

import it.unimol.anpr_github_metrics.beans.Repository;

import java.util.List;

public interface IssueExtractor {

    /**
     * Get the list of all contributors of a repository
     * @param repository the repository
     * @return a list of User
     */
    public List<User> getContributors(Repository repository);

    /**
     * Get the list of all the fixed issues in a repository
     * @param repository the repository
     * @return a list of fixed issues
     */
    public List<Issue> getFixedIssues(Repository repository);

    /**
     * Get the list of all the fixed issues in a repository by a given user
     * @param user the user
     * @param repository the repository
     * @return a list of fixed issues
     */
    public List<Issue> getFixedIssues(User user, Repository repository);

    /**
     * Get the list of all the opened issues in a repository
     * @param repository
     * @return
     */
    public List<Issue> getOpenIssues(Repository repository);


    /**
     * Get the list of all the issues in a repository opened by a given user
     * @param user
     * @param repository
     * @return
     */
    public List<Issue> getOpenIssues(User user, Repository repository);

    /**
     * Gets the list of all the commits involved in an issue
     * @param issue Issue
     * @return List of all the involved commits
     */
    public List<Commit> getCommitsInvolvedInIssue(Issue issue);
}