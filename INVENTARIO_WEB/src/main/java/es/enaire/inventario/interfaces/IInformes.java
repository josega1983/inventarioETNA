package es.enaire.inventario.interfaces;

import java.util.Map;

/**
 * Interface de generacion de informes PDF para envios
 *
 */

public interface IInformes {
	/**
	 * Método que nos obtiene la lista de parametros del informe
	 * @return La lista de parametros
	 */
	public Map<String, Object> getParametersList();
}