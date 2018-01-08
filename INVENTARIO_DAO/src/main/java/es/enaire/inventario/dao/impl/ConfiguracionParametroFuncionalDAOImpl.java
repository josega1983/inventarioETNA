package es.enaire.inventario.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.enaire.inventario.business.FiltroConfiguracionParametroFuncional;
import es.enaire.inventario.dao.ConfiguracionParametroFuncionalDAO;
import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;
import es.enaire.inventario.dtos.ParametroFuncionalDTO;
import es.enaire.inventario.dtos.TipoCampoParametroDTO;
import es.enaire.inventario.model.ConfiguracionParametroFuncional;



public class ConfiguracionParametroFuncionalDAOImpl extends BaseDAOImpl<ConfiguracionParametroFuncionalDTO> implements ConfiguracionParametroFuncionalDAO<ConfiguracionParametroFuncionalDTO> {
	/**
	 * Obtiene el listado de objetos que cumplen el filtro especificado.
	 * @param filtro El filtro para obtener la busqueda de los objetos que lo cumplen.
	 * @param pageSize El tamanio de la pagina.
	 * @param currentPage La pagina actual.
	 * @return El listado de objetos que cumplen el filtro especificado.
	 * @throws Exception Excepcion que se puede producir durante el proceso de recuperacion.
	 */
	@SuppressWarnings("unchecked")
	public List<ConfiguracionParametroFuncionalDTO> search(FiltroConfiguracionParametroFuncional filtro, Long pageSize, Long currentPage) throws Exception{
		List<ConfiguracionParametroFuncionalDTO> list = new ArrayList<ConfiguracionParametroFuncionalDTO>();
		try {
			ArrayList<String> conditions = new ArrayList<String>();
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			String query = "select cpf from ConfiguracionParametroFuncional cpf ";
			
			if (filtro.getId() != null) {
				conditions.add("cpf.id = :idConfiguracionParametroFuncional");
				parameters.put("idConfiguracionParametroFuncional", filtro.getId());
			}
			if(filtro.getIdExcluir() != null){
				conditions.add("cpf.id != :idConfiguracionParametroFuncionalExcluir");
				parameters.put("idConfiguracionParametroFuncionalExcluir", filtro.getIdExcluir());
			}
			if(filtro.getParametroFuncional() != null){
				ParametroFuncionalDTO parametroFuncional = filtro.getParametroFuncional();
				if(parametroFuncional.getId() !=null){
					conditions.add("cpf.parametroFuncional.id = :idParametroFuncional");
					parameters.put("idParametroFuncional", parametroFuncional.getId());
				}
				if(parametroFuncional.getNombre() != null && parametroFuncional.getNombre().length() >0){
					conditions.add("UPPER(cpf.parametroFuncional.nombre) like UPPER('%" + parametroFuncional.getNombre() + "' || '%')");
				}
			}
			if(filtro.getTipoCampoParametro() != null){
				TipoCampoParametroDTO tipoCampoParametro = filtro.getTipoCampoParametro();
				if(tipoCampoParametro.getId() != null){
					conditions.add("cpf.tipoCampoParametro.id = :idTipoCampoParametro");
					parameters.put("idTipoCampoParametro", tipoCampoParametro.getId());	
				}
				if(tipoCampoParametro.getNombre() != null && tipoCampoParametro.getNombre().length()>0){
					conditions.add("UPPER(cpf.tipoCampoParametro.nombre) like UPPER('%" + tipoCampoParametro.getNombre() + "' || '%')");
				}
			}
			if(filtro.getNombreCampo() !=null && filtro.getNombreCampo().length() >0 ){
				conditions.add("UPPER(cpf.nombreCampo) like UPPER('%" + filtro.getNombreCampo() + "' || '%')");
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


			List<ConfiguracionParametroFuncional> configuracionParametroFuncionalFiltrados = consulta.getResultList();
			if (configuracionParametroFuncionalFiltrados != null) {
				for (ConfiguracionParametroFuncional configuracionParametroFuncional : configuracionParametroFuncionalFiltrados) {
					list.add(configuracionParametroFuncional.crearDTO());
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().toString() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName(),e);
			throw (e);
		}
		return list;
	}

	@Override
	public ConfiguracionParametroFuncionalDTO saveModificacion(
			ConfiguracionParametroFuncionalDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	};
}
