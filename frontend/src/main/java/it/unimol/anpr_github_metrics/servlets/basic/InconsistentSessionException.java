package it.unimol.anpr_github_metrics.servlets.basic;

/**
 * @author Simone Scalabrino.
 */
public class InconsistentSessionException extends Exception {
    public InconsistentSessionException(String context) {
        super("Inconsistent session as for " + context);
    }
}
