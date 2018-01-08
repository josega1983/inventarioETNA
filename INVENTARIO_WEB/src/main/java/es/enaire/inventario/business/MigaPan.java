package es.enaire.inventario.business;

import java.util.Map;


/**
 * Clase que representa la informacion de cada miga de pan, donde se especifica el sitio donde se encuentra.
 *
 */
public class MigaPan {
	
	/**
	 * La etiqueta visual para el usuario que indica la seccion asociada al link. 
	 */
	private String etiqueta;
	
	/**
	 * El espacio de nombres para la redireccion cuando el usuario pulse sobre la miga de pan.
	 */
	private String espacioNombre;
	
	/**
	 * La accion a desencadenar sobre el espacio de nombres especificado.
	 */
	private String accionNombre;
	
	/**
	 * Los parametros asociados a la miga de pan.
	 */
	private Map<String, String> parametros;
	

	/**
	 * Constructor de una miga de pan con su etiqueta y enlace.
	 * @param etiqueta La etiqueta asociada a la miga de pan.
	 * @param espacioNombre El espacio de nombres para la redireccion.
	 * @param accionNombre El nombre de la accion a desencadenar en la redireccion.
	 */
	public MigaPan(String etiqueta, String espacioNombre, String accionNombre) {
		this.etiqueta = etiqueta;
		this.espacioNombre = espacioNombre;
		this.accionNombre = accionNombre;
	}
	
	/**
	 * Constructor de una miga de pan con su etiqueta y enlace.
	 * @param etiqueta La etiqueta asociada a la miga de pan.
	 * @param espacioNombre El espacio de nombres para la redireccion.
	 * @param accionNombre El nombre de la accion a desencadenar en la redireccion.
	 * @param parametros Los parametros asociados a la accion a desencadendar.
	 */
	public MigaPan(String etiqueta, String espacioNombre, String accionNombre, Map<String, String> parametros) {
		this.etiqueta = etiqueta;
		this.espacioNombre = espacioNombre;
		this.accionNombre = accionNombre;
		this.parametros = parametros;
	}
	
	/**
	 * Devuelve la etiqueta asociada a la miga de pan.
	 * @return La etiqueta asociada a la miga de pan.
	 */
	public String getEtiqueta() {
		return etiqueta;
	}
	
	/**
	 * Establece la etiqueta asociada a la miga de pan.
	 * @param etiqueta la etiqueta asociada a la miga de pan.
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	/**
	 * Devuelve el espacio de nombres asociado a la miga de pan.
	 * @return El espacio de nombres asociado a la miga de pan.
	 */
	public String getEspacioNombre() {
		return espacioNombre;
	}
	
	/**
	 * Establece el espacio de nombres asociado a la miga de pan.
	 * @param espacioNombre El espacio de nombres asociado a la miga de pan.
	 */
	public void setEspacioNombre(String espacioNombre) {
		this.espacioNombre = espacioNombre;
	}
	
	/**
	 * Devuelve el nombre de la accion asociada a la miga de pan.
	 * @return El nombre de la accion asociada a la miga de pan.
	 */
	public String getAccionNombre() {
		return accionNombre;
	}
	
	/**
	 * Establece el nombre de la accion asociada a la miga de pan.
	 * @param accionNombre El nombre de la accion asociada a la miga de pan.
	 */
	public void setAccionNombre(String accionNombre) {
		this.accionNombre = accionNombre;
	}

	/**
	 * Devuelve los parametros asociados a la miga de pan.
	 * @return Los parametros asociados a la miga de pan.
	 */
	public Map<String, String> getParametros() {
		return parametros;
	}

	/**
	 * Establece los parametros asociados a la miga de pan.
	 * @param parametros Los parametros asociados a la miga de pan.
	 */ 
	public void setParametros(Map<String, String> parametros) {
		this.parametros = parametros;
	}
}

