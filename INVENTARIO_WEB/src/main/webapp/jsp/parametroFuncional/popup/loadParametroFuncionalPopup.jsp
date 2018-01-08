<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:include value="/jsp/common/contentPopup.jsp">
	<!-- Propiedades para configurar el contenido del popup a mostrar. -->
	<s:param name="idPopup">idParametrosFuncionalPopup</s:param>
	<s:param name="tituloPopup"><s:text name="parametroFuncional.seleccionar_titulo_popup"/></s:param>
	<s:param name="vistaFiltroPopup">/jsp/parametroFuncional/popup/filtroPopup.jsp</s:param>
	<s:param name="vistaHeaderTablePopup">/jsp/parametroFuncional/popup/headerResultadosPopup.jsp</s:param>
	<s:param name="vistaBodyTablePopup">/jsp/parametroFuncional/popup/bodyResultadosPopup.jsp</s:param>
	
	
	<!-- Propiedades para la comunicacion con los actions y para cargar el elemento seleccionado.  -->
	<s:param name="urlAccionSearch"><s:url namespace='/parametroFuncional' action='searchPopup'/></s:param>
	<s:param name="urlAccionMoreResults"><s:url namespace='/parametroFuncional' action='moreResultsPopup'/></s:param>
	<s:param name="urlAccionSelect"><s:url namespace='/parametroFuncional' action='selectElementoFromPopup'/></s:param>
	
	<s:param name="identificadorBotonPopup">idParametroFuncionalBotonPopup</s:param>
	<s:param name="identificadorDetalleElementoSeleccionado">detalleParametroFuncionalSeleccionadoSeleccionada</s:param>
</s:include>
