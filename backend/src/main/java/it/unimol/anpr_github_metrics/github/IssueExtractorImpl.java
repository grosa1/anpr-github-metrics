package it.unimol.anpr_github_metrics.github;


import com.jcabi.github.*;
import it.unimol.anpr_github_metrics.beans.*;
import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.utils.DateUtils;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.IOException;
import java.security.cert.CollectionCertStoreParameters;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorImpl implements IssueExtractor {
    private Github github;

    public IssueExtractorImpl(Github github) {
        this.github = github;
    }

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

    public Collection<Issue> getClosedIssues(Repository repository) throws GithubException {
        Collection<Issue> allIssues = this.getIssues(repository);

        return allIssues.stream().filter(Issue::isClosed).collect(Collectors.toList());
    }

    public Collection<Issue> getClosedIssues(User user, Repository repository) throws GithubException {
        Collection<Issue> allIssues = this.getIssues(repository);

        return allIssues.stream().filter(issue -> issue.isClosed() && issue.getAuthor().equals(user)).collect(Collectors.toList());
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
            //Note that events older than 90 days are not reported!
            //TODO remove events dependency, just rely on issues, if possible
            for (Event event : remoteIssue.events()) {
                String eventType = event.json().getString("event");
                if (eventType.equals("closed") || eventType.equals("referenced")) {
                    if (!event.json().isNull("commit_id")) {
                        String commitId = event.json().getString("commit_id");

                        Commit commit = this.loadCommit(remoteRepository, commitId);
                        commits.add(commit);
                    }
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
            Map<String, String> params = new HashMap<>();
            params.put("state", "all");

            ExecutorService executorService = Executors.newCachedThreadPool();
            Collection<Callable<Issue>> callables = new ArrayList<>();

            for (com.jcabi.github.Issue remoteIssue : remoteRepository.issues().iterate(params)) {
                callables.add(new IssueLoaderThread(this.github, repository, remoteIssue));
            }

            try {
                List<Future<Issue>> futures = executorService.invokeAll(callables);

                for(Future<Issue> future : futures){
                    Issue issue = null;
                    try {
                        issue = future.get();
                        issues.add(issue);
                    } catch (InterruptedException | ExecutionException e) {
                        // Ignore the issue
                    }
                }

            } catch (InterruptedException e) {
                throw new GithubException();
            }

            repository.setIssues(issues);
        }
        return repository.getIssues();
    }

    private Commit loadCommit(Repo remoteRepository, String commitId) throws IOException {
        RepoCommit remoteCommit = remoteRepository.commits().get(commitId);
        return loadCommit(remoteCommit);
    }

    private Commit loadCommit(RepoCommit remoteCommit) throws IOException {
        JsonObject commitJson = remoteCommit.json();

        Collection<Commit.FileChange> changes = new ArrayList<>();
        JsonArray fileArray = commitJson.getJsonArray("files");
        for (JsonValue jsonValue : fileArray) {
            assert jsonValue instanceof JsonObject;
            JsonObject fileJson = (JsonObject) jsonValue;
            Commit.FileChange fileChange = new Commit.FileChange();

            fileChange.setFileName(fileJson.getString("filename"));
            fileChange.setAddedLines(fileJson.getInt("additions"));
            fileChange.setRemovedLines(fileJson.getInt("deletions"));

            changes.add(fileChange);
        }

        Commit commit = new Commit();
        commit.setHash(remoteCommit.sha());
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
}
