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
import es.enaire.inventario.annotations.IdTarget;
import es.enaire.inventario.dtos.TipoCampoParametroDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla TIPOS_CAMPOS_PARAMETROS.
 *
 */
@Entity
@Table(name="TIPOS_CAMPOS_PARAMETROS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.TipoCampoParametroDTO")
public class TipoCampoParametro implements IBaseEntity<TipoCampoParametroDTO>,
		Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 7390462328959109042L;
	/**
	 * El id de tipo de campo de parametros
	 */
	@IdTarget
	private Long id;
	/**
	 * Nombre
	 */
	private String tipoCampo;
	/**
	 * Orden
	 */
	private Integer orden;
	/**
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_TIPOS_CAMPOS_PARAMETROS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_TIPOS_CAMPOS_PARAMETROS", sequenceName = "SEQ_TIPOS_CAMPOS_PARAMETROS", allocationSize = 1)
	@Column(name = "ID_TIPO_CAMPO")
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
	@Column(name="TIPO_CAMPO", nullable=false)
	public String getTipoCampo() {
		return tipoCampo;
	}
	/**
	 * Establece el nombre
	 * @param nombre el nombre
	 */
	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}
	/**
	 * Obtiene el tipo
	 * @return el tipo
	 */
	@Basic
	@Column(name="ORDEN", nullable=false)
	public Integer getOrden() {
		return orden;
	}
	/**
	 * Establece el tipo
	 * @param tipo el tipo
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public TipoCampoParametro(TipoCampoParametroDTO dto) {
		this.setId(dto.getId());
		this.setTipoCampo(dto.getNombre());
		this.setOrden(dto.getTipo());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public TipoCampoParametro() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de TipoCampoParametro.
	 * @return El objeto que va a transportar la informacion de TipoCampoParametro.
	 */
	@Override
	public TipoCampoParametroDTO crearDTO() {
		TipoCampoParametroDTO dto = new TipoCampoParametroDTO();
		dto.setId(getId());
		dto.setNombre(getTipoCampo());
		dto.setTipo(getOrden());
		return dto;
	}
	@Override
	public void setActivo(String string) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setFechaBaja(Date date) {
		// TODO Auto-generated method stub

	}

}
