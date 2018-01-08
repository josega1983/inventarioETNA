package es.enaire.inventario.actions;

import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.business.FiltroConfiguracionParametroFuncional;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;
import es.enaire.inventario.util.ConstantesMigasPan;

public class ConfiguracionParametroFuncionalAction extends BaseAction<ConfiguracionParametroFuncionalDTO> implements SessionAware {

	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 7400247368418127016L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Configuración Parametro Funcional";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_CONFIGURACION_PARAMETRO_FUNCIONAL = "Filtro_busqueda_configuracion_parametro_funcional";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_CONFIGURACION_PARAMETRO_FUNCIONAL = "Filtro_busqueda_popup_configuracion_parametro_funcional";
	/**
	 * Constructor
	 */
	public ConfiguracionParametroFuncionalAction() {
		elemento = new ConfiguracionParametroFuncionalDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.CONFIGURACION_PARAMETRO_FUNCIONAL;
		nombreFiltroSesion = FILTRO_BUSQUEDA_CONFIGURACION_PARAMETRO_FUNCIONAL;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_CONFIGURACION_PARAMETRO_FUNCIONAL;
		setNamespace(ConstantesMigasPan.ESPACIO_CONFIGURACION_PARAMETRO_FUNCIONAL);
		setFiltro(new FiltroConfiguracionParametroFuncional());

	}
	/**
	 * Devuelve el DTO de la region.
	 * @return elemento
	 */
	@Override
	public ConfiguracionParametroFuncionalDTO getModel() {
		return elemento;
	}
	/**
	 * Accion que obtiene el listado de empresas para que pueda trabajar el usuario.
	 * @return El resultado con el listado de empresas para trabajar.
	 */
	public String list(){
		//Si llega a este punto es porque se tiene que mostrar el contenido del listado de empresas.
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACION_PARAMETRO_FUNCIONAL;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de region para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACION_PARAMETRO_FUNCIONAL;
		return super.volverList();
	}
	/**
	 * Accion de buscar
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_CONFIGURACION_PARAMETRO_FUNCIONAL;
		return super.search();
	}
	public void aplicarEstadoMenu(ConfiguracionParametroFuncionalDTO resultado) {
		//TODO PENDIENTE DE IMPLEMENTAR EL LISTADO DE ACCIONES QUE TIENE PARA RESULTADO DEL LISTADO.

		String estadoMenu = "menu_verMas|menu_modificar|";
//		if(resultado.getActivo().equals(ACTIVO_NO)){
//			estadoMenu = estadoMenu.concat("menu_reactivar|");
//		}
//		else {
//			estadoMenu = estadoMenu.concat("menu_eliminar|");
//		}
		estadoMenu = estadoMenu.concat("menu_cerrar|");
		resultado.setEstadoMenu(estadoMenu);

	}

}
