package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Entidad que representa el estado de la empresa.
 *
 */
public class EstadoEmpresaDTO implements Serializable {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 4258427065382030152L;
	/**
	 * Id de Estado
	 */
	@IdTarget
	private Long id;

	/**
	 * Nombre del estado
	 */
	private String nombre;

	/**
	 * Obtiene el Id de Estado
	 * @return el Id de Estado
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Establece el Id de Estado
	 * @param id el Id de Estado
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Obtiene el Nombre de estado
	 * @return el Nombre de estado
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el Nombre de estado
	 * @param nombre el Nombre de estado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
