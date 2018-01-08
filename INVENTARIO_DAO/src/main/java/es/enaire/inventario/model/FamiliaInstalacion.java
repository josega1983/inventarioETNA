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
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla FAMILIAS_INSTALACIONES.
 *
 */
@Entity
@Table(name="FAMILIAS_INSTALACIONES")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.FamiliaInstalacionDTO")
public class FamiliaInstalacion implements Serializable,IBaseEntity<FamiliaInstalacionDTO> {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 3615567114257273750L;
	/**
	 * El id 
	 */
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Area
	 */
	private String area;
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
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_FAMILIAS_INSTALACIONES", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_FAMILIAS_INSTALACIONES", sequenceName = "SEQ_FAMILIAS_INSTALACIONES", allocationSize = 1)
	@Column(name = "ID_FAMILIA_INSTALACION")
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
	 * Obtiene el area
	 * @return el area
	 */
	@Basic
	@Column(name="AREA", nullable=false)
	public String getArea() {
		return area;
	}
	/**
	 * Establece el area
	 * @param area el area
	 */
	public void setArea(String area) {
		this.area = area;
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
	public FamiliaInstalacion(FamiliaInstalacionDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setArea(dto.getArea());
		this.setFechaAlta(dto.getFechaAlta());
		this.setFechaBaja(dto.getFechaBaja());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public FamiliaInstalacion() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public FamiliaInstalacionDTO crearDTO() {
		FamiliaInstalacionDTO dto = new FamiliaInstalacionDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setArea(getArea());
		dto.setFechaAlta(getFechaAlta());
		dto.setFechaBaja(getFechaBaja());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		return dto;
	}


}
