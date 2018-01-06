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
import java.net.URLEncoder;
@WebServlet("/auth")
public class AuthServlet extends HttpGetServlet {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String requestURL = ("http://localhost:8080/zeus-api/getAccessToken");

        String query = String.format("code=%s&state=%s",
                URLEncoder.encode(code, "UTF-8"),
                URLEncoder.encode(state, "UTF-8"));

        URL url = new URL(requestURL + "?" + query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.print(conn.getResponseCode());
        out.close();

//        switch (responseCode) {
//            case 200:
//                response.sendRedirect("/zeus/dashboard");
//                break;
//
//            case 403:
//                System.out.println("403");
//                response.sendRedirect("/zeus/login");
//
//                break;
//
//            case 500:
//                System.out.println("500");
//                response.sendRedirect("/zeus/login");
//
//                break;
//        }
    }
}


