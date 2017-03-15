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
  
    if(isset($_GET['action']) && $_GET['action']=="add"){ 
          
        $id=intval($_GET['id']); 
              
              
            $sql_s="SELECT * FROM Product 
                WHERE ProductID={$id}"; 
            $query_s=mysql_query($sql_s); 
            if(mysql_num_rows($query_s)!=0){ 
                $row_s=mysql_fetch_array($query_s); 
                  
                $_SESSION['orderItemID'][$_SESSION['sessionOrderNumber']] = $row_s['ProductID'];
		$_SESSION['orderItemName'][$_SESSION['sessionOrderNumber']] = $row_s['ProductName'];
		$_SESSION['orderItemPrice'][$_SESSION['sessionOrderNumber']] = $row_s['Price'];

		$_SESSION['sessionOrderNumber']++;
                  
                  
            }else{ 
                  
                $message="This product id it's invalid!"; 
                  
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
	<ul>
		<li><a href="pizzahome.php">Home</a></li>
		<li><a class="active" href="pizzamenu.php">Menu</a></li>
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
		<div class='vertNav unselectable'>
			<h2>Pizza</h2>
			<h2>Sides</h2>
			<h2>Drinks</h2>
		</div>
		<h1>1</h1>

		<div class='menuSection'>
		<h1>Menu</h1>
		<?php 
        if(isset($message)){ 
            echo "<h2>$message</h2>"; 
        } 
    ?> 

			<?php
			$sql="SELECT * FROM Product ORDER BY ProductID ASC";
			$query=mysql_query($sql);
			
			while ($row=mysql_fetch_array($query)) {
			?>
			<table style="width:160px">
  				<tr>
    					<th colspan="2" style="height: 150px;"><?php echo $row['ProductName'] ?></th>
  				</tr>
  				<tr>
    					<td><a href="pizzamenu.php?action=add&id=<?php echo $row['ProductID'] ?>">Add to Basket</td>
    					<td style="background-color: white; text-align: left;">£<?php echo $row['Price'] ?></td>
  				</tr>
			<?php
			}
			?>
			</table> 
		</div>

		
		

	
</body>
</html>
