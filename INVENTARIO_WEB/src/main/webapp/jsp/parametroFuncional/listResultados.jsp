<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<tbody id="items">
	<s:hidden name="hayMas"/>
	<s:iterator value="list" status="parametroFuncionalStatus">
		<tr class="<s:if test="#parametroFuncionalStatus.odd == true ">odd</s:if><s:else>even</s:else><s:if test="destacado == 1 "> is-destacada</s:if>" id="rowId_<s:property value="id"/>">
			<input type="hidden" name="estadoMenu" value="${estadoMenu}" id="estadoMenu">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('global.name')"/>"><s:property value="nombre"/></td>
			<td title="<s:property value="getText('global.descripcion')"/>" ><s:property value="descripcion"/></td>
			<td title="<s:property value="getText('global.descripcion')"/>" >
				<s:if test="configuracionParametroFuncionalList != null && configuracionParametroFuncionalList.size()>0">
					<s:iterator value="configuracionParametroFuncionalList" var="configuracionParametroFuncional" status="configuracionParametroFuncionalStatus">
						<s:property value="nombreCampo"/><s:if test="!#configuracionParametroFuncionalStatus.last">,</s:if>
					</s:iterator>
				</s:if>
			</td>
			<td title="<s:property value="getText('global.activo')"/>"><s:property value="activo"/></td>
			<td title="<s:property value="getText('global.fechaBaja')"/>"><s:date format="dd/MM/yyyy HH:mm:ss" name="fechaBaja"/></td>
		</tr>
	</s:iterator>
</tbody>