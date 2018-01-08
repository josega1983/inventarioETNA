package es.enaire.inventario.business;

import java.io.Serializable;

import es.enaire.inventario.dtos.CentroDTO;
import es.enaire.inventario.dtos.RegionDTO;
import es.enaire.inventario.dtos.SectorDTO;
import es.enaire.inventario.dtos.UnidadMantenimientoDTO;
/**
 * Clase de mapeo con la informacion del filtro de  region.
 *
 */
public class FiltroUbicacionLogica extends BaseFiltro implements Serializable {

	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = -8205079387759177124L;
	/**
	 * Region
	 */
	private RegionDTO region;
	/**
	 * Sector
	 */
	private SectorDTO sector;
	/**
	 * Centro
	 */
	private CentroDTO centro;
	/**
	 * Unidad de Mantenimiento
	 */
	private UnidadMantenimientoDTO unidadMantenimiento;
	/**
	 * Obtiene la region
	 * @return la region
	 */
	public RegionDTO getRegion() {
		return region;
	}
	/**
	 * Establece la region
	 * @param region
	 */
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	/**
	 * Obtiene el sector
	 * @return el sector
	 */
	public SectorDTO getSector() {
		return sector;
	}
	/**
	 * Establece el sector
	 * @param sector el sector
	 */
	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}
	/**
	 * Obtiene el centro
	 * @return el centro
	 */
	public CentroDTO getCentro() {
		return centro;
	}
	/**
	 * Establece el centro
	 * @param centro el centro
	 */
	public void setCentro(CentroDTO centro) {
		this.centro = centro;
	}
	/**
	 * Obtiene la unidad de mantenimiento
	 * @return la unidad de mantenimiento
	 */
	public UnidadMantenimientoDTO getUnidadMantenimiento() {
		return unidadMantenimiento;
	}
	/**
	 * Establece la unidad de mantenimiento
	 * @param unidadMantenimiento la unidad de mantenimiento
	 */
	public void setUnidadMantenimiento(UnidadMantenimientoDTO unidadMantenimiento) {
		this.unidadMantenimiento = unidadMantenimiento;
	}
	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = super.generateStringCamposFiltro();
		if(getRegion() !=null && (getRegion().getId() != null || getRegion().getNombre() != null)){
			listCamposFiltro = listCamposFiltro.concat("Region").concat(", ");
		}
		if(getCentro() != null && (getCentro().getId() !=null || getCentro().getNombre() != null)){
			listCamposFiltro = listCamposFiltro.concat("Centro").concat(", ");
		}
		if(getSector() != null && (getSector().getId() !=null || getSector().getNombre() != null)){
			listCamposFiltro = listCamposFiltro.concat("Sector").concat(", ");
		}
		if(getUnidadMantenimiento() != null && (getUnidadMantenimiento().getId() !=null || getUnidadMantenimiento().getNombre() !=null)){
			listCamposFiltro = listCamposFiltro.concat("Unidad de Mantenimiento").concat(", ");
		}
		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}
