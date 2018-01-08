


angular.module('inventarioApp')
.service('elementService', function($http) {
	
	this.getElements = function() {
		return $http({
			method: 'GET',
			url: 'elements' // CTC action
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}


	this.DTOToVis = function() {
		return $http({
			method: 'GET',
			url: 'elements' // CTC action
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}
});