<%@page import="org.jboss.weld.context.http.Http"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html ng-app="MyApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Home</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-cookies.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
        <script src="controllers.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


    </head>

    <style>

        #gmap {
            width: 99%;
            height: 400px;
            background-color: grey;
        }

    </style>

    <body ng-controller="ModalDemoCtrl as $ctrl" ng-init="$ctrl.open()">
        <div ng-controller="cookieCtrl">
            <ul>
                <li><a class='active' href="index.jsp">Hangman's Pizza</a></li>
                <li><a href="menu.jsp">Menu</a></li>
                <li style="float:right"><a href="logout.jsp" id='loginButton' ng-hide="!checkLoginStatus()"><span>Log Out</span></a></li>
                <li style="float:right"><a href="customerdetails.jsp" id='registerButton' ng-hide="!checkLoginStatus()"><span id='registerButton'>Account</span></a></li>
                <li style="float:right"><a href="basket.jsp"><span id='basketStatus'>Basket <img src="cart.png" alt="cart" height="42" width="42"></span></a></li>
                <li style="float:right"><a href="login.jsp" id='loginButton' ng-hide="checkLoginStatus()"><span>Log In</span></a></li>
                <li style="float:right"><a href="register.jsp" id='registerButton' ng-hide="checkLoginStatus()"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>


        <div class='page mainSection'>
            <!-- Trigger/Open The Modal -->

            <!-- The Modal -->
            <h1>Our Location</h1>
            <table id="map" style="height: 400px;">
                <tr>
                    <th style="width: 2000px; height: 400px;" id="gmap">Map to store</th>
                </tr>
            </table>	
            <table id="openingTimes" align="center" style="display: block;">
                <tr><td style="font-weight:bold">Opening Times:</td></tr>
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
                <tr>
                    <td><span>{{showCookie}}</span></td>
                </tr>
            </table>

            <div ng-controller="ModalDemoCtrl as $ctrl" class="modal-demo">
                <script type="text/ng-template" id="myModalContent.html">
                    <form>
                    <div ng-controller="cookieCtrl" ng-switch="loginStatus" ng-init="loginStatus = checkLoginStatus()">
                    <div class="modal-header" ng-switch-when=false>
                    <h3 class="modal-title" id="modal-title">Please enter your postcode.</h3>
                    </div>
                    <div class="modal-header" ng-switch-when=true ng-init="user = getLoginName()">
                    <h3 class="modal-title" id="modal-title">Welcome Back.</h3>
                    </div>
                    <div class="modal-body" id="modal-body">
                    <div ng-switch-when=false>
                    <input type="text" ng-model="Postcode"></input>
                    </div>
                    <div ng-switch-when=true ng-init="user = getLoginName()">
                    <h4>{{user}}</h4>
                    </div>
                    </div>
                    <div class="modal-footer">
                    <button class="btn btn-primary" type="button" ng-click="$ctrl.ok(request, Postcode)" ng-init="request = 'addPostcode'">OK</button>
                    <button class="btn btn-warning" type="button" ng-click="$ctrl.cancel()">Cancel</button>
                    </div>
                    </div>
                    </form>
                </script>
            </div>
        </div>
    </body>

    <script>

        function initMap() {
            var pizzaPlace = {lat: 50.374323, lng: -4.141022};
            var map = new google.maps.Map(document.getElementById('gmap'), {
                zoom: 15,
                center: pizzaPlace
            });
            var marker = new google.maps.Marker({
                position: pizzaPlace,
                map: map
            });
        }
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPVW1pGrRYmEcn_tW0xr23jDIMrxZpEUM&callback=initMap">
    </script>

</html>