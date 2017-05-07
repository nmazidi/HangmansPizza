<%-- 
    Document   : newjsp
    Created on : 28-Apr-2017, 12:36:10
    Author     : Aaron
--%>

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
                            //$scope.data.address.ADDRESS_ID = "";
                            $scope.submit = function () {
                                $http({
                                 method: 'POST',
                                 url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/addresses',
                                 data: $httpParamSerializer($scope.data.address),
                                 headers: {'Content-Type': 'application/x-www-form-urlencoded',
                                 'Accept': 'application/json'}
                                 }).
                                 success(function (data, status, headers, config) {
                                 $window.location.replace('register.jsp');
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
                <li style="float:right"><a id='loginButton' href="#/?action=checklogin"><span>TestLogin</span></a></li>
                <li style="float:right"><a class="active" id='registerButton' href="register.jsp"><span id='registerButton'>TestRegister</span></a></li>
            </ul>
        </div>

        <div class='page mainSection container'>
            <ul>
                <li data-ng-repeat="message in messages">{{message}}</li>
            </ul>
            <fieldset>
                <legend>Address Details</legend>
                <label>Address Line 1:</label>
                <input type='text' name='addressLine1' placeholder="Address Line 1" ng-model="data.address.ADDRESS_LINE_1" align="right" required><br>
                <label>Address Line 2:</label>
                <input type='text' name='addressLine2' placeholder="Address Line 2" ng-model="data.address.ADDRESS_LINE_2"><br>
                <label>City:</label>
                <input type='text' name='city' placeholder="City" ng-model="data.address.TOWN" required><br>
                <label>Postcode:</label>
                <input type='text' name='postcode' placeholder="Postcode" ng-model="data.address.POSTCODE" required><br>
                <input type='text' name='id' ng-model="data.address.ADDRESS_ID" value="1" ng-hide="true"><br>
            </fieldset>
            <input type="button" value="Send" ng-click="submit()"/>


        </div>
    </body>
</html>