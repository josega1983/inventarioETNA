$('.campoMultiple').each(function() {
  var $this = $(this),
      $input = $this.find('input,select,textarea'),
      cuenta = $('[name="' + $input.attr('name') + '"').length,
      $botones = $('<div class="campoMultiple-botones"></div>');

  if (cuenta === 1) {
    $this.addClass('is-unico');
  }

  $botones.append('<button type="button" href="javascript:void(0)" class="campoMultiple-mas">+</button>');
  $botones.append('<button type="button" href="javascript:void(0)" class="campoMultiple-menos">-</button>');
  $this.append($botones);
});

$(document.body).on('click', '.campoMultiple-mas', function() {
  var $this = $(this),
      $label = $this.closest('.campoMultiple'),
      $input = $label.find('input,select,textarea'),
      $todosLosDelCampo = $('[name="' + $input.attr('name') + '"]'),
      maximo = parseInt($label.data('maximo') || 10000, 10),
      $copia;

  if (maximo > $todosLosDelCampo.length) {
    $label.removeClass('is-unico');
    $copia = $label.clone();

    $copia.find('input,textarea,select').val('');

    $label.after($copia);

    if (maximo === $todosLosDelCampo.length + 1) {
      $todosLosDelCampo.add($copia).closest('.campoMultiple').addClass('is-maximo');
    }
  }
});

$(document.body).on('click', '.campoMultiple-menos', function() {
  var $this = $(this),
      $label = $this.closest('.campoMultiple'),
      $input = $label.find('input,select,textarea'),
      $todosLosDelCampo = $('[name="' + $input.attr('name') + '"]');

  if ($todosLosDelCampo.length > 1) {
    $label.remove();
    $todosLosDelCampo.closest('.campoMultiple').removeClass('is-maximo');
  }

  if ($todosLosDelCampo.length === 2) {
    $todosLosDelCampo.closest('.campoMultiple').addClass('is-unico');
  }
});

$('label.date input').datetimepicker({
  locale: 'es',
  format: 'DD/MM/YYYY HH:mm'
});

$(document.body).on('change', 'input[type="file"]', function() {
  var $this = $(this),
      $padre = $this.parent(),
      $pendiente = $('<div class="adjuntos-pendiente"><button type="button" class="adjuntos-pendiente-quitar">Quitar</button></div>'),
      copia = $this.clone(),
      $pendientes = $this.closest('.adjuntos-nuevo').prev();

  $pendientes.append($pendiente).show();
  $pendiente.prepend($this);
  var trozos = $this.val().split('\\');
  $pendiente.append(trozos.pop());

  $padre.append(copia);
});

$(document.body).on('click', '.adjuntos-pendiente-quitar', function() {
  var $this = $(this),
      $contenedor = $this.closest('.adjuntos-pendientes'),
      hijos = $contenedor.find('.adjuntos-pendiente').length;

  $this.parent().remove();
  if (hijos === 1) {
    $contenedor.hide()
  }
});