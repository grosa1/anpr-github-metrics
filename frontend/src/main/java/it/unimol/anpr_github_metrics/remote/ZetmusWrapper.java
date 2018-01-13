package it.unimol.anpr_github_metrics.remote;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import javax.ws.rs.Path;

public class ZetmusWrapper {
    public static final String BASE_URL = "http://localhost:8080/zeus-api";

    public String getOpenIssuesNumber(String loginName, String repositoryName, String oauthToken) throws UnirestException {
        String result = null;

        HttpRequest req = Unirest.get(BASE_URL + "/analytics/number-of-open-issues/" + loginName + "/" + repositoryName + "/" + oauthToken);
        int responseCode = req.asString().getStatus();
        if(responseCode == 200) {
            result = req.asString().getBody();
        } else {
            throw new UnirestException(String.valueOf(responseCode) + " - " + req.asString().getStatusText());
        }

        return result;
    }
}
