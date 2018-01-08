package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.dtos.ValoresParametroElementoDTO;

public class ValoresParametroElemento implements
		IBaseEntity<ValoresParametroElementoDTO>, Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = -5648419387945332641L;
	/**
	 * El id 
	 */
	private Long id;
	/**
	 * Valores parametros familias de elementos
	 */
	private ValoresParametrosFamiliasElemento valoresParametrosFamiliasElemento;
	/**
	 * Elemento
	 */
	private Elemento elemento;
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
	public ValoresParametrosFamiliasElemento getValoresParametrosFamiliasElemento() {
		return valoresParametrosFamiliasElemento;
	}
	/**
	 * Establece los Valores parametros familias de elementos
	 * @param valoresParametrosFamiliasElemento los Valores parametros familias de elementos
	 */
	public void setValoresParametrosFamiliasElemento(
			ValoresParametrosFamiliasElemento valoresParametrosFamiliasElemento) {
		this.valoresParametrosFamiliasElemento = valoresParametrosFamiliasElemento;
	}
	/**
	 * Obtiene el elemento
	 * @return el elemento
	 */
	public Elemento getElemento() {
		return elemento;
	}
	/**
	 * Establece el elemento
	 * @param elemento el elemento
	 */
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ValoresParametroElemento(ValoresParametroElementoDTO dto) {
		this.setId(dto.getId());
		if (dto.getElemento() != null) {
			this.setElemento(new Elemento(dto.getElemento()));
		}
		if (dto.getValoresParametrosFamiliasElemento() != null) {
			this.setValoresParametrosFamiliasElemento(new ValoresParametrosFamiliasElemento(dto.getValoresParametrosFamiliasElemento()));
		}

	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ValoresParametroElemento() {
	}
	@Override
	public ValoresParametroElementoDTO crearDTO() {
		ValoresParametroElementoDTO dto = new ValoresParametroElementoDTO();
		dto.setId(getId());
		if (getElemento() != null) {
			dto.setElemento(getElemento().crearDTO());
		}
		if (getValoresParametrosFamiliasElemento() != null) {
			dto.setValoresParametrosFamiliasElemento(getValoresParametrosFamiliasElemento().crearDTO());
		}
		return dto;
	}

	@Override
	public void setActivo(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFechaBaja(Date date) {
		// TODO Auto-generated method stub

	}

}
