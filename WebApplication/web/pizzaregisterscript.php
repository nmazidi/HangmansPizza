<?php
include "pizzacommon.php";

$conn = new mysqli($host, $username, $password, $dbname);
if ($conn->connect_errno) {
	die('Could not connect: ' . $conn->connect_error);
}
if (empty($_POST["username"]) || empty($_POST["email"]) || empty($_POST["password"]) || empty($_POST["passwordVarification"]) || empty($_POST["addressLine1"]) || empty($_POST["city"]) || empty($_POST["postcode"])) {
	echo "Empty required field";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}
$firstName = mysqli_real_escape_string($conn, $_POST["firstName"]);
$lastName = mysqli_real_escape_string($conn, $_POST["lastName"]);
$username = mysqli_real_escape_string($conn, $_POST["username"]);
$email = mysqli_real_escape_string($conn, $_POST["email"]);
$password = mysqli_real_escape_string($conn, $_POST["password"]);
$passwordVarification = mysqli_real_escape_string($conn, $_POST["passwordVarification"]);
$passwordCheckSuccessful = hash('sha256', $_POST["password"]);
$addressLine1 = mysqli_real_escape_string($conn, $_POST["addressLine1"]);
$city = mysqli_real_escape_string($conn, $_POST["city"]);
$postcode = mysqli_real_escape_string($conn, $_POST["postcode"]);

if ($password != $passwordVarification) {
	echo "Password fields not matching.";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}
if (strlen($username) > 20) {
	echo "Username too long";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}

if (strlen($email) > 255) {
	echo "Email too long";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}

if (strlen($addressline1) > 255) {
	echo "Address Line 1 too long";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}

if (strlen($addressline2) > 255) {
	echo "Address Line 2 too long";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}

if (strlen($postcode) > 10) {
	echo "Postcode too long";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}
$namequery = "SELECT Username FROM Customer WHERE Username = '$username'";
$emailquery = "SELECT Email FROM Customer WHERE Email = '$email'";
$nameresult = $conn->query($namequery);
$emailresult = $conn->query($emailquery);
if (mysqli_num_rows($nameresult) >= 1) {
	echo "Duplicate username";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}
if (mysqli_num_rows($emailresult) >=1) {
	echo "Duplicate email";
	echo "<br><a href=$baseurl/pizzaregister.php>Go back</a>";
	die();
}
$query = "INSERT INTO Customer (Username, Email, Password, addressLine1, City, Postcode, FirstName, LastName) VALUES ('$username', '$email', '$passwordCheckSuccessful', '$addressLine1', '$city', '$postcode', '$firstName', '$lastName')";
$conn->query($query);
echo "<br>Thank you for registering";
echo "<br><a href=$baseurl/pizzahome.php>Go back to homepage</a>";
die();
