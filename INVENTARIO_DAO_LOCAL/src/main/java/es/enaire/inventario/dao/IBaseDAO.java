package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.BaseFiltro;


/**
 * DAO para la gestion de elementos
 * @param <T>
 */
public interface IBaseDAO<T> {
	/**
	 * Obtiene un elemento asociado por su identificador.
	 * @param id El identificador del elemento.
	 * @return El elemento recuperado a traves de su identificador.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public T listById(Long id) throws Exception;
	/**
	 * Obtiene la lista de de elementos del catalogo deseado.
	 * @param entrada con los datos de entrada.
	 * @return La lista de elementos recuperadas.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public List<T> list(Long paginaActual, Long tamanioPagina) throws Exception;

	/**
	 * Obtiene toda la lista de elementos del catalogo deseado.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public List<T> list() throws Exception;
	/**
	 * Obtiene un listado de elementos del catalogo por nombre
	 * @param nombre nombre a buscar 
	 * @return listado de elementos del catalogo
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public List<T> listByName(String nombre) throws Exception;
	/**
	 * Obtiene la lista de elementos del catalogo.
	 * @param entrada con los datos de entrada.
	 * @return La lista de elementos recuperadas.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public List<T> search(BaseFiltro filtro, Long paginaActual, Long tamanioPagina) throws Exception;
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	public T saveOrUpdate(T dto) throws Exception;
	/**
	 * Metodo que nos guarda las modificaciones de una entidad en las tablas de modificacion
	 * @param dto a guardar
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado
	 */
	public T saveModificacion(T dto) throws Exception;
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	public T reactivar(Long id) throws Exception;
	/**
	 * Elimina un registro
	 * @param id Identficador de elemento
	 * @throws Exception Excepcion que se puede producir durante el proceso de borrado.
	 */
	public void delete(Long id) throws Exception;
	/**
	 * Elimina fisicamente un registro
	 * @param id Indentificador del registro
	 *  @throws Exception Excepcion que se puede producir durante el proceso de borrado.
	 */
	public void remove(Long id) throws Exception;
}
