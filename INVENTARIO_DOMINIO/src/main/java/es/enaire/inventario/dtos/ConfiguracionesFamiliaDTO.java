package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionesFamiliaDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -1435501995741172231L;
	/**
	 * El id de configuraciones de familia
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionDTO familiaInstalacion;
	/**
	 * Listado de nodos hijos
	 */
	private List<ConfiguracionElementosFamiliaDTO> hijos;
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
	 * Establece los nodos hijos del ConfiguracionElementosFamilia
	 * @param hijos de ConfiguracionElementosFamilia actual
	 */
	public void setHijos(List<ConfiguracionElementosFamiliaDTO> hijos) {
		this.hijos = hijos;
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
