package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroSector;
import es.enaire.inventario.dao.SectorDAO;
import es.enaire.inventario.dtos.SectorDTO;
import es.enaire.inventario.model.Sector;

public class SectorDAOImpl extends BaseDAOImpl<SectorDTO> implements
		SectorDAO<SectorDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<SectorDTO> search(FiltroSector filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<SectorDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroSector filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroSector filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<SectorDTO> list = new ArrayList<SectorDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select s from Sector s ";
			if(cantidad){
				query = "select count(*) from Sector s ";
			}
			if(filtro != null){
				if (filtro.getId() != null) {
					conditions.add("s.id = :idSector");
					parameters.put("idSector", filtro.getId());
				}
				if(filtro.getIdExcluir() != null){
					conditions.add("s.id != :idSectorExcluir");
					parameters.put("idSectorExcluir", filtro.getIdExcluir());
				}
				if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
					conditions.add("UPPER(s.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
				}
				if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
					conditions.add("UPPER(s.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
				}
				if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
					conditions.add("s.activo = :isActivo");
					parameters.put("isActivo", filtro.getActivo());
				}
				if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
					conditions.add("s.fechaBaja >= :fechaBajaDesde");
					parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
				}
				if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
					conditions.add("s.fechaBaja <= :fechaBajaHasta");
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

			List<Sector> sectoresFiltrados = consulta.getResultList();
			if(cantidad){
				return (sectoresFiltrados != null ? sectoresFiltrados.get(0):0L);
			}
			if (sectoresFiltrados != null) {
				for (Sector sector : sectoresFiltrados) {
					list.add(sector.crearDTO());
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
	public List<SectorDTO> listByName(String nombre) throws Exception {
		List<SectorDTO> list = new ArrayList<SectorDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select s from Sector s where UPPER(s.nombre) = UPPER('" + nombre + "') and s.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<Sector> sectoresFiltrados = consulta.getResultList();

			if (sectoresFiltrados != null) {
				for (Sector sector : sectoresFiltrados) {
					list.add(sector.crearDTO());
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
	public SectorDTO saveModificacion(SectorDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
