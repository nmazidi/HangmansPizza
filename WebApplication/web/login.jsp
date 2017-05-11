<%-- 
    Document   : newjsp
    Created on : 28-Apr-2017, 12:36:10
    Author     : Aaron
--%>

<%@page import="java.security.MessageDigest"%>
<%
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="MyApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Register</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-cookies.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
        <script src="controllers.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

        <script>
            var MyApp = angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
            -
                    MyApp.controller('FormController',
                        function ($scope, $window, $http, $httpParamSerializer, $cookieStore) {
                            $scope.messages = [];
                            $scope.customers = {};
                            $scope.data = {};
                            $scope.submit = function () {
                                $http({
                                    method: 'POST',
                                    url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/customers?login=login',
                                    data: $httpParamSerializer($scope.data.loginDetails),
                                    headers: {'Content-Type': 'application/x-www-form-urlencoded',
                                        'Accept': 'application/json'}
                                }).then(function (response) {
                                    $scope.data = response.data;
                                    $cookieStore.put("userId", response.data.CUSTOMER_ID);
                                    $cookieStore.put("customerName", response.data.CUSTOMER_FORENAME);
                                    $cookieStore.put("username", response.data.CUSTOMER_EMAIL);
                                    $cookieStore.put("isLoggedIn", "true");
                                    $cookieStore.put("loginStatus", "loggedIn");
                                    var basket = {};
                                    $cookieStore.put("basket", basket);
                                    $cookieStore.put("basketIndex", 0);
                                    $window.location.href = 'index.jsp';
                                });
                            };
                            $scope.ReadCookie = function () {
                                $window.alert($cookieStore.get('userId'));
                                };
                        });
                        
            angular.module('MyApp').controller('cookieCtrl', function ($scope, $window, $cookieStore) {

                $scope.postcodeEntered = function (data) {
                    $cookieStore.put('postcode', data);
                    $window.alert($cookieStore.get('postcode'));
                };

                $scope.getLoginStatus = function () {
                    return $cookieStore.get('loginStatus');
                };

                $scope.getLoginName = function () {
                    return $cookieStore.get('customerName');
                };

                $scope.checkLoginStatus = function () {
                    var checkResult;
                    var cookieToCheck = $cookieStore.get('loginStatus');
                    if (cookieToCheck === "loggedIn") {
                        checkResult = true;
                        return checkResult;
                    } else {
                        checkResult = false;
                        return checkResult;
                    }
                };

                $scope.getCurrentUsername = function () {
                    var checkResult;
                    var cookieToCheck = $cookieStore.get('loginStatus');
                    if (cookieToCheck === "loggedIn") {
                        return $cookieStore.get('username');
                    } else {
                        checkResult = false;
                        return 'Register';
                    }
                };

                $scope.getCurrentUserId = function () {
                    var checkResult;
                    var cookieToCheck = $cookieStore.get('loginStatus');
                    if (cookieToCheck === "loggedIn") {
                        return $cookieStore.get('userId');
                    }
                };

                $scope.loginOutSwitch = function () {
                    var loginStatus = checkLoginStatus();
                    if (loginStatus === true) {
                        return 'Log Out';
                    } else {
                        return 'Log Out';
                    }
                };

                $scope.checkBasketIndex = function (basketIndex) {
                    if ($cookieStore.get('basketIndex') === null) {
                        basketIndex = 0;
                        $cookieStore.put('basketIndex', 1);
                    } else {
                        basketIndex = basketIndex++;
                        $cookieStore.put('basketIndex', basketIndex);
                    }
                    return basketIndex;
                };

                $scope.logout = function () {
                    angular.forEach($cookieStore, function (v, k) {
                        $cookieStore.remove(k);
                    });
                };



            });
        </script>

        <style type="text/css">
            .container {
                width: 500px;
                clear: both;
            }
            .container input {
                width: 100%;
                clear: both;
            }

        </style>
    </head>

    <body ng-controller="FormController">
        <div ng-controller="cookieCtrl">
            <ul>
                <li><a href="index.jsp">Hangman's Pizza</a></li>
                <li><a href="menu.jsp">Menu</a></li>
                <li style="float:right"><a href="logout.jsp" id='loginButton' ng-hide="!checkLoginStatus()"><span>Log Out</span></a></li>
                <li style="float:right"><a href="customerdetails.jsp" id='registerButton' ng-hide="!checkLoginStatus()"><span id='registerButton'>Account</span></a></li>
                <li style="float:right"><a href="basket.jsp"><span id='basketStatus'>Basket <img src="cart.png" alt="cart" height="42" width="42"></span></a></li>
                <li class='active' style="float:right"><a href="login.jsp" id='loginButton' ng-hide="checkLoginStatus()"><span>Log In</span></a></li>
                <li style="float:right"><a href="register.jsp" id='registerButton' ng-hide="checkLoginStatus()"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>

        <div class='page mainSection container'>
            <ul>
                <li data-ng-repeat="message in messages">{{message}}</li>
            </ul>
            <fieldset>
                <legend>Login Details</legend>
                <label>Email:</label>
                <input type='text' name='email' placeholder="Email" ng-model="data.loginDetails.email" required><br>
                <label>Password:</label>
                <input type='password' name='password' placeholder="Password" ng-model="data.loginDetails.password"><br>
            </fieldset>
            <input type="button" value="Log In" ng-click="submit()"/>


        </div>
    </body>
</html>