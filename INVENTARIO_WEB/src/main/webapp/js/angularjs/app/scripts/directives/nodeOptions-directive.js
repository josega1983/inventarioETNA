
angular.module('inventarioApp')
.directive('nodeOptionsDirective', function () {
	return {
		restrict: 'E',
		scope: {
			info: '=modalInfo',
			onEdit: '&editFn',
			onClone: '&cloneFn',
			onDelete: '&deleteFn',
			onClose: '&closeFn'
		},
		templateUrl: 'views/nodeOptionsDirective.html',
		link: function (scope, elem, attrs) {
		}
	};
});