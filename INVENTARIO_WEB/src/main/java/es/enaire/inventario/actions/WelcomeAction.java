package es.enaire.inventario.actions;


import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.UsuarioDTO;
import es.enaire.inventario.util.ConstantesMigasPan;

/**
 * Accion para la gestion de migas de pan.
 * @param <T>
 *
 */
public class WelcomeAction extends BaseAction<UsuarioDTO> implements SessionAware {

	/**
	 * Identificador unico de serializacion.
	 */
	private static final long serialVersionUID = -7944234680873857840L;

	/**
	 * Constante string que define el elemento sobre el trabaja este action.
	 */
	private static final String ELEMENTO = "Welcome";


	/**
	 * Usuario logueado.
	 */
	private String user;
	
	/**
	 * Constructor
	 */
	public WelcomeAction() {
		elemento = new UsuarioDTO();
		tipoEntrada = TipoEntradaConstants.USUARIO;
		nombreElemento = ELEMENTO;
		setNamespace(ConstantesMigasPan.ESPACIO_AUTENTICATE);
	}

	/**
	 * Devuelve el DTO del usuario.
	 * @return elemento
	 */
	@Override
	public UsuarioDTO getModel() {
		return elemento;
	}
	
	/**
	 * Accion para mostrar la pagina de inicio.
	 * @return Redireccion a la pagina de inicio.
	 */
	public String welcome() {
        return "welcome";
    }
	
	 /**
     * Metodo de autenticacion
     * @return El resultado de la autenticacion ERROR, SUCCESS, o welcomeLink
     * @throws Exception Excepciones producidas durante el proceso de descrifrado.
     */
    public String authenticate() throws Exception {
    	
//    	user = EncryptDecrypt.decrypt(user);
//    	Map<String, Object> session = getSession();
//    	session.put(Constantes.USUARIO_ETNAJ, user);
//    	setSession(session);
//    	try {
//    		UsuarioDTO usuario = usuarioDAO.listByNick(user);
//    		PerfilDTO perfil = perfilDAO.listByNick(user);
//    		UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(usuario, perfil);
//    		session.put(Constantes.USUARIO_AUTENTICADO, usuarioAutenticado);
//		
//    	
//    	} catch (Exception e) {
//    		trazarLOG(this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getMethodName(), e);
//			return ERROR;
//		}
    	
		return "welcomeLink";
    }

	
    /**
     * Obtiene el usuario que ha accedido a la aplicacion
     * @return el usuario que ha accedido a la aplicacion
     */
	public String getUser() {
		return user;
	}
	/**
	 * Establece el usuario que ha accedido a la aplicacion
	 * @param user El usuario que ha accedido a la aplicacion
	 */
	public void setUser(String user) {
		this.user = user;
	}
    
    
	
	/**
	 * Metodo para aplicar los menus de acciones en funcion del estado del elemento.
	 * @param resultado El resultado del listado para aplicar el estado del menu.
	 */
	@Override
	public void aplicarEstadoMenu(UsuarioDTO resultado) {
	}
}
