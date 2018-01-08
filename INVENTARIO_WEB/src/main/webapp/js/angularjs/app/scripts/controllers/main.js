'use strict';

/**
 * @ngdoc function
 * @name inventarioApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the inventarioApp
 */
angular.module('inventarioApp')
.controller('MainCtrl', function ($scope, $rootScope, $compile, visCustom, elementService) {



	var mainCtrl = this;

	$rootScope.despelgarMenu = false;
	mainCtrl.elementsList = [];
	for(var i=0; i < 20; i++) {
		mainCtrl.elementsList[i] = {nombre: 'element'+i};
	}

	// CSS pARAMETERS
	mainCtrl.iconWidth = 32;
	mainCtrl.iconHeight = 32;
	mainCtrl.numNodes = 0;
	mainCtrl.maxDepth = 10;
	mainCtrl.nodeWidth = 72;
	mainCtrl.nodeHeight = 72;
	mainCtrl.nodeFontSize = 15;
	mainCtrl.nodeMargin = 1.141;
	mainCtrl.modalPadding = 9;

	// FLAGS AND OTHER PARAMETERS
	mainCtrl.hoverNode = false;
	mainCtrl.isFullscreen = false;
	mainCtrl.viewPosition = null;



	mainCtrl.modal = {
		modalClass: {
			top: 0,
			left: 0,
			width: 'auto',
			height: 'auto',
			'padding-left': mainCtrl.modalPadding + 'px',
			'z-index': 'auto'
		},
		iconClass: {
			width: mainCtrl.iconWidht + 'px',
			height: mainCtrl.iconHeight + 'px',
			'font-size': mainCtrl.nodeFontSize + 'px',
			'margin-top': mainCtrl.nodeMargin + 'px',
			'margin-bottom': mainCtrl.nodeMargin + 'px'
		},
		selectedNode: null,
		modalIsVisible: false,
		hideDuplicateOption: false
	}




	/**
	* Definition of the JQuery UI draggable library and the data visualization library VIS JS
	* constructor of all the required data when the controller is initialized
	*/
	function constructor() {
		elementService.getElements()
		.then(function(response) {
			console.log('controller data', response);
			if(!response.error) {
				mainCtrl.elementsList = response.data;
			}
		});
		definitionDroppable();
		var container = document.getElementById('graph');
		visCustom.defineVisCanvas(container);
		visCustom.set('node_width', mainCtrl.nodeWidth);
		visCustom.set('node_height', mainCtrl.nodeHeight);
		definitionCanvasEvents();
	}

	constructor();




	/**
	* Definition of all the events required for the vis js library
	* CLICK => display and position of custom menu for nodes
	* ZOOM => definition of custom menu icons size related to zoom amount
	* AFTER_DRAWING => recalculate the position of the menu when the tree has been redifined
	* DRAGGING => recalculate the position of menu while the tree is dragged around the canvas
	* HOVER_NODE => define the cursor as pointer when over a node
	* HOVER_BLUR => remove definition of pointer from cursor
	*/
	function definitionCanvasEvents() {

		// CLICK EVENT
		visCustom.set('event_click', function(params) {
			if(params.nodes.length > 0) {
				mainCtrl.modal.selectedNode = params.nodes[0];
				mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.modal.selectedNode);
				mainCtrl.modal.modalIsVisible = true;
				var parents = visCustom.getNetwork().getConnectedNodes(mainCtrl.modal.selectedNode, 'from');
				mainCtrl.modal.hideDuplicateOption = (parents.length <= 0 ? true : false);
				$scope.$digest();
			}
		});

		// ZOOM EVENT
		visCustom.set('event_zoom', function(params) {
			var canvasScale = params.scale;
			mainCtrl.defineModalSize(canvasScale);
			$scope.$digest();

			if(mainCtrl.modal.modalIsVisible) {
				mainCtrl.defineModalPosition(canvasScale, mainCtrl.modal.selectedNode);
				$scope.$digest();
			}
		});

		// AFTER_DRAWING EVENT
		// every hover over a node makes it redraw, find condition to avoid repositioning our modal
		visCustom.set('after_drawing', function(params) {
			if(mainCtrl.modal.modalIsVisible) {
				mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.modal.selectedNode);
				if(!$scope.$$phase) {
					$scope.$digest();
				}
			}
		});

		// DRAGGING EVENT
		visCustom.set('while_dragging', function(params) {
			if(mainCtrl.modal.modalIsVisible) {
				mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.modal.selectedNode);
				$scope.$digest();
			}
		});

		// HOVER_NODE EVENT
		visCustom.set('hover_node', function(params) {
			mainCtrl.hoverNode = true;
			$scope.$digest();
		});

		// BLUR_NODE EVENT
		visCustom.set('blur_node', function(params) {
			mainCtrl.hoverNode = false;
			$scope.$digest();
		});
	}




	/**
	* Function in charge of calculating the position the node options modal must take inside the view
	*/
	mainCtrl.defineModalSize = function(scale, node) {
		var newIconWidth = (scale*100) * mainCtrl.iconWidth / 100;
		var newIconHeight = (scale*100) * mainCtrl.iconHeight / 100;
		var newIconFintSize = (scale*100) * mainCtrl.nodeFontSize / 100;
		var newNodeMargin = (scale*100) * mainCtrl.nodeMargin / 100;
		var newmodalPadding = (scale*100) *  mainCtrl.modalPadding / 100;

		mainCtrl.modal.modalClass['padding-left'] = newmodalPadding + 'px';
		mainCtrl.modal.iconClass['margin-top'] = newNodeMargin + 'px';
		mainCtrl.modal.iconClass['margin-bottom'] = newNodeMargin + 'px';
		mainCtrl.modal.iconClass.width = newIconWidth + 'px';
		mainCtrl.modal.iconClass.height = newIconHeight + 'px';
		mainCtrl.modal.iconClass['font-size'] = newIconFintSize + 'px';
	}




	/**
	* Function in charge of calculating the position the node options modal must take inside the view
	*/
	mainCtrl.defineModalPosition = function(scale, node) {
		var modalHeight = $('div.modal').height();
		var nodeCanvasPos = visCustom.getNetwork().getPositions([node])[node];
		var nodeDOMPos = visCustom.getNetwork().canvasToDOM({
			x: nodeCanvasPos.x,
			y: nodeCanvasPos.y
		});
		var nodeWidth = ((scale*100) * mainCtrl.nodeWidth) / 100;
		nodeDOMPos.x += $('#graph canvas').offset().left + (nodeWidth/2);
		nodeDOMPos.y += $('#graph canvas').offset().top - (modalHeight/2);

		mainCtrl.modal.modalClass.top = nodeDOMPos.y + 'px';
		mainCtrl.modal.modalClass.left = nodeDOMPos.x + 'px';
	}




	/**
	* Called from node menu options
	* Function in charge of removing a node from the tree. The deletion of the node
	* works on cascade, so all the child elements attached to the removed node will also
	* be eliminated.
	*/
	mainCtrl.deleteNode = function() {

		swal({
			title: "Eliminar Nodo",
			text: "Si eliminas este nodo todo los hijos asociados a el tambien seran borrados. Esta seguro de querer borrar este nodo?",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Eliminar",
			closeOnConfirm: true
		},
		function(){
			mainCtrl.modal.modalIsVisible = false;
			deleteTreeCascade(mainCtrl.modal.selectedNode);
			visCustom.deleteNode(mainCtrl.modal.selectedNode);
			if(visCustom.getNodes().length == 0) {
				mainCtrl.numNodes = 0;
			}
			mainCtrl.modal.selectedNode = null;
			$scope.$digest();
		});
	}



	/**
	* RECURSIVE FUNCTION
	* Deletes all nodes that extend of given parent, full branches extending from
	* given node will be deleted permenantly.
	*/
	function deleteTreeCascade(nodeID) {
		var children = visCustom.getNetwork().getConnectedNodes(nodeID, 'to');
		if(children.length > 0) {
			for(var i=0; i<children.length; i++) {
				deleteTreeCascade(children[i]);
				visCustom.deleteNode(children[i]);
			}
		}
	}




	/**
	* NOT POSIBLE TO IMPLEMENT FUNCTIONALITY RIGHT NOW
	*/
	mainCtrl.editNode = function() {
		
	}




	/**
	* Called from node menu options
	* Function in charge of closing the node options menu
	*/
	mainCtrl.closeMenu = function() {
		visCustom.getNetwork().unselectAll();
		mainCtrl.modal.selectedNode = null;
		mainCtrl.modal.modalIsVisible = false;
	}




	/**
	* Called from node menu options
	* Function in charge of duplicating selected branch and attaching it to the closest parent
	*/
	mainCtrl.duplicateBranch = function() {
		var parentNodeID = visCustom.getNetwork().getConnectedNodes(mainCtrl.modal.selectedNode, 'from');
		if(parentNodeID.length > 0) {
			swal({
				title: "Duplicar rama",
				text: "Esta apunto de duplicar una rama entera con todos sus hijos, esta seguro de querer hacerlo?",
				type: "warning",
				showCancelButton: true,
				confirmButtonClass: "btn-danger",
				confirmButtonText: "Duplicar",
				closeOnConfirm: true
			},
			function(){
				mainCtrl.modal.modalIsVisible = false;
				parentNodeID = parentNodeID[0];
				duplicateTreeCascade(parentNodeID, mainCtrl.modal.selectedNode);
				$scope.$digest();
			});
		}
	}




	/**
	* RECURSIVE FUNCTION
	* Duplicate all nodes that extend of given parent, full branches extending from
	* given node will be cloned.
	*/
	function duplicateTreeCascade(parentNodeID, nodeToCopyID) {
		var children = visCustom.getNetwork().getConnectedNodes(nodeToCopyID, 'to');
		var nodeInfo = visCustom.getNodes(nodeToCopyID);
		var newParentNode = mainCtrl.numNodes;
		var newNode = {
			id: mainCtrl.numNodes,
			label: nodeInfo.label + '_Copy',
			depth: nodeInfo.depth,
			objectID: 2
		};
		visCustom.newNode(mainCtrl.numNodes, parentNodeID, newNode);
		mainCtrl.numNodes++;
		if(children.length > 0) {
			for(var i=0; i<children.length; i++) {
				duplicateTreeCascade(newParentNode ,children[i]);
			}
		}
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
			cursorAt: { left: 25, top: 0 },
			cursor: "move"
		});
		$( ".ui-droppable" ).droppable({
			drop: function( event, ui ) {

				var newNodeName = ui.draggable.data('val');
				var type = ui.draggable.data('type');
				var parentNodeID = visCustom.getNetwork().getNodeAt({
					x: event.clientX - $(event.target).offset().left,
					y: event.clientY - $(event.target).offset().top
				});


				if(parentNodeID != null || mainCtrl.numNodes == 0) {
					var depth = (parentNodeID == null ? 0 : visCustom.getNodes(parentNodeID).depth+1);
					if(depth < mainCtrl.maxDepth) {
						var newNode = {
							id: mainCtrl.numNodes,
							label: newNodeName, // "\ue9a4"
							depth: depth,
							objectID: 1
						};
						visCustom.newNode(mainCtrl.numNodes, parentNodeID, newNode);
						mainCtrl.numNodes++;
					}
				}
			}

		});
	}




	/**
	* Makes the canvas container fill the full screen or the other way around
	*/
	mainCtrl.canvasFullscreen = function() {
		mainCtrl.viewPosition = visCustom.getNetwork().getViewPosition();
		mainCtrl.modal.modalClass['z-index'] = (mainCtrl.isFullscreen ? 'auto' : 10001);
		mainCtrl.isFullscreen = !mainCtrl.isFullscreen;
	}




	/**
	* Centers the tree in the middle allowing to view all nodes
	*/
	mainCtrl.centerTree = function() {
		visCustom.getNetwork().fit();
		mainCtrl.defineModalSize(visCustom.getScale());
	}



	/**
	* Redraws the VIS JS tree every time the container canvas is resized
	*/
	mainCtrl.resizeCanvas = function() {
		visCustom.getNetwork().redraw();
		if(mainCtrl.viewPosition != null) {
			visCustom.getNetwork().moveTo(mainCtrl.viewPosition);
		}
	}



	/**
	* Zooms in the VIS JS canvas
	*/
	mainCtrl.zoomInCanvas = function() {
		var scale = visCustom.getScale();
		var newScale = scale+(scale/2);
		mainCtrl.defineModalSize(newScale);
		visCustom.getNetwork().moveTo({ scale: newScale });
	}



	/**
	* Zooms out the VIS JS canvas
	*/
	mainCtrl.zoomOutCanvas = function() {
		var scale = visCustom.getScale();
		var newScale = scale-(scale/3);
		if(newScale > 0) {
			mainCtrl.defineModalSize(newScale);
			visCustom.getNetwork().moveTo({scale: newScale});
		}
	}

});
