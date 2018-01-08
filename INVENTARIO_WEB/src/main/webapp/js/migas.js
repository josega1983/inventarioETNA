(function() {
  function Migas(clavePersistencia, persistencia) {
    this.clavePersistencia = clavePersistencia;
    this.migas = JSON.parse(sessionStorage[clavePersistencia] || '[]');
    this.persistencia = persistencia.persiste;
  }

  Migas.prototype.nuevaMiga = function(datos) {
    this.migas.push(this.persistencia(datos));
    this.guarda();
  };

  Migas.prototype.quitaMiga = function(datos) {
    this.migas = _.without(this.migas, this.persistencia(datos));
    this.guarda();
  };

  Migas.prototype.guarda = function() {
    sessionStorage[this.clavePersistencia] = JSON.stringify(this.migas);
  };

  function MigasPersistencia() {
  }

  MigasPersistencia.prototype.persiste = function(datos) {
    return datos.html();
  };

  var migas = new Migas('migas', new MigasPersistencia());

  $(document.body).on('click', '.miga-menu-mostrar', function() {
    $(this).toggleClass('is-abierto');
    return false;
  });

  $(document.body).on('click', '.miga-fijar', function() {
    var $miga = $(this).closest('.miga'),
        copia = copiaMiga($miga),
        $migas = $miga.parent();

    $migas.append(copia.addClass('miga-fija'));

    migas.nuevaMiga(copia);
  });

  $(document.body).on('click', '.miga-quitar', function() {
    var $miga = $(this).closest('.miga');

    migas.quitaMiga($miga);

    $miga.remove();
  });

  function copiaMiga($miga) {
    var $copia = $miga.clone();

    $copia.addClass('miga-fija');
    $copia.find('.miga-menu-mostrar,.miga-menu').remove();
    $copia.append('<a class="miga-quitar">Quitar</a>');
    return $copia;
  }

  function ponMigasGuardadas() {
    _.each(migas.migas, function(miga) {
      $('.migas').append($('<div class="miga miga-fija">' + miga + '</div>'));
    });
  }

  ponMigasGuardadas();
}());