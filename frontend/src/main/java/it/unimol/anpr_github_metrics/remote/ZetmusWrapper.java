package it.unimol.anpr_github_metrics.remote;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.ws.rs.Path;

public class ZetmusWrapper {
    public static final String BASE_URL = "http://localhost:8080/zeus-api";

    public String getOpenIssuesNumber(String loginName, String repositoryName, String oauthToken) throws UnirestException {
        return Unirest.get(BASE_URL + "/analytics/number-of-open-issues/" + loginName + "/" + repositoryName + "/" + oauthToken)
                .asString()
                .getBody();
    }
}
