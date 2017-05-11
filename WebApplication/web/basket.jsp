<%-- 
    Document   : newjsp
    Created on : 28-Apr-2017, 12:36:10
    Author     : Aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="MyApp">
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" title="default">
        <meta charset="UTF-8">
        <title>Hangman's Pizza - Basket</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-cookies.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
        <script src="controllers.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body ng-init="currentUser = getCurrentUserId()" ng-controller="submitCtrl" ng-model="currentUser">
        <div ng-controller="cookieCtrl">
            <ul>
                <li><a href="index.jsp">Hangman's Pizza</a></li>
                <li><a href="menu.jsp">Menu</a></li>
                <li style="float:right"><a href="logout.jsp" id='loginButton' ng-hide="!checkLoginStatus()"><span>Log Out</span></a></li>
                <li style="float:right"><a href="customerdetails.jsp" id='registerButton' ng-hide="!checkLoginStatus()"><span id='registerButton'>Account</span></a></li>
                <li class='active' style="float:right"><a href="basket.jsp"><span id='basketStatus'>Basket <img src="cart.png" alt="cart" height="42" width="42"></span></a></li>
                <li style="float:right"><a href="login.jsp" id='loginButton' ng-hide="checkLoginStatus()"><span>Log In</span></a></li>
                <li style="float:right"><a href="register.jsp" id='registerButton' ng-hide="checkLoginStatus()"><span id='registerButton'>Register</span></a></li>
            </ul>
        </div>

        <div class='page menuSection' ng-controller="orderCtrl">
            <h1>Basket</h1>

            <div style="text-align: center; width: 100%;" ng-repeat="x in orders">
                <table id="itemBasket" style="text-align: center; table-align: center; margin: auto; width: 100%;" ng-model="orderRequest">
                    <tr>
                        <th>Product Name</th><th>Item Price</th>
                    </tr>

                    <tr>
                        <td>{{x.ITEM_ID}}</td><td>{{x.UNIT_PRICE| currency : "£"}}</td><td><button class="btn" ng-click="deleteBasketItem(x.ITEM_ID)" ng-controller="submitCtrl" value="Delete Item">Delete Item</button></td>
                    </tr>
                    <tr>
                        <td></br></td>
                    </tr>
                </table>
            </div>
            <span>Sub total: </span>
            <button ng-click="submitOrderData()" ng-controller="submitCtrl" value="Send Order">Send Order</button>
        </div>


    </body>
    <script>
        var MyApp = angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
        MyApp.controller('orderCtrl', function ($scope, $http) {
            $scope.basketCount = 0;
            $http.get("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/order_item")
                    .then(function (response) {
                        $scope.orders = response.data;
                    });

            $http.get("http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/items")
                    .then(function (response) {
                        $scope.items = response.data;
                    });
                    
            $scope.submitOrder = function() {
                
            };

        });
        
        MyApp.controller('submitCtrl', [
            '$scope', '$window', '$http', '$httpParamSerializer',
            function ($scope, $window, $http, $httpParamSerializer) {
                
                $scope.submitOrderData = function() {
                    $scope.httpPost();
                };
                
                $scope.httpPost = function() {
                    $scope.data = {ORDER_ID: 82, CUSTOMER_ID: 183, DATE_PLACED: new Date(), ORDER_TYPE: "Delivery", NOTES: "n/a", ORDER_STATUS: "PLACED"};
                    $http({
                        method: 'POST',
                        url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/orders',
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
                                } else {
                                    alert('Unexpected server error. ');
                                }
                            });
                };
                
                $scope.deleteBasketItem = function(itemID) {
                    $http({
                        method: 'DELETE',
                        url: 'http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/order_item/' + itemID,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded',
                            'Accept': 'application/json'}
                    }).
                            success(function (data, status, headers, config) {
                                alert('Item deleted successfully');
                            }).
                            error(function (data, status, headers, config) {
                                console.log($scope.data);
                                if (status === status) {
                                    $scope.messages = data;
                                } else {
                                    alert('Unexpected server error. ');
                                }
                            });
                };
            }]);
        
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
</html>