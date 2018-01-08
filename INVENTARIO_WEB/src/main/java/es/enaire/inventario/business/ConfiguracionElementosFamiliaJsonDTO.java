package es.enaire.inventario.business;

import java.io.Serializable;
import java.util.List;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionElementosFamiliaJsonDTO implements Serializable{
	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -6695262998542982724L;
	/**
	 * El id de configuracion de elemntos familia
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia de elementos
	 */
	private FamiliaElementoJsonDTO familiaElemento;
	/**
	 * Configuracion Elementos Familia Padre
	 */
	private List<ConfiguracionElementosFamiliaJsonDTO> hijos;
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
	 * Obtiene el listado de nodos hijos
	 * @return listado de nodos hijos
	 */
	public List<ConfiguracionElementosFamiliaJsonDTO> getHijos() {
		return hijos;
	}
	/**
	 * Establece los nodos hijos del configuracionElementosFamilia
	 * @param hijos de configuracionElementosFamilia actual
	 */
	public void setHijos(List<ConfiguracionElementosFamiliaJsonDTO> hijos) {
		this.hijos = hijos;
	}
}
