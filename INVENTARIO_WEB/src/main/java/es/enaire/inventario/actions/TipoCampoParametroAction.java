package es.enaire.inventario.actions;

import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.business.FiltroTipoCampoParametro;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.TipoCampoParametroDTO;
import es.enaire.inventario.util.ConstantesMigasPan;

public class TipoCampoParametroAction extends BaseAction<TipoCampoParametroDTO> implements SessionAware {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = -722594566496928616L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Tipo de Campo de Parámetro";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_TIPO_CAMPO_PARAMETRO = "Filtro_busqueda_tipo_campo_parametro";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_TIPO_CAMPO_PARAMETRO = "Filtro_busqueda_popup_tipo_campo_parametro";
	/**
	 * Constructor
	 */
	public TipoCampoParametroAction() {
		elemento = new TipoCampoParametroDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.TIPO_CAMPO_PARAMETRO;
		nombreFiltroSesion = FILTRO_BUSQUEDA_TIPO_CAMPO_PARAMETRO;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_TIPO_CAMPO_PARAMETRO;
		setNamespace(ConstantesMigasPan.ESPACIO_TIPO_CAMPO_PARAMETRO);
		setFiltro(new FiltroTipoCampoParametro());

	}
	/**
	 * Devuelve el DTO de la region.
	 * @return elemento
	 */
	@Override
	public TipoCampoParametroDTO getModel() {
		return elemento;
	}
	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		//Si llega a este punto es porque se tiene que mostrar el contenido del listado de empresas.
		nombreMiga = ConstantesMigasPan.LISTADO_TIPO_CAMPO_PARAMETRO;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de region para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_TIPO_CAMPO_PARAMETRO;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_TIPO_CAMPO_PARAMETRO;
		return super.search();
	}
	public void aplicarEstadoMenu(TipoCampoParametroDTO resultado) {
		//TODO PENDIENTE DE IMPLEMENTAR EL LISTADO DE ACCIONES QUE TIENE PARA RESULTADO DEL LISTADO.

		String estadoMenu = "menu_verMas|menu_modificar|";
		estadoMenu = estadoMenu.concat("menu_cerrar|");
		resultado.setEstadoMenu(estadoMenu);

	}

}
