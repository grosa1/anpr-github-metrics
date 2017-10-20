package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        //TODO write test
    }

    @Test
    public void getNumberOfLabeledOpenIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfOpenIssues() throws Exception {
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(NullGithub);
        long openIssue = analytics.getNumberOfOpenIssues("myRepoName");
        assertEquals(4, openIssue);
    }

    @Test
    public void getNumberOfUncommentedOpenIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getNumberOfUnlabeledOpenIssues() throws Exception {
        //TODO write test
    }

    @Test
    public void getUncommentedOpenIssue() throws Exception {
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(NullGithub);
        ArrayList<Issue> issues = analytics.getUncommentedOpenIssue("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(0, issues.size());
    }

    @Test
    public void getUnlabeledOpenIssue() throws Exception {
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(NullGithub);
        ArrayList<Issue> issues = analytics.getUnlabeledOpenIssues("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(4, issues.size());
    }

}
