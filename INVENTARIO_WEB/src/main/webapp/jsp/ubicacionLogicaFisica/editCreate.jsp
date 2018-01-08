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
			<div class="formulario-contenido form-view" id="formulario-region">

				<h2 class="titulo-seccion"><s:property value="getText('ubicacionLogicaFisica')"/></h2>
				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/ubicacionLogica' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="ubicacionLogica"/></s:param>
						<s:param name="identificadorBotonPopup">idUbicacionLogicaPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="ubicacionLogica != null && ubicacionLogica.id != null"><s:property value="ubicacionLogica.region.nombre"/>-<s:property value="ubicacionLogica.sector.nombre"/>-
						<s:property value="ubicacionLogica.centro.nombre"/>-<s:property value="ubicacionLogica.unidadMantenimiento.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idUbicacionesLogicaPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/ubicacionLogica/popup/detalleUbicacionLogicaSeleccionadaPopup.jsp"/>	

				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/ubicacionFisica' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="ubicacionFisica"/></s:param>
						<s:param name="identificadorBotonPopup">idUbicacionFisicaPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="ubicacionFisica != null && ubicacionFisica.id != null"><s:property value="ubicacionFisica.region.nombre"/>-<s:property value="ubicacionFisica.sector.nombre"/>-
						<s:property value="ubicacionFisica.localizacion.nombre"/>-<s:property value="ubicacionFisica.emplazamiento.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idUbicacionesFisicaPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/ubicacionFisica/popup/detalleUbicacionFisicaSeleccionadaPopup.jsp"/>

				<div class="form-row">														
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
						<span class="dato"><textarea type="text" value="" maxlength="500" name="observaciones"><s:property value="observaciones"/></textarea></span>
					</label>
				</div>

			</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>