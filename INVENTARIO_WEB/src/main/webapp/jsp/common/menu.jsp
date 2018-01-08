<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<a class="menu-desplegar" ng-click="$root.openingMenu()">Desplegar</a>
<a class="menu-plegar" ng-click="$root.openingMenu()">Plegar</a>
<div class="menu-avatar">
<%-- 	<s:url var="usuarioDetalleURL" namespace="/usuario" action="#"/>
	<s:a theme="simple" cssClass="menu-avatar-enlace" href="%{usuarioDetalleURL}"> --%>
		<div class="menu-avatar-nombre"><s:property value="#session.USUARIO_AUTENTICADO.username" /></div>
<%-- 	</s:a> --%>
</div>
<nav class="menu-opciones">
	<ul class="menu-lista ">
		<li class="menu-item <s:if test ='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ELEMENTO == seccionSeleccionada'>activo</s:if>">
			<a href="#" class="icon icon-clipboard menu-opcion">
				<p><s:text name="menu.consultaGestion"/></p>
			</a>
		</li>

		<li class="menu-item">
			<a href="#" class="icon icon-clipboard menu-opcion">
				<p><s:text name="menu.otrasConsultas"/></p>
			</a>
		</li>

		<li class="menu-item submenu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA_FISICA == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_FISICA == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ESTADO_INSTALACION == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ESTADO_ELEMENTO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_EMPLAZAMIENTO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_EMPLAZAMIENTO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_LOCALIZACION == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UNIDAD_MANTENIMIENTO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CENTRO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_SECTOR == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_PROPIETARIO_ACTIVO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_REGION == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_RESPONSABLE_MANTENIMIENTO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_INSTALACION == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_INSTALACION == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_ELEMENTO == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_PARAMETRO_FUNCIONAL == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CONFIGURACION_FAMILIA == seccionSeleccionada ||
										@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CONFIGURACIONES_TIPO == seccionSeleccionada'>activo</s:if>">
			<a class="menu-desplegarSubmenu" href="javascript:void(0)"></a>
			<a href="#" class="icon icon-breafcase menu-opcion">
				<p><s:text name="menu.normalizacion"/></p>
			</a>

			<ul class="submenu">
				<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_INSTALACION == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_INSTALACION == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_ELEMENTO == seccionSeleccionada
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_PARAMETRO_FUNCIONAL == seccionSeleccionada ||'>activo</s:if>">
					<a class="menu-desplegarSubmenu" href="javascript:void(0)"></a>
					<a href="#" class="menu-opcion">
						<s:text name="menu.inventario"/>
					</a>

					<ul class="submenu">
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_INSTALACION == seccionSeleccionada ||
														@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_ELEMENTO == seccionSeleccionada'>activo</s:if>">
							<a class="menu-desplegarSubmenu" href="javascript:void(0)"></a>
							<a href="#" class="menu-opcion">
								<s:text name="menu.familias"/>
							</a>
							<ul class="submenu">
								<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_INSTALACION == seccionSeleccionada'>activo</s:if>">
									<s:url var="familiaInstalacionUrl" namespace="/familiaInstalacion" action="list"/>
									<s:a theme="simple" cssClass="menu-opcion" href="%{familiaInstalacionUrl}">
										<s:text name="menu.instalaciones"/>
									</s:a>
								</li>
								<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_FAMILIA_ELEMENTO == seccionSeleccionada'>activo</s:if>">
									<s:url var="familiaElementoUrl" namespace="/familiaElemento" action="list"/>
									<s:a theme="simple" cssClass="menu-opcion" href="%{familiaElementoUrl}">
										<s:text name="menu.elementos"/>
									</s:a>
								</li>
							</ul>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_INSTALACION == seccionSeleccionada'>activo</s:if>">
							<a class="menu-desplegarSubmenu" href="javascript:void(0)"></a>
							<a href="#" class="menu-opcion">
								<s:text name="menu.tipos"/>
							</a>
							<ul class="submenu">
								<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_INSTALACION == seccionSeleccionada'>activo</s:if>">
									<s:url var="tipoInstalacionUrl" namespace="/tipoInstalacion" action="list"/>
									<s:a theme="simple" cssClass="menu-opcion" href="%{tipoInstalacionUrl}">
										<s:text name="menu.instalaciones"/>
									</s:a>
								</li>
							</ul>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_PARAMETRO_FUNCIONAL == seccionSeleccionada '>activo</s:if>">
							<s:url var="parametroFuncionalUrl" namespace="/parametroFuncional" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{parametroFuncionalUrl}">
								<s:text name="menu.parametroFuncionales"/>
							</s:a>
						</li>
					</ul>
				</li>
				<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CONFIGURACION_FAMILIA == seccionSeleccionada '>activo</s:if>">
					<s:url var="configuracionFamiliaUrl" namespace="/configuracionFamilia" action="list"/>
					<s:a theme="simple" cssClass="menu-opcion" target="_self" href="%{configuracionFamiliaUrl}">
						<s:text name="menu.configuracionFamiliaComponente"/>
					</s:a>
					<!-- <a href="#" class="menu-opcion">
						<s:text name="menu.configuracionFamiliaComponente"/>
					</a> -->
				</li>
				<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CONFIGURACIONES_TIPO == seccionSeleccionada '>activo</s:if>">
					<s:url var="configuracionesTipoUrl" namespace="/configuracionesTipo" action="list"/>
					<s:a theme="simple" cssClass="menu-opcion" target="_self" href="%{configuracionesTipoUrl}">
						<s:text name="menu.configuracionTipoComponente"/>
					</s:a>
				</li>


				<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA_FISICA == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_FISICA == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ESTADO_INSTALACION == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ESTADO_ELEMENTO == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_EMPLAZAMIENTO == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_EMPLAZAMIENTO == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_LOCALIZACION == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UNIDAD_MANTENIMIENTO == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CENTRO == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_SECTOR == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_PROPIETARIO_ACTIVO == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_REGION == seccionSeleccionada ||
												@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_RESPONSABLE_MANTENIMIENTO == seccionSeleccionada ||'>activo</s:if>">
					<a class="menu-desplegarSubmenu" href="javascript:void(0)"></a>
					<a href="#" class="menu-opcion">
						<s:text name="menu.organizacion"/>
					</a>

					<ul class="submenu">
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_PROPIETARIO_ACTIVO == seccionSeleccionada'>activo</s:if>">
							<s:url var="propietarioActivoUrl" namespace="/propietarioActivo" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{propietarioActivoUrl}">
								<s:text name="menu.propietarioActivo"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_RESPONSABLE_MANTENIMIENTO == seccionSeleccionada'>activo</s:if>">
							<s:url var="responsableMantenimientoUrl" namespace="/responsableMantenimiento" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{responsableMantenimientoUrl}">
								<s:text name="menu.responsableMantenimiento"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_REGION == seccionSeleccionada'>activo</s:if>">
							<s:url var="regionUrl" namespace="/region" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{regionUrl}">
								<s:text name="menu.regiones"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_SECTOR == seccionSeleccionada'>activo</s:if>">
							<s:url var="sectorUrl" namespace="/sector" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{sectorUrl}">
								<s:text name="menu.sectores"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_CENTRO == seccionSeleccionada'>activo</s:if>">
							<s:url var="centroUrl" namespace="/centro" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{centroUrl}">
								<s:text name="menu.centros"/>
							</s:a>
						</li>

						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UNIDAD_MANTENIMIENTO == seccionSeleccionada'>activo</s:if>">
							<s:url var="unidadMantenimientoUrl" namespace="/unidadMantenimiento" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{unidadMantenimientoUrl}">
								<s:text name="menu.unidadesMantenimiento"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_LOCALIZACION == seccionSeleccionada'>activo</s:if>">
							<s:url var="localizacionUrl" namespace="/localizacion" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{localizacionUrl}">
								<s:text name="menu.localizaciones"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_EMPLAZAMIENTO == seccionSeleccionada'>activo</s:if>">
							<s:url var="emplazamientoUrl" namespace="/emplazamiento" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{emplazamientoUrl}">
								<s:text name="menu.emplazamientos"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_TIPO_EMPLAZAMIENTO == seccionSeleccionada'>activo</s:if>">
							<s:url var="tipoEmplazamientoUrl" namespace="/tipoEmplazamiento" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{tipoEmplazamientoUrl}">
								<s:text name="menu.tiposEmplazamientos"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ESTADO_ELEMENTO == seccionSeleccionada'>activo</s:if>">
							<s:url var="estadoElementoUrl" namespace="/estadoElemento" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{estadoElementoUrl}">
								<s:text name="menu.estadoElementos"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_ESTADO_INSTALACION == seccionSeleccionada'>activo</s:if>">
							<s:url var="estadoInstalacionUrl" namespace="/estadoInstalacion" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{estadoInstalacionUrl}">
								<s:text name="menu.estadoInstalaciones"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_FISICA == seccionSeleccionada'>activo</s:if>">
							<s:url var="ubicacionFisicaUrl" namespace="/ubicacionFisica" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{ubicacionFisicaUrl}">
								<s:text name="menu.ubicacionesFisicas"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA == seccionSeleccionada'>activo</s:if>">
							<s:url var="ubicacionLogicaUrl" namespace="/ubicacionLogica" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{ubicacionLogicaUrl}">
								<s:text name="menu.ubicacionesLogicas"/>
							</s:a>
						</li>
						<li class="menu-item <s:if test='@es.enaire.inventario.util.ConstantesMigasPan@ESPACIO_UBICACION_LOGICA_FISICA == seccionSeleccionada'>activo</s:if>">
							<s:url var="ubicacionLogicaFisicaUrl" namespace="/ubicacionLogicaFisica" action="list"/>
							<s:a theme="simple" cssClass="menu-opcion" href="%{ubicacionLogicaFisicaUrl}">
								<s:text name="menu.ubicacionesLogicasFisicas"/>
							</s:a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</nav>

<div class="menu-salir">
	<a href="../../" class="icon icon-thinOff">
		<p>Salir</p>
	</a>
    <!-- <a href="../" class="menu-salir-enlace">Salir</a> -->
</div>
