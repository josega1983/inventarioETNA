<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="formulario-contenido">
	<s:push value="elemento">
		<h2 class="titulo-seccion"><s:property value="getText('sector')"/></h2>
		<div class="form-row">
			<div class="form-column col-30">
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.name')"/></span>
						<span class="dato"><s:property  value="nombre"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-min">
						<span class="etiqueta"><s:property value="getText('global.activo')"/></span>
						<span class="dato"><s:property  value="activo"/></span>
					</label>
				</div>
			</div>
			<div class="form-column col-60">
				<s:if test="activo == 'NO'">
					<div class="form-row">
						<label class="col-min">
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
