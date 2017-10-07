package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Simone Scalabrino.
 */
public class AuthenticatorTest {

    @Test
    public void test() throws IOException {
        String token = "f86035f8bc5c425839f9b4fec06fcc441e8b8d30";
        assertNotNull(token);

        Authenticator.getInstance().authenticate(token);

        Github github = Authenticator.getInstance().getGitHub();
        assertNotNull(github);

        Repo repo = github.repos().get(new Coordinates.Simple("intersimone999/anpr-github-metrics"));
        assertNotNull(repo);
        assertNotNull(repo.json().toString());
    }
}