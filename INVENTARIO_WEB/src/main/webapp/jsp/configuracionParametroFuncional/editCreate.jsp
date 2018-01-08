<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="edicion edicion-formulario">
	<s:form id="formularioCrearRegion" name="formularioCrearRegion" cssClass="edicion-formulario form-view" theme="simple">
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
					<h2 class="titulo-seccion"><s:property value="getText('region')"/></h2>
										<div class="form-row">
						<s:include value="/jsp/common/botonSelectPopup.jsp">
							<s:param name="obligatorio">true</s:param>
							<s:param name="urlAccionCargar"><s:url namespace='/parametroFuncional' action='loadPopup'/></s:param>
							<s:param name="etiquetaBotonPopup"><s:text name="parametroFuncional"/></s:param>
							<s:param name="identificadorBotonPopup">idParametroFuncionalBotonPopup</s:param>
							<s:param name="textoElementoSeleccionado"><s:if test="parametroFuncional != null && parametroFuncional.id != null"><s:property value="parametroFuncional.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
							<s:param name="idPopup">idParametrosFuncionalPopup</s:param>
							<s:param name="excluirPopup">NO</s:param>
						</s:include>
					</div>
					<s:include value="/jsp/parametroFuncional/popup/detalleParametroFuncionalSeleccionadoPopup.jsp"/>	
					<div class="form-row">
						<label class="col-mid obligatorio">
							<span class="etiqueta"><s:property value="getText('configuracionParametroFuncional.nombreCampo')"/></span>
							<span class="dato"><input type="text" required="true"  value="${nombreCampo}"  maxlength="30" name="nombreCampo" /></span>
						</label>
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('configuracionParametroFuncional.tipoCampo')"/></span>
							<span class="dato"><input type="text"  maxlength="500"  value="${tipoCampo}" name="tipoCampo" /></span>
						</label>
					</div>
				</fieldset>
			</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>