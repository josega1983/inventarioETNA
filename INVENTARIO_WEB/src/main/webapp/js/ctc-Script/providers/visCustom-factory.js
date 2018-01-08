

angular.module('inventarioApp')
.factory('visCustom', function($rootScope, $http, $location) {


	var visData = this;
	visData.network;
	visData.nodes = new vis.DataSet();
	visData.edges = new vis.DataSet();

	visData.shapes = {
		element: 'box',
		installation: 'circle'
	}

	visData.options;
	visData.data = {
		nodes: visData.nodes,
		edges: visData.edges
	};

	visData.event_click;
	visData.event_zoom;
	visData.after_drawing;
	visData.while_dragging;
	visData.hover_node;
	visData.blur_node;
	visData.DTOJson = '';


	var object = {
		defineVisCanvas: function(container, options) {
			if(typeof options != 'undefined' && options != null) {
				visData.event_click = (typeof options.event_click === 'function' ? options.event_click : null);
				visData.event_zoom = (typeof options.event_zoom === 'function' ? options.event_zoom : null);
				visData.after_drawing = (typeof options.after_drawing === 'function' ? options.after_drawing : null);
				visData.while_dragging = (typeof options.while_dragging === 'function' ? options.while_dragging : null);
				visData.hover_node = (typeof options.hover_node === 'function' ? options.hover_node : null);
				visData.blur_node = (typeof options.blur_node === 'function' ? options.blur_node : null);
			}

			obtainVisConfig();
			visData.network = new vis.Network(container, visData.data, visData.options);
			defineVisEvents();
		},
		getNodes: function(nodeId) {
			if(nodeId == null) {
				return visData.nodes;
			} else {
				return visData.nodes._data[nodeId];
			}
		},
		getEdges: function() {
			return visData.edges;
		},
		getNetwork: function() {
			return visData.network;
		},
		getScale: function() {
			return visData.network.getScale();
		},
		newNode: newNode,
		buildFromJson: function(json, context){
			var label = "";
			var object = cloneObject(json);
			delete object.hijos;
			if(context == $rootScope.TYPE_CONFIGURATION) {
				var tipoInstalacion = object.tipoInstalacion;
				label = tipoInstalacion.familiaInstalacion.nombre + "\n" + tipoInstalacion.marca + "\n" + tipoInstalacion.modelo;
			} else {
				var familiaInstalacion = object.familiaInstalacion;
				label = familiaInstalacion.nombre;
			}
			var node = {
				id: 0,
				label: label,
				depth: 0,
				type: $rootScope.INSTALLATION_TYPE,
				object : object,
				shape: visData.shapes.installation
			}
			newNode(node.id, null, node);
			if(json.hijos) {
				for(var i=0; i<json.hijos.length; i++) {
					buildElementsFromJson(json.hijos[i], node.id, node.depth+1);
				}
			}
			visData.network.fit();
			visData.network.redraw();
			return visData.nodes.length;
		},
		deleteNode: function(nodeId) {
			var edges = visData.network.getConnectedEdges(nodeId);
			for(var j=0; j<edges.length; j++) {
				visData.edges.remove(edges[j]);
			}
			visData.nodes.remove(nodeId);
			visData.network.redraw();
		},
		visToDTO: function() {
			var jsonDTO = {};
			jsonDTO = constructDTO(visData.nodes._data[0].id);
			return jsonDTO;
		},
		set: function(name, param) {
			switch (name) {
				case 'event_click':
					visData.event_click = (typeof param === 'function' ? param : null);
				break;
				case 'event_zoom':
					visData.event_zoom = (typeof param === 'function' ? param : null);
				break;
				case 'after_drawing':
					visData.after_drawing = (typeof param === 'function' ? param : null);
				break;
				case 'while_dragging':
					visData.while_dragging = (typeof param === 'function' ? param : null);
				break;
				case 'hover_node':
					visData.hover_node = (typeof param === 'function' ? param : null);
				break;
				case 'blur_node':
					visData.blur_node = (typeof param === 'function' ? param : null);
				break;
				case 'node_width':
					visData.network.setOptions({
						nodes: {
							heightConstraint: { minimum: (typeof param == 'number' ? param : 72) }}
					});
				break;
				case 'node_height':
					visData.network.setOptions({
						nodes: {
							widthConstraint: {
								minimum: (typeof param == 'number' ? param : 72),
								maximum: (typeof param == 'number' ? param : 72)
							}
						}
					});
				break;
				default:
				break;
			}
		}
	}


	function obtainVisConfig() {
		$.ajax({
			url: '../js/ctc-Script/providers/visOptions.json',
			async: false,
			dataType: 'json',
			success: function (response) {
				visData.options = response.options;
			}
		});
	}


	function constructDTO (nodeID) {
		var node = visData.nodes._data[nodeID];
		var object = node.object;
		var childNodes = visData.network.getConnectedNodes(node.id, 'to');
		if(childNodes.length > 0) {
			object['hijos'] = [];
			for(var i=0; i < childNodes.length; i++) {
				object.hijos.push(constructDTO(childNodes[i]));
			}
		}
		return object;
	}


	function buildElementsFromJson(json, parentId, depth) {
		var object = cloneObject(json);
		delete object.hijos;
		var node = {
			id: visData.nodes.length,
			label: json.familiaElemento.nombre,
			depth: depth,
			type: $rootScope.ELEMENT_TYPE,
			object : object,
			shape: visData.shapes.element
		}
		newNode(node.id, parentId, node);
		if(json.hijos) {
			for(var i=0; i<json.hijos.length; i++) {
				buildElementsFromJson(json.hijos[i], node.id, depth+1);
			}
		}
	}


	function cloneObject(object) {
		var clone = {};
		if(typeof object  == 'object') {
			for(var key in object) {
				if(typeof object[key]  == 'object' && object[key] != null) {
					clone[key] = cloneObject(object[key]);
				} else {
					clone[key] = object[key];
				}
			}
		}
		return clone;
	}


	function newNode(nodeID, parentID, params) {
		var newNode = {
			id: nodeID,
			label: "new node",
			shape: 'box'
		}
		if(params) {
			for(var key in params) {
				newNode[key] = params[key];
			}
		}

		visData.nodes.add(newNode);
		if(parentID != null) {
			visData.edges.add({
				from: parentID,
				to: nodeID
			});
		}
		visData.network.redraw();
	}



	function defineVisEvents() {
		visData.network.on('click', function(params) {
			if(typeof visData.event_click === 'function') {
				visData.event_click(params);
			}
			visData.network.redraw();
		});

		// ZOOM EVENT
		visData.network.on('zoom', function(params) {
			if(typeof visData.event_zoom === 'function') {
				visData.event_zoom(params);
			}
			visData.network.redraw();
		});

		// AFTER_DRAWING EVENT
		// every hover over a node makes it redraw, find condition to avoid repositioning our modal
		visData.network.on('afterDrawing', function(params) {
			if(typeof visData.after_drawing === 'function') {
				visData.after_drawing(params);
			}
		});

		// DRAGGING EVENT
		visData.network.on('dragging', function(params) {
			if(typeof visData.while_dragging === 'function') {
				visData.while_dragging(params);
			}
		});

		// HOVER_NODE EVENT
		visData.network.on('hoverNode', function(params) {
			if(typeof visData.hover_node === 'function') {
				visData.hover_node(params);
			}
		});

		// BLUR_NODE EVENT
		visData.network.on('blurNode', function(params) {
			if(typeof visData.blur_node === 'function') {
				visData.blur_node(params);
			}
		});
	} 

	return object;

});