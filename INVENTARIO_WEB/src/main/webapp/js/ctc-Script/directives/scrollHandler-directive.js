
angular.module('inventarioApp')
.directive('scrollVerticalHandler', function ($compile) {
	return {
		restrict: 'A',
		scope: {
			callback: '&scrollVerticalHandler'
		},
		link: function (scope, element) {
			element.on('scroll', function (e) {
				var loader = angular.element('.loader2');
				var content = angular.element('.draggable-elements');
				if(loader.length > 0 && loader.offset().top - content.scrollTop() < content.innerHeight()) {
					scope.callback();
				}
			});
		}
	};
});