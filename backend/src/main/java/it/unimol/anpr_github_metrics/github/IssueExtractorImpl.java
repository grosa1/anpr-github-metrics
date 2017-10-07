package it.unimol.anpr_github_metrics.github;


import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;

import java.util.List;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorImpl implements IssueExtractor {
    private Github github;

    public void setGithub(Github github) {
        this.github = github;
    }

    public List<User> getContributors(Repository repository) {
        //TODO implement
        throw new RuntimeException();
    }

    public List<Issue> getFixedIssues(Repository repository) {
        //TODO implement
        throw new RuntimeException();
    }

    public List<Issue> getFixedIssues(User user, Repository repository) {
        //TODO implement
        throw new RuntimeException();
    }

    public List<Issue> getOpenIssues(Repository repository) {
        //TODO implement
        throw new RuntimeException();
    }

    public List<Issue> getOpenIssues(User user, Repository repository) {
        //TODO implement
        throw new RuntimeException();
    }

    public List<Commit> getCommitsInvolvedInIssue(Issue issue) {
        //TODO implement
        throw new RuntimeException();
    }
}
