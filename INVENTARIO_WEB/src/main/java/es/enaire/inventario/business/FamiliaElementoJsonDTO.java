package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class FamiliaElementoJsonDTO implements Serializable{
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -1806410044368528198L;
	/**
	 * El id de Familia Elemento
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
