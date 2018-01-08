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
import es.enaire.inventario.dtos.UbicacionFisicaDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla UBICACIONES_FISICAS.
 *
 */
@Entity
@Table(name="UBICACIONES_FISICAS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.UbicacionFisicaDTO")
public class UbicacionFisica implements Serializable, IBaseEntity<UbicacionFisicaDTO> {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 4609461150339422531L;
	/**
	 * El id de 
	 */
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Region
	 */
	private Region region;
	/**
	 * Localizacion
	 */
	private Localizacion localizacion;
	/**
	 * Emplazamiento
	 */
	private Emplazamiento emplazamiento;
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
	@GeneratedValue(generator = "SEQ_UBICACIONES_FISICAS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_UBICACIONES_FISICAS", sequenceName = "SEQ_UBICACIONES_FISICAS", allocationSize = 1)
	@Column(name = "ID_UBICACION_FISICA")
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
	 * Obtiene el nombre de la Ubicacion Fisica
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el nombre de la Ubicacion Fisica
	 * @param String nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene la region
	 * @return la region
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REGION", nullable = true)
	public Region getRegion() {
		return region;
	}
	/**
	 * Establece la region
	 * @param region
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	/**
	 * Obtiene la localizacion
	 * @return la localizacion
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCALIZACION", nullable = true)
	public Localizacion getLocalizacion() {
		return localizacion;
	}
	/**
	 * Establece la localizacion
	 * @param localizacion la localizacion
	 */
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	/**
	 * Obtiene el emplazamiento
	 * @return el emplazamiento
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLAZAMIENTO", nullable = true)
	public Emplazamiento getEmplazamiento() {
		return emplazamiento;
	}
	/**
	 * Establece el emplazamiento
	 * @param emplazamiento el emplazamiento
	 */
	public void setEmplazamiento(Emplazamiento emplazamiento) {
		this.emplazamiento = emplazamiento;
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
	public UbicacionFisica(UbicacionFisicaDTO dto){
		this.setId(dto.getId());
		this.setNombre(dto.getNombre());
		this.setActivo(dto.getActivo());
		if (dto.getEmplazamiento() != null) {
			this.setEmplazamiento(new Emplazamiento(dto.getEmplazamiento()));	
		}
		this.setFechaBaja(dto.getFechaBaja());
		if(dto.getLocalizacion() != null){
			this.setLocalizacion(new Localizacion(dto.getLocalizacion()));
		}
		if(dto.getRegion() != null){
			this.setRegion(new Region(dto.getRegion()));
		}
		this.setObservaciones(dto.getObservaciones());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public UbicacionFisica() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public UbicacionFisicaDTO crearDTO() {
		UbicacionFisicaDTO dto = new UbicacionFisicaDTO();
		dto.setId(getId());
		dto.setNombre(getNombre());
		dto.setActivo(getActivo());
		if(getEmplazamiento() != null){
			dto.setEmplazamiento(getEmplazamiento().crearDTO());
		}
		dto.setFechaBaja(getFechaBaja());
		if(getLocalizacion() !=null){
			dto.setLocalizacion(getLocalizacion().crearDTO());
		}
		if(getRegion() != null){
			dto.setRegion(getRegion().crearDTO());
		}
		dto.setObservaciones(getObservaciones());
		return dto;
	}

}
