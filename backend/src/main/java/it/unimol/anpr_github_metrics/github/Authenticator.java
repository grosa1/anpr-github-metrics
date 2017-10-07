package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;

/**
 * Authenticates a GitHub user
 * @author Simone Scalabrino.
 */
public class Authenticator {
    private static Authenticator instance;
    private RtGithub github;

    /**
     * Returns an authenticator instance
     * @return Instance of the authenticator
     */
    public static Authenticator getInstance() {
        if (instance == null)
            instance = new Authenticator();

        return instance;
    }

    /**
     * Authenticates the user
     * @param username Username
     * @param password Password
     */
    public void authenticate(String username, String password) {
        this.github = new RtGithub(username, password);
    }

    /**
     * Authenticates the user
     * @param oauth Authentication token
     * @return A Github object
     */
    public void authenticate(String oauth) {
        this.github = new RtGithub(oauth);
    }

    /**
     * Returns the Github object for queries
     * @return Github object
     */
    public Github getGitHub() {
        return this.github;
    }
}
