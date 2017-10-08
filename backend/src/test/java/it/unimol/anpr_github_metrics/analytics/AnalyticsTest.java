package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import it.unimol.anpr_github_metrics.github.IssueExtractorStub;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class AnalyticsTest {
    public static final Github NullGithub = null;
    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(IssueExtractorFactory.InstantiationStrategy.TESTING);
    }

    @Test
    public void getMeanFirstResponseTime() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        long meanFirstResponseTime = analytics.getMeanFirstResponseTime("myRepoName");
        assertEquals(15225000,meanFirstResponseTime);
    }

    @Test
    public void getFirstTimeDistribution() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        HashMap<Issue, Long> firstResponseTime = analytics.getFirstResponseTimeDistribution("myRepoName");
        assertEquals(HashMap.class, firstResponseTime.getClass());
    }

    @Test
    public void getMeanTicketClosingTime() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        long meanClosingTime = analytics.getMeanTicketClosingTime("myRepoName");
        assertEquals(143400000, meanClosingTime);
    }


    @Test
    public void getNumberOfOpenIssues() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        long openIssue = analytics.getNumberOfOpenIssues("myRepoName");
        assertEquals(4, openIssue);
    }

    @Test
    public void getOpenIssueWithoutComment() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        ArrayList<Issue> issues = analytics.getOpenIssueWithoutComment("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(0, issues.size());
    }


    @Test
    public void getOpenIssueWithoutLabel() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        ArrayList<Issue> issues = analytics.getOpenIssueWithoutLabel("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(4, issues.size());
    }


    @Test
    public void getTimeToLastComment() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        HashMap<Issue, Long> issueTime = analytics.getTimeToLastComment("myRepoName");
        assertEquals(HashMap.class, issueTime.getClass());
    }


    @Test
    public void getClosedIssueWithoutComment() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        ArrayList<Issue> issues = analytics.getClosedIssueWithoutComment("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(2, issues.size());
    }


    @Test
    public void getFixedIssueWithoutComment() throws Exception {
        Analytics analytics = new Analytics(NullGithub);
        ArrayList<Issue> issues = analytics.getFixedIssueWithoutComment("myRepoName");
        assertEquals(ArrayList.class, issues.getClass());
        assertEquals(0, issues.size());
    }
}