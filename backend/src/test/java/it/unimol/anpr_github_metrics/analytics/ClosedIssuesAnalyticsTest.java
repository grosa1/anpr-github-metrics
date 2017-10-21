package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.Authenticator;
import it.unimol.anpr_github_metrics.github.AuthenticatorTest;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClosedIssuesAnalyticsTest {
    public static final Github NullGithub = null;
    public static final String RepositoryName = "grosa1/Spoon-Knife";
    public static final IssueExtractorFactory.InstantiationStrategy Environment = IssueExtractorFactory.InstantiationStrategy.TESTING;

    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(Environment);
    }

    @Test
    public void getAverageClosingTime() throws Exception {
        //TODO write test
    }

    @Test
    public void getClosingTimeDistribution() throws Exception {
        //TODO write test
    }

    @Test
    public void getCommentedClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getLabeledClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfClosedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        int closedIssues = analytics.getNumberOfClosedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(4, closedIssues);
                break;

            case PRODUCTION:
                assertEquals(9, closedIssues);
                break;
        }
    }

    @Test
    public void getNumberOfCommentedClosedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        int numberOfCommented = analytics.getNumberOfCommentedClosedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOfCommented);
                break;

            case PRODUCTION:
                assertEquals(4, numberOfCommented);
                break;
        }
    }

    @Test
    public void getNumberOfLabeledClosedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        int numberOflabeled = analytics.getNumberOfLabeledClosedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOflabeled);
                break;

            case PRODUCTION:
                assertEquals(4, numberOflabeled);
                break;
        }
    }

    @Test
    public void getNumberOfUncommentedClosedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        int numberOfUncommented = analytics.getNumberOfUncommentedClosedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOfUncommented);
                break;

            case PRODUCTION:
                assertEquals(5, numberOfUncommented);
                break;
        }
    }

    @Test
    public void getNumberOfUnlabeledClosedIssues() throws Exception {
        Github github = Authenticator.getInstance().authenticate(AuthenticatorTest.TOKEN).getGitHub();

        Repository repository = new Repository();
        repository.setName(RepositoryName);

        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        int numberOfUnlabeled = analytics.getNumberOfUnlabeledClosedIssues(repository.getName());

        switch (Environment){
            case TESTING:
                assertEquals(2,numberOfUnlabeled);
                break;

            case PRODUCTION:
                assertEquals(5, numberOfUnlabeled);
                break;
        }
    }

    @Test
    public void getUncommentedClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getUnlabeledClosedIssues() throws Exception {
        //TODO write test
    }

}
