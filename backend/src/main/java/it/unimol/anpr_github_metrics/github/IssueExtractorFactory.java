package it.unimol.anpr_github_metrics.github;

/**
 * @author Simone Scalabrino.
 */
public class IssueExtractorFactory {
    public static IssueExtractor getInstance() {
        return new IssueExtractorImpl();
    }
}
