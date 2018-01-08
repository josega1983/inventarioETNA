package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroConfiguracionesFamilia;
import es.enaire.inventario.dao.ConfiguracionesFamiliaDAO;
import es.enaire.inventario.dtos.ConfiguracionElementosFamiliaDTO;
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.ConfiguracionElementosFamilia;
import es.enaire.inventario.model.ConfiguracionesFamilia;

public class ConfiguracionesFamiliaDAOImpl extends BaseDAOImpl<ConfiguracionesFamiliaDTO> implements ConfiguracionesFamiliaDAO<ConfiguracionesFamiliaDTO> {
	/**
	 * Obtiene un elemento asociado por su identificador.
	 * @param id El identificador del elemento.
	 * @return El elemento recuperado a traves de su identificador.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	@Override
	public ConfiguracionesFamiliaDTO listById (Long id) throws Exception {
		ConfiguracionesFamiliaDTO resultado = null;
		ConfiguracionesFamilia elemento = (ConfiguracionesFamilia) entityManager.find(ConfiguracionesFamilia.class, id);
		if(elemento != null) {
			resultado = elemento.crearDTO();
			List<ConfiguracionElementosFamiliaDTO> hijos = getHijos(elemento.getId(), true);
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
	private List<ConfiguracionElementosFamiliaDTO> getHijos(Long idPadre, boolean esPrincipal) throws Exception {
		List<ConfiguracionElementosFamiliaDTO> hijos = new ArrayList<ConfiguracionElementosFamiliaDTO>();
		try {
			Query query = entityManager.createQuery("SELECT cef FROM ConfiguracionElementosFamilia cef WHERE cef.configuracionesFamilia.id =:idPadre and cef.configuracionElementosFamiliaPadre.id is null");
			if (!esPrincipal) {
				query = entityManager.createQuery("SELECT cef FROM ConfiguracionElementosFamilia cef WHERE cef.configuracionElementosFamiliaPadre.id =:idPadre");
				query.setParameter("idPadre", idPadre);
			}else {
				query.setParameter("idPadre", idPadre);
			}
			List<ConfiguracionElementosFamilia> list = query.getResultList();
			if (list != null && list.size() > 0) {
				for (ConfiguracionElementosFamilia configuracionElementosFamilia : list) {
					ConfiguracionElementosFamiliaDTO configuracionElementosFamiliaDTO = configuracionElementosFamilia.crearDTO();
					//Para cada elemento de la lista comprobamos si tienen hijos
					configuracionElementosFamiliaDTO.setHijos(getHijos(configuracionElementosFamilia.getId(), false));
					hijos.add(configuracionElementosFamiliaDTO);
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
	public List<ConfiguracionesFamiliaDTO> search(FiltroConfiguracionesFamilia filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<ConfiguracionesFamiliaDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroConfiguracionesFamilia filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroConfiguracionesFamilia filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<ConfiguracionesFamiliaDTO> list = new ArrayList<ConfiguracionesFamiliaDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select cf from ConfiguracionesFamilia cf ";
			if(cantidad){
				query = "select count(*) from ConfiguracionesFamilia cf ";
			}

			if (filtro.getId() != null) {
				conditions.add("cf.id = :idConfiguracionesFamilia");
				parameters.put("idConfiguracionesFamilia", filtro.getId());
			}

			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				if(filtro.getIdExcluir() != null){ //Comprobacion de duplicados en cuyo caso el nombre deberá ser ademas igual
					conditions.add("cf.id != :idConfiguracionesFamiliaExcluir");
					parameters.put("idConfiguracionesFamiliaExcluir", filtro.getIdExcluir());
					conditions.add("cf.nombre = :nombre");
					parameters.put("nombre", filtro.getNombre());
				}else{
					conditions.add("UPPER(cf.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
				}
			}
			if(filtro.getFamiliaInstalacion() != null){
				FamiliaInstalacionDTO familiaInstalacion = filtro.getFamiliaInstalacion();
				if(familiaInstalacion.getId() != null){
					conditions.add("cf.familiaInstalacion.id = :idFamiliaInstalacion");
					parameters.put("idFamiliaInstalacion", familiaInstalacion.getId());
				}
				if(familiaInstalacion.getNombre() != null && familiaInstalacion.getNombre().length() >0 ){
					conditions.add("UPPER(cf.familiaInstalacion.nombre) like UPPER('%" + familiaInstalacion.getNombre() + "' || '%')");
				}
			}
			if (filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0) {
				conditions.add("UPPER(cf.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getFechaAltaDesde() != null){
				conditions.add("cf.fechaAlta >= :fechaAltaDesde");
				parameters.put("fechaAltaDesde", filtro.getFechaAltaDesde());
			}
			if(filtro.getFechaAltaHasta() != null){
				conditions.add("cf.fechaAlta <= :fechaAltaHasta");
				parameters.put("fechaAltaHasta", filtro.getFechaAltaHasta());
			}
			if(filtro.getFechaBajaDesde() != null){
				conditions.add("cf.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() != null){
				conditions.add("cf.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("cf.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
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

			List<ConfiguracionesFamilia> configuracionesFamiliasFiltrados = consulta.getResultList();
			if(cantidad){
				return (configuracionesFamiliasFiltrados != null ? configuracionesFamiliasFiltrados.get(0):0L);
			}
			if (configuracionesFamiliasFiltrados != null) {
				for (ConfiguracionesFamilia configuracionesFamilia : configuracionesFamiliasFiltrados) {
					list.add(configuracionesFamilia.crearDTO());
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
	public ConfiguracionesFamiliaDTO saveOrUpdate(ConfiguracionesFamiliaDTO dto) throws Exception {
		try {
			ConfiguracionesFamilia entidad =  (ConfiguracionesFamilia)EntityFactory.getEntityObjectFromDTO(ConfiguracionesFamilia.class, dto);

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
	 * Metodo que nos guarda las modificaciones de una entidad en las tablas de modificacion
	 * @param dto a guardar
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado
	 */
	@Override
	public ConfiguracionesFamiliaDTO saveModificacion(
			ConfiguracionesFamiliaDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Crea un nodo hijo de configuraciones elements familia
	 * @param dto configuracion elemento familia dto
	 * @return configuracion elemento familia
	 * @throws Exception Excepcion que se puede producir durante el proceso de creaccion y/o actualizacion
	 */
	@Override
	public ConfiguracionesFamiliaDTO saveOrUpdateConfiguracionElementosFamilia(ConfiguracionesFamiliaDTO dto) throws Exception {
		try {
		 List<ConfiguracionElementosFamiliaDTO> hijos = dto.getHijos();
		 if(hijos != null && hijos.size() >0){
			 for (ConfiguracionElementosFamiliaDTO hijo : hijos) {
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
	 * Metodo que  nos guarda un elemtno de Configuracion Elementos Familia a partir de la informacion de su dto y de su padre
	 * @param padre dto del padre
	 * @param hijo dto del hijo
	 * @param configuracionesFamilia con la informacion de la configuracion familia
	 * @throws Exception Excepcion que se puede producir durante el proceso de creaccion y/o actualizacion
	 */
	private void saveElemento(ConfiguracionElementosFamiliaDTO padre, ConfiguracionElementosFamiliaDTO hijo,ConfiguracionesFamiliaDTO configuracionesFamilia) throws Exception {
		ConfiguracionElementosFamilia elemento = new ConfiguracionElementosFamilia(hijo);
		elemento.setConfiguracionesFamilia(new ConfiguracionesFamilia(configuracionesFamilia));
		if(padre != null){
			elemento.setConfiguracionElementosFamiliaPadre(new ConfiguracionElementosFamilia(padre));
		}
		elemento.setId(null);
		elemento.setActivo(SI);
		elemento.setFechaAlta(new Date());
		entityManager.persist(elemento);
		entityManager.flush();
		hijo.setId(elemento.getId());
		List<ConfiguracionElementosFamiliaDTO> hijos = hijo.getHijos();
		if(hijos != null && hijos.size() >0){
			for (ConfiguracionElementosFamiliaDTO configuracionElementosFamilia : hijos) {
				saveElemento(hijo, configuracionElementosFamilia, configuracionesFamilia);
			}
		}
	}
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Override
	public ConfiguracionesFamiliaDTO reactivar(Long id) throws Exception{
		try {
			ConfiguracionesFamilia elemento = (ConfiguracionesFamilia) entityManager.find(ConfiguracionesFamilia.class, id);
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
	 * Elimina fisicamente un registro
	 * @param id Indentificador del registro
	 *  @throws Exception Excepcion que se puede producir durante el proceso de borrado.
	 */
	public void remove(Long id) throws Exception {
		try{
			ConfiguracionesFamiliaDTO configuracionFamilia = listById(id);

			for(ConfiguracionElementosFamiliaDTO elemento: configuracionFamilia.getHijos()) {
				//Borramos los elementos primero de la configuracion de familia.
				ConfiguracionElementosFamilia entidadElemento = entityManager.find(ConfiguracionElementosFamilia.class, elemento.getId());
				entityManager.remove(entidadElemento);
				entityManager.flush();
			}

		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}
	}
}
