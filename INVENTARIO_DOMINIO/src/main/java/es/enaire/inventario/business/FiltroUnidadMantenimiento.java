package es.enaire.inventario.business;

import java.io.Serializable;
/**
 * Clase de mapeo con la informacion del filtro de  unidad de mantenimiento.
 *
 */

public class FiltroUnidadMantenimiento extends BaseFiltro implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 1291891731959031904L;
	/**
	 * Nombre
	 */
	private String nombre;
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
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();

		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
