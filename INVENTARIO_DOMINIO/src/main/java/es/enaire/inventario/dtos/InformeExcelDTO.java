package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que nos recoge la informacion de todo el informe excel
 *
 */
public class InformeExcelDTO implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -4464034604731146049L;
	/**
	 * Nombre del informe
	 */
	private String nombre;
	/**
	 * Cabecera del informe
	 */
	private String[] cabecera;
	/**
	 * Filas del informe
	 */
	private List<FilaExcelDTO> filas;
	/**
	 * Obtiene el Nombre del informe
	 * @return el  Nombre del informe
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el  Nombre del informe
	 * @param nombre el  Nombre del informe
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene la cabecera
	 * @return la cabecera
	 */
	public String[] getCabecera() {
		return cabecera;
	}
	/**
	 * Establece la cabecera
	 * @param cabecera la cabecera
	 */
	public void setCabecera(String[] cabecera) {
		this.cabecera = cabecera;
	}
	/**
	 * Obtiene las filas
	 * @return las filas
	 */
	public List<FilaExcelDTO> getFilas() {
		return filas;
	}
	/**
	 * Establece las filas
	 * @param filas las filas
	 */
	public void setFilas(List<FilaExcelDTO> filas) {
		this.filas = filas;
	}

}
