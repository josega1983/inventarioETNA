package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Clase de mapeo con la informacion de Tipo de installacion JSON.
 *
 */
public class TipoInstalacionJsonDTO implements Serializable{

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 132385615822131134L;
	/**
	 * El id 
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionJsonDTO familiaInstalacion;
	/**
	 * Marca
	 */
	private String marca;
	/**
	 * Modelo
	 */
	private String modelo;
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
	 * Obtiene la familia de instalaciones
	 * @return  la familia de instalaciones
	 */
	public FamiliaInstalacionJsonDTO getFamiliaInstalacion() {
		return familiaInstalacion;
	}
	/**
	 * Establece la familia de instalaciones
	 * @param familiaInstalacion la familia de instalaciones
	 */
	public void setFamiliaInstalacion(FamiliaInstalacionJsonDTO familiaInstalacion) {
		this.familiaInstalacion = familiaInstalacion;
	}
	/**
	 * Obtiene la marca
	 * @return la marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * Establece la marca
	 * @param marca la marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * Obtiene el modelo
	 * @return el modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Establece el modelo
	 * @param modelo el modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
