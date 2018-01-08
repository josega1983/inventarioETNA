package es.enaire.inventario.dao;

import java.util.List;

import es.enaire.inventario.business.FiltroTipoCampoParametro;

public interface TipoCampoParametroDAO<TipoCampoParametroDTO> extends IBaseDAO<TipoCampoParametroDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public List<TipoCampoParametroDTO> search(FiltroTipoCampoParametro filtro, Long pageSize, Long currentPage) throws Exception;
}
