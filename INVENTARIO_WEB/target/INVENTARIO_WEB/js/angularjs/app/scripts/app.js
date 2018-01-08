'use strict';

/**
 * @ngdoc overview
 * @name inventarioApp
 * @description
 * # inventarioApp
 *
 * Main module of the application.
 */
angular
  .module('inventarioApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
