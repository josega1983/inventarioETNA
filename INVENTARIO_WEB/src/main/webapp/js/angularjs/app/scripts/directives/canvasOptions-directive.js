
angular.module('inventarioApp')
.directive('canvasOptionsDirective', function () {
	return {
		restrict: 'E',
		scope: {
			isFullscreen: '=fullscreenFlag',
			onFullscreen: '&fullscreenFn',
			zoomIn: '&zoomInFn',
			zoomOut: '&zoomOutFn',
			onCenter: '&centerFn'
		},
		templateUrl: 'views/canvasOptionsDirective.html',
		link: function (scope, elem, attrs) {
		}
	};
});