<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:set var="obligatorio">${param.obligatorio}</s:set>
<s:set var="urlAccionCargar">${param.urlAccionCargar}</s:set>
<s:set var="etiquetaBotonPopup">${param.etiquetaBotonPopup}</s:set>
<s:set var="identificadorBotonPopup">${param.identificadorBotonPopup}</s:set>
<s:set var="textoElementoSeleccionado">${param.textoElementoSeleccionado}</s:set>
<s:set var="idPopup">${param.idPopup}</s:set>
<s:set var="excluirPopup">${param.excluirPopup}</s:set>


<div class="componenteBotonPopup col-100">
	<label <s:if test ="#obligatorio == 'true'">obligatorio"</s:if>>
		<span class="etiqueta"><s:property value="#etiquetaBotonPopup"/></span>
		<span class="dato">
			<button type="button" id="<s:property value="#identificadorBotonPopup"/>" type="button" class="edicion-buscar" data-popup="<s:property value="#idPopup"/>" data-action="<s:property value="#urlAccionCargar"/>">
				<s:property value="#textoElementoSeleccionado"/> 
			</button> 
		</span> 
	</label> 
</div>

<s:if test ="#excluirPopup != 'SI'">
	<div class="popup" id="<s:property value="#idPopup"/>" style="display: none;"></div>
</s:if>
