package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Clase de mapeo con la informacion de familia instalaciones.
 *
 */
public class FamiliaInstalacionJsonDTO implements Serializable{

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 175340987988790508L;
	/**
	 * El id
	 */
	@IdTarget
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Obtiene el id
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Establece el id
	 * @param id el id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Obtiene el nombre
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el nombre
	 * @param nombre el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
