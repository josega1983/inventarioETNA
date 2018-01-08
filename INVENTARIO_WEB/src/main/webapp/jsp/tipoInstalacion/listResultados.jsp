<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<tbody id="items">
	<s:hidden name="hayMas"/>
	<s:iterator value="list" status="regionStatus">
		<tr class="<s:if test="#regionStatus.odd == true ">odd</s:if><s:else>even</s:else><s:if test="destacado == 1 "> is-destacada</s:if>" id="rowId_<s:property value="id"/>">
			<input type="hidden" name="estadoMenu" value="${estadoMenu}" id="estadoMenu">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('familiasInstalaciones')"/>"><s:property value="familiaInstalacion.nombre"/></td>
			<td title="<s:property value="getText('global.marca')"/>"><s:property value="marca"/></td>
			<td title="<s:property value="getText('global.modelo')"/>"><s:property value="modelo"/></td>
			<td title="<s:property value="getText('global.activo')"/>"><s:property value="activo"/></td>
			<td title="<s:property value="getText('global.fechaAlta')"/>"><s:date format="dd/MM/yyyy HH:mm:ss" name="fechaAlta"/></td>
			<td title="<s:property value="getText('global.fechaBaja')"/>"><s:date format="dd/MM/yyyy HH:mm:ss" name="fechaBaja"/></td>
		</tr>
	</s:iterator>
</tbody>