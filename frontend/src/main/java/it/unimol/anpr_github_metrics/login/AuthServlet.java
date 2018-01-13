package it.unimol.anpr_github_metrics.login;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.remote.GithubWrapper;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(ServletPath.AUTHORIZE)
public class AuthServlet extends HttpGetServlet {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession(true));

        String code = request.getParameter("code");
        String state = request.getParameter("state");

        try {
            String token = new GithubWrapper().getOAuthToken(code, state);
            User user = new GithubWrapper().getUser(token);

            if (token != null && user != null) {
                handler.setToken(token);
                handler.setUser(user);
                response.sendRedirect(request.getContextPath() + ServletPath.REPOSITORIES);

//                PrintWriter out = response.getWriter();
//                response.setContentType("text/html;charset=UTF-8");
//                out.print(handler.getUser().getLogin());
//                out.close();

            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid request data.");
            }

        } catch (UnirestException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Request error.");
        }
    }
}


