<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<tbody id="items">
	<s:hidden name="hayMas"/>
	<s:iterator value="list" status="regionStatus">
		<tr class="<s:if test="#regionStatus.odd == true ">odd</s:if><s:else>even</s:else><s:if test="destacado == 1 "> is-destacada</s:if>" id="rowId_<s:property value="id"/>">
			<input type="hidden" name="estadoMenu" value="${estadoMenu}" id="estadoMenu">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('region')"/>"><s:property value="region.nombre"/></td>
			<td title="<s:property value="getText('region')"/>"><s:property value="sector.nombre"/></td>
			<td title="<s:property value="getText('centro')"/>"><s:property value="centro.nombre"/></td>
			<td title="<s:property value="getText('unidadMantenimiento')"/>"><s:property value="unidadMantenimiento.nombre"/></td>			
			<td title="<s:property value="getText('global.activo')"/>"><s:property value="activo"/></td>
			<td title="<s:property value="getText('global.fechaBaja')"/>"><s:date format="dd/MM/yyyy HH:mm:ss" name="fechaBaja"/></td>
		</tr>
	</s:iterator>
</tbody>