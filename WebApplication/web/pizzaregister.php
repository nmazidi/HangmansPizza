<?php
if(isset($_GET['action']) && $_GET['action']=="checkregister"){
		if($_SESSION["username"] != NULL) {
			header("location: pizzaprofile.php");
		} else {
			header("location: pizzaregister.php");
		}
	}
?>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/pizza.css" title="default">
	<meta charset="UTF-8">
	<title>Hangman's Pizza - Home</title>
</head>

<body>
	<ul>
		<li><a href="pizzahome.php">Home</a></li>
		<li><a href="pizzamenu.php">Menu</a></li>
		<li><a href="pizzadeals.php">Deals</a></li>
		<li><a href="pizzabasket.php">Basket</a></li>
		<li style="float:right"><a href="pizzalogin.html">Log In</a></li>
		<li style="float:right"><a class="active" href="pizzaregister.php">Register</a></li>
	</ul>

		<div class='page mainSection'>
			<h1>Register</h1>
			<form action="pizzaregisterscript.php" method="post">
				First Name: <br>
				<input type="text" name="firstName"><br>
				Last Name: <br>
				<input type="text" name="lastName"><br>
				Username: <br>
				<input type="text" name="username"><br>
				Email: <br>
				<input type="text" name="email"><br>
				Password: <br>
				<input type="password" name="password"><br>
				Re-type Password: <br>
				<input type="password" name="passwordVarification"><br>
				Address Line 1: <br>
				<input type="text" name="addressLine1"><br>
				City: <br>
				<input type="text" name="city"><br>
				Postcode: <br>
				<input type="text" name="postcode"><br>
			<input type="submit">
			</form>
		</div>

		
		

	
</body>
</html>
