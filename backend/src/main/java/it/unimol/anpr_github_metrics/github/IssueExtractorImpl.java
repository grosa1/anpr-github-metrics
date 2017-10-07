package it.unimol.anpr_github_metrics.github;


import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;

import java.util.List;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorImpl implements IssueExtractor {
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
}
