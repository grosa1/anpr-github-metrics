package it.unimol.anpr_github_metrics.github.proxies;

import com.jcabi.github.*;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */

//TODO this proxy might be useless: double check and, if necessary, remove.
public class IssueProxy implements Issue, Proxy<Issue> {
    private final RepoProxy repo;
    private final Issue origin;

    public IssueProxy(RepoProxy repo, Issue origin) {
        this.repo = repo;
        this.origin = origin;
    }
    @Override
    public Repo repo() {
        return this.repo;
    }

    @Override
    public int number() {
        return this.origin.number();
    }

    @Override
    public Comments comments() {
        return this.origin.comments();
    }

    @Override
    public IssueLabels labels() {
        return this.origin.labels();
    }

    @Override
    public Iterable<Event> events() throws IOException {
        return this.origin.events();
    }

    @Override
    public boolean exists() throws IOException {
        return this.origin.exists();
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
    public Issue getOrigin() {
        return this.origin;
    }

    @Override
    public int compareTo(Issue issue) {
        return this.origin.compareTo(issue);
    }
}
