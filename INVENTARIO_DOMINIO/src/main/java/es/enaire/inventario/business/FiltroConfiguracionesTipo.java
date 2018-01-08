package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
import es.enaire.inventario.dtos.TipoInstalacionDTO;

public class FiltroConfiguracionesTipo extends BaseFiltro implements Serializable {
	/**
	 * Identificador univoco de la tabla.
	 */
	private static final long serialVersionUID = -5041230849397647436L;
	/**
	 * Tipo Instalaciones
	 */
	private TipoInstalacionDTO tipoInstalacion;

	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionDTO familiaInstalacion;

	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Obtiene la tipo de instalaciones
	 * @return la tipo de instalaciones
	 */
	public TipoInstalacionDTO getTipoInstalacion() {
		return tipoInstalacion;
	}
	/**
	 * Establece la tipo de instalaciones
	 * @param tipoInstalacion la tipo de instalaciones
	 */
	public void setTipoInstalacion(TipoInstalacionDTO tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
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
		String listCamposFiltro = inicializarCamposComunes();

		if (getTipoInstalacion() != null && getTipoInstalacion().getId() !=null) {
			listCamposFiltro = listCamposFiltro.concat("Tipo de Instalación").concat(", ");
		}
		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}
		if (getFamiliaInstalacion() != null && getFamiliaInstalacion().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Familia de Instalación").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
	public FamiliaInstalacionDTO getFamiliaInstalacion() {
		return familiaInstalacion;
	}
	public void setFamiliaInstalacion(FamiliaInstalacionDTO familiaInstalacion) {
		this.familiaInstalacion = familiaInstalacion;
	}
}
