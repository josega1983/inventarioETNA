package es.enaire.inventario.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.enaire.inventario.business.BaseFiltro;
import es.enaire.inventario.dao.IBaseDAO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.IBaseEntity;

/**
 * Clase que almacena la informacion del entity manager para poder realizar las operaciones del DAO.
 * @param <T>
 * @param <T>
 *
 */
public abstract class BaseDAOImpl<T> implements IBaseDAO<T>{

	/**
	 * Indicador para almacenar en las entidades el valor TRUE de los campos booleanos de la entidad. 
	 */
	public final static String SI = "SI";
	
	/**
	 * Indicador para almacenar en las entidades el valor FALSE de los campos booleanos de la entidad.
	 */
	public final static String NO = "NO";
	
	
	/**
	 * Logger de INTRA.
	 */
	final static Log logger = LogFactory.getLog("INTRA");


	/**
	 * El entityManager para la realizacion de las operaciones con la base de datos.
	 */
	@PersistenceContext(unitName="inventarioDB", type=PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;

	/**
	 * La clase de la entidad que representa el DAO para trabajar con su correspondiente tabla de mapeo.
	 */
	@SuppressWarnings("rawtypes")
	private Class<? extends IBaseEntity> claseEntidad;

	/**
	 * Constructor del BaseDAO.
	 */
	public BaseDAOImpl() {
		claseEntidad = EntityFactory.getEntityClassFromDTO(getDTOClass());
	}

	/**
	 * Obtiene la clase del DTO que representa el DAO.
	 * @return La clase del DTO asociado al DAO.
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getDTOClass() {
		return ((Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	/**
	 * Obtiene un elemento asociado por su identificador.
	 * @param id El identificador del elemento.
	 * @return El elemento recuperado a traves de su identificador.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */

	@SuppressWarnings("unchecked")
	public T listById (Long id) throws Exception {
		T resultado = null;
		IBaseEntity<T> elemento = (IBaseEntity<T>) entityManager.find(claseEntidad, id);
		if(elemento != null) {
			resultado = elemento.crearDTO();
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
	public List<T> list(Long paginaActual, Long tamanioPagina){
		Query consulta = entityManager.createQuery("from " + claseEntidad.getName());
		if(tamanioPagina != null && tamanioPagina > 0 && paginaActual != null){
			consulta.setFirstResult((paginaActual.intValue() * tamanioPagina.intValue()));
			consulta.setMaxResults(tamanioPagina.intValue());
		}

		List<IBaseEntity<T>> queryList = consulta.getResultList();
		if(queryList == null){
			return null;
		}
		List<T> list= new ArrayList<T>();
		for(IBaseEntity<T> elemento: queryList){
			list.add(elemento.crearDTO());
		}
		 return list;
	}

	/**
	 * Lista todos los resultados del objeto a devolver que hay en la base de datos
	 * @return La lista de elementos a devolver
	 */
	public List<T> list(){
		return list(null,null);
	}
	/**
	 * Obtiene un listado de elementos del catalogo por nombre
	 * @param nombre nombre a buscar 
	 * @return listado de elementos del catalogo
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<T> listByName(String nombre) throws Exception{
		Query consulta = entityManager.createQuery("from " + claseEntidad.getName()+" where nombre =:nombre");
		consulta.setParameter("nombre", nombre);

		List<IBaseEntity<T>> queryList = consulta.getResultList();
		if(queryList == null){
			return null;
		}
		List<T> list= new ArrayList<T>();
		for(IBaseEntity<T> elemento: queryList){
			list.add(elemento.crearDTO());
		}
		 return list;
		
	}
	/**
	 * Lista los resultados del objeto a devolver
	 * @param paginaActual Pagina de la base de datos a devolver
	 * @param tamanioPagina Tamano de resultados dentro de una pagina
	 * @return La lista de elementos a devolver
	 */
	@SuppressWarnings("unchecked")
	public List<T> search(BaseFiltro filtro, Long paginaActual, Long tamanioPagina){
		Query consulta = entityManager.createQuery("from " + claseEntidad.getName());
		consulta.setFirstResult((paginaActual.intValue() * tamanioPagina.intValue()));
		consulta.setMaxResults(tamanioPagina.intValue());

		List<IBaseEntity<T>> queryList = consulta.getResultList();
		if(queryList == null){
			return null;
		}
		List<T> list= new ArrayList<T>();
		for(IBaseEntity<T> elemento: queryList){
			list.add(elemento.crearDTO());
		}
		 return list;
	}


	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	@SuppressWarnings("unchecked")
	public T saveOrUpdate(T dto) throws Exception {
		try {
			IBaseEntity<T> entidad =  (IBaseEntity<T>)EntityFactory.getEntityObjectFromDTO(claseEntidad, dto);

			if(EntityFactory.getIdDTOValue(dto) != null) {
				//Es una actualizacion.
				entityManager.merge(entidad);
				entityManager.flush();
			}
			else {
				//Es un insert.
				entidad.setActivo(SI);
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
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param id con el id de la entrada a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@SuppressWarnings("unchecked")
	public T reactivar(Long id) throws Exception{
		try {
			IBaseEntity<T> elemento = (IBaseEntity<T>) entityManager.find(claseEntidad, id);
			if(elemento != null) {
				elemento.setActivo(SI);
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
	 * Elimina logicamente un registro
	 * @param id Identficador de registro
	 * @throws Exception Excepcion que se puede producir durante el proceso de borrado.
	 */
	@SuppressWarnings("unchecked")
	public void delete(Long id) throws Exception {
		try {
			IBaseEntity<T> elemento = (IBaseEntity<T>) entityManager.find(claseEntidad, id);
			if(elemento != null) {
				elemento.setActivo(NO);
				elemento.setFechaBaja(new Date());
				entityManager.persist(elemento);
				entityManager.flush();
			}
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
	@SuppressWarnings("unchecked")
	public void remove(Long id) throws Exception {
		try{
			IBaseEntity<T> elemento = (IBaseEntity<T>) entityManager.find(claseEntidad, id);
			entityManager.remove(elemento);
			entityManager.flush();
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}
	}
}
