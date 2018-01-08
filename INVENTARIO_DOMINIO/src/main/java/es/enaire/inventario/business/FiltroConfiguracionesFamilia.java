package es.enaire.inventario.business;

import java.io.Serializable;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;

public class FiltroConfiguracionesFamilia extends BaseFiltro implements Serializable {
	/**
	 * Identificador univoco de la tabla.
	 */
	private static final long serialVersionUID = -6298454905017720786L;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionDTO familiaInstalacion;
	/**
	 * Nombre
	 */
	private String nombre;

	/**
	 * Obtiene la familia de instalaciones
	 * @return la familia de instalaciones
	 */
	public FamiliaInstalacionDTO getFamiliaInstalacion() {
		return familiaInstalacion;
	}
	/**
	 * Establece la familia de instalaciones
	 * @param familiaInstalacion la familia de instalaciones
	 */
	public void setFamiliaInstalacion(FamiliaInstalacionDTO familiaInstalacion) {
		this.familiaInstalacion = familiaInstalacion;
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

		if (getFamiliaInstalacion() != null && getFamiliaInstalacion().getId() !=null) {
			listCamposFiltro = listCamposFiltro.concat("Familia de Instalación").concat(", ");
		}

		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
