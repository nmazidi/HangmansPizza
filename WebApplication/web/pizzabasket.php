

<?php
	if(isset($_GET['action']) && $_GET['action']=="checkbasket"){
		if($_SESSION["sessionOrderNumber"] == NULL) {
			$errormessage = "Basket is empty";
			header("location: pizzabasket.php");
		} else {
			header("location: pizzacheckout.php");
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
	if(isset($_GET['action']) && $_GET['action']=="checklogin"){
		if($_SESSION["username"] != NULL) {
			session_unset();

			session_destroy();

			header("location: pizzahome.php");
		} else {
			header("location: pizzalogin.php");
		}
	}
?>

<?php 
  
    if(isset($_GET['action']) && $_GET['action']=="removeitem"){ 
          
        $indextodelete=intval($_GET['basketindex']); 
              for($x = $indextodelete; $x < $_SESSION['sessionOrderNumber'] - 1; $x++) {
			$_SESSION['orderItemName'][$x] = $_SESSION['orderItemName'][$x + 1];
			$_SESSION['orderItemPrice'][$x] = $_SESSION['orderItemPrice'][$x + 1];
		}
          $_SESSION['sessionOrderNumber'] = $_SESSION['sessionOrderNumber'] - 1;
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
		<li><a class="active" href="pizzabasket.php"><span id='basketStatus'>Basket <?php echo $_SESSION['sessionOrderNumber']?></span></a></li>
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


		<div class='page menuSection'>
			<h1>Basket</h1>
			<span><?php if(isset($errormessage)) echo $errormessage ?> </span>

			<table id="itemBasket" style="text-align: center; table-align: center; margin: auto; width: 100%;">
				<tr>
					<th>Product Name</th><th>Item Price     </th>
				</tr>
<?php
$subTotal = 0.00;
for($x = 0; $x < $_SESSION['sessionOrderNumber']; $x++) {
$_SESSION['cart'][$x]=array($_SESSION['orderItemID'][$x], $_SESSION['orderItemName'][$x], $_SESSION['orderItemPrice'][$x]);
?>
				<tr>
					<td><?php echo $_SESSION['orderItemName'][$x] ?></td><td>£<?php echo $_SESSION['orderItemPrice'][$x] ?></td><td><a href="pizzabasket.php?action=removeitem&basketindex=<?php echo $x ?>">Remove From Basket</a></td>
				</tr>
<?php 
$subTotal = $subTotal + $_SESSION['orderItemPrice'][$x];
	}
?>
				<tr>
					<td></br></td>
				</tr>
				<tr>
					<td>Sub total:</td><td>£<?php echo $subTotal ?></td>
				</tr>
			</table>
		<span><a href="pizzabasket.php?action=checkbasket">Proceed to checkout</a></span>
		</div>

		
		

	
</body>
</html>
