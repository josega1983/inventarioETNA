<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:include value="/jsp/common/contentPopup.jsp">
	<!-- Propiedades para configurar el contenido del popup a mostrar. -->
	<s:param name="idPopup">idEmplazamientosPopup</s:param>
	<s:param name="tituloPopup"><s:property value="getText('emplazamiento.seleccionar_titulo_popup')"/></s:param>
	<s:param name="vistaFiltroPopup">/jsp/emplazamiento/popup/filtroPopup.jsp</s:param>
	<s:param name="vistaHeaderTablePopup">/jsp/emplazamiento/popup/headerResultadosPopup.jsp</s:param>
	<s:param name="vistaBodyTablePopup">/jsp/emplazamiento/popup/bodyResultadosPopup.jsp</s:param>
	
	
	<!-- Propiedades para la comunicacion con los actions y para cargar el elemento seleccionado.  -->
	<s:param name="urlAccionSearch"><s:url namespace='/emplazamiento' action='searchPopup'/></s:param>
	<s:param name="urlAccionMoreResults"><s:url namespace='/emplazamiento' action='moreResultsPopup'/></s:param>
	<s:param name="urlAccionSelect"><s:url namespace='/emplazamiento' action='selectElementoFromPopup'/></s:param>
	
	<s:param name="identificadorBotonPopup">idEmplazamientoBotonPopup</s:param>
	<s:param name="identificadorDetalleElementoSeleccionado">detalleEmplazamientoSeleccionado</s:param>
</s:include>
