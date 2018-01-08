package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroTipoInstalacion;
import es.enaire.inventario.dao.TipoInstalacionDAO;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
import es.enaire.inventario.dtos.TipoInstalacionDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.TipoInstalacion;

public class TipoInstalacionDAOImpl extends BaseDAOImpl<TipoInstalacionDTO> implements
TipoInstalacionDAO<TipoInstalacionDTO> {
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	@Override
	public TipoInstalacionDTO saveOrUpdate(TipoInstalacionDTO dto) throws Exception{
		try {
			TipoInstalacion entidad = new TipoInstalacion(dto) ;

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
	public TipoInstalacionDTO reactivar(Long id) throws Exception{
		try {
			TipoInstalacion elemento = (TipoInstalacion) entityManager.find(TipoInstalacion.class, id);
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
	public List<TipoInstalacionDTO> search(FiltroTipoInstalacion filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<TipoInstalacionDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroTipoInstalacion filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroTipoInstalacion filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<TipoInstalacionDTO> list = new ArrayList<TipoInstalacionDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select ti from TipoInstalacion ti ";
			if(cantidad){
				query = "select count(*) from TipoInstalacion ti ";
			}

			if (filtro.getId() != null) {
				conditions.add("ti.id = :idTipoInstalacion");
				parameters.put("idTipoInstalacion", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("ti.id != :idTipoInstalacionExcluir");
				parameters.put("idTipoInstalacionExcluir", filtro.getIdExcluir());
			}
			if(filtro.getMarca() != null && filtro.getMarca().length() > 0){
				conditions.add("UPPER(ti.marca) like UPPER('%" + filtro.getMarca() + "' || '%')");
			}
			if(filtro.getModelo() != null && filtro.getModelo().length() > 0){
				conditions.add("UPPER(ti.modelo) like UPPER('%" + filtro.getModelo() + "' || '%')");
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(ti.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}

			if (filtro.getFamiliaInstalacion() != null) {
				FamiliaInstalacionDTO familiaInstalacion = filtro.getFamiliaInstalacion();
				if(familiaInstalacion.getId() != null){
					conditions.add("ti.familiaInstalacion.id = :idFamiliaInstalacion");
					parameters.put("idFamiliaInstalacion", familiaInstalacion.getId());
				}
				if (familiaInstalacion.getNombre() != null && familiaInstalacion.getNombre().length() > 0) {
					conditions.add("UPPER(ti.familiaInstalacion.nombre) like UPPER('%"+ familiaInstalacion.getNombre() + "' || '%')");
				}
			}
			if(filtro.getAsociadaConfiguracion() != null && filtro.getAsociadaConfiguracion() ){
				conditions.add("ti.id not in (select ct.tipoInstalacion.id from ConfiguracionesTipo ct WHERE ct.activo = :activoCTC)");
				parameters.put("activoCTC", BaseDAOImpl.SI);
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("ti.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("ti.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("ti.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
			}
			if(filtro.getFechaAltaDesde() !=null && !filtro.getFechaAltaDesde().equals("") ){
				conditions.add("ti.fechaAlta >= :fechaAltaDesde");
				parameters.put("fechaAltaDesde", filtro.getFechaAltaDesde());
			}
			if(filtro.getFechaAltaHasta() !=null && !filtro.getFechaAltaHasta().equals("") ){
				conditions.add("ti.fechaAlta <= :fechaAltaHasta");
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
				query += "  ORDER BY ti.familiaInstalacion.nombre ASC, ti.familiaInstalacion.id DESC ";
			}

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());

			consulta.setFirstResult((currentPage.intValue() * pageSize.intValue()));
			consulta.setMaxResults(pageSize.intValue());

			List<TipoInstalacion> tipoInstalacionesFiltrados = consulta.getResultList();
			if(cantidad){
				return (tipoInstalacionesFiltrados != null ? tipoInstalacionesFiltrados.get(0):0L);
			}
			if (tipoInstalacionesFiltrados != null) {
				for (TipoInstalacion tipoInstalacion : tipoInstalacionesFiltrados) {
					list.add(tipoInstalacion.crearDTO());
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
	public TipoInstalacionDTO saveModificacion(TipoInstalacionDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
