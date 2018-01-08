package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroConfiguracionElementosTipo;


public interface ConfiguracionElementosTipoDAO<ConfiguracionElementosTipoDTO> extends IBaseDAO<ConfiguracionElementosTipoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<ConfiguracionElementosTipoDTO> search(FiltroConfiguracionElementosTipo filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public ConfiguracionElementosTipoDTO reactivar(Long id) throws Exception;
}
