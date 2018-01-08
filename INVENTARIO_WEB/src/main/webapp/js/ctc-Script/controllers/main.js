'use strict';

/**
 * @ngdoc function
 * @name inventarioApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the inventarioApp
 */
angular.module('inventarioApp')
.controller('MainCtrl', function ($scope, $rootScope, visCustom) {



	var mainCtrl = this;
	// PARAMETERS
	mainCtrl.MAX_DEPTH = 10;
	mainCtrl.numNodes = 0;
	mainCtrl.copiedBranch = {};
	mainCtrl.viewPosition = null;
	mainCtrl.selectedNode = null;
	// FLAGS
	mainCtrl.showCanvasLoader = false;
	mainCtrl.hoverNode = false;
	mainCtrl.isFullscreen = false;
	// CSS pARAMETERS
	mainCtrl.iconWidth = 32;
	mainCtrl.iconHeight = 32;
	mainCtrl.nodeWidth = 72;
	mainCtrl.nodeHeight = 72;
	mainCtrl.nodeFontSize = 15;
	mainCtrl.nodeMargin = 1.141;
	mainCtrl.modalPadding = 15;
	

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
			width: mainCtrl.iconWidth + 'px',
			height: mainCtrl.iconHeight + 'px',
			'font-size': mainCtrl.nodeFontSize + 'px',
			'margin-top': mainCtrl.nodeMargin + 'px',
			'margin-bottom': mainCtrl.nodeMargin + 'px'
		},
		modalIsVisible: false,
		hideOption: false,
		hidePaste: true
	}




	/**
	* Definition of the JQuery UI draggable library and the data visualization library VIS JS
	* constructor of all the required data when the controller is initialized
	*/
	mainCtrl.constructor = function() {
		var container = document.getElementById('graph');
		visCustom.defineVisCanvas(container);
		visCustom.set('node_width', mainCtrl.nodeWidth);
		visCustom.set('node_height', mainCtrl.nodeHeight);
		definitionCanvasEvents();
	}




	/**
	* Callback called when main content is resized.
	*/
	$rootScope.resizeContent = function() {
		mainCtrl.resizeCanvas();
		mainCtrl.defineModalSize(visCustom.getScale());
	}




	/**
	*
	*/
	mainCtrl.definePositionOnScroll = function() {
		mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.selectedNode);
		$scope.$apply();
	}




	/**
	*
	*/
	mainCtrl.toggleCanvasMenu = function(flag) {
		mainCtrl.modal.modalIsVisible = flag;
		if(mainCtrl.modal.modalIsVisible) {
			document.querySelector('.main-container > .contenido').addEventListener("scroll", mainCtrl.definePositionOnScroll );
		} else {
			document.querySelector('.main-container > .contenido').removeEventListener("scroll", mainCtrl.definePositionOnScroll );
		}
	}



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
				mainCtrl.selectedNode = params.nodes[0];
				mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.selectedNode);
				mainCtrl.defineModalSize(visCustom.getScale());
				mainCtrl.toggleCanvasMenu(true);
				var parents = visCustom.getNetwork().getConnectedNodes(mainCtrl.selectedNode, 'from');
				mainCtrl.modal.hideOption = (parents.length <= 0 ? true : false);
				$scope.$digest();
			}
		});

		// ZOOM EVENT
		visCustom.set('event_zoom', function(params) {
			var canvasScale = params.scale;
			mainCtrl.defineModalSize(canvasScale);
			$scope.$digest();

			if(mainCtrl.modal.modalIsVisible) {
				mainCtrl.defineModalPosition(canvasScale, mainCtrl.selectedNode);
				$scope.$digest();
			}
		});

		// AFTER_DRAWING EVENT
		// every hover over a node makes it redraw, find condition to avoid repositioning our modal
		visCustom.set('after_drawing', function(params) {
			if(mainCtrl.modal.modalIsVisible) {
				mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.selectedNode);
				if(!$scope.$$phase) {
					$scope.$digest();
				}
			}
		});

		// DRAGGING EVENT
		visCustom.set('while_dragging', function(params) {
			if(mainCtrl.modal.modalIsVisible) {
				mainCtrl.defineModalPosition(visCustom.getScale(), mainCtrl.selectedNode);
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
		if(typeof nodeCanvasPos != 'undefined') {
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
	}




	/**
	* Called from node menu options
	* Function in charge of removing a node from the tree. The deletion of the node
	* works on cascade, so all the child elements attached to the removed node will also
	* be eliminated.
	*/
	mainCtrl.deleteNode = function(callback) {

		swal({
			title: "Eliminar Nodo",
			text: "Eliminar el nodo eliminara tambien todos sus nodos asociados. Esta seguro de querer borrar este nodo?",
			type: "warning",
			showCancelButton: true,
			confirmButtonClass: "btn-danger",
			confirmButtonText: "Eliminar",
			closeOnConfirm: true
		},
		function(){
			mainCtrl.toggleCanvasMenu(false);
			mainCtrl.showCanvasLoader = true;
			deleteTreeCascade(mainCtrl.selectedNode);
			visCustom.deleteNode(mainCtrl.selectedNode);
			if(visCustom.getNodes().length == 0) {
				mainCtrl.numNodes = 0;
				$scope.whenRootNodeDeleted();
			}
			mainCtrl.selectedNode = null;
			mainCtrl.showCanvasLoader = false;
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
	* Called from node menu options
	* Function in charge of closing the node options menu
	*/
	mainCtrl.closeMenu = function() {
		visCustom.getNetwork().unselectAll();
		mainCtrl.selectedNode = null;
		mainCtrl.toggleCanvasMenu(false);
	}




	/**
	* Called from node menu options
	* Function in charge of duplicating selected branch and attaching it to the closest parent
	*/
	mainCtrl.duplicateBranch = function(callback) {
		var parentNodeID = visCustom.getNetwork().getConnectedNodes(mainCtrl.selectedNode, 'from');
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
				mainCtrl.toggleCanvasMenu(false);
				mainCtrl.showCanvasLoader = true;
				parentNodeID = parentNodeID[0];
				duplicateTreeCascade(parentNodeID, mainCtrl.selectedNode);
				mainCtrl.showCanvasLoader = false;
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
		var newObject = $rootScope.cloneObject(nodeInfo.object);
		newObject.id = null;
		var newNode = {
			id: mainCtrl.numNodes,
			label: nodeInfo.label,
			depth: nodeInfo.depth,
			object: newObject,
			shape: nodeInfo.shape
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
	* Called from node menu options
	* Function in charge of making a copy of a given branch
	*/
	mainCtrl.copyBranch = function() {
		mainCtrl.copiedBranch = {};
		mainCtrl.modal.hidePaste = false;
		mainCtrl.copiedBranch = copyTreeCascade(mainCtrl.selectedNode, 0);
	}




	/**
	* RECURSIVE FUNCTION
	* Copies all nodes that extend of given parent, full branches extending from
	* given node will be copied.
	*/
	function copyTreeCascade(nodeToCopyID, depth) {
		depth++;
		var children = visCustom.getNetwork().getConnectedNodes(nodeToCopyID, 'to');
		var nodeInfo = visCustom.getNodes(nodeToCopyID);
		var newObject = $rootScope.cloneObject(nodeInfo.object);
		newObject.id = null;
		var maxDepth = depth;
		var json = {
			node: {
				label: nodeInfo.label,
				depth: nodeInfo.depth,
				object: newObject,
				shape: nodeInfo.shape
			},
			children: []
		};
		if(children.length > 0) {
			for(var i=0; i<children.length; i++) {
				var data = copyTreeCascade(children[i], depth);
				json.children.push(data.json);
				if(data.depth > maxDepth) {
					maxDepth = data.depth;
				}
			}
		}
		return {json: json, depth: maxDepth};
	}




	/**
	* Called from node menu options
	* Function in charge of pastying the copy of a branch
	*/
	mainCtrl.pasteBranch = function() {
		var actualNode = visCustom.getNodes(mainCtrl.selectedNode);
		if(actualNode.depth + mainCtrl.copiedBranch.depth > 10) {
			swal({
				title: "Pegar rama copiada",
				text: "Si se pega la rama en el nodo actual se supera la profundidad maxima del arbol.",
				type: "warning",
				confirmButtonText: "Aceptar",
				closeOnConfirm: true
			});
		} else {
			pasteTreeCascade(mainCtrl.copiedBranch.json, mainCtrl.selectedNode, actualNode.depth+1);
			mainCtrl.copiedBranch = {}
			mainCtrl.modal.hidePaste = true;
		}
	}




	/**
	* RECURSIVE FUNCTION
	* Pastes all nodes that that where copied whit the branch
	*/
	function pasteTreeCascade(json, parentNodeID, depth) {
		var newParentNode = mainCtrl.numNodes;
		var newNode = $rootScope.cloneObject(json.node);
		newNode.id = mainCtrl.numNodes;
		newNode.depth = depth;
		visCustom.newNode(mainCtrl.numNodes, parentNodeID, newNode);
		mainCtrl.numNodes++;
		if(json.children.length > 0) {
			for(var i=0; i<json.children.length; i++) {
				pasteTreeCascade(json.children[i], newParentNode, depth+1);
			}
		}
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