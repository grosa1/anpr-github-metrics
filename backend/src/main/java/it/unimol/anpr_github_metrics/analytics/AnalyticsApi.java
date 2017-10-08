package it.unimol.anpr_github_metrics.analytics;


import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.IssueComment;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.github.GithubException;
import it.unimol.anpr_github_metrics.github.IssueExtractor;
import it.unimol.anpr_github_metrics.github.IssueExtractorFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

/**
 * This class of services handles the mean first response time of an issue, i.e., the mean response time of the first comment of an issue
 * applications
 * @author Code Warrior Team.
 */
@Path("/analytics")
public class AnalyticsApi {

    @GET
    @Path("/mean-first-response-time/{repository-name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeanFirstResponseTime(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            Long meanFirstResponseTime = analytics.getMeanFirstResponseTime(repoName);
            return Response.status(Response.Status.OK).entity(meanFirstResponseTime).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/first-response-time-distribution/{repository-name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFirstTimeDistribution(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            HashMap<Issue, Long> firstResponseTimeDistribution = analytics.getFirstResponseTimeDistribution(repoName);
            return Response.status(Response.Status.OK).entity(firstResponseTimeDistribution).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
