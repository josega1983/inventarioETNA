<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_SECTOR)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombre}"/>
</s:if>
<div id="detalleSectorSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_SECTOR)}">
		<s:hidden id="idSectorSeleccionada" theme="simple" name="sector.id" value="%{elemento.id}" />
		<s:hidden id="nombreSectorSeleccionada" theme="simple" name="sector.nombre" value="%{elemento.nombre}" />
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idSectorSeleccionada" theme="simple" name="sector.id"/>
		<s:hidden id="idSectorSeleccionada" theme="simple" name="sector.nombre"/>
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
