package it.unimol.anpr_github_metrics.github;

import it.unimol.anpr_github_metrics.beans.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IssueExtractorStub implements IssueExtractor {
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

        Date date = new Date(2017, 10, 8);
        date.setHours(20);
        date.setMinutes(00);
        issue1.setCreatedTime(date);

        date = new Date(2017, 10, 9);
        date.setHours(10);
        date.setMinutes(00);
        issue1.setClosedTime(date);

        ArrayList<IssueComment> comments = new ArrayList<>();

        IssueComment comment1 = new IssueComment();
        date = new Date(2017, 10, 8);
        date.setHours(20);
        date.setMinutes(30);
        comment1.setCreatedTime(date);

        IssueComment comment2 = new IssueComment();
        date = new Date(2017, 10, 8);
        date.setHours(22);
        date.setMinutes(00);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue1.setComments(comments);

        Issue issue2 = new Issue();
        issue2.setAuthor(daniel);
        issue2.setTitle("Issue 2 Title");
        issue2.setBody("Issue 2 Body");

        date = new Date(2017, 10, 8);
        date.setHours(12);
        date.setMinutes(00);
        issue2.setCreatedTime(date);

        date = new Date(2017, 10, 9);
        date.setHours(20);
        date.setMinutes(00);
        issue2.setClosedTime(date);

        comments = new ArrayList<>();
        comment1 = new IssueComment();
        date = new Date(2017, 10, 8);
        date.setHours(14);
        date.setMinutes(30);
        comment1.setCreatedTime(date);

        comment2 = new IssueComment();
        date = new Date(2017, 10, 8);
        date.setHours(12);
        date.setMinutes(15);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue2.setComments(comments);

        Issue issue3 = new Issue();
        issue3.setAuthor(stefano);
        issue3.setTitle("Issue 3 Title");
        issue3.setBody("Issue 3 Body");

        date = new Date(2017, 10, 8);
        date.setHours(6);
        date.setMinutes(00);
        issue3.setCreatedTime(date);

        date = new Date(2017, 10, 10);
        date.setHours(20);
        date.setMinutes(30);
        issue3.setClosedTime(date);

        comments = new ArrayList<>();
        comment1 = new IssueComment();
        date = new Date(2017, 10, 8);
        date.setHours(7);
        date.setMinutes(50);
        comment1.setCreatedTime(date);

        comment2 = new IssueComment();
        date = new Date(2017, 10, 8);
        date.setHours(9);
        date.setMinutes(20);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue3.setComments(comments);


        Issue issue4 = new Issue();
        issue4.setAuthor(stefano);
        issue4.setTitle("Issue 4 Title");
        issue4.setBody("Issue 4 Body");

        date = new Date(2017, 10, 8);
        date.setHours(17);
        date.setMinutes(10);
        issue4.setCreatedTime(date);

        date = new Date(2017, 10, 10);
        date.setHours(20);
        date.setMinutes(00);
        issue4.setClosedTime(date);

        comments = new ArrayList<>();
        comment1 = new IssueComment();
        date = new Date(2017, 10, 9);
        date.setHours(7);
        date.setMinutes(30);
        comment1.setCreatedTime(date);

        comment2 = new IssueComment();
        date = new Date(2017, 10, 9);
        date.setHours(12);
        date.setMinutes(15);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue4.setComments(comments);

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

        Date date = new Date(2017, 10, 6);
        date.setHours(20);
        date.setMinutes(00);
        issue1.setCreatedTime(date);

        date = new Date(2017, 10, 9);
        date.setHours(15);
        date.setMinutes(00);
        issue1.setClosedTime(date);

        ArrayList<IssueComment> comments = new ArrayList<>();

        IssueComment comment1 = new IssueComment();
        date = new Date(2017, 10, 10);
        date.setHours(20);
        date.setMinutes(30);
        comment1.setCreatedTime(date);

        IssueComment comment2 = new IssueComment();
        date = new Date(2017, 10, 9);
        date.setHours(22);
        date.setMinutes(00);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue1.setComments(comments);

        Issue issue2 = new Issue();
        issue2.setAuthor(daniel);
        issue2.setTitle("Issue 2 Title");
        issue2.setBody("Issue 2 Body");

        date = new Date(2017, 10, 4);
        date.setHours(12);
        date.setMinutes(00);
        issue2.setCreatedTime(date);

        date = new Date(2017, 10, 7);
        date.setHours(16);
        date.setMinutes(30);
        issue2.setClosedTime(date);

        comments = new ArrayList<>();
        comment1 = new IssueComment();
        date = new Date(2017, 10, 5);
        date.setHours(14);
        date.setMinutes(30);
        comment1.setCreatedTime(date);

        comment2 = new IssueComment();
        date = new Date(2017, 10, 5);
        date.setHours(12);
        date.setMinutes(15);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue2.setComments(comments);

        Issue issue3 = new Issue();
        issue3.setAuthor(stefano);
        issue3.setTitle("Issue 3 Title");
        issue3.setBody("Issue 3 Body");

        date = new Date(2017, 10, 3);
        date.setHours(6);
        date.setMinutes(00);
        issue3.setCreatedTime(date);

        date = new Date(2017, 10, 7);
        date.setHours(10);
        date.setMinutes(00);
        issue3.setClosedTime(date);

        comments = new ArrayList<>();
        comment1 = new IssueComment();
        date = new Date(2017, 10, 5);
        date.setHours(7);
        date.setMinutes(50);
        comment1.setCreatedTime(date);

        comment2 = new IssueComment();
        date = new Date(2017, 10, 5);
        date.setHours(9);
        date.setMinutes(20);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue3.setComments(comments);


        Issue issue4 = new Issue();
        issue4.setAuthor(stefano);
        issue4.setTitle("Issue 4 Title");
        issue4.setBody("Issue 4 Body");

        date = new Date(2017, 10, 9);
        date.setHours(10);
        date.setMinutes(10);
        issue4.setCreatedTime(date);

        date = new Date(2017, 10, 9);
        date.setHours(23);
        date.setMinutes(00);
        issue4.setClosedTime(date);

        comments = new ArrayList<>();
        comment1 = new IssueComment();
        date = new Date(2017, 10, 9);
        date.setHours(17);
        date.setMinutes(30);
        comment1.setCreatedTime(date);

        comment2 = new IssueComment();
        date = new Date(2017, 10, 9);
        date.setHours(12);
        date.setMinutes(15);
        comment2.setCreatedTime(date);

        comments.add(comment1);
        comments.add(comment2);

        issue4.setComments(comments);

        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);

        return issues;
    }

    @Override
    public Collection<Issue> getOpenIssues(User user, Repository repository) throws GithubException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Collection<Commit> getCommitsInvolvedInIssue(Issue issue) throws GithubException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Collection<Issue> getIssues(Repository repository) throws GithubException {
        throw new RuntimeException("Not implemented");
    }
}
