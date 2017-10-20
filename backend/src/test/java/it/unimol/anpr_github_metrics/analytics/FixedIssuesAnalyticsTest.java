package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FixedIssuesAnalyticsTest {
    public static final Github NullGithub = null;

    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(IssueExtractorFactory.InstantiationStrategy.TESTING);
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
        //TODO write test
    }

    @Test
    public void getNumberOfFixedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfLabeledFixedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfUncommentedFixedIssues() throws Exception {
        //TODO write test
    }


    @Test
    public void getNumberOfUnlabeledFixedIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getUncommentedFixedIssues() throws Exception {
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(NullGithub);
        ArrayList<Issue> issues = analytics.getUncommentedFixedIssues("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(0, issues.size());
    }

    @Test
    public void getUnlabeledFixedIssues() throws Exception {
        //TODO write test
    }
}
