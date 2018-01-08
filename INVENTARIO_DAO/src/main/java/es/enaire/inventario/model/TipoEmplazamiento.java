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
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla TIPO_EMPLAZAMIENTOS.
 *
 */
@Entity
@Table(name="TIPO_EMPLAZAMIENTOS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.TipoEmplazamientoDTO")
public class TipoEmplazamiento implements Serializable, IBaseEntity<TipoEmplazamientoDTO> {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -1350556376430037496L;
	/**
	 * El id de Tipo de Emplazamiento
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
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_TIPO_EMPLAZAMIENTOS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_TIPO_EMPLAZAMIENTOS", sequenceName = "SEQ_TIPO_EMPLAZAMIENTOS", allocationSize = 1)
	@Column(name = "ID_TIPO_EMPLAZAMIENTO")
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
	 * Obtiene las observaciones
	 * @return las observaciones
	 */
	@Basic
	@Column(name="OBSERVACIONES", nullable=true)
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
	@Column(name="ACTIVO", nullable=true)
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
	public TipoEmplazamiento(TipoEmplazamientoDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setDescripcion(dto.getDescripcion());
		this.setFechaBaja(dto.getFechaBaja());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public TipoEmplazamiento() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public TipoEmplazamientoDTO crearDTO() {
		TipoEmplazamientoDTO dto = new TipoEmplazamientoDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setDescripcion(getDescripcion());
		dto.setFechaBaja(getFechaBaja());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		return dto;
	}

}
