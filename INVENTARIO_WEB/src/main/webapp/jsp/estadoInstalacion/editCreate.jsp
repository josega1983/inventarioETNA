<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="edicion edicion-formulario">
	<s:form id="formularioCrearRegion" name="formularioCrearRegion" cssClass="edicion-formulario form-view" theme="simple">
		<s:if test="hasActionErrors()">
			<s:iterator value="actionErrors">
		    	<div class="alert alert-danger alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					<s:property/>
				</div>
			</s:iterator>
		</s:if>
	<s:push value="elemento">
	<s:hidden name="elemento.id"/>
	<s:hidden name="elemento.activo"/>
	<s:hidden name="elemento.fechaAlta"/>	
		<div class="formulario-contenido form-view" id="formulario-region">

			<h2 class="titulo-seccion"><s:property value="getText('estadoInstalaciones')"/></h2>

			<div class="form-row">
				<label class="col-max obligatorio">
					<span class="etiqueta"><s:property value="getText('global.name')"/></span>
					<span class="dato"><input type="text" required="true"  value="${nombre}"  maxlength="30" name="nombre" /></span>
				</label>
			</div>

			<div class="form-row">
				<label class="col-max">
					<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
					<span class="dato"><textarea type="text"  maxlength="500"  value="" name="observaciones"><s:property value="observaciones"/></textarea></span>
				</label>
			</div>

		</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>