package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionesTipoDTO implements Serializable {
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 7309046924310845142L;
	/**
	 * El id de configuraciones de tipos
	 */
	@IdTarget
	private Long id;
	/**
	 * Tipo Instalaciones
	 */
	private TipoInstalacionDTO tipoInstalacion;
	/**
	 * Configuracion Elementos Tipo hijos
	 */
	private List<ConfiguracionElementosTipoDTO> hijos;
	/**
	 * Nombre
	 */
	private String nombre;
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
	 * El contenido de la imagen que representa la estructura de la configuracion.
	 */
	private String imagenArbol;

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
	public TipoInstalacionDTO getTipoInstalacion() {
		return tipoInstalacion;
	}
	/**
	 * Establece la familia de instalaciones
	 * @param familiaInstalacion la familia de instalaciones
	 */
	public void setTipoInstalacion(TipoInstalacionDTO tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
	}
	/**
	 * Obtiene la Configuracion Elementos Tipo hijos
	 * @return la Configuracion Elementos Tipo hijos
	 */
	public List<ConfiguracionElementosTipoDTO> getHijos() {
		return hijos;
	}
	/**
	 * Establece la Configuracion Elementos Tipo hijos
	 * @param hijos la Configuracion Elementos Tipo hijos
	 */
	public void setHijos(List<ConfiguracionElementosTipoDTO> hijos) {
		this.hijos = hijos;
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
	 * Obtiene el contenido de la imagen que representa la estructura de la configuracion.
	 * @return El contenido de la imagen que representa la estructura de la configuracion.
	 */
	public String getImagenArbol() {
		return imagenArbol;
	}
	/**
	 * Establece el contenido de la imagen que representa la estructura de la configuracion.
	 * @param imagenArbol El contenido de la imagen que representa la estructura de la configuracion.
	 */
	public void setImagenArbol(String imagenArbol) {
		this.imagenArbol = imagenArbol;
	}
}
