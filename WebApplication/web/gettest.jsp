<%-- 
    Document   : menu
    Created on : 28-Apr-2017, 13:37:26
    Author     : Aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        


<div ng-app="myApp" ng-controller="customersCtrl">

<ul>
  <li ng-repeat="x in myData">
    {{ x.CUSTOMER_FORENAME + ', ' + x.CUSTOMER_SURNAME }}
  </li>
</ul>

</div>

<script>
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $http({
        method: 'GET', 
        dataType: 'jsonp',
        url:"http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/customers"
    }).then(function(response)
    {
        $scope.myData = response.data;
    });
});
</script>




    </body>
</html>
