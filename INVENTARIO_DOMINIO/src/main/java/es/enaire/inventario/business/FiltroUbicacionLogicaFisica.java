package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.UbicacionFisicaDTO;
import es.enaire.inventario.dtos.UbicacionLogicaDTO;

public class FiltroUbicacionLogicaFisica extends BaseFiltro implements
		Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 4799124828624410670L;
	/**
	 * Ubicacion logica
	 */
	private UbicacionLogicaDTO ubicacionLogica;
	/**
	 * Ubicacion fisica
	 */
	private UbicacionFisicaDTO ubicacionFisica;
	/**
	 * Obtiene la ubicacion Logica
	 * @return la ubicacion Logica
	 */
	public UbicacionLogicaDTO getUbicacionLogica() {
		return ubicacionLogica;
	}
	/**
	 * Establece la ubicacion Logica
	 * @param ubicacionLogica la ubicacion Logica
	 */
	public void setUbicacionLogica(UbicacionLogicaDTO ubicacionLogica) {
		this.ubicacionLogica = ubicacionLogica;
	}
	/**
	 * Obtiene la ubicacion Fisica
	 * @return la ubicacion Fisica
	 */
	public UbicacionFisicaDTO getUbicacionFisica() {
		return ubicacionFisica;
	}
	/**
	 * Establece la ubicacion Fisica
	 * @param ubicacionFisica la ubicacion Fisica
	 */
	public void setUbicacionFisica(UbicacionFisicaDTO ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = "";

		if(getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Id").concat(", ");
		}
		if(getUbicacionFisica() != null && getUbicacionFisica().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Ubicacion Física").concat(", ");
		}
		if(getUbicacionLogica() != null && getUbicacionLogica().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Ubicacion Lógica").concat(", ");
		}
		if(getObservaciones() != null && getObservaciones().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Observaciones").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
