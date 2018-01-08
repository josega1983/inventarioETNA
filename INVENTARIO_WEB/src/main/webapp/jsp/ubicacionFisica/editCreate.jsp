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

				<h2 class="titulo-seccion"><s:text name="ubicacionFisica"/></h2>

				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.name')"/></span>
						<span class="dato"><input type="text" required="true" value="${nombre}" maxlength="30" name="nombre" /></span>
					</label>
				</div>

				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/region' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="region"/></s:param>
						<s:param name="identificadorBotonPopup">idRegionBotonPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="region != null && region.id != null"><s:property value="region.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idRegionesPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/region/popup/detalleRegionSeleccionadaPopup.jsp"/>

				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/localizacion' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="localizacion"/></s:param>
						<s:param name="identificadorBotonPopup">idLocalizacionBotonPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="localizacion != null && localizacion.id != null"><s:property value="localizacion.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idLocalizacionesPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/localizacion/popup/detalleLocalizacionSeleccionadaPopup.jsp"/>

				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/emplazamiento' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="emplazamiento"/></s:param>
						<s:param name="identificadorBotonPopup">idEmplazamientoBotonPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="emplazamiento != null && emplazamiento.id != null"><s:property value="emplazamiento.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idEmplazamientosPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/emplazamiento/popup/detalleEmplazamientoSeleccionadoPopup.jsp"/>
				
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
						<span class="dato"><input type="text" value="${observaciones}" maxlength="500" name="observaciones" /></span>
					</label>
				</div>

			</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>