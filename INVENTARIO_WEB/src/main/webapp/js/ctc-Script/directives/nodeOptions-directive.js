
angular.module('inventarioApp')
.directive('nodeOptionsDirective', function () {
	return {
		restrict: 'E',
		scope: {
			info: '=modalInfo',
			onEdit: '&editFn',
			onClone: '&cloneFn',
			onDelete: '&deleteFn',
			onClose: '&closeFn',
			onCopy: '&copyFn',
			onPaste: '&pasteFn'
		},
		templateUrl: '../jsp/angularCommon/nodeOptionsDirective.html',
		link: function (scope, elem, attrs) {
		}
	};
});