package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.ConfiguracionElementosFamiliaDTO;
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;

public class FiltroConfiguracionElementosFamilia extends BaseFiltro implements Serializable {

	/**
	 * Identificador univoco de la tabla.
	 */
	private static final long serialVersionUID = -2883176478368947480L;
	/**
	 * Configuracion Familia
	 */
	private ConfiguracionesFamiliaDTO configuracionesFamilia;
	/**
	 * Familia de elementos
	 */
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Configuracion Elementos Familia Padre
	 */
	private ConfiguracionElementosFamiliaDTO configuracionElementosFamiliaPadre;
	/**
	 * Obtiene las configuraciones familia
	 * @return las configuraciones familia
	 */
	public ConfiguracionesFamiliaDTO getConfiguracionesFamilia() {
		return configuracionesFamilia;
	}
	/**
	 * Establece las configuraciones familia
	 * @param configuracionesFamilia las configuraciones familia
	 */
	public void setConfiguracionesFamilia(ConfiguracionesFamiliaDTO configuracionesFamilia) {
		this.configuracionesFamilia = configuracionesFamilia;
	}
	/**
	 * Obtiene la familia de elemento
	 * @return la familia de elemento
	 */
	public FamiliaElementoDTO getFamiliaElemento() {
		return familiaElemento;
	}
	/**
	 * Establece la familia de elemento
	 * @param familiaElemento la familia de elemento
	 */
	public void setFamiliaElemento(FamiliaElementoDTO familiaElemento) {
		this.familiaElemento = familiaElemento;
	}
	/**
	 * Obtiene la Configuracion Elementos Familia Padre
	 * @return la Configuracion Elementos Familia Padre
	 */
	public ConfiguracionElementosFamiliaDTO getConfiguracionElementosFamiliaPadre() {
		return configuracionElementosFamiliaPadre;
	}
	/**
	 * Establece Configuracion Elementos Familia Padre
	 * @param configuracionElementosFamiliaPadre Configuracion Elementos Familia Padre
	 */
	public void setConfiguracionElementosFamiliaPadre(
			ConfiguracionElementosFamiliaDTO configuracionElementosFamiliaPadre) {
		this.configuracionElementosFamiliaPadre = configuracionElementosFamiliaPadre;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = inicializarCamposComunes();
		if(getConfiguracionElementosFamiliaPadre() != null && getConfiguracionElementosFamiliaPadre().getId() !=null){
			listCamposFiltro = listCamposFiltro.concat("Configuracion Elementos Familia Padre").concat(", ");
		}
		if (getConfiguracionesFamilia() != null && getConfiguracionesFamilia().getId() != null) {
			listCamposFiltro = listCamposFiltro.concat("Configuraciones Familia").concat(", ");
		}
		if(getFamiliaElemento() !=null && getFamiliaElemento().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Familia de Elemento").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
