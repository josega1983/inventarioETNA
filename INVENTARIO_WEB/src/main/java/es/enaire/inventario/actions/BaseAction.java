package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.jsoniter.output.JsonStream;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.enaire.inventario.backend.IFacadeMIBackend;
import es.enaire.inventario.business.BaseFiltro;
import es.enaire.inventario.business.MigaPan;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.dtos.UsuarioDTO;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.GestionPropiedadesConfiguracion;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.utilities.Utilities;

/**
 * Informacion basica disponible para todos los actions.
 */
public abstract class BaseAction<T> extends ActionSupport implements SessionAware, ModelDriven<T>  {
	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = -5735508716525368515L;
	/**
	 * Numero de elementos por pagina del listado
	 */
	public static final int PAGE_SIZE = 10;
	/**
	 * Usuario registrado en el objeto sesion
	 */
	public static final String USUARIO_AUTENTICADO= "USUARIO_AUTENTICADO";
	/**
	 * Constante de activo si
	 */
	public static final String ACTIVO_SI="SI";
	/**
	 * Constante de Activo no
	 */
	public static final String ACTIVO_NO="NO";
	/**
	 * Los datos de la sesion.
	 */
	private Map<String, Object> session;

	/**
	 * Numero de la pagina actual del listado.
	 */
	private int actualPage;

	/**
	 * Indicador de si hay mas resultados para obtener de la paginacion.
	 */
	private Boolean hayMas;

	/**
	 * Define si el action ha sido inicializado para evitar repetir cargas de datos, etc
	 */
	private boolean inicializado;
	/**
	 * Representa la fila seleccionada del listado de consulta del action.
	 */
	private String idFilaSeleccionada;

	/**
	 * Representa las migas de pan asociadas a la ubicacion actual del usuario.
	 */
	private List<MigaPan> migas;

	/**
	 * Accion a la que redirige
	 */
	private String actionName;

	/**
	 * Namespace del actionName que redirige
	 */
	private String namespace;
	/**
	 * Id del formulario
	 */
	private String idFormulario;
	/**
	 * Nombre de la miga
	 */
	protected String nombreMiga;

	/**
	 * Logger de INVENTARIO.
	 */
	final static Log loggerINVENTARIO = LogFactory.getLog("INVENTARIO");

	/**
	 * Indica el permiso actual que se esta utilizando
	 */
	protected String permiso;
	/**
	 * Stream para enviar el resultado.
	 */
	private InputStream inputStream;
	/**
	 * Nombre del informe
	 */
	private String nombreInforme;
	/**
	 * El numero total de resultados busqueda
	 */
	private Long resultados;

	/**
	 * Fachada para conectar con el backend.
	 */
	@Autowired
	private IFacadeMIBackend facadeBackend;

	/**
	 * Usuario que ha acedido a la aplicacion
	 */
	private UsuarioDTO usuarioAutenticado;

	/**
	 * Elemento DTO
	 */
	protected T elemento;

	/**
	 * Listado de elementos que se pasara a la jsp de listado.
	 */
	protected List<T> list;

	/**
	 * Listado de elementos que se pasara al popup.
	 */
	protected List<T> popupList;

	/**
	 * Nombre del filtro en sesion, sera definido en el constructor de la clase hija mediante una constante.
	 */
	protected String nombreFiltroSesion;

	/**
	 * Nombre del filtro del poup en sesion, sera definido en el constructor de la clase hija mediante una constante.
	 */
	protected String nombreFiltroPopup;

	/**
	 * Nombre del elemento , sera definido en el constructor de la clase hija mediante una constante.
	 */
	protected String nombreElemento;

	/**
	 * Tipo de entrada, , sera definido en el constructor de la clase hija mediante una constante.
	 */
	protected String tipoEntrada;
	/**
	 * Accion en que nos encontremos
	 */
	protected Integer accion;
	/**
	 * Filtro de busqueda del filtro
	 */
	protected BaseFiltro filtro;

	/**
	 * Archivos del formulario
	 */
	private List<File> archivoList;

	/**
	 * si el dato debe devolverse en JSON
	 */
	private boolean resultJSON;

	/**
	 * JSON en base 64 pasado por webservice POST
	 */
	protected String base64JSON;

	/**
	 * Content disposition
	 */
	private String contentDisposition;
	/**
	 * Formato del dpocumento
	 */
	private String documentFormat = "xls";
	/**
	 * Constructor del base action.
	 */
	public BaseAction() {
		list = new ArrayList<T>();
		setInicializado(false);
	}


	/**
	 * Metodo que devuelve la estructura de las clasesDTO para poder tratar los datos en angular
	 * @return JSON
	 */
	public String obtenerModelForAngular() {
		setInputStream(new ByteArrayInputStream(JsonStream.serialize(elemento).getBytes(StandardCharsets.UTF_8)));
		return SUCCESS;
	}

	/**
	 * Obtiene el tamano maximo de los listados de la aplicacion
	 * @return el tamano maximo de los listados de la aplicacion
	 */
	public  Long getMaxSizeList() {
		return GestionPropiedadesConfiguracion.getMaxSizeList();
	}
	/**
	 * Método que nos comprueba si el numero de resultados excede de una cantidad determinada
	 * @return SUCCESS o ERROR
	 */
	public String preExportarList() {
//		if(getSession().containsKey(ID_COLUMNA_ORDENADA_EXPORTACION) && getSession().containsKey(ORDEN_EXPORTACION)) {
//		filtro.setAplicadaOrdenacion(true);
//		filtro.setIdPropiedadParaOrdenar((Integer)getSession().get(ID_COLUMNA_ORDENADA_EXPORTACION));
//		filtro.setOrdenacion((String)getSession().get(ORDEN_EXPORTACION));
//	}
		String resultado=SUCCESS;
		String json="";
		try {
				RespuestaDTO respuestaCantidad = getFacadeBackend().getCantidad(new EntradaDTO(filtro, 0L, 10000L, tipoEntrada, null));
				if (!respuestaCantidad.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
					json =   "{\"resultado\": \"KO_APLICACION\", \"mensaje\": \"Error, consulte al administrador de la aplicación para más información\","
							+ "\"titulo\": \"Exportar Informe\"}";
					setInputStream(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));
					this.setMigas(MigasAction.getMigas(getSession()));
					return SUCCESS;
				}
			setResultados((Long) respuestaCantidad.getObjetoRespuesta()[0]);
			filtro.setCantidadInforme(getResultados());
			getSession().remove(nombreFiltroSesion);
			getSession().put(nombreFiltroSesion, filtro);
			if(getResultados()>getMaxSizeList()){
				String[] error={getResultados().toString()};
				json =   "{\"resultado\": \"KO\", \"mensaje\": \""+ getText("global.mensaje.informes",error) + "\","
						+ "\"titulo\": \"Exportar Informe\"}";
			}else{
			json =   "{\"resultado\": \"OK\", \"mensaje\": \"Lista por debajo del máximo\","
					+ "\"titulo\": \"Exportar Informe\"}";
			};
		} catch (Exception e) {
			json =   "{\"resultado\": \"KO\", \"mensaje\": \"Error, consulte al administrador de la aplicación para más información\","
					+ "\"titulo\": \"Exportar Informe\"}";
			resultado=INPUT;
			e.printStackTrace();
		}

		setInputStream(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));
		return resultado;
	}


	/**
	 * Metodo para trazar en el log la informacion del error.
	 * @param clase La clase donde se produjo el error.
	 * @param numeroLinea El numero de linea donde se produjo el error.
	 * @param metodo El metodo donde se produjo el error.
	 * @param e La excepcion que se produjo.
	 */
	public void trazarLOG(String clase, int numeroLinea, String metodo, Exception e) {
		loggerINVENTARIO.error(clase + ":" +  metodo + " [" + numeroLinea + "] ", e);
	}

	/**
	 * Metodo para trazar en el log la informacion del error.
	 * @param clase La clase donde se produjo el error.
	 * @param numeroLinea El numero de linea donde se produjo el error.
	 * @param metodo El metodo donde se produjo el error.
	 * @param mensaje El mensaje informativo del error.
	 */
	public void trazarLOG(String clase, int numeroLinea, String metodo, String mensaje) {
		loggerINVENTARIO.error(clase + ":" +  metodo + " [" + numeroLinea + "] " + mensaje);
	}

	@SuppressWarnings("unchecked")
	private List <T> getListado(BaseFiltro filtro, String nombreFiltro) throws RemoteException{
		List <T> resultado = new ArrayList<T>();
		EntradaDTO entrada = new EntradaDTO();
		entrada.setTipoEntrada(tipoEntrada);
		entrada.setPaginaActual((long) getActualPage());
		entrada.setTamanioPagina((long) PAGE_SIZE);
		if(filtro != null){
			entrada.setObjetoEntrada(filtro);
		}
		getSession().remove(nombreFiltro);
		RespuestaDTO respuesta = null;
		if(filtro != null) {
			respuesta = getFacadeBackend().search(entrada);
		}
		else {
			respuesta = getFacadeBackend().list(entrada);
		}

		if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			addActionError(getText(respuesta.getCodigoRespuesta()));
			return null;
		};
		Object [] objetoRespuestaList = respuesta.getObjetoRespuesta();
		if(objetoRespuestaList != null){
			for (Object objeto : objetoRespuestaList) {
				resultado.add((T) objeto);
			}
			setHayMas(resultado.size() == PAGE_SIZE);
			if(filtro != null) {
				filtro.setHayMas(resultado.size() == PAGE_SIZE);
				filtro.generateStringCamposFiltro();
				getSession().put(nombreFiltro, filtro);
			}
		}
		else{
			setHayMas(false);
		}

		return resultado;
	}


	private String getListadoFacade() throws RemoteException{
		String resultado = ERROR;
		List <T> listado =  getListado(filtro, nombreFiltroSesion);

		if(listado != null){
			if(resultJSON) {
				Map<String,Object> list = new HashMap<String, Object>();
				list.put("list", listado.toArray());
				list.put("more_results", getHayMas());
				setInputStream(new ByteArrayInputStream(JsonStream.serialize(list).getBytes(StandardCharsets.UTF_8)));
			} else {
				setList(listado);
				for(T elemento: listado){
					aplicarEstadoMenu(elemento);
				}
			}

			resultado = (resultJSON ? "json" : SUCCESS);
		}
		else{
			resultado = INPUT;
		}
		return resultado;
	}

	private String getListadoPopupFacade() throws RemoteException{
		String resultado = ERROR;
		List <T> listado =  getListado(filtro, nombreFiltroPopup);
		if(listado != null){
			setPopupList(listado);
			setHayMas(popupList.size() == PAGE_SIZE);
			if(filtro != null){
				filtro.setHayMas(popupList.size() == PAGE_SIZE);
			}
			resultado = SUCCESS;
		}
		else{
			resultado = INPUT;
		}
		return resultado;
	}

	/**
	 * Lista todas los elementos
	 *
	 * @return String ERROR or SUCCESS
	 */
	public String list() {
		setActionName(ActionContext.getContext().getName());
		String resultado =ERROR;
		if(!resultJSON) {
			MigasAction.clearMigas(getSession());
			Map<String, String> parametros = new HashMap<String, String>();
			MigasAction.addNewMiga(getSession(), new MigaPan(nombreMiga, namespace , ConstantesMigasPan.ACCION_VOLVER_LISTAR, parametros));
			this.setMigas(MigasAction.getMigas(getSession()));
		}

		filtro = null;

		try {
			this.setActualPage(0);//Es aplicar el obtener listado, luego reseteamos la pagina a 0.
			resultado = getListadoFacade();
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = {nombreElemento.toLowerCase()};
			addActionError(getText("error.list", errorParameters));
		}
		return resultado;
	}

	/**
	 * Lista mas resultados de la siguiente pagina
	 * @return Resultados de la nueva pagina
	 */
	public String moreResults(){
		String resultado = ERROR;
		try {
			filtro = (BaseFiltro) getSession().get(nombreFiltroSesion);
			resultado = getListadoFacade();
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = {nombreElemento.toLowerCase()};
			addActionError(getText("error.moreResults", errorParameters));
			resultado = ERROR;
		}
		return resultado;
	}

	/**
	 * Se utiliza para listar los resultados que realizo antes.
	 *
	 * @return String La visualizacion del listado de resultados obtenidos anteriormente.
	 */
	public String volverList() {
		if(getSession().containsKey(nombreFiltroSesion)) {
			//Si existe un filtro de busqueda previo, recuperaremos esta informacion de la amiga.
			try {
				this.setActualPage(0);//Es aplicar el obtener listado, luego reseteamos la pagina a 0.
				setFiltro((BaseFiltro) getSession().get(nombreFiltroSesion));
				search();

				setHayMas(list.size() == PAGE_SIZE);
				getFiltro().setHayMas(list.size() == PAGE_SIZE);
				getSession().put(nombreFiltroSesion, getFiltro());

				MigasAction.clearMigas(getSession());
				Map<String, String> parametros = new HashMap<String, String>();
				MigasAction.addNewMiga(getSession(), new MigaPan(nombreMiga, namespace, ConstantesMigasPan.ACCION_VOLVER_LISTAR, parametros));
				this.setMigas(MigasAction.getMigas(getSession()));
				setActionName(ConstantesMigasPan.ACCION_LISTAR);
				return SUCCESS;
			} catch (Exception e) {
				trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
				String[] errorParameters = {nombreElemento.toLowerCase()};
				addActionError(getText("error.list", errorParameters));
				return ERROR;
			}
		}
		else{
			return "listAll";
		}
	}

	/**
	 *
	 * @param id Identificacion del elemento que queremos recuperar de base de datos
	 * @return El elemento con la id introducido
	 * @throws RemoteException
	 */
	@SuppressWarnings("unchecked")
	protected T getElementById(Long id) throws RemoteException{
		T elemento = (T) getModel();
		Utilities.setIdDTO(elemento, id);

		EntradaDTO entrada = new EntradaDTO();
		//TODO cambiar la constante por generica
		entrada.setTipoEntrada(tipoEntrada);
		entrada.setObjetoEntrada(elemento);
		RespuestaDTO respuesta = getFacadeBackend().info(entrada);
		if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			addActionError(getText(respuesta.getCodigoRespuesta()));
			return null;
		};
		return (T) respuesta.getObjetoRespuesta(0);
	}

	/**
	 * Prepara los datos para redirigir a la pantalla en que muestra los datos del elemento
	 * de forma ordenada
	 * @return SUCCESS or ERROR
	 */
	public String showMore() {
		setActionName(ActionContext.getContext().getName());
		//Preparamos el action por si viene de la miga de pan.
		MigaPan migaPanRecuperada = MigasAction.extraerMiga(getSession(), ConstantesMigasPan.ACCION_VER_MAS);
		if(migaPanRecuperada != null) {
			setIdFilaSeleccionada(migaPanRecuperada.getParametros().get("idFilaSeleccionada"));
		}
		if (getIdFilaSeleccionada() != null){
			try {
				Long id= Long.parseLong(getIdFilaSeleccionada());

				elemento = getElementById(id);
				if(elemento == null){
					return INPUT;
				}

				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put("idFilaSeleccionada", getIdFilaSeleccionada());
				MigasAction.addNewMiga(getSession(), new MigaPan(nombreElemento + " " + ConstantesMigasPan.OPCION_MAS_DETALLE + ": " + getIdFilaSeleccionada(), namespace, ConstantesMigasPan.ACCION_VER_MAS, parametros));
			} catch (Exception e) {
				trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
				String[] errorParameters = {nombreElemento.toLowerCase()};
				addActionError(getText("error.showMore", errorParameters));
				return ERROR;
			}
		}
		this.setMigas(MigasAction.getMigas(getSession()));
		return SUCCESS;
	}

	/**
	 * Lista un elemento
	 *
	 * @return String ERROR or SUCCESS
	 */
	public String edit() {
		setActionName(ActionContext.getContext().getName());
		String resultado = ERROR;

		MigaPan migaPanRecuperada = MigasAction.extraerMiga(getSession(), ConstantesMigasPan.ACCION_EDITAR);
		if(migaPanRecuperada != null && migaPanRecuperada.getParametros() != null) {
			setIdFilaSeleccionada(migaPanRecuperada.getParametros().get("idFilaSeleccionada"));
		}

		if (getIdFilaSeleccionada() != null){
			try{
				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put("idFilaSeleccionada", getIdFilaSeleccionada());
				MigasAction.addNewMiga(getSession(), new MigaPan(ConstantesMigasPan.OPCION_MODIFICAR + " " + nombreElemento + ": " + getIdFilaSeleccionada(), namespace, ConstantesMigasPan.ACCION_EDITAR, parametros));
				Long id= Long.parseLong(getIdFilaSeleccionada());
				elemento = getElementById(id);
				if(elemento == null){
					return INPUT;
				}
				resultado = SUCCESS;
				}
			catch (Exception e) {
				trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
				String[] errorParameters = {nombreElemento.toLowerCase()};
				addActionError(getText("error.edit", errorParameters));
				resultado = ERROR;
			}
		}
		else {
			// Creacion de un nuevo elemento
			elemento = getModel();
			MigasAction.addNewMiga(getSession(), new MigaPan(ConstantesMigasPan.OPCION_NUEVA + " " + nombreElemento, namespace, ConstantesMigasPan.ACCION_EDITAR));
			resultado = SUCCESS;
		}

		this.setMigas(MigasAction.getMigas(getSession()));
		return resultado;
	}

	/**
	 * Busca el objeto con los valores que se le pasa en el filtro de busqueda y setea un nuevo listado con los resultados
	 * Este listado sera el nuevo mostrado por la jsp.
	 * @return String SUCCESS or ERROR
	 */
	public String search() {
		setActionName(ActionContext.getContext().getName());
		String resultado = ERROR;
		if(!resultJSON) {
			MigasAction.clearMigas(getSession());
			Map<String, String> parametros = new HashMap<String, String>();
			MigasAction.addNewMiga(getSession(), new MigaPan(nombreMiga, namespace, ConstantesMigasPan.ACCION_VOLVER_LISTAR, parametros));
			this.setMigas(MigasAction.getMigas(getSession()));
		}

		try {
			this.setActualPage(0);//Es aplicar la busqueda, luego reseteamos la pagina a 0.
			resultado = getListadoFacade();
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = {nombreElemento.toLowerCase()};
			addActionError(getText("error.list", errorParameters));
			resultado = ERROR;
		}

		return resultado;
	}

	/**
	 * Guarda el elemento (ya sea alta o modificacion)
	 * @return SUCCESS or ERROR
	 */
	public String saveOrUpdate() {
		String resultado = SUCCESS;
		//El usuario se da de alta bloqueado
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(tipoEntrada);
			entrada.setObjetoEntrada(elemento);
			entrada.setAccion(accion);
			RespuestaDTO respuesta = getFacadeBackend().edit(entrada);
			if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
				if(respuesta.getObjetoRespuesta() != null && respuesta.getObjetoRespuesta().length > 0){
					addActionError(getText(respuesta.getCodigoRespuesta(), new String[]{Utilidades.convertArrayToString(respuesta.getObjetoRespuesta())}));
				}
				else{
					addActionError(getText(respuesta.getCodigoRespuesta()));
				}
				this.setMigas(MigasAction.getMigas(getSession()));
				return ERROR;
			}
			else {
				elemento = (T) respuesta.getObjetoRespuesta()[0];
			}
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = { nombreElemento.toLowerCase() };
			addActionError(getText("error", errorParameters));
		}
		return resultado;
	}
	/**
	 * Metodo que reactiva un elemento dado
	 * @return SUCCESS or ERROR
	 */
	public String reactivar() {
		String resultado = SUCCESS;
		if (getIdFilaSeleccionada() != null){
			Long id= Long.parseLong(getIdFilaSeleccionada());
			T elemento = (T) getModel();
			Utilities.setIdDTO(elemento, id);

			try {
				EntradaDTO entrada = new EntradaDTO();
				entrada.setTipoEntrada(tipoEntrada);
				entrada.setObjetoEntrada(elemento);
				entrada.setAccion(accion);
				RespuestaDTO respuesta = getFacadeBackend().reactivar(entrada);
				if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
					if(respuesta.getObjetoRespuesta() != null && respuesta.getObjetoRespuesta().length > 0){
						addActionError(getText(respuesta.getCodigoRespuesta(), new String[]{Utilidades.convertArrayToString(respuesta.getObjetoRespuesta())}));
					}
					else{
						addActionError(getText(respuesta.getCodigoRespuesta()));
					}
					this.setMigas(MigasAction.getMigas(getSession()));
					return ERROR;
				}
			} catch (Exception e) {
				trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
				String[] errorParameters = { nombreElemento.toLowerCase() };
				addActionError(getText("error", errorParameters));
			}
		}
		return resultado;
	}

	/**
	 * Accion que elimina una elemento (Comprobando si se puede eliminar
	 * Para que una elemento se pueda elimnar debe cumplir lo siguiente
	 * El elemento debe pertenecer al elemento que la elimina
	 * El elemento no debe tener un estado más avanzado de "tramitandose"
	 * El elemento no debe tener ningun otro elemento asignado
	 * @return String
	 */
	public String delete() {
		//El usuario se da de alta bloqueado
		try {
			if (getIdFilaSeleccionada() != null){
				Long id= Long.parseLong(getIdFilaSeleccionada());
				T elemento = (T) getModel();
				Utilities.setIdDTO(elemento, id);
			}
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(tipoEntrada);
			entrada.setObjetoEntrada(elemento);
			entrada.setAccion(accion);
			RespuestaDTO respuesta = getFacadeBackend().delete(entrada);
			if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
				if(respuesta.getObjetoRespuesta() != null && respuesta.getObjetoRespuesta().length > 0){
					addActionError(getText(respuesta.getCodigoRespuesta(), new String[]{Utilidades.convertArrayToString(respuesta.getObjetoRespuesta())}));
				}
				else{
					addActionError(getText(respuesta.getCodigoRespuesta()));
				}
				return ERROR;
			}
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = { nombreElemento.toLowerCase() };
			addActionError(getText("error", errorParameters));
			return ERROR;
		}
		return SUCCESS;
	}

	public T getModel(){
		return elemento;
	}

	/**
	 * Construye la vista para la seleccion de un elemento por popup.
	 * @return String SUCCESS or ERROR
	 */
	public String loadPopup() {
		try {
			this.setActualPage(0);//Es aplicar la busqueda, luego reseteamos la pagina a 0.
			getSession().remove(nombreFiltroPopup);
			filtro.setActivo(ACTIVO_SI);
			return getListadoPopupFacade();
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = {getText("usuario").toLowerCase()};
			addActionError(getText("error.list", errorParameters));
			return ERROR;
		}
	}

	/**
	 * Busca el listado de elementos filtrados por el filtro de busqueda y setea un nuevo listado con los resultados
	 * Este listado sera el nuevo mostrado por la jsp.
	 * @return String SUCCESS or ERROR
	 */
	public String searchPopup() {
		try {
			this.setActualPage(0);//Es aplicar la busqueda, luego reseteamos la pagina a 0.
			filtro.setActivo(ACTIVO_SI);
			getSession().put(nombreFiltroPopup, filtro);
			return getListadoPopupFacade();
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = {getText("usuario").toLowerCase()};
			addActionError(getText("error.search", errorParameters));
			return ERROR;
		}
	}

	/**
	 * Para obtener mas resultados en la busqueda de elementos en el listado del popup.
	 * @return La vista con los nuevos resultados.
	 */
	public String moreResultsPopup() {
		try {
			BaseFiltro filtro = (BaseFiltro) getSession().get(nombreFiltroPopup);
			boolean hacerBusqueda = true;
			if(filtro != null) {
				if(filtro.getHayMas() != null && !filtro.getHayMas()) {
					hacerBusqueda = false;
				}
			}

			if(hacerBusqueda) {
				return getListadoPopupFacade();
			}

			return SUCCESS;
		} catch (Exception e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			String[] errorParameters = {getText("usuario").toLowerCase()};
			addActionError(getText("error.moreResults", errorParameters));
			return ERROR;
		}
	}

	/**
	 * Accion para obtener el detalle del elemento seleccionado desde el popup.
	 * @return La vista con el detalle del elemento.
	 */
	public String selectElementoFromPopup() {
		String resultado = SUCCESS;
		if (getIdFilaSeleccionada() != null){
			try{
				elemento = getElementById(Long.parseLong(getIdFilaSeleccionada()));
				if(elemento == null){
					resultado = ERROR;
				}
				resultado = SUCCESS;
			} catch (Exception e) {
				trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
				String[] errorParameters = {nombreElemento.toLowerCase()};
				addActionError(getText("error.edit", errorParameters));
				resultado = ERROR;
			}
		}

		return resultado;
	}

	private void buildErrorByTipoEntrada(String errorText, String tipoEntrada){
		String [] parameters = new String[1];
		if(tipoEntrada.equals(TipoEntradaConstants.REGION))
			parameters[0] = "estados";
		else
			parameters[0] = " ";

		addActionError(getText(errorText,parameters));
	}

	/**
	 * Llama al Weblogic para recuperar el listado de opciones para un determinado objeto de la base de datos
	 * @param tipoEntrada Especifica el tipo de objeto que se quiere recuperar
	 * @return el listado de elementos.
	 * @throws Exception RemoteException si es un error de conexion/servidor, Excepcion generica si el codigo de respuesta no es OK
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	protected <T> List<T> getListOptions(String tipoEntrada) throws Exception{
		List <T> listado = new ArrayList<T>();
		EntradaDTO entrada = new EntradaDTO();
		entrada.setTipoEntrada(tipoEntrada);
		try{
			RespuestaDTO respuesta = getFacadeBackend().list(entrada);

			if(respuesta.getCodigoRespuesta() == null){
				buildErrorByTipoEntrada("error.actividad.listado",tipoEntrada);
				throw new NullPointerException();
			}
			else if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
				addActionError(getText(respuesta.getCodigoRespuesta()));
				throw new Exception();
			};
			Object[] listadoArray= respuesta.getObjetoRespuesta();
			for(Object tipoActividad : listadoArray){
				listado.add((T) tipoActividad);
			}
		} catch (RemoteException e) {
			trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
			buildErrorByTipoEntrada("error.actividad.listado",tipoEntrada);
			throw e;
		}
		return listado;
	}

	/**
	 * Devuelve el permiso que tiene el usuario autenticado para acceso al action.
	 * @return El permiso que tiene el usuario autenticado para acceso al action.
	 */
	public String getPermiso() {
		return permiso;
	}

	/**
	 * Establece el permiso que tiene el usuario autenticado para acceso al action.
	 * @param permiso El permiso que tiene el usuario autenticado para acceso al action.
	 */
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	/**
	 * Devuelve el identificador de la fila seleccionada en el listado de consulta del action.
	 * @return El identificador de la fila seleccionada.
	 */
	public String getIdFilaSeleccionada() {
		return idFilaSeleccionada;
	}

	/**
	 * Establece el identificador de la fila seleccionada.
	 * @param idFilaSeleccionada El identificador de la fila seleccionada.
	 */
	public void setIdFilaSeleccionada(String idFilaSeleccionada) {
		this.idFilaSeleccionada = idFilaSeleccionada;
	}

	/**
	 * Establece las migas de pan asociadas a la ubicacion actual del usuario.
	 * @param migas Las migas de pan asociadas a la ubicacion actual del usuario.
	 */
	public void setMigas(List<MigaPan> migas) {
		this.migas = migas;
	}

	/**
	 * Devuelve las migas de pan asociadas a la ubicacion actual del usuario.
	 * @return Las migas de pan asociadas a la ubicacion actual del usuario.
	 */
	public List<MigaPan> getMigas() {
		return migas;
	}

	/**
	 * Devuelve el valor de la variable debug del Properties.
	 * @return el valor de la variable debug del properties.
	 */
	public boolean isModeDebug() {
		return GestionPropiedadesConfiguracion.getModeDebug();
	}

	/**
	 * Devuelve las migas de pan fijadas del usuario.
	 * @return Las migas de pan fijadas del usuario.
	 */
	public List<List<MigaPan>> getMigasFijadas() {
		return MigasAction.getMigasFijas(session);
	}

	/**
	 * Obtiene la pagina actual de la que recuperar dichos resultados.
	 * @return La pagina actual de la que recuperar dichos resultados.
	 */
	public int getActualPage() {
		return actualPage;
	}

	/**
	 * Establece la pagina actual de la que recuperar dichos resultados.
	 * @param actualPage La pagina actual de la que recuperar dichos resultados.
	 */
	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

	/**
	 * Obtiene el indicador de si hay mas resultados de la paginacion.
	 * @return El indicador de si hay mas resultados de la paginacion.
	 */
	public Boolean getHayMas() {
		return hayMas;
	}

	/**
	 * Establece el indicador de si hay mas resultados de la paginacion.
	 * @param hayMas El indicador de si hay mas resultados de la paginacion.
	 */
	public void setHayMas(Boolean hayMas) {
		this.hayMas = hayMas;
	}

	/**
	 * Establece la sesion.
	 * @param session La sesion.
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * Obtiene la sesion.
	 * @return La sesion.
	 */
	public Map<String, Object> getSession() {
		return this.session;
	}

	/**
	 * Obtiene el nombre de la accion seleccionada.
	 * @return La accion.
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * Establece el nombre de la accion seleccionada.
	 * @param actionName La accion.
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * Obtiene el namespace.
	 * @return El namespace.
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * Establece el namespace.
	 * @param namespace El namespace.
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	/**
	 * Obtiene  el Id del formulario
	 * @return el Id del formulario
	 */
	public String getIdFormulario() {
		return idFormulario;
	}
	/**
	 * Establece Id del formulario
	 * @param idFormulario Id del formulario
	 */
	public void setIdFormulario(String idFormulario) {
		this.idFormulario = idFormulario;
	}

	/**
	 * @return the inicializado
	 */
	public boolean isInicializado() {
		return inicializado;
	}

	/**
	 * @param inicializado the inicializado to set
	 */
	public void setInicializado(boolean inicializado) {
		this.inicializado = inicializado;
	}
	/**
	 * Obtiene el nombre del informe
	 * @return el Nombre del informe
	 */
	/**
	 * Obtiene el valor de la variable inputStream para fichero JSON.
	 * @return inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * Establece el valor de la variable inputStream para fichero JSON.
	 * @param inputStream El valor de la variable inputStream.
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getNombreInforme() {
		return nombreInforme;
	}
	/**
	 * Establece el nombre del informe
	 * @param nombreInforme El nombre del informe
	 */

	public void setNombreInforme(String nombreInforme) {
		this.nombreInforme = nombreInforme;
	}
	/**
	 * Obtiene el numero total de resultados busqueda
	 * @return el numero total de resultados busqueda
	 */
	public Long getResultados() {
		return resultados;
	}
	/**
	 * Establece el numero total de resultados busqueda
	 * @param resultados el numero total de resultados busqueda
	 */
	public void setResultados(Long resultados) {
		this.resultados = resultados;
	}

	/**
	 * Obtiene la fachada para conectar con el backend.
	 * @return La fachada para conectar con el backend.
	 */
	public IFacadeMIBackend getFacadeBackend() {
		return facadeBackend;
	}

	/**
	 * Establece la fachada para conectar con el backend.
	 * @param servicioINTRA La fachada para conectar con el backend.
	 */
	public void setFacadeBackend(IFacadeMIBackend facadeBackend) {
		this.facadeBackend = facadeBackend;
	}

	/**
	 * Obtiene el usuario autenticado
	 * @return el usuario autenticado
	 */
	public UsuarioDTO getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
	/**
	 * Establece el usuario autenticado
	 * @param usuarioAutenticado El usuario autenticado
	 */

	public void setUsuarioAutenticado(UsuarioDTO usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	/**
	 * Obtiene el elemento.
	 * @return El elemento.
	 */
	public T getElemento() {
		return elemento;
	}

	/**
	 * Establece el elemento a establecer.
	 * @param elemento El elemento a establecer
	 */
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	/**
	 * Obtiene la lista de elementos.
	 * @return list La lista de elementos
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Establece la lista de elementos.
	 * @param list La lista de elementos
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * Obtiene la lista de elementos.
	 * @return list La lista de elementos
	 */
	public List<T> getPopupList() {
		return popupList;
	}

	/**
	 * Establece la lista de elementos.
	 * @param list La lista de elementos
	 */
	public void setPopupList(List<T> popupList) {
		this.popupList = popupList;
	}

	/**
	 * Obtiene el objeto filtro usado en la busqueda de los elementos.
	 * @return El objeto filtro usado en la busqueda de los elementos
	 */
	public BaseFiltro getFiltro() {
		return filtro;
	}

	/**
	 * Obtiene el objeto filtro usado en la busqueda de elementos.
	 * @param filtro El objeto filtro usado en la busqueda de elementos
	 */
	public void setFiltro(BaseFiltro filtro) {
		this.filtro = filtro;
	}

	/**
	 *Obtiene los archivos del formulario
	 * @return los archivos del formulario
	 */
	public List<File> getArchivoList() {
		return archivoList;
	}
	/**
	 * Establece los archivos del formulario
	 * @param archivoList los archivos del formulario
	 */
	public void setArchivoList(List<File> archivoList) {
		this.archivoList = archivoList;
	}

	/**
	 * Metodo para aplicar los menus de acciones en funcion del estado del elemento.
	 * @param resultado El resultado del listado para aplicar el estado del menu.
	 */
	public abstract void aplicarEstadoMenu(T resultado);

	/**
	 * Metodo para conocer en que seccion del menu nos encontramos.
	 * @return el nombre del la seccion actual
	 */
	public String getSeccionSeleccionada() {
		return MigasAction.getLastMiga(getSession()).getEspacioNombre();
	}

	/**
	 * comprueba el valor de la variable resultJSON
	 * @return resultJSON
	 */
	public boolean isResultJSON() {
		return resultJSON;
	}

	/**
	 * Establece el valor de la variable resultJSON
	 * @param resultJSON
	 */
	public void setResultJSON(boolean resultJSON) {
		this.resultJSON = resultJSON;
	}

	/**
	 * Obtiene el valor JSON en base 64 pasado como parametro en el webservice
	 * @return base64 JSON
	 */
	public String getBase64JSON() {
		return base64JSON;
	}

	/**
	 * Establece el JSON en base64 pasado por parametro en el webservice
	 * @param base64json
	 */
	public void setBase64JSON(String base64json) {
		base64JSON = base64json;
	}

	/**
	 * Obtiene el Content Disposition
	 * @return el Content Disposition
	 */
	public String getContentDisposition() {
		return contentDisposition;
	}
	/**
	 * Establece el Content Disposition
	 * @param contentDisposition el Content Disposition
	 */
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	/**
	 * Obtiene el formato del documento
	 * @return el formato del documento
	 */
	public String getDocumentFormat() {
		return documentFormat;
	}
	/**
	 * Establece el formato del documento
	 * @param documentFormat el formato del documento
	 */
	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}
	String getExcelContentType() {
	    return documentFormat == "xlsx" ? "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" : "application/vnd.ms-excel";
	}
}
