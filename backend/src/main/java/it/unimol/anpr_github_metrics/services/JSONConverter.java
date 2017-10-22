package it.unimol.anpr_github_metrics.services;

import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Stefano Dalla Palma
 */
public class JSONConverter {

    public static JSONArray issuesToJSON(final Collection<Issue> issues) {
        JSONArray array = new JSONArray();
        issues.forEach(issue -> {
            array.put(issueToJSON(issue));
        });

        return array;
    }

    public static JSONObject issueToJSON(final Issue issue) {
        JSONObject json = new JSONObject();

        json.put("number", issue.getNumber());
        json.put("title", issue.getTitle());
        json.put("body", issue.getBody());
        json.put("created_at", issue.getCreatedTime().getTime());
        if (issue.isClosed()) json.put("closed_at", issue.getClosedTime().getTime());
        if (null != issue.getUpdatedTime()) json.put("updated_at", issue.getUpdatedTime().getTime());
        json.put("closed", issue.isClosed());
        json.put("fixed", issue.isFixed());
        json.put("duplicated", issue.isDuplicated());
        json.put("invalid", issue.isInvalid());
        json.put("locked", issue.isLocked());

        json.put("author", userToJSON(issue.getAuthor()));

        // A Json object representing only the name and url of the repository
        JSONObject lightRepo = new JSONObject();
        lightRepo.put("name", issue.getRepository().getName());
        lightRepo.put("url", issue.getRepository().getUrl());

        json.put("repository", lightRepo);

        return json;
    }

    public static JSONObject repositoryToJSON(final Repository repo) {
        JSONObject json = new JSONObject();

        json.put("name", repo.getName());
        json.put("url", repo.getUrl());

        JSONArray issues = new JSONArray();
        repo.getIssues().forEach(issue -> {
            issues.put(issueToJSON(issue));
        });
        json.put("issues", issues);

        JSONArray contributors = new JSONArray();
        repo.getContributors().forEach(contributor -> {
            contributors.put(userToJSON(contributor));
        });
        json.put("contributors", contributors);

        return json;
    }

    public static JSONObject userToJSON(final User user) {
        JSONObject json = new JSONObject();

        json.put("login", user.getLogin());
        json.put("url", user.getUrl());

        return json;
    }
}