<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="MyApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Menu</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-cookies.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
        <script src="controllers.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body ng-controller="itemsCtrl">
        <div ng-controller="cookieCtrl">
            <ul>
                <li><a href="index.jsp">Hangman's Pizza</a></li>
                <li><a class='active' href="menu.jsp">Menu</a></li>
                <li style="float:right"><a href="logout.jsp" id='loginButton' ng-hide="!checkLoginStatus()"><span>Log Out</span></a></li>
                <li style="float:right"><a href="customerdetails.jsp" id='registerButton' ng-hide="!checkLoginStatus()"><span id='registerButton'>Account</span></a></li>
                <li style="float:right"><a href="basket.jsp"><span id='basketStatus'>Basket <img src="cart.png" alt="cart" height="42" width="42"></span></a></li>
                <li style="float:right"><a href="login.jsp" id='loginButton' ng-hide="checkLoginStatus()"><span>Log In</span></a></li>
                <li style="float:right"><a href="register.jsp" id='registerButton' ng-hide="checkLoginStatus()"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>

        <div class='page mainSection'>
 


            <div ng-controller="itemsCtrl"> 
                <table>
                    <th style="text-align: center; width: 2000px;">Pizzas</th>
                </table>
                <div style="text-align: center; width: 100%;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-switch="x.ITEM_TYPE" ng-init="itemID = x.ITEM_ID">
                    <div ng-switch-when="Pizza"> <!--NO NEED FOR FORM-->
                        <table id="pizza">
                            <tr><td style="text-align: center; width: 2000px;">{{x.ITEM_NAME}}</td></tr>
                            <tr><td><select id="pizzaSizeSelect" ng-model="sizeSelection[x.ITEM_ID]" ng-options="pizzaSize as pizzaSize.name for pizzaSize in pizzaSizes" ng-change="sizeChange(sizeSelection[x.ITEM_ID])">
                                    </select></td></tr>
                            <tr><td><select id="pizzaCrustSelect" ng-model="crustSelection[x.ITEM_ID]" ng-options="pizzaCrust as pizzaCrust.name for pizzaCrust in pizzaCrusts" ng-change="crustChange(crustSelection[x.ITEM_ID])">
                                    </select></td></tr>
                            <tr><td><input class="btn" type="button" ng-controller="itemsCtrl" ng-click="submitPizza(sizeSelection[x.ITEM_ID], crustSelection[x.ITEM_ID], x.ITEM_ID, x.SELLING_PRICE)" value="ADD TO BASKET"></td></td></tr>
                            <tr><td></td></tr>
                        </table>
                    </div>
                </div>
                <table>
                    <th style="text-align: center; width: 2000px;">Sides</th>
                </table>
                <div style="text-align: center; width: 100%;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-switch="x.ITEM_TYPE">
                    <div ng-switch-when="Side">
                        <table id="pizza">
                            <tr><td style="text-align: center; width: 2000px;">{{x.ITEM_NAME}}</td></tr>
                            <tr><td><select id="pizzaSizeSelect" ng-init="pizzaSizeSelector = pizzaSize[1]" ng-model="x.ITEM_ID" ng-options="pizzaSize as pizzaSize.name for pizzaSize in pizzaSizes" ng-change="sizeChange(x.ITEM_ID)">
                                    </select></td></tr>
                            <tr><td><select id="pizzaCrustSelect" ng-init="pizzaCrustSelector = pizzaCrust[1]" ng-model="x.ITEM_ID" ng-options="pizzaCrust as pizzaCrust.name for pizzaCrust in pizzaCrusts" ng-change="crustChange(pizzaCrustSelector)">
                                    </select></td></tr>
                            <tr><td><input class="btn" type="button" ng-controller="itemsCtrl" ng-click="submitPizza(sizeSelection[x.ITEM_ID], crustSelection[x.ITEM_ID], x.ITEM_ID, x.SELLING_PRICE)" value="ADD TO BASKET"></td></tr>
                        </table>
                    </div>
                </div>
                <table>
                    <th style="text-align: center; width: 2000px;">Drinks</th>
                </table>
                <div style="text-align: center; width: 100%;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-switch="x.ITEM_TYPE">
                    <div ng-switch-when="Drink">
                        <table id="pizza">
                            <tr><td style="text-align: center; width: 2000px;">{{x.ITEM_NAME}}</td></tr>
                            <tr><td><select id="drinkSizeSelect" ng-init="drinkSizeSelector = drinkSize[1]" ng-model="drinkSelection[x.ITEM_ID]" ng-options="drinkSize as drinkSize.name for drinkSize in drinkSizes" ng-change="sizeChange(drinkSelection[x.ITEM_ID])">
                                    </select></td></tr>
                            <tr><td><input class="btn" type="button" ng-controller="itemsCtrl" ng-click="submitDrink(drinkSelection[x.ITEM_ID], x.ITEM_ID, x.SELLING_PRICE)" value="ADD TO BASKET"></td></tr>
                        </table>
                    </div>
                </div>
            </div>


        </div>


        <script>
            var app = angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
            app.controller('itemsCtrl', function ($window, $scope, $http, $httpParamSerializer) {
                $http.get("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/items")
                        .then(function (response) {
                            $scope.items = response.data;
                            $scope.itemPrice = [];
                        });
                $scope.sizeSelection = [];
                $scope.crustSelection = [];
                $scope.drinkSelection = [];
                $scope.priceMod = [];
                $scope.pizzaSizes = [{id: 1, name: "Small", mod: 0.8}, {id: 2, name: "Medium", mod: 1}, {id: 3, name: "Large", mod: 1.2}];
                $scope.pizzaCrusts = [{id: 1, name: "Classic"}, {id: 2, name: "Cheese"}, {id: 3, name: "BBQ"}];
                $scope.drinkSizes = [{id: 1, name: "330ml", priceMod: 0.5}, {id: 2, name: "0.5L", priceMod: 1}, {id: 3, name: "1L", priceMod: 2}];

                $scope.sizeChange = function (pizzaSize) {
                    $scope.sizeSelection[pizzaSize] = pizzaSize.name;
                    $scope.priceMod[pizzaSize] = pizzaSize.mod;
                };

                $scope.drinkSizeChange = function (drinkSize) {
                    $scope.drinkSelection[drinkSize] = drinkSize.name;
                    $scope.priceMod = drinkSize.priceMod;
                };

                $scope.crustChange = function (pizzaCrust) {
                    $scope.crustSelection[pizzaCrust] = pizzaCrust.id;
                };

                $scope.addToBasket = function (basketCount) {
                    basketCount++;
                };


                $scope.httpPost = function (data, qty) {
                    $scope.data = data;
                    $http({
                        method: 'POST',
                        url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/order_item',
                        data: $httpParamSerializer($scope.data),
                        //data: $scope.data.customer,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded',
                            'Accept': 'application/json'}
                    }).
                            success(function (data, status, headers, config) {
                                //$window.location.replace('index.jsp');
                            }).
                            error(function (data, status, headers, config) {
                                console.log($scope.data);
                                if (status === status) {
                                    $scope.messages = data;
                                } else if (status === 409) {
                                    $window.alert("409");
                                    qty++;
                                    $scope.data = {ITEM_ID: itemId, ORDER_ID: 82, QUANTITY: qty, UNIT_PRICE: itemPrice, ITEM_SIZE: "Medium", ITEM_CRUSTTYPE: "BBQ"};
                                    $scope.httpPost(data, qty);
                                } else {
                                    alert('Unexpected server error. ');
                                }
                            });
                };

                $scope.submitPizza = function (pizzaSize, pizzaCrust, itemId, itemPrice) {
                    $scope.data = {ITEM_ID: itemId, ORDER_ID: 82, QUANTITY: 1, UNIT_PRICE: itemPrice, ITEM_SIZE: "Medium", ITEM_CRUSTTYPE: "BBQ"};
                    //$checkBasketIndex(basketIndex);
                    $scope.httpPost($scope.data);

                };

                $scope.submitDrink = function (drinkSize, itemId, itemPrice) {
                    var qty = 1;
                    var basketIndex;
                    $scope.data = {};
                    $scope.data = {ITEM_ID: itemId, ORDER_ID: 82, QUANTITY: 1, UNIT_PRICE: itemPrice, ITEM_SIZE: "1L", ITEM_CRUSTTYPE: "n/a"};
                    //$checkBasketIndex(basketIndex);
                    $scope.httpPost($scope.data, qty);

                };

            });

            angular.module('MyApp').controller('cookieCtrl', function ($scope, $window, $cookieStore) {

                $scope.postcodeEntered = function (data) {
                    $cookieStore.put('postcode', data);
                    $window.alert($cookieStore.get('postcode'));
                };

                $scope.getLoginStatus = function () {
                    return $cookieStore.get('loginStatus');
                };

                $scope.getLoginName = function () {
                    return $cookieStore.get('customerName');
                };

                $scope.checkLoginStatus = function () {
                    var checkResult;
                    var cookieToCheck = $cookieStore.get('loginStatus');
                    if (cookieToCheck === "loggedIn") {
                        checkResult = true;
                        return checkResult;
                    } else {
                        checkResult = false;
                        return checkResult;
                    }
                };

                $scope.getCurrentUsername = function () {
                    var checkResult;
                    var cookieToCheck = $cookieStore.get('loginStatus');
                    if (cookieToCheck === "loggedIn") {
                        return $cookieStore.get('username');
                    } else {
                        checkResult = false;
                        return 'Register';
                    }
                };

                $scope.getCurrentUserId = function () {
                    var checkResult;
                    var cookieToCheck = $cookieStore.get('loginStatus');
                    if (cookieToCheck === "loggedIn") {
                        return $cookieStore.get('userId');
                    }
                };

                $scope.loginOutSwitch = function () {
                    var loginStatus = checkLoginStatus();
                    if (loginStatus === true) {
                        return 'Log Out';
                    } else {
                        return 'Log Out';
                    }
                };

                $scope.checkBasketIndex = function (basketIndex) {
                    if ($cookieStore.get('basketIndex') === null) {
                        basketIndex = 0;
                        $cookieStore.put('basketIndex', 1);
                    } else {
                        basketIndex = basketIndex++;
                        $cookieStore.put('basketIndex', basketIndex);
                    }
                    return basketIndex;
                };

                $scope.logout = function () {
                    angular.forEach($cookieStore, function (v, k) {
                        $cookieStore.remove(k);
                    });
                };



            });



        </script>

    </body>
</html>