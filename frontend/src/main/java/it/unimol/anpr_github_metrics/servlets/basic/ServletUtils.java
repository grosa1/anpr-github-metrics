package it.unimol.anpr_github_metrics.servlets.basic;

import it.unimol.anpr_github_metrics.jsp.JSPUtils;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
public class ServletUtils {
    private static ServletUtils instance;

    public static ServletUtils getInstance() {
        if (instance == null)
            return new ServletUtils();

        return instance;
    }

    /**
     * Checks if the user is logged in locally and remotely.
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public boolean checkLoginAndEnforce(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession());

        if (!handler.isLoggedIn()) {
            response.sendRedirect("login.jsp");
            return false;
        }

        //TODO check remote login

        return true;
    }
}
