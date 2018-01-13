<!doctype html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Tickets</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileColor" content="#3372DF">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">
    <link rel="stylesheet" href="CSS/tickets.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
    </style>
</head>
<body>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <header class="demo-header mdl-layout__header mdl-color--yellow mdl-color-text--white">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">Home</span>
            <div class="mdl-layout-spacer"></div>
            <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                <i class="material-icons">more_vert</i>
            </button>
            <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                <li class="mdl-menu__item">About</li>
            </ul>
        </div>
    </header>
    <div class="demo-drawer mdl-layout__drawer mdl-color--orange mdl-color-text--white">
        <header class="demo-drawer-header">
            <img src=" " class="demo-avatar">
            <span>Hi Name User</span>
            <div class="demo-avatar-dropdown">
                <span>hello@example.com</span>
                <div class="mdl-layout-spacer"></div>
                <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                    <i class="material-icons" role="presentation">arrow_drop_down</i>
                    <span class="visuallyhidden">Accounts</span>
                </button>
                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                    <li class="mdl-menu__item">logout</li>
                </ul>
            </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey">
            <a class="mdl-navigation__link" href="dashboard.html"><i
                    class="mdl-color-text--blue-grey-400 material-icons" role="presentation"></i>Dashboard</a>

            <a class="mdl-navigation__link" href="settings.html"><i class="mdl-color-text--blue-grey-400 material-icons"
                                                                    role="presentation"></i>Settings</a>
            <a class="mdl-navigation__link" href="repositories.html"><i
                    class="mdl-color-text--blue-grey-400 material-icons" role="presentation"></i>Repositories</a>
            <div class="mdl-layout-spacer"></div>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons"
                                                       role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
        </nav>
    </div>

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
</div>

<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
