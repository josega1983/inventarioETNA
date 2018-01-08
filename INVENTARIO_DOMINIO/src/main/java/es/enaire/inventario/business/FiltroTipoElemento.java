package es.enaire.inventario.business;

import java.io.Serializable;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroTipoElemento extends BaseFiltro implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 7964157394189901771L;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Marca
	 */
	private String marca;
	/**
	 * Modelo
	 */
	private String modelo;
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
	 * Obtiene la marca
	 * @return la marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * Establece la marca
	 * @param marca la marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * Obtiene el modelo
	 * @return el modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Establece el modelo
	 * @param modelo el modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();
		if(getMarca() != null && getMarca().length() >0) {
			listCamposFiltro = listCamposFiltro.concat("Marca").concat(", ");
		}
		if(getModelo() != null && getModelo().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Modelo").concat(", ");
		}
		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
