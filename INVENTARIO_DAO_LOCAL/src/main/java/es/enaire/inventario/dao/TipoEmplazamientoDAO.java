package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroTipoEmplazamiento;
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;



public interface TipoEmplazamientoDAO<RegionDTO> extends IBaseDAO<TipoEmplazamientoDTO> {

	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<TipoEmplazamientoDTO> search(FiltroTipoEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroTipoEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception;
}
