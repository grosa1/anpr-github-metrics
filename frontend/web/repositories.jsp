<%@ page import="it.unimol.anpr_github_metrics.jsp.JSPUtils" %>
<%@ page import="it.unimol.anpr_github_metrics.session.SessionKey" %>
<%@ page import="it.unimol.anpr_github_metrics.beans.Repository" %>
<%@ page import="it.unimol.anpr_github_metrics.session.SessionHandler" %>

<%
    JSPUtils utils = JSPUtils.getInstance();

    if (!utils.requireUnlessRedirect(request, response, SessionKey.MY_REPOSITORIES, "ShowRepositories")) {
        return;
    }

    Repository[] repositories = SessionHandler.getInstance(request.getSession()).getRepositories();
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Repositories</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">
    <link rel="stylesheet" href="CSS/repositories.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="demo-header mdl-layout__header mdl-color--cyan mdl-color-text--white">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">Seleziona repository</span>
            <div class="mdl-layout-spacer"></div>
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                <i class="material-icons">more_vert</i>
            </button>
            <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                <a class="mdl-menu__item" href="https://github.com/intersimone999/anpr-github-metrics" target="_blank" id="view-source">Informazioni su anpr-unimol</a>
            </ul>
        </div>
    </header>
    <main class="mdl-layout__content mdl-color--grey-100">

        <div class="demo-container mdl-grid">

            <!-- repo item -->

            <ul class="repository-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--cyan">
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Repository</h4>
                                eventually description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.jsp" class="mdl-button">use this repository</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>



            <!-- START REPO EXAMPLES - TO REMOVE -->
            <% for (Repository repository : repositories) { %>
                <ul class="repository-item mdl-list">
                    <li class="mdl-list__item mdl-list__item--six-line mdl-color--cyan">
                        <span class="mdl-list__item-primary-content">
                          <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                            <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                                <div class="mdl-card__supporting-text">
                                    <h4><%=repository.getName()%></h4>
                                    <!-- Description (if any) -->
                                </div>
                                <div class="mdl-card__actions">
                                    <a href="dashboard.jsp" class="mdl-button">use this repository</a>
                                </div>
                            </div>

                        </span>
                    </li>
                </ul>
            <% } %>
            <!-- END REPO EXAMPLES-->



        </div>
    </main>
</div>

<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
