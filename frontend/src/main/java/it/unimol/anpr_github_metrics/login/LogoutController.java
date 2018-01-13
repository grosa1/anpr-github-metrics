package it.unimol.anpr_github_metrics.login;

import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletPath.LOGOUT)
public class LogoutController extends HttpGetServlet {
    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler.getInstance(request.getSession()).logout();
        response.sendRedirect(request.getContextPath() + ServletPath.LOGIN);
    }
}
