package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Clase de mapeo de la asociacion de ubicaciones logicas y fisicas
 *
 */

public class UbicacionLogicaFisicaDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -201860880553821593L;
	/**
	 * El id
	 */
	@IdTarget
	private Long id;
	/**
	 * Ubicacion logica
	 */
	private UbicacionLogicaDTO ubicacionLogica;
	/**
	 * Ubicacion fisica
	 */
	private UbicacionFisicaDTO ubicacionFisica;
	/**
	 * Observaciones 
	 */
	private String observaciones;
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
	 * Obtiene la ubicacion Logica
	 * @return la ubicacion Logica
	 */
	public UbicacionLogicaDTO getUbicacionLogica() {
		return ubicacionLogica;
	}
	/**
	 * Establece la ubicacion Logica
	 * @param ubicacionLogica la ubicacion Logica
	 */
	public void setUbicacionLogica(UbicacionLogicaDTO ubicacionLogica) {
		this.ubicacionLogica = ubicacionLogica;
	}
	/**
	 * Obtiene la ubicacion Fisica
	 * @return la ubicacion Fisica
	 */
	public UbicacionFisicaDTO getUbicacionFisica() {
		return ubicacionFisica;
	}
	/**
	 * Establece la ubicacion Fisica
	 * @param ubicacionFisica la ubicacion Fisica
	 */
	public void setUbicacionFisica(UbicacionFisicaDTO ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}
	/**
	 * Obtiene las observaciones
	 * @return las observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Establece las observaciones
	 * @param observaciones las observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
