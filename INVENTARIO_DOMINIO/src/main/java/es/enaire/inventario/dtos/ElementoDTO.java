package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ElementoDTO  implements Serializable {
	/**
	 *  Indicador de serializacion
	 */
	private static final long serialVersionUID = 8173852442150667472L;
	/**
	 * El id de elementos
	 */
	@IdTarget
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
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Estado de elemento
	 */
	private EstadoElementoDTO estadoElemento;
	/**
	 * Valores parametros funcionales
	 */
	private List<ValoresParametroFuncionalDTO> valoresParametroFuncionalList;
	/**
	 * Usuario
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
//	/**
//	 * Listado de Modificaciones
//	 */
//	private List<ModificacionElementoDTO> modificacionesList;
	/**
	 * Opciones a mostrar en el estado del menu
	 */
	private String estadoMenu;
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
	 * Obtiene el estado de elemento
	 * @return el estado de elemento
	 */
	public EstadoElementoDTO getEstadoElemento() {
		return estadoElemento;
	}
	/**
	 * Establece el estado de elemento
	 * @param estadoElemento el estado de elemento
	 */
	public void setEstadoElemento(EstadoElementoDTO estadoElemento) {
		this.estadoElemento = estadoElemento;
	}
	/**
	 * Obtiene la lista de valores de parametros funcionales
	 * @return la lista de valores de parametros funcionales
	 */
	public List<ValoresParametroFuncionalDTO> getValoresParametroFuncionalList() {
		return valoresParametroFuncionalList;
	}
	/**
	 * Establece la lista de valores de parametros funcionales
	 * @param valoresParametroFuncionalList la lista de valores de parametros funcionales
	 */
	public void setValoresParametroFuncionalList(
			List<ValoresParametroFuncionalDTO> valoresParametroFuncionalList) {
		this.valoresParametroFuncionalList = valoresParametroFuncionalList;
	}
	/**
	 * Obtiene el usuario
	 * @return el usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	/**
	 * Establece el usuario
	 * @param usuario el usuario
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
//	/**
//	 * Obtiene el listado de modificaciones
//	 * @return el listado de modificaciones
//	 */
//	public List<ModificacionElementoDTO> getModificacionesList() {
//		return modificacionesList;
//	}
//	/**
//	 * Establece el listado de modificaciones
//	 * @param modificacionesList el listado de modificaciones
//	 */
//	public void setModificacionesList(List<ModificacionElementoDTO> modificacionesList) {
//		this.modificacionesList = modificacionesList;
//	}
	/**
	 * Obtiene el estado del menu
	 * @return El estado del menu
	 */
	public String getEstadoMenu() {
		return estadoMenu;
	}
	/**
	 * Establece el estado del menu
	 * @param estadoMenu El estado de menu a establecer
	 */
	public void setEstadoMenu(String estadoMenu) {
		this.estadoMenu = estadoMenu;
	}

}
