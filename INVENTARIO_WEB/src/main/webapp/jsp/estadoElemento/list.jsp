<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<div class="listado">
	<table class="listado-tabla listado-tabla-2-columnas" data-columnas="4">
		<thead>
			<s:actionerror/>
			<s:hidden name="actualPage"/>
			<s:hidden name="actionURL" value="moreResults"/>
			<tr class="filtrado">
				<s:if test="filtro != null && filtro.camposFiltro!= null">
					<th colspan="14">
						<s:url var="estadoElementoUrl" namespace="/estadoElemento" action="list"/>
						<b><s:property value="getText('global.filtrado')"/></b> <s:property value="filtro.camposFiltro"/> <s:a href="%{estadoElementoUrl}"><s:property value="getText('global.quitarFiltro')"/></s:a>
					</th>
				</s:if>
			</tr>
			</th>
			<tr data-id="1">
				<th scope="col" style="display: none;"></th>
				<th scope="col"><s:property value="getText('global.id')"/></th>
				<th scope="col"><s:property value="getText('global.name')"/></th>
				<th scope="col"><s:property value="getText('global.activo')"/></th>
				<th scope="col"><s:property value="getText('global.fechaAlta')"/></th>
				<th scope="col"><s:property value="getText('global.fechaBaja')"/></th>
				<th scope="col"><s:property value="getText('global.observaciones')"/></th>
			</tr>
		</thead>

		<s:if test="list.size() > 0">
			<s:include value="listResultados.jsp"/>
		</s:if>
	</table>

	<s:if test="list.size() == 0">
		<label class="col-100">
			<span class="etiqueta"><s:property value="getText('global.noHayDatos')"/></span>
		</label>
	</s:if>
	<s:if test="hayMas">
		<div class="spinner-carga"><img src="../img/common-enaire/ajax-loader.gif" alt="Cargando…"></div>
	</s:if>
	<s:form name="changeStatusForm" theme="simple" id="formularioAccionesRegion">
		<s:hidden id="idFilaSeleccionada" name="idFilaSeleccionada"/>
		<div class="listado-opciones-popup">
			<div class="listado-opcion" id="menu_verMas" style="display:none">
				<s:submit key="global.verMas" action="showMore" theme="simple"/>.
			</div>
			<div class="listado-opcion" id="menu_modificar" style="display:none">
					<s:submit key="global.modificar" action="edit"/>
			</div>
			<div class="listado-opcion" id="menu_eliminar" style="display:none">
					<button type="button" class="formulario-boton" onclick="confirmarAccion('formularioAccionesRegion', 'Vas a dar de baja un Estado de Elemento. ¿Desea continuar?', 'delete');"><s:property value="getText('global.delete')" /></button>
			</div>
			<div class="listado-opcion" id="menu_reactivar" style="display:none">
					<button type="button" class="formulario-boton" onclick="confirmarAccion('formularioAccionesRegion', 'Vas a reactivar un Estado de Elemento. ¿Desea continuar?', 'reactivar');"><s:property value="getText('global.reactivar')" /></button>
			</div>
			<div class="listado-opcion listado-opcion-cerrar" id="menu_cerrar" style="display:none">
				<a href="javascript:void(0)">Cerrar</a>
			</div>
		</div>
	</s:form>
</div>

