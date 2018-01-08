package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroRegion;
import es.enaire.inventario.dao.RegionDAO;
import es.enaire.inventario.dtos.RegionDTO;
import es.enaire.inventario.model.Region;

/**
 * Implementacion del DAO para la gestion de regiones.
 */
public class RegionDAOImpl extends BaseDAOImpl<RegionDTO> implements RegionDAO<RegionDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<RegionDTO> search(FiltroRegion filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<RegionDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroRegion filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroRegion filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select r from Region r ";
			if(cantidad){
				query = "select count(*) from Region r ";
			}
			if(filtro != null){
				if (filtro.getId() != null) {
					conditions.add("r.id = :idRegion");
					parameters.put("idRegion", filtro.getId());
				}
				if(filtro.getIdExcluir() != null){
					conditions.add("r.id != :idRegionExcluir");
					parameters.put("idRegionExcluir", filtro.getIdExcluir());
				}
				if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
					conditions.add("UPPER(r.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
				}
				if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
					conditions.add("UPPER(r.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
				}
				if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
					conditions.add("r.activo = :isActivo");
					parameters.put("isActivo", filtro.getActivo());
				}
				if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
					conditions.add("r.fechaBaja >= :fechaBajaDesde");
					parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
				}
				if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
					conditions.add("r.fechaBaja <= :fechaBajaHasta");
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

			List<Region> regionesFiltrados = consulta.getResultList();
			if(cantidad){
				return (regionesFiltrados != null ? regionesFiltrados.get(0):0L);
			}
			if (regionesFiltrados != null) {
				for (Region region : regionesFiltrados) {
					list.add(region.crearDTO());
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
	public List<RegionDTO> listByName(String nombre) throws Exception {
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select r from Region r where UPPER(r.nombre) = UPPER('" + nombre + "') and r.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<Region> regionesFiltrados = consulta.getResultList();

			if (regionesFiltrados != null) {
				for (Region region : regionesFiltrados) {
					list.add(region.crearDTO());
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
	public RegionDTO saveModificacion(RegionDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
