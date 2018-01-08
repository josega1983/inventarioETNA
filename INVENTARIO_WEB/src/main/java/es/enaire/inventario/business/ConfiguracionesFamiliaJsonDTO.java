package es.enaire.inventario.business;

import java.io.Serializable;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionesFamiliaJsonDTO implements Serializable{

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -6713550924421055204L;
	/**
	 * El id de configuraciones d efmailia
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionJsonDTO familiaInstalacion;
	/**
	 * Listado de nodos hijos
	 */
	private List<ConfiguracionElementosFamiliaJsonDTO> hijos;
	/**
	 * Observaciones
	 */
	private String observaciones;
	/**
	 * Activo
	 */
	private String activo;
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
	 * @return la familia de instalaciones
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
	 * Obtiene si es activo
	 * @return si es activo
	 */
	public String getActivo() {
		return activo;
	}
	/**
	 * Establece si es activo
	 * @param activo si es activo
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}
	/**
	 * Obtiene el listado de nodos hijos
	 * @return listado de nodos hijos
	 */
	public List<ConfiguracionElementosFamiliaJsonDTO> getHijos() {
		return hijos;
	}
	/**
	 * Establece los nodos hijos del ConfiguracionElementosFamilia
	 * @param hijos de ConfiguracionElementosFamilia actual
	 */
	public void setHijos(List<ConfiguracionElementosFamiliaJsonDTO> hijos) {
		this.hijos = hijos;
	}
}
