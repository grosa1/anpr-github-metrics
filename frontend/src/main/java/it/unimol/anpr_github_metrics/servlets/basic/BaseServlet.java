package it.unimol.anpr_github_metrics.servlets.basic;

import it.unimol.anpr_github_metrics.configuration.ServletPath;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    protected void setSessionError(String msg, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath() + ServletPath.LOGIN + "?msg=" + msg);
        } catch (IOException e) {
        }
    }
}
