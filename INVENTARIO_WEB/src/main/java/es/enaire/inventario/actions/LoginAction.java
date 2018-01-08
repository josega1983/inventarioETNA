package es.enaire.inventario.actions;


import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.dtos.UsuarioDTO;


public class LoginAction extends BaseAction<UsuarioDTO> implements SessionAware{

	/**
	 * Constante de serializacion
	 */
	private static final long serialVersionUID = -2444484703377625103L;
	
	/**
	 * Constructor estandar de la clase
	 */
	public LoginAction() {
		elemento = new UsuarioDTO();
	}
	/**
	 * Devuelve el DTO del Usuario que ha accedido a la aplicacion.
	 * @return elemento
	 */
	@Override
	public UsuarioDTO getModel() {
		return elemento;
	}

	/**
     * Metodo que nos realiza el log Out de la sesion.
     * @return SUCCESS.
     */
    public String logOut() {
		getSession().clear();
		return SUCCESS;
	}

    /**
     * Metodo para redirigir a la pagina de que no tiene permisos de acceso.
     * @return SUCCESS.
     */
    public String sinPermisos() {
    	return SUCCESS;
    }

	/**
	 * Metodo para aplicar los menus de acciones en funcion del estado del elemento.
	 * @param resultado El resultado del listado para aplicar el estado del menu.
	 */
	@Override
	public void aplicarEstadoMenu(UsuarioDTO resultado) {
	}
}
