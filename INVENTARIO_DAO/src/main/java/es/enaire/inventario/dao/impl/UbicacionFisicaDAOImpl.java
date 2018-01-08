package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroUbicacionFisica;
import es.enaire.inventario.dao.UbicacionFisicaDAO;
import es.enaire.inventario.dtos.EmplazamientoDTO;
import es.enaire.inventario.dtos.LocalizacionDTO;
import es.enaire.inventario.dtos.RegionDTO;
import es.enaire.inventario.dtos.UbicacionFisicaDTO;
import es.enaire.inventario.model.UbicacionFisica;

public class UbicacionFisicaDAOImpl extends BaseDAOImpl<UbicacionFisicaDTO> implements
UbicacionFisicaDAO<UbicacionFisicaDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<UbicacionFisicaDTO> search(FiltroUbicacionFisica filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<UbicacionFisicaDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroUbicacionFisica filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroUbicacionFisica filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<UbicacionFisicaDTO> list = new ArrayList<UbicacionFisicaDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select uf from UbicacionFisica uf ";
			if(cantidad){
				query = "select count(*) from UbicacionFisica uf ";
			}

			if (filtro.getId() != null) {
				conditions.add("uf.id = :idUbicacionFisica");
				parameters.put("idUbicacionFisica", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("uf.id != :idUbicacionFisicaExcluir");
				parameters.put("idUbicacionFisicaExcluir", filtro.getIdExcluir());
			}
			if(filtro.getRegion() != null){
				RegionDTO region = filtro.getRegion();
				if(region.getId() != null){
					conditions.add("uf.region.id = :idRegion");
					parameters.put("idRegion", region.getId());
				}
				if( region.getNombre() != null && region.getNombre().length() > 0){
					conditions.add("UPPER(uf.region.nombre) like UPPER('%" + region.getNombre() + "' || '%')");
				}
			}

			if(filtro.getLocalizacion() != null){
				LocalizacionDTO localizacion = filtro.getLocalizacion();
				if(localizacion.getId() != null){
					conditions.add("uf.localizacion.id = :idLocalizacion");
					parameters.put("idLocalizacion", localizacion.getId());
				}
				if(localizacion.getNombre() != null && localizacion.getNombre().length() > 0){
					conditions.add("UPPER(uf.localizacion.nombre) like UPPER('%" + localizacion.getNombre() + "' || '%')");
				}
			}
			if(filtro.getEmplazamiento() != null){
				EmplazamientoDTO emplazamiento = filtro.getEmplazamiento();
				if(emplazamiento.getId() != null){
					conditions.add("uf.emplazamiento.id = :idEmplazamiento");
					parameters.put("idEmplazamiento", emplazamiento.getId());
				}
				if(emplazamiento.getNombre() != null && emplazamiento.getNombre().length() > 0){
					conditions.add("UPPER(uf.emplazamiento.nombre) like UPPER('%" + emplazamiento.getNombre() + "' || '%')");
				}
			}

			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("uf.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(uf.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("uf.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("uf.fechaBaja <= :fechaBajaHasta");
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

			List<UbicacionFisica> ubicacionFisicasFiltrados = consulta.getResultList();
			if(cantidad){
				return (ubicacionFisicasFiltrados != null ? ubicacionFisicasFiltrados.get(0):0L);
			}
			if (ubicacionFisicasFiltrados != null) {
				for (UbicacionFisica ubicacionFisica : ubicacionFisicasFiltrados) {
					list.add(ubicacionFisica.crearDTO());
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
	public List<UbicacionFisicaDTO> listByName(String nombre) throws Exception {
		List<UbicacionFisicaDTO> list = new ArrayList<UbicacionFisicaDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select uf from UbicacionFisica uf where UPPER(uf.nombre) = UPPER('" + nombre + "') and uf.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<UbicacionFisica> ubicacionFisicasFiltrados = consulta.getResultList();
	
			if (ubicacionFisicasFiltrados != null) {
				for (UbicacionFisica ubicacionFisica : ubicacionFisicasFiltrados) {
					list.add(ubicacionFisica.crearDTO());
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
	public UbicacionFisicaDTO saveModificacion(UbicacionFisicaDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
