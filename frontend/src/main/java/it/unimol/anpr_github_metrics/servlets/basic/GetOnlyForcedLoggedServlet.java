package it.unimol.anpr_github_metrics.servlets.basic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * @author Simone Scalabrino.
 */
public abstract class GetOnlyForcedLoggedServlet extends HttpServlet {
    @Override
    protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Method POST not allowed here.");
    }

    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.run(request, response);
    }

    protected abstract void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
