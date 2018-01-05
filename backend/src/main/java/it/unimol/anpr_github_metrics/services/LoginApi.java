package it.unimol.anpr_github_metrics.services;

import com.jcabi.github.Github;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.config.OAuthConfigManager;
import it.unimol.anpr_github_metrics.github.Authenticator;
import it.unimol.anpr_github_metrics.config.OAuthParms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class LoginApi {

    @GET
    @Path("/getAccessToken")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@QueryParam("code") String code, @QueryParam("state") String state, @Context HttpServletRequest request) {

        String token;
        HttpResponse<String> accessTokenResponse = null;
        try {
            accessTokenResponse = Unirest.post("https://github.com/login/oauth/access_token")
                    .field("client_id", OAuthParms.CLIENT_ID)
                    .field("client_secret", OAuthParms.CLIENT_SECRET)
                    .field("code", code)
                    .field("redirect_uri", OAuthParms.REDIRECT_URI)
                    .field("state", state)
                    .asString();

            if(accessTokenResponse.getBody().contains("error")) {
                throw new ForbiddenException();
            }

            Map<String, String> map = getQueryMap(accessTokenResponse.getBody());
            token = map.get("access_token");

            //TODO check
            HttpSession session = request.getSession(true);

            Github github = Authenticator.getInstance().authenticate(token).getGitHub();
            session.setAttribute("token", token);
            session.setAttribute("github", github);

            //TODO controllo su sessione
            return Response.status(Response.Status.OK).build();

        } catch (UnirestException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (ForbiddenException e) {
            System.out.println(accessTokenResponse.getBody().toString());
            return Response.status(Response.Status.FORBIDDEN).build();
        }
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
