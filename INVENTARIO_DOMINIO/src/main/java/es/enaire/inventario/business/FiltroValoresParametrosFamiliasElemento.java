package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.FamiliaElementoDTO;
import es.enaire.inventario.dtos.ValoresParametroFuncionalDTO;

public class FiltroValoresParametrosFamiliasElemento extends BaseFiltro
		implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -8024075993944944233L;
	/**
	 * Familia de elemento
	 */
	private FamiliaElementoDTO familiaElemento;
	/**
	 * Valor parametro funcional
	 */
	private ValoresParametroFuncionalDTO valoresParametroFuncional;
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
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();

		if (getFamiliaElemento() != null && getFamiliaElemento().getId() != null) {
			listCamposFiltro = listCamposFiltro.concat("Familia de Elemento").concat(", ");
		}
		if (getValoresParametroFuncional() != null && getValoresParametroFuncional().getId() != null) {
			listCamposFiltro = listCamposFiltro.concat("Valores de Parametro Funcional").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
