angular.module('MyApp',  ['ui.bootstrap']);
// add manager Modal
var addManagerModal = function($scope, $modal) {
	$scope.open = function () {
	  var modalInstance = $modal.open({
      templateUrl: 'addManager.html',
      controller: ModalInstanceCtrl});
};
	


};

var ModalInstanceCtrl = function ($scope, $modalInstance) {

  $scope.ok = function () {
    $modalInstance.close();
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};


//add Captain modal

var addCaptainModal = function($scope, $modal) {
	$scope.open = function () {
	  var captainModalInstance = $modal.open({
      templateUrl: 'addCaptain.html',
      controller: captainModalInstanceCtrl});
};
	


};

var captainModalInstanceCtrl = function ($scope, $modalInstance) {

  $scope.ok = function () {
    $modalInstance.close();
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};
