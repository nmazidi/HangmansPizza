<?php
session_start();
require("pizzaconnections.php");

if (!isset($_SESSION['cart'])) {
    $_SESSION['cart'] = array();
}

if (!isset($_SESSION['sessionOrderNumber'])) {
    $_SESSION['sessionOrderNumber'] = 0;
}

if (!isset($_SESSION['orderItemID'])) {
    $_SESSION['orderItemID'] = array();
}

if (!isset($_SESSION['orderItemPrice'])) {
    $_SESSION['orderItemPrice'] = array();
}

if (!isset($_SESSION['orderItemName'])) {
    $_SESSION['orderItemName'] = array();
}
?>

<?php
	if(isset($_GET['action']) && $_GET['action']=="checklogin"){
		if($_SESSION["username"] != NULL) {
			session_unset();

			session_destroy();

			header("location: pizzahome.php");
		} else {
			header("location: pizzalogin.php");
		}
	}

if(isset($_GET['action']) && $_GET['action']=="checkregister"){
		if($_SESSION["username"] != NULL) {
			header("location: pizzaprofile.php");
		} else {
			header("location: pizzaregister.php");
		}
	}
?>

<?php
if($_SESSION["username"] != NULL) {
	$username = $_SESSION["username"];
	$sql="SELECT * FROM Customer WHERE Username = '$username'";
		$query=mysql_query($sql);
			
		while ($row=mysql_fetch_array($query)) {
			$email = $row['Email'];
			$addressLine1 = $row['AddressLine1'];
			$city = $row['City'];
			$postcode = $row['Postcode'];
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
	<div>
		<ul>
			<li><a href="pizzahome.php">Home</a></li>
			<li><a href="pizzamenu.php">Menu</a></li>
			<li><a href="pizzadeals.php">Deals</a></li>
			<li><a href="pizzabasket.php"><span id='basketStatus'>Basket <?php echo $_SESSION['sessionOrderNumber']?></span></a></li>
		<li style="float:right"><a id='loginButton' href="pizzahome.php?action=checklogin"><span><?php
if ($_SESSION["username"] != NULL){
	echo "Log Out";
}
else {
	echo "Log In";
}
?></span></a></li>
			<li style="float:right"><a id='registerButton' href="pizzaregister.php"><span id='registerButton'><?php
if ($_SESSION["username"] != NULL){
	echo "Hi, " . $_SESSION["username"];
}
else {
	echo "Register";
}
?></span></a></li>
		</ul>
	</div>

		<div class='page mainSection'>
			<h1>Delivery Details</h1>
			<form action="pizzapayment.php" method="post">
				Username: <br>
				<input type="text" name="username" value="<?php echo $username ?>"/><br>
				Email: <br>
				<input type="text" name="email" value="<?php echo $email ?>"/><br>
				Address Line 1: <br>
				<input type="text" name="addressLine1" value="<?php echo $addressLine1 ?>"/><br>
				City: <br>
				<input type="text" name="city" value="<?php echo $city ?>"/><br>
				Postcode: <br>
				<input type="text" name="postcode" value="<?php echo $postcode ?>"/><br>
			<input type="submit" value="Proceed to payment">
			</form>
		</div>
	
</body>
