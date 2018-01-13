package it.unimol.anpr_github_metrics.dashboard;

import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.IssueTest;
import it.unimol.anpr_github_metrics.configuration.Page;
import it.unimol.anpr_github_metrics.configuration.ServletPath;
import it.unimol.anpr_github_metrics.remote.GithubWrapper;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletPath.DASHBOARD)
public class DashboardController extends HttpGetServlet {

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession());

        if (handler.isLoggedIn()) {

            if (!handler.isSet(SessionHandler.GITHUB_REPO)) {
                response.sendRedirect(request.getContextPath() + ServletPath.REPOSITORIES);
            }
            response.setContentType("text/html");
            request.setAttribute("user", handler.getUser());
            request.setAttribute("repo", handler.getRepo());

            try {
                String page = request.getParameter("page");
                if (page != null) {
                    if (page.equals(Page.TICKETS)) {
                        IssueTest[] issues = new GithubWrapper().getRepoIssues(handler.getToken(), handler.getRepo().getFullname());
                        if (issues == null) {
                            throw new UnirestException("issues array is null");
                        }
                        request.setAttribute("issues", issues);
                        request.setAttribute("page", "/" + Page.TICKETS);

//                    } else if (page.equals()){
//                        //TODO
                    } else {
                        request.setAttribute("page", "/" + Page.MAIN);
                    }
                } else {
                    request.setAttribute("page", "/" + Page.MAIN);
                }

                request.getRequestDispatcher("/" + Page.DASHBOARD).forward(request, response);


            } catch (ServletException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            } catch (UnirestException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Request error");
            }

        } else {
            this.setSessionError("ERROR: You must be logged in", request, response);
        }
    }
}

//            try {
//                //TODO: include dinamico della pagina nel jsp
//                String page = request.getParameter("page");
//                if (page != null) {
//                    if (page.equals(Page.TICKETS)) {
//                        IssueTest[] issues = new GithubWrapper().getRepoIssues(handler.getToken(), handler.getRepo().getFullname());
//                        if(issues == null) {
//                            throw new UnirestException("issues array is null");
//                        }
//                        request.setAttribute("issues", issues);
//                        request.getRequestDispatcher("/" + Page.TICKETS).forward(request, response);
//
////                    } else if (page.equals()){
////                        //TODO
//                    } else {
//                        request.getRequestDispatcher("/" + Page.MAIN).forward(request, response);
//                    }
//                } else {
//                    request.getRequestDispatcher("/" + Page.MAIN).forward(request, response);
//                }
//
//            } catch (ServletException e) {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
//            } catch (UnirestException e) {
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Request error");
//            }
