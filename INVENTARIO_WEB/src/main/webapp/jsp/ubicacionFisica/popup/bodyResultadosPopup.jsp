<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<s:hidden name="hayMas" id="hayMasPopup" theme="simple"/>
<tbody id="itemsPopup">
	<s:iterator var="region" value="popupList" status="RegionStatus">
		<tr ondblclick="elementoSeleccionadoPopup('<s:property value="id"/>');" 
			class="<s:if test="#RegionStatus.odd == true ">odd</s:if><s:else>even</s:else>" id="rowId_<s:property value="id"/>">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('region')"/>"><s:property value="region.nombre"/></td>
			<td title="<s:property value="getText('localizacion')"/>"><s:property value="localizacion.nombre"/></td>
			<td title="<s:property value="getText('emplazamiento')"/>"><s:property value="emplazamiento.nombre"/></td>
		</tr>
	</s:iterator>
</tbody>
