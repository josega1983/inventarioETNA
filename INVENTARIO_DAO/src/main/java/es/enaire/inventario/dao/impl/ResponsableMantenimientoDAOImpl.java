package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroResponsableMantenimiento;
import es.enaire.inventario.dao.ResponsableMantenimientoDAO;
import es.enaire.inventario.dtos.ResponsableMantenimientoDTO;
import es.enaire.inventario.model.ResponsableMantenimiento;

public class ResponsableMantenimientoDAOImpl extends BaseDAOImpl<ResponsableMantenimientoDTO> implements ResponsableMantenimientoDAO<ResponsableMantenimientoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ResponsableMantenimientoDTO> search(FiltroResponsableMantenimiento filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<ResponsableMantenimientoDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroResponsableMantenimiento filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroResponsableMantenimiento filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<ResponsableMantenimientoDTO> list = new ArrayList<ResponsableMantenimientoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select rm from ResponsableMantenimiento rm ";
			if(cantidad){
				query = "select count(*) from ResponsableMantenimiento rm ";
			}
			if(filtro != null){

				if (filtro.getId() != null) {
					conditions.add("rm.id = :idResponsableMantenimiento");
					parameters.put("idResponsableMantenimiento", filtro.getId());
				}
				if(filtro.getIdExcluir() != null){
					conditions.add("rm.id != :idResponsableMantenimientoExcluir");
					parameters.put("idResponsableMantenimientoExcluir", filtro.getIdExcluir());
				}
				if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
					conditions.add("UPPER(rm.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
				}
				if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
					conditions.add("UPPER(rm.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
				}
				if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
					conditions.add("rm.activo = :isActivo");
					parameters.put("isActivo", filtro.getActivo());
				}
				if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
					conditions.add("rm.fechaBaja >= :fechaBajaDesde");
					parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
				}
				if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
					conditions.add("rm.fechaBaja <= :fechaBajaHasta");
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
			}

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());

			consulta.setFirstResult((currentPage.intValue() * pageSize.intValue()));
			consulta.setMaxResults(pageSize.intValue());

			List<ResponsableMantenimiento> responsableMantenimientoFiltrados = consulta.getResultList();
			if(cantidad){
				return (responsableMantenimientoFiltrados != null ? responsableMantenimientoFiltrados.get(0):0L);
			}
			if (responsableMantenimientoFiltrados != null) {
				for (ResponsableMantenimiento responsableMantenimiento : responsableMantenimientoFiltrados) {
					list.add(responsableMantenimiento.crearDTO());
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
	public List<ResponsableMantenimientoDTO> listByName(String nombre) throws Exception {
		List<ResponsableMantenimientoDTO> list = new ArrayList<ResponsableMantenimientoDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select rm from ResponsableMantenimiento rm where UPPER(rm.nombre) = UPPER('" + nombre + "') and rm.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<ResponsableMantenimiento> responsableMantenimientoFiltrados = consulta.getResultList();
	
			if (responsableMantenimientoFiltrados != null) {
				for (ResponsableMantenimiento responsableMantenimiento : responsableMantenimientoFiltrados) {
					list.add(responsableMantenimiento.crearDTO());
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
	public ResponsableMantenimientoDTO saveModificacion(
			ResponsableMantenimientoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
