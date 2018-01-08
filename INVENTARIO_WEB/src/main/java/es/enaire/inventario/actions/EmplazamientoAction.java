package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import es.enaire.inventario.business.FiltroEmplazamiento;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EmplazamientoDTO;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;
import es.enaire.inventario.interfaces.IInformes;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.ConverterInformeExcel;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.util.UtilidadesExcel;

public class EmplazamientoAction extends BaseAction<EmplazamientoDTO> implements SessionAware, IInformes {

	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 428368582202662497L;

	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Emplazamiento";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_EMPLAZAMIENTO = "Filtro_Emplazamiento_emplazamiento";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_EMPLAZAMIENTO = "Filtro_busqueda_popup_emplazamiento";

	/**
	 * Listado de tipos de emplazamientos
	 */
	private List<TipoEmplazamientoDTO> tiposEmplazamientosList ;
	/**
	 * Constructor
	 */
	public EmplazamientoAction() {
		elemento = new EmplazamientoDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.EMPLAZAMIENTO;
		nombreFiltroSesion = FILTRO_BUSQUEDA_EMPLAZAMIENTO;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_EMPLAZAMIENTO;
		setTiposEmplazamientosList(new ArrayList<TipoEmplazamientoDTO>());
		setNamespace(ConstantesMigasPan.ESPACIO_EMPLAZAMIENTO);
		setFiltro(new FiltroEmplazamiento());
	}
	/**
	 * Devuelve el DTO de la Emplazamiento.
	 * @return elemento
	 */
	@Override
	public EmplazamientoDTO getModel() {
		return elemento;
	}

	/**
	 * Carga el listado de tipos de emplazamiento.
	 * @return
	 */
	private String loadTiposEmplazamientosList(){
		EntradaDTO entrada = new EntradaDTO();
		entrada.setTipoEntrada(TipoEntradaConstants.TIPO_EMPLAZAMIENTO);
		entrada.setPaginaActual(0L);
		entrada.setTamanioPagina(1000L);
		RespuestaDTO respuesta = getFacadeBackend().list(entrada);

		if (!respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			addActionError(getText(respuesta.getCodigoRespuesta()));
			return ERROR;
		};

		Object [] objetoRespuestaList = respuesta.getObjetoRespuesta();
		for (Object objeto : objetoRespuestaList) {
			tiposEmplazamientosList.add((TipoEmplazamientoDTO) objeto);
		}
		return SUCCESS;
	}

	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		nombreMiga = ConstantesMigasPan.LISTADO_EMPLAZAMIENTOS;

		String result = loadTiposEmplazamientosList();
		if(!result.equals(SUCCESS)){
			return ERROR;
		}

		return super.list();
	}

	/**
	 * Accion para volver a mostrar el listado de Emplazamiento para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_EMPLAZAMIENTOS;
		String result = loadTiposEmplazamientosList();
		if(!result.equals(SUCCESS)){
			return ERROR;
		}
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_EMPLAZAMIENTOS;
		String result = loadTiposEmplazamientosList();
		if(!result.equals(SUCCESS)){
			return ERROR;
		}
		return super.search();
	}
	public void aplicarEstadoMenu(EmplazamientoDTO resultado) {
		//TODO PENDIENTE DE IMPLEMENTAR EL LISTADO DE ACCIONES QUE TIENE PARA RESULTADO DEL LISTADO.

		String estadoMenu = "menu_verMas|";
		if(resultado.getActivo().equals("NO")){
			estadoMenu = estadoMenu.concat("menu_reactivar|");
		}
		else {
			estadoMenu = estadoMenu.concat("menu_modificar|menu_eliminar|");
		}
		estadoMenu = estadoMenu.concat("menu_cerrar|");
		resultado.setEstadoMenu(estadoMenu);

	}
	/**
	 * Genera la exportacion del listado de elementos en formato PDF
	 * @return SUCCESS o ERROR
	 */
	public String exportarList_PDF() {
		filtro = (FiltroEmplazamiento) getSession().get(FILTRO_BUSQUEDA_EMPLAZAMIENTO);

		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.EMPLAZAMIENTO);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

			setNombreInforme("ListadoEmplazamientos");
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
		filtro = (FiltroEmplazamiento) getSession().get(FILTRO_BUSQUEDA_EMPLAZAMIENTO);
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.EMPLAZAMIENTO);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

				InformeExcelDTO informeExcelDTO = ConverterInformeExcel.convertFromEmplazamientos(list);
				Workbook workbook = UtilidadesExcel.writeExcel(informeExcelDTO);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
				setContentDisposition("attachment; filename=ListadoEmplazamientos."+getDocumentFormat());

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
		filtro = (FiltroEmplazamiento) getSession().get(nombreFiltroSesion);
		if(filtro == null){
			filtro = new FiltroEmplazamiento();
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
			entrada.setTipoEntrada(TipoEntradaConstants.EMPLAZAMIENTO);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			elemento.setId(Long.parseLong(getIdFilaSeleccionada()));
			entrada.setObjetoEntrada(elemento);

			RespuestaDTO respuesta = getFacadeBackend().info(entrada);
			elemento =(EmplazamientoDTO) respuesta.getObjetoRespuesta()[0];
			setNombreInforme("DetalleEmplazamiento"+ elemento.getId());
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

	public List<TipoEmplazamientoDTO> getTiposEmplazamientosList() {
		return tiposEmplazamientosList;
	}
	public void setTiposEmplazamientosList(List<TipoEmplazamientoDTO> tiposEmplazamientosList) {
		this.tiposEmplazamientosList = tiposEmplazamientosList;
	}
}
