$(document.body).off('cargar-mas-Popup');


$(document.body).on('cargar-mas-Popup', function(e, datos) {
  var cb = datos.cb;
  var newPage = parseInt($("#actualPagePopup").val()) + 1;
  var action =$("#actionPopupURL").val();
  $("#actualPagePopup").val(newPage);
  $.ajax({
		url: action,
		type: "POST",
		data: {
			actualPage: $("#actualPagePopup").val(),
			estadoFiltro: $("#estadoFiltro").val()
		},
		async: false,
		success: function(html){
			validarSesionExpirada(html);
			
			var domElement = document.createElement('table');
			$(domElement).html(html);
			$('#itemsPopup').append($(domElement).find('#itemsPopup').html());
		    $("#hayMasPopup").val($(domElement).find('#hayMasPopup').val());
			cb($(domElement).find('#hayMasPopup').val() === 'true');
		}
	});
});


(function() {
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
            offsetParent.off('scroll load');
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
}());