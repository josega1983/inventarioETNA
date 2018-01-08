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
import es.enaire.inventario.dtos.UbicacionLogicaFisicaDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla ASOCIA_UBI_LOGICAS_FISICAS.
 *
 */
@Entity
@Table(name="ASOCIA_UBI_LOGICAS_FISICAS")
@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.UbicacionLogicaFisicaDTO")
public class UbicacionLogicaFisica implements IBaseEntity<UbicacionLogicaFisicaDTO>, Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -5055333076874935511L;
	/**
	 * El id
	 */
	private Long id;
	/**
	 * Ubicacion logica
	 */
	private UbicacionLogica ubicacionLogica;
	/**
	 * Ubicacion fisica
	 */
	private UbicacionFisica ubicacionFisica;
	/**
	 * Observaciones 
	 */
	private String observaciones;
	/**
	 * Obtiene el id
	 * @return el id
	 */
	@Id
	@GeneratedValue(generator = "SEQ_ASOCIA_UBI_LOGICAS_FISICAS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_ASOCIA_UBI_LOGICAS_FISICAS", sequenceName = "SEQ_ASOCIA_UBI_LOGICAS_FISICAS", allocationSize = 1)
	@Column(name = "ID_ASOCIA_UBI_LOGICA_FISICA")
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
	 * Obtiene la ubicacion Logica
	 * @return la ubicacion Logica
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UBICACION_LOGICA", nullable = false)
	public UbicacionLogica getUbicacionLogica() {
		return ubicacionLogica;
	}
	/**
	 * Establece la ubicacion Logica
	 * @param ubicacionLogica la ubicacion Logica
	 */
	public void setUbicacionLogica(UbicacionLogica ubicacionLogica) {
		this.ubicacionLogica = ubicacionLogica;
	}
	/**
	 * Obtiene la ubicacion Fisica
	 * @return la ubicacion Fisica
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UBICACION_FISICA", nullable = false)
	public UbicacionFisica getUbicacionFisica() {
		return ubicacionFisica;
	}
	/**
	 * Establece la ubicacion Fisica
	 * @param ubicacionFisica la ubicacion Fisica
	 */
	public void setUbicacionFisica(UbicacionFisica ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}
	/**
	 * Obtiene las observaciones
	 * @return las observaciones
	 */
	@Basic
	@Column(name="OBSERVACIONES")
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
	 * Crea un objeto de ubicacion logica fisica a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public UbicacionLogicaFisica(UbicacionLogicaFisicaDTO dto) {
		this.setId(dto.getId());
		this.setObservaciones(dto.getObservaciones());
		if(dto.getUbicacionFisica() != null){
			this.setUbicacionFisica(new UbicacionFisica(dto.getUbicacionFisica()));
		}
		if(dto.getUbicacionLogica() != null){
			this.setUbicacionLogica(new UbicacionLogica(dto.getUbicacionLogica()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public UbicacionLogicaFisica() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la region.
	 * @return El objeto que va a transportar la informacion de la region.
	 */
	@Override
	public UbicacionLogicaFisicaDTO crearDTO() {
		UbicacionLogicaFisicaDTO dto = new UbicacionLogicaFisicaDTO();
		dto.setId(getId());
		dto.setObservaciones(getObservaciones());
		if(getUbicacionFisica() != null){
			dto.setUbicacionFisica(getUbicacionFisica().crearDTO());
		}
		if(getUbicacionLogica() != null){
			dto.setUbicacionLogica(getUbicacionLogica().crearDTO());
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
