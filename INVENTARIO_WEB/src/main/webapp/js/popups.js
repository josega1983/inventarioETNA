window.popups = {
	popup: [],
	cierraPopup: function() {
		$('.popup').fadeOut();
		$(document.body).removeClass('con-popup');

		// Eliminamos el contenido para que las variables no se solapen si estamos metiendo
		//  varios combos popus en la misma pagina
		$('.popup-contenido').remove();
	},
	abrePopup: function(id) {
		this.popup = $('#' + id);
		if (this.popup.length) {
			$(document.body).addClass('con-popup');
			this.popup.fadeIn();

			this.popup.find('.popup-contenido').on('scroll', this.ajustaCabecera.bind(this));
		}
	},
	ajustaCabecera: function() {
		var $thead = this.popup.find('thead');
		console.log($thead.position());
	}
};

$(document).on('click', '.popup-cerrar', function(e) {
	e.stopPropagation();
	e.preventDefault();
	popups.cierraPopup();
});
$(document).on('click', '.popup', function(e) {
	e.stopPropagation();
	e.preventDefault();
	if (e.target.classList.contains('popup')) {
		popups.cierraPopup();
	}
});




$(document).on('click', '.edicion-buscar', function(event) {
	event.stopPropagation();

	var $this = $(this), action = $this.data('action'), popup = $this.data('popup');
	cargando.mostrar();
	setTimeout(function() {
		 $.ajax({
			url: action,
			type: "POST",
			async: false,
			data: {},
			success: function(html){
				validarSesionExpirada(html);
				var domElement = document.createElement('div');
				$(domElement).html(html);
				$("#" + popup).html($(domElement).find('.popup').first().html());
				cargando.ocultar();
				return popups.abrePopup(popup);
			}
		});
	},500);//Espera de un segundo para cargar
});


function searchPopup() {
	var formData = convertJSONForm($("#filtroPopupForm").serializeArray());
	var action = $("#accionFiltroPopup").val();
	
	$.ajax({
		url: action,
		type: "POST",
		async: false,
		data: formData,
		success: function(html){
			validarSesionExpirada(html);
			
			var domElement = document.createElement('table');
			$(domElement).html(html);
			$('#itemsPopup').html($(domElement).find('#itemsPopup').html());
			$("#hayMasPopup").val($(domElement).find('#hayMasPopup').val());
			$("#actualPagePopup").val(0);
		
			if($(domElement).find('#hayMasPopup').val() === 'false') {
				$('.spinner-carga-Popup').closest('.popup-contenido').off('scroll load');
				$('.spinner-carga-Popup').hide();
			}
			else {
				$('.spinner-carga-Popup').show();
				$('.spinner-carga-Popup').each(function() {
					var cargando = false,
						spinner = $(this),
						offsetParent = spinner.closest('.popup-contenido');

					if (offsetParent.length === 0) {
						offsetParent = $(window);
					}

					function spinnerVisible() {
						return spinner.is(":visible") && spinner.offset().top - $(window).scrollTop() < window.innerHeight;
					}

					function cargaDatos() {
						$(document.body).trigger('cargar-mas-Popup', {
							cb: function(hayMas) {
								cargando = false;
								if (!hayMas) {
										spinner.hide();
								}
							}
						});
					}

					offsetParent.on('scroll load', function() {
						if (spinnerVisible() && !cargando) {
							cargando = true;
							cargaDatos();
						}
					});
				});
			}
			cargando.ocultar();
		}
	});
}
	
	
function elementoSeleccionadoPopup(idFilaSeleccionada) {
	var action = $("#actionSelectURL").val();
	var idBotonSelectPopup = $("#idBotonSelectPopup").val();
	var idDetalleElementoSelect = $("#idDetalleElementoSelect").val();
	
	
	$.ajax({
		url: action,
		type: "POST",
		async: false,
		data: {
			idFilaSeleccionada: idFilaSeleccionada
		},
		success: function(html){
				validarSesionExpirada(html);
			
				var domElement = document.createElement('div');
				$(domElement).html(html);
				$("#" + idDetalleElementoSelect).html($(domElement).find('#' + idDetalleElementoSelect).html());
				$("#" + idBotonSelectPopup).text($(domElement).find('#textoBotonSelectPopup').val());
				return popups.cierraPopup();
		}
	});
}
