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

<?php /*
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

		
*/
	
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

          <script language="Javascript">
               function show(x)
                              {
                                var element=document.getElementById(x.id);
                                if(element.id=='a')
                                {
                                  document.getElementById("cash").style.visibility = "visible";
				  document.getElementById("card").style.visibility = "hidden";
                                }
                                else 
                                {
				  document.getElementById("cash").style.visibility = "hidden";
                                  document.getElementById("card").style.visibility = "visible";
                                }
                              }
           </script>

		<div class='page mainSection'>
			<h1>Payment Method</h1>
        		<input type="radio" onclick="show(this)" name="radioButton"  id="a" checked>Pay With Cash  
        		<input type="radio" onclick="show(this)" name="radioButton"  id="b" >Pay With Card
			<div id="cash">
			<h2>Cash</h2>
			</div>
			<div id="card" style="visibility: hidden;">
			<h2>Card</h2>
			<form action="pizzacheckoutscript.php" method="post">
				Card Number: <br>
				<input type="text" name="cardNumber" value=""/><br>
				Cardholder Name: <br>
				<input type="text" name="cardholderName" value=""/><br>
				Card Security Number: <br>
				<input type="text" name="cardSecurityNumber" value=""/><br>
			<input type="submit" value="Complete Order">
			</form>
			</div>
		</div>
	
</body>
