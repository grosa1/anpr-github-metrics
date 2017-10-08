package it.unimol.anpr_github_metrics.github;

import it.unimol.anpr_github_metrics.beans.Commit;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.Repository;
import it.unimol.anpr_github_metrics.beans.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IssueExtractorStub implements IssueExtractor{
    @Override
    public Collection<User> getContributors(Repository repository) throws GithubException {
        return null;
    }

    @Override
    public Collection<Issue> getFixedIssues(Repository repository) throws GithubException {
        ArrayList<Issue> issues = new ArrayList<>();

        User stefano = new User();
        stefano.setUrl("stefanoUri");
        stefano.setLogin("stefano");

        User daniel = new User();
        stefano.setUrl("danielUri");
        stefano.setLogin("daniel");

        Issue issue1 = new Issue();
        issue1.setAuthor(stefano);
        issue1.setTitle("Issue 1 Title");
        issue1.setBody("Issue 1 Body");
        issue1.setCreatedTime(new Date(2017, 10, 8));
        issue1.setClosedTime(new Date(2017, 10, 9));

        Issue issue2 = new Issue();
        issue2.setAuthor(daniel);
        issue2.setTitle("Issue 2 Title");
        issue2.setBody("Issue 2 Body");
        issue2.setCreatedTime(new Date(2017, 10, 8));
        issue2.setClosedTime(new Date(2017, 10, 10));


        Issue issue3 = new Issue();
        issue3.setAuthor(stefano);
        issue3.setTitle("Issue 3 Title");
        issue3.setBody("Issue 3 Body");
        issue3.setCreatedTime(new Date(2017, 10, 6));
        issue3.setClosedTime(new Date(2017, 10, 7));

        Issue issue4 = new Issue();
        issue4.setAuthor(stefano);
        issue4.setTitle("Issue 4 Title");
        issue4.setBody("Issue 4 Body");
        issue4.setCreatedTime(new Date(2017, 10, 6));
        issue4.setClosedTime(new Date(2017, 10, 6));

        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);

        return issues;
    }

    @Override
    public Collection<Issue> getFixedIssues(User user, Repository repository) throws GithubException {
        return null;
    }

    @Override
    public Collection<Issue> getOpenIssues(Repository repository) throws GithubException {
        ArrayList<Issue> issues = new ArrayList<>();

        User stefano = new User();
        stefano.setUrl("stefanoUri");
        stefano.setLogin("stefano");

        User daniel = new User();
        stefano.setUrl("danielUri");
        stefano.setLogin("daniel");

        Issue issue1 = new Issue();
        issue1.setAuthor(stefano);
        issue1.setTitle("Issue 1 Title");
        issue1.setBody("Issue 1 Body");
        issue1.setCreatedTime(new Date(2017, 10, 1));
        issue1.setClosedTime(new Date(2017, 10, 2));

        Issue issue2 = new Issue();
        issue2.setAuthor(daniel);
        issue2.setTitle("Issue 2 Title");
        issue2.setBody("Issue 2 Body");
        issue2.setCreatedTime(new Date(2017, 10, 7));
        issue2.setClosedTime(new Date(2017, 10, 10));


        Issue issue3 = new Issue();
        issue3.setAuthor(daniel);
        issue3.setTitle("Issue 3 Title");
        issue3.setBody("Issue 3 Body");
        issue3.setCreatedTime(new Date(2017, 10, 1));
        issue3.setClosedTime(new Date(2017, 10, 5));

        Issue issue4 = new Issue();
        issue4.setAuthor(stefano);
        issue4.setTitle("Issue 4 Title");
        issue4.setBody("Issue 4 Body");
        issue4.setCreatedTime(new Date(2017, 10, 5));
        issue4.setClosedTime(new Date(2017, 10, 6));

        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);

        return issues;
    }

    @Override
    public Collection<Issue> getOpenIssues(User user, Repository repository) throws GithubException {
        return null;
    }

    @Override
    public Collection<Commit> getCommitsInvolvedInIssue(Issue issue) throws GithubException {
        return null;
    }
}
