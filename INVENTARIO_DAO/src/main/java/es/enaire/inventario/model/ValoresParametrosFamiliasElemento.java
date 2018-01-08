package es.enaire.inventario.model;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.annotations.IdTarget;
import es.enaire.inventario.dtos.ValoresParametrosFamiliasElementoDTO;

public class ValoresParametrosFamiliasElemento implements
		IBaseEntity<ValoresParametrosFamiliasElementoDTO>, Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 8409036080437395452L;
	/**
	 * El id
	 */
	@IdTarget
	private Long id;
	/**
	 * Familia de elemento
	 */
	private FamiliaElemento familiaElemento;
	/**
	 * Valor parametro funcional
	 */
	private ValoresParametroFuncional valoresParametroFuncional;
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
	public FamiliaElemento getFamiliaElemento() {
		return familiaElemento;
	}
	/**
	 * Establece la familia de elemento
	 * @param familiaElemento la familia de elemento
	 */
	public void setFamiliaElemento(FamiliaElemento familiaElemento) {
		this.familiaElemento = familiaElemento;
	}
	/**
	 * Obtiene los valores parametro funcional
	 * @return los valores parametro funcional
	 */
	public ValoresParametroFuncional getValoresParametroFuncional() {
		return valoresParametroFuncional;
	}
	/**
	 * Establece los valores parametro funcional
	 * @param valoresParametroFuncional los valores parametro funcional
	 */
	public void setValoresParametroFuncional(
			ValoresParametroFuncional valoresParametroFuncional) {
		this.valoresParametroFuncional = valoresParametroFuncional;
	}
	/**
	 * Crea un objeto de empresa a partir de su correspondiente objeto que transporta su informacion.
	 * @param dto El objeto que transporta su informacion.
	 */
	public ValoresParametrosFamiliasElemento(ValoresParametrosFamiliasElementoDTO dto) {
		this.setId(dto.getId());
		if (dto.getFamiliaElemento() != null) {
			this.setFamiliaElemento(new FamiliaElemento(dto.getFamiliaElemento()));
		}
		if (dto.getValoresParametroFuncional() !=null) {
			this.setValoresParametroFuncional(new ValoresParametroFuncional(dto.getValoresParametroFuncional()));
		}
	}
	/**
	 * Constructor por defecto utilizado por JPA.
	 */
	public ValoresParametrosFamiliasElemento() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ValoresParametrosFamiliasElementoDTO crearDTO() {
		ValoresParametrosFamiliasElementoDTO dto = new ValoresParametrosFamiliasElementoDTO();
		dto.setId(getId());
		if(getFamiliaElemento() != null){
			dto.setFamiliaElemento(getFamiliaElemento().crearDTO());
		}
		if (getValoresParametroFuncional() != null) {
			dto.setValoresParametroFuncional(getValoresParametroFuncional().crearDTO());
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
