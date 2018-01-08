package es.enaire.inventario.util;



import java.util.ResourceBundle;


/**
 * Clase que tiene funcionalidades para gestionar las propiedades de configuracion del email.
 *
 */
public class GestionPropiedadesEmail {

	/**
	 * Propiedad de la configuracion del host SMTP.
	 */
	public static String MAIL_SMTP_HOST = "mail.smtp.host";

	/**
	 * Propiedad de la configuracion del puerto SMTP.
	 */
	public static String MAIL_SMTP_PORT = "mail.smtp.port";

	/**
	 * Propiedad de la configuracion del usuario de acceso al servidor de email.
	 */
	public static String MAIL_USER = "mail.user";

	/**
	 * Propiedad de la configuracion del password de acceso al servidor de email.
	 */
	public static String MAIL_PASS = "mail.password";

	/**
	 * Propiedad de la configuracion para si se tiene que autenticar en el servidor SMTP.
	 */
	public static String MAIL_AUTENTICACION = "mail.autenticacion";

	/**
	 * Propiedad que contiene el email de soporte automatico de inventario.
	 */
	public static String EMAIL_SOPORTE_INVENTARIO = "email.automatico.soporte";
	
	/**
	 * El archivo que contiene la configuracion del email.
	 */
	public static String FILE_CONFIG = "email";
	
	/**
	 * Recursos de la aplicacion
	 */
	public static ResourceBundle rb = ResourceBundle.getBundle(FILE_CONFIG);
	
	/**
	 * Obtiene de las propiedades de configuracion de la aplicacion la configuracion del servidor de email.
	 * @return La configuracion del email del servidor.
	 */
	public static ConfiguracionEmailServer  getConfiguracionEmailServer() {
		ConfiguracionEmailServer configuracion = new ConfiguracionEmailServer();
		configuracion.setHost(rb.getString(MAIL_SMTP_HOST));
		configuracion.setPort(rb.getString(MAIL_SMTP_PORT));
		configuracion.setUser(rb.getString(MAIL_USER));
		configuracion.setAutenticacion(new Boolean(rb.getString(MAIL_AUTENTICACION)));
		configuracion.setPassword(rb.getString(MAIL_PASS));
		configuracion.setEmailSoporteAutomatico(rb.getString(EMAIL_SOPORTE_INVENTARIO));

		return configuracion;
	}
}

