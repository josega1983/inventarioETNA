package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroParametroFuncional;
import es.enaire.inventario.dao.ParametroFuncionalDAO;
import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;
import es.enaire.inventario.dtos.ParametroFuncionalDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.ConfiguracionParametroFuncional;
import es.enaire.inventario.model.ParametroFuncional;

public class ParametroFuncionalDAOImpl extends BaseDAOImpl<ParametroFuncionalDTO> implements ParametroFuncionalDAO<ParametroFuncionalDTO> {
	/**
	 * Obtiene un elemento asociado por su identificador.
	 * @param id El identificador del elemento.
	 * @return El elemento recuperado a traves de su identificador.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	public ParametroFuncionalDTO listById (Long id) throws Exception {
		ParametroFuncionalDTO resultado = null;
		ParametroFuncional elemento = (ParametroFuncional) entityManager.find(ParametroFuncional.class, id);
		if(elemento != null) {
			resultado = elemento.crearDTO();
			resultado.setConfiguracionParametroFuncionalList(getCampos(id));
		}
		return resultado;
	}
	/**
	 * Lista los resultados del objeto a devolver
	 * @param paginaActual Pagina de la base de datos a devolver
	 * @param tamanioPagina Tamano de resultados dentro de una pagina
	 * @return La lista de elementos a devolver
	 */
	@SuppressWarnings("unchecked")
	public List<ParametroFuncionalDTO> list(Long paginaActual, Long tamanioPagina){
		Query consulta = entityManager.createQuery("from  ParametroFuncional" );
		if(tamanioPagina != null && tamanioPagina > 0 && paginaActual != null){
			consulta.setFirstResult((paginaActual.intValue() * tamanioPagina.intValue()));
			consulta.setMaxResults(tamanioPagina.intValue());
		}

		List<ParametroFuncional> queryList = consulta.getResultList();
		if(queryList == null){
			return null;
		}
		List<ParametroFuncionalDTO> list= new ArrayList<ParametroFuncionalDTO>();
		for(ParametroFuncional elemento: queryList){
			ParametroFuncionalDTO parametroFuncional = elemento.crearDTO();
			//Para cada miembro obtenemos su lista de campos
			parametroFuncional.setConfiguracionParametroFuncionalList(getCampos(elemento.getId()));
			list.add(parametroFuncional);
		}
		 return list;
	}
	/**
	 * Metodo que obtiene una lista de configuraciones de parametro funcional a partir de un id de parametro funcional
	 * @param idParametroFuncional a buscar
	 * @return lista de configuraciones de parametro funcional
	 */
	@SuppressWarnings("unchecked")
	private List<ConfiguracionParametroFuncionalDTO> getCampos(Long idParametroFuncional) {
		List<ConfiguracionParametroFuncionalDTO> list = new ArrayList<ConfiguracionParametroFuncionalDTO>();
		Query query = entityManager.createQuery("SELECT cpf from ConfiguracionParametroFuncional cpf where cpf.parametroFuncional.id = :idParametroFuncional");
		query.setParameter("idParametroFuncional", idParametroFuncional);
		List<ConfiguracionParametroFuncional> queryList = query.getResultList();
		if(queryList == null){
			return null;
		}
		for (ConfiguracionParametroFuncional configuracionParametroFuncional : queryList) {
			list.add(configuracionParametroFuncional.crearDTO());
		}
		return list;
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
	public List<ParametroFuncionalDTO> search(FiltroParametroFuncional filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<ParametroFuncionalDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroParametroFuncional filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroParametroFuncional filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<ParametroFuncionalDTO> list = new ArrayList<ParametroFuncionalDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select pf from ParametroFuncional pf ";
			if(cantidad){
				query = "select count(*) from ParametroFuncional pf ";
			}

			if (filtro.getId() != null) {
				conditions.add("pf.id = :idParametroFuncional");
				parameters.put("idParametroFuncional", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("pf.id != :idParametroFuncionalExcluir");
				parameters.put("idParametroFuncionalExcluir", filtro.getIdExcluir());
			}
			if(filtro.getDescripcion() != null && filtro.getDescripcion().length() >0){
				conditions.add("UPPER(pf.descripcion) like UPPER('%" + filtro.getDescripcion() + "' || '%')");
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(pf.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("pf.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("pf.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("pf.fechaBaja <= :fechaBajaHasta");
				parameters.put("fechaBajaHasta", filtro.getFechaBajaHasta());
			}
			if(filtro.getFechaAltaDesde() !=null && !filtro.getFechaAltaDesde().equals("") ){
				conditions.add("pf.fechaAlta >= :fechaAltaDesde");
				parameters.put("fechaAltaDesde", filtro.getFechaAltaDesde());
			}
			if(filtro.getFechaAltaHasta() !=null && !filtro.getFechaAltaHasta().equals("") ){
				conditions.add("pf.fechaAlta <= :fechaAltaHasta");
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

			List<ParametroFuncional> parametroFuncionalesFiltrados = consulta.getResultList();
			if(cantidad){
				return (parametroFuncionalesFiltrados != null ? parametroFuncionalesFiltrados.get(0):0L);
			}
			if (parametroFuncionalesFiltrados != null) {
				for (ParametroFuncional parametroFuncional : parametroFuncionalesFiltrados) {
					ParametroFuncionalDTO parametroFuncionalDTO = parametroFuncional.crearDTO();
					parametroFuncionalDTO.setConfiguracionParametroFuncionalList(getCampos(parametroFuncional.getId()));
					list.add(parametroFuncionalDTO);
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
	public List<ParametroFuncionalDTO> listByName(String nombre) throws Exception {
		List<ParametroFuncionalDTO> list = new ArrayList<ParametroFuncionalDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select pf from ParametroFuncional pf where UPPER(pf.nombre) = UPPER('" + nombre + "') and pf.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<ParametroFuncional> parametroFuncionalesFiltrados = consulta.getResultList();
	
			if (parametroFuncionalesFiltrados != null) {
				for (ParametroFuncional parametroFuncional : parametroFuncionalesFiltrados) {
					ParametroFuncionalDTO parametroFuncionalDTO = parametroFuncional.crearDTO();
					parametroFuncionalDTO.setConfiguracionParametroFuncionalList(getCampos(parametroFuncional.getId()));
					list.add(parametroFuncionalDTO);
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
	public ParametroFuncionalDTO saveOrUpdate(ParametroFuncionalDTO dto) throws Exception {
		try {
			ParametroFuncional entidad =  (ParametroFuncional)EntityFactory.getEntityObjectFromDTO(ParametroFuncional.class, dto);

			if(EntityFactory.getIdDTOValue(dto) != null) {
				//Es una actualizacion.
				//Eliminamos la lista de campos parametros funcionales que teniamos en la base de datos
				deleteCamposParametrosFuncionalesByIdParametroFuncional(dto.getId());
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
			saveCamposParametrosFuncionales(dto);
			return dto;
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}
	}
	/**
	 * Metodo que nos elimina todos los registros de la tabla campos parametros funcionales por id de parametro funcional
	 * @param idParametroFuncional por el que vamos a borrar
	 * @throws Exception Excepecion que se pueda producir durante el elimina
	 */
	private void deleteCamposParametrosFuncionalesByIdParametroFuncional(Long idParametroFuncional) throws Exception {
		Query delete = entityManager.createQuery("DELETE FROM ConfiguracionParametroFuncional cpf WHERE cpf.parametroFuncional.id = :idParametroFuncional");
		delete.setParameter("idParametroFuncional", idParametroFuncional);
		delete.executeUpdate();
	}
	/**
	 * Metodo que nos guarda en la tabla campos_parametros_funcionales  todas los campos parametros funcionales de un parametro funcional dado
	 * @param dto parametro funcional
	 * @throws Exception Excepcion producida en el proceso de guardado
	 */
	private void saveCamposParametrosFuncionales(ParametroFuncionalDTO dto) throws Exception {
		List<ConfiguracionParametroFuncionalDTO> camposParametroFuncionalList = dto.getConfiguracionParametroFuncionalList();
		if(camposParametroFuncionalList != null && camposParametroFuncionalList.size() >0){
			for (ConfiguracionParametroFuncionalDTO campoParametroFuncionalDTO : camposParametroFuncionalList) {
				ConfiguracionParametroFuncional campoParametroFuncional = new ConfiguracionParametroFuncional(campoParametroFuncionalDTO);
				campoParametroFuncional.setParametroFuncional(new ParametroFuncional(dto));
				entityManager.persist(campoParametroFuncional);
				entityManager.flush();
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
	public ParametroFuncionalDTO reactivar(Long id) throws Exception{
		try {
			ParametroFuncional elemento = (ParametroFuncional) entityManager.find(ParametroFuncional.class, id);
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
	public ParametroFuncionalDTO saveModificacion(ParametroFuncionalDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
