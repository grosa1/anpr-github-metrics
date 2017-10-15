package it.unimol.anpr_github_metrics.github.proxies;

import com.jcabi.github.*;
import com.jcabi.http.Request;
import it.unimol.anpr_github_metrics.beans.Repository;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
public class GithubProxy implements Github, Proxy<Github> {
    private final Github origin;

    private ReposProxy repos;

    public GithubProxy(Github origin) {
        this.origin = origin;
    }

    @Override
    public Request entry() {
        return this.origin.entry();
    }

    @Override
    public Repos repos() {
        if (this.repos == null)
            this.repos = new ReposProxy(this, this.origin.repos());

        return this.repos;
    }

    @Override
    public Gists gists() {
        return this.origin.gists();
    }

    @Override
    public Users users() {
        return this.origin.users();
    }

    @Override
    public Organizations organizations() {
        return this.origin.organizations();
    }

    @Override
    public Markdown markdown() {
        return this.origin.markdown();
    }

    @Override
    public Limits limits() {
        return this.origin.limits();
    }

    @Override
    public Search search() {
        return this.origin.search();
    }

    @Override
    public Gitignores gitignores() throws IOException {
        return null;
    }

    @Override
    public JsonObject meta() throws IOException {
        return null;
    }

    @Override
    public JsonObject emojis() throws IOException {
        return null;
    }

    @Override
    public Github getOrigin() {
        return this.origin;
    }
}
