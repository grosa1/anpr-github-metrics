<%@ page import="it.unimol.anpr_github_metrics.configuration.OAuthParms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<div class="demo-container mdl-grid">

    <div class="demo-content mdl-color--white mdl-shadow--6dp content mdl-color-text--grey-800 mdl-cell mdl-cell--6-col">

        <div class="mdl-card__title mdl-color--light-blue mdl-color-text--white">
            <h2 class="mdl-card__title-text">ZEUS login</h2>
        </div>

        <form class="mdl-cell mdl-cell--12-col cell_con">
            <div id="github">
                <i class="fa fa-github fa-5x" aria-hidden="true"></i>
            </div>
            <div class="mdl-card__actions" id="login">
                <a class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"

                   href="http://github.com/login/oauth/authorize?client_id=${client_id}&redirect_uri=${redirect_uri}&state=${state}">

                        <%--<c:choose>--%>
                            <%--<c:when test="${empty sessionScope.token}">--%>
                                <%--href=<%="http://github.com/login/oauth/authorize?client_id=" + OAuthParms.CLIENT_ID--%>
                                    <%--+ "&redirect_uri=" + OAuthParms.REDIRECT_URI--%>
                                    <%--+ "&state=" + OAuthParms.STATE%> >--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--href="${pageContext.request.contextPath}/repos" >--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>

                    Login con GitHub
                <%--href="http://github.com/login/oauth/authorize?client_id=1211d954012cf73c2e2b&redirect_uri=http://www.unimol.it&state=dsfasgadfhsdghadf">--%>

                </a>
            </div>
        </form>

    </div>
</div>

</body>
</html>

