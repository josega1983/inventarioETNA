package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Clase de mapeo con la informacion de region.
 *
 */
public class FamiliaElementoDTO implements Serializable {
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -4223379153073226656L;
	/**
	 * El id de Region
	 */
	@IdTarget
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Descripcion
	 */
	private String descripcion;
	/**
	 * Observaciones
	 */
	private String observaciones;
	/**
	 * Activo
	 */
	private String activo;
	/**
	 * Fecha de Alta
	 */
	private Date fechaAlta;
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
	 * Obtiene la descripcion
	 * @return la descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Establece la descripcion
	 * @param descripcion la descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
}
