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
	<s:hidden name= "elemento.fechaAlta"/>
			<div class="formulario-contenido" id="formulario-region">
				<fieldset>
					<h2 class="titulo-seccion"><s:property value="getText('elemento')"/></h2>
					<div class="form-row">
						<label class="col-mid obligatorio">
							<span class="etiqueta"><s:property value="getText('global.name')"/></span>
							<span class="dato"><input type="text" required="true" value="${nombre}" maxlength="30" name="nombre" /></span>
						</label>
					</div>
					<div class="form-row">
						<s:include value="/jsp/common/botonSelectPopup.jsp">
							<s:param name="obligatorio">true</s:param>
							<s:param name="urlAccionCargar"><s:url namespace='/familiaElemento' action='loadPopup'/></s:param>
							<s:param name="etiquetaBotonPopup"><s:text name="familiaElemento"/></s:param>
							<s:param name="identificadorBotonPopup">idFamiliaElementoBotonPopup</s:param>
							<s:param name="textoElementoSeleccionado"><s:if test="familiaElemento != null && familiaElemento.id != null"><s:property value="familiaElemento.nombre"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
							<s:param name="idPopup">idFamiliasElementoPopup</s:param>
							<s:param name="excluirPopup">NO</s:param>
						</s:include>
					</div>
					<s:include value="/jsp/familiaElemento/popup/detalleFamiliaElementoSeleccionadaPopup.jsp"/>	
					<div class="form-row">
						<label class="col-max obligatorio">
							<span class="etiqueta"><s:property value="getText('estadoElemento')"/></span>
							<span class="dato">
								<s:select list="estadoElementoList"  listKey="id"   listValue="nombre" value="estadoElemento.id" name="estadoElemento.id"  headerKey="-1" headerValue="Seleccione un estado" required="true" />
							</span>
						</label>
					</div>				
					<div class="form-row">
						<label class="col-max obligatorio">
							<span class="etiqueta"><s:property value="getText('global.descripcion')"/></span>
							<span class="dato"><input type="text" value="${descripcion}" maxlength="500" name="descripcion" /></span>
						</label>
					</div>
					<div class="form-row">						
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
							<span class="dato"><input type="text" value="${observaciones}" maxlength="500" name="observaciones" /></span>
						</label>
					</div>
				</fieldset>
			</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>