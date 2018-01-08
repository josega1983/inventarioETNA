$(document.body).on('click', '.busquedaAvanzada-mostrar', function() {
  var $this = $(this),
      $buscador = $this.closest('.busquedaAvanzada'),
      visible = $buscador.attr('aria-hidden');

  $buscador.attr('aria-hidden', visible !== 'true');
});

$(document.body).on('click', '.busquedaAvanzada-campo > a', function() {
  var $campo = $(this),
      campo = $campo.data('campo'),
      activo = $campo.hasClass('is-activo'),
      $input;

  if (activo) {
    $('.buscador-formulario  [name="' + campo + '"]').parent().remove();
  } else {
    $input = generaInput($campo);
    $('.buscador-formulario .formulario-contenido').append($input);
  }

  $campo.toggleClass('is-activo');
});

$(document.body).on('click', '.busquedaAvanzada-atajos > a', function() {
  var accion = $(this).data('accion');
  switch (accion) {
    case 'marcar':
      $('.busquedaAvanzada-campo > a:not(.is-activo)').click();
      break;
    case 'desmarcar':
      $('.busquedaAvanzada-campo > a.is-activo').click();
      break;
    case 'invertir':
      $('.busquedaAvanzada-campo > a').click();
      break;
  }
});

function marcaTodos() {

}

function generaInput(campo) {
  var ancho = campo.data('ancho') || 50,
      elemento = campo.data('elemento') || 'input',
      tipo = campo.data('tipo') || 'text',
      etiqueta = campo.text(),
      $label = $('<label class="col-' + ancho + '">' + etiqueta + '</label>'),
      $input = $('<' + elemento + ' name="' + campo.data('campo') + '">');

  if (elemento === 'input') {
    $input.attr('type', tipo);
  } else if (elemento === 'select') {
    campo.data('opciones').forEach(function(opcion) {
      var $option = $('<option value="' + opcion.valor + '">' + opcion.etiqueta + '</option>');
      $input.append($option);
    });
  }

  $label.append($input);

  return $label;
}