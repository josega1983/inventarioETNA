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
import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla CAMPOS_PARAMETROS_FUNCIONALES.
 *
 */
@Entity
@Table(name="CAMPOS_PARAMETROS_FUNCIONALES")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO")
public class ConfiguracionParametroFuncional implements Serializable,IBaseEntity<ConfiguracionParametroFuncionalDTO> {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -2110007138828326506L;
	/**
	 * Id 
	 */
	private Long id;
	/**
	 * Parametro Funcional
	 */
	private ParametroFuncional parametroFuncional;
	/**
	 * Nombre de campo
	 */
	private String nombreCampo;
	/**
	 * Tipo de Campo
	 */
	private TipoCampoParametro tipoCampoParametro;
	/**
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_CAMPOS_PARAM_FUNCIONALES", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CAMPOS_PARAM_FUNCIONALES", sequenceName = "SEQ_CAMPOS_PARAM_FUNCIONALES", allocationSize = 1)
	@Column(name = "ID_CAMPO_PARAMETRO_FUNCIONAL")
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
	 * Obtiene el parametro funcional
	 * @return el parametro funcional
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARAMETRO_FUNCIONAL", nullable = false)
	public ParametroFuncional getParametroFuncional() {
		return parametroFuncional;
	}
	/**
	 * Establece el parametro funcional
	 * @param parametroFuncional el parametro funcional
	 */
	public void setParametroFuncional(ParametroFuncional parametroFuncional) {
		this.parametroFuncional = parametroFuncional;
	}
	/**
	 * Obtiene el nombre del campo
	 * @return el nombre del campo
	 */
	@Basic
	@Column(name="NOMBRE_CAMPO", nullable=false)
	public String getNombreCampo() {
		return nombreCampo;
	}
	/**
	 * Establece el nombre del campo
	 * @param nombreCampo el nombre del campo
	 */
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	/**
	 * Obtiene el tipo de Campo
	 * @return el tipo de Campo
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CAMPO", nullable = false)
	public TipoCampoParametro getTipoCampoParametro() {
		return tipoCampoParametro;
	}
	/**
	 * Establece el tipo de Campo
	 * @param tipoCampo el tipo de Campo
	 */
	public void setTipoCampoParametro(TipoCampoParametro tipoCampoParametro) {
		this.tipoCampoParametro = tipoCampoParametro;
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ConfiguracionParametroFuncional(ConfiguracionParametroFuncionalDTO dto) {
		this.setId(dto.getId());
		this.setNombreCampo(dto.getNombreCampo());
		if(dto.getParametroFuncional() !=null){
			this.setParametroFuncional(new ParametroFuncional(dto.getParametroFuncional()));
		}
		if(dto.getTipoCampoParametro() != null){
			this.setTipoCampoParametro(new TipoCampoParametro(dto.getTipoCampoParametro()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ConfiguracionParametroFuncional() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la Centro.
	 * @return El objeto que va a transportar la informacion de la Centro.
	 */
	@Override
	public ConfiguracionParametroFuncionalDTO crearDTO() {
		ConfiguracionParametroFuncionalDTO dto = new ConfiguracionParametroFuncionalDTO();
		dto.setId(getId());
		dto.setNombreCampo(getNombreCampo());
		if(getParametroFuncional() != null){
			dto.setParametroFuncional(getParametroFuncional().crearDTO());
		}
		if(getTipoCampoParametro() != null){
			dto.setTipoCampoParametro(getTipoCampoParametro().crearDTO());;
		}
		return dto;
	}

	@Override
	public void setActivo(String string) {
	}

	@Override
	public void setFechaBaja(Date date) {

	}

}
