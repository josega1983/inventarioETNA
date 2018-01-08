<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<div class="formulario-contenido form-view">
	<s:push value="elemento">
		<h2 class="titulo-seccion"><s:property value="getText('parametroFuncional')"/></h2>
		<div class="form-row">
			<div class="form-column col-70">
				<div class="form-row">
					<label class="col-50">
						<span class="etiqueta"><s:property value="getText('global.name')"/></span>
						<span class="dato"><s:property  value="nombre"/></span>
					</label>
				</div>
				<div class="form-row">
					<label class="col-max">
						<span class="etiqueta"><s:property value="getText('global.descripcion')"/></span>
						<span class="dato"><s:property  value="descripcion"/></span>
					</label>
				</div>
			</div>
			<div class="form-column col-30">
				<div class="form-row">
					<label class="col-20">
						<span class="etiqueta"><s:property value="getText('global.activo')"/></span>
						<span class="dato"><s:property  value="activo"/></span>
					</label>
				</div>
				<s:if test="activo == 'NO'">
					<div class="form-row">
						<label class="col-30">
							<span class="etiqueta"><s:property value="getText('global.fechaBaja')"/></span>
							<span class="dato"><s:date name="fechaBaja" id="idFechaBaja" format="dd/MM/yyyy HH:mm:ss"/></span>
						</label>
					</div>
				</s:if>
			</div>
		</div>
		<s:if test="configuracionParametroFuncionalList != null && configuracionParametroFuncionalList.size()>0">
			<fieldset>
				<div class="plegable is-plegado">
					<h2 class="plegable-titulo"><s:property value="getText('parametroFuncional.camposParametros')" /></h2>
						<div class="plegable-contenido">
							<table class="listado-tabla">
								<thead>
									<tr>
										<th scope="col"><s:property value="getText('parametroFuncional.campos')" /></th>
										<th scope="col"><s:property value="getText('parametroFuncional.campoNombre')" /></th>
										<th scope="col"><s:property value="getText('parametroFuncional.tipo')" /></th>
									</tr>
								</thead>
								<s:iterator value="configuracionParametroFuncionalList" var="configuracionParametroFuncional" status="configuracionParametroFuncionalStatus" >
								<tr>
									<td title="<s:property value="getText('parametroFuncional.campos')"/>">
										<s:property value="getText('parametroFuncional.campo')"/><s:property value='#configuracionParametroFuncionalStatus.index + 1'/>
									</td>
									<td title="<s:property value="getText('parametroFuncional.campos')"/>">
										<s:property  value="nombreCampo"/>
									</td>
									<td title="<s:property value="getText('parametroFuncional.tipo')"/>">
										<s:property  value="tipoCampoParametro.nombre"/>
									</td>
								</tr>
								</s:iterator>
							</table>
						</div>
					</div>
			</fieldset>
		</s:if>
	</s:push>
 </div>

<s:form>
	<div class="botoneraAccionesPie form-row">
		       	<s:submit key="global.modificar" action="edit" theme="simple" cssClass="formulario-boton"/>
	</div>
	<s:hidden id="idFilaSeleccionada" name="idFilaSeleccionada"/>
</s:form>