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
	<title>Hangman's Pizza - Menu</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>

<body>
	<div>
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a class="active" href="menu.jsp">Menu</a></li>
			<li><a href="deals.jsp">Deals</a></li>
			<li><a href="basket.jsp"><span id='basketStatus'>Basket </span></a></li>
		<li style="float:right"><a id='loginButton' href="#/?action=checklogin"><span>TestLogin</span></a></li>
			<li style="float:right"><a id='registerButton' href="register.jsp"><span id='registerButton'>TestRegister</span></a></li>
		</ul>
	</div>

	<div class='page mainSection'>
			<table style="width:160px; text-align: center;">
  				<tr>
    					<th style="height: 150px; text-align: center;"><?php echo $row['ProductName'] ?></th>
  				</tr>
				<tr>
    					<td>
						<select name="size" style="width: 100%">
 						<option value="0">Large ()</option>
						<option value="1">Medium ()</option>
						<option value="2">Small ()</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<select name="crust" style="width: 100%">
 						<option value="1">Classic (£0.00)</option>
						<option value="2">Cheese (£2.00)</option>
						<option value="3">BBQ (£2.00)</option>
						</select>
					</td>
  				</tr>
  				</tr>
  				<tr>
    					<td><a href="pizzamenu.php?action=add&id=<?php echo $row['ProductID'] ?>">Add to Basket</td>
  				</tr>
			</table>
	</div>
	
</body>
</html>