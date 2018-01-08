<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<s:hidden name="hayMas" id="hayMasPopup" theme="simple"/>
<tbody id="itemsPopup">
	<s:iterator var="centro" value="popupList" status="CentroStatus">
		<tr ondblclick="elementoSeleccionadoPopup('<s:property value="id"/>');" 
			class="<s:if test="#CentroStatus.odd == true ">odd</s:if><s:else>even</s:else>" id="rowId_<s:property value="id"/>">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('global.name')"/>"><s:property value="nombre"/></td>
		</tr>
	</s:iterator>
</tbody>
