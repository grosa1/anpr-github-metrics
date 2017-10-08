package it.unimol.anpr_github_metrics.github;

/** This class provide a factory to use the Issue Extractor
 * @author Simone Scalabrino.
 */
public class IssueExtractorFactory {
    public static IssueExtractor extractor = new IssueExtractorImpl();

    public static void setImplementor(IssueExtractor concreteExtractor) {
        extractor = concreteExtractor;
    }
    public static IssueExtractor getInstance() {
        return extractor;
    }
}
