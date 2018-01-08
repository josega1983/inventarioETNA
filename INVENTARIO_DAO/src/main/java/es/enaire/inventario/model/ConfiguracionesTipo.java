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
import es.enaire.inventario.dtos.ConfiguracionesTipoDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla CONFIGURACIONES_TIPOS.
 *
 */
@Entity
@Table(name="CONFIGURACIONES_TIPOS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.ConfiguracionesTipoDTO")
public class ConfiguracionesTipo implements
		IBaseEntity<ConfiguracionesTipoDTO>, Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 3925314487899283115L;
	/**
	 * El id de configuraciones tipo
	 */
	private Long id;
	/**
	 * Tipo Instalaciones
	 */
	private TipoInstalacion tipoInstalacion;
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
	 * Fecha de Alta
	 */
	private Date fechaAlta;

	/**
	 * El contenido de la imagen que representa la estructura de la configuracion.
	 */
	private byte[] imagenArbol;


	/**
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_CONF_TIPOS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CONF_TIPOS", sequenceName = "SEQ_CONF_TIPOS", allocationSize = 1)
	@Column(name = "ID_CONF_TIPO")
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
	 * Obtiene el tipo de instalaciones
	 * @return el tipo de instalaciones
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_INSTALACION", nullable = false)
	public TipoInstalacion getTipoInstalacion() {
		return tipoInstalacion;
	}
	/**
	 * Establece el tipo de instalaciones
	 * @param familiaInstalacion el tipo de instalaciones
	 */
	public void setTipoInstalacion(TipoInstalacion tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
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
	public ConfiguracionesTipo(ConfiguracionesTipoDTO dto) {
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		if(dto.getTipoInstalacion() != null){
			this.setTipoInstalacion(new TipoInstalacion(dto.getTipoInstalacion()));
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
	public ConfiguracionesTipo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la Centro.
	 * @return El objeto que va a transportar la informacion de la Centro.
	 */
	@Override
	public ConfiguracionesTipoDTO crearDTO() {
		ConfiguracionesTipoDTO dto = new ConfiguracionesTipoDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		if(getTipoInstalacion() != null){
			dto.setTipoInstalacion(getTipoInstalacion().crearDTO());
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
