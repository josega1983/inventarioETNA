package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.enaire.inventario.annotations.DTOTarget;
import es.enaire.inventario.dtos.EmplazamientoDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla EMPLAZAMIENTOS_MI.
 *
 */
@Entity
@Table(name="EMPLAZAMIENTOS_MI")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.EmplazamientoDTO")
public class Emplazamiento implements Serializable, IBaseEntity<EmplazamientoDTO> {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 8500228021604435327L;


	/**
	 * El id de Region
	 */
	private Long id;
	/**
	 * Tipo de Emplazamiento
	 */
	private TipoEmplazamiento tipoEmplazamiento;
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
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_EMPLAZAMIENTOS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_EMPLAZAMIENTOS", sequenceName = "SEQ_EMPLAZAMIENTOS", allocationSize = 1)
	@Column(name = "ID_EMPLAZAMIENTO")
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
	 * Obtiene el tipo de emplazamiento
	 * @return el tipo de emplazamiento
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_EMPLAZAMIENTO", nullable = false)
	public TipoEmplazamiento getTipoEmplazamiento() {
		return tipoEmplazamiento;
	}
	/**
	 * Establece el tipo de emplazamiento
	 * @param tipoEmplazamiento el tipo de emplazamiento
	 */
	public void setTipoEmplazamiento(TipoEmplazamiento tipoEmplazamiento) {
		this.tipoEmplazamiento = tipoEmplazamiento;
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
	public Emplazamiento(EmplazamientoDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setFechaBaja(dto.getFechaBaja());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
		if(dto.getTipoEmplazamiento() != null){
			this.setTipoEmplazamiento(new TipoEmplazamiento(dto.getTipoEmplazamiento()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public Emplazamiento() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public EmplazamientoDTO crearDTO() {
		EmplazamientoDTO dto = new EmplazamientoDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setFechaBaja(getFechaBaja());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		if(getTipoEmplazamiento() != null){
			dto.setTipoEmplazamiento(getTipoEmplazamiento().crearDTO());
		}
		return dto;
	}

}
