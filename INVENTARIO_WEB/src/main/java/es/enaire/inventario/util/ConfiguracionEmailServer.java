package es.enaire.inventario.util;

/**
 * Clase que se va a utilizar para representar la informacion de la configuracion del servidor de email.
 *
 */
public class ConfiguracionEmailServer {

	/**
	 * La direccion del servidor de email.
	 */
	private String host;
	
	/**
	 * El puerto de escucha del servidor de email.
	 */
	private String port;
	
	/**
	 * Indicador de si tiene que hacerse autenticacion en el servidor SMTP.
	 */
	private boolean autenticacion;
	
	/**
	 * El usuario de acceso al servidor de email.
	 */
	private String user;
	
	/**
	 * El password de acceso al servidor de email.
	 */
	private String password;
	
	/**
	 * La direccion de correo electronico de soporte con el que se van a mandar los emails de forma automatica.
	 */
	private String emailSoporteAutomatico;
	

	/**
	 * Obtiene la direccion del servidor de email.
	 * @return La direccion del servidor de email.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Establece la direccion del servidor de email.
	 * @param host La direccion del servidor de email.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Obtiene el puerto de escucha del servidor de email.
	 * @return El puerto de escucha del servidor de email.
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Establece el puerto de escucha del servidor de email.
	 * @param port El puerto de escucha del servidor de email.
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * Obtiene el usuario de acceso al servidor de email.
	 * @return El usuario de acceso al servidor de email.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Establece el usuario de acceso al servidor de email.
	 * @param user El usuario de acceso al servidor de email.
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Obtiene el password de acceso al servidor de email.
	 * @return El password de acceso al servidor de email.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece el password de acceso al servidor de email.
	 * @param password El password de acceso al servidor de email.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene el indicador de si tiene que hacerse autenticacion en el servidor SMTP.
	 * @return El indicador de si tiene que hacerse autenticacion en el servidor SMTP.
	 */
	public boolean isAutenticacion() {
		return autenticacion;
	}

	/**
	 * Establece el indicador de si tiene que hacerse autenticacion en el servidor SMTP.
	 * @param autenticacion El indicador de si tiene que hacerse autenticacion en el servidor SMTP.
	 */
	public void setAutenticacion(boolean autenticacion) {
		this.autenticacion = autenticacion;
	}

	/**
	 * Obtiene la direccion de correo electronico de soporte con el que se van a mandar los emails de forma automatica.
	 * @return La direccion de correo electronico de soporte con el que se van a mandar los emails de forma automatica.
	 */
	public String getEmailSoporteAutomatico() {
		return emailSoporteAutomatico;
	}

	/**
	 * Establece la direccion de correo electronico de soporte con el que se van a mandar los emails de forma automatica.
	 * @param emailSoporteAutomatico La direccion de correo electronico de soporte con el que se van a mandar los emails de forma automatica.
	 */
	public void setEmailSoporteAutomatico(String emailSoporteAutomatico) {
		this.emailSoporteAutomatico = emailSoporteAutomatico;
	}
}

