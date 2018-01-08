var filas = '.listado-tabla tbody tr';
var cabeceras = '.listado-tabla thead th';
var menuOpciones = '.listado-opciones-popup';
var tabla = '.listado-tabla';



/******************************/
/*      row element menu      */
/******************************/


/*
* Muestra una opcion del menu opciones
*/
function mostrarCampoMenu(parameter) {
	if(parameter.length > 1) {
		$("#" + parameter).show();
	}
}


/*
* Esconde una opcion del menu opciones
*/
function esconderCampoMenu(parameter) {
	if(parameter.length > 1) {
		$("#" + parameter).hide();
	}
}


/*
* Comprueba si hay una fila activa
*/
function filaActiva() {
	return $(filas).filter('.is-activa');
}


/*
* Oculta todas las opciones del menu opciones de los items del listaod
*/
function esconderCamposVisibles() {
	var elements = $(menuOpciones).find(".listado-opcion");
	for(var i=0; i<elements.length; i++) {
		esconderCampoMenu(elements[i].id);
	}
}


/*
* Esconde el menu opciones del item de la tabla seleccionado en ese momento
*/
function esconderMenuOpciones() {
	$(tabla).removeClass('is-fila-activa');
	$(filas).closest('.is-activa').removeClass('is-activa');
	$(menuOpciones).fadeOut();
	esconderCamposVisibles();
}


/*
* 1. Comprobar si el menu lateral principal esta desplegado, en tal caso esconderlo
* 2. Mostrar el menu de opciones
*/
function mostrarMenuOpciones(filaAfectada) {
	if ($(document.body).hasClass('menu-lateral-activo')) {
		var initRowOffsetTop = filaAfectada.offset().top;
		var initRowWindowTop = initRowOffsetTop - $(window).scrollTop();
		closeAllMenus(); // app.js
		setTimeout(calcularPosiciones, 500); // 500ms side menu animation transition time
	} else {
		closeAllMenus(); // app.js
		calcularPosiciones();
	}
	$("#idFilaSeleccionada").val(filaAfectada.find(".columnaIdentificativa").html());


	function calcularPosiciones() {
		var columnas = $(tabla).data('columnas');
		if (!columnas) {
			columnas = Math.max(1, $(cabeceras).length - 2);
			$(tabla).attr('data-columnas', columnas);
		}
		var corteAzul = filaAfectada.find('td:nth-child(' + (columnas + 1) + ')');

		if(initRowOffsetTop != undefined) {
	 		var actualRowOffsetTop = filaAfectada.offset().top;
			var newScrollTop = actualRowOffsetTop - initRowWindowTop;
			$('html, body').animate({
				scrollTop: newScrollTop
			}, 0, function(){
				mostrar(corteAzul);
			});
		}else{
			mostrar(corteAzul);
		}
	}

	function mostrar(corteAzul) {
		$(document.body).trigger('fila-activa');
		$(filas).removeClass('is-activa');
		$(tabla).addClass('is-fila-activa');
		filaAfectada.addClass('is-activa');

		var menuOffsetTop = filaAfectada.position().top + filaAfectada.outerHeight();
		var listOffsetBottom = $(tabla).position().top + $(tabla).height();
		var menuOffsetBottom = menuOffsetTop + $(menuOpciones).outerHeight();
		var diffMenuOffsetTop = 0;

		if(listOffsetBottom < menuOffsetBottom && menuOffsetBottom > window.innerHeight) {
			diffMenuOffsetTop = $(menuOpciones).outerHeight() + filaAfectada.outerHeight();
			$('.listado-opciones-popup').addClass('top-to-bottom');
		} else {
			$('.listado-opciones-popup').removeClass('top-to-bottom');
		}

		$(menuOpciones).css('top', menuOffsetTop - diffMenuOffsetTop)
					   .css('left', corteAzul.position().left)
					   .css('right', 0)
					   .fadeIn();
	}

}

/*
* 1. Esconde todas las opciones del menu opciones.
* 2. Compruba las opciones que deberia llevar el menu opciones de cada item
* del listado.
*/
function cargarMenuOpciones() {
	if($(this).find("#estadoMenu").val() != undefined && $(this).find("#estadoMenu").val() != '') {
		esconderCamposVisibles();
		mostrarMenuOpciones($(this));
		var estadoMenu =  $(this).find("#estadoMenu").val().split("|");
		jQuery.each( estadoMenu, function( i, val ) {
		  mostrarCampoMenu(val);
		});
		mostrarCampoMenu("menu_cerrar");
	}
}





/******************************/
/*      Event definition      */
/******************************/
$(document).ready(function () {

	$(document).on('click', menuOpciones + ' a[data-accion]', function() {
		var $this = $(this),
			accion = $this.data('accion');
		$(document.body).trigger(accion, [filaActiva().data('id')]);
	});

	// opcion del menu para cerrarlo
	$(document).on('click', menuOpciones + ' .listado-opcion-cerrar', esconderMenuOpciones);

	// REFACTORIZAR:
	// NO SE SI ESTE EVENTO ES REALMENTE NECESARIO PARA EL CORRECTO FUNCIONAMIENTO
	$(document.body).on('menu-abierto', esconderMenuOpciones);

		// cargar y abrir el menu opciones
	$(document).on('click', '.listado-tabla tbody tr', cargarMenuOpciones);

	// opcion eliminar
	$(menuOpciones).on('click', '.opcion-eliminar', function() {
		var $boton = $(this);

		swal({
			title: 'Atención',
			text: $boton.data('mensaje') || "¿Estás seguro de eliminar este objeto?",
			type: $boton.data('tipo') || 'warning',
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "OK",
			closeOnConfirm: false
			//showLoaderOnConfirm: true
		}, function(aceptado) {
			if (aceptado) {
				$.ajax({
					url:  $boton.data('action'),
					type: "POST",
					async: false,
					data: {
						idFilaSeleccionada:  $("#idFilaSeleccionada").val()
					},
					success: function(result){
						validarSesionExpirada(result);

						var respuesta = JSON.parse(result);

						if(respuesta.estado == "OK") {
							swal("Borrado",respuesta.mensaje,"success");

							//Ocultamos el menu que se queda mal, si queda visible.
							var $filas = $('.listado').find('.listado-tabla').find('tbody tr');
							$filas.removeClass('is-activa');
							$('.listado').find('.listado-tabla').removeClass('is-fila-activa');
							$('.listado').find('.listado-opciones-popup').fadeOut();

							//Borramos por javascript el elemento para no tener que llamar a list o search de nuevo
							$("#rowId_" + $("#idFilaSeleccionada").val()).remove();
							if(document.querySelectorAll("[id*='rowId_']").length == 0){
								$(".listado-tabla").append("<label class='col-100'><span class='etiqueta'>NO HAY DATOS</span></label>");
							}
						}
						else {
							swal("Error",respuesta.mensaje,"error");
						};
					},
					error: function (xhr, ajaxOptions, thrownError) {
						swal("Error","Error en la eliminación","error");
					}
				});
			}
		})
		
		return false;
	});
});





/**************************************/
/*      ordering list by columns      */
/**************************************/
$('.listado').each(function() {
	var $listado = $(this),
		$tabla = $listado.find('.listado-tabla'),
		$opciones = $listado.find('.listado-opciones-popup'),
		$cerrar = $opciones.find('.listado-opcion-cerrar'),
		$cabeceras = $tabla.find('thead th');


	$cabeceras.on('click', function() {
		var $this = $(this),
			orden = ordenDeCelda($this);

		$cabeceras.not($this).removeClass('orden-ascendente');
		$cabeceras.not($this).removeClass('orden-descendente');

		orden = orden === 0 ? 1 : -orden;

		$(document.body).trigger('ordenar', {
			tabla: $this.closest('table'),
			columna: $this.index(),
			sentido: orden
		});

		actualizaOrdenDeCelda($this, orden);
	});


	function ordenDeCelda($celda) {
		if ($celda.hasClass('orden-ascendente')) {
			return 1;
		} else if ($celda.hasClass('orden-descendente')) {
			return -1;
		} else {
			return 0;
		}
	}

	function actualizaOrdenDeCelda($celda, orden) {
		$celda.removeClass('orden-ascendente');
		$celda.removeClass('orden-descendente');
		if (orden > 0) {
			$celda.addClass('orden-ascendente');
		} else if (orden < 0) {
			$celda.addClass('orden-descendente');
		}
	}
});