package it.unimol.anpr_github_metrics.login;

import com.mashape.unirest.http.Unirest;
import it.unimol.anpr_github_metrics.configuration.OAuthParms;
import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.servlets.basic.HttpPostServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletPath.LOGIN)
public class LoginController extends HttpGetServlet {

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession(true));

            if (session.isLoggedIn()) {
                if(session.isSet(SessionHandler.GITHUB_REPO)) {
                    response.sendRedirect(request.getContextPath() + ServletPath.DASHBOARD);
                } else {
                    response.sendRedirect(request.getContextPath() + ServletPath.REPOSITORIES);
                }
            } else {
                request.setAttribute("msg", request.getParameter("msg"));

                request.setAttribute("client_id", OAuthParms.CLIENT_ID);
                request.setAttribute("redirect_uri", OAuthParms.REDIRECT_URI);
                request.setAttribute("state", OAuthParms.STATE);

                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

