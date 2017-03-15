<?php
session_start();
if ($_SESSION["username"] != NULL){
	echo "Hi, " . $_SESSION["username"];
}
else {
	echo "Register";
}
?>
