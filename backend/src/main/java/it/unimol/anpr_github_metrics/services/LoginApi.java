package it.unimol.anpr_github_metrics.services;

import com.jcabi.github.Github;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.github.Authenticator;
import it.unimol.anpr_github_metrics.github.proxies.GithubProxy;
import org.apache.http.auth.AUTH;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginApi {
    @GET
    @Path("/getLoginCode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@QueryParam("code") String code, @QueryParam("state") String state, @Context HttpServletRequest request) {
        String token;

        try {
            HttpResponse<JsonNode> tokenRes = Unirest.post("https://github.com/login/oauth/access_token")
                    .field("client_id", "1211d954012cf73c2e2b")
                    .field("client_secret", "1237664d8ab78f6305d2571ee7189fdc5b641ef6")
                    .field("code", code)
                    .field("redirect_uri", "http://www.unimol.it")
                    .field("state", state)
                    .asJson();

            token = tokenRes.getBody().getObject().getString("access_token");

            //TODO check
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            session.setAttribute("github", Authenticator.getInstance().authenticate(token).getGitHub());

            return Response.status(Response.Status.OK).entity(token).build();

        } catch (UnirestException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}