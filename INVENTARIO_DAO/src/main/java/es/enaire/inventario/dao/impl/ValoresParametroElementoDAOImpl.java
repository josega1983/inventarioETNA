package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroValoresParametroElemento;
import es.enaire.inventario.dao.ValoresParametroElementoDAO;
import es.enaire.inventario.dtos.ValoresParametroElementoDTO;
import es.enaire.inventario.model.ValoresParametroElemento;

public class ValoresParametroElementoDAOImpl extends BaseDAOImpl<ValoresParametroElementoDTO> implements ValoresParametroElementoDAO<ValoresParametroElementoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ValoresParametroElementoDTO> search(FiltroValoresParametroElemento filtro, Long pageSize, Long currentPage) throws Exception {
		List<ValoresParametroElementoDTO> list = new ArrayList<ValoresParametroElementoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select vpe from ValoresParametroElemento vpe ";


			if (filtro.getId() != null) {
				conditions.add("vpe.id = :idValoresParametroElemento");
				parameters.put("idValoresParametroElemento", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("vpe.id != :idValoresParametroElementoExcluir");
				parameters.put("idValoresParametroElementoExcluir", filtro.getIdExcluir());
			}
			if(filtro.getElemento() != null && filtro.getElemento().getId() != null){
				conditions.add("vpe.elemento.id = :idElemento");
				parameters.put("idElemento", filtro.getElemento().getId());
			}
			if(filtro.getValoresParametrosFamiliasElemento() != null && filtro.getValoresParametrosFamiliasElemento().getId() != null){
				conditions.add("vpe.valoresParametrosFamiliasElemento.id = :idValoresParametrosFamiliasElemento");
				parameters.put("idValoresParametrosFamiliasElemento", filtro.getValoresParametrosFamiliasElemento().getId());
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("vpe.activo = :isActivo");
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

			List<ValoresParametroElemento> valoresParametroElementosFiltrados = consulta.getResultList();
			if (valoresParametroElementosFiltrados != null) {
				for (ValoresParametroElemento valoresParametroElemento : valoresParametroElementosFiltrados) {
					list.add(valoresParametroElemento.crearDTO());
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
	public ValoresParametroElementoDTO saveModificacion(ValoresParametroElementoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
