function convertJSONForm(jsonForm) {
	var jsonOK = {};
	for(var i = 0; i < jsonForm.length; i++) {
		jsonOK[jsonForm[i].name] = jsonForm[i].value;
	}
	
	return jsonOK;
} 



$(document).keypress(function(event){
	if (event.keyCode === 10 || event.keyCode === 13)
		event.preventDefault();
});

//Función que lanza un mensaje de alerta
 function myAlert(message) {
	$("<div>"+ message+"</div>").dialog({
		modal: true,
		buttons: {
			Ok: function () {
				$(this).dialog("close");
			}
		}
	});
 }

 function myConfirm(dialogText, okFunc, cancelFunc, dialogTitle) {
	$('<div style="padding: 10px; max-width: 500px; word-wrap: break-word;">' + dialogText + '</div>').dialog({
		draggable: false,
		modal: true,
		resizable: false,
		width: 'auto',
		title: dialogTitle || 'Confirm',
		minHeight: 75,
		buttons: {
			"Aceptar": function () {
				if (typeof (okFunc) == 'function') {
					setTimeout(okFunc, 50);
				}
				$(this).dialog('destroy');
			},
			"Cancelar": function () {
				if (typeof (cancelFunc) == 'function') {
					setTimeout(cancelFunc, 50);
				}
				$(this).dialog('destroy');
			}
		}
	});
 }


function actualizaListadoPopup(html){
	$('#items').html($(html).find('#items').html());
	if($(html).find('#hayMas').val() === 'false') {
		$('.spinner-carga').closest('.popup-contenido').off('scroll load');
		$('.spinner-carga').hide();
	}
	else {
		$('.spinner-carga').show();
		$('.spinner-carga').each(function() {
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
				$(document.body).trigger('cargar-mas', {
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

function actualizaListadoUsuario() {
	$("#actualPage").val(0);

	cargando.mostrar();
	setTimeout(function() {
		$.ajax({
			url: "../usuario/searchPopup",
			type: "POST",
			async: false,
			data: {
				"filtroUsuario.usuario": $("#filtroUsuario_usuario").val(),
			},
			success: function(html){
				actualizaListadoPopup(html);
			}
		});
	}, 500);
}

function actualizaListadoEmpresa() {
	$("#actualPage").val(0);

	cargando.mostrar();
	setTimeout(function() {
		$.ajax({
			url: "../empresa/searchPopup",
			type: "POST",
			async: false,
			data: {
				"filtro.nombre": $("#filtro_nombre").val(),
				"filtro.cif": $("#filtro_cif").val(),
				"filtro.codigoEmbarque": $("#filtro_codigoEmbarque").val(),
				"filtro.codigoEmpresa": $("#filtro_codigo").val(),
			},
			success: function(html){
				actualizaListadoPopup(html);
			}
		});
	}, 500);
}

function usuarioSeleccionado(usuarioSeleccionado) {
	$("#formularioCrearSolicitud_usuario_usuario").val(usuarioSeleccionado);
	$("#formularioCrearRol_usuario_usuario").val(usuarioSeleccionado);
	$("#selectUsuarioBoton").html(usuarioSeleccionado);

	return popups.cierraPopup();
}

function empresaSeleccionada(id, nombre, codigo, cif, representante, direccion) {
	$("#formularioCrearActividad_empresa_id").val(id);
	$("#selectEmpresaBoton").html(codigo + "-" + nombre);
	$("#formularioCrearActividad_empresa_cif").html(cif);
	$("#formularioCrearActividad_empresa_representante").html(representante);
	$("#formularioCrearActividad_empresa_direccion").html(direccion);

	return popups.cierraPopup();
}


$(document.body).on('click', '.boton-descargar', function() {
	var $this = $(this),
	fichero = $this.data('fichero');

	$("#formularioDescarga_idFichero").val(fichero);
	$("#formularioDescarga").submit();
});


function limpiarFormularioBusqueda(boton) {
	var $formulario = $(boton).parent().parent().find(".formulario-contenido");

	$formulario.find('input,textarea,select').val('');
	$formulario.find('input:checkbox').each(function() {
		if($(this).prop('checked')) {
			$(this).click();
		}
	});
	$formulario.find('.edicion-buscar').each(function() {
		$(this).html('-');
	});
}

function confirmarAccionPreValidarFormulario(idFormulario, texto, accion, event) {
	var $formularioPre = $("#"+idFormulario);
debugger;
	if($formularioPre[0].checkValidity()){
		event.preventDefault();

		swal({
			title: "Confirmación",
			text: texto,
			type: 'warning',
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "OK",
			closeOnConfirm: true
		}, function(acepto) {
			if (acepto){
				$("#" + idFormulario).attr("action", accion);
				$("#" + idFormulario).submit();
			}
		});
	}
}

function confirmarAccion(idFormulario, texto, accion) {
	swal({
		title: "Confirmación",
		text: texto,
		type: 'warning',
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "OK",
		closeOnConfirm: true
	}, function(acepto) {
		if (acepto){
			$("#" + idFormulario).attr("action", accion);
			$("#" + idFormulario).submit();
		}
	});

	return false;
}
function cancelarAccion(idFormulario, texto) {
	swal({
		title: "ATENCIÓN",
		text: texto,
		type: 'warning',
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "OK",
		closeOnConfirm: true
	}, function(acepto) {
		if (acepto){
			document.getElementById(idFormulario).reset();
		}
	});

	return false;
}
function showSelectMotivoRechazo(urlShow, accion) {
	$.ajax({
		url: urlShow,
		type: "POST",
		async: false,
		data: {
			"actionName": accion
		},
		success: function(html){
			$("#elegirRechazo").html(html);
			return popups.abrePopup("elegirRechazo");
		}
	});
}
function showSelectMotivoRechazoMasivo(idFormulario,urlShow, accion) {
	$.ajax({
		url: urlShow,
		type: "POST",
		async: false,
		data: {
			"actionName": accion,
			"idFormulario":idFormulario
		},
		success: function(html){
			$("#elegirRechazo").html(html);
			return popups.abrePopup("elegirRechazo");
		}
	});
}
function rechazar(){
	var accion = $('#actionName').val();
	var idFormulario = $('#idFormulario').val();
	var motivosRechazoForm_motivoRechazoSeleccionadoPopUp = $("#motivosRechazoForm_motivoRechazoSeleccionadoPopUp").val();
	var motivosRechazoForm_detalleRechazoPopUp = $("#motivosRechazoForm_detalleRechazoPopUp").val();
	$("#motivosRechazoForm_motivoRechazoSeleccionado").val(motivosRechazoForm_motivoRechazoSeleccionadoPopUp);
	$("#motivosRechazoForm_detalleRechazo").val(motivosRechazoForm_detalleRechazoPopUp);
	$("#" + idFormulario).attr("action", accion);
	$("#" + idFormulario).submit();

	}
function cierraPopup() {
	popups.cierraPopup();
	return false;
}
function getInfoActividad(idFormulario, ctx) {
var idActividad = $("#"+idFormulario+"_actividad_id").val();
if (idActividad != ""){
	var url = ctx+"/acreditacionesPersona/getInfoActividad"
		$.ajax({
			url: url ,
			type: "POST",
			async: false,
			data: {
				idActividad: idActividad
			},
			success: function(result){
				var respuesta = JSON.parse(result);
				if(respuesta.estado ==="OK"){
					var respuesta = JSON.parse(result);
					if(respuesta.estado ==="OK"){
						var respuesta = JSON.parse(result);
						$("#"+idFormulario+"_actividad_numero").val(respuesta.numero);
						$("#"+idFormulario+"_actividad_fechaIni").val(respuesta.fechaIni);
						$("#"+idFormulario+"_actividad_fechaFin").val(respuesta.fechaFin);
					}
				}
			},
			error: function (xhr, ajaxOptions, thrownError) {
				swal("Error","Error en la carga de datos de la actividad","error");
				}
			});
	}
	else{
		$("#"+idFormulario+"_actividad_numero").val("");
		$("#"+idFormulario+"_actividad_fechaIni").val("");
		$("#"+idFormulario+"_actividad_fechaFin").val("");
	}
}

function validarSesionExpirada(html) {
	if(html.indexOf("ERROR USUARIO NO AUTENTICADO. POR FAVOR, AUTENTIQUESE DE NUEVO.") != -1) {
		document.open();
		document.write(html);
		document.close();
	}
}
