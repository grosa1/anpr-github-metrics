package it.unimol.anpr_github_metrics.login;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.remote.GithubWrapper;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpGetServlet {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession(true));

        String code = request.getParameter("code");
        String state = request.getParameter("state");

        try {
            String token = new GithubWrapper().getOAuthToken(code, state);
            if (token != null) {
                session.setToken(token);
                response.sendRedirect(request.getContextPath() + "/repos");

//                PrintWriter out = response.getWriter();
//                response.setContentType("text/html;charset=UTF-8");
//                out.print(token);
//                out.close();

            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (UnirestException e) {
            response.sendRedirect("login.jsp");
        }
    }
}


