<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>



<s:set var="idPopup">${param.idPopup}</s:set>
<s:set var="tituloPopup">${param.tituloPopup}</s:set>
<s:set var="vistaFiltroPopup">${param.vistaFiltroPopup}</s:set>
<s:set var="vistaHeaderTablePopup">${param.vistaHeaderTablePopup}</s:set>
<s:set var="vistaBodyTablePopup">${param.vistaBodyTablePopup}</s:set>

<s:set var="urlAccionMoreResults">${param.urlAccionMoreResults}</s:set>
<s:set var="urlAccionSelect">${param.urlAccionSelect}</s:set>
<s:set var="urlAccionSearch">${param.urlAccionSearch}</s:set>

<s:set var="identificadorDetalleElementoSeleccionado">${param.identificadorDetalleElementoSeleccionado}</s:set>
<s:set var="identificadorBotonPopup">${param.identificadorBotonPopup}</s:set>



<div class="popup" id="<s:property value="#idPopup"/>" style="display: none;">
	<div class="popup-contenido">
		<a class="popup-cerrar"><s:property value="getText('global.close')"/></a>
		<h2 class="popup-titulo"><s:property value="#tituloPopup"/></h2>

		<s:hidden name="actualPage" theme="simple" id="actualPagePopup"/>
		<s:hidden name="actionURL" theme="simple" id="actionPopupURL" value="%{#urlAccionMoreResults}"/>
		
		<s:hidden theme="simple" id="actionSelectURL" value="%{#urlAccionSelect}"/>
		<s:hidden theme="simple" id="accionFiltroPopup" value="%{#urlAccionSearch}"/>
		<s:hidden theme="simple" id="idDetalleElementoSelect" value="%{#identificadorDetalleElementoSeleccionado}"/>
		<s:hidden theme="simple" id="idBotonSelectPopup" value="%{#identificadorBotonPopup}"/>
		
		
		<div class="popup-filtro edicion-formulario" id="formularioBusqueda">
			<div class="formulario-contenido">
				
				<s:include value="%{#vistaFiltroPopup}">
				</s:include>

				<button class="formulario-boton-buscar-popup col-100" type="button" onclick="searchPopup()"><s:property value="getText('global.search')" /></button>
			</div>
		</div>
		<div class="popup-listado" id="listadoFiltradoPopup">
			<table class="listado-tabla listado-tabla--seleccion">
	           	<thead>
	            	<s:include value="%{#vistaHeaderTablePopup}"/>
	            </thead>
	            <s:include value="%{#vistaBodyTablePopup}"/>
	        </table>

	        <s:if test="hayMas">
	        	<div class="spinner-carga-Popup"><img src="../img/ajax-loader.gif" alt="Cargando…"></div>
	        </s:if>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/paginacionPopup.js" type="text/javascript"></script>
</div>
