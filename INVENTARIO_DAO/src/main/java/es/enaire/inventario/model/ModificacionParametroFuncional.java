package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.dtos.ModificacionParametroFuncionalDTO;

public class ModificacionParametroFuncional implements IBaseEntity<ModificacionParametroFuncionalDTO>,
		Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -7275313361637119507L;
	/**
	 * El Id
	 */
	private Long id;
	/**
	 * Parametro Funcional
	 */
	private ParametroFuncional parametroFuncional;
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
	 * Fecha de baja
	 */
	private Date fechaBaja;
	/**
	 * Modificacion
	 */
	private String modificacion;
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
	 * Obtiene el parametro funcional
	 * @return el parametro funcional
	 */
	public ParametroFuncional getParametroFuncional() {
		return parametroFuncional;
	}
	/**
	 * Establece el parametro funcional
	 * @param parametroFuncional el parametro funcional
	 */
	public void setParametroFuncional(ParametroFuncional parametroFuncional) {
		this.parametroFuncional = parametroFuncional;
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
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ModificacionParametroFuncional(ModificacionParametroFuncionalDTO dto) {
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setDescripcion(dto.getDescripcion());
		this.setFechaBaja(dto.getFechaBaja());
		this.setModificacion(dto.getModificacion());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
		if(dto.getParametroFuncional() != null){
			this.setParametroFuncional(new ParametroFuncional(dto.getParametroFuncional()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ModificacionParametroFuncional() {
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	@Override
	public ModificacionParametroFuncionalDTO crearDTO() {
		ModificacionParametroFuncionalDTO dto = new ModificacionParametroFuncionalDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setDescripcion(getDescripcion());
		dto.setFechaBaja(getFechaBaja());
		dto.setModificacion(getModificacion());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		if(getParametroFuncional() != null){
			dto.setParametroFuncional(getParametroFuncional().crearDTO());
		}
		return dto;
	}



}
