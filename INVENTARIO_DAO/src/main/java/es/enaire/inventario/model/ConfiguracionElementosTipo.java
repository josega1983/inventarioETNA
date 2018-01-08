package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import es.enaire.inventario.dtos.ConfiguracionElementosTipoDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla CONFIGURACIONES_ELEM_TIPOS.
 *
 */
@Entity
@Table(name="CONFIGURACIONES_ELEM_TIPOS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.ConfiguracionElementosTipoDTO")
public class ConfiguracionElementosTipo implements IBaseEntity<ConfiguracionElementosTipoDTO>, Serializable {

	/**
	 *  Indicador de serializacion
	 */
	private static final long serialVersionUID = -5048341768483446210L;
	/**
	 * El id de ConfiguracionElementosFamilia
	 */
	private Long id;
	/**
	 * Configuracion Tipo
	 */
	private ConfiguracionesTipo configuracionesTipo;
	/**
	 * Familia de elementos
	 */
	private FamiliaElemento familiaElemento;
	/**
	 * Configuracion Elementos Tipo Padre
	 */
	private ConfiguracionElementosTipo configuracionElementosTipoPadre;
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
	@GeneratedValue(generator = "SEQ_CONF_ELEM_TIPOS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CONF_ELEM_TIPOS", sequenceName = "SEQ_CONF_ELEM_TIPOS", allocationSize = 1)
	@Column(name = "ID_CONF_ELEM_TIPO")
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
	 * Obtiene las configuraciones Tipo
	 * @return las configuraciones Tipo
	 */
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="ID_CONF_TIPO", nullable=false)
	public ConfiguracionesTipo getConfiguracionesTipo() {
		return configuracionesTipo;
	}
	/**
	 * Establece las configuraciones Tipo
	 * @param configuracionesFamilia las configuraciones Tipo
	 */
	public void setConfiguracionesTipo(ConfiguracionesTipo configuracionesTipo) {
		this.configuracionesTipo = configuracionesTipo;
	}
	/**
	 * Obtiene la familia de elemento
	 * @return la familia de elemento
	 */
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="ID_FAMILIA_ELEMENTO", nullable=false)
	public FamiliaElemento getFamiliaElemento() {
		return familiaElemento;
	}
	/**
	 * Establece la familia de elemento
	 * @param familiaElemento la familia de elemento
	 */
	public void setFamiliaElemento(FamiliaElemento familiaElemento) {
		this.familiaElemento = familiaElemento;
	}
	/**
	 * Obtiene la Configuracion Elementos Tipo Padre
	 * @return la Configuracion Elementos Tipo Padre
	 */
	@ManyToOne(fetch =FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name ="ID_CONF_ELEM_TIPO_PADRE", nullable=true)
	public ConfiguracionElementosTipo getConfiguracionElementosTipoPadre() {
		return configuracionElementosTipoPadre;
	}
	/**
	 * Establece Configuracion Elementos Tipo Padre
	 * @param configuracionElementosFamiliaPadre Configuracion Elementos Tipo Padre
	 */
	public void setConfiguracionElementosTipoPadre(
			ConfiguracionElementosTipo configuracionElementosTipoPadre) {
		this.configuracionElementosTipoPadre = configuracionElementosTipoPadre;
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
	@Column(name="FECHA_ALTA", nullable=true)
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
	public ConfiguracionElementosTipo(ConfiguracionElementosTipoDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		if(dto.getFamiliaElemento() != null){
			this.setFamiliaElemento(new FamiliaElemento(dto.getFamiliaElemento()));
		}
		this.setFechaAlta(dto.getFechaAlta());
		this.setFechaBaja(dto.getFechaBaja());
		this.setObservaciones(dto.getObservaciones());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ConfiguracionElementosTipo() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la ConfiguracionElementosFamilia.
	 * @return El objeto que va a transportar la informacion de la ConfiguracionElementosFamilia.
	 */
	@Override
	public ConfiguracionElementosTipoDTO crearDTO() {
		ConfiguracionElementosTipoDTO dto = new ConfiguracionElementosTipoDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		if(getFamiliaElemento() != null){
			dto.setFamiliaElemento(getFamiliaElemento().crearDTO());
		}
		dto.setFechaAlta(getFechaAlta());
		dto.setFechaBaja(getFechaBaja());
		dto.setObservaciones(getObservaciones());
		return dto;
	}

}
