package it.unimol.anpr_github_metrics.services;

import com.jcabi.github.Github;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import it.unimol.anpr_github_metrics.github.Authenticator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
@Path("/login")
public class LoginApi {

    public static final String ON_LOGIN_REDIRECT_URI = "http://www.unimol.it";

    @GET
    @Path("/getLoginCode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@QueryParam("code") String code, @QueryParam("state") String state, @Context HttpServletRequest request) {

        String token;
        HttpResponse<String> res = null;
        try {
            res = Unirest.post("https://github.com/login/oauth/access_token")
                    .field("client_id", "1211d954012cf73c2e2b")
                    .field("client_secret", "1237664d8ab78f6305d2571ee7189fdc5b641ef6")
                    .field("code", code)
                    .field("redirect_uri", ON_LOGIN_REDIRECT_URI)
                    .field("state", state)
                    .asString();

            if(res.getBody().contains("error")) {
                throw new ForbiddenException();
            }

            Map<String, String> map = getQueryMap(res.getBody());
            token = map.get("access_token");

            //TODO check
            HttpSession session = request.getSession();

            Github github = Authenticator.getInstance().authenticate(token).getGitHub();
            session.setAttribute("token", token);
            session.setAttribute("github", github);

            URI uri = new URI(ON_LOGIN_REDIRECT_URI);
            return Response.seeOther(uri).status(Response.Status.OK).build();

        } catch (UnirestException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (ForbiddenException e) {
            System.out.println(res.getBody().toString());
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

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
