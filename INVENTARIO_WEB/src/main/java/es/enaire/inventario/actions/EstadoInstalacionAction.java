package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import es.enaire.inventario.business.FiltroEstadoInstalacion;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.EstadoInstalacionDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.interfaces.IInformes;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.ConverterInformeExcel;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.util.UtilidadesExcel;

public class EstadoInstalacionAction extends BaseAction<EstadoInstalacionDTO> implements SessionAware, IInformes {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 5882331198106233939L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Estado Instalación";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_ESTADO_INSTALACION = "Filtro_region_estado_instalacion";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_ESTADO_INSTALACION = "Filtro_busqueda_popup_estado_instalacion";
	/**
	 * Constructor
	 */
	public EstadoInstalacionAction() {
		elemento = new EstadoInstalacionDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.ESTADO_INSTALACION;
		nombreFiltroSesion = FILTRO_BUSQUEDA_ESTADO_INSTALACION;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_ESTADO_INSTALACION;
		setNamespace(ConstantesMigasPan.ESPACIO_ESTADO_INSTALACION);
		setFiltro(new FiltroEstadoInstalacion());

	}
	/**
	 * Devuelve el DTO de la region.
	 * @return elemento
	 */
	@Override
	public EstadoInstalacionDTO getModel() {
		return elemento;
	}
	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		nombreMiga = ConstantesMigasPan.LISTADO_ESTADO_INSTALACION;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de region para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_ESTADO_INSTALACION;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_ESTADO_INSTALACION;
		return super.search();
	}
	public void aplicarEstadoMenu(EstadoInstalacionDTO resultado) {
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
	 * Genera la exportacion del listado de elementos en formato PDF
	 * @return SUCCESS o ERROR
	 */
	public String exportarList_PDF() {
		filtro = (FiltroEstadoInstalacion) getSession().get(FILTRO_BUSQUEDA_ESTADO_INSTALACION);

		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.ESTADO_INSTALACION);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

			setNombreInforme("ListadoEstadosInstalaciones");
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
		filtro = (FiltroEstadoInstalacion) getSession().get(FILTRO_BUSQUEDA_ESTADO_INSTALACION);
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.ESTADO_INSTALACION);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

				InformeExcelDTO informeExcelDTO = ConverterInformeExcel.convertFromEstadoInstalacions(list);
				Workbook workbook = UtilidadesExcel.writeExcel(informeExcelDTO);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
				setContentDisposition("attachment; filename=ListadoEstadoInstalaciones."+getDocumentFormat());

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
		filtro = (FiltroEstadoInstalacion) getSession().get(nombreFiltroSesion);
		if(filtro == null){
			filtro = new FiltroEstadoInstalacion();
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
			entrada.setTipoEntrada(TipoEntradaConstants.ESTADO_INSTALACION);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			elemento.setId(Long.parseLong(getIdFilaSeleccionada()));
			entrada.setObjetoEntrada(elemento);

			RespuestaDTO respuesta = getFacadeBackend().info(entrada);
			elemento =(EstadoInstalacionDTO) respuesta.getObjetoRespuesta()[0];
			setNombreInforme("DetalleEstadoInstalacion"+ elemento.getId());
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
