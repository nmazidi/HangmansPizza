<?php
session_start();
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
			<li><a class="active" href="pizzahome.php">Home</a></li>
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
		<h1>Map</h1>
			<table id="map" style="width: 100%; height: 300px;">
				<tr>
					<th>Map to store</th>
				</tr>
			</table>	
			<table id="openingTimes" align="center" style="width: 100%:">
			<tr>
				<td>Monday</td>
				<td>17:00 - 23:00</td>
			</tr>
			<tr>
				<td>Tuesday</td>
				<td>17:00 - 23:00</td>
			</tr>
			<tr>
				<td>Wednesday</td>
				<td>17:00 - 23:00</td>
			</tr>
			<tr>
				<td>Thursday</td>
				<td>17:00 - 23:00</td>
			</tr>
			<tr>
				<td>Friday</td>
				<td>17:00 - 23:00</td>
			</tr>
			<tr>
				<td>Saturday</td>
				<td>17:00 - 23:00</td>
			</tr>
			</table>
	</div>
	
</body>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script src="jquery.min.js" type="text/javascript"></script>

