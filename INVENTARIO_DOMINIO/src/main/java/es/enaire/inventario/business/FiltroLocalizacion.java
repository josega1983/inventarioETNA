package es.enaire.inventario.business;

import java.io.Serializable;

public class FiltroLocalizacion extends BaseFiltro implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -2980428128289314934L;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Codigo AIP
	 */
	private String codigoAIP;
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
	 * Obtiene el codigo AIP
	 * @return el codigo AIP
	 */
	public String getCodigoAIP() {
		return codigoAIP;
	}
	/**
	 * Establece el codigo AIP
	 * @param codigoAIP EL CODIGO AIP
	 */
	public void setCodigoAIP(String codigoAIP) {
		this.codigoAIP = codigoAIP;
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
		if(getCodigoAIP() != null && getCodigoAIP().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Código AIP").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);

	}
}
