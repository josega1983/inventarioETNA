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
import es.enaire.inventario.dtos.LocalizacionDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla LOCALIZACIONES.
 *
 */
@Entity
@Table(name="LOCALIZACIONES")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.LocalizacionDTO")
public class Localizacion implements Serializable, IBaseEntity<LocalizacionDTO> {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 3808951126778003130L;
	/**
	 * El id de Localizacion
	 */
	private Long id;
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
	 * Codigo AIP
	 */
	private String codigoAIP;
	/**
	 * Fecha de baja
	 */
	private Date fechaBaja;
	/**
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_LOCALIZACIONES", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_LOCALIZACIONES", sequenceName = "SEQ_LOCALIZACIONES", allocationSize = 1)
	@Column(name = "ID_LOCALIZACION")
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
	 * Obtiene el codigo AIP
	 * @return el codigo AIP
	 */ 
	@Basic
	@Column(name="INDICADOR_AIP", nullable=true)
	public String getCodigoAIP() {
		return codigoAIP;
	}
	/**
	 * Establece el codigo AIP
	 * @param codigoAIP EL CODIGO AIP
	 */
	public void setCodigoAIP(String codigoAIP) {
		this.codigoAIP = codigoAIP;
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
	public Localizacion(LocalizacionDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setCodigoAIP(dto.getCodigoAIP());
		this.setFechaBaja(dto.getFechaBaja());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public Localizacion() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public LocalizacionDTO crearDTO() {
		LocalizacionDTO dto = new LocalizacionDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setCodigoAIP(getCodigoAIP());
		dto.setFechaBaja(getFechaBaja());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		return dto;
	}
}
