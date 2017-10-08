package it.unimol.anpr_github_metrics.analytics;

import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;
import it.unimol.anpr_github_metrics.github.IssueExtractorImpl;
import it.unimol.anpr_github_metrics.github.IssueExtractorStub;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class AnalyticsTest {
    @Test
    public void getMeanFirstResponseTime() throws Exception {
        IssueExtractorFactory.setImplementor(new IssueExtractorStub());
        Analytics analytics = new Analytics();
        long meanFirstResponseTime = analytics.getMeanFirstResponseTime("myRepoName");
        assertEquals(10,meanFirstResponseTime);
    }

    @Test
    public void getFirstTimeDistribution() throws Exception {
    }

}