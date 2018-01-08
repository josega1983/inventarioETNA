<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="edicion edicion-formulario">
	<s:form id="formularioSolicitar" name="formularioCrearRegion" cssClass="edicion-formulario form-view" theme="simple">
		<s:if test="hasActionErrors()">
			<s:iterator value="actionErrors">
		    	<div class="alert alert-danger alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					<s:property/>
				</div>
			</s:iterator>
		</s:if>
	<s:push value="elemento">
		<s:hidden name="elemento.id"/>
		<s:hidden name="elemento.activo"/>
			<div class="formulario-contenido" id="formulario-region">
				<fieldset>
				<h2 class="titulo-seccion"><s:property value="getText('configuracionesTipo.solicitud')"/></h2>
					<div class="form-row">
						<s:include value="/jsp/common/botonSelectPopup.jsp">
							<s:param name="obligatorio">true</s:param>
							<s:param name="urlAccionCargar"><s:url namespace='/familiaInstalacion' action='loadPopup'/></s:param>
							<s:param name="etiquetaBotonPopup"><s:text name="familiaInstalacion"/></s:param>
							<s:param name="identificadorBotonPopup">idFamiliaInstalacionBotonPopup</s:param>
							<s:param name="textoElementoSeleccionado"><s:if test="familiaInstalacion != null && familiaInstalacion.id != null"><s:property value="familiaInstalacion.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
							<s:param name="idPopup">idFamiliasInstalacionPopup</s:param>
							<s:param name="excluirPopup">NO</s:param>
						</s:include>
					</div>
					<s:include value="/jsp/familiaInstalacion/popup/detalleFamiliaInstalacionSeleccionadaPopup.jsp"/>
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('global.marca')"/></span>
							<span class="dato"><input type="text" value="${tipoInstalacion.marca}" maxlength="500" name="tipoInstalacion.marca" id="marca" /></span>
						</label>
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('global.marca')"/></span>
							<span class="dato"><input type="text" value="${tipoInstalacion.modelo}" maxlength="500" name="tipoInstalacion.modelo" id="modelo" /></span>
						</label>
				</fieldset>
			</div>
		</s:push>
	<div class="botoneraAccionesPie">
        	<%-- <s:submit key="global.solicitar" cssClass="formulario-boton opcion-solicitar"  theme="simple" onclick="popUp(event);"/> --%>
        	<button type="button" class="formulario-boton" onclick="popUp(event);"><s:property value="getText('global.solicitar')"/></button>
	</div>
	</s:form>
</div>
<script type="text/javascript">
function popUp(event) {
	event.preventDefault();
	swal({
		title: 'Atención',
		text: "Esta seguro de solicitar la creaccion de esa marca y modelo",
		type: 'warning',
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "OK",
		closeOnConfirm: false
		//showLoaderOnConfirm: true
	},function(aceptado) {
				$.ajax({
				url:  'solicitarCreaccion',
				type: "POST",
				async: false,
				data: {
 					"elemento.tipoInstalacion.familiaInstalacion.nombre" : $('#familiaInstalacionSeleccionada_nombre').val(),
					"elemento.tipoInstalacion.marca" : $('#marca').val(),
					"elemento.tipoInstalacion.modelo": $('#modelo').val(),
				},
				success: function(result){
					validarSesionExpirada(result);
					var respuesta = JSON.parse(result);
					if(respuesta.estado === "OK"){
							swal({
									title: 'Atención',
									text: respuesta.mensaje,
									type:  'warning',
									showCancelButton: true,
									confirmButtonColor: "#DD6B55",
									confirmButtonText: "OK",
									closeOnConfirm: true
									//showLoaderOnConfirm: true
								}, function (aceptado){
									$('#formularioSolicitar').attr('action', 'list');
									$('#formularioSolicitar').submit();
								});
					}
					if(respuesta.estado === "KO"){
					swal("Error",respuesta.mensaje,"error");
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					swal("Error","Error en el envio","error");
				}
			});

	})
}
</script>