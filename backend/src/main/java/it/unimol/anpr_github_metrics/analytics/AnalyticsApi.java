package it.unimol.anpr_github_metrics.analytics;


import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.GithubException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class of services handles the end-point api of services
 * @author Code Warrior Team.
 */
@Path("/analytics")
public class AnalyticsApi {

    @GET
    @Path("/mean-first-response-time/{repository-name}")
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

    @GET
    @Path("/mean-ticket-closing-time/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeanTicketClosingTime(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            Long meanTicketClosingTime = analytics.getMeanTicketClosingTime(repoName);
            return Response.status(Response.Status.OK).entity(meanTicketClosingTime).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/ticket-closing-time-distribution/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketClosingTimeDistribution(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            HashMap<Issue, Long> ticketClosingTimeDistribution = analytics.getTicketClosingTimeDistribution(repoName);
            return Response.status(Response.Status.OK).entity(ticketClosingTimeDistribution).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/number-of-open-issues/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfOpenIssues(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            int numberOfOpenIssues = analytics.getNumberOfOpenIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfOpenIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/open-issues-without-comment/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpenWithoutComment(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            ArrayList<Issue> issues = analytics.getOpenIssueWithoutComment(repoName);
            return Response.status(Response.Status.OK).entity(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/open-issues-without-label/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpenIssueWithoutLabel(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            ArrayList<Issue> issues = analytics.getOpenIssueWithoutLabel(repoName);
            return Response.status(Response.Status.OK).entity(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/time-to-last-commit/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeToLastComment(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            HashMap<Issue, Long> issueTimes = analytics.getTimeToLastComment(repoName);
            return Response.status(Response.Status.OK).entity(issueTimes).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/closed-issues-without-comment/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClosedIssueWithoutComment(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            ArrayList<Issue> issues = analytics.getClosedIssueWithoutComment(repoName);
            return Response.status(Response.Status.OK).entity(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/fixed-issues-without-comment/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixedIssueWithoutComment(@PathParam("repository-name") String repoName) {

        Analytics analytics = new Analytics();
        try {
            ArrayList<Issue> issues = analytics.getFixedIssueWithoutComment(repoName);
            return Response.status(Response.Status.OK).entity(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
