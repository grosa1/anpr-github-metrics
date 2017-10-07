package it.unimol.anpr_github_metrics.analytics;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class of services handles the mean first response time of an issue, i.e., the mean response time of the first comment of an issue
 * applications
 * @author Simone Scalabrino.
 */
@Path("/analytics")
public class Analytics {

    @GET
    @Path("/mean-first-time-response")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveDataFlow(@PathParam("appId") String appId) {

        return Response.status(Response.Status.OK).entity("something").build();
    }
}
