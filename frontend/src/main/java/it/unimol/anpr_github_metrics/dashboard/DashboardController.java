package it.unimol.anpr_github_metrics.dashboard;

import com.jcabi.github.Repo;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(ServletPath.DASHBOARD)
public class DashboardController extends HttpGetServlet {
    public static final String MAIN = "dashboard.jsp";
    public static final String TICKETS = "tickets.jsp";
    public static final String ISSUE = "issue.jsp";

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession());

        if (session.isSet(SessionHandler.GITHUB_TOKEN)
                && session.isSet(SessionHandler.USER)
                && session.isSet(SessionHandler.GITHUB_REPO)) {

            request.setAttribute("user", session.getUser());
            request.setAttribute("repo", session.getRepo());

            String page = (String) request.getAttribute("page");

//            if (page != null) {
//                //IF PAGE NOT SET
                request.getRequestDispatcher(page).forward(request, response);
//            } else {
//                request.getRequestDispatcher(MAIN).forward(request, response);
//            }

        } else {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + ServletPath.LOGIN);
//            if (!session.isSet(SessionHandler.GITHUB_TOKEN))
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "no token.");
//
//
//            if (!session.isSet(SessionHandler.GITHUB_REPO))
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "no repo.");
//
//            if (!session.isSet(SessionHandler.USER))
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "no user.");
        }
    }
}
