<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_INSTALACION)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.familiaInstalacion.nombre} | %{elemento.marca} | %{elemento.modelo}"/>
</s:if>
<div id="detalleTipoInstalacionSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_INSTALACION)}">
		<s:hidden id="idTipoInstalacionSeleccionada" theme="simple" name="tipoInstalacion.id" value="%{elemento.id}" />
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idTipoInstalacionSeleccionada" theme="simple" name="tipoInstalacion.id"/>
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
