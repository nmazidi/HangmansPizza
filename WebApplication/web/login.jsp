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
	<title>Hangman's Pizza - Login</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>

<body>
	<div>
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="menu.jsp">Menu</a></li>
			<li><a href="deals.jsp">Deals</a></li>
			<li><a href="basket.jsp"><span id='basketStatus'>Basket </span></a></li>
		<li style="float:right"><a id='loginButton' class="active" href="#/?action=checklogin"><span>TestLogin</span></a></li>
			<li style="float:right"><a id='registerButton' href="register.jsp"><span id='registerButton'>TestRegister</span></a></li>
		</ul>
	</div>

	<div class='page mainSection'>
            <form action='login_submit.jsp' method='POST'>
                    Username:<br>
                    <input type='text' name='username' value=''><br>
                    Password:<br>
                    <input type='text' name='password' value=''><br>
                <input type='submit' value='submit'>
            </form>
        </div>
	
</body>
</html>