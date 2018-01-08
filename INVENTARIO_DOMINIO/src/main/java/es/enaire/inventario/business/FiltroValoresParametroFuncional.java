package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;

public class FiltroValoresParametroFuncional extends BaseFiltro implements
		Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -2112377520087926360L;
	/**
	 * Configuracion Parametro Funcional
	 */
	private ConfiguracionParametroFuncionalDTO configuracionParametroFuncional;
	/**
	 * Valor
	 */
	private String valor;
	/**
	 * Condicion disparo
	 */
	private String condicionDisparo;
	/**
	 * Severidad Alarma
	 */
	private String severidadAlarma;
	/**
	 * Obtiene la configuracion de parametro funcional
	 * @return la configuracion de parametro funcional
	 */
	public ConfiguracionParametroFuncionalDTO getConfiguracionParametroFuncional() {
		return configuracionParametroFuncional;
	}
	/**
	 * Establece la configuracion de parametro funcional
	 * @param configuracionParametroFuncional la configuracion de parametro funcional
	 */
	public void setConfiguracionParametroFuncional(
			ConfiguracionParametroFuncionalDTO configuracionParametroFuncional) {
		this.configuracionParametroFuncional = configuracionParametroFuncional;
	}
	/**
	 * Obtiene el valor
	 * @return el valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * Establece el valor
	 * @param valor el valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * Obtiene la condicion de disparo
	 * @return la condicion de disparo
	 */
	public String getCondicionDisparo() {
		return condicionDisparo;
	}
	/**
	 * Establece la condicion de disparo
	 * @param condicionDisparo la condicion de disparo
	 */
	public void setCondicionDisparo(String condicionDisparo) {
		this.condicionDisparo = condicionDisparo;
	}
	/**
	 * Obtiene la severidad de alarma
	 * @return la severidad de alarma
	 */
	public String getSeveridadAlarma() {
		return severidadAlarma;
	}
	/**
	 * Establece la severidad de alarma
	 * @param severidadAlarma la severidad de alarma
	 */
	public void setSeveridadAlarma(String severidadAlarma) {
		this.severidadAlarma = severidadAlarma;
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
		if(getCondicionDisparo() != null && getCondicionDisparo().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Condicion disparo").concat(", ");
		}
		if(getConfiguracionParametroFuncional() != null && getConfiguracionParametroFuncional().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Configuracion Parametro Funcional").concat(", ");
		}
		if(getSeveridadAlarma() != null && getSeveridadAlarma().length() >0) {
			listCamposFiltro = listCamposFiltro.concat("Severidad de Alarma").concat(", ");
		}
		if(getValor() != null && getValor().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Valor").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
