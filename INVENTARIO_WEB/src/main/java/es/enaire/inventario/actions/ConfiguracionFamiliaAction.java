package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.opensymphony.xwork2.ActionContext;

import es.enaire.inventario.business.ConfiguracionElementosFamiliaJsonDTO;
import es.enaire.inventario.business.ConfiguracionesFamiliaJsonDTO;
import es.enaire.inventario.business.FiltroConfiguracionesFamilia;
import es.enaire.inventario.business.FiltroFamiliaInstalacion;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.converterJson.ConfiguracionFamiliaConverterJSON;
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.interfaces.IInformes;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.ConverterInformeExcel;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.util.UtilidadesExcel;

public class ConfiguracionFamiliaAction extends BaseAction<ConfiguracionesFamiliaDTO> implements SessionAware, IInformes  {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 2614079554530095155L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "CFC";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_CONFIGURACIONES_FAMILIA = "Filtro_busqueda_configuraciones_familia";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_CONFIGURACIONES_FAMILIA = "Filtro_busqueda_popup_configuraciones_familia";
	/**
	 * Listado de familia de elementos
	 */
	private List<FamiliaElementoDTO> familiaElementoList;
	/**
	 * Filtro para las familias de instalacion
	 */
	FiltroFamiliaInstalacion filtroInstalacion;

	/**
	 * Constructor
	 */
	public ConfiguracionFamiliaAction() {
		elemento = new ConfiguracionesFamiliaDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.CONFIGURACIONES_FAMILIA;
		nombreFiltroSesion = FILTRO_BUSQUEDA_CONFIGURACIONES_FAMILIA;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_CONFIGURACIONES_FAMILIA;
		setNamespace(ConstantesMigasPan.ESPACIO_CONFIGURACION_FAMILIA);
		setFiltro(new FiltroConfiguracionesFamilia());

	}
	/**
	 * Devuelve el DTO de la region.
	 * @return elemento
	 */
	@Override
	public ConfiguracionesFamiliaDTO getModel() {
		if(!isInicializado()){
			setFamiliaElementoList(Utilidades.getListadoFamiliaElementos(getFacadeBackend()));
		}
		setInicializado(true);
		return elemento;
	}

	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		//Si llega a este punto es porque se tiene que mostrar el contenido del listado de empresas.
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACIONES_FAMILIA;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de region para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACIONES_FAMILIA;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACIONES_FAMILIA;
		return super.search();
	}
	public void aplicarEstadoMenu(ConfiguracionesFamiliaDTO resultado) {
		//TODO PENDIENTE DE IMPLEMENTAR EL LISTADO DE ACCIONES QUE TIENE PARA RESULTADO DEL LISTADO.

		String estadoMenu = "menu_verMas|";
		if(resultado.getActivo().equals(ACTIVO_NO)){
			estadoMenu = estadoMenu.concat("menu_reactivar|");
		}
		else {
			estadoMenu = estadoMenu.concat("menu_modificar|menu_eliminar|");
		}
		estadoMenu = estadoMenu.concat("menu_cerrar|");
		resultado.setEstadoMenu(estadoMenu);

	}
	/**
	 * Obtiene el Listado de familia de elementos
	 * @return el Listado de familia de elementos
	 */
	public List<FamiliaElementoDTO> getFamiliaElementoList() {
		return familiaElementoList;
	}
	/**
	 * Establece el Listado de familia de elementos
	 * @param familiaElementoList el Listado de familia de elementos
	 */
	public void setFamiliaElementoList(List<FamiliaElementoDTO> familiaElementoList) {
		this.familiaElementoList = familiaElementoList;
	}

	/**
	 * Metodo que devuelve la estructura de las clasesDTO para poder tratar los datos en angular
	 * @return JSON
	 */
	public String obtenerModelForAngular() {
		Map<String,Object> models = new HashMap<String, Object>();
		models.put("installation", new ConfiguracionesFamiliaJsonDTO());
		models.put("element", new ConfiguracionElementosFamiliaJsonDTO());
		models.put("context", "configuracionFamilia");
		setInputStream(new ByteArrayInputStream(JsonStream.serialize(models).getBytes(StandardCharsets.UTF_8)));
		return SUCCESS;
	}

	public String obtenerConfigurationByID() {
		String resultado = ERROR;
		EntradaDTO entrada = new EntradaDTO(elemento, null, null, TipoEntradaConstants.CONFIGURACIONES_FAMILIA, null);
		RespuestaDTO respuesta = getFacadeBackend().info(entrada);
		if(respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)){
			Object objetoRespuesta = respuesta.getObjetoRespuesta(0);
			ConfiguracionesFamiliaJsonDTO confFamilia = ConfiguracionFamiliaConverterJSON.convertirDTOToJsonDTO((ConfiguracionesFamiliaDTO) objetoRespuesta);
			setInputStream(new ByteArrayInputStream(JsonStream.serialize(confFamilia).getBytes(StandardCharsets.UTF_8)));
			resultado = SUCCESS;
		}
		return resultado;
	}

	/**
	 * Deserializa el parametro base64JSON a el modelo correspondiente
	 */
	public String saveOrUpdate() {
		try {
			String aux = new String(Base64.getDecoder().decode(base64JSON), "utf-8");
			ConfiguracionesFamiliaJsonDTO confFamiliaJson = JsonIterator.deserialize(aux, ConfiguracionesFamiliaJsonDTO.class);
			elemento = ConfiguracionFamiliaConverterJSON.convertirJsonDTOToDTO(confFamiliaJson);

		} catch (UnsupportedEncodingException e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = { nombreElemento.toLowerCase() };
			addActionError(getText("error", errorParameters));
		}

		String respuesta = super.saveOrUpdate();
		String json =  "{\"estado\": \"OK\", \"idElemento\": \""+ elemento.getId() + "\"}";
		if(respuesta.equals(ERROR)) {
			String errores = "";
			for(String error: getActionErrors()) {
				errores += (error + "\n");
			}

			json =  "{\"estado\": \"ERROR\", \"mensaje\": \""+ errores + "\"}";
		}
		setInputStream(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));

		return respuesta;
	}
	/**
	 *
	 * @return
	 */
	public String saveOrUpdateImage() {
		String respuesta = ERROR;
		try {
			ConfiguracionesFamiliaDTO configuracionFamilia = new ConfiguracionesFamiliaDTO();
			configuracionFamilia.setId(Long.parseLong(getIdFilaSeleccionada()));
			RespuestaDTO respuestaInfo = getFacadeBackend().info(new EntradaDTO(configuracionFamilia, null, null, TipoEntradaConstants.CONFIGURACIONES_FAMILIA, null));
			if(respuestaInfo.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)){
				Object objetoRespuesta = respuestaInfo.getObjetoRespuesta(0);
				elemento = (ConfiguracionesFamiliaDTO) objetoRespuesta;
				elemento.setImagenArbol(base64JSON);
			}

			respuesta = super.saveOrUpdate();

			String json =  "{\"estado\": \"OK\"}";
			setInputStream(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));

		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = { nombreElemento.toLowerCase() };
			addActionError(getText("error", errorParameters));
		}

		return respuesta;
	}
	/**
	 * Obtiene un listado de familia de instalaciones en formato JSON
	 * @return SUCCESS
	 */
	public String obtenerFamiliaInstalacion() {
		EntradaDTO entrada = new EntradaDTO(filtroInstalacion, 0L, 10L, TipoEntradaConstants.FAMILIA_INSTALACION, null);
		entrada.setPaginaActual((long) getActualPage());
		RespuestaDTO respuestaFamiliaIntalacion = getFacadeBackend().search(entrada);
		if(respuestaFamiliaIntalacion.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)){
			Object[] objetoRespuesta  = respuestaFamiliaIntalacion.getObjetoRespuesta();
			Map<String,Object> list = new HashMap<String, Object>();
			list.put("list", objetoRespuesta);
			list.put("more_results", objetoRespuesta.length == PAGE_SIZE);
			setInputStream(new ByteArrayInputStream(JsonStream.serialize(list).getBytes(StandardCharsets.UTF_8)));
		}
		return SUCCESS;

	}

	/**
	 * Genera la exportacion del listado de elementos en formato PDF
	 * @return SUCCESS o ERROR
	 */
	public String exportarList_PDF() {
		filtro = (FiltroConfiguracionesFamilia) getSession().get(FILTRO_BUSQUEDA_CONFIGURACIONES_FAMILIA);

		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.CONFIGURACIONES_FAMILIA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

			setNombreInforme("ListadoConfiguracionFamilias");
		} catch (Exception e) {
			loggerINVENTARIO.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Genera la exportacion del listado de elementos en formato XLS
	 * @return SUCCESS o ERROR
	 */
	public String exportarList_XLS() {
		filtro = (FiltroConfiguracionesFamilia) getSession().get(FILTRO_BUSQUEDA_CONFIGURACIONES_FAMILIA);
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.CONFIGURACIONES_FAMILIA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

				InformeExcelDTO informeExcelDTO = ConverterInformeExcel.convertFromConfiguracionFamilias(list);
				Workbook workbook = UtilidadesExcel.writeExcel(informeExcelDTO);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
				setContentDisposition("attachment; filename=ListadoConfiguracionFamilias."+getDocumentFormat());

		} catch (Exception e) {
			loggerINVENTARIO.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			return ERROR;
		}
		return SUCCESS;
	}


	/**
	 * Método que nos comprueba si el numero de resultados excede de una cantidad determinada
	 * @return SUCCESS o ERROR
	 */
	@Override
	public String preExportarList() {
		filtro = (FiltroConfiguracionesFamilia) getSession().get(nombreFiltroSesion);
		if(filtro == null){
			filtro = new FiltroConfiguracionesFamilia();
		}
		return super.preExportarList();
	}

	/**
	 * Genera la exportacion de los detalles del elemento
	 * @return SUCCESS o ERROR
	 */
	public String exportarShowMore() {
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.CONFIGURACIONES_FAMILIA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			elemento.setId(Long.parseLong(getIdFilaSeleccionada()));
			entrada.setObjetoEntrada(elemento);

			RespuestaDTO respuesta = getFacadeBackend().info(entrada);
			elemento =(ConfiguracionesFamiliaDTO) respuesta.getObjetoRespuesta()[0];
			setNombreInforme("DetalleConfiguracionFamilia"+ elemento.getId());
		} catch (Exception e) {
			loggerINVENTARIO.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			return ERROR;
		}
		return SUCCESS;
	}


	/** Método que nos obtiene la lista de parametros del informe
	 *
	 */
	@Override
	public Map<String, Object> getParametersList() {
		Map<String, Object> parametersList= new HashMap<String, Object>();
		ServletContext contexto = ServletActionContext.getServletContext();
		parametersList.put("TITULO", ELEMENTO);
		String path_imagen = contexto.getRealPath("/img/common-enaire/logo.png");
		parametersList.put("URL_IMAGEN", path_imagen);
		String path_favicon = contexto.getRealPath("/img/common-enaire/favicon.png");
		parametersList.put("URL_FAVICON", path_favicon);
		String path_subreport= contexto.getRealPath("/informes/subReports/") + "/";
		parametersList.put("SUBREPORT_DIR", path_subreport);
		String path_subreport_subreports=contexto.getRealPath("/informes/subReports/") + "/";
		parametersList.put("SUBREPORT_DIR_SUBREPORTS", path_subreport_subreports);
		parametersList.put("isPDF", false);//Para no tener que sacar las cabeceras.
		parametersList.put(JRParameter.IS_IGNORE_PAGINATION, ActionContext.getContext().getName().toUpperCase().contains("XLS"));

		if(filtro != null){
			parametersList.put("CAMPOS_FILTRO", filtro.getCamposFiltro());
		}

		return parametersList;
	}

	/**
	 * Devuelve los filtros que se estan utilizando para la busqueda de familia instalaciones
	 * @return FiltroFamiliaInstalacion
	 */
	public FiltroFamiliaInstalacion getFiltroInstalacion() {
		return filtroInstalacion;
	}
	/**
	 * Define los filtros que se van a utilizar para la busqueda de familia instalaciones
	 * @param filtroFamiliaInstalacion
	 */
	public void setFiltroInstalacion(FiltroFamiliaInstalacion filtroInstalacion) {
		this.filtroInstalacion = filtroInstalacion;
	}

}
