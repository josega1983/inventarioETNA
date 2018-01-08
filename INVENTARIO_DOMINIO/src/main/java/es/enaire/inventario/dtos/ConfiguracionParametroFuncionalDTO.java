package es.enaire.inventario.dtos;

import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class ConfiguracionParametroFuncionalDTO implements Serializable {

	/**
	 * Indicador de serializacion
	 */
	private static final long serialVersionUID = 6674652492223690105L;
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
	 * Opciones a mostrar en el estado del menu
	 */
	private String estadoMenu;
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
	 * Obtiene el estado del menu
	 * @return El estado del menu
	 */
	public String getEstadoMenu() {
		return estadoMenu;
	}
	/**
	 * Establece el estado del menu
	 * @param estadoMenu El estado de menu a establecer
	 */
	public void setEstadoMenu(String estadoMenu) {
		this.estadoMenu = estadoMenu;
	}
}
