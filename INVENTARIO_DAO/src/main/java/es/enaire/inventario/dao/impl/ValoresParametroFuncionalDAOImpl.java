package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroValoresParametroFuncional;
import es.enaire.inventario.dao.ValoresParametroFuncionalDAO;
import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;
import es.enaire.inventario.dtos.ValoresParametroFuncionalDTO;
import es.enaire.inventario.model.ValoresParametroFuncional;

public class ValoresParametroFuncionalDAOImpl extends BaseDAOImpl<ValoresParametroFuncionalDTO> implements
ValoresParametroFuncionalDAO<ValoresParametroFuncionalDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ValoresParametroFuncionalDTO> search(FiltroValoresParametroFuncional filtro, Long pageSize, Long currentPage) throws Exception {
		List<ValoresParametroFuncionalDTO> list = new ArrayList<ValoresParametroFuncionalDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select vpf from ValoresParametroFuncional vpf ";


			if (filtro.getId() != null) {
				conditions.add("vpf.id = :idValoresParametroFuncional");
				parameters.put("idValoresParametroFuncional", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("vpf.id != :idValoresParametroFuncionalExcluir");
				parameters.put("idValoresParametroFuncionalExcluir", filtro.getIdExcluir());
			}
			if (filtro.getCondicionDisparo() != null && filtro.getCondicionDisparo().length() >0) {
				conditions.add("UPPER(vpf.condicionDisparo) like UPPER('%" + filtro.getCondicionDisparo() + "' || '%')");
			}
			if (filtro.getConfiguracionParametroFuncional() != null) {
				ConfiguracionParametroFuncionalDTO configuracionParametroFuncional = filtro.getConfiguracionParametroFuncional();
				if(configuracionParametroFuncional.getId() != null){
					conditions.add("vpf.configuracionParametroFuncional.id = :idConfiguracionParametroFuncional");
					parameters.put("idConfiguracionParametroFuncional", configuracionParametroFuncional.getId());
				}
			}
			if(filtro.getSeveridadAlarma() != null && filtro.getSeveridadAlarma().length() >0){
				conditions.add("UPPER(vpf.severidadAlarma) like UPPER('%" + filtro.getCondicionDisparo() + "' || '%')");
			}
			if(filtro.getValor() != null && filtro.getValor().length() >0){
				conditions.add("UPPER(vpf.valor) like UPPER('%" + filtro.getValor() + "' || '%')");
			}
			if(filtro.getActivo() != null && (filtro.getActivo().equals(BaseDAOImpl.SI) || filtro.getActivo().equals(BaseDAOImpl.NO))){
				conditions.add("vpf.activo = :isActivo");
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

			List<ValoresParametroFuncional> valoresParametroFuncionalsFiltrados = consulta.getResultList();
			if (valoresParametroFuncionalsFiltrados != null) {
				for (ValoresParametroFuncional valoresParametroFuncional : valoresParametroFuncionalsFiltrados) {
					list.add(valoresParametroFuncional.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}
	@Override
	public ValoresParametroFuncionalDTO saveModificacion(
			ValoresParametroFuncionalDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
