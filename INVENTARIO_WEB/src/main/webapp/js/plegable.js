$('.plegable.is-plegado').each(function() {
  var $this = $(this),
      $contenido = $this.find('.plegable-contenido');

  if ($contenido.length === 0) {
    $this.addClass('is-vacio');
  }

  $contenido.hide();
});

$(document.body).on('click', '.plegable-titulo', function() {
  var $titulo = $(this),
      $plegable = $titulo.closest('.plegable'),
      $contenido = $titulo.next();

  $plegable.toggleClass('is-plegado');
  $contenido.slideToggle();
});