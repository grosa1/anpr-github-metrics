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
     * @return
     */
    public static Authenticator getInstance() {
        if (instance == null)
            instance = new Authenticator();

        return instance;
    }

    /**
     * Returns an authenticate
     * @param username Username
     * @param password Password
     * @return a Github object
     */
    public Github authenticate(String username, String password) {
        //TODO implement
        throw new RuntimeException();
    }

    /**
     * Returns an authenticate
     * @param oauth Authentication token
     * @return A Github object
     */
    public Github authenticate(String oauth) {
        //TODO implement
        throw new RuntimeException();
    }
}
