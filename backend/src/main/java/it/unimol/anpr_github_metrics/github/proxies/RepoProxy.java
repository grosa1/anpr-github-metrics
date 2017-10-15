package it.unimol.anpr_github_metrics.github.proxies;

import com.jcabi.github.*;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
public class RepoProxy implements Repo, Proxy<Repo> {
    private final GithubProxy github;
    private final Coordinates coordinates;
    private final Repo origin;

    private IssuesProxy issuesProxy;

    public RepoProxy(GithubProxy github, Repo origin, Coordinates coordinates) {
        this.github = github;
        this.origin = origin;
        this.coordinates = coordinates;
    }

    @Override
    public Github github() {
        return github;
    }

    @Override
    public Coordinates coordinates() {
        return this.coordinates;
    }

    @Override
    public Issues issues() {
        if (this.issuesProxy == null)
            this.issuesProxy = new IssuesProxy(this, this.origin.issues());

        return this.issuesProxy;
    }

    @Override
    public Milestones milestones() {
        return this.origin.milestones();
    }

    @Override
    public Pulls pulls() {
        return this.origin.pulls();
    }

    @Override
    public Hooks hooks() {
        return this.origin.hooks();
    }

    @Override
    public IssueEvents issueEvents() {
        return this.origin.issueEvents();
    }

    @Override
    public Labels labels() {
        return this.origin.labels();
    }

    @Override
    public Assignees assignees() {
        return this.origin.assignees();
    }

    @Override
    public Releases releases() {
        return this.origin.releases();
    }

    @Override
    public DeployKeys keys() {
        return this.origin.keys();
    }

    @Override
    public Forks forks() {
        return this.origin.forks();
    }

    @Override
    public RepoCommits commits() {
        return this.origin.commits();
    }

    @Override
    public Branches branches() {
        return this.origin.branches();
    }

    @Override
    public Contents contents() {
        return this.origin.contents();
    }

    @Override
    public Collaborators collaborators() {
        return this.origin.collaborators();
    }

    @Override
    public Git git() {
        return this.origin.git();
    }

    @Override
    public Stars stars() {
        return this.origin.stars();
    }

    @Override
    public Notifications notifications() {
        return this.origin.notifications();
    }

    @Override
    public Iterable<Language> languages() throws IOException {
        return this.origin.languages();
    }

    @Override
    public void patch(JsonObject jsonObject) throws IOException {
        this.origin.patch(jsonObject);
    }

    @Override
    public JsonObject json() throws IOException {
        return this.origin.json();
    }

    @Override
    public Repo getOrigin() {
        return this.origin;
    }

    @Override
    public int compareTo(Repo repo) {
        return this.origin.compareTo(repo);
    }
}
