package it.unimol.anpr_github_metrics.beans;

/**
 * @author Simone Scalabrino.
 */
public class User {
    private String login;
    private String url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User user = (User) o;

            return this.login.equals(user.login);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.login.hashCode();
    }
}
