package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ParametroFuncionalDTO implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 7326142600638482135L;
	/**
	 * El Id
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
	 * Modificacion
	 */
	private String modificacion;
	/**
	 * Lista de Configuracion de parametros funcionales
	 */
	private List<ConfiguracionParametroFuncionalDTO> configuracionParametroFuncionalList;
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
	 * Obtiene si es una modificacion
	 * @return si es una modificacion
	 */
	public String getModificacion() {
		return modificacion;
	}
	/**
	 * Establece si es una modificacion
	 * @param modificacion si es una modificacion
	 */
	public void setModificacion(String modificacion) {
		this.modificacion = modificacion;
	}
	/**
	 * Obtiene la Lista de Configuracion de parametros funcionales
	 * @return la Lista de Configuracion de parametros funcionales
	 */
	public List<ConfiguracionParametroFuncionalDTO> getConfiguracionParametroFuncionalList() {
		return configuracionParametroFuncionalList;
	}
	/**
	 * Establece la Lista de Configuracion de parametros funcionales
	 * @param configuracionParametroFuncionalList la Lista de Configuracion de parametros funcionales
	 */
	public void setConfiguracionParametroFuncionalList(
			List<ConfiguracionParametroFuncionalDTO> configuracionParametroFuncionalList) {
		this.configuracionParametroFuncionalList = configuracionParametroFuncionalList;
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
