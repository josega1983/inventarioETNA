package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroPropietarioActivo;
import es.enaire.inventario.dao.PropietarioActivoDAO;
import es.enaire.inventario.dtos.PropietarioActivoDTO;
import es.enaire.inventario.model.PropietarioActivo;

public class PropietarioActivoDAOImpl extends BaseDAOImpl<PropietarioActivoDTO> implements PropietarioActivoDAO<PropietarioActivoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<PropietarioActivoDTO> search(FiltroPropietarioActivo filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<PropietarioActivoDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroPropietarioActivo filtro, Long pageSize, Long currentPage) throws Exception {
		return (Long) searchImpl(filtro, true, pageSize, currentPage);
	}
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	private Object searchImpl(FiltroPropietarioActivo filtro,boolean cantidad,Long pageSize, Long currentPage) throws Exception {
		List<PropietarioActivoDTO> list = new ArrayList<PropietarioActivoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select pa from PropietarioActivo pa ";
			if(cantidad){
				query = "select count(*) from PropietarioActivo pa ";
			}

			if (filtro.getId() != null) {
				conditions.add("pa.id = :idPropietarioActivo");
				parameters.put("idPropietarioActivo", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("pa.id != :idPropietarioActivoExcluir");
				parameters.put("idPropietarioActivoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(pa.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(pa.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("pa.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("pa.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("pa.fechaBaja <= :fechaBajaHasta");
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

			List<PropietarioActivo> propietarioActivosFiltrados = consulta.getResultList();
			if(cantidad){
				return (propietarioActivosFiltrados != null ? propietarioActivosFiltrados.get(0):0L);
			}
			if (propietarioActivosFiltrados != null) {
				for (PropietarioActivo propietarioActivo : propietarioActivosFiltrados) {
					list.add(propietarioActivo.crearDTO());
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
	public List<PropietarioActivoDTO> listByName(String nombre) throws Exception {
		List<PropietarioActivoDTO> list = new ArrayList<PropietarioActivoDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select pa from PropietarioActivo pa where UPPER(pa.nombre) = UPPER('" + nombre + "') and pa.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);
			
			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<PropietarioActivo> propietarioActivosFiltrados = consulta.getResultList();
	
			if (propietarioActivosFiltrados != null) {
				for (PropietarioActivo propietarioActivo : propietarioActivosFiltrados) {
					list.add(propietarioActivo.crearDTO());
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
	public PropietarioActivoDTO saveModificacion(PropietarioActivoDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
