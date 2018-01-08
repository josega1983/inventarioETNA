'use strict';

/**
 * @ngdoc function
 * @name inventarioApp.controller:VerMasCtrl
 * @description
 * # VerMasCtrl
 * Controller of the inventarioApp
 */
angular.module('inventarioApp')
.controller('VerMasCtrl', function ($scope, $rootScope, visCustom, configurationService) {



	var vermasCtrl = this;

	vermasCtrl.configurationID = null;
	vermasCtrl.isFullscreen = false;
	vermasCtrl.modalPadding = 9;
	vermasCtrl.treeJson = null;
	vermasCtrl.structures = null;
	vermasCtrl.showCanvasLoader = true;
	vermasCtrl.modalClass = {
		top: 0,
		left: 0,
		width: 'auto',
		height: 'auto',
		'padding-left': vermasCtrl.modalPadding + 'px',
		'z-index': 'auto'
	};



	/**
	* Definition of the JQuery UI draggable library and the data visualization library VIS JS
	* constructor of all the required data when the controller is initialized
	*/
	function constructor() {
		vermasCtrl.showCanvasLoader = true;
		var container = document.getElementById('graph');
		visCustom.defineVisCanvas(container, null);

		configurationService.getInitialContextStructures()
		.then(function(response) {
			if(!response.error) {
				vermasCtrl.structures = response.data;
				if($rootScope.editID) {
					configurationService.getConfigurationByID($rootScope.editID, vermasCtrl.structures.context)
					.then(function(response){
						if(!response.error) {
							vermasCtrl.configurationID = $rootScope.editID;
							visCustom.buildFromJson(response.data, vermasCtrl.structures.context);
						}
						vermasCtrl.showCanvasLoader = false;
					});
				}
			}
		});
	}

	constructor();

	

	/**
	* Global function called when side menu triggered.
	*/
	$rootScope.resizeContent = function() {
		visCustom.getNetwork().redraw();
	}



	/**
	* Makes the canvas container fill the full screen or the other way around
	*/
	vermasCtrl.canvasFullscreen = function() {
		vermasCtrl.modalClass['z-index'] = (vermasCtrl.isFullscreen ? 'auto' : 10001);
		vermasCtrl.isFullscreen = !vermasCtrl.isFullscreen;
	}




	/**
	* Centers the tree in the middle allowing to view all nodes
	*/
	vermasCtrl.centerTree = function() {
		visCustom.getNetwork().fit();
	}



	/**
	* Zooms in the VIS JS canvas
	*/
	vermasCtrl.zoomInCanvas = function() {
		var scale = visCustom.getScale();
		var newScale = scale+(scale/2);
		visCustom.getNetwork().moveTo({ scale: newScale });
	}



	/**
	* Zooms out the VIS JS canvas
	*/
	vermasCtrl.zoomOutCanvas = function() {
		var scale = visCustom.getScale();
		var newScale = scale-(scale/3);
		if(newScale > 0) {
			visCustom.getNetwork().moveTo({scale: newScale});
		}
	}



	// vermasCtrl.drawTree = function() {
	// 	visCustom.buildFromJson(vermasCtrl.treeJson);
	// }
});
