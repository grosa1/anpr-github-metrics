package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Github;

/**
 * @author Simone Scalabrino.
 */
public class Authenticator {
    private static Authenticator instance;

    public static Authenticator getInstance() {
        if (instance == null)
            instance = new Authenticator();

        return instance;
    }

    public Github authenticate(String username, String password) {
        //TODO implement
        throw new RuntimeException();
    }
}
