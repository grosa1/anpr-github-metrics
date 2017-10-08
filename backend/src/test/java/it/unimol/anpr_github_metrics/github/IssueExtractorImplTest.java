package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Repos;
import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorImplTest {

    @Before
    public void setup() {
    }

    @Test
    public void getContributors() throws Exception {
        IssueExtractorImpl implementor = new IssueExtractorImpl();

        Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN);
        implementor.setGithub(Authenticator.getInstance().getGitHub());

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        Collection<User> contributors = implementor.getContributors(repository);

        List<User> sortedContributors = new ArrayList<>(contributors);
        sortedContributors.sort(Comparator.comparing(User::getLogin));

        assertEquals(2, contributors.size());
        assertEquals("intersimone999", sortedContributors.get(0).getLogin());
        assertEquals("octocat", sortedContributors.get(1).getLogin());
    }

    @Test
    public void getFixedIssues() throws Exception {
        IssueExtractorImpl implementor = new IssueExtractorImpl();

        Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN);
        implementor.setGithub(Authenticator.getInstance().getGitHub());

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        Collection<Issue> issues = implementor.getFixedIssues(repository);

        assertEquals(0, issues.size());
    }

    @Test
    public void getFixedIssuesWithUser() throws Exception {
        //TODO write test
    }

    @Test
    public void getOpenIssues() throws Exception {
        IssueExtractorImpl implementor = new IssueExtractorImpl();

        Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN);
        implementor.setGithub(Authenticator.getInstance().getGitHub());

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        Collection<Issue> issues = implementor.getOpenIssues(repository);

        assertEquals(1, issues.size());
        assertEquals("intersimone999", new ArrayList<>(issues).get(0).getAuthor().getLogin());
    }

    @Test
    public void getOpenIssuesWithUser() throws Exception {
        //TODO write test
    }

    @Test
    public void getCommitsInvolvedInIssue() throws Exception {
        IssueExtractorImpl implementor = new IssueExtractorImpl();

        Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN);
        implementor.setGithub(Authenticator.getInstance().getGitHub());

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        //TODO get CLOSED issues, not FIXED (there is still no fixed issue in the test repo)
        List<Issue> issues = new ArrayList<>(implementor.getFixedIssues(repository));

        Collection<Commit> commitsInvolvedInIssue = implementor.getCommitsInvolvedInIssue(issues.get(0));
    }

}