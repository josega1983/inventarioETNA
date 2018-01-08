package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroEmplazamiento;
import es.enaire.inventario.dao.EmplazamientoDAO;
import es.enaire.inventario.dtos.EmplazamientoDTO;
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;
import es.enaire.inventario.model.Emplazamiento;

public class EmplazamientoDAOImpl extends BaseDAOImpl<EmplazamientoDTO> implements EmplazamientoDAO<EmplazamientoDTO> {
	/**
	 * Obtiene un listado de elementos del catalogo por nombre
	 * @param nombre nombre a buscar
	 * @return listado de elementos del catalogo
	 * @throws Exception Excepcion producida durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<EmplazamientoDTO> listByName(String nombre, TipoEmplazamientoDTO tipoEmplazamiento) throws Exception{
		Query consulta = entityManager.createQuery("from Emplazamiento e where e.nombre =:nombre and e.tipoEmplazamiento.id =:idTipoEmplazamiento");
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("idTipoEmplazamiento", tipoEmplazamiento.getId());

		List<Emplazamiento> queryList = consulta.getResultList();
		if(queryList == null){
			return null;
		}
		List<EmplazamientoDTO> list= new ArrayList<EmplazamientoDTO>();
		for(Emplazamiento elemento: queryList){
			list.add(elemento.crearDTO());
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
	public List<EmplazamientoDTO> search(FiltroEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception {
		return (List<EmplazamientoDTO>) searchImpl(filtro, false, pageSize, currentPage);
	}
	/**
	 * Obtiene la cantidad de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	public Long getCantidad(FiltroEmplazamiento filtro, Long pageSize, Long currentPage) throws Exception {
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
	private Object searchImpl(FiltroEmplazamiento filtro,boolean cantidad, Long pageSize, Long currentPage) throws Exception {
		List<EmplazamientoDTO> list = new ArrayList<EmplazamientoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select e from Emplazamiento e ";
			if(cantidad){
				query = "select count(*) from Emplazamiento e  ";
			}

			if (filtro.getId() != null) {
				conditions.add("e.id = :idEmplazamiento");
				parameters.put("idEmplazamiento", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("e.id != :idEmplazamientoExcluir");
				parameters.put("idEmplazamientoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(e.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getObservaciones() != null && filtro.getObservaciones().length() > 0){
				conditions.add("UPPER(e.observaciones) like UPPER('%" + filtro.getObservaciones() + "' || '%')");
			}
			if(filtro.getTipoEmplazamiento() != null && filtro.getTipoEmplazamiento().getId() != null){
				conditions.add("UPPER(e.tipoEmplazamiento.id) = :idTipoEmplazamiento");
				parameters.put("idTipoEmplazamiento", filtro.getTipoEmplazamiento().getId());
			}
			if(filtro.getTipoEmplazamiento() != null && filtro.getTipoEmplazamiento().getNombre() != null && filtro.getTipoEmplazamiento().getNombre().length()>0){
				conditions.add("UPPER(e.tipoEmplazamiento.nombre) like UPPER('%" + filtro.getTipoEmplazamiento().getNombre() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("e.activo = :isActivo");
				parameters.put("isActivo", filtro.getActivo());
			}
			if(filtro.getFechaBajaDesde() !=null && !filtro.getFechaBajaDesde().equals("") ){
				conditions.add("e.fechaBaja >= :fechaBajaDesde");
				parameters.put("fechaBajaDesde", filtro.getFechaBajaDesde());
			}
			if(filtro.getFechaBajaHasta() !=null && !filtro.getFechaBajaHasta().equals("") ){
				conditions.add("e.fechaBaja <= :fechaBajaHasta");
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

			List<Emplazamiento> emplazamientosFiltrados = consulta.getResultList();
			if(cantidad){
				return (emplazamientosFiltrados != null ? emplazamientosFiltrados.get(0):0L);
			}
			if (emplazamientosFiltrados != null) {
				for (Emplazamiento emplazamiento : emplazamientosFiltrados) {
					list.add(emplazamiento.crearDTO());
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
	public List<EmplazamientoDTO> listByName(String nombre) throws Exception {
		List<EmplazamientoDTO> list = new ArrayList<EmplazamientoDTO>();
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		try {
			String query = "select e from Emplazamiento e where UPPER(e.nombre) = UPPER('" + nombre + "') and e.activo = :isActivo";
			parameters.put("isActivo", BaseDAOImpl.SI);

			Query consulta = entityManager.createQuery(query);
			for (Map.Entry<String, Object> entry : parameters.entrySet())
				consulta.setParameter(entry.getKey(), entry.getValue());
			List<Emplazamiento> emplazamientosFiltrados = consulta.getResultList();
	
			if (emplazamientosFiltrados != null) {
				for (Emplazamiento emplazamiento : emplazamientosFiltrados) {
					list.add(emplazamiento.crearDTO());
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
	public EmplazamientoDTO saveModificacion(EmplazamientoDTO dto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
