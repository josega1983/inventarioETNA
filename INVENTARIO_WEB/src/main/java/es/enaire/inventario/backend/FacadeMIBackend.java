package es.enaire.inventario.backend;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.enaire.inventario.backend.operaciones.OperacionesCentro;
import es.enaire.inventario.backend.operaciones.OperacionesConfiguracionParametroFuncional;
import es.enaire.inventario.backend.operaciones.OperacionesConfiguracionesFamilia;
import es.enaire.inventario.backend.operaciones.OperacionesConfiguracionesTipo;
import es.enaire.inventario.backend.operaciones.OperacionesEmplazamiento;
import es.enaire.inventario.backend.operaciones.OperacionesEstadoElemento;
import es.enaire.inventario.backend.operaciones.OperacionesEstadoInstalacion;
import es.enaire.inventario.backend.operaciones.OperacionesFamiliaElemento;
import es.enaire.inventario.backend.operaciones.OperacionesFamiliaInstalacion;
import es.enaire.inventario.backend.operaciones.OperacionesLocalizacion;
import es.enaire.inventario.backend.operaciones.OperacionesParametroFuncional;
import es.enaire.inventario.backend.operaciones.OperacionesPropietarioActivo;
import es.enaire.inventario.backend.operaciones.OperacionesRegion;
import es.enaire.inventario.backend.operaciones.OperacionesResponsableMantenimiento;
import es.enaire.inventario.backend.operaciones.OperacionesSector;
import es.enaire.inventario.backend.operaciones.OperacionesTipoCampoParametro;
import es.enaire.inventario.backend.operaciones.OperacionesTipoEmplazamiento;
import es.enaire.inventario.backend.operaciones.OperacionesTipoInstalacion;
import es.enaire.inventario.backend.operaciones.OperacionesUbicacionFisica;
import es.enaire.inventario.backend.operaciones.OperacionesUbicacionLogica;
import es.enaire.inventario.backend.operaciones.OperacionesUbicacionLogicaFisica;
import es.enaire.inventario.backend.operaciones.OperacionesUnidadMantenimiento;
import es.enaire.inventario.backend.operaciones.OperacionesValoresParametroElemento;
import es.enaire.inventario.backend.operaciones.OperacionesValoresParametroFuncional;
import es.enaire.inventario.backend.operaciones.OperacionesValoresParametrosFamiliasElemento;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.RespuestaDTO;


/**
 * Implementacion de las diferentes operaciones que ofrece el backend.
 *
 */
public class FacadeMIBackend implements IFacadeMIBackend {
	/**
	 * Objeto por el que realizar las operaciones de Centros
	 */
	@Autowired
	private OperacionesCentro operacionesCentro;
	/**
	 * Objeto por el que realizar las operaciones de Configuraciones Familia
	 */
	@Autowired
	private OperacionesConfiguracionesFamilia operacionesConfiguracionesFamilia;
	/**
	 * Objeto por el que realizar las operaciones de Configuraciones Tipo
	 */
	@Autowired
	private OperacionesConfiguracionesTipo operacionesConfiguracionesTipo;
	/**
	 * Objeto por el que realizar las operaciones de Configuracion Parametro Funcional
	 */
	@Autowired
	private OperacionesConfiguracionParametroFuncional operacionesConfiguracionParametroFuncional;
	/**
	 * Objeto por el que realizar las operaciones de Emplazamientos
	 */
	@Autowired
	private OperacionesEmplazamiento operacionesEmplazamiento;
	/**
	 * Objeto por el que realizar las operaciones de Estados elemento
	 */
	@Autowired
	private OperacionesEstadoElemento operacionesEstadoElemento;
	/**
	 * Objeto por el que realizar las operaciones de Estados instalaciones
	 */
	@Autowired
	private OperacionesEstadoInstalacion operacionesEstadoInstalacion;
	/**
	 * Objeto por el que realizar las operaciones de Familia de Elementos
	 */
	@Autowired
	private OperacionesFamiliaElemento operacionesFamiliaElemento;
	/**
	 * Objeto por el que realizar las operaciones de Familia de Instalaciones
	 */
	@Autowired
	private OperacionesFamiliaInstalacion operacionesFamiliaInstalacion;
	/**
	 * Objeto por el que realizar las operaciones de Localizaciones
	 */
	@Autowired
	private OperacionesLocalizacion operacionesLocalizacion;
	/**
	 * Objeto por el que realizar las operaciones de Parametro Funcional
	 */
	@Autowired
	private OperacionesParametroFuncional operacionesParametroFuncional;
	/**
	 * Objeto por el que realizar las operaciones de Propietario Activo
	 */
	@Autowired
	private OperacionesPropietarioActivo operacionesPropietarioActivo;
	/**
	 * Objeto por el que realizar las operaciones de Regiones
	 */
	@Autowired
	private OperacionesRegion operacionesRegion;
	/**
	 * Objeto por el que realizar las operaciones de Responsable de Mantenimiento
	 */
	@Autowired
	private OperacionesResponsableMantenimiento operacionesResponsableMantenimiento;
	/**
	 * Objeto por el que realizar las operaciones de Sectores
	 */
	@Autowired
	private OperacionesSector operacionesSector;
	/**
	 * Objeto por el que realizar las operaciones de Tipo Campo Parametro
	 */
	@Autowired
	private OperacionesTipoCampoParametro operacionesTipoCampoParametro;
	/**
	 * Objeto por el que realizar las operaciones de tipos de emplazamientos
	 */
	@Autowired
	private OperacionesTipoEmplazamiento operacionesTipoEmplazamiento;
	/**
	 * Objeto por el que realizar las operaciones de tipos de instalaciones
	 */
	@Autowired
	private OperacionesTipoInstalacion operacionesTipoInstalacion;
	/**
	 * Objeto por el que realizar las operaciones de ubicacion fisica
	 */
	@Autowired
	private OperacionesUbicacionFisica operacionesUbicacionFisica;
	/**
	 * Objeto por el que realizar las operaciones de ubicacion logica
	 */
	@Autowired
	private OperacionesUbicacionLogica operacionesUbicacionLogica;
	/**
	 * Objeto por el que realizar las operaciones de ubicacion logica fisica
	 */
	@Autowired
	private OperacionesUbicacionLogicaFisica operacionesUbicacionLogicaFisica;
	/**
	 * Objeto por el que realizar las operaciones de unidad de mantenimiento
	 */
	@Autowired
	private OperacionesUnidadMantenimiento operacionesUnidadMantenimiento;
	/**
	 * Objeto por el que realizar las operaciones de valores de parametro elemento
	 */
	@Autowired
	private OperacionesValoresParametroElemento operacionesValoresParametroElemento;
	/**
	 * Objeto por el que realizar las operaciones de unidad de mantenimiento
	 */
	@Autowired
	private OperacionesValoresParametroFuncional operacionesValoresParametroFuncional;
	/**
	 * Objeto por el que realizar las operaciones de unidad de mantenimiento
	 */
	@Autowired
	private OperacionesValoresParametrosFamiliasElemento operacionesValoresParametrosFamiliasElemento;
	/**
	 * Logger de INTRA.
	 */
	private final static Log loggerINTRA = LogFactory.getLog("INTRA");


	/**
	 * Obtiene los datos del usuario actual
	 * @param entrada de la informacion solicitada
	 * @return la respuesta con el codigo respuesta y el usuario como objeto respuesta
	 */
	@Override
	public RespuestaDTO info(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesConfiguracionParametroFuncional.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)){
				respuesta=operacionesRegion.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_CAMPO_PARAMETRO)){
				respuesta=operacionesTipoCampoParametro.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_ELEMENTO)){
				respuesta=operacionesValoresParametroElemento.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesValoresParametroFuncional.info(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETROS_FAMILIAS_ELEMENTO)){
				respuesta=operacionesValoresParametrosFamiliasElemento.info(entrada);
			}
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}

		return respuesta;
	}
	/**
	 * Obtiene la lista con los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	@Override
	public RespuestaDTO list(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesConfiguracionParametroFuncional.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)){
				respuesta=operacionesRegion.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_CAMPO_PARAMETRO)){
				respuesta=operacionesTipoCampoParametro.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_ELEMENTO)){
				respuesta=operacionesValoresParametroElemento.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesValoresParametroFuncional.list(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETROS_FAMILIAS_ELEMENTO)){
				respuesta=operacionesValoresParametrosFamiliasElemento.list(entrada);
			}
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}
		return respuesta;
	}

	/**
	 * Obtiene la lista con los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	@Override
	public RespuestaDTO search(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesConfiguracionParametroFuncional.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)) {
				respuesta = operacionesRegion.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_CAMPO_PARAMETRO)){
				respuesta=operacionesTipoCampoParametro.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_ELEMENTO)){
				respuesta=operacionesValoresParametroElemento.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesValoresParametroFuncional.search(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETROS_FAMILIAS_ELEMENTO)){
				respuesta=operacionesValoresParametrosFamiliasElemento.search(entrada);
			}
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}
		return respuesta;
	}

	/**
	 * Crea o actualiza un registro en la base de datos del tipo de la entrada seleccionada
	 * @param entrada con el objeto a crear o actualizar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	@Override
	public RespuestaDTO edit(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesConfiguracionParametroFuncional.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)){
				respuesta=operacionesRegion.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_CAMPO_PARAMETRO)){
				respuesta=operacionesTipoCampoParametro.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_ELEMENTO)){
				respuesta=operacionesValoresParametroElemento.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesValoresParametroFuncional.edit(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETROS_FAMILIAS_ELEMENTO)){
				respuesta=operacionesValoresParametrosFamiliasElemento.edit(entrada);
			}
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}
		return respuesta;
	}
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param entrada con el objeto a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	@Override
	public RespuestaDTO reactivar(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesConfiguracionParametroFuncional.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)){
				respuesta=operacionesRegion.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_CAMPO_PARAMETRO)){
				respuesta=operacionesTipoCampoParametro.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_ELEMENTO)){
				respuesta=operacionesValoresParametroElemento.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesValoresParametroFuncional.reactivar(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETROS_FAMILIAS_ELEMENTO)){
				respuesta=operacionesValoresParametrosFamiliasElemento.reactivar(entrada);
			}
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}
		return respuesta;
	}
	/**
	 * Elimina un registro en la base de datos del tipo de entrada seleccionada
	 * @param entrada con el objeto a eliminar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	@Override
	public RespuestaDTO delete(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesConfiguracionParametroFuncional.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)){
				respuesta=operacionesRegion.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_CAMPO_PARAMETRO)){
				respuesta=operacionesTipoCampoParametro.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_ELEMENTO)){
				respuesta=operacionesValoresParametroElemento.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETRO_FUNCIONAL)){
				respuesta=operacionesValoresParametroFuncional.delete(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.VALORES_PARAMETROS_FAMILIAS_ELEMENTO)){
				respuesta=operacionesValoresParametrosFamiliasElemento.delete(entrada);
			}
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}
		return respuesta;
	}
	/**
	 * Obtiene cantidad de objetos que verifican los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	public RespuestaDTO getCantidad(EntradaDTO entrada){
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
			if(tipoEntrada.equals(TipoEntradaConstants.CENTRO)){
				respuesta=operacionesCentro.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_FAMILIA)){
				respuesta=operacionesConfiguracionesFamilia.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.CONFIGURACIONES_TIPO)){
				respuesta=operacionesConfiguracionesTipo.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.EMPLAZAMIENTO)){
				respuesta=operacionesEmplazamiento.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_ELEMENTO)){
				respuesta=operacionesEstadoElemento.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.ESTADO_INSTALACION)){
				respuesta=operacionesEstadoInstalacion.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_ELEMENTO)){
				respuesta=operacionesFamiliaElemento.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.FAMILIA_INSTALACION)){
				respuesta=operacionesFamiliaInstalacion.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.LOCALIZACION)){
				respuesta=operacionesLocalizacion.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PARAMETRO_FUNCIONAL)){
				respuesta=operacionesParametroFuncional.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.PROPIETARIO_ACTIVO)){
				respuesta=operacionesPropietarioActivo.getCantidad(entrada);
			}			
			if(tipoEntrada.equals(TipoEntradaConstants.REGION)){
				respuesta=operacionesRegion.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.RESPONSABLE_MANTENIMIENTO)){
				respuesta=operacionesResponsableMantenimiento.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.SECTOR)){
				respuesta=operacionesSector.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_EMPLAZAMIENTO)){
				respuesta=operacionesTipoEmplazamiento.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.TIPO_INSTALACION)){
				respuesta=operacionesTipoInstalacion.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_FISICA)){
				respuesta=operacionesUbicacionFisica.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA)){
				respuesta=operacionesUbicacionLogica.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UBICACION_LOGICA_FISICA)){
				respuesta=operacionesUbicacionLogicaFisica.getCantidad(entrada);
			}
			if(tipoEntrada.equals(TipoEntradaConstants.UNIDAD_MANTENIMIENTO)){
				respuesta=operacionesUnidadMantenimiento.getCantidad(entrada);
			}
			
	}catch(Exception e) {
		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
		loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
	}
	return respuesta;
	}
	
	/**
	 * Actuliza el estado de un registro de la base de datos del tipo de entrada seleccionada
	 * @param entrada con el objeto a actualizar el estado
	 * @return la respuesta con el codigo respuesta y el objeto actualizado como objeto respuesa
	 */
	@Override
	public RespuestaDTO changeStatus(EntradaDTO entrada) {
		RespuestaDTO respuesta = new RespuestaDTO();
		try {
			String tipoEntrada=entrada.getTipoEntrada();
		}catch(Exception e) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_GENERICO);
			loggerINTRA.error(this.getClass().toString() + ":" +  Thread.currentThread().getStackTrace()[1].getMethodName() + " [" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "] ", e);
		}
		return respuesta;
	}

}
