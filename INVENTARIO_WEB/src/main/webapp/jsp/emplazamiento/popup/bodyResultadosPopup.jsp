<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<s:hidden name="hayMas" id="hayMasPopup" theme="simple"/>
<tbody id="itemsPopup">
	<s:iterator var="emplazamiento" value="popupList" status="emplazamientoStatus">
		<tr ondblclick="elementoSeleccionadoPopup('<s:property value="id"/>');" 
			class="<s:if test="#emplazamientoStatus.odd == true ">odd</s:if><s:else>even</s:else>" id="rowId_<s:property value="id"/>">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('global.name')"/>"><s:property value="nombre"/></td>
			<td title="<s:property value="getText('tipoEmplazamiento')"/>"><s:property value="tipoEmplazamiento.nombre"/></td>
		</tr>
	</s:iterator>
</tbody>
