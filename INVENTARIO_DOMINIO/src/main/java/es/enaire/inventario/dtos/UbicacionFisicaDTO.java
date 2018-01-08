package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Clase de mapeo con la informacion de region.
 *
 */
public class UbicacionFisicaDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -2641891817977817826L;
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
	 * Region
	 */
	private RegionDTO region;
	/**
	 * Localizacion
	 */
	private LocalizacionDTO localizacion;
	/**
	 * Emplazamiento
	 */
	private EmplazamientoDTO emplazamiento;
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
	 * Obtiene el nombre de la Ubicacion Fisica
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el nombre de la Ubicaicon Fisica
	 * @param String nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene la region
	 * @return la region
	 */
	public RegionDTO getRegion() {
		return region;
	}
	/**
	 * Establece la region
	 * @param region
	 */
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	/**
	 * Obtiene la localizacion
	 * @return la localizacion
	 */
	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}
	/**
	 * Establece la localizacion
	 * @param localizacion la localizacion
	 */
	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}
	/**
	 * Obtiene el emplazamiento
	 * @return el emplazamiento
	 */
	public EmplazamientoDTO getEmplazamiento() {
		return emplazamiento;
	}
	/**
	 * Establece el emplazamiento
	 * @param emplazamiento el emplazamiento
	 */
	public void setEmplazamiento(EmplazamientoDTO emplazamiento) {
		this.emplazamiento = emplazamiento;
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
	
}
