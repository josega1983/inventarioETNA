

angular.module('inventarioApp')
.factory('visTempCustom', function($rootScope, $http, $location) {


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



	var object = {
		defineVisCanvas: function(container) {
			visData.nodes = new vis.DataSet();
			visData.edges = new vis.DataSet();
			visData.data = {
				nodes: visData.nodes,
				edges: visData.edges
			};
			obtainVisConfig();
			visData.network = new vis.Network(container, visData.data, visData.options);
			visData.network.redraw();
		},
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

	return object;

});