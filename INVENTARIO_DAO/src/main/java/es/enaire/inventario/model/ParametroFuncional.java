package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.enaire.inventario.annotations.DTOTarget;
import es.enaire.inventario.dtos.ParametroFuncionalDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla PARAMETROS_FUNCIONALES.
 *
 */
@Entity
@Table(name="PARAMETROS_FUNCIONALES")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.ParametroFuncionalDTO")
public class ParametroFuncional implements IBaseEntity<ParametroFuncionalDTO>,
		Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -1551644932404299270L;
	/**
	 * El Id
	 */
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
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_PARAMETROS_FUNCIONALES", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_PARAMETROS_FUNCIONALES", sequenceName = "SEQ_PARAMETROS_FUNCIONALES", allocationSize = 1)
	@Column(name = "ID_PARAMETRO_FUNCIONAL")
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
	@Basic
	@Column(name="NOMBRE", nullable=false)
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
	@Basic
	@Column(name="DESCRIPCION", nullable=true)
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
	@Basic
	@Column(name="ACTIVO", nullable=false)
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
	@Basic
	@Column(name="FECHA_ALTA", nullable=false)
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
	@Basic
	@Column(name="FECHA_BAJA", nullable=true)
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
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ParametroFuncional(ParametroFuncionalDTO dto) {
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setDescripcion(dto.getDescripcion());
		this.setFechaAlta(dto.getFechaAlta());
		this.setFechaBaja(dto.getFechaBaja());
		this.setNombre(dto.getNombre());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ParametroFuncional() {
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	@Override
	public ParametroFuncionalDTO crearDTO() {
		ParametroFuncionalDTO dto = new ParametroFuncionalDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setDescripcion(getDescripcion());
		dto.setFechaAlta(getFechaAlta());
		dto.setFechaBaja(getFechaBaja());
		dto.setNombre(getNombre());
		return dto;
	}



}
