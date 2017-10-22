package it.unimol.anpr_github_metrics.services;


import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.analytics.*;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.github.GithubException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class of services handles the end-point api of services
 * @author Stefano Dalla Palma.
 *
 * The current working implemented api are the following:
 * - /average-closing-time
 * - /average-first-response-time
 * - /closing-time-distribution
 * - /first-response-time-distribution
 * - /number-of-closed-issues
 * - /number-of-commented-closed-issues
 * - /number-of-commented-fixed-issues
 * - /number-of-commented-labeled-issues
 * - /number-of-commented-open-issues
 * - /number-of-fixed-issues
 * - /number-of-labeled-closed-issues ---
 * - /number-of-labeled-fixed-issues
 * - /number-of-labeled-open-issues
 * - /number-of-open-issues
 * - /number-of-uncommented-closed-issues
 * - /number-of-uncommented-fixed-issues
 * - /number-of-uncommented-open-issues
 * - /number-of-unlabeled-closed-issues
 * - /number-of-unlabeled-fixed-issues
 * - /number-of-unlabeled-open-issues
 */
@Path("/analytics")
public class AnalyticsApi {

    // ============================ AVERAGE CLOSING TIME ============================//
    @GET
    @Path("/average-closing-time/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAverageClosingTime(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        try {
            Long averageClosingTime = analytics.getAverageClosingTime(repoName);
            return Response.ok(averageClosingTime).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ AVERAGE FIXING TIME ============================//
    @GET
    @Path("/average-fixing-time/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAverageFixingTime(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        try {
            Long averageFixingTime = analytics.getAverageFixingTime(repoName);
            return Response.ok(averageFixingTime).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ AVERAGE FIRST RESPONSE TIME ============================//
    @GET
    @Path("/average-first-response-time/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAverageResponseTime(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        IssuesAnalytics analytics = new IssuesAnalytics(github);
        try {
            Long averageFirstResponseTime = analytics.getAverageFirstResponseTime(repoName);
            return Response.ok(averageFirstResponseTime).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ AVERAGE TIME FROM LAST COMMENT ============================//
    @GET
    @Path("/average-time-from-last-comment/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAverageTimeFromLastComment(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        IssuesAnalytics analytics = new IssuesAnalytics(github);
        try {
            Long averageTimeFromLastComment = analytics.getAverageTimeFromLastComment(repoName);
            return Response.ok(averageTimeFromLastComment).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ COMMENTED CLOSED ISSUES ============================//
    @GET
    @Path("/commented-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentedClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        try {
            final ArrayList<Issue> commentedIssues = analytics.getCommentedClosedIssues(repoName);
            JSONArray json = JSONConverter.issuesToJSON(commentedIssues);
            return Response.ok(json).build();

        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ COMMENTED FIXED ISSUES ============================//
    @GET
    @Path("/commented-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentedFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        try {
            ArrayList<Issue> commentedIssues = analytics.getCommentedFixedIssues(repoName);
            // TODO transalate in JsonObject
            return Response.ok(commentedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ COMMENTED OPEN ISSUES ============================//
    @GET
    @Path("/commented-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentedOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);
        try {
            ArrayList<Issue> commentedIssues = analytics.getCommentedOpenIssues(repoName);
            // TODO transalate in JsonObject
            return Response.ok(commentedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ CLOSING TIME DISTRIBUTION ============================//
    @GET
    @Path("/closing-time-distribution/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketClosingTimeDistribution(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        try {
            HashMap<Issue, Long> ticketClosingTimeDistribution = analytics.getClosingTimeDistribution(repoName);
            //TODO return JsonObject
            return Response.ok(ticketClosingTimeDistribution).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ FIRST RESPONSE TIME DISTRIBUTION ============================//
    @GET
    @Path("/first-response-time-distribution/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFirstResponseTimeDistribution(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        IssuesAnalytics analytics = new IssuesAnalytics(github);
        try {
            HashMap<Issue, Long> firstResponseTimeDistribution = analytics.getFirstResponseTimeDistribution(repoName);
            //TODO return JsonObject
            return Response.ok(firstResponseTimeDistribution).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ FIXING TIME DISTRIBUTION ============================//
    @GET
    @Path("/fixing-time-distribution/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFixingTimeDistribution(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        try {
            HashMap<Issue, Long> fixingTimeDistribution = analytics.getFixingTimeDistribution(repoName);
            //TODO return JsonObject
            return Response.ok(fixingTimeDistribution).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ LABELED CLOSED ISSUES ============================//
    @GET
    @Path("/labeled-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabeledClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        try {
            ArrayList<Issue> labeledIssues = analytics.getLabeledClosedIssues(repoName);
            // TODO transalate in JsonObject
            return Response.ok(labeledIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ LABELED FIXED ISSUES ============================//
    @GET
    @Path("/labeled-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabeledFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        try {
            ArrayList<Issue> labeledIssues = analytics.getLabeledFixedIssues(repoName);
            // TODO transalate in JsonObject
            return Response.ok(labeledIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ============================ LABELED OPEN ISSUES ============================//
    @GET
    @Path("/labeled-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabeledOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);
        try {
            ArrayList<Issue> labeledIssues = analytics.getLabeledOpenIssues(repoName);
            // TODO transalate in JsonObject
            return Response.ok(labeledIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF COMMENTED CLOSED ISSUES ============================//
    @GET
    @Path("/number-of-commented-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfCommentedClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);

        try {
            int numberOfCommentedClosedIssues = analytics.getNumberOfCommentedClosedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfCommentedClosedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF COMMENTED FIXED ISSUES ============================//
    @GET
    @Path("/number-of-commented-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfCommentedFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);

        try {
            int numberOfCommentedFixedIssues = analytics.getNumberOfCommentedFixedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfCommentedFixedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF COMMENTED OPEN ISSUES ============================//
    @GET
    @Path("/number-of-commented-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfCommentedOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            int numberOfCommentedOpenIssues = analytics.getNumberOfCommentedOpenIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfCommentedOpenIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF CLOSED ISSUES ============================//
    @GET
    @Path("/number-of-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);

        try {
            int numberOfClosedIssues = analytics.getNumberOfClosedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfClosedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF FIXED ISSUES ============================//
    @GET
    @Path("/number-of-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);

        try {
            int numberOfFixedIssues = analytics.getNumberOfFixedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfFixedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF LABELED CLOSED ISSUES ============================//
    @GET
    @Path("/number-of-labeled-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfLabeledClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);

        try {
            int numberOfLabeledClosedIssues = analytics.getNumberOfLabeledClosedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfLabeledClosedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF LABELED FIXED ISSUES ============================//
    @GET
    @Path("/number-of-labeled-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfLabeledFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);

        try {
            int numberOfLabeledFixedIssues = analytics.getNumberOfLabeledFixedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfLabeledFixedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF LABELED OPEN ISSUES ============================//
    @GET
    @Path("/number-of-labeled-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfLabeledOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            int numberOfLabeledOpenIssues = analytics.getNumberOfLabeledOpenIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfLabeledOpenIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF OPEN ISSUES ============================//
    @GET
    @Path("/number-of-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            int numberOfOpenIssues = analytics.getNumberOfOpenIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfOpenIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF UNCOMMENTED CLOSED ISSUES ============================//
    @GET
    @Path("/number-of-uncommented-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfUncommentedClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);

        try {
            int numberOfUncommentedClosedIssues = analytics.getNumberOfUncommentedClosedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfUncommentedClosedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF UNCOMMENTED FIXED ISSUES ============================//
    @GET
    @Path("/number-of-uncommented-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfUncommentedFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);

        try {
            int numberOfUncommentedFixedIssues = analytics.getNumberOfUncommentedFixedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfUncommentedFixedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF UNCOMMENTED OPEN ISSUES ============================//
    @GET
    @Path("/number-of-uncommented-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfUncommentedOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            int numberOfUncommentedOpenIssues = analytics.getNumberOfUncommentedOpenIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfUncommentedOpenIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF UNLABELED CLOSED ISSUES ============================//
    @GET
    @Path("/number-of-unlabeled-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfUnlabeledClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);

        try {
            int numberOfUnlabeledClosedIssues = analytics.getNumberOfUnlabeledClosedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfUnlabeledClosedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF UNLABELED FIXED ISSUES ============================//
    @GET
    @Path("/number-of-unlabeled-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfUnlabeledFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);

        try {
            int numberOfUnlabeledFixedIssues = analytics.getNumberOfUnlabeledFixedIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfUnlabeledFixedIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ NUMBER OF UNLABELED OPEN ISSUES ============================//
    @GET
    @Path("/number-of-unlabeled-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfUnlabeledOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            int numberOfUnlabeledOpenIssues = analytics.getNumberOfUnlabeledOpenIssues(repoName);
            return Response.status(Response.Status.OK).entity(numberOfUnlabeledOpenIssues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ TIMES FROM LAST COMMENT ============================//
    @GET
    @Path("/times-from-last-comment/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimesFromLastComment(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        IssuesAnalytics analytics = new IssuesAnalytics(github);
        try {
            HashMap<Issue, Long> issueTimes = analytics.getTimesFromLastComment(repoName);
            return Response.status(Response.Status.OK).entity(issueTimes).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ UNCOMMENTED CLOSED ISSUES ============================//
    @GET
    @Path("/uncommented-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUncommentedClosedIssue(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);
        try {
            ArrayList<Issue> issues = analytics.getUncommentedClosedIssues(repoName);
            //  TODO return JsonObject
            return Response.ok(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    // ============================ UNCOMMENTED FIXED ISSUES ============================//
    @GET
    @Path("/uncommented-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUncommentedFixedIssue(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);
        try {
            ArrayList<Issue> issues = analytics.getUncommentedFixedIssues(repoName);
            //  TODO return JsonObject
            return Response.ok(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ UNCOMMENTED OPEN ISSUES ============================//
    @GET
    @Path("/uncommented-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUncommentedOpen(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            ArrayList<Issue> issues = analytics.getUncommentedOpenIssue(repoName);
            //  TODO return JsonObject
            return Response.ok(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ UNLABELED CLOSED ISSUES ============================//
    @GET
    @Path("/unlabeled-closed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnlabeledClosedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        ClosedIssuesAnalytics analytics = new ClosedIssuesAnalytics(github);

        try {
            ArrayList<Issue> issues = analytics.getUnlabeledClosedIssues(repoName);
            //  TODO return JsonObject
            return Response.ok(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ UNLABELED FIXED ISSUES ============================//
    @GET
    @Path("/unlabeled-fixed-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnlabeledFixedIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        FixedIssuesAnalytics analytics = new FixedIssuesAnalytics(github);

        try {
            ArrayList<Issue> issues = analytics.getUnlabeledFixedIssues(repoName);
            //  TODO return JsonObject
            return Response.ok(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ============================ UNLABELED OPEN ISSUES ============================//
    @GET
    @Path("/unlabeled-open-issues/{login-name}/{repository-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnlabeledOpenIssues(@PathParam("repository-name") String repoPath, @PathParam("login-name") String loginName, @Context HttpServletRequest request) {
        if (!checkSession(request.getSession())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Github github = (Github)request.getSession().getAttribute("github");

        String repoName = loginName+"/"+repoPath;
        OpenIssuesAnalytics analytics = new OpenIssuesAnalytics(github);

        try {
            ArrayList<Issue> issues = analytics.getUnlabeledOpenIssues(repoName);
            //  TODO return JsonObject
            return Response.ok(issues).build();
        } catch (GithubException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }





    public boolean checkSession(HttpSession session) {
        Object githubAttribute = session.getAttribute("github");
        return (githubAttribute != null && githubAttribute instanceof Github);
    }
}
