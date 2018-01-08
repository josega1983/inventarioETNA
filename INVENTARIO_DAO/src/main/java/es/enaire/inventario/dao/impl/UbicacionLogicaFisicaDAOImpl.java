package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroUbicacionLogicaFisica;
import es.enaire.inventario.dao.UbicacionLogicaFisicaDAO;
import es.enaire.inventario.dtos.UbicacionFisicaDTO;
import es.enaire.inventario.dtos.UbicacionLogicaDTO;
import es.enaire.inventario.dtos.UbicacionLogicaFisicaDTO;
import es.enaire.inventario.model.UbicacionLogicaFisica;

public class UbicacionLogicaFisicaDAOImpl extends BaseDAOImpl<UbicacionLogicaFisicaDTO> implements
UbicacionLogicaFisicaDAO<UbicacionLogicaFisicaDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<UbicacionLogicaFisicaDTO> search(FiltroUbicacionLogicaFisica filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<UbicacionLogicaFisicaDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroUbicacionLogicaFisica filtro, Long pageSize, Long currentPage) throws Exception {
		return (Long) searchImpl(filtro, true, pageSize, currentPage);
	}
	/**
	 * Metodo que nos devuelve o un cantidad de objetos que cumplen el fitro especificado o el listado que lo cumplen
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param cantidad si lo que se quiere obtener es una cantidad o un listado
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado o la cantidad de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	private Object searchImpl(FiltroUbicacionLogicaFisica filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<UbicacionLogicaFisicaDTO> list = new ArrayList<UbicacionLogicaFisicaDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select ulf from UbicacionLogicaFisica ulf ";
			if(cantidad){
				query = "select count(*) from UbicacionLogicaFisica ulf ";
			}

			if (filtro.getId() != null) {
				conditions.add("ulf.id = :idUbicacionLogicaFisica");
				parameters.put("idUbicacionLogicaFisica", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("ulf.id != :idUbicacionLogicaFisicaExcluir");
				parameters.put("idUbicacionLogicaFisicaExcluir", filtro.getIdExcluir());
			}
			if(filtro.getUbicacionFisica() != null){
				UbicacionFisicaDTO  ubicacionFisica = filtro.getUbicacionFisica();
				if(ubicacionFisica.getId() != null){
					conditions.add("ulf.ubicacionFisica.id = :idUbicacionFisica");
					parameters.put("idUbicacionFisica", ubicacionFisica.getId());
				}
			}
			if(filtro.getUbicacionLogica() != null){
				UbicacionLogicaDTO  ubicacionLogica = filtro.getUbicacionLogica();
				if(ubicacionLogica.getId() != null){
					conditions.add("ulf.ubicacionLogica.id = :idUbicacionLogica");
					parameters.put("idUbicacionLogica", ubicacionLogica.getId());
				}
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("ulf.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(ulf.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}


			int nCount = 0;
			for (String condition : conditions) {
				if (nCount == 0)
					query += " where ";
				else
					query += " and ";
				query += condition;
				nCount++;
			}

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());

			consulta.setFirstResult((currentPage.intValue() * pageSize.intValue()));
			consulta.setMaxResults(pageSize.intValue());

			List<UbicacionLogicaFisica> ubicacionLogicaFisicasFiltrados = consulta.getResultList();
			if(cantidad){
				return (ubicacionLogicaFisicasFiltrados != null ? ubicacionLogicaFisicasFiltrados.get(0):0L);
			}
			if (ubicacionLogicaFisicasFiltrados != null) {
				for (UbicacionLogicaFisica uUbicacionLogicaFisica : ubicacionLogicaFisicasFiltrados) {
					list.add(uUbicacionLogicaFisica.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}
	/**
	 * Metodo que nos guarda las modificaciones de una entidad en las tablas de modificacion
	 * @param dto a guardar
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado
	 */
	@Override
	public UbicacionLogicaFisicaDTO saveModificacion(
			UbicacionLogicaFisicaDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
