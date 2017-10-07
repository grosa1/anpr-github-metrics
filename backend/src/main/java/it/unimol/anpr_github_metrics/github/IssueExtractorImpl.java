package it.unimol.anpr_github_metrics.github;


import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorImpl implements IssueExtractor {
    private Github github;

    public void setGithub(Github github) {
        this.github = github;
    }

    public Collection<User> getContributors(Repository repository) throws GithubException {
        if (repository.getContributors() == null) {
            Repo remoteRepository = github.repos().get(new Coordinates.Simple(repository.getName()));

            List<User> contributors = new ArrayList<User>();
            for (com.jcabi.github.User remoteUser : remoteRepository.collaborators().iterate()) {
                User user = new User();
                try {
                    user.setLogin(remoteUser.login());
                } catch (IOException e) {
                    throw new GithubException();
                }

                // TODO set url by login
                user.setUrl("");

                contributors.add(user);
            }

            repository.setContributors(contributors);
        }

        return repository.getContributors();
    }

    public Collection<Issue> getFixedIssues(Repository repository) throws GithubException {
        //TODO implement
        throw new RuntimeException();
    }

    public Collection<Issue> getFixedIssues(User user, Repository repository) throws GithubException {
        //TODO implement
        throw new RuntimeException();
    }

    public Collection<Issue> getOpenIssues(Repository repository) throws GithubException {
        //TODO implement
        throw new RuntimeException();
    }

    public Collection<Issue> getOpenIssues(User user, Repository repository) throws GithubException {
        //TODO implement
        throw new RuntimeException();
    }

    public Collection<Commit> getCommitsInvolvedInIssue(Issue issue) throws GithubException {
        //TODO implement
        throw new RuntimeException();
    }
}
