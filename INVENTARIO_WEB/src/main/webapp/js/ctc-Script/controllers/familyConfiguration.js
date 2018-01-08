'use strict';

/**
 * @ngdoc function
 * @name inventarioApp.controller:familyConf
 * @description
 * # familyConf
 * Controller of the inventarioApp
 */
angular.module('inventarioApp')
.controller('FamilyConfigurationCtrl', function ($scope, $rootScope, visCustom, configurationService) {

	var familyConf = this;
	// PARAMETERS
	familyConf.structures;
	familyConf.elementsList = {};
	familyConf.installationsList = {};
	familyConf.actualPageInstallations = 0;
	familyConf.actualPageElements = 0;
	familyConf.searchValues = {};
	familyConf.configurationObs = "";
	familyConf.configurationID = null;
	// FLAGS
	familyConf.disableInstallationList = false;
	familyConf.disableElementsList = true;
	familyConf.showListLoader = false;
	familyConf.searchMore = true;
	familyConf.timeOutTime = 0;



	/**
	* Gets the installations of its corresponding context
	*/
	function getInstallation(params) {
		familyConf.installationsList = null;
		familyConf.actualPageInstallations = 0;
		familyConf.searchMore = true;
		if(!familyConf.disableInstallationList){
			familyConf.showListLoader = true;
		}
		configurationService.getInstallation(params, familyConf.actualPageInstallations)
		.then(function(response) {
			if(!response.error) {
				familyConf.installationsList = response.data;
				familyConf.actualPageInstallations++;
				if(!familyConf.disableInstallationList && !familyConf.installationsList.more_results) {
					familyConf.showListLoader = false;
				}
			}
		});
	}



	function getMoreInstallations(params) {
		configurationService.getInstallation(params, familyConf.actualPageInstallations)
		.then(function(response) {
			if(!response.error) {
				familyConf.installationsList.list = familyConf.installationsList.list.concat(response.data.list);
				familyConf.installationsList.more_results = response.data.more_results;
				familyConf.actualPageInstallations++;
				if(familyConf.installationsList.more_results){
					familyConf.searchMore = true;
				}
				if(!familyConf.disableInstallationList && !familyConf.installationsList.more_results) {
					familyConf.showListLoader = false;
				}
			}
		});
	}


	/**
	* Gets the elements of its corresponding context
	*/
	function getElements(params) {
		familyConf.elementsList = null;
		familyConf.actualPageElements = 0;
		familyConf.searchMore = true;
		if(!familyConf.disableElementsList) {
			familyConf.showListLoader = true;
		}
		configurationService.getElements(params)
		.then(function(response) {
			if(!response.error) {
				familyConf.elementsList = response.data;
				familyConf.actualPageElements++;
				if(!familyConf.disableElementsList && !familyConf.elementsList.more_results) {
					familyConf.showListLoader = false;
				}
			}
		});
	}


	function getMoreElements() {
		configurationService.getMoreElements(familyConf.actualPageElements)
		.then(function(response) {
			if(!response.error) {
				familyConf.elementsList.list = familyConf.elementsList.list.concat(response.data.list);
				familyConf.elementsList.more_results = response.data.more_results;
				familyConf.actualPageElements++;
				if(familyConf.elementsList.more_results){
					familyConf.searchMore = true;
				}
				if(!familyConf.disableElementsList && !familyConf.elementsList.more_results) {
					familyConf.showListLoader = false;
				}
			}
		});
	}



	/**
	* Gets the corresponding configuration and loads it on screen
	*/
	familyConf.getConfigurationByID = function(context) {
		$scope.main.showCanvasLoader = true;
		configurationService.getConfigurationByID($rootScope.editID, context)
		.then(function(response){
			if(!response.error) {
				var configuration = response.data;
				familyConf.configurationID = parseInt($rootScope.editID);
				familyConf.configurationObs = configuration.observaciones;
				$scope.main.numNodes = visCustom.buildFromJson(configuration, familyConf.structures.context);
				visCustom.getNetwork().fit();
				$scope.main.showCanvasLoader = false;
				if($scope.main.numNodes > 0) {
					familyConf.showCorrespondingList($rootScope.ELEMENT_TYPE);
					getInstallation(null);
					getElements(null);
				}
			} else {
				$scope.main.showCanvasLoader = false;
			}
		});
	}




	/**
	* Definition of the JQuery UI draggable library and the data visualization library VIS JS
	* constructor of all the required data when the controller is initialized
	*/
	function constructor() {
		$scope.main.constructor();
		configurationService.getInitialContextStructures()
		.then(function(response) {
			if(!response.error) {
				familyConf.structures = response.data;
				if($rootScope.editID) {
					familyConf.getConfigurationByID(familyConf.structures.context);
				} else {
					getInstallation(null);
					getElements(null);
				}
				if(familyConf.structures.context == $rootScope.TYPE_CONFIGURATION) {
					familyConf.getConfigurationFamily(null);
				}
			}
		});

		definitionDroppable();
	}

	constructor();





	/**
	* Shows list corresponding to given parameter, two posible parameters:
	* ELEMENT_TYPE = "element"
	* INSTALLATION_TYPE = "installation"
	* also clears the search filters
	*/
	familyConf.showCorrespondingList = function(type) {
		familyConf.searchValues = {};
		switch (type) {
			case $rootScope.INSTALLATION_TYPE:
				familyConf.disableInstallationList = false;
				familyConf.disableElementsList = true;
				familyConf.searchMore = true;
				getInstallation(null);
			break;
			case $rootScope.ELEMENT_TYPE:
				familyConf.disableInstallationList = true;
				familyConf.disableElementsList = false;
				familyConf.searchMore = true;
				getElements(null);
			break;
		}
	}





	/**
	* FUNCTION CALLED FROM MAIN CONTROLLER
	* Defines the function called when in the main visjs canvas the root node is deleted.
	*/
	$scope.$parent.whenRootNodeDeleted = function() {
		// familyConf.search($rootScope.INSTALLATION_TYPE);
		familyConf.showCorrespondingList($rootScope.INSTALLATION_TYPE);
	}





	/**
	* Definition of all the required data for the draggable, droppable library JQuery UI.
	* In charge of creating the new nodes and from where the should extend.
	* DEFINING DRAGGABLE => What elements can be dragged and whats its containment
	* DEFINING DROPPABLE => The allowed contaiment where the draggable elements can be dropped
	* 		and the creating of nodes.
	*/
	function definitionDroppable() {
		$( ".draggable-elements .ui-draggable" ).draggable({
			containment: "body",
			appendTo: "body",
			scroll: false,
			helper: 'clone',
			start: function( event, ui ) {
				$(ui.helper).height($(ui.helper).width());
			},
			cursorAt: { left: 45, top: 10 },
			cursor: "move"
		});
		$( ".ui-droppable" ).droppable({
			drop: function( event, ui ) {
				var type = ui.draggable.data('type');
				var objectIndex = ui.draggable.data('index');
				var object = {}, name;
				var shape = 'box';
				if(type == $rootScope.ELEMENT_TYPE) {
					name = familyConf.elementsList.list[objectIndex].nombre;
					for(var key in familyConf.structures.element) {
						object[key] = null;
					}
					object.familiaElemento = familyConf.elementsList.list[objectIndex];
				} else {
					shape = 'circle';
					object = familyConf.structures.installation; // POINTER
					name = familyConf.installationsList.list[objectIndex].nombre;
					object.familiaInstalacion = familyConf.installationsList.list[objectIndex];
					familyConf.showCorrespondingList($rootScope.ELEMENT_TYPE);
					$scope.$digest();
				}

				var parentNodeID = visCustom.getNetwork().getNodeAt({
					x: event.clientX - $(event.target).offset().left,
					y: event.clientY - $(event.target).offset().top
				});


				if(parentNodeID != null || $scope.main.numNodes == 0) {
					var depth = (parentNodeID == null ? 1 : visCustom.getNodes(parentNodeID).depth+1);
					if(depth < $scope.main.MAX_DEPTH) {
						var newNode = {
							id: $scope.main.numNodes,
							label: name,
							depth: depth,
							object: object,
							type: type,
							shape: shape
						};
						visCustom.newNode($scope.main.numNodes, parentNodeID, newNode);
						$scope.main.numNodes++;
					} else {
						swal({
							title: "Aviso",
							text: "Se ha llegado a la profundidad maxima del arbol",
							type: "warning",
							confirmButtonText: "Aceptar",
							closeOnConfirm: true
						});
					}
				}
			}

		});
	}




	/**
	* Function in charge of adding a installation family to the graph, instead of using the
	* dragg and drop this method uses the on click event to create.
	*/
	familyConf.createInstallationFamily = function(objectIndex) {
		var object = familyConf.structures.installation; // POINTER
		object.familiaInstalacion = familyConf.installationsList.list[objectIndex];
		familyConf.showCorrespondingList($rootScope.ELEMENT_TYPE);

		var newNode = {
			id: $scope.main.numNodes,
			label: familyConf.installationsList.list[objectIndex].nombre,
			depth: 1,
			object: object,
			type: $rootScope.INSTALLATION_TYPE,
			shape: 'circle'
		};
		visCustom.newNode($scope.main.numNodes, null, newNode);
		$scope.main.numNodes++;
	}





	/**
	* Saves the actual configuration of the tree with all its data, in case of an error
	* while saving it will show a modal notifying the user.
	*/
	familyConf.saveConfiguration = function() {

		// close menu if open
		visCustom.getNetwork().fit();
		var $body = angular.element('body');
		if($body.hasClass('menu-lateral-activo')) {
			$body.removeClass('menu-lateral-activo');
			familyConf.timeOutTime = 300;
			$rootScope.openingMenu();
		} else {
			familyConf.timeOutTime = 0;
		}

		var rootNode = visCustom.getNodes(0);
		rootNode.object.observaciones = familyConf.configurationObs;
		rootNode.object.id = parseInt(familyConf.configurationID);
		var jsonDTO = visCustom.visToDTO();
		$scope.main.showCanvasLoader = true;
		configurationService.putTreeConfigurationDTO(jsonDTO)
		.then(function(response) {
			var errorFlag = true;
			if(!response.error || response.data.estado.toLowerCase() != "error") {
				familyConf.configurationID = parseInt(response.data.idElemento);
				familyConf.saveImageFromCanvas(response.data.idElemento);
				errorFlag = false;
			}
			$scope.main.showCanvasLoader = false;
			swal({
				title: (errorFlag ? "Error" : "Información"),
				text: (errorFlag ? "Se ha producido un error al momento de guardar la configuracion\n"+reponse.data.mensaje : "La configuracion se ha guardado correctamente"),
				type: (errorFlag ? "error" : "success"),
				confirmButtonText: "Aceptar",
				closeOnConfirm: true
			});
		});
	}




	/**
	* Calls for more elements when the pagination loader is visible in the list
	*/
	familyConf.getMoreResults = function(type) {
		if(familyConf.searchMore) {
			switch (type) {
				case $rootScope.ELEMENT_TYPE:
					familyConf.searchMore = false;
					getMoreElements();
				break;
				case $rootScope.INSTALLATION_TYPE:
					familyConf.searchMore = false;
					getMoreInstallations(familyConf.searchValues);
				break;
			}
		}
	}


	/**
	* Clears the selected filter and searchs with the left parameters
	*/
	familyConf.clearFilter = function(type, filter) {
		familyConf.searchValues[filter] = null;
		familyConf.search(type);
	}


	/**
	* Search done by the filters found in each list header, it searches for elements and installations.
	*/
	familyConf.search = function(type) {
		switch (type) {
			case $rootScope.ELEMENT_TYPE:
				getElements(familyConf.searchValues);
			break;
			case $rootScope.INSTALLATION_TYPE:
				getInstallation(familyConf.searchValues);
			break;
		}
	}




	/**
	* Centers the canvas image. After the the canvas is centered it downloads the image
	* and encodes it in base64.
	*/
	familyConf.saveImageFromCanvas = function(configurationID) {
		setTimeout(function() {
			var canvas = angular.element('.graph-container canvas')[0];
			var pngUrl = canvas.toDataURL("image/png");
			var pngBase64 = btoa(pngUrl);
			configurationService.putTreeConfigurationImage({
				'base64JSON': pngBase64,
				'idFilaSeleccionada': configurationID
			})
			.then(function() {

			});
		}, familyConf.timeOutTime );
	}
});