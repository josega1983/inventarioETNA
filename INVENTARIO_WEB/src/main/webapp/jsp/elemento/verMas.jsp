<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<fieldset>
	<div class="formulario-contenido">
	<s:push value="elemento">
		<h2 class="titulo-seccion"><s:property value="getText('parametroFuncional')"/></h2>
		<label class="col-40">
			<span class="etiqueta"><s:property value="getText('global.name')"/></span>
			<span class="dato"><s:property  value="nombre"/></span>
		</label>
		<label class="col-60">
			<span class="etiqueta"><s:property value="getText('global.descripcion')"/></span>
			<span class="dato"><s:property  value="descripcion"/></span>
		</label>
		<label class="col-60">
			<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
			<span class="dato"><s:property  value="observaciones"/></span>
		</label>
		<label class="col-40">
			<span class="etiqueta"><s:property value="getText('global.activo')"/></span>
			<span class="dato"><s:property  value="activo"/></span>
		</label>
		<s:if test="activo == 'NO'">
			<label class="col-30">
				<span class="etiqueta"><s:property value="getText('global.fechaBaja')"/></span>
				<span class="dato"><s:date name="fechaBaja" id="idFechaBaja" format="dd/MM/yyyy HH:mm:ss"/></span>
			</label>
		</s:if>
	</s:push>
 </div>
</fieldset>
<s:form>
	<div class="botoneraAccionesPie form-row">
		       	<s:submit key="global.modificar" action="edit" theme="simple" cssClass="formulario-boton"/>
	</div>
	<s:hidden id="idFilaSeleccionada" name="idFilaSeleccionada"/>
</s:form>