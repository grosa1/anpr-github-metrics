package it.unimol.anpr_github_metrics.servlets;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.configuration.ConfigurationManager;
import it.unimol.anpr_github_metrics.servlets.basic.GetOnlyForcedLoggedServlet;
import it.unimol.anpr_github_metrics.servlets.basic.ServletUtils;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
@WebServlet(name="/ShowRepositories")
public class ShowRepositories extends GetOnlyForcedLoggedServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession());

        if (!ServletUtils.getInstance().checkLoginAndEnforce(request, response))
            return;

        try {
            HttpResponse<Repository[]> unirestResponse =
                    Unirest.get(ConfigurationManager.getInstance().getBackendEndpoint() + "get-repositories")
                            .asObject(Repository[].class);

            handler.setRepositories(unirestResponse.getBody());
            response.sendRedirect("repositories.jsp");
        } catch (UnirestException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Problem with backend.");
        }
    }
}
