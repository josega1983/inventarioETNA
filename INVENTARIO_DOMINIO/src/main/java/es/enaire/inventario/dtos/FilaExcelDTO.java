package es.enaire.inventario.dtos;

import java.io.Serializable;

/**
 * Clase que recoge las filas del excele
 *
 */
public class FilaExcelDTO implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -8683419549194507766L;
	/**
	 * Fila del excel
	 */
	private String[] fila;
	/**
	 * Obtiene la fila de excel
	 * @return la fila de excel
	 */
	public String[] getFila() {
		return fila;
	}
	/**
	 * Establece la fila de excel
	 * @param fila la fila de excel
	 */
	public void setFila(String[] fila) {
		this.fila = fila;
	}
}
