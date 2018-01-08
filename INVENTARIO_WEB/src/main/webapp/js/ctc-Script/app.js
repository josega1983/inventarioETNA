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
.run(function($rootScope) {
	// DTO Types
	$rootScope.INSTALLATION_TYPE = "instalacion";
	$rootScope.ELEMENT_TYPE = "elemento";
	// Context Types
	$rootScope.TYPE_CONFIGURATION = "configuracionesTipo";
	$rootScope.FAMILY_CONFIGURATION = "configuracionFamilia";

	$rootScope.openingMenu = function(){
		var interval = setInterval(function() {
			$rootScope.resizeContent();
		}, 5);

		setTimeout(function() {
			clearInterval(interval);
		},300);
	}

	$rootScope.cloneObject = function(object) {
		var clone = {};
		if(typeof object  == 'object') {
			for(var key in object) {
				if(typeof object[key]  == 'object' && object[key] != null) {
					clone[key] = $rootScope.cloneObject(object[key]);
				} else {
					clone[key] = object[key];
				}
			}
		}
		return clone;
	}
})
.config(['$httpProvider', function($httpProvider) {
	//initialize get if not there
	if (!$httpProvider.defaults.headers.get) {
		$httpProvider.defaults.headers.get = {};    
	}

	// Answer edited to include suggestions from comments
	// because previous version of code introduced browser-related errors

	//disable IE ajax request caching
	$httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
	// extra
	$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
	$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}]);
