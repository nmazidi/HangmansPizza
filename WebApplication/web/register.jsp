<%-- 
    Document   : newjsp
    Created on : 28-Apr-2017, 12:36:10
    Author     : Aaron
--%>


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
    
    <script>
        var MyApp = angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
        MyApp.controller('FormController', [
            '$scope', '$http', '$httpParamSerializer',
            function ($scope,  $http, $httpParamSerializer) {
                $scope.messages = [];
                $scope.data = {};
                //$scope.data.CUSTOMER_ID = 1;
                $scope.submit = function () {
                    $http({
                        method: 'POST',
                        url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/customers',
                        data: $httpParamSerializer($scope.data.customer),
                        //data: $scope.data.customer,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded',
                            'Accept': 'application/json'}
                    }).
                            success(function (data, status, headers, config) {
                                //$window.location.replace('index.jsp');
                            }).
                            error(function (data, status, headers, config) {
                                console.log($scope.data);
                                if (status === 400) {
                                    $scope.messages = data;
                                } else {
                                    alert('Unexpected server error. ');
                                }
                            });

                };
            }]);
        
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

    <body ng-controller="FormController">
        <div ng-controller="cookieCtrl">
            <ul class='navbar-static-top'>
                <li><a href="index.jsp">Hangman's Pizza</a></li>
                <li><a href="menu.jsp">Menu</a></li>
                <li style="float:right"><a href="logout.jsp" id='loginButton' ng-hide="!checkLoginStatus()"><span>Log Out</span></a></li>
                <li style="float:right"><a href="customerdetails.jsp" id='registerButton' ng-hide="!checkLoginStatus()"><span id='registerButton'>Account</span></a></li>
                <li style="float:right"><a href="basket.jsp"><span id='basketStatus'>Basket <img src="cart.png" alt="cart" height="42" width="42"></span></a></li>
                <li style="float:right"><a href="login.jsp" id='loginButton' ng-hide="checkLoginStatus()"><span>Log In</span></a></li>
                <li class='active' style="float:right"><a href="register.jsp" id='registerButton' ng-hide="checkLoginStatus()"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>

        <div class='page mainSection container'>
            <ul>
                <li data-ng-repeat="message in messages">{{message}}</li>
            </ul>
            <fieldset>
                <legend>Personal Information</legend>
                <label class="label">Title:</label>
                <input class="radio" type="radio" name="title" value="Mr" checked ng-model="data.customer.CUSTOMER_TITLE">Mr
                <input class="radio" type="radio" name="title" value="Mrs" ng-model="data.customer.CUSTOMER_TITLE">Mrs
                <input class="radio" type="radio" name="title" value="Other" ng-model="data.customer.CUSTOMER_TITLE">Other<br>
                <label class="label">First Name:</label>
                <input class="input-sm" type='text' name='firstName' placeholder="First Name" ng-model="data.customer.CUSTOMER_FORENAME"><br>-
                <label class="label">Last Name:</label>
                <input class="input-sm" type='text' name='lastName' placeholder="Last Name" ng-model="data.customer.CUSTOMER_SURNAME"><br>
                <label class="label">Date of birth:</label>
                <input class="input-sm" type="date" name="dob" placeholder="DoB" ng-model="data.customer.CUSTOMER_DOB">
                <label class="label">E-mail:</label>
                <input class="input-sm" type='text' name='email' placeholder="E-mail" ng-model="data.customer.CUSTOMER_EMAIL"><br>
                <label class="label">Phone Number:</label>
                <input class="input-sm" type="text" name="phone" placeholder="Phone Number" ng-model="data.customer.CUSTOMER_PHONE"<br>
                <label class="label">Password:</label>
                <input class="input-sm" type='password' name='password' placeholder="Password" ng-model="data.customer.CUSTOMER_PASSWORD" required><br>
                <label class="label">Re-type Password:</label>
                <input class="input-sm" type='password' name='passwordValidation' placeholder="Re-type Password"><br>
                <input type='text' name='id' ng-model="data.customer.CUSTOMER_ID" value="1" hidden="true"><br>
                <input type='text' name='id' ng-model="data.customer.ADDRESS_ID" value="1" hidden="true"><br>
                <input type='text' name='id' ng-model="data.customer.PASSWORD_SALT" value="1" hidden="true"><br>
            </fieldset>
            <input type="button" value="Send" ng-click="submit()"/>


        </div>
    </body>
</html>