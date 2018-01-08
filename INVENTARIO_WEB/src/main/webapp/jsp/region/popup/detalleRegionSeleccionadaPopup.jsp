<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_REGION)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombre}"/>
</s:if>
<div id="detalleRegionSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_REGION)}">
		<s:hidden id="idRegionSeleccionada" theme="simple" name="region.id" value="%{elemento.id}" />
		<s:hidden id="nombreRegionSeleccionada" theme="simple" name="region.nombre" value="%{elemento.nombre}" />
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idRegionSeleccionada" theme="simple" name="region.id"/>
		<s:hidden id="nombreRegionSeleccionada" theme="simple" name="region.nombre"/>
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
