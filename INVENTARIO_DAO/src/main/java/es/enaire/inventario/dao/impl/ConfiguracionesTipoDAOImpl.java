package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroConfiguracionesTipo;
import es.enaire.inventario.dao.ConfiguracionesTipoDAO;
import es.enaire.inventario.dtos.ConfiguracionElementosTipoDTO;
import es.enaire.inventario.dtos.ConfiguracionesTipoDTO;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
import es.enaire.inventario.dtos.TipoInstalacionDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.ConfiguracionElementosTipo;
import es.enaire.inventario.model.ConfiguracionesTipo;

public class ConfiguracionesTipoDAOImpl extends BaseDAOImpl<ConfiguracionesTipoDTO> implements ConfiguracionesTipoDAO<ConfiguracionesTipoDTO> {
	/**
	 * Obtiene un elemento asociado por su identificador.
	 * @param id El identificador del elemento.
	 * @return El elemento recuperado a traves de su identificador.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	@Override
	public ConfiguracionesTipoDTO listById (Long id) throws Exception {
		ConfiguracionesTipoDTO resultado = null;
		ConfiguracionesTipo elemento = (ConfiguracionesTipo) entityManager.find(ConfiguracionesTipo.class, id);
		if(elemento != null) {
			resultado = elemento.crearDTO();
			List<ConfiguracionElementosTipoDTO> hijos = getHijos(elemento.getId(), true);
			resultado.setHijos(hijos);
		}

		return resultado;
	}
	/**
	 * Metodo que la lista de hijos de un id del padre
	 * @param idPadre del padre
	 * @param esPrincipal nos indica si es un nodo principal
	 * @return listado de hijos
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */

	@SuppressWarnings("unchecked")
	private List<ConfiguracionElementosTipoDTO> getHijos(Long idPadre, boolean esPrincipal) throws Exception {
		List<ConfiguracionElementosTipoDTO> hijos = new ArrayList<ConfiguracionElementosTipoDTO>();
		try {
			Query query = entityManager.createQuery("SELECT cet FROM ConfiguracionElementosTipo cet WHERE cet.configuracionesTipo.id =:idPadre and cet.configuracionElementosTipoPadre.id is null");
			if (!esPrincipal) {
				query = entityManager.createQuery("SELECT cet FROM ConfiguracionElementosTipo cet WHERE cet.configuracionElementosTipoPadre.id =:idPadre");
				query.setParameter("idPadre", idPadre);
			}else {
				query.setParameter("idPadre", idPadre);
			}
			List<ConfiguracionElementosTipo> list = query.getResultList();
			if (list != null && list.size() > 0) {
				for (ConfiguracionElementosTipo configuracionElementosTipo : list) {
					ConfiguracionElementosTipoDTO configuracionElementosTipoDTO = configuracionElementosTipo.crearDTO();
					//Para cada elemento de la lista comprobamos si tienen hijos
					configuracionElementosTipoDTO.setHijos(getHijos(configuracionElementosTipo.getId(), false));
					hijos.add(configuracionElementosTipoDTO);
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return hijos;
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
	public List<ConfiguracionesTipoDTO> search(FiltroConfiguracionesTipo filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<ConfiguracionesTipoDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroConfiguracionesTipo filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroConfiguracionesTipo filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<ConfiguracionesTipoDTO> list = new ArrayList<ConfiguracionesTipoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select ct from ConfiguracionesTipo ct ";
			if(cantidad){
				query = "select count(*) from ConfiguracionesTipo ct ";
			}

			if (filtro.getId() != null) {
				conditions.add("ct.id = :idConfiguracionesTipo");
				parameters.put("idConfiguracionesTipo", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("ct.id != :idConfiguracionesTipoExcluir");
				parameters.put("idConfiguracionesTipoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(ct.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(ct.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getTipoInstalacion() != null){
				TipoInstalacionDTO tipoInstalacion = filtro.getTipoInstalacion();
				if(tipoInstalacion.getId() != null){
					conditions.add("ct.tipoInstalacion.id = :idTipoInstalacion");
					parameters.put("idTipoInstalacion", tipoInstalacion.getId());
				}
				if(tipoInstalacion.getFamiliaInstalacion() != null) {
					FamiliaInstalacionDTO familiaInstalacion = tipoInstalacion.getFamiliaInstalacion();
					if(familiaInstalacion.getId() != null) {
						conditions.add("ct.tipoInstalacion.familiaInstalacion.id = :idFamiliaInstalacion");
						parameters.put("idFamiliaInstalacion", familiaInstalacion.getId());
					}
				}
				if(tipoInstalacion.getMarca() != null) {
					conditions.add("UPPER(ct.tipoInstalacion.marca) like UPPER('%" + tipoInstalacion.getMarca() + "' || '%')");
				}
				if(tipoInstalacion.getModelo() != null) {
					conditions.add("UPPER(ct.tipoInstalacion.modelo) like UPPER('%" + tipoInstalacion.getModelo() + "' || '%')");
				}
			}
			if(filtro.getFamiliaInstalacion() != null){
				FamiliaInstalacionDTO familiaInstalacion = filtro.getFamiliaInstalacion();
				if(familiaInstalacion.getId() != null) {
					conditions.add("ct.tipoInstalacion.familiaInstalacion.id = :idFamiliaInstalacion");
					parameters.put("idFamiliaInstalacion", familiaInstalacion.getId());
				}
			}
			if(filtro.getFechaAltaDesde() != null){
				conditions.add("ct.fechaAlta >= :fechaAltaDesde");
				parameters.put("fechaAltaDesde", filtro.getFechaAltaDesde());
			}
			if(filtro.getFechaAltaHasta() != null){
				conditions.add("ct.fechaAlta <= :fechaAltaHasta");
				parameters.put("fechaAltaHasta", filtro.getFechaAltaHasta());
			}
			if(filtro.getFechaBajaDesde() != null){
				conditions.add("ct.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() != null){
				conditions.add("ct.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("ct.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getActivo() != null && filtro.getActivo().equals(BaseDAOImpl.SI)){
				conditions.add("ct.activo ='"+BaseDAOImpl.SI+"'");
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

			List<ConfiguracionesTipo> configuracionesTiposFiltrados = consulta.getResultList();
			if(cantidad){
				return (configuracionesTiposFiltrados != null ? configuracionesTiposFiltrados.get(0):0L);
			}
			if (configuracionesTiposFiltrados != null) {
				for (ConfiguracionesTipo configuracionesTipo : configuracionesTiposFiltrados) {
					list.add(configuracionesTipo.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	@Override
	public ConfiguracionesTipoDTO saveOrUpdate(ConfiguracionesTipoDTO dto) throws Exception {
		try {
			ConfiguracionesTipo entidad =  (ConfiguracionesTipo)EntityFactory.getEntityObjectFromDTO(ConfiguracionesTipo.class, dto);
			//TODO:Se tendra que quitar cuando se elimine el campo nombre de la base de datos
			entidad.setNombre(dto.getTipoInstalacion().getFamiliaInstalacion().getNombre());
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
	}
	/**
	 * Crea un nodo hijo de configuraciones elements familia
	 * @param dto configuracion elemento familia dto
	 * @return configuracion elemento familia
	 * @throws Exception Excepcion que se puede producir durante el proceso de creaccion y/o actualizacion
	 */
	public ConfiguracionesTipoDTO saveOrUpdateConfiguracionElementosTipo(ConfiguracionesTipoDTO dto) throws Exception {
		try {
		 List<ConfiguracionElementosTipoDTO> hijos = dto.getHijos();
		 if(hijos != null && hijos.size() >0){
			 for (ConfiguracionElementosTipoDTO hijo : hijos) {
				 saveElemento(null,hijo, dto);
			}
		 }

		}  catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}

		return dto;
	}
	/**
	 * Metodo que  nos guarda un elemtno de Configuracion Elementos Tipo a partir de la informacion de su dto y de su padre
	 * @param padre dto del padre
	 * @param hijo dto del hijo
	 * @param configuracionesTipo con la informacion de la configuracion familia
	 * @throws Exception Excepcion que se puede producir durante el proceso de creaccion y/o actualizacion
	 */
	private void saveElemento(ConfiguracionElementosTipoDTO padre, ConfiguracionElementosTipoDTO hijo,ConfiguracionesTipoDTO configuracionesTipo) throws Exception {
		ConfiguracionElementosTipo elemento = new ConfiguracionElementosTipo(hijo);
		elemento.setConfiguracionesTipo(new ConfiguracionesTipo(configuracionesTipo));
		if(padre != null){
			elemento.setConfiguracionElementosTipoPadre(new ConfiguracionElementosTipo(padre));
		}
		elemento.setId(null);
		elemento.setActivo(SI);
		elemento.setFechaAlta(new Date());
		entityManager.persist(elemento);
		entityManager.flush();
		hijo.setId(elemento.getId());
		List<ConfiguracionElementosTipoDTO> hijos = hijo.getHijos();
		if(hijos != null && hijos.size() >0){
			for (ConfiguracionElementosTipoDTO configuracionElementosTipo : hijos) {
				saveElemento(hijo, configuracionElementosTipo, configuracionesTipo);
			}
		}
	}

	/**
	 * Elimina fisicamente un registro
	 * @param id Indentificador del registro
	 *  @throws Exception Excepcion que se puede producir durante el proceso de borrado.
	 */
	public void remove(Long id) throws Exception {
		try{
			ConfiguracionesTipoDTO configuracionTipo = listById(id);

			for(ConfiguracionElementosTipoDTO elemento: configuracionTipo.getHijos()) {
				//Borramos los elementos primero de la configuracion de familia.
				ConfiguracionElementosTipo entidadElemento = entityManager.find(ConfiguracionElementosTipo.class, elemento.getId());
				entityManager.remove(entidadElemento);
				entityManager.flush();
			}

		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}
	}
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public ConfiguracionesTipoDTO reactivar(Long id) throws Exception{
		try {
			ConfiguracionesTipo elemento = (ConfiguracionesTipo) entityManager.find(ConfiguracionesTipo.class, id);
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
	public ConfiguracionesTipoDTO saveModificacion(ConfiguracionesTipoDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
