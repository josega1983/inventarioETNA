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
import es.enaire.inventario.dtos.TipoInstalacionDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla TIPOS_INSTALACIONES.
 *
 */
@Entity
@Table(name="TIPOS_INSTALACIONES")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.TipoInstalacionDTO")
public class TipoInstalacion implements IBaseEntity<TipoInstalacionDTO>,
		Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 2368460557994056960L;
	/**
	 * El id 
	 */
	private Long id;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacion familiaInstalacion;
	/**
	 * Marca
	 */
	private String marca;
	/**
	 * Modelo
	 */
	private String modelo;
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
	@GeneratedValue(generator = "SEQ_TIPOS_INSTALACIONES", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_TIPOS_INSTALACIONES", sequenceName = "SEQ_TIPOS_INSTALACIONES", allocationSize = 1)
	@Column(name = "ID_TIPO_INSTALACION")
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
	 * @return  la familia de instalaciones
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
	 * Obtiene la marca
	 * @return la marca
	 */
	@Basic
	@Column(name="MARCA", nullable=false)
	public String getMarca() {
		return marca;
	}
	/**
	 * Establece la marca
	 * @param marca la marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * Obtiene el modelo
	 * @return el modelo
	 */
	@Basic
	@Column(name="MODELO", nullable=false)
	public String getModelo() {
		return modelo;
	}
	/**
	 * Establece el modelo
	 * @param modelo el modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
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
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public TipoInstalacion(TipoInstalacionDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		this.setFechaAlta(dto.getFechaAlta());
		this.setFechaBaja(dto.getFechaBaja());
		this.setMarca(dto.getMarca());
		this.setModelo(dto.getModelo());
		this.setObservaciones(dto.getObservaciones());
		if(dto.getFamiliaInstalacion() != null){
			this.setFamiliaInstalacion(new FamiliaInstalacion(dto.getFamiliaInstalacion()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public TipoInstalacion() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public TipoInstalacionDTO crearDTO() {
		TipoInstalacionDTO dto = new TipoInstalacionDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		dto.setFechaAlta(getFechaAlta());
		dto.setFechaBaja(getFechaBaja());
		dto.setMarca(getMarca());
		dto.setModelo(getModelo());
		dto.setObservaciones(getObservaciones());
		if(getFamiliaInstalacion() != null){
			dto.setFamiliaInstalacion(getFamiliaInstalacion().crearDTO());
		}
		return  dto;
	}




}
