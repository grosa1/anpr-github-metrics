package it.unimol.anpr_github_metrics.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/hello")
public class HelloServelt extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // then write the data of the response
        //out.println("<h2>" + "Hello servlet!" + "</h2>");

        //String[] array = {"elemento1", "elemento2", "elemento3", "elemento1", "elemento2", "elemento3", "elemento1", "elemento2", "elemento3", "elemento1", "elemento2", "elemento3", };

        ArrayList<String> posts = new ArrayList<String>();

        for(int i = 0; i<20; i++) {
            posts.add("elemento " + i);
        }
//        out.print("<ul>");
//        for(String s : array) {
//            out.print("<li>" + s + "</li>");
//        }
//        out.print("</ul>");


        request.setAttribute("posts", posts);
        request.getRequestDispatcher("login.jsp").forward(request, response);
        response.sendRedirect("login.jsp");

    }

}


