'use strict';

/**
 * The root blinkUrbanApp module.
 *
 * @type {conferenceApp|*|{}}
 */
var blinkUrbanApp = blinkUrbanApp || {};

/**
 * @ngdoc module
 * @name blinkUrbanControllers
 *
 * @description
 * Angular module for controllers.
 *
 */
blinkUrbanApp.controllers = angular.module('blinkUrbanControllers', ['ui.bootstrap']);


/**
 * @ngdoc controller
 * @name RootCtrl
 *
 * @description
 * The root controller having a scope of the body element and methods used in the application wide
 * such as user authentications.
 *
 */
blinkUrbanApp.controllers.controller('RootCtrl', function ($scope, $location) {
    /**
     * Returns if the viewLocation is the currently viewed page.
     *
     * @param viewLocation
     * @returns {boolean} true if viewLocation is the currently viewed page. Returns false otherwise.
     */
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };

    /**
     * Collapses the navbar on mobile devices.
     */
    $scope.collapseNavbar = function () {
        angular.element(document.querySelector('.navbar-collapse')).removeClass('in');
    };
    
    $scope.getSignedInState = function () {
        
    };

});

blinkUrbanApp.controllers.controller('LoginCtrl', function($scope, $rootScope){
	
	$scope.init = function(){
		$scope.loading = false;
	};
	
	$scope.login = function(user){
		$scope.loading = true;
		gapi.client.blinkurban.login(user).execute(function (resp) {
			$scope.loading = false;
			if (resp.error) {
                // The request has failed.
				var errorMessage = resp.error.message || '';
				$scope.messages = 'Failed to login: ' + errorMessage;
				$scope.alertStatus = 'danger';
			}else{
				// The request has succeeded.
			}
        });
	};
	
});

blinkUrbanApp.controllers.controller('RegistrationCtrl', function($scope, $rootScope){
	
	$scope.init = function(){
		$scope.loading = false;
		$scope.account = {};
		$scope.genders = ["Male", "Female", "None"];
	};
	
	$scope.createUser = function(account){
		$scope.loading = true;
		gapi.client.blinkurban.createUser(account).execute(function (resp) {
			$scope.loading = false;
			if (resp.error) {
                // The request has failed.
				var errorMessage = resp.error.message || '';
				$scope.messages = 'Failed to create an account: ' + errorMessage;
				$scope.alertStatus = 'danger';
			}else{
				// The request has succeeded.
			}
        });
	};
	
});
