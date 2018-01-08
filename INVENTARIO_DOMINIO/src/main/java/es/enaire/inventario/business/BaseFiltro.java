package es.enaire.inventario.business;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseFiltro implements Serializable {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 7849180267220705648L;

	/**
	 * Identificador univoco de la tabla.
	 */
	private Long id;

	/**
	 * Observaciones
	 */
	private String observaciones;
	/**
	 * Fecha de alta desde la que filtrar.
	 */
	private Date fechaAltaDesde;
	/**
	 * Fecha de alta hasta la que filtrar.
	 */
	private Date fechaAltaHasta;
	/**
	 * Fecha de baja desde la que filtrar.
	 */
	private Date fechaBajaDesde;
	/**
	 * Fecha de baja hasta la que filtrar.
	 */
	private Date fechaBajaHasta;

	/**
	 * Detecta si solo hay que mostrar los que estan Activo
	 */
	private String activo;

	/**
     * Detecta si hay mas resultados que se salen fuera de la pagina actual
     */
    private Boolean hayMas;

    /**
     * string con el listado de los campos del filtro
     */
    private String camposFiltro;

	/**
	 * Id a excluir en caso de busquedas de duplicados
	 */
	private Long idExcluir;
	/**
     * Cantidad de objetos para informe
     */
    private Long cantidadInforme;


	/**
	 * Obtiene el identificador de el usuario.
	 * @return El identificador de el usuario.
	 */
	public Long getId() {
		return id;
	}
    /**
     * Establece el identificador de el usuario.
     * @param id El identificador de el usuario.
     */
	public void setId(Long id) {
		this.id = id;
	}


    /**
     * Obtiene si hay mas resultados que salen fuera para la pagina actual.
     * @return true si hay mas resultados, false en caso contrario.
     */
    public Boolean getHayMas() {
		return hayMas;
	}

    /**
     * Establece si hay mas resultados que salen fuera para la pagina actual.
     * @param hayMas true si hay mas resultados, false en caso contrario.
     */
	public void setHayMas(Boolean hayMas) {
		this.hayMas = hayMas;
	}

	/**
	 * Campos del filtro separados por comas para su representacion
	 * @return Los campos del filtro.
	 */
	public String getCamposFiltro() {
		return camposFiltro;
	}
	/**
	 * Establece los valores campos del filtro separados por comas para su representacion.
	 * @param camposFiltro Los valores campos del filtro.
	 */
	public void setCamposFiltro(String camposFiltro) {
		this.camposFiltro = camposFiltro;
	}
	/**
	 * Obtiene si es activo
	 * @return si es activo
	 */
	public String getActivo() {
		return activo;
	}
	/**
	 * Establece si es activo
	 * @param activo si es activo
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}
	/**
	 * Obtiene el Id a excluir en caso de busquedas de duplicados
	 * @return el Id a excluir en caso de busquedas de duplicados
	 */
	public Long getIdExcluir() {
		return idExcluir;
	}
	/**
	 * Establece el Id a excluir en caso de busquedas de duplicados
	 * @param idExcluir el Id a excluir en caso de busquedas de duplicados
	 */
	public void setIdExcluir(Long idExcluir) {
		this.idExcluir = idExcluir;
	}

	/**
	 * Obtiene las observaciones
	 * @return las observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Establece las observaciones
	 * @param observaciones las observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * Obtiene la fecha de baja desde la que filtrar.
	 * @return la fecha de baja desde la que filtrar.
	 */
	public Date getFechaBajaDesde() {
		return fechaBajaDesde;
	}
	/**
	 * Establece la fecha de baja desde la que filtrar.
	 * @param fechaBajaDesde la fecha de baja desde la que filtrar.
	 */
	public void setFechaBajaDesde(Date fechaBajaDesde) {
		this.fechaBajaDesde = fechaBajaDesde;
	}
	/**
	 * Obtiene la fecha de baja desde la que filtrar.
	 * @return la fecha de baja desde la que filtrar.
	 */
	public Date getFechaBajaHasta() {
		return fechaBajaHasta;
	}
	/**
	 * Establece la fecha de baja hasta la que filtrar.
	 * @param fechaBajaHasta La fecha de baja hasta la que filtrar.
	 */
	public void setFechaBajaHasta(Date fechaBajaHasta) {
		this.fechaBajaHasta = fechaBajaHasta;
	}
	/**
	 * Obtiene la fecha de alta desde la que filtrar.
	 * @return La fecha de alta desde la que filtrar.
	 */
	public Date getFechaAltaDesde() {
		return fechaAltaDesde;
	}
	/**
	 * Establece la fecha de alta desde la que filtrar.
	 * @param fechaAltaDesde La fecha de alta desde la que filtrar.
	 */
	public void setFechaAltaDesde(Date fechaAltaDesde) {
		this.fechaAltaDesde = fechaAltaDesde;
	}
	/**
	 * Obtiene la fecha de alta hasta la que filtrar.
	 * @return La fecha de alta hasta la que filtrar.
	 */
	public Date getFechaAltaHasta() {
		return fechaAltaHasta;
	}
	/**
	 * Establece la fecha de alta hasta la que filtrar.
	 * @param fechaAltaHasta La fecha de alta hasta la que filtrar.
	 */
	public void setFechaAltaHasta(Date fechaAltaHasta) {
		this.fechaAltaHasta = fechaAltaHasta;
	}

	/**
	 * Obtiene la Cantidad de objetos para informe
	 * @return la Cantidad de objetos para informe
	 */
	public Long getCantidadInforme() {
		return cantidadInforme;
	}
	/**
	 * Establece la Cantidad de objetos para informe
	 * @param cantidadInforme la Cantidad de objetos para informe
	 */

	public void setCantidadInforme(Long cantidadInforme) {
		this.cantidadInforme = cantidadInforme;
	}

	/**
	 * Metodo para inicializar los campos comunes del filtro.
	 * @return El filtro de los campos comunes del filtro.
	 */
	protected String inicializarCamposComunes() {
		String listCamposFiltro = "";

		if(getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Id").concat(", ");
		}
		if(getObservaciones() != null && getObservaciones().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Observaciones").concat(", ");
		}
		if(getActivo() != null && getActivo().length() > 0){
			listCamposFiltro = listCamposFiltro.concat("Activo [").concat(getActivo()).concat("], ");
		}
		if(getFechaBajaDesde() != null){
			listCamposFiltro = listCamposFiltro.concat("Fecha Baja Desde").concat(", ");
		}
		if(getFechaBajaHasta() != null){
			listCamposFiltro = listCamposFiltro.concat("Fecha Baja Hasta").concat(", ");
		}
		if(getFechaAltaDesde() != null){
			listCamposFiltro = listCamposFiltro.concat("Fecha Alta Desde").concat(", ");
		}
		if(getFechaAltaHasta() != null){
			listCamposFiltro = listCamposFiltro.concat("Fecha Alta Hasta").concat(", ");
		}

		return listCamposFiltro;
	}

	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro() {
		return inicializarCamposComunes();
	}
	/**
	 * Elimina la ultima coma de campos filtro o devuelve null si no encuentra nada.
	 */
	protected String finalizarGenerateStringCamposFiltro(String listCamposFiltro){
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
