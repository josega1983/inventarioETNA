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

import es.enaire.inventario.business.FiltroUbicacionFisica;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.dtos.UbicacionFisicaDTO;
import es.enaire.inventario.interfaces.IInformes;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.ConverterInformeExcel;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.util.UtilidadesExcel;

public class UbicacionFisicaAction extends BaseAction<UbicacionFisicaDTO> implements
		SessionAware, IInformes {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -6615309901058529336L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Ubicación Fisica";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_UNIDAD_FISICA = "Filtro_busqueda_unidad_fisica";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_UNIDAD_FISICA = "Filtro_busqueda_popup_unidad_fisica";
	/**
	 * Constructor
	 */
	public UbicacionFisicaAction() {
		elemento = new UbicacionFisicaDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.UBICACION_FISICA;
		nombreFiltroSesion = FILTRO_BUSQUEDA_UNIDAD_FISICA;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_UNIDAD_FISICA;
		setNamespace(ConstantesMigasPan.ESPACIO_UBICACION_FISICA);
		setFiltro(new FiltroUbicacionFisica());

	}
	/**
	 * Devuelve el DTO de la UnidadLogica.
	 * @return elemento
	 */
	@Override
	public UbicacionFisicaDTO getModel() {
		return elemento;
	}
	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		nombreMiga = ConstantesMigasPan.LISTADO_UBICACION_FISICAS;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de UnidadLogica para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_UBICACION_FISICAS;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_UBICACION_FISICAS;
		return super.search();
	}
	public void aplicarEstadoMenu(UbicacionFisicaDTO resultado) {
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
		filtro = (FiltroUbicacionFisica) getSession().get(FILTRO_BUSQUEDA_UNIDAD_FISICA);

		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.UBICACION_FISICA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

			setNombreInforme("ListadoUbicacionesFisicas");
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
		filtro = (FiltroUbicacionFisica) getSession().get(FILTRO_BUSQUEDA_UNIDAD_FISICA);
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.UBICACION_FISICA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

				InformeExcelDTO informeExcelDTO = ConverterInformeExcel.convertFromUbicacionFisicas(list);
				Workbook workbook = UtilidadesExcel.writeExcel(informeExcelDTO);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
				setContentDisposition("attachment; filename=ListadoUbicacionFisicas."+getDocumentFormat());

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
		filtro = (FiltroUbicacionFisica) getSession().get(nombreFiltroSesion);
		if(filtro == null){
			filtro = new FiltroUbicacionFisica();
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
			entrada.setTipoEntrada(TipoEntradaConstants.UBICACION_FISICA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			elemento.setId(Long.parseLong(getIdFilaSeleccionada()));
			entrada.setObjetoEntrada(elemento);

			RespuestaDTO respuesta = getFacadeBackend().info(entrada);
			elemento =(UbicacionFisicaDTO) respuesta.getObjetoRespuesta()[0];
			setNombreInforme("DetalleUbicacionFisica"+ elemento.getId());
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
