package it.unimol.anpr_github_metrics.login;

import com.mashape.unirest.http.Unirest;
import it.unimol.anpr_github_metrics.configuration.OAuthParms;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.servlets.basic.HttpPostServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginController extends HttpPostServlet {

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession(true));

            if (session.isLoggedIn()) {
                response.sendRedirect(request.getContextPath() + "/repos");
            } else {
                Unirest.get("http://github.com/login/oauth/authorize")
                        .queryString("client_id", OAuthParms.CLIENT_ID)
                        .queryString("redirect_uri", OAuthParms.REDIRECT_URI)
                        .queryString("state", OAuthParms.STATE);
            }
        }
    }

