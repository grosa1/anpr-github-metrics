package it.unimol.anpr_github_metrics.beans;

import java.util.Collection;
import java.util.List;

/**
 * @author Simone Scalabrino.
 */
public class Repository {
    private String name;
    private String url;

    private Collection<Issue> issues;
    private Collection<User> contributors;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Collection<Issue> issues) {
        this.issues = issues;
    }

    public Collection<User> getContributors() {
        return contributors;
    }

    public void setContributors(Collection<User> contributors) {
        this.contributors = contributors;
    }
}
