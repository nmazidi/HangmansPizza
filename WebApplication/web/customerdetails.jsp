<%@page import="org.jboss.weld.context.http.Http"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html ng-app="MyApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-cookies.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
        <script src="controllers.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
       
        
       </head>

    <body>
                <div ng-controller="cookieCtrl">
            <ul>
                <li><a href="index.jsp">Hangman's Pizza</a></li>
                <li><a href="menu.jsp">Menu</a></li>
                <li style="float:right"><a href="logout.jsp" id='loginButton' ng-hide="!checkLoginStatus()"><span>Log Out</span></a></li>
                <li class='active' style="float:right"><a href="customerdetails.jsp" id='registerButton' ng-hide="!checkLoginStatus()"><span id='registerButton'>Account</span></a></li>
                <li style="float:right"><a href="basket.jsp"><span id='basketStatus'>Basket <img src="cart.png" alt="cart" height="42" width="42"></span></a></li>
                <li style="float:right"><a href="login.jsp" id='loginButton' ng-hide="checkLoginStatus()"><span>Log In</span></a></li>
                <li style="float:right"><a href="register.jsp" id='registerButton' ng-hide="checkLoginStatus()"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>


        <div class='page mainSection table' ng-controller="customerCtrl">
            <div style="text-align: center; width: 100%;">
                <table id="customerDetails" style="text-align: center; table-align: center; margin: auto; width: 100%;">
                    <tr>
                        <th style="text-align: center; width: 2000px;">Customer Name: </th>
                    </tr>

                    <tr>
                        <td style="text-align: center; width: 2000px;">{{firstName + " " + lastName}}</td>
                    </tr>
                    <tr>
                        <th style="text-align: center; width: 2000px;">Customer Email: </th>
                    </tr>

                    <tr>
                        <td style="text-align: center; width: 2000px;">{{email}}</td>
                    </tr>
                    <tr>
                        <th style="text-align: center; width: 2000px;">Customer Phone: </th>
                    </tr>

                    <tr>
                        <td style="text-align: center; width: 2000px;">{{phone}}</td>
                    </tr>
                    <tr>
                        <th style="text-align: center; width: 2000px;">Customer Date of Birth: </th>
                    </tr>

                    <tr>
                        <td style="text-align: center; width: 2000px;">{{dateOfBirth | date : "shortDate"}}</td>
                    </tr>
                </table>
            </div>
        </div>
    </body>

    <script>
        var app = angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
        app.controller('customerCtrl', function ($scope, $http, $cookieStore) {
            var currentUserId = $cookieStore.get('userId');
            $http.get("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/customers/" + currentUserId)
                    .then(function (response) {
                        $scope.firstName = response.data.CUSTOMER_FORENAME;
                $scope.firstName = response.data.CUSTOMER_FORENAME;
                $scope.lastName = response.data.CUSTOMER_SURNAME;
                $scope.email = response.data.CUSTOMER_EMAIL;
                $scope.phone = response.data.CUSTOMER_PHONE;
                $scope.dateOfBirth = response.data.CUSTOMER_DOB;
                    });

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
</html>