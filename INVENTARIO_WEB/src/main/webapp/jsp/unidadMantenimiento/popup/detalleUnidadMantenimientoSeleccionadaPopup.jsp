<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UNIDAD_MANTENIMIENTO)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombre}"/>
</s:if>
<div id="detalleUnidadMantenimientoSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UNIDAD_MANTENIMIENTO)}">
		<s:hidden id="idUnidadMantenimientoSeleccionada" theme="simple" name="unidadMantenimiento.id" value="%{elemento.id}" />
		<s:hidden id="nombreUnidadMantenimientoSeleccionada" theme="simple" name="unidadMantenimiento.nombre" value="%{elemento.nombre}" />
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idUnidadMantenimientoSeleccionada" theme="simple" name="unidadMantenimiento.id"/>
		<s:hidden id="nombreUnidadMantenimientoSeleccionada" theme="simple" name="unidadMantenimiento.nombre"/>
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
