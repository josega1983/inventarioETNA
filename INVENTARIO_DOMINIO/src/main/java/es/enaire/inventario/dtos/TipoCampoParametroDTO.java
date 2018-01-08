package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class TipoCampoParametroDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 3516261769688846092L;
	/**
	 * El id de tipo de campos parámetros
	 */
	@IdTarget
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Tipo
	 */
	private Integer tipo;
	/**
	 * Opciones a mostrar en el estado del menu
	 */
	private String estadoMenu;
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
	/**
	 * Obtiene el tipo
	 * @return el tipo
	 */
	public Integer getTipo() {
		return tipo;
	}
	/**
	 * Establece el tipo
	 * @param tipo el tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	/**
	 * Obtiene el estado del menu
	 * @return El estado del menu
	 */
	public String getEstadoMenu() {
		return estadoMenu;
	}
	/**
	 * Establece el estado del menu
	 * @param estadoMenu El estado de menu a establecer
	 */
	public void setEstadoMenu(String estadoMenu) {
		this.estadoMenu = estadoMenu;
	}
}
