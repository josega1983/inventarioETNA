package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroEstadoElemento;
import es.enaire.inventario.dao.EstadoElementoDAO;
import es.enaire.inventario.dtos.EstadoElementoDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.EstadoElemento;

public class EstadoElementoDAOImpl extends BaseDAOImpl<EstadoElementoDTO>
		implements EstadoElementoDAO<EstadoElementoDTO> {
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	@Override
	public EstadoElementoDTO saveOrUpdate(EstadoElementoDTO dto) throws Exception{
		try {
			EstadoElemento entidad = new EstadoElemento(dto) ;

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
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<EstadoElementoDTO> search(FiltroEstadoElemento filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<EstadoElementoDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroEstadoElemento filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroEstadoElemento filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<EstadoElementoDTO> list = new ArrayList<EstadoElementoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select ee from EstadoElemento ee ";
			if(cantidad){
				query = "select count(*) from EstadoElemento ee ";
			}

			if (filtro.getId() != null) {
				conditions.add("ee.id = :idEstadoElemento");
				parameters.put("idEstadoElemento", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("ee.id != :idEstadoElementoExcluir");
				parameters.put("idEstadoElementoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(ee.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("ee.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(ee.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("ee.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("ee.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
			}
			if(filtro.getFechaAltaDesde() !=null && !filtro.getFechaAltaDesde().equals("") ){
				conditions.add("ee.fechaAlta >= :fechaAltaDesde");
				parameters.put("fechaAltaDesde", filtro.getFechaAltaDesde());
			}
			if(filtro.getFechaAltaHasta() !=null && !filtro.getFechaAltaHasta().equals("") ){
				conditions.add("ee.fechaAlta <= :fechaAltaHasta");
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

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());

			consulta.setFirstResult((currentPage.intValue() * pageSize.intValue()));
			consulta.setMaxResults(pageSize.intValue());

			List<EstadoElemento> estadoElementosFiltrados = consulta.getResultList();
			if(cantidad){
				return (estadoElementosFiltrados != null ? estadoElementosFiltrados.get(0):0L);
			}
			if (estadoElementosFiltrados != null) {
				for (EstadoElemento estadoElemento : estadoElementosFiltrados) {
					list.add(estadoElemento.crearDTO());
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
	public List<EstadoElementoDTO> listByName(String nombre) throws Exception {
		List<EstadoElementoDTO> list = new ArrayList<EstadoElementoDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select ee from EstadoElemento ee where UPPER(ee.nombre) = UPPER('" + nombre + "') and ee.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<EstadoElemento> estadoElementosFiltrados = consulta.getResultList();
	
			if (estadoElementosFiltrados != null) {
				for (EstadoElemento estadoElemento : estadoElementosFiltrados) {
					list.add(estadoElemento.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public EstadoElementoDTO reactivar(Long id) throws Exception{
		try {
			EstadoElemento elemento = (EstadoElemento) entityManager.find(EstadoElemento.class, id);
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
	 * Metodo que nos guarda las modificaciones de una entidad en las tablas de modificacion
	 * @param dto a guardar
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado
	 */
	@Override
	public EstadoElementoDTO saveModificacion(EstadoElementoDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
