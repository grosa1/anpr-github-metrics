package it.unimol.anpr_github_metrics.session;

import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;

import javax.servlet.http.HttpSession;

/**
 * @author Simone Scalabrino.
 */
public class SessionHandler {
    public static final String GITHUB_TOKEN_KEY = "token";
    public static final String GITHUB_REPO_KEY = "repo";
    public static final String USER_KEY = "user";
    public static final String TIME_KEY = "time";

    private HttpSession session;

    public static SessionHandler getInstance(HttpSession session) {
        return new SessionHandler(session);
    }

    private SessionHandler(HttpSession session) {
        this.session = session;
    }

    public boolean isLoggedIn() {
        if (this.isSet(GITHUB_TOKEN_KEY)) {
            return true;
        }

        return false;
    }

    public void setToken(String value) {
        this.session.setAttribute(GITHUB_TOKEN_KEY, value);
    }

    public void setUser(User value) {
        this.session.setAttribute(USER_KEY, value);
    }

    public void setRepo(Repository value) {
        this.session.setAttribute(GITHUB_REPO_KEY, value);
    }

    public void setSessionDuration(String value) {
        this.session.setAttribute(TIME_KEY, value);
    }

    public String getToken() {
        return (String) this.session.getAttribute(GITHUB_TOKEN_KEY);
    }

    public User getUser() {
        return (User) this.session.getAttribute(USER_KEY);
    }

    public Repository getRepo() {
        return (Repository) this.session.getAttribute(GITHUB_REPO_KEY);
    }

    public Long getSessionDuration() {
        return (Long) this.session.getAttribute(TIME_KEY);
    }

    public boolean isSet(String field) {
        return session.getAttribute(field) != null;
    }
}
