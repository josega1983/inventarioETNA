package es.enaire.inventario.business;

import java.io.Serializable;

public class FiltroTipoCampoParametro extends BaseFiltro implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -5083607679556965544L;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Tipo
	 */
	private Integer tipo;
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
	 * Obtiene el tipo
	 * @return el tipo
	 */
	public Integer getTipo() {
		return tipo;
	}
	/**
	 * Establece el tipo
	 * @param tipo el tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
		if(getTipo() != null){
			listCamposFiltro = listCamposFiltro.concat("Tipo").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
