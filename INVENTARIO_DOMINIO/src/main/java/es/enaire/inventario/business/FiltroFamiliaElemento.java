package es.enaire.inventario.business;

import java.io.Serializable;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroFamiliaElemento extends BaseFiltro implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 7789584243586432838L;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Descripcion
	 */
	private String descripcion;
	/**
	 * Flag que indica ordenar alfabeticamente 
	 */
	private boolean ordenarAlfabeticamenteNombre = false;
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
	 * Obtiene el flag para la ordenacion alfabetica
	 * @return boolean ordenarAlfabeticamenteNombre
	 */
	public boolean isOrdenarAlfabeticamenteNombre() {
		return ordenarAlfabeticamenteNombre;
	}
	/**
	 * Establece el flag para la ordenacion alfabetica
	 * @param boolean ordenarAlfabeticamenteNombre
	 */
	public void setOrdenarAlfabeticamenteNombre(boolean ordenarAlfabeticamenteNombre) {
		this.ordenarAlfabeticamenteNombre = ordenarAlfabeticamenteNombre;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro =  super.generateStringCamposFiltro();

		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}
		if(getDescripcion() != null && getDescripcion().length()>0){
			listCamposFiltro = listCamposFiltro.concat("Descripción").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
