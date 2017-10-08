package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Simone Scalabrino.
 */
public class AuthenticatorTest {
    public static final String TOKEN = "d34794572238fff111575134a1c13bc3c403e6fa";

    @Test
    public void test() throws IOException {
        assertNotNull(TOKEN);

        Github github = Authenticator.getInstance().authenticate(TOKEN).getGitHub();
        assertNotNull(github);

        Repo repo = github.repos().get(new Coordinates.Simple("intersimone999/anpr-github-metrics"));
        assertNotNull(repo);
        assertNotNull(repo.json().toString());
    }
}