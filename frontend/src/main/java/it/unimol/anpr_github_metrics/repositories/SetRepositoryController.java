package it.unimol.anpr_github_metrics.repositories;

import com.google.gson.Gson;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.servlets.basic.InconsistentSessionException;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/set-repo")
public class SetRepositoryController extends HttpGetServlet {
    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession());

        if (session.isSet(SessionHandler.USER) && session.isSet(SessionHandler.GITHUB_TOKEN)) {

            try {
                String repoJson = request.getParameter("repo_json");
                Repository repo = new Gson().fromJson(repoJson, Repository.class);

                if (repo == null) {
                    throw new InconsistentSessionException();
                }


                //TODO:richieste riguardanti la repository
//            String open = new ZetmusWrapper().getOpenIssuesNumber(session.getUser().getLogin(), repo.getName(), session.getToken());
                session.setRepo(repo);
                response.sendRedirect(request.getContextPath() + "/dashboard");

            } catch (InconsistentSessionException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Inconsistent session.");
            }
//        } catch (UnirestException e) {
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Request error.");
//        }

        } else {
            request = null;
            request.setAttribute("msg", "You must be logged in");
            request.getRequestDispatcher(request.getContextPath() + ServletPath.LOGIN).forward(request, response);
        }
    }
}
