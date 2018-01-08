package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroTipoEmplazamiento;
import es.enaire.inventario.dao.TipoEmplazamientoDAO;
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;
import es.enaire.inventario.model.TipoEmplazamiento;

public class TipoEmplazamientoDAOImpl extends BaseDAOImpl<TipoEmplazamientoDTO> implements TipoEmplazamientoDAO<TipoEmplazamientoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<TipoEmplazamientoDTO> search(FiltroTipoEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<TipoEmplazamientoDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroTipoEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroTipoEmplazamiento filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<TipoEmplazamientoDTO> list = new ArrayList<TipoEmplazamientoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select te from TipoEmplazamiento te ";
			if(cantidad){
				query = "select count(*) from TipoEmplazamiento te ";
			}

			if (filtro.getId() != null) {
				conditions.add("te.id = :idTipoEmplazamiento");
				parameters.put("idTipoEmplazamiento", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("te.id != :idTipoEmplazamientoExcluir");
				parameters.put("idTipoEmplazamientoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(te.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getDescripcion() != null && filtro.getDescripcion().length() > 0){
				conditions.add("UPPER(te.descripcion) like UPPER('%" + filtro.getDescripcion() + "' || '%')");
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(te.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("te.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("te.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("te.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
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

			List<TipoEmplazamiento> tipoEmplazamientoesFiltrados = consulta.getResultList();
			if(cantidad){
				return (tipoEmplazamientoesFiltrados != null ? tipoEmplazamientoesFiltrados.get(0):0L);
			}
			if (tipoEmplazamientoesFiltrados != null) {
				for (TipoEmplazamiento tipoEmplazamiento : tipoEmplazamientoesFiltrados) {
					list.add(tipoEmplazamiento.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}
	/**
	 * Obtiene el listado de objetos que cumplen el nombre especificado.
	 * @param String Nombre
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<TipoEmplazamientoDTO> listByName(String nombre) throws Exception {
		List<TipoEmplazamientoDTO> list = new ArrayList<TipoEmplazamientoDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select te from TipoEmplazamiento te where UPPER(te.nombre) = UPPER('" + nombre + "') and te.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<TipoEmplazamiento> tipoEmplazamientoesFiltrados = consulta.getResultList();
	
			if (tipoEmplazamientoesFiltrados != null) {
				for (TipoEmplazamiento tipoEmplazamiento : tipoEmplazamientoesFiltrados) {
					list.add(tipoEmplazamiento.crearDTO());
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
	public TipoEmplazamientoDTO saveModificacion(TipoEmplazamientoDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
