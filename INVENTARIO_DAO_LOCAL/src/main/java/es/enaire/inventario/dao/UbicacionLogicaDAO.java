package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroUbicacionLogica;



public interface UbicacionLogicaDAO<UbicacionLogicaDTO> extends IBaseDAO<UbicacionLogicaDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<UbicacionLogicaDTO> search(FiltroUbicacionLogica filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroUbicacionLogica filtro, Long pageSize, Long currentPage) throws Exception;

}
