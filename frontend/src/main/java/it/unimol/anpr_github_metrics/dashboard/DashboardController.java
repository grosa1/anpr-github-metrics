package it.unimol.anpr_github_metrics.dashboard;

import com.jcabi.github.Repo;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController extends HttpGetServlet {
    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession());
        User user = session.getUser();
        Repository repo = session.getRepo();

//        Page pageId = (Page) request.getAttribute("page");
//
//        switch(pageId) {
//            case HOME:
//                break;
//
//            case REPOSITORIES:
//                break;
//
//            case ISSUES:
//                break;
//
//            case OPENED_ISSUES:
//                break;
//
//            case CLOSED_ISSUES:
//                break;
//
//                default:
                    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
//        }
    }
}