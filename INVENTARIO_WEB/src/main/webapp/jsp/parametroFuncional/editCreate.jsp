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
				<div class="form-column">
					<h2 class="titulo-seccion"><s:property value="getText('parametroFuncional')"/></h2>
					<div class="form-row">
						<label class="col-mid obligatorio">
							<span class="etiqueta"><s:property value="getText('global.name')"/></span>
							<span class="dato"><input type="text" required="true" value="${nombre}" maxlength="30" name="nombre" /></span>
						</label>
						<label class="col-max obligatorio">
							<span class="etiqueta"><s:property value="getText('global.descripcion')"/></span>
							<span class="dato"><input type="text" value="${descripcion}" maxlength="500" name="descripcion" /></span>
						</label>
					</div>
				</div>
				<div class="form-column">
					<legend><s:property value="getText('parametroFuncional.textoConfigurar')"/></legend>
					<s:if test="configuracionParametroFuncionalList != null && configuracionParametroFuncionalList.size()>0">
						<s:iterator value="configuracionParametroFuncionalList" status="configuracionParametroFuncionalStatus" var="configuracionParametroFuncionalValue"> 
							<div class="col-row campoMultiple" style="padding-left:0px;" rel="configuracionParametroFuncionalValue">
								<s:hidden name="configuracionParametroFuncionalList[<s:property value='#configuracionParametroFuncionalStatus.index'/>].id"
								value='<s:property value ="#configuracionParametroFuncionalValue.id"/>' id="configuracionParametroFuncionalList[<s:property value='#configuracionParametroFuncionalStatus.index'/>].id"/>

								<label class="col-max obligatorio">
									<span class="etiqueta"><s:property value="getText('parametroFuncional.campo')"/></span>
									<span class="dato"><input type="text"  maxlength="500" componenteNombreId="<s:property value='#configuracionParametroFuncionalStatus.index + 1' />"  name="configuracionParametroFuncionalList[<s:property value='#configuracionParametroFuncionalStatus.index'/>].nombreCampo" 
									  id="configuracionParametroFuncionalList[<s:property value='#configuracionParametroFuncionalStatus.index'/>].nombreCampo" value='<s:property value ="#configuracionParametroFuncionalValue.nombreCampo"/>'/></span>								
								</label>

								<label class="col-max obligatorio">
									<span class="etiqueta"><s:property value="getText('parametroFuncional.tipo')"/></span>
									<span class="dato">
										<select required="true" name="configuracionParametroFuncionalList[<s:property value='#configuracionParametroFuncionalStatus.index'/>].tipoCampoParametro.id" 
										id="configuracionParametroFuncionalList[<s:property value='#configuracionParametroFuncionalStatus.index'/>].tipoCampoParametro.id" componenteNombreId="<s:property value='#configuracionParametroFuncionalStatus.index + 1' />">
											<option value=""><s:property value="getText('global.select_list')"/></option>
											<s:iterator value="tipoCampoParametroList" status="tipoCampoParametroListStatus2" var="tipoCampoParametroListObj">
												<s:if test="#configuracionParametroFuncionalValue.tipoCampoParametro.id == #tipoCampoParametroListObj.id">
													<option selected="selected" value="<s:property value="#tipoCampoParametroListObj.id"/>"><s:property value="#tipoCampoParametroListObj.nombre"/></option>
												</s:if>
												<s:else>
													<option value="<s:property value="#tipoCampoParametroListObj.id"/>"><s:property value="#tipoCampoParametroListObj.nombre"/></option>
												</s:else>
											</s:iterator>
										</select>
									</span>
								</label>

							</div>
						</s:iterator>
					</s:if>
					<s:else>
						<div class="col-row campoMultiple" style="padding-left:0px;" rel="configuracionParametroFuncionalList">

							<label class="col-max obligatorio">
								<span class="etiqueta"><s:property value="getText('parametroFuncional.campo')"/></span>
								<span class="dato"><input type="text" required="true" value="${configuracionParametroFuncionalList[0].nombreCampo}" maxlength="500" name="configuracionParametroFuncionalList[0].nombreCampo" componenteNombreId="1" id="configuracionParametroFuncionalList[0].nombreCampo" /></span>
							</label>

							<label class="col-max obligatorio">
								<span class="etiqueta"><s:property value="getText('parametroFuncional.tipo')"/></span>
								<span class="dato">
									<select name="configuracionParametroFuncionalList[0].tipoCampoParametro.id" componenteNombreId="1" id="configuracionParametroFuncionalList[0].tipoCampoParametro.id">
										<option value=""><s:property value="getText('global.select_list')"/></option>
										<s:iterator value="tipoCampoParametroList" status="tipoCampoParametroListStatus2" var="tipoCampoParametroListObj">
											<option value="<s:property value="#tipoCampoParametroListObj.id"/>"><s:property value="#tipoCampoParametroListObj.nombre"/></option>
										</s:iterator>
									</select>
								</span>
							</label>
						</div>
					</s:else>				
				</div>
			</div>
	</div>
	</s:push>
	<div class="botoneraAccionesPie">
        	<s:submit key="global.save" action="saveOrUpdate" theme="simple" cssClass="formulario-boton"/>
	</div>	
	</s:form>