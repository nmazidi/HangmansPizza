<%-- 
    Document   : newjsp
    Created on : 28-Apr-2017, 12:36:10
    Author     : Aaron
--%>

<%@page import="java.security.MessageDigest"%>
<%
    String text = "";
    //request.getParameter(CUSTOMER_PASSWORD);

    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(text.getBytes());

    byte byteData[] = md.digest();

    //convert the byte to hex format method 1
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }

    System.out.println("Hex format : " + sb.toString());

    String hashedString = sb.toString();
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

                                /*$http({
                                    method: 'POST',
                                    url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/addresses',
                                    data: $httpParamSerializer($scope.data.address),
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
                                        });*/
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
                <legend>Personal Information</legend>
                <label>Title:</label>
                <input type="radio" name="title" value="Mr" checked ng-model="data.customer.CUSTOMER_TITLE">Mr<br>
                <input type="radio" name="title" value="Mrs" ng-model="data.customer.CUSTOMER_TITLE">Mrs<br>
                <input type="radio" name="title" value="Other" ng-model="data.customer.CUSTOMER_TITLE">Other<br>
                <label>First Name:</label>
                <input type='text' name='firstName' placeholder="First Name" ng-model="data.customer.CUSTOMER_FORENAME"><br>
                <label>Last Name:</label>
                <input type='text' name='lastName' placeholder="Last Name" ng-model="data.customer.CUSTOMER_SURNAME"><br>
                <label>Date of birth:</label>
                <input type="date" name="dob" placeholder="DoB" ng-model="data.customer.CUSTOMER_DOB">
                <!--<label>Username:</label>
                <input type='text' name='username' placeholder="Username" ng-model="data.customer.CUSTOMER_USERNAME"><br>-->
                <label>E-mail:</label>
                <input type='text' name='email' placeholder="E-mail" ng-model="data.customer.CUSTOMER_EMAIL"><br>
                <label>Phone Number:</label>
                <input type="text" name="phone" placeholder="Phone Number" ng-model="data.customer.CUSTOMER_PHONE"<br>
                <label>Password:</label>
                <input type='password' name='password' placeholder="Password" ng-model="data.customer.CUSTOMER_PASSWORD" required><br>
                <label>Re-type Password:</label>
                <input type='password' name='passwordValidation' placeholder="Re-type Password"><br>
                <input type='text' name='id' ng-model="data.customer.CUSTOMER_ID" value="1" ng-hide="true"><br>
                <input type='text' name='id' ng-model="data.customer.ADDRESS_ID" value="1" ng-hide="true"><br>
            </fieldset>
            <input type="button" value="Send" ng-click="submit()"/>


        </div>
    </body>
</html>