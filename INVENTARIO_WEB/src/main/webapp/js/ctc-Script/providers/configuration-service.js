


angular.module('inventarioApp')
.service('configurationService', function($http) {

	/**
	* 
	*/
	this.getInitialContextStructures = function() {
		return $http({
			method: 'GET',
			url: 'obtenerModelForAngular'
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	* GET petition, recives a list of <FamiliaInstalacionDTO> as a JSON.
	*/
	this.getInstallation = function(params, actualPage) {
		var data = { 
			'resultJSON': true,
			'filtroInstalacion.asociadaConfiguracion': true,
			'filtroInstalacion.activo': "SI",
			// 'filtroInstalacion.ordenarAlfabeticamenteNombre': true,
			'actualPage': actualPage
		};
		if(params != null) {
			for(var key in params) {
				data['filtroInstalacion.'+key] = params[key];
			}
		}
		return $http({
			method: 'GET',
			url: 'obtenerInstalacion', // ContextAction
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	* GET petition, recives a list of <FamiliaElementDTO> as a JSON.
	*/
	this.getElements = function(params, actualPage) {
		var data = { 
			'resultJSON': true,
			'filtro.activo': "SI",
			'filtro.ordenarAlfabeticamenteNombre': true
		};
		if(params != null) {
			for(var key in params) {
				data['filtro.'+key] = params[key];
			}
		}
		return $http({
			method: 'GET',
			url: '../familiaElemento/search', // FamiliaElementoAction
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}


	/**
	* GET petition, recives a list of <FamiliaElementDTO> as a JSON.
	*/
	this.getMoreElements = function(actualPage) {
		var data = { 
			'resultJSON': true,
			'actualPage': actualPage
		};
		return $http({
			method: 'GET',
			url: '../familiaElemento/moreResults', // FamiliaElementoAction
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	* PUT petition, creates or edits the tree for the given context (CTC, CFC)
	*/
	this.putTreeConfigurationDTO = function(parameter) {
		var base64 = btoa(JSON.stringify(parameter));
		return $http({
			method: 'POST',
			url: 'saveOrUpdate',
			params: { base64JSON: base64 }
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	* PUT petition, creates or edits the image for the given context (CTC, CFC)
	*/
	this.putTreeConfigurationImage = function(parameter) {
		return $http({
			method: 'POST',
			url: 'saveOrUpdateImage',
			params: parameter
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	* GET petition, gets an existing configuration for editing
	*/
	this.getConfigurationByID = function(configurationID, context) {
		return $http({
			method: 'GET',
			url: '../'+context+'/obtenerConfigurationByID',
			params: { 'elemento.id': configurationID }
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	*
	*/
	this.getConfigurationFamily = function(params, actualPage) {
		var data = { 
			'resultJSON': true,
			'filtro.activo': "SI"
		};
		if(params != null) {
			for(var key in params) {
				data['filtro.'+key] = params[key];
			}
		}
		return $http({
			method: 'GET',
			url: '../configuracionFamilia/search', // ConfiguracionFamilia
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	*
	*/
	this.getMoreConfigurationFamily = function(actualPage) {
		var data = { 
			'resultJSON': true,
			'actualPage': actualPage
		};
		return $http({
			method: 'GET',
			url: '../configuracionFamilia/moreResults', // configuracionFamilia
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	*
	*/
	this.getConfigurationType = function(params, actualPage) {
		var data = { 
			'resultJSON': true,
			'filtro.activo': "SI"
		};
		if(params != null) {
			for(var key in params) {
				data['filtro.'+key] = params[key];
			}
		}
		return $http({
			method: 'GET',
			url: '../configuracionesTipo/search', // ConfiguracionFamilia
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}

	/**
	*
	*/
	this.getMoreConfigurationType = function(actualPage) {
		var data = { 
			'resultJSON': true,
			'actualPage': actualPage
		};
		return $http({
			method: 'GET',
			url: '../configuracionesTipo/moreResults', // configuracionFamilia
			params: data
		}).then(function successCallback(response) {
			response.error = false;
			return response;
		}, function errorCallback(response) {
			response.error = true;
			return response;
		});
	}
});