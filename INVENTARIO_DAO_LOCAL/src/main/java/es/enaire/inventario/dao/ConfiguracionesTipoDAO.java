package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroConfiguracionesTipo;


public interface ConfiguracionesTipoDAO<ConfiguracionesTipoDTO> extends IBaseDAO<ConfiguracionesTipoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<ConfiguracionesTipoDTO> search(FiltroConfiguracionesTipo filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroConfiguracionesTipo filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public ConfiguracionesTipoDTO reactivar(Long id) throws Exception;
	/**
	 * Crea un nodo hijo de configuraciones elements tipo
	 * @param dto configuracion elemento tipo dto
	 * @return configuracion elemento tipo
	 * @throws Exception Excepcion que se puede producir durante el proceso de creaccion y/o actualizacion
	 */
	public ConfiguracionesTipoDTO saveOrUpdateConfiguracionElementosTipo(ConfiguracionesTipoDTO dto) throws Exception;
}
