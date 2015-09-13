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
		[ 'blinkUrbanControllers', 'ngRoute', 'ui.bootstrap' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/account/login', {
				templateUrl : '/html/login.html',
				controller : 'LoginCtrl'
			}).when('/account/registration', {
				templateUrl : '/html/registration.html',
				controller : 'RegistrationCtrl'
			}).when('/', {
				templateUrl : '/html/home.html'
			}).otherwise({
				redirectTo : '/'
			});
		} ]);
/*
 * app.factory('AuthService', function ($http, Session) { var authService = {};
 * 
 * authService.login = function (credentials) { return $http .post('/login',
 * credentials) .then(function (res) { Session.create(res.data.id,
 * res.data.user.id, res.data.user.role); return res.data.user; }); };
 * 
 * authService.isAuthenticated = function () { return !!Session.userId; };
 * 
 * authService.isAuthorized = function (authorizedRoles) { if
 * (!angular.isArray(authorizedRoles)) { authorizedRoles = [authorizedRoles]; }
 * return (authService.isAuthenticated() &&
 * authorizedRoles.indexOf(Session.userRole) !== -1); };
 * 
 * return authService; });
 * 
 * app.service('Session', function () { this.create = function (sessionId,
 * userId, userRole) { this.id = sessionId; this.userId = userId; this.userRole =
 * userRole; }; this.destroy = function () { this.id = null; this.userId = null;
 * this.userRole = null; }; });
 * 
 * app.constant('AUTH_EVENTS', { loginSuccess: 'auth-login-success',
 * loginFailed: 'auth-login-failed', logoutSuccess: 'auth-logout-success',
 * sessionTimeout: 'auth-session-timeout', notAuthenticated:
 * 'auth-not-authenticated', notAuthorized: 'auth-not-authorized' });
 * 
 * app.constant('USER_ROLES', { all: '*', admin: 'admin', editor: 'editor',
 * guest: 'guest' });
 */