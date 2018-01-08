<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="visualizacion">
	<div class="visualizacion-formulario">
		<fieldset>
			<input type="hidden" ng-init="$root.editID = ${elemento.id}" value="${elemento.id}"/>
			<div class="formulario-contenido form-view" ng-controller="VerMasCtrl as vermas">
				<s:push value="elemento">
					<h2 class="titulo-seccion"><s:property value="getText('configuracionesTipo')"/></h2>
					<div class="form-row">
						<div class="form-column col-70">
							<div class="form-row">
								<label class="col-mid">
									<span class="etiqueta"><s:property value="getText('familiaInstalacion')"/></span>
									<span class="dato"><s:property  value="tipoInstalacion.familiaInstalacion.nombre"/></span>
								</label>
							</div>
							<div class="form-row">
								<label class="col-mid">
									<span class="etiqueta"><s:property value="getText('global.marca')"/></span>
									<span class="dato"><s:property  value="tipoInstalacion.marca"/></span>
								</label>
							</div>
							<div class="form-row">
								<label class="col-mid">
									<span class="etiqueta"><s:property value="getText('global.modelo')"/></span>
									<span class="dato"><s:property  value="tipoInstalacion.modelo"/></span>
								</label>
							</div>
							<div class="form-row">
								<label class="col-max">
									<span class="etiqueta"><s:property value="getText('global.observaciones')"/></span>
									<span class="dato"><s:property  value="observaciones"/></span>
								</label>
							</div>
						</div>

						<div class="form-column col-30">
							<div class="form-row">
								<label class="col-max">
									<span class="etiqueta"><s:property value="getText('global.activo')"/></span>
									<span class="dato"><s:property  value="activo"/></span>
								</label>
							</div>
							<div class="form-row">
								<label class="col-mid">
									<span class="etiqueta"><s:property value="getText('global.fechaAlta')"/></span>
									<span class="dato"><s:date name="fechaAlta" id="idFechaAlta" format="dd/MM/yyyy HH:mm:ss"/></span>
								</label>
							</div>
							<div class="form-row">
								<s:if test="activo == 'NO'">
									<label class="col-mid">
										<span class="etiqueta"><s:property value="getText('global.fechaBaja')"/></span>
										<span class="dato"><s:date name="fechaBaja" id="idFechaBaja" format="dd/MM/yyyy HH:mm:ss"/></span>
									</label>
								</s:if>
							</div>
						</div>
					</div>
					<fieldset>
						<div class="plegable">
							<h2 class="plegable-titulo">Configuración Tipo</h2>
							<div class="plegable-contenido">
								<div class="graph-container">
									<div ng-if="vermasCtrl.showCanvasLoader" class="loader-background">
										<div class="loader"></div>
									</div>
									<div id="graph"
										class="graph-canvas ui-droppable"
										resize-handler="$root.resizeCanvas()"
										ng-class="{'canvas-fullscreen': vermas.isFullscreen}">
									</div>
									<canvas-options-directive
										fullscreen-flag="vermas.isFullscreen"
										zoom-in-fn="vermas.zoomInCanvas()"
										zoom-out-fn="vermas.zoomOutCanvas()"
										fullscreen-fn="vermas.canvasFullscreen()"
										center-fn="vermas.centerTree()">
									</canvas-options-directive>
								</div>
							</div>
						</div>
					</fieldset>
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

		<form id="formularioVolverBack" action='/ETNAJ/MI/migas/volverBack' method='post'>
		</form>

		<script type="text/javascript">
			$(".botoneraAccionesPie").prepend("<input type='button' id='botonVolver' value='Volver' class='formulario-boton' formnovalidate=''>");

			$( "#botonVolver" ).click(function() {
				  $("#formularioVolverBack").submit();
			});
		</script>
	</div>
</div>