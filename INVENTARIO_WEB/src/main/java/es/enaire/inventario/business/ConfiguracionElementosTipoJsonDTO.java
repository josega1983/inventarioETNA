package es.enaire.inventario.business;

import java.io.Serializable;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionElementosTipoJsonDTO implements Serializable{

	/**
	 *  Indicador de serializacion
	 */
	private static final long serialVersionUID = -3054860451024898443L;
	@IdTarget
	private Long id;
	/**
	 * Familia de elementos
	 */
	private FamiliaElementoJsonDTO familiaElemento;
	/**
	 * Configuracion Elementos Tipo hijos
	 */
	private List<ConfiguracionElementosTipoJsonDTO> hijos;
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
	 * Obtiene la familia de elemento
	 * @return la familia de elemento
	 */
	public FamiliaElementoJsonDTO getFamiliaElemento() {
		return familiaElemento;
	}
	/**
	 * Establece la familia de elemento
	 * @param familiaElemento la familia de elemento
	 */
	public void setFamiliaElemento(FamiliaElementoJsonDTO familiaElemento) {
		this.familiaElemento = familiaElemento;
	}
	/**
	 * Obtiene la Configuracion Elementos Tipo hijos
	 * @return la Configuracion Elementos Tipo hijos
	 */
	public List<ConfiguracionElementosTipoJsonDTO> getHijos() {
		return hijos;
	}
	/**
	 * Establece la Configuracion Elementos Tipo hijos
	 * @param hijos la Configuracion Elementos Tipo hijos
	 */
	public void setHijos(List<ConfiguracionElementosTipoJsonDTO> hijos) {
		this.hijos = hijos;
	}
}
