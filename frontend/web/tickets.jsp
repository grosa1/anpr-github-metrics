<%--<!doctype html>--%>
<%--<!----%>
  <%--Material Design Lite--%>
  <%--Copyright 2015 Google Inc. All rights reserved.--%>

  <%--Licensed under the Apache License, Version 2.0 (the "License");--%>
  <%--you may not use this file except in compliance with the License.--%>
  <%--You may obtain a copy of the License at--%>

      <%--https://www.apache.org/licenses/LICENSE-2.0--%>

  <%--Unless required by applicable law or agreed to in writing, software--%>
  <%--distributed under the License is distributed on an "AS IS" BASIS,--%>
  <%--WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.--%>
  <%--See the License for the specific language governing permissions and--%>
  <%--limitations under the License--%>
<%---->--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">--%>
    <%--<title>Tickets</title>--%>

    <%--<!-- Add to homescreen for Chrome on Android -->--%>
    <%--<meta name="mobile-web-app-capable" content="yes">--%>

    <%--<!-- Add to homescreen for Safari on iOS -->--%>
    <%--<meta name="apple-mobile-web-app-capable" content="yes">--%>
    <%--<meta name="apple-mobile-web-app-status-bar-style" content="black">--%>
    <%--<meta name="apple-mobile-web-app-title" content="Material Design Lite">--%>

    <%--<!-- Tile icon for Win8 (144x144 + tile color) -->--%>
    <%--<meta name="msapplication-TileColor" content="#3372DF">--%>

    <%--<!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->--%>
    <%--<!----%>
    <%--<link rel="canonical" href="http://www.example.com/">--%>
    <%---->--%>

    <%--<link rel="stylesheet"--%>
          <%--href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">--%>
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <%--<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">--%>
    <%--<link rel="stylesheet" href="CSS/tickets.css">--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">--%>

    <%--<style>--%>
        <%--#view-source {--%>
            <%--position: fixed;--%>
            <%--display: block;--%>
            <%--right: 0;--%>
            <%--bottom: 0;--%>
            <%--margin-right: 40px;--%>
            <%--margin-bottom: 40px;--%>
            <%--z-index: 900;--%>
        <%--}--%>
    <%--</style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">--%>
    <%--<header class="demo-header mdl-layout__header mdl-color--yellow mdl-color-text--white">--%>
        <%--<div class="mdl-layout__header-row">--%>
            <%--<span class="mdl-layout-title">Home</span>--%>
            <%--<div class="mdl-layout-spacer"></div>--%>
            <%--<button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">--%>
                <%--<i class="material-icons">more_vert</i>--%>
            <%--</button>--%>
            <%--<ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">--%>
                <%--<li class="mdl-menu__item">About</li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</header>--%>
    <%--<div class="demo-drawer mdl-layout__drawer mdl-color--orange mdl-color-text--white">--%>
        <%--<header class="demo-drawer-header">--%>
            <%--<img src=" " class="demo-avatar">--%>
            <%--<span>Hi Name User</span>--%>
            <%--<div class="demo-avatar-dropdown">--%>
                <%--<span>hello@example.com</span>--%>
                <%--<div class="mdl-layout-spacer"></div>--%>
                <%--<button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">--%>
                    <%--<i class="material-icons" role="presentation">arrow_drop_down</i>--%>
                    <%--<span class="visuallyhidden">Accounts</span>--%>
                <%--</button>--%>
                <%--<ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">--%>
                    <%--<li class="mdl-menu__item">logout</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</header>--%>
        <%--<nav class="demo-navigation mdl-navigation mdl-color--blue-grey">--%>
            <%--<a class="mdl-navigation__link" href="dashboard.html"><i--%>
                    <%--class="mdl-color-text--blue-grey-400 material-icons" role="presentation"></i>Dashboard</a>--%>

            <%--<a class="mdl-navigation__link" href="settings.html"><i class="mdl-color-text--blue-grey-400 material-icons"--%>
                                                                    <%--role="presentation"></i>Settings</a>--%>
            <%--<a class="mdl-navigation__link" href="repositories.html"><i--%>
                    <%--class="mdl-color-text--blue-grey-400 material-icons" role="presentation"></i>Repositories</a>--%>
            <%--<div class="mdl-layout-spacer"></div>--%>
            <%--<a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons"--%>
                                                       <%--role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>--%>
        <%--</nav>--%>
    <%--</div>--%>
<style>
    html, body {
        font-family: 'Roboto', 'Helvetica', sans-serif;
    }
    .demo-avatar {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        padding: 10px;
    }

    .demo-container {
        justify-content: center;
    }

    .ticket-item {
        padding: 20px;
    }

    .demo-layout .mdl-layout__header .mdl-layout__drawer-button {
        color: rgba(0, 0, 0, 0.54);
    }
    .mdl-layout__drawer .avatar {
        margin-bottom: 16px;
    }
    .demo-drawer {
        border: none;
    }
    /* iOS Safari specific workaround */
    .demo-drawer .mdl-menu__container {
        z-index: -1;
    }
    .demo-drawer .demo-navigation {
        z-index: -2;
    }
    /* END iOS Safari specific workaround */
    .demo-drawer .mdl-menu .mdl-menu__item {
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
    }
    .demo-drawer-header {
        box-sizing: border-box;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        -webkit-justify-content: flex-end;
        -ms-flex-pack: end;
        justify-content: flex-end;
        padding: 16px;
        height: 151px;
    }
    .demo-avatar-dropdown {
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        position: relative;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
        width: 100%;
    }

    .demo-navigation {
        -webkit-flex-grow: 1;
        -ms-flex-positive: 1;
        flex-grow: 1;
    }
    .demo-layout .demo-navigation .mdl-navigation__link {
        display: -webkit-flex !important;
        display: -ms-flexbox !important;
        display: flex !important;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
        color: rgba(255, 255, 255, 0.56);
        font-weight: 500;
    }
    .demo-layout .demo-navigation .mdl-navigation__link:hover {
        background-color: #00BCD4;
        color: #37474F;
    }
    .demo-navigation .mdl-navigation__link .material-icons {
        font-size: 24px;
        color: rgba(255, 255, 255, 0.56);
        margin-right: 32px;
    }

    .demo-content {
        max-width: 1080px;
    }

    .demo-charts {
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
    }
    .demo-chart:nth-child(1) {
        color: #ACEC00;
    }
    .demo-chart:nth-child(2) {
        color: #00BBD6;
    }
    .demo-chart:nth-child(3) {
        color: #BA65C9;
    }
    .demo-chart:nth-child(4) {
        color: #EF3C79;
    }
    .demo-graphs {
        padding: 16px 32px;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        -webkit-flex-direction: column;
        -ms-flex-direction: column;
        flex-direction: column;
        -webkit-align-items: stretch;
        -ms-flex-align: stretch;
        align-items: stretch;
    }
    /* TODO: Find a proper solution to have the graphs
     * not float around outside their container in IE10/11.
     * Using a browserhacks.com solution for now.
     */
    _:-ms-input-placeholder, :root .demo-graphs {
        min-height: 664px;
    }
    _:-ms-input-placeholder, :root .demo-graph {
        max-height: 300px;
    }

    /* TODO end */
    .demo-graph:nth-child(1) {
        color: #00b9d8;
    }
    .demo-graph:nth-child(2) {
        color: #d9006e;
    }

    .demo-cards {
        -webkit-align-items: flex-start;
        -ms-flex-align: start;
        align-items: flex-start;
        -webkit-align-content: flex-start;
        -ms-flex-line-pack: start;
        align-content: flex-start;
    }
    .demo-cards .demo-separator {
        height: 32px;
    }
    .demo-cards .mdl-card__title.mdl-card__title {
        color: white;
        font-size: 24px;
        font-weight: 400;
    }
    .demo-cards ul {
        padding: 0;
    }
    .demo-cards h3 {
        font-size: 1em;
    }
    .demo-updates .mdl-card__title {
        min-height: 200px;
        background-image: url('images/dog.png');
        background-position: 90% 100%;
        background-repeat: no-repeat;
    }
    .demo-cards .mdl-card__actions a {
        color: #00BCD4;
        text-decoration: none;
    }

    .demo-options h3 {
        margin: 0;
    }
    .demo-options .mdl-checkbox__box-outline {
        border-color: rgba(255, 255, 255, 0.89);
    }
    .demo-options ul {
        margin: 0;
        list-style-type: none;
    }
    .demo-options li {
        margin: 4px 0;
    }
    .demo-options .material-icons {
        color: rgba(255, 255, 255, 0.89);
    }
    .demo-options .mdl-card__actions {
        height: 64px;
        display: -webkit-flex;
        display: -ms-flexbox;
        display: flex;
        box-sizing: border-box;
        -webkit-align-items: center;
        -ms-flex-align: center;
        align-items: center;
    }

    .mdl-list__item{
        width: 400px;
    }

    .h2{
        text-align: center;
    }
</style>


    <main class="mdl-layout__content mdl-color--grey-100">

        <div class="demo-container mdl-grid">

            <!-- repo item -->

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>



            <!-- START REPO EXAMPLES - TO REMOVE -->
            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>

            <ul class="ticket-item mdl-list">
                <li class="mdl-list__item mdl-list__item--six-line mdl-color--yellow mdl mdl-shadow--6dp" >
                    <span class="mdl-list__item-primary-content">
                      <i class="fa fa-github fa-5x" aria-hidden="true"></i>

                        <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card__supporting-text">
                                <h4>Issue</h4>
                                #description
                            </div>
                            <div class="mdl-card__actions">
                                <a href="dashboard.html" class="mdl-button">select issue</a>
                            </div>
                        </div>

                    </span>
                </li>
            </ul>
            <!-- END REPO EXAMPLES-->



        </div>
    </main>
<%--</div>--%>


<%--<a href="https://github.com/intersimone999/anpr-github-metrics" target="_blank" id="view-source"--%>
   <%--class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored mdl-color-text--white">View--%>
    <%--Source</a>--%>
<%--<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
