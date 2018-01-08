package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.TipoEmplazamientoDTO;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroEmplazamiento extends BaseFiltro implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -8205079387759177124L;
	/**
	 * Tipo emplazamiento
	 */
	private TipoEmplazamientoDTO tipoEmplazamiento;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Obtiene el tipo de emplazamiento
	 * @return el tipo de emplazamiento
	 */
	public TipoEmplazamientoDTO getTipoEmplazamiento() {
		return tipoEmplazamiento;
	}
	/**
	 * Establece el tipo de emplazamiento
	 * @param tipoEmplazamiento el tipo de emplazamiento
	 */
	public void setTipoEmplazamiento(TipoEmplazamientoDTO tipoEmplazamiento) {
		this.tipoEmplazamiento = tipoEmplazamiento;
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
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();

		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}
		if(getTipoEmplazamiento() != null && getTipoEmplazamiento().getId() !=null){
			listCamposFiltro = listCamposFiltro.concat("Tipo Emplazamiento").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
