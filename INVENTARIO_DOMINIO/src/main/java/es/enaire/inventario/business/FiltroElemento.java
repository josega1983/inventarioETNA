package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.EstadoElementoDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;

public class FiltroElemento extends BaseFiltro implements Serializable {


	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -7814225726228611113L;
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
	 * Modificacion
	 */
	private String modificacion;
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
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();

		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}
		if(getEstadoElemento() != null && getEstadoElemento().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Estado de Elemento").concat(", ");
		}
		if(getDescripcion() != null && getDescripcion().length()>0){
			listCamposFiltro = listCamposFiltro.concat("Descripción").concat(", ");
		}
		if(getFamiliaElemento() != null && getFamiliaElemento().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Familia Elemento").concat(", ");
		}
		if(getModificacion() != null && getModificacion().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Tiene modificaciones").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
