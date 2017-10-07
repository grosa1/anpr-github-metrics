package it.unimol.anpr_github_metrics.github;

import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.User;

import it.unimol.anpr_github_metrics.beans.Repository;

import java.util.Collection;
import java.util.List;

public interface IssueExtractor {

    /**
     * Get the list of all contributors of a repository
     * @param repository the repository
     * @return a list of User
     */
    public Collection<User> getContributors(Repository repository) throws GithubException;

    /**
     * Get the list of all the fixed issues in a repository
     * @param repository the repository
     * @return a list of fixed issues
     */
    public Collection<Issue> getFixedIssues(Repository repository) throws GithubException;

    /**
     * Get the list of all the fixed issues in a repository by a given user
     * @param user the user
     * @param repository the repository
     * @return a list of fixed issues
     */
    public Collection<Issue> getFixedIssues(User user, Repository repository) throws GithubException;

    /**
     * Get the list of all the opened issues in a repository
     * @param repository
     * @return
     */
    public Collection<Issue> getOpenIssues(Repository repository) throws GithubException;


    /**
     * Get the list of all the issues in a repository opened by a given user
     * @param user
     * @param repository
     * @return
     */
    public Collection<Issue> getOpenIssues(User user, Repository repository) throws GithubException;

    /**
     * Gets the list of all the commits involved in an issue
     * @param issue Issue
     * @return List of all the involved commits
     */
    public Collection<Commit> getCommitsInvolvedInIssue(Issue issue) throws GithubException;
}