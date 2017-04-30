<%-- 
    Document   : newjsp
    Created on : 28-Apr-2017, 12:36:10
    Author     : Aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Register</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    </head>

    <body>
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

        <div class='page mainSection'>
            <form action='register_submit.jsp' method='POST'>
                <fieldset>
                    <legend>Personal Information</legend>
                    First Name:<br>
                    <input type='text' name='firstName' value=''><br>
                    Last Name:<br>
                    <input type='text' name='lastName' value=''><br>
                    Username:<br>
                    <input type='text' name='username' value=''><br>
                    Password:<br>
                    <input type='text' name='password' value=''><br>
                    Re-type Password:<br>
                    <input type='text' name='passwordValidation' value=''><br>
                </fieldset>
                <fieldset>
                    <legend>Address Details</legend>
                    Address Line 1:<br>
                    <input type='text' name='addressLine1' value=''><br>
                    Address Line 2:<br>
                    <input type='text' name='addressLine2' value=''><br>
                    City:<br>
                    <input type='text' name='city' value=''><br>
                    Postcode:<br>
                    <input type='text' name='postcode' value=''><br>
                </fieldset>
                <input type='submit' value='submit'>
            </form>
        </div>

    </body>
</html>