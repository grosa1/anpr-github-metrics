package it.unimol.anpr_github_metrics.dashboard;

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

@WebServlet("/dashboard")
public class DashboardController extends HttpPostServlet{

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession());

        String repoJson = request.getParameter("repo_json");

        try {
            Repository repo = new Gson().fromJson(repoJson, Repository.class);
            User user = new GithubWrapper().getUser(session.getToken());
            String open = new ZetmusWrapper().getOpenIssuesNumber(user.getLogin(), repo.getName(), session.getToken());

            request.setAttribute("repo", repo);
            request.setAttribute("user", user);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

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
