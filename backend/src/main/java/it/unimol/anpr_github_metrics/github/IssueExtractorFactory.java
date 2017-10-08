package it.unimol.anpr_github_metrics.github;

import com.jcabi.github.Github;

/** This class provide a factory to use the Issue Extractor
 * @author Simone Scalabrino.
 */
public class IssueExtractorFactory {
    public enum InstantiationStrategy {
        PRODUCTION,
        TESTING
    }
    private static InstantiationStrategy instantiationStrategy = InstantiationStrategy.PRODUCTION;

    public static void setImplementorStrategy(InstantiationStrategy strategy) {
        if (strategy == null)
            throw new RuntimeException("Invalid implementation");

        instantiationStrategy = strategy;
    }

    public static IssueExtractor getInstance(Github github) {
        switch (instantiationStrategy) {
            case PRODUCTION:
                return new IssueExtractorImpl(github);
            case TESTING:
                return new IssueExtractorStub();
            default:
                throw new RuntimeException("Invalid implementation");
        }
    }
}
