package es.enaire.inventario.util;

import java.util.ResourceBundle;


/**
 * Clase que tiene funcionalidades para gestionar las propiedades de configuracion de la aplicacion.
 *
 */
public class GestionPropiedadesConfiguracion {
	/**
	 * Variable del propertie de debug.
	 */
	public static String MODE_DEBUG="mode.debug";
	/**
	 * Varible de tamano maximo de ficheros
	 */
	public static String MAX_LIST_SIZE="list.max.size";
	/**
	 * Variable del patron de emails de usuarios internos.
	 */
	public static String EMAIL_PATRON_INTERNOS = "email.patron.internos";
	
	/**
	 * El archivo que contiene la configuracion de la aplicacion.
	 */
	public static String FILE_CONFIG = "inventario";
	
	/**
	 * Recursos de la aplicacion
	 */
	public static ResourceBundle rb = ResourceBundle.getBundle(FILE_CONFIG);

	/**
	 * Obtiene de las propiedades de debug de la aplicacion.
	 * @return La configuracion del debug de la aplicacion.
	 */
	public static boolean getModeDebug() {
		return new Boolean(rb.getString(MODE_DEBUG));
	}
	/**
	 * Obtiene el tamano maximo de los listados de la aplicacion
	 * @return el tamanio maximo de los listado
	 */
	public static Long getMaxSizeList() {
		return Long.parseLong(rb.getString(MAX_LIST_SIZE));
	}
	/**
	 * Obtiene el patron de emails de usuarios internos.
	 * @return El patron de emails de usuarios internos.
	 */
	public static String getEmailPatronInternos() {
		return rb.getString(EMAIL_PATRON_INTERNOS);
	}
}

