
angular.module('inventarioApp')
.directive("owlCarousel", function() {
	return {
		restrict: 'E',
		transclude: false,
		link: function (scope, element) {
			scope.owlOptions = {
				items: 8,
				margin: 10,
				dots: false,
				nav: true,
				navText: ['',''],
				responsiveClass: true,
				responsive: {
					0: {
						items: 1
					},
					599: {
						items: 2
					},
					724: {
						items: 3
					},
					866: {
						items: 4
					},
					1008: {
						items: 5
					},
					1150: {
						items: 6
					},
					1292: {
						items: 7
					},
					1434: {
						items: 8
					}
				},
				onTranslated: scope.onCarouselTranslatedCallback
			};

			$(element).owlCarousel(scope.owlOptions);


			scope.initCarousel = function(element) {
				var currentItem = $(element).find(".owl-item.active:last-child" );
				var currentIndex = currentItem.index() + 2;
				$(element).find('.owl-stage-outer').remove();
				$(element).trigger('destroy.owl.carousel');
				$(element).html($(element).find('.owl-stage-outer').html()).removeClass('owl-loaded');
				$(element).owlCarousel(scope.owlOptions);
				$(element).trigger('to.owl.carousel', [currentIndex,0]);
				$(element).trigger('refresh.owl.carousel');
			};

		}
	};
})
.directive('owlCarouselItem', function() {
	return {
		restrict: 'A',
		transclude: false,
		link: function(scope, element) {
			if(scope.$last) {
				scope.initCarousel(element.parent());
			}
		}
	};
});