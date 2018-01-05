package it.unimol.anpr_github_metrics.servlets;

import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpGetServlet{

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    }
}
