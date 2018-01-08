package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.business.FiltroUbicacionLogicaFisica;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.dtos.UbicacionLogicaFisicaDTO;
import es.enaire.inventario.util.ConstantesMigasPan;
import es.enaire.inventario.util.ConverterInformeExcel;
import es.enaire.inventario.util.Utilidades;
import es.enaire.inventario.util.UtilidadesExcel;

public class UbicacionLogicaFisicaAction extends BaseAction<UbicacionLogicaFisicaDTO> implements
		SessionAware {

	/**
	 *  Identificador de serializacion
	 */
	private static final long serialVersionUID = -6371914317904383485L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Asociación ubicación logica y fisica";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_UBICACION_LOGICA_FISICA = "Filtro_busqueda_ubicacion_logica";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_UBICACION_LOGICA_FISICA = "Filtro_busqueda_popup_ubicacion_logica_fisica";
	/**
	 * Constructor
	 */
	public UbicacionLogicaFisicaAction() {
		elemento = new UbicacionLogicaFisicaDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.UBICACION_LOGICA_FISICA;
		nombreFiltroSesion = FILTRO_BUSQUEDA_UBICACION_LOGICA_FISICA;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_UBICACION_LOGICA_FISICA;
		setNamespace(ConstantesMigasPan.ESPACIO_UBICACION_LOGICA_FISICA);
		setFiltro(new FiltroUbicacionLogicaFisica());

	}
	/**
	 * Devuelve el DTO de la UnidadLogica.
	 * @return elemento
	 */
	@Override
	public UbicacionLogicaFisicaDTO getModel() {
		return elemento;
	}
	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		nombreMiga = ConstantesMigasPan.LISTADO_UBICACION_LOGICAS_FISICAS;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de UnidadLogica para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_UBICACION_LOGICAS_FISICAS;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_UBICACION_LOGICAS_FISICAS;
		return super.search();
	}
	public void aplicarEstadoMenu(UbicacionLogicaFisicaDTO resultado) {
		String estadoMenu = "menu_verMas|menu_modificar|";
		//TODO : Pendiente de implementar si hay mas opciones
		estadoMenu = estadoMenu.concat("menu_cerrar|");
		resultado.setEstadoMenu(estadoMenu);

	}
	/**
	 * Genera la exportacion del listado de elementos en formato PDF
	 * @return SUCCESS o ERROR
	 */
	public String exportarList_PDF() {
		filtro = (FiltroUbicacionLogicaFisica) getSession().get(FILTRO_BUSQUEDA_UBICACION_LOGICA_FISICA);

		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.UBICACION_LOGICA_FISICA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

			setNombreInforme("ListadoAsociacionUbicaciones");
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
		filtro = (FiltroUbicacionLogicaFisica) getSession().get(FILTRO_BUSQUEDA_UBICACION_LOGICA_FISICA);
		try {
			EntradaDTO entrada = new EntradaDTO();
			entrada.setTipoEntrada(TipoEntradaConstants.UBICACION_LOGICA_FISICA);
			entrada.setTamanioPagina(1000L);
			entrada.setPaginaActual(0L);
			entrada.setObjetoEntrada(filtro);

			RespuestaDTO respuesta = getFacadeBackend().search(entrada);
			list = Utilidades.convertArrayToList(respuesta.getObjetoRespuesta());

				InformeExcelDTO informeExcelDTO = ConverterInformeExcel.convertFromUbicacionLogicaFisicas(list);
				Workbook workbook = UtilidadesExcel.writeExcel(informeExcelDTO);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
				setContentDisposition("attachment; filename=ListadoAsociacionUbicaciones."+getDocumentFormat());

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
		filtro = (FiltroUbicacionLogicaFisica) getSession().get(nombreFiltroSesion);
		if(filtro == null){
			filtro = new FiltroUbicacionLogicaFisica();
		}
		return super.preExportarList();
	}

}
