'use strict';

/**
 * @ngdoc object
 * @name blinkUrbanApp
 * @requires $routeProvider
 * @requires blinkUrbanControllers
 * @requires ui.bootstrap
 *
 * @description
 * Root app, which routes and specifies the partial html and controller depending on the url requested.
 *
 */
var app = angular.module('blinkUrbanApp',
    ['blinkUrbanControllers', 'ngRoute', 'ui.bootstrap']).
    config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
                when('/login', {
                    templateUrl: '/html/login.html',
                    controller: 'LoginCtrl'
                }).
                when('/', {
                    templateUrl: '/html/home.html'
                }).
                otherwise({
                    redirectTo: '/'
                });
        }]);

