package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroTipoInstalacion extends BaseFiltro implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 183723715710084560L;
	/**
	 * Familia Instalaciones
	 */
	private FamiliaInstalacionDTO familiaInstalacion;
	/**
	 * Marca
	 */
	private String marca;
	/**
	 * Modelo
	 */
	private String modelo;
	/**
	 * Flag que indica si el tipo de instalacion esta asociada a una configuracion
	 */
	private Boolean asociadaConfiguracion;
	/**
	 * Flag que indica ordenar alfabeticamente 
	 */
	private boolean ordenarAlfabeticamenteNombre = false;
	/**
	 * Obtiene la familia de instalaciones
	 * @return  la familia de instalaciones
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
	 * Obtiene si el tipo de instalacion esta asociada a una configuracion
	 * @return si el tipo de instalacion esta asociada a una configuracion
	 */
	public Boolean getAsociadaConfiguracion() {
		return asociadaConfiguracion;
	}
	/**
	 * Establece si el tipo de instalacion esta asociada a una configuracion
	 * @param asociadaConfiguracion si el tipo de instalacion esta asociada a una configuracion
	 */
	public void setAsociadaConfiguracion(Boolean asociadaConfiguracion) {
		this.asociadaConfiguracion = asociadaConfiguracion;
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
		String listCamposFiltro = super.generateStringCamposFiltro();
		if(getFamiliaInstalacion() !=null && getFamiliaInstalacion().getId() !=null ){
			listCamposFiltro = listCamposFiltro.concat("Familia de Instalacion").concat(", ");
		}
		if(getMarca() != null && getMarca().length() >0) {
			listCamposFiltro = listCamposFiltro.concat("Marca").concat(", ");
		}
		if(getModelo() != null && getModelo().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Modelo").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
