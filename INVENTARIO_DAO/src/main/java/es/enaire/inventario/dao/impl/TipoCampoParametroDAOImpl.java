package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroTipoCampoParametro;
import es.enaire.inventario.dao.TipoCampoParametroDAO;
import es.enaire.inventario.dtos.TipoCampoParametroDTO;
import es.enaire.inventario.model.TipoCampoParametro;

public class TipoCampoParametroDAOImpl extends BaseDAOImpl<TipoCampoParametroDTO> implements TipoCampoParametroDAO<TipoCampoParametroDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<TipoCampoParametroDTO> search(FiltroTipoCampoParametro filtro, Long pageSize, Long currentPage) throws Exception {
		List<TipoCampoParametroDTO> list = new ArrayList<TipoCampoParametroDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select tcp from TipoCampoParametro tcp ";


			if (filtro.getId() != null) {
				conditions.add("tcp.id = :idTipoCampoParametro");
				parameters.put("idTipoCampoParametro", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("tcp.id != :idTipoCampoParametroExcluir");
				parameters.put("idTipoCampoParametroExcluir", filtro.getIdExcluir());
			}
			if(filtro.getNombre() != null && filtro.getNombre().length() > 0){
				conditions.add("UPPER(tcp.nombre) like UPPER('%" + filtro.getNombre() + "' || '%')");
			}
			if(filtro.getTipo() != null){
				conditions.add("tcp.tipo = :tipo");
				parameters.put("tipo", filtro.getTipo());
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("tcp.activo = :isActivo");
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

			List<TipoCampoParametro> tipoCampoParametrosFiltrados = consulta.getResultList();
			if (tipoCampoParametrosFiltrados != null) {
				for (TipoCampoParametro tipoCampoParametro : tipoCampoParametrosFiltrados) {
					list.add(tipoCampoParametro.crearDTO());
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
	public TipoCampoParametroDTO saveModificacion(TipoCampoParametroDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
