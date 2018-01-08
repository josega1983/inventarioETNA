package es.enaire.inventario.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.business.FiltroElemento;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.ElementoDTO;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.EstadoElementoDTO;
import es.enaire.inventario.dtos.RespuestaDTO;
import es.enaire.inventario.util.ConstantesMigasPan;

public class ElementoAction extends BaseAction<ElementoDTO> implements
		SessionAware {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 4291286049093459264L;
	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Elemento";
	/**
	 * Clave para almacenar el filtro de busqueda en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_ELEMENTO = "Filtro_busqueda_elemento";

	/**
	 * Clave para almacenar el filtro de busqueda del popup en la sesion.
	 */
	private final String FILTRO_BUSQUEDA_POPUP_ELEMENTO = "Filtro_busqueda_popup_elemento";
	/**
	 * Listado de estados de elementos
	 */
	private List<EstadoElementoDTO> estadoElementoList;
	/**
	 * Constructor
	 */
	public ElementoAction() {
		elemento = new ElementoDTO();
		nombreElemento = ELEMENTO;
		tipoEntrada = TipoEntradaConstants.ELEMENTO;
		nombreFiltroSesion = FILTRO_BUSQUEDA_ELEMENTO;
		nombreFiltroPopup = FILTRO_BUSQUEDA_POPUP_ELEMENTO;
		setNamespace(ConstantesMigasPan.ESPACIO_ELEMENTO);
		setFiltro(new FiltroElemento());
		estadoElementoList = new ArrayList<EstadoElementoDTO>();
		
	}
	/**
	 * Devuelve el DTO de la Elemento.
	 * @return elemento
	 */
	@Override
	public ElementoDTO getModel() {
		if (!isInicializado()) {
			RespuestaDTO respuestaEstadoElementos = getFacadeBackend().list(new EntradaDTO(null, 0L, 10L, TipoEntradaConstants.ESTADO_ELEMENTO, null));
			if (respuestaEstadoElementos.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
				Object[] objetoRespuestaEstadoElementos = respuestaEstadoElementos.getObjetoRespuesta();
				if (objetoRespuestaEstadoElementos != null) {
					for (Object object : objetoRespuestaEstadoElementos) {
						EstadoElementoDTO estadoElemento = (EstadoElementoDTO) object;
						estadoElementoList.add(estadoElemento);
					}
					
				}
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
		nombreMiga = ConstantesMigasPan.LISTADO_ELEMENTOS;
		return super.list();
	}


	/**
	 * Accion para volver a mostrar el listado de Elemento para que el usuario pueda trabajar de nuevo.
	 * @return El resultado del listado de empresas para trabajar.
	 */
	@Override
	public String volverList(){
		nombreMiga = ConstantesMigasPan.LISTADO_ELEMENTOS;
		return super.volverList();
	}
	/**
	 * Accion de buscar 
	 */
	@Override
	public String search(){
		nombreMiga = ConstantesMigasPan.LISTADO_ELEMENTOS;
		return super.search();
	}
	public void aplicarEstadoMenu(ElementoDTO resultado) {
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
	 * Obtiene el listado de estados de elemento
	 * @return el listado de estados de elemento
	 */
	public List<EstadoElementoDTO> getEstadoElementoList() {
		return estadoElementoList;
	}
	/**
	 * Establece el listado de estados de elemento
	 * @param estadoElementoList el listado de estados de elemento
	 */
	public void setEstadoElementoList(List<EstadoElementoDTO> estadoElementoList) {
		this.estadoElementoList = estadoElementoList;
	}
}
