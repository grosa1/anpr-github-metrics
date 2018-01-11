package it.unimol.anpr_github_metrics.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.configuration.OAuthParms;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GithubWrapper {

    public String getOAuthToken(String code, String state) throws UnirestException {

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

    public User getUser(String token) throws UnirestException {
        User user = null;

        try {
            HttpResponse<String> res = Unirest.get("https://api.github.com/user")
                    .queryString("access_token", token)
                    .asString();

            if(res.getStatus() == 200) {
                Gson jsonParser = new GsonBuilder().create();
                user = jsonParser.fromJson(res.getBody(), User.class);
            }

        } catch (JSONException e) {
        }

        return user;
    }

    public Repository[] getRepos(String token) throws UnirestException {
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

    public Map<String, String> getQueryMap(String query) {
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
