package it.unimol.anpr_github_metrics.repositories;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.remote.GithubWrapper;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Simone Scalabrino.
 */
@WebServlet(ServletPath.REPOSITORIES)
public class ReposController extends HttpGetServlet {

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession());

        String token = handler.getToken();

        try {
            Repository[] repos = new GithubWrapper().getRepos(token);
            if(repos != null) {
                request.setAttribute("repos", repos);
            }

            request.getRequestDispatcher("repositories.jsp").forward(request, response);
            //TODO aggiungere errore nessuna repo

        } catch (UnirestException e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            out.print("errore");
            out.close();
        }


    }
}
