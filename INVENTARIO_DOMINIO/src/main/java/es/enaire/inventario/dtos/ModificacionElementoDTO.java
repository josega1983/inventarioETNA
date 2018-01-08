package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.annotations.IdTarget;

public class ModificacionElementoDTO implements Serializable {

	/**
	 *  Indicador de serializacion
	 */
	private static final long serialVersionUID = 8255206887252855391L;
	/**
	 * El id de modificacion de elementos
	 */
	@IdTarget
	private Long id;
	/**
	 * Elemento
	 */
	private ElementoDTO elemento;
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
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Usuario quie realiza la modificacion
	 */
	private UsuarioDTO usuario;
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
	 * Obtiene el elemento
	 * @return el elemento
	 */
	public ElementoDTO getElemento() {
		return elemento;
	}
	/**
	 * Establece el elemento
	 * @param elemento el elemento
	 */
	public void setElemento(ElementoDTO elemento) {
		this.elemento = elemento;
	}
	/**
	 * Obtiene el nombre
	 * @return el nombre
	 */
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
	public FamiliaElementoDTO getFamiliaElemento() {
		return familiaElemento;
	}
	/**
	 * Establece la familia de elemento
	 * @param familiaElemento la familia de elemento
	 */
	public void setFamiliaElemento(FamiliaElementoDTO familiaElemento) {
		this.familiaElemento = familiaElemento;
	}
	/**
	 * Obtiene el usuario que realiza la modificacion
	 * @return el usuario que realiza la modificacion
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	/**
	 * Establece el usuario que realiza la modificacion
	 * @param usuario el usuario que realiza la modificacion
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	/**
	 * Obtiene las observaciones
	 * @return las observaciones
	 */
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
	 * Metodo que nos crea una modificacion de elemento a partir de un elemento dado
	 * @param dto del elemento dado
	 * @return la modificacion elemento
	 */
	public static ModificacionElementoDTO createModificacionElementoFromElemento(ElementoDTO dto){
		ModificacionElementoDTO modificacionElementoDTO = new ModificacionElementoDTO();
		modificacionElementoDTO.setElemento(dto);
		modificacionElementoDTO.setFamiliaElemento(dto.getFamiliaElemento());
		modificacionElementoDTO.setNombre(dto.getNombre());
		modificacionElementoDTO.setObservaciones(dto.getObservaciones());
		modificacionElementoDTO.setUsuario(dto.getUsuario());
		return modificacionElementoDTO;
	}
}
