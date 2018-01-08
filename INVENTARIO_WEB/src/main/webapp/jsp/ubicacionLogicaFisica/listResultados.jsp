<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<tbody id="items">
	<s:hidden name="hayMas"/>
	<s:iterator value="list" status="regionStatus">
		<tr class="<s:if test="#regionStatus.odd == true ">odd</s:if><s:else>even</s:else><s:if test="destacado == 1 "> is-destacada</s:if>" id="rowId_<s:property value="id"/>">
			<input type="hidden" name="estadoMenu" value="${estadoMenu}" id="estadoMenu">
			<td title="<s:property value="getText('global.id')"/>" class="columnaIdentificativa"><s:property value="id"/></td>
			<td title="<s:property value="getText('ubicacionFisica')"/>"><s:property value="ubicacionFisica.region.nombre"/>
																		-<s:property value="ubicacionFisica.sector.nombre"/>
																		-<s:property value="ubicacionFisica.localizacion.nombre"/>
																		-<s:property value="ubicacionFisica.emplazamiento.nombre"/></td>
			<td title="<s:property value="getText('ubicacionLogica')"/>"><s:property value="ubicacionLogica.region.nombre"/>
																		-<s:property value="ubicacionLogica.sector.nombre"/>
																		-<s:property value="ubicacionLogica.centro.nombre"/>
																		-<s:property value="ubicacionLogica.unidadMantenimiento.nombre"/></td>
			<td title="<s:property value="getText('global.observaciones')"/>"><s:property value="observaciones"/></td>
		</tr>
	</s:iterator>
</tbody>