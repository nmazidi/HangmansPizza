angular.module('MyApp', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies']);
angular.module('MyApp').controller('ModalDemoCtrl', function ($uibModal, $log, $document) {
    var $ctrl = this;
    $ctrl.items = ['item1', 'item2', 'item3'];

    $ctrl.animationsEnabled = true;

    $ctrl.open = function (size, parentSelector) {
        var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                items: function () {
                    return $ctrl.items;
                }
            }
        });


        modalInstance.result.then(function (selectedItem) {
            $ctrl.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };

    $ctrl.openComponentModal = function () {
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            component: 'modalComponent',
            resolve: {
                items: function () {
                    return $ctrl.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $ctrl.selected = selectedItem;
        }, function () {
            $log.info('modal-component dismissed at: ' + new Date());
        });
    };

    $ctrl.openMultipleModals = function () {
        $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title-bottom',
            ariaDescribedBy: 'modal-body-bottom',
            templateUrl: 'stackedModal.html',
            size: 'sm',
            controller: function ($scope) {
                $scope.name = 'bottom';
            }
        });

        $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'stackedModal.html',
            size: 'sm',
            controller: function ($scope) {
                $scope.name = 'top';
            }
        });
    };

    $ctrl.toggleAnimation = function () {
        $ctrl.animationsEnabled = !$ctrl.animationsEnabled;
    };
});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('MyApp').controller('ModalInstanceCtrl', function ($cookieStore, $window, $uibModalInstance, items) {
    var $ctrl = this;
    $ctrl.items = items;
    $ctrl.selected = {
        item: $ctrl.items[0]
    };

    $ctrl.ok = function (request, data) {
        if (request === 'addPostcode') {
            $uibModalInstance.close($ctrl.selected.item);
        } else {
            $uibModalInstance.close($ctrl.selected.item);
        }

    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

// Please note that the close and dismiss bindings are from $uibModalInstance.


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