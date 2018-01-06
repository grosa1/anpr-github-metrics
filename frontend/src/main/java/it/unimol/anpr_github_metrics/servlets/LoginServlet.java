package it.unimol.anpr_github_metrics.servlets;

import it.unimol.anpr_github_metrics.config.OAuthParms;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpGetServlet {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String authRequest = "http://github.com/login/oauth/authorize?client_id=" + OAuthParms.CLIENT_ID
                + "&redirect_uri=" + OAuthParms.REDIRECT_URI
                + "&state=" + OAuthParms.STATE;

        request.setAttribute("request", authRequest);
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
}
