package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.Authenticator;
import it.unimol.anpr_github_metrics.github.AuthenticatorTest;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import it.unimol.anpr_github_metrics.github.IssueExtractorImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Stefano Dalla Palma.
 */
public class OpenIssuesAnalyticsTest {
    public static final Github NullGithub = null;

    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(IssueExtractorFactory.InstantiationStrategy.TESTING);
    }

    @Test
    public void getCommentedOpenIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getLabeledOpenIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfCommentedOpenIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);
        int numberOfCommented = analytics.getNumberOfCommentedOpenIssues(repository.getName());

        assertEquals(2,numberOfCommented);
    }

    @Test
    public void getNumberOfLabeledOpenIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);
        int numberOflabeled = analytics.getNumberOfLabeledOpenIssues(repository.getName());

        assertEquals(2,numberOflabeled);
    }

    @Test
    public void getNumberOfOpenIssues() throws Exception {
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(NullGithub);
        int openIssues = analytics.getNumberOfOpenIssues("myRepoName");
        assertEquals(4, openIssues);
    }

    @Test
    public void getNumberOfUncommentedOpenIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);
        int numberOfUncommented = analytics.getNumberOfUncommentedOpenIssues(repository.getName());

        assertEquals(2,numberOfUncommented);
    }

    @Test
    public void getNumberOfUnlabeledOpenIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName("grosa1/Spoon-Knife");

        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);
        int numberOfUnlabeled = analytics.getNumberOfUnlabeledOpenIssues(repository.getName());

        assertEquals(2,numberOfUnlabeled);
    }

    @Test
    public void getUncommentedOpenIssue() throws Exception {
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(NullGithub);
        ArrayList<Issue> issues = analytics.getUncommentedOpenIssue("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(2, issues.size());
    }

    @Test
    public void getUnlabeledOpenIssue() throws Exception {
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(NullGithub);
        ArrayList<Issue> issues = analytics.getUnlabeledOpenIssues("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(2, issues.size());
    }

}
