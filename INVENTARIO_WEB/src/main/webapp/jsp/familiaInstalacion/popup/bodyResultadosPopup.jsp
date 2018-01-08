<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<s:hidden name="hayMas" id="hayMasPopup" theme="simple"/>
<tbody id="itemsPopup">
	<s:iterator var="region" value="popupList" status="RegionStatus">
		<tr ondblclick="elementoSeleccionadoPopup('<s:property value="id"/>');" 
			class="<s:if test="#RegionStatus.odd == true ">odd</s:if><s:else>even</s:else>" id="rowId_<s:property value="id"/>">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('global.name')"/>"><s:property value="nombre"/></td>
			<td title="<s:property value="getText('familiasInstalaciones.area')"/>"><s:property value="area"/></td>
		</tr>
	</s:iterator>
</tbody>
