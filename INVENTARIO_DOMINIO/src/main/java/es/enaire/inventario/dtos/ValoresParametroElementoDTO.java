package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class ValoresParametroElementoDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 5106152757323915996L;
	/**
	 * El id 
	 */
	@IdTarget
	private Long id;
	/**
	 * Valores parametros familias de elementos
	 */
	private ValoresParametrosFamiliasElementoDTO valoresParametrosFamiliasElemento;
	/**
	 * Elemento
	 */
	private ElementoDTO elemento;
	/**
	 * Obtiene el id
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Establece el  id
	 * @param id el id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Obtiene los Valores parametros familias de elementos
	 * @return los Valores parametros familias de elementos
	 */
	public ValoresParametrosFamiliasElementoDTO getValoresParametrosFamiliasElemento() {
		return valoresParametrosFamiliasElemento;
	}
	/**
	 * Establece los Valores parametros familias de elementos
	 * @param valoresParametrosFamiliasElemento los Valores parametros familias de elementos
	 */
	public void setValoresParametrosFamiliasElemento(
			ValoresParametrosFamiliasElementoDTO valoresParametrosFamiliasElemento) {
		this.valoresParametrosFamiliasElemento = valoresParametrosFamiliasElemento;
	}
	/**
	 * Obtiene el elemento
	 * @return el elemento
	 */
	public ElementoDTO getElemento() {
		return elemento;
	}
	/**
	 * Establece el elemento
	 * @param elemento el elemento
	 */
	public void setElemento(ElementoDTO elemento) {
		this.elemento = elemento;
	}
	
	
}
