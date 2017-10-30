package it.unimol.anpr_github_metrics.session;

import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.servlets.basic.InconsistentSessionException;

import javax.servlet.http.HttpSession;

/**
 * @author Simone Scalabrino.
 */
public class SessionHandler {
    private HttpSession session;

    public static SessionHandler getInstance(HttpSession session) {
        return new SessionHandler(session);
    }

    private SessionHandler(HttpSession session) {
        this.session = session;
    }

    public User getUser() throws InconsistentSessionException {
        Object user = load(SessionKey.GITHUB_TOKEN);

        if (user == null) {
            return null;
        } else if (user instanceof User) {
            return (User)user;
        } else {
            throw new InconsistentSessionException("login user");
        }
    }

    public void setUser(User user) {
        save(SessionKey.LOGGED_USER, user);
    }

    public boolean isLoggedIn() {
        try {
            return this.getUser() != null;
        } catch (InconsistentSessionException e) {
            this.setUser(null);
            return false;
        }
    }

    public void setRepositories(Repository[] repositories) {
        save(SessionKey.MY_REPOSITORIES, repositories);
    }

    public Repository[] getRepositories() {
        Object repositories = load(SessionKey.MY_REPOSITORIES);

        if (repositories == null) {
            return null;
        } else if (repositories instanceof Repository[]) {
            return (Repository[])repositories;
        } else {
            throw new RuntimeException("Inconsistent session as for login user.");
        }
    }

    private void save(SessionKey key, Object value) {
        this.session.setAttribute(key.toString(), value);
    }

    private Object load(SessionKey key) {
        return this.session.getAttribute(key.toString());
    }

    public boolean hasField(SessionKey field) {
        return session.getAttribute(field.toString()) != null;
    }
}
