package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Base64;
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
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla CONFIGURACIONES_FAMILIAS.
 *
 */
@Entity
@Table(name="CONFIGURACIONES_FAMILIAS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO")
public class ConfiguracionesFamilia implements
		IBaseEntity<ConfiguracionesFamiliaDTO>, Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -1176127646517192422L;
	/**
	 * El id de onfiguraciones de famlia
	 */
	private Long id;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacion familiaInstalacion;
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
	 * El contenido de la imagen que representa la estructura de la configuracion.
	 */
	private byte[] imagenArbol;

	/**
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_CONF_FAMILIAS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CONF_FAMILIAS", sequenceName = "SEQ_CONF_FAMILIAS", allocationSize = 1)
	@Column(name = "ID_CONF_FAMILIA")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FAMILIA_INSTALACION", nullable = false)
	public FamiliaInstalacion getFamiliaInstalacion() {
		return familiaInstalacion;
	}
	/**
	 * Establece la familia de instalaciones
	 * @param familiaInstalacion la familia de instalaciones
	 */
	public void setFamiliaInstalacion(FamiliaInstalacion familiaInstalacion) {
		this.familiaInstalacion = familiaInstalacion;
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
	 * Obtiene el contenido de la imagen que representa la estructura de la configuracion.
	 * @return El contenido de la imagen que representa la estructura de la configuracion.
	 */
	@Basic
	@Column(name="IMAGEN_ARBOL", nullable = true)
	public byte[] getImagenArbol() {
		return imagenArbol;
	}

	/**
     * Establece el contenido de la imagen que representa la estructura de la configuracion.
     * @param imagenArbol El contenido de la imagen que representa la estructura de la configuracion.
     */
	public void setImagenArbol(byte[] imagenArbol) {
		this.imagenArbol = imagenArbol;
	}

	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ConfiguracionesFamilia(ConfiguracionesFamiliaDTO dto) {
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		if(dto.getFamiliaInstalacion() != null){
			this.setFamiliaInstalacion(new FamiliaInstalacion(dto.getFamiliaInstalacion()));
		}
		this.setFechaAlta(dto.getFechaAlta());
		this.setFechaBaja(dto.getFechaBaja());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
		try {
			this.setImagenArbol(new String(Base64.getDecoder().decode(dto.getImagenArbol()), "utf-8").getBytes());
		} catch (Exception e) {
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ConfiguracionesFamilia() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la Centro.
	 * @return El objeto que va a transportar la informacion de la Centro.
	 */
	@Override
	public ConfiguracionesFamiliaDTO crearDTO() {
		ConfiguracionesFamiliaDTO dto = new ConfiguracionesFamiliaDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		if(getFamiliaInstalacion() != null){
			dto.setFamiliaInstalacion(getFamiliaInstalacion().crearDTO());
		}
		dto.setFechaAlta(getFechaAlta());
		dto.setFechaBaja(getFechaBaja());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		if(getImagenArbol() != null) {
			try {
				dto.setImagenArbol(new String(Base64.getEncoder().encode(getImagenArbol())));
			} catch (Exception e) {
			}
		}
		return dto;
	}
}
