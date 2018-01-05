package es.enaire.etnaj.actions;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport  {

	private static final long serialVersionUID = -319307496182411343L;
	/**
	 * Usuario logueado.
	 */
	private String user;

	/**
	 * Accion que redirige a la pagina de bienvenida
	 * @return welcome
	 */
    public String welcome() {
        return "welcome";
    }
    /**
     * Metodo de autenticacion
     * @return SUCCESS
     */
    public String authenticate() {
    	//TODO: habra que cambiar en un futuro la forma de obtener el usuario (Los iguales no viajan). Asi que habra que pegarle a esto otra codificacion de decode.
		setUser("QZfc1TJu2uVVecbmgRcz8Q");
		return SUCCESS;
	}
    /**
     * Obtiene el usuario logueado
     * @return el usuario logueado
     */
	public String getUser() {
		return user;
	}
	/**
	 * Establece el usuario logueado
	 * @param user el usuario
	 */

	public void setUser(String user) {
		this.user = user;
	}
}
