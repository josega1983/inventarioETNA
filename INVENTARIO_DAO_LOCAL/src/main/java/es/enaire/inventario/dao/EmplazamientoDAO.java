package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroEmplazamiento;
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;



public interface EmplazamientoDAO<EmplazamientoDTO> extends IBaseDAO<EmplazamientoDTO> {
	/**
	 * Obtiene un listado de elementos del catalogo por nombre y tipo de emplazamiento
	 * @param nombre nombre a buscar 
	 * @param tipoEmplazamiento tipo de emplazamiento
	 * @return listado de elementos del catalogo
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public List<EmplazamientoDTO> listByName(String nombre, TipoEmplazamientoDTO tipoEmplazamiento) throws Exception;
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<EmplazamientoDTO> search(FiltroEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception;

}
