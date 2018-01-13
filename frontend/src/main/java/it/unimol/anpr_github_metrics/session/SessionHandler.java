package it.unimol.anpr_github_metrics.session;

import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;

import javax.servlet.http.HttpSession;

/**
 * @author Simone Scalabrino.
 */
public class SessionHandler {
    public static final String GITHUB_TOKEN = "token";
    public static final String GITHUB_REPO = "repo";
    public static final String USER = "user";
    public static final String TIME = "time";

    private HttpSession session;

    public static SessionHandler getInstance(HttpSession session) {
        return new SessionHandler(session);
    }

    private SessionHandler(HttpSession session) {
        this.session = session;
    }

    public boolean isLoggedIn() {
        if (this.isSet(GITHUB_TOKEN) && this.isSet(USER)) {
            return true;
        }

        return false;
    }

    public void invalidateRepo() {
        this.session.removeAttribute(GITHUB_REPO);
    }

    public void logout() {
        this.session.invalidate();
    }

    public void setToken(String value) {
        this.session.setAttribute(GITHUB_TOKEN, value);
    }

    public void setUser(User value) {
        this.session.setAttribute(USER, value);
    }

    public void setRepo(Repository value) {
        this.session.setAttribute(GITHUB_REPO, value);
    }

    public void setSessionDuration(String value) {
        this.session.setAttribute(TIME, value);
    }

    public String getToken() {
        return (String) this.session.getAttribute(GITHUB_TOKEN);
    }

    public User getUser() {
        return (User) this.session.getAttribute(USER);
    }

    public Repository getRepo() {
        return (Repository) this.session.getAttribute(GITHUB_REPO);
    }

    public Long getSessionDuration() {
        return (Long) this.session.getAttribute(TIME);
    }

    public boolean isSet(String field) {
        return session.getAttribute(field) != null;
    }
}
