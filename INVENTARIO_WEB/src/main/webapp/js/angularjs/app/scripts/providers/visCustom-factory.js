

angular.module('inventarioApp')
.factory('visCustom', function() {


	var visData = this;
	visData.network;
	visData.nodes = new vis.DataSet();
	visData.edges = new vis.DataSet();

	visData.data = {
		nodes: visData.nodes,
		edges: visData.edges
	};

	// VIS JS canvas configuration JSON
	visData.options = {
		layout: {
			hierarchical: {
				direction: "UD",
				sortMethod: "directed",
				nodeSpacing: 150
			}
		},
		interaction: {
			dragNodes :false,
			hover:true
		},
		manipulation: {
			addNode: true,
			addEdge: true,
			editEdge: true,
			deleteNode: true,
			deleteEdge: true
		},
		physics: {
			enabled: false
		},
		nodes: {
			heightConstraint: {
				minimum: 72
			},
			widthConstraint: {
				minimum: 72,
				maximum: 72
			},
			color: {
				border: '#009ee3',
				background: '#009ee3',
				highlight: {
					border: '#2B7CE9',
					background: '#D2E5FF'
				},
				hover: {
					border: '#2B7CE9',
					background: '#D2E5FF'
				}
			},
			font: {
				size: 15,
				color: 'white',
				face: 'icomoon'
			},
			shadow:{
				enabled: true,
				color: 'rgba(128, 128, 128,0.75)',
				size:2,
				x:2,
				y:2
			}
		},
		edges: {
			shadow:{
				enabled: true,
				color: 'rgba(128, 128, 128,0.75)',
				size:2,
				x:2,
				y:2
			},
			chosen: false
		}
	};

	visData.event_click;
	visData.event_zoom;
	visData.after_drawing;
	visData.while_dragging;
	visData.hover_node;
	visData.blur_node;



	var object = {
		defineVisCanvas: function(container, options = null) {
			if(options != null) {
				visData.event_click = (typeof options.event_click === 'function' ? options.event_click : null);
				visData.event_zoom = (typeof options.event_zoom === 'function' ? options.event_zoom : null);
				visData.after_drawing = (typeof options.after_drawing === 'function' ? options.after_drawing : null);
				visData.while_dragging = (typeof options.while_dragging === 'function' ? options.while_dragging : null);
				visData.hover_node = (typeof options.hover_node === 'function' ? options.hover_node : null);
				visData.blur_node = (typeof options.blur_node === 'function' ? options.blur_node : null);
			}
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
		newNode: function(nodeID, parentID, params) {
			var newNode = {
				id: nodeID,
				label: "new node",
				shape: 'circle',
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
			constructDTO(jsonDTO, visData.nodes_data[0].id); // first element is the fisrt node
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


	function constructDTO (json, nodeID) {
		var node = visData.nodes._data[0];
		console.log(node);
		var childNodes = visData.network.getConnectedNodes(node.id, 'to');
		console.log('conected nodes', childNodes);
		for(var i=0; i < childNodes.length; i++) {

		}
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