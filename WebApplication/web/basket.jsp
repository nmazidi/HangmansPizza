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
	<title>Hangman's Pizza - Basket</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>

<body>
	<div>
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="menu.jsp">Menu</a></li>
			<li><a href="deals.jsp">Deals</a></li>
			<li><a class="active" href="basket.jsp"><span id='basketStatus'>Basket </span></a></li>
		<li style="float:right"><a id='loginButton' href="#/?action=checklogin"><span>TestLogin</span></a></li>
			<li style="float:right"><a id='registerButton' href="register.jsp"><span id='registerButton'>TestRegister</span></a></li>
		</ul>
	</div>

    <div class='page menuSection'>
        <h1>Basket</h1>
        

        <table id="itemBasket" style="text-align: center; table-align: center; margin: auto; width: 100%;">
            <tr>
                <th>Product Name</th><th>Item Price     </th>
            </tr>
            
            <tr>
                <td></td><td></td><td><a href="pizzabasket.php?action=removeitem&basketindex=<?php echo $x ?>">Remove From Basket</a></td>
            </tr>
            <tr>
                <td></br></td>
            </tr>
            <tr>
                <td>Sub total:</td><td></td>
            </tr>
        </table>
        <span><a href="pizzabasket.php?action=checkbasket">Proceed to checkout</a></span>
    </div>

	
</body>
</html>