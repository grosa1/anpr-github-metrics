package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Comment;
import com.jcabi.github.Github;
import it.unimol.anpr_github_metrics.beans.Issue;
import it.unimol.anpr_github_metrics.beans.IssueComment;
import it.unimol.anpr_github_metrics.beans.User;
import it.unimol.anpr_github_metrics.utils.DateUtils;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @author Stefano Dalla Palma
 */
public class CommentLoaderThread implements Callable<IssueComment> {
    private final Github github;
    private final Issue issue;
    private final Comment remoteComment;

    public CommentLoaderThread(Github github, Issue issue, Comment remoteComment) {
        this.github = github;
        this.issue = issue;
        this.remoteComment = remoteComment;
    }

    @Override
    public IssueComment call() throws Exception {

        JsonObject commentJson = this.remoteComment.json();

        IssueComment comment = new IssueComment();
        comment.setAuthor(loadUser(commentJson.getJsonObject("user").getString("login")));
        comment.setBody(commentJson.getString("body"));
        comment.setCreatedTime(DateUtils.getMandatoryDate(commentJson.getString("created_at")));
        comment.setUpdatedTime(DateUtils.getMandatoryDate(commentJson.getString("updated_at")));
        comment.setUrl(commentJson.getString("html_url"));
        comment.setIssue(issue);

        return comment;
    }

    private User loadUser(String userLogin) throws IOException {
        com.jcabi.github.User remoteUser = this.github.users().get(userLogin);
        User user = new User();
        user.setLogin(userLogin);
        user.setUrl(remoteUser.json().getString("html_url"));

        return user;
    }
}
