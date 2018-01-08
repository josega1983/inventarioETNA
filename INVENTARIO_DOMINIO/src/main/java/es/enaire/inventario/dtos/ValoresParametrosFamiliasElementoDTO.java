package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class ValoresParametrosFamiliasElementoDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 557078151654924370L;
	/**
	 * El id
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia de elemento
	 */
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Valor parametro funcional
	 */
	private ValoresParametroFuncionalDTO valoresParametroFuncional;
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
	 * Obtiene los valores parametro funcional
	 * @return los valores parametro funcional
	 */
	public ValoresParametroFuncionalDTO getValoresParametroFuncional() {
		return valoresParametroFuncional;
	}
	/**
	 * Establece los valores parametro funcional
	 * @param valoresParametroFuncional los valores parametro funcional
	 */
	public void setValoresParametroFuncional(
			ValoresParametroFuncionalDTO valoresParametroFuncional) {
		this.valoresParametroFuncional = valoresParametroFuncional;
	}
	
}
