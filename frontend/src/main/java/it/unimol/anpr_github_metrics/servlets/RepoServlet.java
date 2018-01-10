package it.unimol.anpr_github_metrics.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;
import it.unimol.anpr_github_metrics.session.SessionKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Simone Scalabrino.
 */
@WebServlet("/repos")
public class RepoServlet extends HttpGetServlet {

    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession());

        String token = handler.load(SessionKey.GITHUB_TOKEN);

        try {
            Repository[] repos = this.getRepos(token);
            if(repos != null) {
                request.setAttribute("repos", repos);
            }

            request.getRequestDispatcher("repositories.jsp").forward(request, response);

        } catch (UnirestException e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            out.print("errore");
            out.close();
        }


    }

    private Repository[] getRepos(String token) throws UnirestException {
        Repository[] reposArray = null;

        try {
            HttpResponse<String> res = Unirest.get("https://api.github.com/user/repos")
                    .queryString("access_token", token)
                    .asString();

            if(res.getStatus() == 200) {
                Gson jsonParser = new GsonBuilder().create();
                reposArray = jsonParser.fromJson(res.getBody(), Repository[].class);
            }

        } catch (JSONException e) {
        }

        return reposArray;
    }
}
