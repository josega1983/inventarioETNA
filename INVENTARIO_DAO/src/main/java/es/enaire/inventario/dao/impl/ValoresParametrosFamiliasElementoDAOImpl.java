package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroValoresParametrosFamiliasElemento;
import es.enaire.inventario.dao.ValoresParametrosFamiliasElementoDAO;
import es.enaire.inventario.dtos.ValoresParametrosFamiliasElementoDTO;
import es.enaire.inventario.model.ValoresParametrosFamiliasElemento;

public class ValoresParametrosFamiliasElementoDAOImpl extends BaseDAOImpl<ValoresParametrosFamiliasElementoDTO> implements ValoresParametrosFamiliasElementoDAO<ValoresParametrosFamiliasElementoDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ValoresParametrosFamiliasElementoDTO> search(FiltroValoresParametrosFamiliasElemento filtro, Long pageSize, Long currentPage) throws Exception {
		List<ValoresParametrosFamiliasElementoDTO> list = new ArrayList<ValoresParametrosFamiliasElementoDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select vpfe from ValoresParametrosFamiliasElemento vpfe ";


			if (filtro.getId() != null) {
				conditions.add("vpfe.id = :idValoresParametrosFamiliasElemento");
				parameters.put("idValoresParametrosFamiliasElemento", filtro.getId());
			}
			if (filtro.getIdExcluir() != null){
				conditions.add("vpfe.id != :idValoresParametrosFamiliasElementoExcluir");
				parameters.put("idValoresParametrosFamiliasElementoExcluir", filtro.getIdExcluir());
			}
			if (filtro.getFamiliaElemento() != null && filtro.getFamiliaElemento().getId() != null ) {
				conditions.add("vpfe.familiaElemento.id = :idFamiliasElemento");
				parameters.put("idFamiliasElemento", filtro.getFamiliaElemento().getId());
			}
			if (filtro.getValoresParametroFuncional() != null && filtro.getValoresParametroFuncional().getId() != null) {
				conditions.add("vpfe.valoresParametroFuncional.id = :idValoresParametroFuncional");
				parameters.put("idValoresParametroFuncional", filtro.getValoresParametroFuncional().getId());
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("vpfe.activo = :isActivo");
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

			List<ValoresParametrosFamiliasElemento> valoresParametrosFamiliasElementosFiltrados = consulta.getResultList();
			if (valoresParametrosFamiliasElementosFiltrados != null) {
				for (ValoresParametrosFamiliasElemento valoresParametrosFamiliasElemento : valoresParametrosFamiliasElementosFiltrados) {
					list.add(valoresParametrosFamiliasElemento.crearDTO());
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
	public ValoresParametrosFamiliasElementoDTO saveModificacion(ValoresParametrosFamiliasElementoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
