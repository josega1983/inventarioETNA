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
		</div>
		<div class="form-row">
			<label class="col-mid">
				<span class="etiqueta"><s:property value="getText('global.observaciones')" /></span>
				<s:textfield maxlength="500" name="filtro.observaciones" key="global.name"/>
			</label>
		</div>
	</div>

	<div class="buscador-botones">
			<button type="button" class="buscador-limpiar" onclick="limpiarFormularioBusqueda(this);"><s:property value="getText('global.limpiar')" /></button>
			<s:submit cssClass="buscador-buscar" key="global.search" />
	</div>
</s:form>