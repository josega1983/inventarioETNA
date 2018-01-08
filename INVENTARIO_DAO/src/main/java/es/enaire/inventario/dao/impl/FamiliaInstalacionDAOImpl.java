package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroFamiliaInstalacion;
import es.enaire.inventario.dao.FamiliaInstalacionDAO;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.FamiliaInstalacion;

public class FamiliaInstalacionDAOImpl extends
		BaseDAOImpl<FamiliaInstalacionDTO> implements
		FamiliaInstalacionDAO<FamiliaInstalacionDTO> {
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	@Override
	public FamiliaInstalacionDTO saveOrUpdate(FamiliaInstalacionDTO dto) throws Exception{
		try {
			FamiliaInstalacion entidad = new FamiliaInstalacion(dto) ;

			if(EntityFactory.getIdDTOValue(dto) != null) {
				//Es una actualizacion.
				entityManager.merge(entidad);
				entityManager.flush();
			}
			else {
				//Es un insert.
				entidad.setActivo(SI);
				entidad.setFechaAlta(new Date());
				entityManager.persist(entidad);
				entityManager.flush();
				EntityFactory.setIdDTOFromIdEntity(dto, entidad);
			}

			return dto;
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}
	};
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public FamiliaInstalacionDTO reactivar(Long id) throws Exception{
		try {
			FamiliaInstalacion elemento = (FamiliaInstalacion) entityManager.find(FamiliaInstalacion.class, id);
			if(elemento != null) {
				elemento.setActivo(SI);
				elemento.setFechaAlta(new Date());
				elemento.setFechaBaja(null);
				entityManager.persist(elemento);
				entityManager.flush();
			}

			return elemento.crearDTO();
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}
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
	public List<FamiliaInstalacionDTO> search(FiltroFamiliaInstalacion filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<FamiliaInstalacionDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroFamiliaInstalacion filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroFamiliaInstalacion filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<FamiliaInstalacionDTO> list = new ArrayList<FamiliaInstalacionDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select fi from FamiliaInstalacion as fi ";
			if(cantidad){
				query = "select count(*) from FamiliaInstalacion as fi ";
			}

			if (filtro.getId() != null) {
				conditions.add("fi.id = :idFamiliaInstalacion");
				parameters.put("idFamiliaInstalacion", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("fi.id != :idFamiliaInstalacionExcluir");
				parameters.put("idFamiliaInstalacionExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(fi.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getArea() != null && filtro.getArea().length() > 0){
				conditions.add("UPPER(fi.area) like UPPER('%" + filtro.getArea() + "' || '%')");
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(fi.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getAsociadaConfiguracion() != null && filtro.getAsociadaConfiguracion() ){
				conditions.add("fi.id not in (select cf.familiaInstalacion.id from ConfiguracionesFamilia as cf where fi.activo = :isConfiguracionActivo)");
				parameters.put("isConfiguracionActivo", BaseDAOImpl.SI);
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("fi.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("fi.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("fi.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
			}
			if(filtro.getFechaAltaDesde() !=null && !filtro.getFechaAltaDesde().equals("") ){
				conditions.add("fi.fechaAlta >= :fechaAltaDesde");
				parameters.put("fechaAltaDesde", filtro.getFechaAltaDesde());
			}
			if(filtro.getFechaAltaHasta() !=null && !filtro.getFechaAltaHasta().equals("") ){
				conditions.add("fi.fechaAlta <= :fechaAltaHasta");
				parameters.put("fechaAltaHasta", filtro.getFechaAltaHasta());
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

			if(filtro.isOrdenarAlfabeticamenteNombre()) {
				query += "  ORDER BY fi.nombre ASC, fi.id DESC ";
			}

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());

			consulta.setFirstResult((currentPage.intValue() * pageSize.intValue()));
			consulta.setMaxResults(pageSize.intValue());

			List<FamiliaInstalacion> familiaInstalacionesFiltrados = consulta.getResultList();
			if(cantidad){
				return (familiaInstalacionesFiltrados != null ? familiaInstalacionesFiltrados.get(0):0L);
			}
			if (familiaInstalacionesFiltrados != null) {
				for (FamiliaInstalacion familiaInstalacion : familiaInstalacionesFiltrados) {
					list.add(familiaInstalacion.crearDTO());
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
	public List<FamiliaInstalacionDTO> listByName(String nombre) throws Exception {
		List<FamiliaInstalacionDTO> list = new ArrayList<FamiliaInstalacionDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select fi from FamiliaInstalacion as fi where UPPER(fi.nombre) = UPPER('" + nombre + "') and fi.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<FamiliaInstalacion> familiaInstalacionesFiltrados = consulta.getResultList();
	
			if (familiaInstalacionesFiltrados != null) {
				for (FamiliaInstalacion familiaInstalacion : familiaInstalacionesFiltrados) {
					list.add(familiaInstalacion.crearDTO());
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
	public FamiliaInstalacionDTO saveModificacion(FamiliaInstalacionDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
