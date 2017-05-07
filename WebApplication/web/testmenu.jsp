<%-- 
    Document   : testmenu
    Created on : 03-May-2017, 17:48:42
    Author     : amalzard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Menu</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

        <script>
            var app = angular.module('myApp', []);
            app.controller('itemsCtrl', function ($scope, $http) {
                $http.get("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/items")
                        .then(function (response) {
                            $scope.items = response.data;
                            $scope.itemPrice = [];

                        });
                $scope.sizeSelection = 0;
                $scope.crustSelection = 0;
                $scope.priceMod = 0;
                $scope.pizzaSizes = [{id: 1, name: "Small", mod: -2}, {id: 2, name: "Medium", mod: 0}, {id: 3, name: "Large", mod: 2}];
                $scope.pizzaCrusts = [{id: 1, name: "Classic"}, {id: 2, name: "Cheese"}, {id: 3, name: "BBQ"}];
                $scope.sizeChange = function (pizzaSize) {
                    $scope.sizeSelection = pizzaSize.id;
                    $scope.priceMod = pizzaSize.mod;
                };

                $scope.crustChange = function (pizzaCrust) {
                    $scope.crustSelection = pizzaCrust.id;
                };
            });

        </script>
    </head>
    <body>
        <h1>Test</h1>
        <div ng-app="menuItem" ng-controller="itemsCtrl">
            <table style="width:160px; text-align: center;" ng-repeat="x in items" ng-model="x.ITEM_TYPE" ng-init="">
                <tr>
                    <th>Pizza</th><th>Type</th><th>Price</th>
                </tr>
                <tr>
                    <td>{{x.ITEM_NAME}}</td><td>{{x.ITEM_TYPE}}</td><td>{{x.SELLING_PRICE| currency : "£"}}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
<div id='menuItem' ng-init='itemSize = ""; itemQty = ""'>
                    <table style="width:160px; text-align: center;" ng-repeat="x in items" ng-model="x.ITEM_TYPE">
                        <tr>
                            <th style="height: 150px; text-align: center;"> {{ x.ITEM_NAME}} </th>
                        </tr>
                        <tr>
                            <td>
                                <div ng-switch="x.ITEM_TYPE">
                                    <div ng-switch-when="Pizza">
                                        <select id="pizzaSizeSelect" style="width: 100%" ng-init="pizzaSizeSelector = pizzaSize[1]" ng-model="pizzaSizeSelector" ng-options="pizzaSize as pizzaSize.name for pizzaSize in pizzaSizes" ng-change="sizeChange(pizzaSizeSelector)">
                                        </select>
                                    </div>
                                    <div ng-switch-when="Side">
                                        <select name="sideQuantity" style="width: 100%" ng-model="sideQuantity">
                                            <option value="1">1 (£{{x.SELLING_PRICE * 1}})</option>
                                            <option value="2">2 (£{{x.SELLING_PRICE * 2}})</option>
                                            <option value="3">3 (£{{x.SELLING_PRICE * 3}})</option>
                                        </select>
                                    </div>
                                    <div ng-switch-when="Drink">
                                        <select name="drinkQuantity" style="width: 100%" ng-model="drinkQuantity">
                                            <option value="1">1 (£{{x.SELLING_PRICE * 1}})</option>
                                            <option value="2">2 (£{{x.SELLING_PRICE * 2}})</option>
                                            <option value="3">3 (£{{x.SELLING_PRICE * 3}})</option>
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div ng-switch="x.ITEM_TYPE">
                                    <div ng-switch-when="Pizza">
                                        <select id="pizzaCrustSelect" style="width: 100%" ng-init="pizzaCrustSelector = pizzaCrust[1]" ng-model="pizzaCrustSelector" ng-options="pizzaCrust as pizzaCrust.name for pizzaCrust in pizzaCrusts" ng-change="crustChange(pizzaCrustSelector)">
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><a ng-href="menu.jsp#/?action=add&id={{ x.ITEM_ID }}&pizzasize={{sizeSelection}}&pizzacrust={{crustSelection}}">Add to Basket</td>
                            <td>{{itemPrice[x]}}</td>
                        </tr>
                    </table>
                </div>
