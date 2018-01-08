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
import es.enaire.inventario.dtos.UbicacionLogicaDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla UBICACIONES_LOGICAS.
 *
 */
@Entity
@Table(name="UBICACIONES_LOGICAS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.UbicacionLogicaDTO")
public class UbicacionLogica implements Serializable, IBaseEntity<UbicacionLogicaDTO> {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -9211132761321617794L;
	/**
	 * El id de Ubicacion Logica
	 */
	private Long id;
	/**
	 * El nombre de la Ubicacion Logica
	 */
	private String nombre;
	/**
	 * Region
	 */
	private Region region;
	/**
	 * Sector
	 */
	private Sector sector;
	/**
	 * Centro
	 */
	private Centro centro;
	/**
	 * Unidad de Mantenimiento
	 */
	private UnidadMantenimiento unidadMantenimiento;
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
	@GeneratedValue(generator = "SEQ_UBICACIONES_LOGICAS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_UBICACIONES_LOGICAS", sequenceName = "SEQ_UBICACIONES_LOGICAS", allocationSize = 1)
	@Column(name = "ID_UBICACION_LOGICA")
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
	 * Obtiene el nombre de la Ubicacion Logica
	 * @return nombre de Ubicacion Logica
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el nombre de la Ubicacion Logica
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
	 * Obtiene el sector
	 * @return el sector
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SECTOR", nullable = true)
	public Sector getSector() {
		return sector;
	}
	/**
	 * Establece el sector
	 * @param sector el sector
	 */
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	/**
	 * Obtiene el centro
	 * @return el centro
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CENTRO", nullable = true)
	public Centro getCentro() {
		return centro;
	}
	/**
	 * Establece el centro
	 * @param centro el centro
	 */
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	/**
	 * Obtiene la unidad de mantenimiento
	 * @return la unidad de mantenimiento
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UNIDAD_MANTENIMIENTO", nullable = true)
	public UnidadMantenimiento getUnidadMantenimiento() {
		return unidadMantenimiento;
	}
	/**
	 * Establece la unidad de mantenimiento
	 * @param unidadMantenimiento la unidad de mantenimiento
	 */
	public void setUnidadMantenimiento(UnidadMantenimiento unidadMantenimiento) {
		this.unidadMantenimiento = unidadMantenimiento;
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
	public UbicacionLogica(UbicacionLogicaDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setNombre(dto.getNombre());
		if(dto.getCentro() != null){
			this.setCentro(new Centro(dto.getCentro()));
		}
		this.setFechaBaja(dto.getFechaBaja());
		if(dto.getRegion() != null){
			this.setRegion(new Region(dto.getRegion()));
		}
		if(dto.getSector() !=null){
			this.setSector(new Sector(dto.getSector()));
		}
		this.setObservaciones(dto.getObservaciones());
		if(dto.getUnidadMantenimiento() != null){
			this.setUnidadMantenimiento(new UnidadMantenimiento(dto.getUnidadMantenimiento()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public UbicacionLogica() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public UbicacionLogicaDTO crearDTO() {
		UbicacionLogicaDTO dto = new UbicacionLogicaDTO();
		dto.setId(getId());
		dto.setNombre(getNombre());
		dto.setActivo(getActivo());
		if(getCentro() != null){
			dto.setCentro(getCentro().crearDTO());
		}
		dto.setFechaBaja(getFechaBaja());
		if(getRegion() != null){
			dto.setRegion(getRegion().crearDTO());
		}
		if(getSector() != null){
			dto.setSector(getSector().crearDTO());
		}
		dto.setObservaciones(getObservaciones());
		if(getUnidadMantenimiento() != null){
			dto.setUnidadMantenimiento(getUnidadMantenimiento().crearDTO());
		}
		return dto;
	}

}
