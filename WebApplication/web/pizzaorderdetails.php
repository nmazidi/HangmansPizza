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
if(isset($_GET['action']) && $_GET['action']=="checkregister"){
		if($_SESSION["username"] != NULL) {
			header("location: pizzaprofile.php");
		} else {
			header("location: pizzaregister.php");
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
			<li style="float:right"><a id='registerButton' href="pizzaregister.html"><span id='registerButton'><?php
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
		<h1>Order Details</h1>
<table id="itemBasket" style="text-align: center; table-align: center; margin: auto; width: 100%;">
				<tr>
					<th>Pizza Name     </th><th>Item Price     </th>
				</tr>
<?php
$subTotal = 0.00;
for($x = 0; $x < $_SESSION['sessionOrderNumber']; $x++) {
$_SESSION['cart'][$x]=array($_SESSION['orderItemID'][$x], $_SESSION['orderItemName'][$x], $_SESSION['orderItemPrice'][$x]);
?>
				<tr>
					<td><?php echo $_SESSION['orderItemName'][$x] ?></td><td>£<?php echo $_SESSION['orderItemPrice'][$x] ?></td>
				</tr>
<?php 
$subTotal = $subTotal + $_SESSION['orderItemPrice'][$x];
	}
?>
				<tr>
					<td>Sub total:</td><td>£<?php echo $subTotal ?></td>
				</tr>
			</table>
			
	</div>
	
</body>
