package es.enaire.inventario.business;

import java.io.Serializable;

public class FiltroParametroFuncional extends BaseFiltro implements Serializable {
	/**
	 * Identificador univoco de la tabla.
	 */
	private static final long serialVersionUID = 4775914558636761802L;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Descripcion
	 */
	private String descripcion;
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
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();
		if (getDescripcion() != null && getDescripcion().length()>0) {
			listCamposFiltro = listCamposFiltro.concat("Descripción").concat(", ");
		}
		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
