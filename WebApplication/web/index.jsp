<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<link rel="stylesheet" type="text/css" href="default.css" title="default">
	<meta charset="UTF-8">
	<title>Hangman's Pizza - Home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>

<body>
	<div>
		<ul>
			<li><a class="active" href="index.jsp">Home</a></li>
			<li><a href="menu.jsp">Menu</a></li>
			<li><a href="deals.jsp">Deals</a></li>
			<li><a href="basket.jsp"><span id='basketStatus'>Basket </span></a></li>
		<li style="float:right"><a id='loginButton' href="login.jsp"><span>TestLogin</span></a></li>
			<li style="float:right"><a id='registerButton' href="addressregister.jsp"><span id='registerButton'>Register</span></a></li>
		</ul>
	</div>

	<div class='page mainSection'>
		<h1>Map</h1>
			<table id="map" style="height: 300px;">
				<tr>
					<th style="width: 2000px">Map to store</th>
				</tr>
			</table>	
			<table id="openingTimes" align="center">
			<tr>
				<td style="width: 2000px">Monday</td>
				<td style="width: 2000px">17:00 - 23:00</td>
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
</html>