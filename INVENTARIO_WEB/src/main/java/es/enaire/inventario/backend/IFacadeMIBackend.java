package es.enaire.inventario.backend;


import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.RespuestaDTO;


/**
 * Interfaz de los servicios que va a ofrecer el backend.
 *
 */
public interface IFacadeMIBackend {
	/**
	 * Obtiene los datos de la entrada solicitada.
	 * @param entrada de la informacion solicitada
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO info(EntradaDTO entrada);
	/**
	 * Obtiene la lista con los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO list(EntradaDTO entrada);
	/**
	 * Crea o actualiza un registro en la base de datos del tipo de la entrada seleccionada
	 * @param entrada con el objeto a crear o actualizar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO edit(EntradaDTO entrada);
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param entrada con el objeto a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO reactivar(EntradaDTO entrada);
	/**
	 * Elimina un registro en la base de datos del tipo de entrada seleccionada
	 * @param entrada con el objeto a eliminar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO delete(EntradaDTO entrada);
	/**
	 * Obtiene la lista con los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO search(EntradaDTO entrada);
	/**
	 * Obtiene cantidad de objetos que verifican los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 */
	RespuestaDTO getCantidad(EntradaDTO entrada);
	/**
	 * Actuliza el estado de un registro de la base de datos del tipo de entrada seleccionada
	 * @param entrada con el objeto a actualizar el estado
	 * @return la respuesta con el codigo respuesta y el objeto actualizado como objeto respuesa
	 */
	RespuestaDTO changeStatus(EntradaDTO entrada);
}
