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
import es.enaire.inventario.dtos.ConfiguracionElementosFamiliaDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla CONFIGURACIONES_ELEM_FAMILIAS.
 *
 */
@Entity
@Table(name="CONFIGURACIONES_ELEM_FAMILIAS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.ConfiguracionElementosFamiliaDTO")
public class ConfiguracionElementosFamilia implements IBaseEntity<ConfiguracionElementosFamiliaDTO>, Serializable {
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -3173784547240312432L;
	/**
	 * El id de ConfiguracionElementosFamilia
	 */
	private Long id;
	/**
	 * Configuracion Familia
	 */
	private ConfiguracionesFamilia configuracionesFamilia;
	/**
	 * Familia de elementos
	 */
	private FamiliaElemento familiaElemento;
	/**
	 * Configuracion Elementos Familia Padre
	 */
	private ConfiguracionElementosFamilia configuracionElementosFamiliaPadre;
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
	@GeneratedValue(generator = "SEQ_CONF_ELEM_FAMILIAS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CONF_ELEM_FAMILIAS", sequenceName = "SEQ_CONF_ELEM_FAMILIAS", allocationSize = 1)
	@Column(name = "ID_CONF_ELEM_FAMILIA")
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
	 * Obtiene las configuraciones familia
	 * @return las configuraciones familia
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CONF_FAMILIA", nullable =false)
	public ConfiguracionesFamilia getConfiguracionesFamilia() {
		return configuracionesFamilia;
	}
	/**
	 * Establece las configuraciones familia
	 * @param configuracionesFamilia las configuraciones familia
	 */
	public void setConfiguracionesFamilia(ConfiguracionesFamilia configuracionesFamilia) {
		this.configuracionesFamilia = configuracionesFamilia;
	}
	/**
	 * Obtiene la familia de elemento
	 * @return la familia de elemento
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_FAMILIA_ELEMENTO", nullable =false)
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
	 * Obtiene la Configuracion Elementos Familia Padre
	 * @return la Configuracion Elementos Familia Padre
	 */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name="ID_CONF_ELEM_FAMILIA_PADRE", nullable =true)
	public ConfiguracionElementosFamilia getConfiguracionElementosFamiliaPadre() {
		return configuracionElementosFamiliaPadre;
	}
	/**
	 * Establece Configuracion Elementos Familia Padre
	 * @param configuracionElementosFamiliaPadre Configuracion Elementos Familia Padre
	 */
	public void setConfiguracionElementosFamiliaPadre(
			ConfiguracionElementosFamilia configuracionElementosFamiliaPadre) {
		this.configuracionElementosFamiliaPadre = configuracionElementosFamiliaPadre;
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
	public ConfiguracionElementosFamilia(ConfiguracionElementosFamiliaDTO dto){
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
	public ConfiguracionElementosFamilia() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la ConfiguracionElementosFamilia.
	 * @return El objeto que va a transportar la informacion de la ConfiguracionElementosFamilia.
	 */
	@Override
	public ConfiguracionElementosFamiliaDTO crearDTO() {
		ConfiguracionElementosFamiliaDTO dto = new ConfiguracionElementosFamiliaDTO();
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
