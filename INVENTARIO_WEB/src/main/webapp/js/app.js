
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
  }
};

$(document.body).on('click', '.popup-cerrar', function() {
  popups.cierraPopup();
});
$(document.body).on('click', '.popup', function(e) {
  if (e.target.classList.contains('popup')) {
    popups.cierraPopup();
  }
});
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
swal.setDefaults({
  confirmButtonColor: '#009ee3'
});

$(document.body).on('ordenar', function(e, datos) {
  var filas = datos.tabla.find('tbody tr').toArray();

  filas = _.sortBy(filas, function(tr) {
    var valor = $(tr).find('td:nth-child(' + (datos.columna + 1) + ')').text();

    return parseFloat(valor) || valor;
  });

  if (datos.sentido === -1) {
    filas.reverse();
  }

  $('tbody').html($(filas));
});

//////////////////////////////////////////// OPCIONES DE PLEGAR Y DESPLEGAR

$('.plegable.is-plegado, .tablaAnidada-interior > tbody').each(function() {
	var $this = $(this),
		$contenido = $this.find('> .plegable-contenido');

	if ($contenido.length === 0) {
		$this.addClass('is-vacio');
	}

	$contenido.hide();
});

$(document.body).on('click', '.plegable-titulo', function(e) {
	var $titulo = $(this),
		$plegable = $titulo.closest('.plegable'),
		$contenido = $titulo.nextAll('.plegable-contenido');

	if (e.target.nodeName !== 'INPUT' && e.target.nodeName !== 'SELECT' && e.target.nodeName !== 'OPTION' && e.target.nodeName !== 'TEXTAREA') {
		$plegable.toggleClass('is-plegado');
		$contenido.slideToggle();
	} else {
		return false;
	}
});


$(document.body).on('click', '.tablaAnidada-interior thead tr', function(e) {
	  var $titulo = $(this),
	      $plegable = $titulo.parent(),
	      $contenido = $plegable.nextAll('tbody').find('> .plegable-contenido');

	  if (e.target.nodeName !== 'INPUT' && e.target.nodeName !== 'SELECT' && e.target.nodeName !== 'TEXTAREA') {
	    $plegable.toggleClass('is-plegado');
	    $contenido.slideToggle();
	  } else {
	    return false;
	  }
});
/////////////////////////////////////////////////////////////////////////////

$(document.body).on('click', '.miga-menu-mostrar', function() {
	$(this).toggleClass('is-abierto');
    return false;
});

$(document.body).on('click', '.miga-fijar', function() {
	var $miga = $(this).closest('.miga');
	$miga.find('.miga-menu-mostrar').click();
	var data = null;
	var action = "";
	if($("#fijarMigaBoton").val() != null){
		data = $('#fijarMigaBoton').serialize();
		// $('#fijarMigaBoton').submit();
		document.formularioCrearSolicitud.action=$('#fijarMigaBoton')[0].name.split('action:')[1];
		document.formularioCrearSolicitud.submit();
	}
	else{
		action = "/ETNAJ/MI/migas/addNewMigaFija";
		$.ajax({
			  url: action,
			  type: "POST",
			  async: false,
			  data: {
			  },
			  success: function(result){

				  validarSesionExpirada(result);

				  var respuesta = JSON.parse(result);
				  if(respuesta.estado == "OK") {
					  $nuevaMigaFija = "<div class='miga miga-fija'><a class='miga-fija-accion' href='" + respuesta.accion + "'>"+ respuesta.etiqueta + "</a><a class='miga-quitar'>"+ respuesta.posicion + "</a></div>";
					  $miga.parent().append($nuevaMigaFija);
				  }
			  }
		});
	}


});

$(document.body).on('click', '.miga-quitar', function() {
	var $miga = $(this).closest('.miga');
	var idMigaEliminar = $(this).text();

	$.ajax({
		  url: "/ETNAJ/MI/migas/removeMigaFija",
		  type: "POST",
		  async: false,
		  data: {
			  idMigaSeleccionada: $(this).text()
		  },
		  success: function(result){
			  validarSesionExpirada(result);

			  var respuesta = JSON.parse(result);
			  if(respuesta.estado == "OK") {
				  $miga.parent().find(".miga-fija").each(function() {
					  var $migaAccion = $(this).find(".miga-fija-accion"),
					      $migaQuitar = $(this).find(".miga-quitar");

					  if(parseInt($migaQuitar.text()) > parseInt(idMigaEliminar)) {
						  var nuevoValor = parseInt($migaQuitar.text()) - 1;
						  $migaAccion.attr('href', $migaAccion.attr('href').replace("idMigaSeleccionada="+$migaQuitar.text(),"idMigaSeleccionada="+nuevoValor));
						  $migaQuitar.text(nuevoValor);
					  }
				  });



				  $miga.remove();
			  }
		  }
	});
});

$(document.body).on('click', '.edicion-buscar-nuevoElemento', function(event) {
	 event.stopPropagation();//Paramos el evento.
	 event.preventDefault();//Paramos el evento.

	 var $this = $(this), patron = $this.data('patron');
	 var $contenedor = $("#"+ $this.data('contenedorPosicion'));
	 var posicion = $this.attr("name");
	 if(patron != "") {
		 posicion = parseInt($this.attr("name").replace(patron, "")) - 1;
	 }

	 $contenedor.val(posicion);

	 $("#botonNuevoElemento").click();
});

	$('.campoMultiplePartidas').each(function() {
	  var $this = $(this),
	      rel = $this.attr('rel'),
	      cuenta = $('[rel="' + rel + '"]').length,
	      $botones = $('<div class="campoMultiplePartidas-botones"></div>');

	  if (cuenta === 1) {
	    $this.addClass('is-unico');
	  }

	  if($this.find(".campoMultiplePartidas-botones").length == 0) {
		  $botones.append('<button type="button" href="javascript:void(0)" class="campoMultiplePartidas-mas">+</button>');
		  $botones.append('<button type="button" href="javascript:void(0)" class="campoMultiplePartidas-menos">-</button>');
		  $this.append($botones);
	  }
	});

	$(document.body).on('click', '.campoMultiplePartidas-mas', function() {
	  var $this = $(this),
	      $label = $this.closest('.campoMultiplePartidas'),
	      rel = $label.attr('rel'),
	      $todosLosDelCampo = $('[rel="' + rel + '"]'),
	      maximo = parseInt($label.data('maximo') || 10000, 10),
	      $copia;

	  if (maximo > $todosLosDelCampo.length) {
		    $label.removeClass('is-unico');
		    $copia = $("#plantillaPartidaUnTipo").clone();
		    $copia.removeClass('noVisiblePartida');
		    $copia.attr('rel', "partidas");
		    $copia.removeClass('is-unico');
		    $copia.attr("id", "");

			var max = $('[rel="' + rel + '"]').find('input,textarea,select').toArray().reduce(function(acc, elemento) {
				 if(!isNaN(parseInt(/\d+$/.exec(elemento.getAttribute('name'))))) {
					 return Math.max(parseInt(/\d+$/.exec(elemento.getAttribute('name'))), acc);
				 }
				 else {
					 return acc;
				 }
		    }, 0) + 1;

		    $copia.find('input,textarea,select').val('').each(function() {
		    	if(!$(this).hasClass("esTipoPartida")) {
		    		var componenteFile = $(this).attr('componenteNombreId');
			        if(typeof componenteFile !== typeof undefined && componenteFile !== false) {
			        	 var maxValue = $('[rel="' + rel + '"]').find('input,textarea,select').toArray().reduce(function(acc, elemento) {
			        		 if(!$(elemento).hasClass("esTipoPartida")){
				        		 if(!isNaN(parseInt(/\d+$/.exec(elemento.getAttribute('componenteNombreId'))))) {
				        			 return Math.max(parseInt(/\d+/g.exec(elemento.getAttribute('componenteNombreId'))), acc);
				        		 }
				        		 else {
				        			 return acc;
				        		 }
			        		 }
			        		 else {
			        			 return acc;
			        		 }
			      	      }, 0) + 1;

			        	  var sonIguales = $(this).attr('name') === $(this).attr('id');
			      	      $(this).attr('name', $(this).attr('name').replace(/\d+/g, maxValue - 1));

			      	      if(sonIguales) {
			      	    	 $(this).attr('id', $(this).attr('id').replace(/\d+/g, maxValue - 1));
			      	      }
			      	      else {
			      	    	 $(this).attr('id', $(this).attr('id').replace(/\d+$/, '') + maxValue);
			      	      }

			      	      if(!$(this).hasClass("esTipoPartida")){
			      	    	  $(this).attr('componenteNombreId', componenteFile.replace(/\d+$/, '') + maxValue);
			      	      }
			    	}
				    else {
				    	var id = $(this).attr('name').replace(/\d+$/, '') + max;

				    	$(this).attr('name', id);
				    	$(this).attr('id', id);
				    }
		    	}
		    });

		    $copia.find('.campoMultiple').each(function() {
		    	var $this = $(this), rel = $this.attr('rel');
		    	var cuenta = $('.nombrePartida').toArray().reduce(function(acc, elemento) {
	        		 if(!isNaN(parseInt(/\d+$/.exec(elemento.getAttribute('componenteNombreId'))))) {
	        			 return Math.max(parseInt(/\d+/g.exec(elemento.getAttribute('componenteNombreId'))), acc);
	        		 }
	        		 else {
	        			 return acc;
	        		 }
	      	      }, 0);

		    	$(this).attr('rel', "tipos_objeto"+ (cuenta + 1));

		    	$this.find('input,textarea,select').val('').each(function() {
		    		var componenteFile = $(this).attr('componenteNombreId');
			        if(typeof componenteFile !== typeof undefined && componenteFile !== false) {
			        	  var sonIguales = $(this).attr('name') === $(this).attr('id');

		        		  $(this).attr('name', $(this).attr('name').replace(/partidas\[\d+\]/g, "partidas[" + (cuenta) + "]"));

		        		  if(sonIguales) {
		        			  $(this).attr('id', $(this).attr('id').replace(/partidas\[\d+\]/g, "partidas[" + (cuenta) + "]"));
		        		  }
		        		  else {
		        			  $(this).attr('id', $(this).attr('id').replace(/partidas_\d+_/g, "partidas_" + (cuenta) + "_"));
		        		  }

			    	}
			    });

		    	$this.find('.edicion-buscar').text('').each(function() {
		    	      var $this = $(this);

		    	      if($this.hasClass("esTipoPartida")) {
		    	    	  var id = $(this).attr('id').replace(/selectTipoObjetoBoton\d+_/g, 'selectTipoObjetoBoton' + (cuenta) + "_");
		    	    	  $this.attr("id", id);
		    	    	  $this.attr("name", id);
		    	    	  $this.attr("data-identificador-boton", id);
		    	    	  $this.attr("data-identificador-a-modificar", $this.parent().parent().find(".idTipoPartida").attr('id'));
		    	      }
		    	      else {
		    	    	  ['data-identificador-a-modificar', 'data-identificador-boton', 'name', 'id'].forEach(function(attr) {
		    	        	  var valor = $this.attr(attr);

		    	        	  if (valor) {
		    	        		  $this.attr(attr, valor.replace(/\d+$/, '') + max);
		    	        	  }
		    	          });
		    	      }
		    	});
		    });

		    $label.after($copia);

		    if (maximo === $todosLosDelCampo.length + 1) {
		      $todosLosDelCampo.add($copia).closest('.campoMultiplePartidas').addClass('is-maximo');
		    }
	  }
	});

	$(document.body).on('click', '.campoMultiplePartidas-menos', function() {
	  var $this = $(this),
	      $label = $this.closest('.campoMultiplePartidas'),
	      rel = $label.attr('rel'),
	      $todosLosDelCampo = $('[rel="' + rel + '"]');

	  if ($todosLosDelCampo.length > 1) {
	    $label.remove();
	    $todosLosDelCampo.removeClass('is-maximo');
	  }

	  if ($todosLosDelCampo.length === 2) {
	    $todosLosDelCampo.addClass('is-unico');
	  }
	});






$('.campoMultiple').each(function() {
  var $this = $(this),
      rel = $this.attr('rel'),
      cuenta = $('[rel="' + rel + '"]').length,
      $botones = $('<div class="campoMultiple-botones"></div>');

  if (cuenta === 1) {
    $this.addClass('is-unico');
  }

  if($this.find(".campoMultiple-botones").length == 0) {
	  $botones.append('<button type="button" href="javascript:void(0)" class="campoMultiple-mas">+</button>');
	  $botones.append('<button type="button" href="javascript:void(0)" class="campoMultiple-menos">-</button>');
	  $this.append($botones);
  }
});

$(document.body).on('click', '.campoMultiple-mas', function() {
  var $this = $(this),
      $label = $this.closest('.campoMultiple'),
      rel = $label.attr('rel'),
      $todosLosDelCampo = $('[rel="' + rel + '"]'),
      maximo = parseInt($label.data('maximo') || 10000, 10),
      $copia;

  if (maximo > $todosLosDelCampo.length) {
    $label.removeClass('is-unico');
    $copia = $label.clone();

	var max = $('[rel="' + rel + '"]').find('input,textarea,select').toArray().reduce(function(acc, elemento) {
		 if(!isNaN(parseInt(/\d+$/.exec(elemento.getAttribute('name'))))) {
			 return Math.max(parseInt(/\d+$/.exec(elemento.getAttribute('name'))), acc);
		 }
		 else {
			 return acc;
		 }
    }, 0) + 1;

    $copia.find('input,textarea,select').val('').each(function() {
    	var componenteFile = $(this).attr('componenteNombreId');
        if(typeof componenteFile !== typeof undefined && componenteFile !== false) {
        	 var maxValue = $('[rel="' + rel + '"]').find('input,textarea,select').toArray().reduce(function(acc, elemento) {
        		 if(!isNaN(parseInt(/\d+$/.exec(elemento.getAttribute('componenteNombreId'))))) {
        			 return Math.max(parseInt(/\d+/g.exec(elemento.getAttribute('componenteNombreId'))), acc);
        		 }
        		 else {
        			 return acc;
        		 }

      	      }, 0) + 1;

        	  if($(this).hasClass("esTipoPartida")){
        		  var sonIguales = $(this).attr('name') === $(this).attr('id');

        		  $(this).attr('name', $(this).attr('name').replace(/tipos\[\d+\]/g, "tipos[" + (maxValue - 1) + "]"));

        		  if(sonIguales) {
        			  $(this).attr('id', $(this).attr('id').replace(/tipos\[\d+\]/g, "tipos[" + (maxValue - 1) + "]"));
        		  }
        		  else {
        			  $(this).attr('id', $(this).attr('id').replace(/tipos_\d+_/g, "tipos_" + (maxValue - 1) + "_"));
        		  }
        	  }
        	  else {
        		  var sonIguales = $(this).attr('name') === $(this).attr('id');
          	      $(this).attr('name', $(this).attr('name').replace(/\d+/g, maxValue - 1));

          	      if(sonIguales) {
          	    	 $(this).attr('id', $(this).attr('id').replace(/\d+/g, maxValue - 1));
          	      }
          	      else {
          	    	 $(this).attr('id', $(this).attr('id').replace(/\d+$/, '') + maxValue);
          	      }
        	  }

      	      $(this).attr('componenteNombreId', componenteFile.replace(/\d+$/, '') + maxValue);
    	}
	    else {
	    	var id = $(this).attr('name').replace(/\d+$/, '') + max;

	    	$(this).attr('name', id);
	    	$(this).attr('id', id);
	    }
    });


    $copia.find('.edicion-buscar').text('').each(function() {
      var $this = $(this);

      if($this.hasClass("esTipoPartida")) {
    	  var maxValue = $('[rel="' + rel + '"]').find('input,textarea,select').toArray().reduce(function(acc, elemento) {
     		 if(!isNaN(parseInt(/\d+$/.exec(elemento.getAttribute('componenteNombreId'))))) {
     			 return Math.max(parseInt(/\d+/g.exec(elemento.getAttribute('componenteNombreId'))), acc);
     		 }
     		 else {
     			 return acc;
     		 }

   	      }, 0) + 1;


    	  var id = $(this).attr('id').replace(/tipoObjeto_\d+/g, 'tipoObjeto_' + (maxValue - 1));
    	  $this.attr("id", id);
    	  $this.attr("name", id);
    	  $this.attr("data-identificador-boton", id);
    	  $this.attr("data-identificador-a-modificar", $this.parent().parent().find(".idTipoPartida").attr('id'));
      }
      else {
    	  ['data-identificador-a-modificar', 'data-identificador-boton', 'name', 'id'].forEach(function(attr) {
        	  var valor = $this.attr(attr);

        	  if (valor) {
        		  $this.attr(attr, valor.replace(/\d+$/, '') + max);
        	  }
          });
      }
    });

    $copia.find('.edicion-buscar-nuevoElemento').text('').each(function() {
      var $this = $(this);
      ['data-identificador-a-modificar', 'data-identificador-boton', 'name', 'id']
          .forEach(function(attr) {
        var valor = $this.attr(attr);

        if (valor) {
          $this.attr(attr, valor.replace(/\d+$/, '') + max);
        }
      });
    });

    $copia.find("#descargaBoton").removeClass("boton-descargar");
    $copia.find("#descargaBoton").addClass("boton-oculto-descargar");

    $copia.find(".adjuntos-nuevo").removeClass("boton-agregar-oculto");

    $label.after($copia);

    if (maximo === $todosLosDelCampo.length + 1) {
      $todosLosDelCampo.add($copia).closest('.campoMultiple').addClass('is-maximo');
    }
  }
});

$(document.body).on('click', '.campoMultiple-menos', function() {
  var $this = $(this),
      $label = $this.closest('.campoMultiple'),
      rel = $label.attr('rel'),
      $todosLosDelCampo = $('[rel="' + rel + '"]');

  if ($todosLosDelCampo.length > 1) {
    $label.remove();
    $todosLosDelCampo.removeClass('is-maximo');
  }

  if ($todosLosDelCampo.length === 2) {
    $todosLosDelCampo.addClass('is-unico');
  }
});


window.cargando = {
  mostrar: function() {
    document.body.classList.add('is-cargando');
  },
  ocultar: function() {
    document.body.classList.remove('is-cargando');
  }
};
var $body = $(document.body);



$(document.body).on('change', 'input[type="file"]', function() {
  var $this = $(this),
      nombreArchivo = $("#" + $this.attr("componenteNombreId"));
  var trozos = $this.val().split('\\');
  nombreArchivo.val(trozos.pop());
});



$(document.body).on('keydown', 'input[type="number"]', function(e) {
	  if (e.ctrlKey || e.altKey || e.metaKey) {
		 e.preventDefault();
	    return false;
	  }

	  if ((e.key < '0' || e.key > '9') && e.key !== '.' && e.key !== ',' && e.keyCode > 40) {
	    e.preventDefault();
	    return false;
	  }
});


$(document.body).on('change', 'input[type="checkbox"][data-controlado]', function() {
	  var $input = $(this),
	      $controlado = $($input.data('controlado'));

	  if ($input.is(':checked')) {
	    $controlado.removeAttr('disabled');
	  } else {
	    $controlado.attr('disabled', 'disabled');
	  }
});








/**********************************/
/*      Datepicker definiton      */
/**********************************/
$('label.date input').datetimepicker({
	locale: 'es',
	format: 'DD/MM/YYYY HH:mm',
	widgetPositioning: {
		horizontal: 'right',
		vertical: 'bottom'
	}
});



function closeAllMenus() {
	$(document.body).removeAttr('class');
}