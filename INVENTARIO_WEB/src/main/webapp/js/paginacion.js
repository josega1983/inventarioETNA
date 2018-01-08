$(document.body).off('cargar-mas');


$(document.body).on('cargar-mas', function(e, datos) {
  var cb = datos.cb;
  var newPage = parseInt($("#actualPage").val()) + 1;
  var action =$("#actionURL").val()
  $("#actualPage").val(newPage);
  $.ajax({
		url: action,
		type: "POST",
		data: {
			actualPage: $("#actualPage").val(),
			estadoFiltro: $("#estadoFiltro").val()
		},
		async: false,
		success: function(html){
			// validarSesionExpirada(html);

			$('#items').append($(html).find('#items').html());
		    cb($(html).find('#hayMas').val() === 'true');
		}
	});
});


(function() {
  $('.spinner-carga').each(function() {
    var cargando = false,
        spinner = $(this),
        offsetParent = spinner.closest('.popup-contenido');

    if (offsetParent.length === 0) {
      offsetParent = $('.contenido');//$(window);
    }

    function spinnerVisible() {
      return spinner.is(":visible") && spinner.offset().top - $(window).scrollTop() < window.innerHeight;
    }

    function cargaDatos() {
      $(document.body).trigger('cargar-mas', {
        cb: function(hayMas) {
          cargando = false;
          if (!hayMas) {
            spinner.hide();
            offsetParent.off('scroll load');
          }
        }
      });
    }

    offsetParent.on('scroll', function() {
      checkPagination();
    });

    checkPagination();

    function checkPagination() {
      console.log('comprobando');
      if (spinnerVisible() && !cargando) {
        cargando = true;
        cargaDatos();
      }
    }
  });
}());