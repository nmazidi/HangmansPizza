<%@page import="java.util.HashMap"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.security.MessageDigest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page import="classes.CartItem"%>
<%
    //Object Cart = new Cart;
    request.getSession();
    //if(action != null) {
    // if (action == "addpizza") {
    //   int basket = (Integer) session.getAttribute("basket");
    //  basket = basket + 1;
    //   session.setAttribute("basketCount", basket);
    //  }
    //  }

    HashMap<String, String> cart;
    
    session.setAttribute("currentCart", cart);
    if (session.getAttribute("Cart") == null) {
        //session.setAttribute("Cart", ["itemName" : ""])
    }
    if (session.getAttribute("action") == "addpizza") {
            //if(session.getAttribute("username") == null) {
            // session.invalidate();
            if (session.getAttribute("basketCount") == null) {
                session.setAttribute("basketCount", 1);
            } else {
                Integer basket = (Integer) session.getAttribute("basketCount");
                session.setAttribute("basketCount", basket);
            }
            //response.sendRedirect("index.jsp");
            //return;
        }

    String text = "123456";

    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(text.getBytes());

    byte byteData[] = md.digest();

    //convert the byte to hex format method 1
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }

    System.out.println("Hex format : " + sb.toString());

    String hashedString = sb.toString();


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Menu</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular-cookies.js"></script>
    </head>

    <body>
        <div>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a class="active" href="menu.jsp">Menu</a></li>
                <li><a href="deals.jsp">Deals</a></li>
                <li><a href="basket.jsp"><span id='basketStatus'>Basket <%= session.getAttribute("basketCount") %></span></a></li>
                <li style="float:right"><a id='loginButton' href="?action=checklogin"><span>TestLogin</span></a></li>
                <li style="float:right"><a id='registerButton' href="register.jsp"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>

        <div class='page mainSection'>


            <div ng-app="myApp" ng-controller="itemsCtrl"> 
                <table>
                    <th style="text-align: center; width: 2000px;">Pizzas</th>
                </table>
                <div style="text-align: center; width: 100%;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-switch="x.ITEM_TYPE" ng-init="itemID = x.ITEM_ID">
                    <div ng-switch-when="Pizza"> <!--NO NEED FOR FORM-->
                        <form ng-submit="submit(x.ITEM_ID, menuItem)">
                            <table id="pizza">
                                <tr><td style="text-align: center; width: 2000px;">{{x.ITEM_NAME}}</td></tr>
                                <tr><td><select id="pizzaSizeSelect" ng-model="menuItem.sizeSelection[x.ITEM_ID]" ng-options="pizzaSize as pizzaSize.name for pizzaSize in pizzaSizes" ng-change="sizeChange(sizeSelection[x.ITEM_ID])">
                                        </select></td></tr>
                                <tr><td><select id="pizzaCrustSelect" ng-model="menuItem.crustSelection[x.ITEM_ID]" ng-options="pizzaCrust as pizzaCrust.name for pizzaCrust in pizzaCrusts" ng-change="crustChange(crustSelection[x.ITEM_ID])">
                                        </select></td></tr>
                                <tr><td><input type="submit" value="Add to basket"></button></td></tr>
                            </table>
                        </form>
                    </div>
                </div>
                <table>
                    <th style="text-align: center; width: 2000px;">Sides</th>
                </table>
                <div style="text-align: center; width: 100%;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-switch="x.ITEM_TYPE">
                    <div ng-switch-when="Side">
                        <form action="">
                            <table id="pizza">
                                <tr><td style="text-align: center; width: 2000px;">{{x.ITEM_NAME}}</td></tr>
                                <tr><td><select id="pizzaSizeSelect" ng-init="pizzaSizeSelector = pizzaSize[1]" ng-model="x.ITEM_ID" ng-options="pizzaSize as pizzaSize.name for pizzaSize in pizzaSizes" ng-change="sizeChange(x.ITEM_ID)">
                                        </select></td></tr>
                                <tr><td><select id="pizzaCrustSelect" ng-init="pizzaCrustSelector = pizzaCrust[1]" ng-model="x.ITEM_ID" ng-options="pizzaCrust as pizzaCrust.name for pizzaCrust in pizzaCrusts" ng-change="crustChange(pizzaCrustSelector)">
                                        </select></td></tr>
                                <tr><td><input type="submit" value="Add to basket"></td></tr>
                            </table>
                        </form>
                    </div>
                </div>
                <table>
                    <th style="text-align: center; width: 2000px;">Drinks</th>
                </table>
                <div style="text-align: center; width: 100%;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-switch="x.ITEM_TYPE">
                    <div ng-switch-when="Drink">
                        <table id="pizza">
                            <tr><td style="text-align: center; width: 2000px;">{{x.ITEM_NAME}}</td></tr>
                            <tr><td><select id="pizzaSizeSelect" ng-init="drinkSizeSelector = drinkSize[1]" ng-model="drinkSelection[x.ITEM_ID]" ng-options="drinkSize as drinkSize.name for drinkSize in drinkSizes" ng-change="sizeChange(drinkSelection[x.ITEM_ID])">
                                    </select></td></tr>
                            <tr><td><a ng-href='?action=adddrink&itemid={{x.ITEM_ID}}&drinksize={{drinkSelection[x.ITEM_ID].id}}'>Add to basket</a></td></tr>
                        </table>
                    </div>
                </div>
            </div>


        </div>


        <script>
            var app = angular.module('myApp', []);
            app.controller('itemsCtrl', function ($scope, $http) {
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
                    $scope.sizeSelection[pizzaSize] = pizzaSize.id;
                    $scope.priceMod[pizzaSize] = pizzaSize.mod;
                };

                $scope.drinkSizeChange = function (drinkSize) {
                    $scope.drinkSelection[drinkSize] = drinkSize.id;
                    $scope.priceMod = drinkSize.priceMod;
                };

                $scope.crustChange = function (pizzaCrust) {
                    $scope.crustSelection[pizzaCrust] = pizzaCrust.id;
                };
            });


        </script>
    </div>

</body>
</html>