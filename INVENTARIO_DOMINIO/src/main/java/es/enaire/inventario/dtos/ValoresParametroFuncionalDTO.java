package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class ValoresParametroFuncionalDTO implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 423671806397611447L;
	/**
	 * El Id
	 */
	@IdTarget
	private Long id;
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
	 * Obtiene el id
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Establece el id
	 * @param id el id
	 */
	public void setId(Long id) {
		this.id = id;
	}
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
	
}
