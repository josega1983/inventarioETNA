package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.dtos.ValoresParametroFuncionalDTO;

public class ValoresParametroFuncional implements IBaseEntity<ValoresParametroFuncionalDTO>, Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 1553486682624850403L;
	/**
	 * El Id
	 */
	private Long id;
	/**
	 * Configuracion Parametro Funcional
	 */
	private ConfiguracionParametroFuncional configuracionParametroFuncional;
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
	public ConfiguracionParametroFuncional getConfiguracionParametroFuncional() {
		return configuracionParametroFuncional;
	}
	/**
	 * Establece la configuracion de parametro funcional
	 * @param configuracionParametroFuncional la configuracion de parametro funcional
	 */
	public void setConfiguracionParametroFuncional(
			ConfiguracionParametroFuncional configuracionParametroFuncional) {
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
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ValoresParametroFuncional(ValoresParametroFuncionalDTO dto) {
		this.setId(dto.getId());
		this.setCondicionDisparo(getCondicionDisparo());
		if(dto.getConfiguracionParametroFuncional() != null){
			this.setConfiguracionParametroFuncional(new ConfiguracionParametroFuncional(dto.getConfiguracionParametroFuncional()) );
		}
		this.setSeveridadAlarma(dto.getSeveridadAlarma());
		this.setValor(dto.getValor());
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ValoresParametroFuncional() {
		
	}
	@Override
	public ValoresParametroFuncionalDTO crearDTO() {
		ValoresParametroFuncionalDTO dto = new ValoresParametroFuncionalDTO();
		dto.setId(getId());
		dto.setCondicionDisparo(getCondicionDisparo());
		if(getConfiguracionParametroFuncional() != null){
			dto.setConfiguracionParametroFuncional(getConfiguracionParametroFuncional().crearDTO());
		}
		dto.setSeveridadAlarma(getSeveridadAlarma());
		dto.setValor(getValor());
		return dto;
	}

	@Override
	public void setActivo(String string) {
		
	}

	@Override
	public void setFechaBaja(Date date) {
		
	}

}
