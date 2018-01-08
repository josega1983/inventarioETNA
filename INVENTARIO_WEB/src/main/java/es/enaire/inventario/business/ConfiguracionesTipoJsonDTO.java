package es.enaire.inventario.business;

import java.io.Serializable;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionesTipoJsonDTO implements Serializable{

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 1828090367208749829L;
	/**
	 * El id de configuraciones de tipo
	 */
	@IdTarget
	private Long id;
	/**
	 * Tipo Instalaciones
	 */
	private TipoInstalacionJsonDTO tipoInstalacion;
	/**
	 * Configuracion Elementos Tipo hijos
	 */
	private List<ConfiguracionElementosTipoJsonDTO> hijos;
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
	public TipoInstalacionJsonDTO getTipoInstalacion() {
		return tipoInstalacion;
	}
	/**
	 * Establece la familia de instalaciones
	 * @param familiaInstalacion la familia de instalaciones
	 */
	public void setTipoInstalacion(TipoInstalacionJsonDTO tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
	}
	/**
	 * Obtiene la Configuracion Elementos Tipo hijos
	 * @return la Configuracion Elementos Tipo hijos
	 */
	public List<ConfiguracionElementosTipoJsonDTO> getHijos() {
		return hijos;
	}
	/**
	 * Establece la Configuracion Elementos Tipo hijos
	 * @param hijos la Configuracion Elementos Tipo hijos
	 */
	public void setHijos(List<ConfiguracionElementosTipoJsonDTO> hijos) {
		this.hijos = hijos;
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

}
