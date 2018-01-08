<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="formulario-contenido form-view">
	<s:push value="elemento">
		<h2 class="titulo-seccion"><s:text name="ubicacionLogica"/></h2>

		<div class="form-row">
			<div class="form-column col-30">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('region')"/></span>
						<span class="dato"><s:property  value="region.nombre"/></span>
					</label>
				</div>
			</div>
			<div class="form-column col-35">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('sector')"/></span>
						<span class="dato"><s:property  value="sector.nombre"/></span>
					</label>
				</div>
			</div>
			<div class="form-column col-35">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('centro')"/></span>
						<span class="dato"><s:property  value="centro.nombre"/></span>
					</label>
				</div>
			</div>
		</div>

		<div class="form-row">
			<div class="form-column col-30">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('unidadMantenimiento')"/></span>
						<span class="dato"><s:property  value="unidadMantenimiento.nombre"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.activo')"/></span>
						<span class="dato"><s:property  value="activo"/></span>
					</label>
				</div>
			</div>
			<div class="form-column col-70">
				<s:if test="activo == 'NO'">
					<div class="form-row">
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('global.fechaBaja')"/></span>
							<span class="dato"><s:date name="fechaBaja" id="idFechaBaja" format="dd/MM/yyyy HH:mm:ss"/></span>
						</label>
					</div>
				</s:if>
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
		<s:if test="elemento.activo == 'SI'">
			<s:submit key="global.modificar" action="edit" theme="simple" cssClass="formulario-boton"/>
		</s:if>
	</div>
	<s:hidden id="idFilaSeleccionada" name="idFilaSeleccionada"/>
</s:form>