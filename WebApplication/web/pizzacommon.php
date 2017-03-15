<?php
$host = "untz";
$username = "root";
$password = "Aa030193";
$dbname = "Pizzadb";
$usertablename = "Customer";
$baseurl = "http://71.19.147.8";

function genkey() {
	return bin2hex(openssl_random_pseudo_bytes(10));
}
?>
