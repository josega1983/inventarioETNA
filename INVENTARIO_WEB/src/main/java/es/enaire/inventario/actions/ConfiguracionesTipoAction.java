package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

import es.enaire.inventario.business.ConfiguracionElementosTipoJsonDTO;
import es.enaire.inventario.business.ConfiguracionesTipoJsonDTO;
import es.enaire.inventario.business.FiltroConfiguracionesTipo;
import es.enaire.inventario.business.FiltroTipoInstalacion;
import es.enaire.inventario.business.MigaPan;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.converterJson.ConfiguracionTipoConverterJSON;
import es.enaire.inventario.dtos.ConfiguracionesTipoDTO;
import es.enaire.inventario.dtos.ElementoDTO;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.interfaces.IInformes;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.ConverterInformeExcel;
import es.enaire.inventario.util.InfoEmail;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.util.UtilidadesEmail;
import es.enaire.inventario.util.UtilidadesExcel;

public class ConfiguracionesTipoAction extends BaseAction<ConfiguracionesTipoDTO> implements SessionAware, IInformes  {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 4988501402440196403L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Configuraciones de Tipo";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_CONFIGURACIONES_TIPO = "Filtro_busqueda_configuraciones_tipo";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_CONFIGURACIONES_TIPO = "Filtro_busqueda_popup_configuraciones_tipo";
	/**
	 * Lista de elementos
	 */
	private List<ElementoDTO> elementosList;
	/**
	 * Filtro para las familias de instalacion
	 */
	FiltroTipoInstalacion filtroInstalacion;
	/**
	 * Constructor
	 */
	public ConfiguracionesTipoAction() {
		elemento = new ConfiguracionesTipoDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.CONFIGURACIONES_TIPO;
		nombreFiltroSesion = FILTRO_BUSQUEDA_CONFIGURACIONES_TIPO;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_CONFIGURACIONES_TIPO;
		setNamespace(ConstantesMigasPan.ESPACIO_CONFIGURACIONES_TIPO);
		setFiltro(new FiltroConfiguracionesTipo());
		elementosList = new ArrayList<ElementoDTO>();

	}
	/**
	 * Devuelve el DTO de la region.
	 * @return elemento
	 */
	@Override
	public ConfiguracionesTipoDTO getModel() {
		if(!isInicializado()){
			//TODO : CAMBIAR CUANDO SE ACABE LA IMPLEMENTACION!!!
			for (int i = 0; i < 20; i++) {
				ElementoDTO elementoDTO = new ElementoDTO();
				elementoDTO.setId(new Long(i));
				elementoDTO.setNombre("Elemeto"+i);
				elementosList.add(elementoDTO);
			}
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
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACIONES_TIPO;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de region para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACIONES_TIPO;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACIONES_TIPO;
		return super.search();
	}
	public void aplicarEstadoMenu(ConfiguracionesTipoDTO resultado) {
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
	 * Obtiene la lista de elementos
	 * @return la lista de elementos
	 */
	public List<ElementoDTO> getElementosList() {
		return elementosList;
	}
	/**
	 * Estado de elementos
	 * @param elementosList la lista de elementos
	 */
	public void setElementosList(List<ElementoDTO> elementosList) {
		this.elementosList = elementosList;
	}

	/**
	 * Metodo que devuelve la estructura de las clasesDTO para poder tratar los datos en angular
	 * @return JSON
	 */
	public String obtenerModelForAngular() {
		Map<String,Object> models = new HashMap<String, Object>();
		models.put("installation", new ConfiguracionesTipoJsonDTO());
		models.put("element", new ConfiguracionElementosTipoJsonDTO());
		models.put("context", "configuracionesTipo");
		setInputStream(new ByteArrayInputStream(JsonStream.serialize(models).getBytes(StandardCharsets.UTF_8)));
		return SUCCESS;
	}

	/**
	 * Deserializa el parametro base64JSON a el modelo correspondiente
	 */
	public String saveOrUpdate() {
		try {
			String aux = new String(Base64.getDecoder().decode(base64JSON), "utf-8");
			ConfiguracionesTipoJsonDTO confTipoJson = JsonIterator.deserialize(aux, ConfiguracionesTipoJsonDTO.class);
			elemento = ConfiguracionTipoConverterJSON.convertirJsonDTOToDTO(confTipoJson);
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
			ConfiguracionesTipoDTO configuracionTipo = new ConfiguracionesTipoDTO();
			configuracionTipo.setId(Long.parseLong(getIdFilaSeleccionada()));
			RespuestaDTO respuestaInfo = getFacadeBackend().info(new EntradaDTO(configuracionTipo, null, null, TipoEntradaConstants.CONFIGURACIONES_TIPO, null));
			if(respuestaInfo.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)){
				Object objetoRespuesta = respuestaInfo.getObjetoRespuesta(0);
				elemento = (ConfiguracionesTipoDTO) objetoRespuesta;
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
	 * Metodo que nos dirige al formulario de solicitus de creaccion de Marca y Modelo
	 * @return SUCCESS
	 */
	public String  goToSolicitarCreaccion() {
		elemento = getModel();
		MigasAction.addNewMiga(getSession(), new MigaPan(ConstantesMigasPan.ACCION_SOLICITAR_CREACCION + " " + "Marca y Modelo", getNamespace(),ConstantesMigasPan.ACCION_SOLICITAR));
		this.setMigas(MigasAction.getMigas(getSession()));
		return SUCCESS;
	}
	/**
	 * Obtiene un listado de tipos de instalacione en formato JSON
	 * @return SUCCESS
	 */
	public String obtenerTipoInstalacion() {
		EntradaDTO entrada = new EntradaDTO(filtroInstalacion, 0L, 10L, TipoEntradaConstants.TIPO_INSTALACION, null);
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

	public String obtenerConfigurationByID() {
		String resultado = ERROR;
		EntradaDTO entrada = new EntradaDTO(elemento, null, null, TipoEntradaConstants.CONFIGURACIONES_TIPO, null);
		RespuestaDTO respuesta = getFacadeBackend().info(entrada);
		if(respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)){
			Object objetoRespuesta = respuesta.getObjetoRespuesta(0);
			ConfiguracionesTipoJsonDTO confTipo = ConfiguracionTipoConverterJSON.convertirDTOToJsonDTO((ConfiguracionesTipoDTO) objetoRespuesta);
			setInputStream(new ByteArrayInputStream(JsonStream.serialize(confTipo).getBytes(StandardCharsets.UTF_8)));
			resultado = SUCCESS;
		}
		return resultado;
	}
	/**
	 * Devuelve los filtros que se estan utilizando para la busqueda de tipo de instalacion
	 * @return FiltroTipoInstalacion
	 */
	public FiltroTipoInstalacion getFiltroInstalacion() {
		return filtroInstalacion;
	}
	/**
	 * Define los filtros que se van a utilizar para la busqueda de tipo de instalacion
	 * @param filtroTipoInstalacion
	 */
	public void setFiltroInstalacion(FiltroTipoInstalacion filtroInstalacion) {
		this.filtroInstalacion = filtroInstalacion;
	}
	/**
	 * Metodo que nos envia un email solicitando la creaccion de una marca y modelo
	 * @return SUCCESS
	 */
	public String solicitarCreaccion() {
		String resultado = SUCCESS;
		String json = null;
		try {
			InfoEmail email = new InfoEmail();
			email.setDestino("etna@enaire.es");
			email.setAsunto("[INVENTARIO] Solicitud de Nueva Marca y/o Modelo");
			String texto = UtilidadesEmail.getTextoEmailTipoInstacion(elemento.getTipoInstalacion().getFamiliaInstalacion().getNombre(),
					elemento.getTipoInstalacion().getMarca(), elemento.getTipoInstalacion().getModelo());
			email.setTexto(texto);
			UtilidadesEmail.sendEmailFromSoporteAutomatico(email);
			json =  "{\"estado\": \"OK\", \"mensaje\": \"Se ha envidado el email correctamente, hasta que no se cree la marca y el modelo no podr� continuar con la creacci�n de la configuraci�n\"}";
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			json =  "{\"estado\": \"KO\", \"mensaje\": \"Se ha producido un error al enviar el email\"}";
			resultado = ERROR;
		}
		setInputStream(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));
		return resultado;
	}

	/**
	 * Genera la exportacion del listado de elementos en formato PDF
	 * @return SUCCESS o ERROR
	 */
	public String exportarList_PDF() {
		filtro = (FiltroConfiguracionesTipo) getSession().get(FILTRO_BUSQUEDA_CONFIGURACIONES_TIPO);

		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.CONFIGURACIONES_TIPO);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

			setNombreInforme("ListadoConfiguracionesTipos");
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
		filtro = (FiltroConfiguracionesTipo) getSession().get(FILTRO_BUSQUEDA_CONFIGURACIONES_TIPO);
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.CONFIGURACIONES_TIPO);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

				InformeExcelDTO informeExcelDTO = ConverterInformeExcel.convertFromConfiguracionTipos(list);
				Workbook workbook = UtilidadesExcel.writeExcel(informeExcelDTO);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
				setContentDisposition("attachment; filename=ListadoConfiguracionesTipos."+getDocumentFormat());

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
		filtro = (FiltroConfiguracionesTipo) getSession().get(nombreFiltroSesion);
		if(filtro == null){
			filtro = new FiltroConfiguracionesTipo();
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
			entrada.setTipoEntrada(TipoEntradaConstants.CONFIGURACIONES_TIPO);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			elemento.setId(Long.parseLong(getIdFilaSeleccionada()));
			entrada.setObjetoEntrada(elemento);

			RespuestaDTO respuesta = getFacadeBackend().info(entrada);
			elemento =(ConfiguracionesTipoDTO) respuesta.getObjetoRespuesta()[0];
			setNombreInforme("DetalleConfiguracionesTipo"+ elemento.getId());
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
}
