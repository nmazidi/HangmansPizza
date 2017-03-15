<?php
$DBhost = "untz";
$DBusername = "root";
$DBpassword = "Aa030193";
$DBname = "Pizzadb";
$baseurl = "http://71.19.147.8";

mysql_connect($DBhost, $DBusername, $DBpassword) or die("Could not connect");

mysql_select_db($DBname) or die("Could not connect");
?>
