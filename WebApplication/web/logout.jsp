<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="MyApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Logout</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-cookies.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body ng-controller="cookieCtrl" ng-init="logout()">
        <h1>{{test}}</h1>
    </body>

    <script>
        angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
        angular.module('MyApp').controller('cookieCtrl', function ($scope, $window, $cookieStore) {

            $cookieStore.remove("userId");
            $cookieStore.remove("customerName");
            $cookieStore.remove("username");
            $cookieStore.remove("isLoggedIn");
            $cookieStore.remove("loginStatus");
            $window.location.href = 'index.jsp';
        });
        
        
    </script>
</html>
