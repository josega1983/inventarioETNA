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

				<h2 class="titulo-seccion"><s:text name="ubicacionLogica"/></h2>

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
						<s:param name="urlAccionCargar"><s:url namespace='/sector' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="sector"/></s:param>
						<s:param name="identificadorBotonPopup">idSectorBotonPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="sector != null && sector.id != null"><s:property value="sector.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idSectoresPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/sector/popup/detalleSectorSeleccionadaPopup.jsp"/>

				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/centro' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="centro"/></s:param>
						<s:param name="identificadorBotonPopup">idCentroBotonPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="centro != null && centro.id != null"><s:property value="centro.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idCentrosPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/centro/popup/detalleCentroSeleccionadoPopup.jsp"/>	

				<div class="form-row">
					<s:include value="/jsp/common/botonSelectPopup.jsp">
						<s:param name="obligatorio">true</s:param>
						<s:param name="urlAccionCargar"><s:url namespace='/unidadMantenimiento' action='loadPopup'/></s:param>
						<s:param name="etiquetaBotonPopup"><s:text name="unidadMantenimiento"/></s:param>
						<s:param name="identificadorBotonPopup">idUnidadMantenimientoBotonPopup</s:param>
						<s:param name="textoElementoSeleccionado"><s:if test="unidadMantenimiento != null && unidadMantenimiento.id != null"><s:property value="unidadMantenimiento.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
						<s:param name="idPopup">idUnidadesMantenimientoPopup</s:param>
						<s:param name="excluirPopup">NO</s:param>
					</s:include>
				</div>
				<s:include value="/jsp/unidadMantenimiento/popup/detalleUnidadMantenimientoSeleccionadaPopup.jsp"/>

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