<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<div class="formulario-contenido form-view">
	<s:push value="elemento">
		<h2 class="titulo-seccion"><s:property value="getText('familiasInstalaciones')"/></h2>
		<div class="form-row">
			<div class="form-column col-40">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.name')"/></span>
						<span class="dato"><s:property  value="nombre"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('familiasInstalaciones.area')"/></span>
						<span class="dato"><s:property  value="area"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.activo')"/></span>
						<span class="dato"><s:property  value="activo"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.fechaAlta')"/></span>
						<span class="dato"><s:date name="fechaAlta" id="idFechaAlta" format="dd/MM/yyyy HH:mm:ss"/></span>
					</label>
				</div>
				<s:if test="activo == 'NO'">
					<div class="form-row">
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('global.fechaBaja')"/></span>
							<span class="dato"><s:date name="fechaBaja" id="idFechaBaja" format="dd/MM/yyyy HH:mm:ss"/></span>
						</label>
					</div>
				</s:if>
			</div>
			<div class="form-column col-60">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('familiasInstalaciones.area')"/></span>
						<span class="dato"><s:property  value="area"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
						<span class="dato"><s:property  value="observaciones"/></span>
					</label>
				</div>
			</div>
		</div>
	</s:push>
</div>

<s:form>
	<div class="botoneraAccionesPie form-row">
		       	<s:submit key="global.modificar" action="edit" theme="simple" cssClass="formulario-boton"/>
	</div>
	<s:hidden id="idFilaSeleccionada" name="idFilaSeleccionada"/>
</s:form>