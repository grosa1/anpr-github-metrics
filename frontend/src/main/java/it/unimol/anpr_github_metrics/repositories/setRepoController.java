package it.unimol.anpr_github_metrics.repositories;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.remote.ZetmusWrapper;
import it.unimol.anpr_github_metrics.servlets.basic.HttpPostServlet;
import it.unimol.anpr_github_metrics.remote.GithubWrapper;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setRepo")
public class setRepoController extends HttpPostServlet{

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession());

        String repoJson = request.getParameter("repo_json");

        try {
            Repository repo = new Gson().fromJson(repoJson, Repository.class);
            session.setRepo(repo);

            String open = new ZetmusWrapper().getOpenIssuesNumber(session.getUser().getLogin(), repo.getName(), session.getToken());
            request.getRequestDispatcher(request.getContextPath() + "/dashboard").forward(request, response);

        } catch (UnirestException e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            out.print("errore dashboard controller");
            out.close();
        }

//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html;charset=UTF-8");
//        out.print(repoName);
//        out.close();
    }
}
