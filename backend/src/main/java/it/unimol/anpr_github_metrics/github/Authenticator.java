package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Github;

/**
 * Authenticates a GitHub user
 * @author Simone Scalabrino.
 */
public class Authenticator {
    private static Authenticator instance;

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
        //TODO implement
        throw new RuntimeException();
    }

    /**
     * Authenticates the user
     * @param oauth Authentication token
     * @return A Github object
     */
    public void authenticate(String oauth) {
        //TODO implement
        throw new RuntimeException();
    }

    /**
     * Returns the Github object for queries
     * @return Github object
     */
    public Github getGitHub() {
        //TODO implement
        throw new RuntimeException();
    }
}
