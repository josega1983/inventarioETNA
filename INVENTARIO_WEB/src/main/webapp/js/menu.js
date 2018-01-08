

/****************************/
/*      Menu principal      */
/****************************/


$(document).on('click', '.menu-desplegar', function(){
	closeAllMenus(); // app.js
	esconderMenuOpciones(); // listados.js
	$('body').addClass('menu-lateral-activo');
});
$(document).on('click', '.menu-plegar', function(){
	$('body').removeClass('menu-lateral-activo');
});



$(document).on('click', '.menu .menu-desplegarSubmenu', function() {
	var $desplegar = $(this);

	$desplegar.next('a').toggleClass('is-activo');
	$desplegar.toggleClass('is-abierto');

	var $menuItem = $desplegar.parent();
	if($menuItem.hasClass('activo') && !$menuItem.hasClass('submenu-item')) {
		$menuItem.removeClass('activo');
	} else {
		if($desplegar.next('a').hasClass('is-activo')) {
			if($menuItem.find('.menu-item.activo').length) {
				$menuItem.addClass('activo');
			}
		}
	}

});



/***************************************************/
/*      submenu cuando menu principal plegado      */
/***************************************************/
/**
* Change all the functionality for a submenu withing the menus context with a width = 0,
* play with given width depending if menu is shrinked or not. Menu should be fixed or absolute 
* so it wont reduce even more the content container.
*/


$(document).on('click', '.menu .menu-item.submenu-item', function() {
	if(!$('body').hasClass('menu-lateral-activo')) {
		$(this).addClass('blue-background');
		var title = $(this).find('.menu-opcion').html();
		var offsetPositionTop = $('.menu').offset().top;
		var offsetPositionRight = $(this).offset().left + $('.menu').outerWidth();

		$('.submenu-modal').find('.submenu-title').html(title);
		$('.submenu-modal').css('top', offsetPositionTop)
						   .css('left', offsetPositionRight)
						   .css('display', 'inline-block')
						   .fadeIn();
	}
});
$(document).on('mouseenter', '.submenu-modal', function(event) {
	$('.submenu-modal').mouseleave(function(event) {
		$('.submenu-modal').fadeOut();
		$('.menu-item.blue-background').removeClass('blue-background');
		$('.submenu-modal').off('mouseout');
	});
});


$(document).on('click', '.submenu-modal .menu-desplegarSubmenu', function() {
	var $desplegar = $(this);


	var $menuItem = $desplegar.parent();
	if($menuItem.hasClass('activo') && !$menuItem.hasClass('submenu-item')) {
		$menuItem.removeClass('activo');
	} else {
		if($desplegar.next('a').hasClass('is-activo')) {
			if($menuItem.find('.menu-item.activo').length) {
				$menuItem.addClass('activo');
			}
		}
	}
	$desplegar.next('a').toggleClass('is-activo');
	$desplegar.toggleClass('is-abierto');
});




/********************************************/
/*      Menu sperior lateral izquierdo      */
/********************************************/


function cierraMenu() {
	$(document.body).removeClass('buscador-activo');
	$(document.body).removeClass('menu-desplegable-activo');
	$('.menu-desplegable-caja .opcion-buscar').off('click');
}

$(document).on('click', '.menu-enlace', function() {
	$(document.body).trigger('menu-abierto');
	$(document.body).addClass('menu-desplegable-activo');
	$('.menu-desplegable-caja .opcion-buscar').on('click', function() {
		closeAllMenus(); // app.js
		$(document.body).addClass('buscador-activo menu-desplegable-activo');
	});
});

$(document).on('click', '.menu-cerrar', cierraMenu);
