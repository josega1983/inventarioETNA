package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.annotations.IdTarget;
/**
 * Clase de mapeo con la informacion de region.
 *
 */
public class TipoInstalacionDTO implements Serializable {
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -4183866473418900119L;
	/**
	 * El id 
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionDTO familiaInstalacion;
	/**
	 * Marca
	 */
	private String marca;
	/**
	 * Modelo
	 */
	private String modelo;
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
	 * Obtiene la familia de instalaciones
	 * @return  la familia de instalaciones
	 */
	public FamiliaInstalacionDTO getFamiliaInstalacion() {
		return familiaInstalacion;
	}
	/**
	 * Establece la familia de instalaciones
	 * @param familiaInstalacion la familia de instalaciones
	 */
	public void setFamiliaInstalacion(FamiliaInstalacionDTO familiaInstalacion) {
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
