package it.unimol.anpr_github_metrics.session;

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

    public boolean isLoggedIn() {
        if (this.isSet(SessionKey.GITHUB_TOKEN) && this.isSet(SessionKey.LOGGED_USERNAME)) {
            return true;
        }

        return false;
    }

    public void store(SessionKey key, Object value) {
        this.session.setAttribute(key.toString(), value);
    }

    public String load(SessionKey key) {
        return (String) this.session.getAttribute(key.toString());
    }

    public boolean isSet(SessionKey field) {
        return session.getAttribute(field.toString()) != null;
    }
}
