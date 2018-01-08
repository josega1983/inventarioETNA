package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroEstadoElemento;


public interface EstadoElementoDAO<EstadoElementoDTO> extends IBaseDAO<EstadoElementoDTO> {
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	@Override
	public EstadoElementoDTO saveOrUpdate(EstadoElementoDTO dto) throws Exception;
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<EstadoElementoDTO> search(FiltroEstadoElemento filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroEstadoElemento filtro, Long pageSize, Long currentPage) throws Exception;
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public EstadoElementoDTO reactivar(Long id) throws Exception;
}
