package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionElementosFamiliaDTO implements Serializable {
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 6000583447877720374L;
	/**
	 * El id de configuraciones de elementos familia
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia de elementos
	 */
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Configuracion Elementos Familia Padre
	 */
	private List<ConfiguracionElementosFamiliaDTO> hijos;
	/**
	 * Observaciones
	 */
	private String observaciones;
	/**
	 * Activo
	 */
	private String activo;
	/**
	 * Fecha de baja
	 */
	private Date fechaBaja;
	/**
	 * Fecha de Alta
	 */
	private Date fechaAlta;
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
	 * Obtiene la familia de elemento
	 * @return la familia de elemento
	 */
	public FamiliaElementoDTO getFamiliaElemento() {
		return familiaElemento;
	}
	/**
	 * Establece la familia de elemento
	 * @param familiaElemento la familia de elemento
	 */
	public void setFamiliaElemento(FamiliaElementoDTO familiaElemento) {
		this.familiaElemento = familiaElemento;
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
	 * Obtiene el area
	 * @return el area
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * Establece el area
	 * @param fechaAlta el area
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * Obtiene la fecha de baja
	 * @return la fecha de baja
	 */
	public Date getFechaBaja() {
		return fechaBaja;
	}
	/**
	 * Establece la fecha de baja
	 * @param fechaBaja la fecha de baja
	 */
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
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
	/**
	 * Obtiene el listado de nodos hijos
	 * @return listado de nodos hijos
	 */
	public List<ConfiguracionElementosFamiliaDTO> getHijos() {
		return hijos;
	}
	/**
	 * Establece los nodos hijos del configuracionElementosFamilia
	 * @param hijos de configuracionElementosFamilia actual
	 */
	public void setHijos(List<ConfiguracionElementosFamiliaDTO> hijos) {
		this.hijos = hijos;
	}
}
