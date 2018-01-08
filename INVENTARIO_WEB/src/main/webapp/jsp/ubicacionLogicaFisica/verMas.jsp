<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>


	<div class="formulario-contenido form-view">
		<s:push value="elemento">

			<h2 class="titulo-seccion"><s:property value="getText('ubicacionLogicaFisica')"/></h2>

			<div class="form-row">
				<div class="form-column col-50">
					<div class="form-row">
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('ubicacionLogica')"/></span>
							<span class="dato"><s:property value="ubicacionLogica.region.nombre"/>
											-<s:property value="ubicacionLogica.sector.nombre"/>
											-<s:property value="ubicacionLogica.centro.nombre"/>
											-<s:property value="ubicacionLogica.unidadMantenimiento.nombre"/></span>
						</label>
					</div>
				</div>

				<div class="form-column col-50">				
					<div class="form-row">
						<label class="col-max">
							<span class="etiqueta"><s:property value="getText('ubicacionFisica')"/></span>
							<span class="dato"><s:property value="ubicacionLogica.region.nombre"/>
												-<s:property value="ubicacionLogica.sector.nombre"/>
												-<s:property value="ubicacionLogica.centro.nombre"/>
												-<s:property value="ubicacionLogica.unidadMantenimiento.nombre"/></span>
						</label>
					</div>
				</div>
			</div>
				
				
			<div class="form-row">			
				<label class="col-max">
					<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
					<span class="dato"><s:property  value="observaciones"/></span>
				</label>
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