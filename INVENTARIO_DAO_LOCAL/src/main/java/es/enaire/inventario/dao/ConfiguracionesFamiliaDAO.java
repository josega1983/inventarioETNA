package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroConfiguracionesFamilia;


public interface ConfiguracionesFamiliaDAO<ConfiguracionesFamiliaDTO> extends IBaseDAO<ConfiguracionesFamiliaDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<ConfiguracionesFamiliaDTO> search(FiltroConfiguracionesFamilia filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroConfiguracionesFamilia filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Crea un nodo hijo de configuraciones elements familia
	 * @param dto configuracion elemento familia dto
	 * @return configuracion elemento familia
	 * @throws Exception Excepcion que se puede producir durante el proceso de creaccion y/o actualizacion
	 */
	public ConfiguracionesFamiliaDTO saveOrUpdateConfiguracionElementosFamilia(ConfiguracionesFamiliaDTO dto) throws Exception;
}
