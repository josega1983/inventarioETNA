<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<h1 class="buscador-titulo hidden"><s:property value="getText('global.search')"/></h1>
<s:form cssClass="buscador-formulario" action="search" theme="simple" id="formularioBusqueda">
	<s:if test="hasActionErrors()">
		<s:iterator value="actionErrors">
			<div class="alert alert-danger alert-dismissable">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
				<s:property/>
			</div>
		</s:iterator>
	</s:if>
	<div class="formulario-contenido form-view">
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('global.id')" /></span>
				<input type="number" min="1" max="999999999" name="filtro.id" value="<s:property value='filtro.id'/>" id="formularioBusqueda_filtro_id">
			</label>
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('global.activo')" /></span>
				<select name="filtro.activo" id="filtroActivo" onchange="changeIndicadorActivo(this);">
					<option value=""><s:property value="getText('global.select_list')"/></option>
					<option <s:if test="filtro.activo == 'SI'">selected="selected"</s:if> value="SI"><s:property value="getText('global.si')"/></option>
					<option <s:if test="filtro.activo == 'NO'">selected="selected"</s:if> value="NO"><s:property value="getText('global.no')"/></option>
				</select>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('region')" /></span>
				<s:textfield maxlength="30" name="filtro.region.nombre" key="global.region"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('sector')" /></span>
				<s:textfield maxlength="30" name="filtro.sector.nombre" key="global.name"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('centro')" /></span>
				<s:textfield maxlength="30" name="filtro.centro.nombre" key="global.name"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('unidadMantenimiento')" /></span>
				<s:textfield maxlength="30" name="filtro.unidadMantenimiento.nombre" key="global.name"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('global.observaciones')" /></span>
				<s:textfield maxlength="500" name="filtro.observaciones" key="global.name"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid date" id="fechaBaja">
				<span class="etiqueta"><s:property value="getText('global.fechaBaja')"/></span>
				<span class="dato col-mid">
					<div class="form-row">
						<div class="form-column col-45">
							<div class="form-row">
								<s:date name="filtro.fechaBajaDesde" var="fechaBajaDesdeId" format="dd/MM/yyyy"/>
								<s:textfield name="filtro.fechaBajaDesde" value="%{fechaBajaDesdeId}" key="global.desde"/>
							</div>
						</div>
						<div class="form-column col-10 separator">
							<span class="etiqueta">-</span>
						</div>
						<div class="form-column col-45">
							<div class="form-row">
								<s:date name="filtro.fechaBajaHasta" var="fechaBajaHastaId" format="dd/MM/yyyy"/>
								<s:textfield name="filtro.fechaBajaHasta" value="%{fechaBajaHastaId}" key="global.hasta"/>
							</div>
						</div>
					</div>
				</span>
			</label>
		</div>
	</div>

	<div class="buscador-botones">
			<button type="button" class="buscador-limpiar" onclick="limpiarFormularioBusqueda(this);"><s:property value="getText('global.limpiar')" /></button>
			<s:submit cssClass="buscador-buscar" key="global.search" />
	</div>
</s:form>

<script>
function show_field(parameters, aplicar) {
	var campos = parameters.replace(/\s+/g, '').split(",");
	for (i = 0; i < campos.length; i++) {
		$("#" + campos[i]).toggle();
	}

	$("#" + aplicar).val($("#" + aplicar).val() === "true" ? "false" : "true");
}

function changeIndicadorActivo(sel) {
	if(sel.value === 'SI' || sel.value === '') {
		//Se oculta la fecha de baja.
		$("#fechaBaja").find('input,textarea,select').val('');
		$("#fechaBaja").hide();
	}
	if(sel.value === 'NO') {
		//Se habilita la fecha de baja.
		$("#fechaBaja").show();
	}
}

$("#filtroActivo").trigger("change");
</script>