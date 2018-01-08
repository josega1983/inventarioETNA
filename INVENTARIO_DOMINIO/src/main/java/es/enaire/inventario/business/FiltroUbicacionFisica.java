package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.EmplazamientoDTO;
import es.enaire.inventario.dtos.LocalizacionDTO;
import es.enaire.inventario.dtos.RegionDTO;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroUbicacionFisica extends BaseFiltro implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 8278914356197921772L;
	/**
	 * Region
	 */
	private RegionDTO region;
	/**
	 * Localizacion
	 */
	private LocalizacionDTO localizacion;
	/**
	 * Emplazamiento
	 */
	private EmplazamientoDTO emplazamiento;
	/**
	 * Obtiene la region
	 * @return la region
	 */
	public RegionDTO getRegion() {
		return region;
	}
	/**
	 * Establece la region
	 * @param region
	 */
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	/**
	 * Obtiene la localizacion
	 * @return la localizacion
	 */
	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}
	/**
	 * Establece la localizacion
	 * @param localizacion la localizacion
	 */
	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}
	/**
	 * Obtiene el emplazamiento
	 * @return el emplazamiento
	 */
	public EmplazamientoDTO getEmplazamiento() {
		return emplazamiento;
	}
	/**
	 * Establece el emplazamiento
	 * @param emplazamiento el emplazamiento
	 */
	public void setEmplazamiento(EmplazamientoDTO emplazamiento) {
		this.emplazamiento = emplazamiento;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();
		if(getRegion() !=null && (getRegion().getId() != null || getRegion().getNombre() != null)){
			listCamposFiltro = listCamposFiltro.concat("Region").concat(", ");
		}
		if(getEmplazamiento() != null && (getEmplazamiento().getId() !=null  || getEmplazamiento().getNombre() != null)){
			listCamposFiltro = listCamposFiltro.concat("Emplazamiento").concat(", ");
		}
		if(getLocalizacion() != null && (getLocalizacion().getId() !=null  || getLocalizacion().getNombre() != null)){
			listCamposFiltro = listCamposFiltro.concat("Localizacion").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
