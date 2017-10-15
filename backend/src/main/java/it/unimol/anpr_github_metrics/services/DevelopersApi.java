package it.unimol.anpr_github_metrics.services;

import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.github.Authenticator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
@Path("/dev")
public class DevelopersApi {
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testLogin(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("token") != null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

        Github github = Authenticator.getInstance().authenticate(Authenticator.TEST).getGitHub();
        session.setAttribute("token", Authenticator.TEST);
        session.setAttribute("github", github);

        try {
            if (!github.users().self().login().equals("")) {
                return Response.status(Response.Status.OK).entity(true).build();
            }
        } catch (IOException e) {
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Test login not working").build();
    }

    @GET
    @Path("/quota")
    @Produces(MediaType.APPLICATION_JSON)
    public Response quota(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Github github = (Github) session.getAttribute("github");
        if (github == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        try {
            int limit = github.limits().get("core").json().getInt("remaining");
            return Response.status(Response.Status.OK).entity(limit).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
