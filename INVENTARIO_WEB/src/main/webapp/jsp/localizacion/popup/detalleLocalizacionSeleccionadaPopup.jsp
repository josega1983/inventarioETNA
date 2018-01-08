<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_LOCALIZACION)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombre}"/>
</s:if>
<div id="detalleLocalizacionSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_LOCALIZACION)}">
		<s:hidden id="idLocalizacionSeleccionada" theme="simple" name="localizacion.id" value="%{elemento.id}" />
		<s:hidden id="nombreLocalizacionSeleccionada" theme="simple" name="localizacion.nombre" value="%{elemento.nombre}" />
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idLocalizacionSeleccionada" theme="simple" name="localizacion.id"/>
		<s:hidden id="nombreLocalizacionSeleccionada" theme="simple" name="localizacion.nombre"/>
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
