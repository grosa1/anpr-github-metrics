package it.unimol.anpr_github_metrics.analytics;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class IssuesAnalyticsTest {
    public static final Github NullGithub = null;

    @Before
    public void setup() {
        IssueExtractorFactory.setImplementorStrategy(IssueExtractorFactory.InstantiationStrategy.TESTING);
    }

    @Test
    public void getAverageFirstResponseTime() throws Exception {
        IssuesAnalytics analytics = new IssuesAnalytics(NullGithub);
        long meanFirstResponseTime = analytics.getAverageFirstResponseTime("myRepoName");
        assertEquals(15225000,meanFirstResponseTime);
    }

    @Test
    public void getAverageTimeFromLastComment() throws Exception {
        //TODO write test
    }

    @Test
    public void getFirstResponseTimeDistribution() throws Exception {
        IssuesAnalytics analytics = new IssuesAnalytics(NullGithub);
        HashMap<Issue, Long> firstResponseTime = analytics.getFirstResponseTimeDistribution("myRepoName");
        assertEquals(HashMap.class, firstResponseTime.getClass());
    }

    @Test
    public void getTimesFromLastComment() throws Exception {
        IssuesAnalytics analytics = new IssuesAnalytics(NullGithub);
        HashMap<Issue, Long> issueTime = analytics.getTimesFromLastComment("myRepoName");
        assertEquals(HashMap.class, issueTime.getClass());
    }
}