package it.unimol.anpr_github_metrics.servlets;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.configuration.OAuthParms;
import it.unimol.anpr_github_metrics.servlets.basic.HttpGetServlet;
import it.unimol.anpr_github_metrics.session.SessionHandler;
import it.unimol.anpr_github_metrics.session.SessionKey;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/auth")
public class AuthServlet extends HttpGetServlet {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionHandler session = SessionHandler.getInstance(request.getSession(true));

        String code = request.getParameter("code");
        String state = request.getParameter("state");

        try {
            String token = this.getOAuthToken(code, state);
            String loginName = this.getUsername(token);
            if (token != null && loginName != null) {
                session.store(SessionKey.GITHUB_TOKEN, token);
                session.store(SessionKey.LOGGED_USERNAME, loginName);
                response.sendRedirect("/zeus/repos");

//                PrintWriter out = response.getWriter();
//                response.setContentType("text/html;charset=UTF-8");
//                out.print(this.getUsername("sdfsdf"));
//                out.close();

            } else {
                response.sendRedirect("/zeus/login");
            }

        } catch (UnirestException e) {
            response.sendRedirect("/zeus/login");
        }
    }

    private String getOAuthToken(String code, String state) throws UnirestException {

        String token = null;
        HttpResponse<String> accessTokenResponse = Unirest.post("https://github.com/login/oauth/access_token")
                .field("client_id", OAuthParms.CLIENT_ID)
                .field("client_secret", OAuthParms.CLIENT_SECRET)
                .field("code", code)
                .field("redirect_uri", OAuthParms.REDIRECT_URI)
                .field("state", state)
                .asString();

        if (!accessTokenResponse.getBody().contains("error")) {
            Map<String, String> map = this.getQueryMap(accessTokenResponse.getBody());
            token = map.get("access_token");
        }

        return token;
    }

    private String getUsername(String token) throws UnirestException {
        String loginName = null;

        try {
            JSONObject jsonRes = Unirest.get("https://api.github.com/user")
                    .queryString("access_token", token)
                    .asJson()
                    .getBody()
                    .getObject();

            //TODO SAREBBE MEGLIO IL CONTROLLO SUL CODICE DI RISPOSTA
            loginName = jsonRes.getString("login");
        } catch (JSONException e) {
        }

        return loginName;
    }

    private Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=", 2)[0];
            String value = param.split("=", 2)[1];
            map.put(name, value);
        }
        return map;
    }
}


