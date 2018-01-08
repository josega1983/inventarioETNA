<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:include value="/jsp/common/contentPopup.jsp">
	<!-- Propiedades para configurar el contenido del popup a mostrar. -->
	<s:param name="idPopup">idRegionesPopup</s:param>
	<s:param name="tituloPopup"><s:text name="region.seleccionar_titulo_popup"/></s:param>
	<s:param name="vistaFiltroPopup">/jsp/region/popup/filtroPopup.jsp</s:param>
	<s:param name="vistaHeaderTablePopup">/jsp/region/popup/headerResultadosPopup.jsp</s:param>
	<s:param name="vistaBodyTablePopup">/jsp/region/popup/bodyResultadosPopup.jsp</s:param>
	
	
	<!-- Propiedades para la comunicacion con los actions y para cargar el elemento seleccionado.  -->
	<s:param name="urlAccionSearch"><s:url namespace='/region' action='searchPopup'/></s:param>
	<s:param name="urlAccionMoreResults"><s:url namespace='/region' action='moreResultsPopup'/></s:param>
	<s:param name="urlAccionSelect"><s:url namespace='/region' action='selectElementoFromPopup'/></s:param>
	
	<s:param name="identificadorBotonPopup">idRegionBotonPopup</s:param>
	<s:param name="identificadorDetalleElementoSeleccionado">detalleRegionSeleccionada</s:param>
</s:include>
