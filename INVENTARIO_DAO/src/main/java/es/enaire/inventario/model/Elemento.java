package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.dtos.ElementoDTO;
/**
 * Entidad que representa la informacion almacenada en la tabla CENTROS.
 *
 */
//@Entity
//@Table(name="CENTROS")
//@DTOTarget(claseImplementacionDTO="es.enaire.inventario.dtos.CentroDTO")
public class Elemento implements IBaseEntity<ElementoDTO>, Serializable {
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 5696133615480099625L;
	/**
	 * El id de elemento
	 */
	private Long id;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Descripcion
	 */
	private String descripcion;
	/**
	 * Familia Elemento
	 */
	private FamiliaElemento familiaElemento;
	/**
	 * Estado Elemento
	 */
	private EstadoElemento estadoElemento;
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
	 * Modificacion
	 */
	private String modificacion;
	/**
	 * Obtiene el id
	 * @return el id
	 */
//	@Id
//	@GeneratedValue(generator = "SEQ_CENTROS", strategy = GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "SEQ_CENTROS", sequenceName = "SEQ_CENTROS", allocationSize = 1)
//	@Column(name = "ID_Centro")
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
//	@Basic
//	@Column(name="NOMBRE", nullable=false)
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
	 * Obtiene la descripcion
	 * @return la descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Establece la descripcion
	 * @param descripcion la descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Obtiene la familia de elemento
	 * @return la familia de elemento
	 */
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
	 * Obtiene el estado de elemento
	 * @return el estado de elemento
	 */
	public EstadoElemento getEstadoElemento() {
		return estadoElemento;
	}
	/**
	 * Establece el estado de elemento
	 * @param estadoElemento el estado de elemento
	 */
	public void setEstadoElemento(EstadoElemento estadoElemento) {
		this.estadoElemento = estadoElemento;
	}
	/**
	 * Obtiene las observaciones
	 * @return las observaciones
	 */
//	@Basic
//	@Column(name="OBSERVACIONES", nullable=true)
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
//	@Basic
//	@Column(name="ACTIVO", nullable=true)
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
//	@Basic
//	@Column(name="FECHA_BAJA", nullable=true)
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
	 * Obtiene si es una modificacion
	 * @return si es una modificacion
	 */
	public String getModificacion() {
		return modificacion;
	}
	/**
	 * Establece si es una modificacion
	 * @param modificacion si es una modificacion
	 */
	public void setModificacion(String modificacion) {
		this.modificacion = modificacion;
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public Elemento(ElementoDTO dto){
		this.setId(dto.getId());
		this.setActivo(dto.getActivo());
		if(dto.getEstadoElemento() != null){
			this.setEstadoElemento(new EstadoElemento(dto.getEstadoElemento()));
		}
		if(dto.getFamiliaElemento() != null){
			this.setFamiliaElemento(new FamiliaElemento(dto.getFamiliaElemento()));
		}
		this.setFechaBaja(dto.getFechaBaja());
		this.setDescripcion(dto.getDescripcion());
		this.setModificacion(dto.getModificacion());
		this.setNombre(dto.getNombre());
		this.setObservaciones(dto.getObservaciones());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public Elemento() {
	}
	/**
	 * Crea el objeto que va a transportar la informacion de la Centro.
	 * @return El objeto que va a transportar la informacion de la Centro.
	 */
	@Override
	public ElementoDTO crearDTO() {
		ElementoDTO dto = new ElementoDTO();
		dto.setId(getId());
		dto.setActivo(getActivo());
		if(getEstadoElemento() != null){
			dto.setEstadoElemento(getEstadoElemento().crearDTO());
		}
		if(getFamiliaElemento() != null){
			dto.setFamiliaElemento(getFamiliaElemento().crearDTO());
		}
		dto.setFechaBaja(getFechaBaja());
		dto.setDescripcion(getDescripcion());
		dto.setModificacion(getModificacion());
		dto.setNombre(getNombre());
		dto.setObservaciones(getObservaciones());
		return dto;
	}

}
