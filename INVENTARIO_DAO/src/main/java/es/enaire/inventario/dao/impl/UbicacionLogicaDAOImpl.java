package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroUbicacionLogica;
import es.enaire.inventario.dao.UbicacionLogicaDAO;
import es.enaire.inventario.dtos.CentroDTO;
import es.enaire.inventario.dtos.RegionDTO;
import es.enaire.inventario.dtos.SectorDTO;
import es.enaire.inventario.dtos.UbicacionLogicaDTO;
import es.enaire.inventario.dtos.UnidadMantenimientoDTO;
import es.enaire.inventario.model.UbicacionLogica;

public class UbicacionLogicaDAOImpl extends BaseDAOImpl<UbicacionLogicaDTO> implements
UbicacionLogicaDAO<UbicacionLogicaDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<UbicacionLogicaDTO> search(FiltroUbicacionLogica filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<UbicacionLogicaDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroUbicacionLogica filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroUbicacionLogica filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<UbicacionLogicaDTO> list = new ArrayList<UbicacionLogicaDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select ul from UbicacionLogica ul ";
			if(cantidad){
				query = "select count(*) from UbicacionLogica ul ";
			}

			if (filtro.getId() != null) {
				conditions.add("ul.id = :idUbicacionLogica");
				parameters.put("idUbicacionLogica", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("ul.id != :idUbicacionLogicaExcluir");
				parameters.put("idUbicacionLogicaExcluir", filtro.getIdExcluir());
			}
			if (filtro.getRegion() != null){
				RegionDTO region = filtro.getRegion();
				if(region.getId() != null){
					conditions.add("ul.region.id = :idRegion");
					parameters.put("idRegion", region.getId());
				}
				if( region.getNombre() != null && region.getNombre().length() > 0){
					conditions.add("UPPER(ul.region.nombre) like UPPER('%" + region.getNombre() + "' || '%')");
				}
			}
			if(filtro.getSector() != null){
				SectorDTO  sector = filtro.getSector();
				if(sector.getId() != null){
					conditions.add("ul.sector.id = :idSector");
					parameters.put("idSector", sector.getId());
				}
				if(sector.getNombre() != null && sector.getNombre().length() > 0){
					conditions.add("UPPER(ul.sector.nombre) like UPPER('%" + sector.getNombre() + "' || '%')");
				}
			}

			if(filtro.getCentro() != null){
				CentroDTO  centro = filtro.getCentro();
				if(centro.getId() != null){
					conditions.add("ul.centro.id = :idCentro");
					parameters.put("idCentro", centro.getId());
				}
				if(centro.getNombre() != null && centro.getNombre().length() > 0){
					conditions.add("UPPER(ul.centro.nombre) like UPPER('%" + centro.getNombre() + "' || '%')");
				}
			}
			if(filtro.getUnidadMantenimiento() != null){
				UnidadMantenimientoDTO  unidadMantenimiento = filtro.getUnidadMantenimiento();
				if(unidadMantenimiento.getId() != null){
					conditions.add("ul.unidadMantenimiento.id = :idUnidadMantenimiento");
					parameters.put("idUnidadMantenimiento", unidadMantenimiento.getId());
				}
				if(unidadMantenimiento.getNombre() != null && unidadMantenimiento.getNombre().length() > 0){
					conditions.add("UPPER(ul.unidadMantenimiento.nombre) like UPPER('%" + unidadMantenimiento.getNombre() + "' || '%')");
				}
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(ul.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("ul.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("ul.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("ul.fechaBaja <= :fechaBajaHasta");
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

			List<UbicacionLogica> ubicacionLogicasFiltrados = consulta.getResultList();
			if(cantidad){
				return (ubicacionLogicasFiltrados != null ? ubicacionLogicasFiltrados.get(0):0L);
			}
			if (ubicacionLogicasFiltrados != null) {
				for (UbicacionLogica ubicacionLogica : ubicacionLogicasFiltrados) {
					list.add(ubicacionLogica.crearDTO());
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
	public UbicacionLogicaDTO saveModificacion(UbicacionLogicaDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
