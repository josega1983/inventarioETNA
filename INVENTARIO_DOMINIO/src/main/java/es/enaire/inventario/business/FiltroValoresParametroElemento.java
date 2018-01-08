package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.ElementoDTO;
import es.enaire.inventario.dtos.ValoresParametrosFamiliasElementoDTO;

public class FiltroValoresParametroElemento extends BaseFiltro implements
		Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 3102236187469232947L;
	/**
	 * Valores parametros familias de elementos
	 */
	private ValoresParametrosFamiliasElementoDTO valoresParametrosFamiliasElemento;
	/**
	 * Elemento
	 */
	private ElementoDTO elemento;
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
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = "";

		if(getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Id").concat(", ");
		}
		if(getElemento() != null && getElemento().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Elemento").concat(", ");
		}
		if (getValoresParametrosFamiliasElemento() != null && getValoresParametrosFamiliasElemento().getId() != null) {
			listCamposFiltro = listCamposFiltro.concat("Valores Parametros Familia Elementos").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
