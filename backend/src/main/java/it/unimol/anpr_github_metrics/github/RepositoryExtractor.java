package it.unimol.anpr_github_metrics.github;

import it.unimol.anpr_github_metrics.beans.Repository;

/**
 * @author Stefano Dalla Palma
 */
public interface RepositoryExtractor {

    /**
     * Get the list of all contributors of a repository
     * @param repository the repository to populate
     * @return a populated repository
     */
    public Repository getRepository(Repository repository) throws GithubException;

}
