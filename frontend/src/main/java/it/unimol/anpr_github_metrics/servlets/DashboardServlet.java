package it.unimol.anpr_github_metrics.servlets;

import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpGetServlet{

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        URL url = new URL("http://localhost:8080/zeus-api/getLoginStatus");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.print(conn.getResponseCode());
        out.close();

    }
}
