package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;
import es.enaire.inventario.dtos.ParametroFuncionalDTO;
import es.enaire.inventario.dtos.TipoCampoParametroDTO;

public class FiltroConfiguracionParametroFuncional extends BaseFiltro implements
		Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -471253287188900028L;
	/**
	 * Id 
	 */
	@IdTarget
	private Long id;
	/**
	 * Parametro Funcional
	 */
	private ParametroFuncionalDTO parametroFuncional;
	/**
	 * Nombre de campo
	 */
	private String nombreCampo;
	/**
	 * Tipo de Campo
	 */
	private TipoCampoParametroDTO tipoCampoParametro;
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
	 * Obtiene el parametro funcional
	 * @return el parametro funcional
	 */
	public ParametroFuncionalDTO getParametroFuncional() {
		return parametroFuncional;
	}
	/**
	 * Establece el parametro funcional
	 * @param parametroFuncional el parametro funcional
	 */
	public void setParametroFuncional(ParametroFuncionalDTO parametroFuncional) {
		this.parametroFuncional = parametroFuncional;
	}
	/**
	 * Obtiene el nombre del campo
	 * @return el nombre del campo
	 */
	public String getNombreCampo() {
		return nombreCampo;
	}
	/**
	 * Establece el nombre del campo
	 * @param nombreCampo el nombre del campo
	 */
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	/**
	 * Obtiene el tipo de Campo
	 * @return el tipo de Campo
	 */
	public TipoCampoParametroDTO getTipoCampoParametro() {
		return tipoCampoParametro;
	}
	/**
	 * Establece el tipo de Campo
	 * @param tipoCampo el tipo de Campo
	 */
	public void setTipoCampoParametro(TipoCampoParametroDTO tipoCampoParametro) {
		this.tipoCampoParametro = tipoCampoParametro;
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
		if(getNombreCampo() != null && getNombreCampo().length()>0){
			listCamposFiltro = listCamposFiltro.concat("Nombre de Campo").concat(", ");
		}
		if(getParametroFuncional() != null && getParametroFuncional().getId() !=null){
			listCamposFiltro = listCamposFiltro.concat("Parametro Funcional").concat(", ");
		}
		if(getTipoCampoParametro() != null && getTipoCampoParametro().getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Tipo de Campo").concat(", ");
		}
		//Eliminamos la ultima coma y espacio introducidos.
		if(listCamposFiltro != ""){
			listCamposFiltro = listCamposFiltro.substring(0, listCamposFiltro.length()-2);
		}
		else {
			listCamposFiltro = null; //Devolvemos null si no hay ningun filtro introducido
		}

		setCamposFiltro(listCamposFiltro);
		return listCamposFiltro;
	}
}
