package it.unimol.anpr_github_metrics.github;

import it.unimol.anpr_github_metrics.beans.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class IssueExtractorStub implements IssueExtractor {
    @Override
    public Collection<User> getContributors(Repository repository) throws GithubException {
        return null;
    }

    @Override
    public Collection<Issue> getClosedIssues(Repository repository) throws GithubException {
        // User
        User user = new User();
        user.setUrl("userUrl");
        user.setLogin("userLogin");

        // Creation and closed Date (diff by 10 hour)
        Date creationDate = new Date(2017, 10, 6);
        creationDate.setHours(10);
        creationDate.setMinutes(00);

        Date closedDate = new Date(2017, 10, 6);
        closedDate.setHours(20);
        closedDate.setMinutes(00);


        // Comments
        Collection<IssueComment> comments;
        IssueComment comment = new IssueComment();
        comment.setAuthor(user);
        comment.setBody("This is a comment.");

        Date dateComment = new Date(2017, 10, 5);
        dateComment.setHours(14);
        dateComment.setMinutes(30);
        comment.setCreatedTime(dateComment);

        // Labels
        Collection<Issue.Label> labels = new HashSet<>();
        labels.add(Issue.Label.HELP);


        Collection<Issue> issues = new ArrayList<>();

        // Issues 1 - No comment, no label
        Issue issue1 = new Issue();
        issue1.setAuthor(user);
        issue1.setTitle("Uncommented and Unlabeled Closed Issue");
        issue1.setBody("This is an uncommented and unlabeled closed issue");
        issue1.setCreatedTime(creationDate);
        issue1.setClosedTime(closedDate);
        issue1.setComments(new ArrayList<>());   // No comments
        issue1.setLabels(new HashSet<>());       // No labels

        // Issues 2 - 1 Comment, no label
        Issue issue2 = new Issue();
        issue2.setAuthor(user);
        issue2.setTitle("Commented Open Issue");
        issue2.setBody("This an open issue with 1 comment and no label.");

        creationDate = new Date(2017, 10, 4);
        creationDate.setHours(12);
        creationDate.setMinutes(00);
        issue2.setCreatedTime(creationDate);
        issue2.setClosedTime(closedDate);

        comment.setIssue(issue2);
        comments = new ArrayList<>();
        comments.add(comment);
        issue2.setComments(comments);       // 1 comment
        issue2.setLabels(new HashSet<>());  // No labels

        // Issues 3 - No comment, 1 label
        Issue issue3 = new Issue();
        issue3.setAuthor(user);
        issue3.setTitle("Labeled Open Issue");
        issue3.setBody("This an open issue with 1 label and no comment.");
        issue3.setCreatedTime(creationDate);
        issue3.setClosedTime(closedDate);

        issue3.setComments(new ArrayList<>());   // No comment
        issue3.setLabels(labels);                // 1 label

        // Issues 4 - 1 comment, 1 label
        Issue issue4 = new Issue();
        issue4.setAuthor(user);
        issue4.setTitle("Labeled and Commented Open Issue");
        issue4.setBody("This is a open issue with one label and one comment");
        issue4.setCreatedTime(creationDate);
        issue4.setClosedTime(closedDate);

        comment.setIssue(issue4);
        comments = new ArrayList<>();
        comments.add(comment);
        issue4.setComments(comments);   // 1 comment
        issue4.setLabels(labels);       // 1 label

        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);

        return issues;
    }

    @Override
    public Collection<Issue> getClosedIssues(User user, Repository repository) throws GithubException {
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

        // User
        User user = new User();
        user.setUrl("userUrl");
        user.setLogin("userLogin");

        // Comments
        Collection<IssueComment> comments;
        IssueComment comment = new IssueComment();
        comment.setAuthor(user);
        comment.setBody("This is a comment.");

        Date dateComment = new Date(2017, 10, 5);
        dateComment.setHours(14);
        dateComment.setMinutes(30);
        comment.setCreatedTime(dateComment);

        // Labels
        Collection<Issue.Label> labels = new HashSet<>();
        labels.add(Issue.Label.HELP);


        Collection<Issue> issues = new ArrayList<>();

        // Issues 1 - No comment, no label
        Issue issue1 = new Issue();
        issue1.setAuthor(user);
        issue1.setTitle("Uncommented and Unlabeled Open Issue");
        issue1.setBody("This is an uncommented and unlabeled open issue");
        issue1.setLabels(new HashSet<>());

        Date date = new Date(2017, 10, 6);
        date.setHours(20);
        date.setMinutes(00);
        issue1.setCreatedTime(date);

        issue1.setComments(new ArrayList<>());   // No comments
        issue1.setLabels(new HashSet<>());       // No labels

        // Issues 2 - 1 Comment, no label
        Issue issue2 = new Issue();
        issue2.setAuthor(user);
        issue2.setTitle("Commented Open Issue");
        issue2.setBody("This an open issue with 1 comment and no label.");

        date = new Date(2017, 10, 4);
        date.setHours(12);
        date.setMinutes(00);
        issue2.setCreatedTime(date);

        comment.setIssue(issue2);
        comments = new ArrayList<>();
        comments.add(comment);
        issue2.setComments(comments);       // 1 comment
        issue2.setLabels(new HashSet<>());  // No labels

        // Issues 3 - No comment, 1 label
        Issue issue3 = new Issue();
        issue3.setAuthor(user);
        issue3.setTitle("Labeled Open Issue");
        issue3.setBody("This an open issue with 1 label and no comment.");

        date = new Date(2017, 10, 3);
        date.setHours(6);
        date.setMinutes(00);
        issue3.setCreatedTime(date);

        issue3.setComments(new ArrayList<>());   // No comment
        issue3.setLabels(labels);                // 1 label

        // Issues 4 - 1 comment, 1 label
        Issue issue4 = new Issue();
        issue4.setAuthor(user);
        issue4.setTitle("Labeled and Commented Open Issue");
        issue4.setBody("This is a open issue with one label and one comment");

        date = new Date(2017, 10, 9);
        date.setHours(10);
        date.setMinutes(10);
        issue4.setCreatedTime(date);

        comment.setIssue(issue4);
        comments = new ArrayList<>();
        comments.add(comment);
        issue4.setComments(comments);   // 1 comment
        issue4.setLabels(labels);       // 1 label

        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);

        return issues;
    }

    @Override
    public Collection<Issue> getOpenIssues(User user, Repository repository) throws GithubException {
        throw  new RuntimeException("Not implemented");
    }

    @Override
    public Collection<Commit> getCommitsInvolvedInIssue(Issue issue) throws GithubException {
        throw new RuntimeException("Not implemented");
    }


    @Override
    public Collection<Issue> getIssues(Repository repository) throws GithubException {
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
}
