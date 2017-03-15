<?php
include "pizzacommon.php";
$baseurl = "http://71.19.147.8";

$conn = new mysqli($host, $username, $password, $dbname);
if ($conn->connect_errno) {
        die('Could not connect: ' . $conn->connect_error);
}

if (empty($_POST["username"]) || empty($_POST["password"])) {
	echo "Empty required field";
	echo "<br><a href=$baseurl/pizzalogin.html>Go back</a>";
	die();
}

$username = mysqli_real_escape_string($conn, $_POST["username"]);
$password = hash('sha256', $_POST["password"]);

$query = "SELECT * FROM Customer WHERE Username = '$username'";
$result = $conn->query($query);
if (mysqli_num_rows($result) == 1) {
	session_start();
	$_SESSION["username"] = $username;
	header("location: pizzahome.php");
}
else {
	echo "Invalid username/password combination";
	echo "<br><a href=$baseurl/pizzalogin.html>Go back</a>";
	die();
}
?>
