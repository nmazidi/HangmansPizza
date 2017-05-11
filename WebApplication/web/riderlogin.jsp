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
<html data-ng-app="FormApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Register</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

        <script>
            var formApp = angular.module('FormApp', []);
            -
                    formApp.controller('FormController', [
                        '$scope', '$window', '$http', '$httpParamSerializer',
                        function ($scope, $window, $http, $httpParamSerializer) {
                            $scope.messages = [];
                            $scope.data = {};
                            $scope.submit = function () {
                                $http({
                                    method: 'POST',
                                    url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider?login=login',
                                    data: $httpParamSerializer($scope.data.loginDetails),
                                    headers: {'Content-Type': 'application/x-www-form-urlencoded',
                                        'Accept': 'application/json'}
                                }).success(function (data, status, headers, config) {

                                    console.log(response.data);
                                }).
                                        error(function (data, status, headers, config) {
                                            if (status === 400) {
                                                $scope.messages = data;
                                            } else {
                                                alert('Unexpected server error. ');
                                            }
                                        });
                            };
                        }]);
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

    <body data-ng-controller="FormController">
        <div>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="menu.jsp">Menu</a></li>
                <li><a href="deals.jsp">Deals</a></li>
                <li><a href="basket.jsp"><span id='basketStatus'>Basket </span></a></li>
                <li style="float:right" class="active"><a id='loginButton' href="#/?action=checklogin"><span>TestLogin</span></a></li>
                <li style="float:right"><a class="active" id='registerButton' href="register.jsp"><span id='registerButton'>TestRegister</span></a></li>
            </ul>
        </div>

        <div class='page mainSection container'>
            <ul>
                <li data-ng-repeat="message in messages">{{message}}</li>
            </ul>
            <ul>
                <li data-ng-repeat="x in myData">{{x.CUSTOMER_ID}}</li>
            </ul>
            <fieldset>
                <legend>Login Details</legend>
                <label>Email:</label>
                <input type='text' name='email' placeholder="Email" ng-model="data.loginDetails.email" required><br>
                <label>Password:</label>
                <input type='password' name='password' placeholder="Password" ng-model="data.loginDetails.password"><br>
            </fieldset>
            <input type="button" value="Send" ng-click="submit()"/>


        </div>
    </body>
</html>