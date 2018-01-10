package it.unimol.anpr_github_metrics.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

/**
 * @author Simone Scalabrino.
 */
public class Repository {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String apiUrl; //GITHUB API URL
    @SerializedName("html_url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pushed_at")
    @Expose
    private String pushed_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("fork")
    @Expose
    private boolean isFork;
    @SerializedName("homepage")
    @Expose
    private String homepageUrl;

    private Collection<Issue> issues;
    private Collection<User> contributors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Collection<Issue> issues) {
        this.issues = issues;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isFork() {
        return isFork;
    }

    public void setFork(boolean fork) {
        isFork = fork;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public Collection<User> getContributors() {
        return contributors;
    }

    public void setContributors(Collection<User> contributors) {
        this.contributors = contributors;
    }
}
