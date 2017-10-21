package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.Authenticator;
import it.unimol.anpr_github_metrics.github.AuthenticatorTest;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class FixedIssuesAnalyticsTest {
    public static final Github NullGithub = null;
    public static final String RepositoryName = "grosa1/Spoon-Knife";
    public static final IssueExtractorFactory.InstantiationStrategy Environment = IssueExtractorFactory.InstantiationStrategy.TESTING;

    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(Environment);
    }

    @Test
    public void getAverageFixingTime() throws Exception {
        //TODO write test
    }

    @Test
    public void getFixingTimeDistribution() throws Exception {
        //TODO write test
    }

    @Test
    public void getCommentedFixedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getLabeledFixedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfCommentedFixedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        int numberOfCommented = analytics.getNumberOfCommentedFixedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOfCommented);
                break;

            case PRODUCTION:
                assertEquals(2, numberOfCommented);
                break;
        }
    }

    @Test
    public void getNumberOfFixedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        int closedIssues = analytics.getNumberOfFixedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(4, closedIssues);
                break;

            case PRODUCTION:
                assertEquals(4, closedIssues);
                break;
        }
    }

    @Test
    public void getNumberOfLabeledFixedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        int numberOflabeled = analytics.getNumberOfLabeledFixedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOflabeled);
                break;

            case PRODUCTION:
                assertEquals(2, numberOflabeled);
                break;
        }
    }

    @Test
    public void getNumberOfUncommentedFixedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        int numberOfUncommented = analytics.getNumberOfUncommentedFixedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOfUncommented);
                break;

            case PRODUCTION:
                assertEquals(2, numberOfUncommented);
                break;
        }
    }


    @Test
    public void getNumberOfUnlabeledFixedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        int numberOfUnlabeled = analytics.getNumberOfUnlabeledFixedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOfUnlabeled);
                break;

            case PRODUCTION:
                assertEquals(2, numberOfUnlabeled);
                break;
        }
    }

    @Test
    public void getUncommentedFixedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getUnlabeledFixedIssues() throws Exception {
        //TODO write test
    }
}
