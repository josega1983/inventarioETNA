<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:include value="/jsp/common/contentPopup.jsp">
	<!-- Propiedades para configurar el contenido del popup a mostrar. -->
	<s:param name="idPopup">idTiposInstalacionPopup</s:param>
	<s:param name="tituloPopup"><s:text name="tipoInstalacion.seleccionar_titulo_popup"/></s:param>
	<s:param name="vistaFiltroPopup">/jsp/tipoInstalacion/popup/filtroNoCTCPopup.jsp</s:param>
	<s:param name="vistaHeaderTablePopup">/jsp/tipoInstalacion/popup/headerResultadosPopup.jsp</s:param>
	<s:param name="vistaBodyTablePopup">/jsp/tipoInstalacion/popup/bodyResultadosPopup.jsp</s:param>
	
	
	<!-- Propiedades para la comunicacion con los actions y para cargar el elemento seleccionado.  -->
	<s:param name="urlAccionSearch"><s:url namespace='/tipoInstalacion' action='searchPopup'/></s:param>
	<s:param name="urlAccionMoreResults"><s:url namespace='/tipoInstalacion' action='moreResultsPopup'/></s:param>
	<s:param name="urlAccionSelect"><s:url namespace='/tipoInstalacion' action='selectElementoFromPopup'/></s:param>
	
	<s:param name="identificadorBotonPopup">idTipoInstalacionBotonPopup</s:param>
	<s:param name="identificadorDetalleElementoSeleccionado">detalleTipoInstalacionSeleccionada</s:param>
</s:include>
