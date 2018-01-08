package es.enaire.inventario.business;

import java.io.Serializable;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroFamiliaInstalacion extends BaseFiltro implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -2686542195994137565L;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Area
	 */
	private String area;
	/**
	 * Flag que indica si la familia de instalacion esta asociada a una configuracion
	 */
	private Boolean asociadaConfiguracion;
	/**
	 * Flag que indica ordenar alfabeticamente 
	 */
	private boolean ordenarAlfabeticamenteNombre = false;
	/**
	 * Obtiene el nombre
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Obtiene el area
	 * @return el area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * Establece el area
	 * @param area el area
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * Establece el nombre
	 * @param nombre el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Obtiene si la familia de instalacion esta asociada a una configuracion
	 * @return si la familia de instalacion esta asociada a una configuracion
	 */
	public Boolean getAsociadaConfiguracion() {
		return asociadaConfiguracion;
	}
	/**
	 * Establece si la familia de instalacion esta asociada a una configuracion
	 * @param asociadaConfiguracion si la familia de instalacion esta asociada a una configuracion
	 */
	public void setAsociadaConfiguracion(Boolean asociadaConfiguracion) {
		this.asociadaConfiguracion = asociadaConfiguracion;
	}
	/**
	 * Obtiene el flag para la ordenacion alfabetica
	 * @return boolean ordenarAlfabeticamenteNombre
	 */
	public boolean isOrdenarAlfabeticamenteNombre() {
		return ordenarAlfabeticamenteNombre;
	}
	/**
	 * Establece el flag para la ordenacion alfabetica
	 * @param boolean ordenarAlfabeticamenteNombre
	 */
	public void setOrdenarAlfabeticamenteNombre(boolean ordenarAlfabeticamenteNombre) {
		this.ordenarAlfabeticamenteNombre = ordenarAlfabeticamenteNombre;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro =  super.generateStringCamposFiltro();

		if(getArea() != null && getArea().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Area").concat(", ");
		}
		if(getNombre() != null && getNombre().length() >0){
			listCamposFiltro = listCamposFiltro.concat("Nombre").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
