<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.region.nombre}-%{elemento.sector.nombre}-%{elemento.centro.nombre}-%{elemento.unidadMantenimiento.nombre}"/>
</s:if>
<div id="detalleUbicacionLogicaSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA)}">
		<s:hidden id="idUbicacionLogicaSeleccionada" theme="simple" name="ubicacionLogica.id" value="%{elemento.id}" />
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idUbicacionLogicaSeleccionada" theme="simple" name="ubicacionLogica.id"/>
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
