package it.unimol.anpr_github_metrics.jsp;

import it.unimol.anpr_github_metrics.session.SessionHandler;
import it.unimol.anpr_github_metrics.session.SessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
public class JSPUtils {
    private static JSPUtils instance;

    public static JSPUtils getInstance() {
        if (instance == null)
            return new JSPUtils();

        return instance;
    }

    public boolean require(HttpServletRequest request, HttpServletResponse response, SessionKey field) throws IOException {
        return requireUnlessRedirect(request, response, field, null);
    }

    public boolean requireUnlessRedirect(HttpServletRequest request, HttpServletResponse response, SessionKey field, String redirect) throws IOException {
        SessionHandler handler = SessionHandler.getInstance(request.getSession());

        if (!handler.hasField(field)) {
            if (redirect != null)
                response.sendRedirect(redirect);
            return false;
        }

        return true;
    }
}
