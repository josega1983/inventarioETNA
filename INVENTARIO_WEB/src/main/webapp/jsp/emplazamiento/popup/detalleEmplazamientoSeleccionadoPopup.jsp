<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_EMPLAZAMIENTO)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombre}"/>
</s:if>
<div id="detalleEmplazamientoSeleccionado">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_EMPLAZAMIENTO)}">
		<s:hidden id="idEmplazamientoSeleccionado" theme="simple" name="emplazamiento.id" value="%{elemento.id}" />
		<s:hidden id="nombreEmplazamientoSeleccionado" theme="simple" name="emplazamiento.nombre" value="%{elemento.nombre}" />
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idEmplazamientoSeleccionado" theme="simple" name="emplazamiento.id"/>
		<s:hidden id="nombreEmplazamientoSeleccionado" theme="simple" name="emplazamiento.nombre"/>
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
