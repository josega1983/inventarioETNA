<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_INSTALACION)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombre}"/>
</s:if>
<div id="detalleFamiliaInstalacionSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_INSTALACION)}">
		<s:hidden id="idFamiliaInstalacionSeleccionada" theme="simple" name="familiaInstalacion.id" value="%{elemento.id}" />
		<s:hidden id="familiaInstalacionSeleccionada_nombre" theme="simple" name="familiaInstalacion.nombre" value="%{elemento.nombre}"/>
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idFamiliaInstalacionSeleccionada" theme="simple" name="familiaInstalacion.id"/>
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
