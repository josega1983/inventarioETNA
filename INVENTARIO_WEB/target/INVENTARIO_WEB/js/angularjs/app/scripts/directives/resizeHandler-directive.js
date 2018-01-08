

angular.module('inventarioApp')
.directive('resizeHandler', function ($compile) {
	return {
		restrict: 'A',
		scope: {
			resize: '&resizeHandler'
		},
		link: function (scope, element) {
			scope.getWidth = function() {
				return $(element[0]).clientWidth;
			}


			angular.element(document).ready(function() {
				scope.$watch(scope.getWidth, function(width) {
					scope.resize();
				});
			});

		}
	};
});