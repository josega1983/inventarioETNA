<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<h1 class="buscador-titulo"><s:property value="getText('global.search')"/></h1>
<s:form cssClass="buscador-formulario" action="search" theme="simple" id="formularioBusqueda" name="formularioBusqueda">
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
				<s:property value="getText('global.id')" />
				<input type="number" min="1" max="999999999" name="filtro.id" value="<s:property value='filtro.id'/>" id="formularioBusqueda_filtro_id">
			</label>
			<label class="col-mid">
				<s:property value="getText('global.activo')" />
				<select name="filtro.activo" id="filtroActivo" onchange="changeIndicadorActivo(this);">
					<option value=""><s:property value="getText('global.select_list')"/></option>
					<option <s:if test="filtro.activo == 'SI'">selected="selected"</s:if> value="SI"><s:property value="getText('global.si')"/></option>
					<option <s:if test="filtro.activo == 'NO'">selected="selected"</s:if> value="NO"><s:property value="getText('global.no')"/></option>
				</select>
			</label>
		</div>
		<div class="form-row">

			<label class="col-mid">
				<s:property value="getText('global.name')" />
				<s:textfield maxlength="250" name="filtro.nombre" key="global.name"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<s:property value="getText('global.descripcion')" />
				<s:textfield maxlength="250" cssClass="col-30" name="filtro.descripcion" key="global.descripcion"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-mid">
				<s:property value="getText('global.observaciones')" />
				<s:textfield maxlength="250" cssClass="col-30" name="filtro.observaciones" key="global.observaciones"/>
			</label>
		</div>
		<div class="form-row">
			<label class="col-max date">
				<span class="etiqueta"><s:property value="getText('global.fechaAlta')"/></span>
				<span class="dato col-mid">
					<div class="form-row">
						<div class="form-column col-45">
							<div class="form-row">
								<s:date name="filtro.fechaAltaDesde" var="fechaCreacionDesdeId" format="dd/MM/yyyy"/>
								<s:textfield name="filtro.fechaAltaDesde" value="%{fechaCreacionDesdeId}" key="global.desde"/>
							</div>
						</div>
						<div class="form-column col-10 separator">
							<span class="etiqueta">-</span>
						</div>
						<div class="form-column col-45">
							<div class="form-row">
								<s:date name="filtro.fechaAltaHasta" var="fechaCreacionHastaId" format="dd/MM/yyyy"/>
								<s:textfield name="filtro.fechaAltaHasta" value="%{fechaCreacionHastaId}" key="global.hasta"/>
							</div>
						</div>
					</div>
				</span>
			</label>
		</div>
		<div class="form-row">
			<label class="col-max date" id="fechaBajaBusqueda" style="display: none;">
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
		$("#fechaBajaBusqueda").find('input,textarea,select').val('');
		$("#fechaBajaBusqueda").hide();
	}
	if(sel.value === 'NO') {
		//Se habilita la fecha de baja.
		$("#fechaBajaBusqueda").show();
	}
}

$("#filtroActivo").trigger("change");
</script>
