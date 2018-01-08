'use strict';

/**
 * @ngdoc function
 * @name inventarioApp.controller:typeConf
 * @description
 * # typeConf
 * Controller of the inventarioApp
 */
angular.module('inventarioApp')
.controller('TypeConfigurationCtrl', function ($scope, $compile, $rootScope, visCustom, visTempCustom, configurationService) {

	var typeConf = this;
	// PARAMETERS

	// structures and main info
	typeConf.structures;
	typeConf.configurationObs = "";
	typeConf.configurationID = null;
	typeConf.installationType = null;
	// All related to element list
	typeConf.elementsList = {};
	typeConf.actualPageElements = 0;
	typeConf.searchValues = {};
	typeConf.showListLoader = false;
	// All related to carousel list
	typeConf.configurationTypeList = {};
	typeConf.configurationFamilyList = {};
	typeConf.configurationConcatList = [];
	typeConf.actualPageConfigurationType = 0;
	typeConf.searchCarouselValues = null;
	typeConf.showCarouselLoader = false;
	typeConf.showCarouselSearch = false;
	// All related to configuration modal
	typeConf.showConfigurationModal = false;
	typeConf.showCanvasModalLoader = false;
	typeConf.modalSelectedConf = null;
	typeConf.modalTitle = '';
	// Other
	typeConf.showCarouselDisabled = true;
	typeConf.searchMore = true;
	typeConf.timeOutTime = 0;



	/**
	* Gets the installations of its corresponding context and searches for
	* all CFC and CTC than belong to that installation.
	*/
	typeConf.getInstallation = function(params) {
		typeConf.showCarouselLoader = true;
		configurationService.getInstallation(params, 0)
		.then(function(response) {
			if(!response.error) {
				typeConf.installationType = response.data.list.pop();
				var installationFamilyID = typeConf.installationType.familiaInstalacion.id;
				typeConf.getConfigurationFamily({'familiaInstalacion.id': installationFamilyID});
				typeConf.getConfigurationType({'tipoInstalacion.familiaInstalacion.id': installationFamilyID});
			}
		});
	}




	/**
	* Gets the elements of its corresponding context
	*/
	typeConf.getElements = function(params) {
		typeConf.elementsList = {list: []};
		typeConf.actualPageElements = 0;
		typeConf.searchMore = true;
		typeConf.showListLoader = true;
		configurationService.getElements(params)
		.then(function(response) {
			if(!response.error) {
				typeConf.elementsList = response.data;
				typeConf.actualPageElements++;
				if(!typeConf.elementsList.more_results) {
					typeConf.showListLoader = false;
				}
			}
		});
	}




	/**
	* Called when the pagination spinner is visible, it obteins the 10 next elements of the 
	* list. This elements are obteined based on the corresponging context.
	*/
	function getMoreElements() {
		configurationService.getMoreElements(typeConf.actualPageElements)
		.then(function(response) {
			if(!response.error) {
				typeConf.elementsList.list = typeConf.elementsList.list.concat(response.data.list);
				typeConf.elementsList.more_results = response.data.more_results;
				typeConf.actualPageElements++;
				if(typeConf.elementsList.more_results){
					typeConf.searchMore = true;
				}
				if(!typeConf.elementsList.more_results) {
					typeConf.showListLoader = false;
				} else {
					typeConf.showListLoader = true;
				}
			}
		});
	}




	/**
	* Gets the family configurations (CFC)
	* this list of is later concatenated whit the CTC list.
	*/
	typeConf.getConfigurationFamily = function(params) {
		typeConf.configurationConcatList = [];
		typeConf.configurationFamilyList = {list: []};
		configurationService.getConfigurationFamily(params, 0)
		.then(function(response) {
			if(!response.error) {
				typeConf.configurationFamilyList = response.data;
				typeConf.configurationConcatList = typeConf.configurationFamilyList.list.concat(typeConf.configurationTypeList.list);
			}
		});
	}




	/**
	* Gets the configuration types (CTC)
	* this list of is later concatenated whit the CFC list.
	*/
	typeConf.getConfigurationType = function(params) {
		typeConf.configurationConcatList = [];
		typeConf.configurationTypeList = {list: []};
		typeConf.actualPageConfigurationType = 0;
		typeConf.showCarouselLoader = true;
		configurationService.getConfigurationType(params, typeConf.actualPageConfigurationType)
		.then(function(response) {
			if(!response.error) {
				for(var index=0; index < response.data.list.length; index++) {
					if(response.data.list[index].id == $rootScope.editID) {
						response.data.list.splice(index, 1);
						break;
					}
				}
				typeConf.configurationTypeList = response.data;
				typeConf.configurationConcatList = typeConf.configurationFamilyList.list.concat(typeConf.configurationTypeList.list);
				typeConf.actualPageConfigurationType++;
				if(!typeConf.configurationTypeList.more_results){
					typeConf.showCarouselLoader = false;
				}
			}
		});
	}




	/**
	* Called when the pagination spinner is visible, it obteins the 10 next configuration types of the 
	* list. This configurations are later concatenated with the existing CFC and CTC
	*/
	function getMoreConfigurationType() {
		configurationService.getMoreConfigurationType(typeConf.actualPageConfigurationType)
		.then(function(response) {
			if(!response.error) {
				for(var index=0; index < response.data.list.length; index++) {
					if(response.data.list[index].id == $rootScope.editID) {
						response.data.list.splice(index, 1);
						break;
					}
				}
				typeConf.configurationTypeList.list = typeConf.configurationTypeList.list.concat(response.data.list);
				typeConf.configurationConcatList = typeConf.configurationConcatList.list.concat(response.data.list);
				typeConf.configurationTypeList.more_results = response.data.more_results;				
				typeConf.actualPageConfigurationType++;
				if(!typeConf.configurationTypeList.more_results){
					typeConf.showCarouselLoader = false;
				}
			}
		});
	}




	/**
	* Gets the corresponding configuration with all the required info to be able to bild the tree.
	* This function get the configuration depending of the given parameter context. (CFC or CTC)
	*/
	typeConf.getConfigurationByID = function(context) {
		$scope.main.showCanvasLoader = true;
		configurationService.getConfigurationByID($rootScope.editID, context)
		.then(function(response){
			if(!response.error) {
				typeConf.getInstallation({
					'id' : response.data.tipoInstalacion.id,
					'asociadaConfiguracion': false
				});
				var configuration = response.data;
				typeConf.configurationID = parseInt($rootScope.editID);
				typeConf.configurationObs = configuration.observaciones;
				$scope.main.numNodes = visCustom.buildFromJson(configuration, typeConf.structures.context);
				visCustom.getNetwork().fit();
				$scope.main.showCanvasLoader = false;
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
				typeConf.structures = response.data;
				if($rootScope.editID) {
					angular.element('#idTipoInstalacionBotonPopup')[0].setAttribute('disabled', true);
					typeConf.getConfigurationByID(typeConf.structures.context);
				}
			}
		});

		typeConf.getElements(null);

		definitionDroppable();
		popupHiddenInputListener();
	}

	constructor();




	/**
	* Callback called when main content is resized.
	*/
	var auxFunct = $rootScope.resizeContent;
	$rootScope.resizeContent = function() {
		var element = angular.element('.owl-carousel');
		$(element).trigger('refresh.owl.carousel');
		auxFunct();
	}




	/**
	*
	*/
	typeConf.showCarouselSearchFunction = function() {
		typeConf.showCarouselSearch = !typeConf.showCarouselSearch;
		$scope.initCarousel();
	}



	/**
	* Function in charge of defining the changes on the hidden input filled in by the modal.
	* If the listener is triggered it calls getInstallations searching by the obteined id.
	*/
	function popupHiddenInputListener() {
		document.getElementById('installationTypeID').addEventListener('DOMSubtreeModified', function () {
			var element = document.getElementById('idTipoInstalacionSeleccionada');
			if(element != null) {
				var installationID = document.getElementById('idTipoInstalacionSeleccionada').value;
				if(typeof installationID != 'undefined' && installationID != null && installationID.trim().length > 0) {
					typeConf.showCarouselDisabled = false;
					typeConf.getInstallation({
						'id' : installationID,
						'asociadaConfiguracion': true
					});
				}
			}
		}, false)
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

				var parentNodeID = visCustom.getNetwork().getNodeAt({
					x: event.clientX - $(event.target).offset().left,
					y: event.clientY - $(event.target).offset().top
				});

				if(parentNodeID != null || $scope.main.numNodes == 0) {
					var depth = (parentNodeID == null ? 0 : visCustom.getNodes(parentNodeID).depth+1);
					if(depth < $scope.main.MAX_DEPTH) {
						var object = $rootScope.cloneObject(typeConf.structures.element);
						object.familiaElemento = typeConf.elementsList.list[objectIndex];
						var newNode = {
							id: $scope.main.numNodes,
							label: typeConf.elementsList.list[objectIndex].nombre,
							depth: depth,
							object: object,
							type: type,
							shape: 'box'
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
	* Defines the function called when in the main visjs canvas the root node is deleted.
	*/
	$scope.$parent.whenRootNodeDeleted = function() {
		typeConf.showCarouselDisabled = false;
		angular.element('#idTipoInstalacionBotonPopup')[0].removeAttribute('disabled');
	}




	/**
	* Saves the actual configuration of the tree with all its data, in case of an error
	* while saving it will show a modal notifying the user.
	*/
	typeConf.saveConfiguration = function() {

		// close menu if open
		visCustom.getNetwork().fit();
		var $body = angular.element('body');
		if($body.hasClass('menu-lateral-activo')) {
			$body.removeClass('menu-lateral-activo');
			$rootScope.openingMenu();
			typeConf.timeOutTime = 300;
		} else {
			typeConf.timeOutTime = 0;
		}

		var rootNode = visCustom.getNodes(0);
		rootNode.object.observaciones = typeConf.configurationObs;
		rootNode.object.id = parseInt(typeConf.configurationID);
		var jsonDTO = visCustom.visToDTO();
		$scope.main.showCanvasLoader = true;
		configurationService.putTreeConfigurationDTO(jsonDTO)
		.then(function(response) {
			var errorFlag = true;
			if(!response.error && response.data.estado.toLowerCase() != "error") {
				typeConf.configurationID = parseInt(response.data.idElemento);
				typeConf.saveImageFromCanvas(response.data.idElemento);
				errorFlag = false;
			}
			$scope.main.showCanvasLoader = false;
			swal({
				title: (errorFlag ? "Error" : "Información"),
				text: (errorFlag ? "Se ha producido un error al momento de guardar la configuracion\n"+(response.data != null ? response.data.mensaje:'') : "La configuracion se ha guardado correctamente"),
				type: (errorFlag ? "error" : "success"),
				confirmButtonText: "Aceptar",
				closeOnConfirm: true
			});
		});

	}




	/**
	* called when the loader of pagination becomes visible.
	* gets the next set of items of the list
	*/
	typeConf.getMoreResults = function(type) {
		if(typeConf.searchMore) {
			typeConf.searchMore = false;
			getMoreElements();
		}
	}




	/**
	* Clears the selected filter and searchs with the edited parameters
	*/
	typeConf.clearFilter = function(filter) {
		typeConf.searchValues[filter] = null;
		typeConf.getElements(typeConf.searchValues);
	}




	/**
	* Opens a modal with the corresponding configuration bassed on id and context passed through parameter.
	*/
	typeConf.openConfigurationFamilyModal = function(index, context) {
		typeConf.modalSelectedConf = {
			index: index,
			context: context
		};
		var id = typeConf.configurationConcatList[index].id;
		typeConf.showCanvasModalLoader = true;
		configurationService.getConfigurationByID(id, context)
		.then(function(response){
			typeConf.showCanvasModalLoader = false;
			if(!response.error) {
				if(context == $rootScope.TYPE_CONFIGURATION) {
					typeConf.modalTitle = response.data.tipoInstalacion.familiaInstalacion.nombre +'|'+ response.data.tipoInstalacion.marca +'|'+ response.data.tipoInstalacion.modelo;
				} else {
					typeConf.modalTitle = response.data.familiaInstalacion.nombre;
				}
				var container = document.getElementById('modal-graph');
				visTempCustom.defineVisCanvas(container);
				visTempCustom.buildFromJson(response.data, context);
			}
		});
		typeConf.showConfigurationModal = true;
	}




	/**
	* Closes the modal that shows the configuration of the items show in the carousel
	*/
	typeConf.closeConfigurationFamilyModal = function() {
		typeConf.showConfigurationModal = false;
		typeConf.modalSelectedConf = null;
	}




	/**
	* Clears the given filter passed throught parameter and searches with ones that are left
	*/
	typeConf.clearCarouselFilter = function(filter) {
		typeConf.searchCarouselValues[filter] = null;
		typeConf.carouselSearch();
	}




	/**
	* Called when search button is pressed in the carousel search form
	*/
	typeConf.carouselSearch = function() {
		typeConf.showCarouselSearch = false;
		var filters = typeConf.searchCarouselValues;
		var params = {
			'tipoInstalacion.familiaInstalacion.id': typeConf.installationType.familiaInstalacion.id,
			'tipoInstalacion.marca' : (typeof filters['tipoInstalacion.marca'] != 'undefined' ? filters['tipoInstalacion.marca'] : null),
			'tipoInstalacion.modelo' : (typeof filters['tipoInstalacion.modelo'] != 'undefined' ? filters['tipoInstalacion.modelo'] : null)
		}
		typeConf.getConfigurationType(params);
	}




	/**
	* Called when the clear button is pressed in the carousel search form.
	* clears all the existing filters and searchs for all the results again.
	*/
	typeConf.clearCarouselSearch = function() {
		typeConf.showCarouselSearch = false;
		typeConf.searchCarouselValues = null;
		typeConf.getConfigurationType({'tipoInstalacion.familiaInstalacion.id': typeConf.installationType.familiaInstalacion.id});
	}




	/**
	* PAGINATION MANAGER
	* onTranslated callback, function called when the carousel is moved (buttons or dragg). Checks if the 
	* loader (witch is the las element) is visible, if visible we call for more results.
	*/
	$rootScope.onCarouselTranslatedCallback = function(event) {
		var loader = angular.element('.carousel-item-loader .loader2');
		var content = angular.element('.owl-carousel');
		var offsetRigth = content.offset().left + content.outerWidth();
		if(loader.is(':visible') && loader.offset().left < offsetRigth) {
			typeConf.getMoreConfigurationType();
		}
	}




	typeConf.defineCTCFromOther = function() {
		angular.element('#idTipoInstalacionBotonPopup')[0].setAttribute('disabled', true);
		typeConf.showCanvasLoader = true;
		typeConf.showConfigurationModal = false;
		typeConf.showCarouselDisabled = true;
		var id = typeConf.configurationConcatList[typeConf.modalSelectedConf.index].id;
		configurationService.getConfigurationByID(id, typeConf.modalSelectedConf.context)
		.then(function(response){
			if(!response.error) {
				typeConf.structures.installation.tipoInstalacion = typeConf.installationType;
				var CTC = typeConf.structures.installation;
				CTC.hijos = response.data.hijos;
				$scope.main.numNodes = visCustom.buildFromJson(CTC, typeConf.structures.context);
				visCustom.getNetwork().fit();
			}
			typeConf.showCanvasLoader = false;
		});
	}




	typeConf.defineCTCfromZero = function() {
		angular.element('#idTipoInstalacionBotonPopup')[0].setAttribute('disabled', true);
		typeConf.showCarouselDisabled = true;
		var object = typeConf.structures.installation; // POINTER
		var name = typeConf.installationType.familiaInstalacion.nombre+"\n"+typeConf.installationType.modelo+"\n"+typeConf.installationType.marca;
		object.tipoInstalacion = typeConf.installationType;

		var newNode = {
			id: $scope.main.numNodes,
			label: name,
			depth: 1,
			object: object,
			type: $rootScope.INSTALLATION_TYPE,
			shape: 'circle'
		};
		visCustom.newNode($scope.main.numNodes, null, newNode);
		$scope.main.numNodes++;
	}




	/**
	* Centers the canvas image. After the the canvas is centered it downloads the image
	* and encodes it in base64.
	*/
	typeConf.saveImageFromCanvas = function(configurationID) {
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
		}, typeConf.timeOutTime );
	}


	typeConf.imageToBase64 = function(image) {
		return atob(image);
	}

});