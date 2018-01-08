<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CONFIGURACION_PARAMETRO_FUNCIONAL)}">
	<s:hidden id="textoBotonSelectPopup" theme="simple" value="%{elemento.nombreCampo}"/>
</s:if>
<div id="detalleConfiguracionParametroFuncionalSeleccionada">
	<s:if test="%{namespace.equals(@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CONFIGURACION_PARAMETRO_FUNCIONAL)}">
		<s:hidden id="idConfiguracionParametroFuncionalSeleccionada" theme="simple" name="configuracionParametroFuncional.id" value="%{elemento.id}" />
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion vinculando con los valores de elemento. -->
	</s:if>
	<s:else>
		<s:hidden id="idConfiguracionParametroFuncionalSeleccionada" theme="simple" name="configuracionParametroFuncional.id"/>
	
		<!-- Podemos añadir mas datos de visualizacion si queremos en esta seccion. -->
	</s:else>	
</div>
