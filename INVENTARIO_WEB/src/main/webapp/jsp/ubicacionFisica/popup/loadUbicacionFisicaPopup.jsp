<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:include value="/jsp/common/contentPopup.jsp">
	<!-- Propiedades para configurar el contenido del popup a mostrar. -->
	<s:param name="idPopup">idUbicacionesFisicaPopup</s:param>
	<s:param name="tituloPopup"><s:text name="ubicacionFisica.seleccionar_titulo_popup"/></s:param>
	<s:param name="vistaFiltroPopup">/jsp/ubicacionFisica/popup/filtroPopup.jsp</s:param>
	<s:param name="vistaHeaderTablePopup">/jsp/ubicacionFisica/popup/headerResultadosPopup.jsp</s:param>
	<s:param name="vistaBodyTablePopup">/jsp/ubicacionFisica/popup/bodyResultadosPopup.jsp</s:param>
	
	
	<!-- Propiedades para la comunicacion con los actions y para cargar el elemento seleccionado.  -->
	<s:param name="urlAccionSearch"><s:url namespace='/ubicacionFisica' action='searchPopup'/></s:param>
	<s:param name="urlAccionMoreResults"><s:url namespace='/ubicacionFisica' action='moreResultsPopup'/></s:param>
	<s:param name="urlAccionSelect"><s:url namespace='/ubicacionFisica' action='selectElementoFromPopup'/></s:param>
	
	<s:param name="identificadorBotonPopup">idUbicacionFisicaPopup</s:param>
	<s:param name="identificadorDetalleElementoSeleccionado">detalleUbicacionFisicaSeleccionada</s:param>
</s:include>
