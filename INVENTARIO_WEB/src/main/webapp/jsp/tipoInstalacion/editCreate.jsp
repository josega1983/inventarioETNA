<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="edicion edicion-formulario">
	<s:form id="formularioCrearRegion" name="formularioCrearRegion form-view" cssClass="edicion-formulario" theme="simple">
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
	<s:hidden name ="elemento.fechaAlta"/>
			<div class="formulario-contenido" id="formulario-region">
				<fieldset>
					<h2 class="titulo-seccion"><s:property value="getText('tipoInstalacion')"/></h2>
					<div class="form-row">
						<s:include value="/jsp/common/botonSelectPopup.jsp">
							<s:param name="obligatorio">true</s:param>
							<s:param name="urlAccionCargar"><s:url namespace='/familiaInstalacion' action='loadPopup'/></s:param>
							<s:param name="etiquetaBotonPopup"><s:text name="familiasInstalaciones"/></s:param>
							<s:param name="identificadorBotonPopup">idFamiliaInstalacionBotonPopup</s:param>
							<s:param name="textoElementoSeleccionado"><s:if test="familiaInstalacion != null && familiaInstalacion.id != null"><s:property value="familiaInstalacion.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
							<s:param name="idPopup">idFamiliasInstalacionPopup</s:param>
							<s:param name="excluirPopup">NO</s:param>
						</s:include>
					</div>
					<s:include value="/jsp/familiaInstalacion/popup/detalleFamiliaInstalacionSeleccionadaPopup.jsp"/>
					<div class="form-row">
						<label class="col-max obligatorio">
							<span class="etiqueta"><s:property value="getText('global.marca')"/></span>
							<span class="dato"><input type="text" required="true"  value="${marca}"  maxlength="30" name="marca" /></span>
						</label>
						<label class="col-max obligatorio">
							<span class="etiqueta"><s:property value="getText('global.modelo')"/></span>
							<span class="dato"><input type="text" required="true"  value="${modelo}"  maxlength="30" name="modelo" /></span>
						</label>
					</div>
					<div class="form-row">
						<label class="col-max ">
							<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
							<span class="dato"><input type="text"  maxlength="500"  value="${observaciones}" name="observaciones" /></span>
						</label>
					</div>
				</fieldset>
			</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>