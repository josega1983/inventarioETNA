


angular.module('inventarioApp')
.directive('draggableHandler', function ($compile) {
	return {
		restrict: 'A',
		scope: {
			resize: '&resizeHandler'
		},
		link: function (scope, element) {
			element.draggable({
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

		}
	};
});