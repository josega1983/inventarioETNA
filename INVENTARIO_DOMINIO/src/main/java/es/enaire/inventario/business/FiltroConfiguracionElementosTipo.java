package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.ConfiguracionElementosTipoDTO;
import es.enaire.inventario.dtos.ConfiguracionesTipoDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;

public class FiltroConfiguracionElementosTipo extends BaseFiltro implements Serializable {

	/**
	 * Identificador univoco de la tabla.
	 */
	private static final long serialVersionUID = 7035368318696185295L;
	/**
	 * Configuracion Tipo
	 */
	private ConfiguracionesTipoDTO configuracionesTipo;
	/**
	 * Familia de elementos
	 */
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Configuracion Elementos Tipo Padre
	 */
	private ConfiguracionElementosTipoDTO configuracionElementosTipoPadre;
	/**
	 * Obtiene las configuraciones Tipo
	 * @return las configuraciones Tipo
	 */
	public ConfiguracionesTipoDTO getConfiguracionesTipo() {
		return configuracionesTipo;
	}
	/**
	 * Establece las configuraciones Tipo
	 * @param configuracionesFamilia las configuraciones Tipo
	 */
	public void setConfiguracionesTipo(ConfiguracionesTipoDTO configuracionesTipo) {
		this.configuracionesTipo = configuracionesTipo;
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
	 * Obtiene la Configuracion Elementos Familia Tipo
	 * @return la Configuracion Elementos Familia Tipo
	 */
	public ConfiguracionElementosTipoDTO getConfiguracionElementosTipoPadre() {
		return configuracionElementosTipoPadre;
	}
	/**
	 * Establece Configuracion Elementos Tipo Padre
	 * @param configuracionElementosFamiliaPadre Configuracion Elementos Tipo Padre
	 */
	public void setConfiguracionElementosTipoPadre(
			ConfiguracionElementosTipoDTO configuracionElementosTipoPadre) {
		this.configuracionElementosTipoPadre = configuracionElementosTipoPadre;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = inicializarCamposComunes();
		if(getConfiguracionElementosTipoPadre() != null && getConfiguracionElementosTipoPadre().getId() !=null){
			listCamposFiltro = listCamposFiltro.concat("Configuracion Elementos Tipo Padre").concat(", ");
		}
		if (getConfiguracionesTipo() != null && getConfiguracionesTipo().getId() != null) {
			listCamposFiltro = listCamposFiltro.concat("Configuraciones Tipo").concat(", ");
		}
		if(getFamiliaElemento() !=null && getFamiliaElemento().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Familia de Elemento").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
