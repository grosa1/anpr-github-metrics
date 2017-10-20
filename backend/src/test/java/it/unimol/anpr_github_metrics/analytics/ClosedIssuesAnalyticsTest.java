package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ClosedIssuesAnalyticsTest {
    public static final Github NullGithub = null;

    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(IssueExtractorFactory.InstantiationStrategy.TESTING);
    }

    @Test
    public void getAverageClosingTime() throws Exception {
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(NullGithub);
        long meanClosingTime = analytics.getAverageClosingTime("myRepoName");
        assertEquals(143400000, meanClosingTime);
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
    public void getNumberOfCommentedClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfLabeledClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfUncommentedClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfUnlabeledClosedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getUncommentedClosedIssues() throws Exception {
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(NullGithub);
        ArrayList<Issue> issues = analytics.getUncommentedClosedIssues("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(2, issues.size());
    }

    @Test
    public void getUnlabeledClosedIssues() throws Exception {
        //TODO write test
    }

}
