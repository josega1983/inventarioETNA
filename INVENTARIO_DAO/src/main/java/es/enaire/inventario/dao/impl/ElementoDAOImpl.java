package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroElemento;
import es.enaire.inventario.dao.ElementoDAO;
import es.enaire.inventario.dtos.ElementoDTO;
import es.enaire.inventario.dtos.ModificacionElementoDTO;
import es.enaire.inventario.factory.EntityFactory;
import es.enaire.inventario.model.Elemento;
import es.enaire.inventario.model.ModificacionElemento;

public class ElementoDAOImpl extends BaseDAOImpl<ElementoDTO> implements ElementoDAO<ElementoDTO> {
	/**
	 * Obtiene un elemento asociado por su identificador.
	 * @param id El identificador del elemento.
	 * @return El elemento recuperado a traves de su identificador.
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	@Override
	public ElementoDTO listById(Long id) throws Exception {
		ElementoDTO resultado = null;
		Elemento elemento = (Elemento) entityManager.find(Elemento.class, id);
		if(elemento != null) {
			resultado = elemento.crearDTO();
		}

		return resultado;
	}
	/**
	 * Guarda o actualiza la informacion del dto a su correspondiente entidad.
	 * @param dto La informacion del dto que se va a guardar con respecto a su entidad.
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado o de actualizacion.
	 */
	public ElementoDTO saveOrUpdate(ElementoDTO dto) throws Exception {
		try {
			Elemento entidad = new Elemento(dto);

			if(EntityFactory.getIdDTOValue(dto) != null) {
				//Es una actualizacion.
				//Tenemos que gusrdar ademas la modificacion en la tamba de modificaciones de elementos
				saveModificacion(dto);
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
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ElementoDTO> search(FiltroElemento filtro, Long pageSize, Long currentPage) throws Exception {
		List<ElementoDTO> list = new ArrayList<ElementoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select e from Elemento e ";


			if (filtro.getId() != null) {
				conditions.add("e.id = :idElemento");
				parameters.put("idElemento", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("e.id != :idElementoExcluir");
				parameters.put("idElementoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(e.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getEstadoElemento() != null && filtro.getEstadoElemento().getId() != null){
				conditions.add("e.estadoElemento.id = :idEstadoElemento");
				parameters.put("idEstadoElemento", filtro.getEstadoElemento().getId());
			}
			if(filtro.getDescripcion() != null && filtro.getDescripcion().length() >0){
				conditions.add("UPPER(e.descripcion) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getFamiliaElemento() != null && filtro.getFamiliaElemento().getId() != null){
				conditions.add("e.familiaElemento.id = :idFamiliaElemento");
				parameters.put("idFamiliaElemento", filtro.getFamiliaElemento().getId());
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("e.activo = :isActivo");
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

			List<Elemento> elementosFiltrados = consulta.getResultList();
			if (elementosFiltrados != null) {
				for (Elemento elemento : elementosFiltrados) {
					list.add(elemento.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}
	/**
	 * Metodo que nos guarda las modificaciones de un elemento en las tablas de modificacion
	 * @param dto a guardar
	 * @throws Exception Excepcion que se puede producir durante el proceso de guardado
	 */
	public ElementoDTO saveModificacion(ElementoDTO dto) throws Exception {
		try {
			ModificacionElementoDTO modificacionElemento = ModificacionElementoDTO.createModificacionElementoFromElemento(dto);
			ModificacionElemento entidad = new ModificacionElemento(modificacionElemento);
			entityManager.persist(entidad);
			entityManager.flush();
			return dto;

		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw e;
		}

	}
}
