package es.enaire.inventario.backend.operaciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.enaire.inventario.business.FiltroConfiguracionesFamilia;
import es.enaire.inventario.constants.AccionesCatalogosConstans;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.dao.ConfiguracionesFamiliaDAO;
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.RespuestaDTO;

public class OperacionesConfiguracionesFamilia {

	/**
	 * DAO de configuracionesFamiliaes
	 */
	@Autowired
	private ConfiguracionesFamiliaDAO<ConfiguracionesFamiliaDTO> configuracionesFamiliaDAO;
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 */
	private RespuestaDTO validateInfo(EntradaDTO entrada) {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null || entrada.getObjetoEntrada()==null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(!(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO)){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TIPO_ENTRADA_NO_ESPERADA);
			return respuesta;
		}

		if(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO) {
			ConfiguracionesFamiliaDTO configuracionesFamilia =(ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
			if (configuracionesFamilia.getId() == null) {
				respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ID_NO_INFORMADO);
				return respuesta;
			}
		}

		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	/**
	 * Obtiene los datos de la entrada solicitada.
	 * @param entrada de la informacion solicitada
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional(readOnly=true)
	public RespuestaDTO info(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta= validateInfo(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {

			ConfiguracionesFamiliaDTO configuracionesFamiliaRespuesta = null;
			if(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO) {
				configuracionesFamiliaRespuesta = configuracionesFamiliaDAO.listById(((ConfiguracionesFamiliaDTO)entrada.getObjetoEntrada()).getId());
			}

			if(configuracionesFamiliaRespuesta != null) {
				Object[] objetoRespuesta={configuracionesFamiliaRespuesta};
				respuesta.setObjetoRespuesta(objetoRespuesta);
			}
		}
		return respuesta;
	}
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 */
	private RespuestaDTO validateList(EntradaDTO entrada) {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(entrada.getTamanioPagina() == null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TAMANIO_NO_INFORMADO);
			return respuesta;
		}
		if (entrada.getPaginaActual() == null) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_PAGINA_ACTUAL_NO_INFORMADA);
			return respuesta;
		}
		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	/**
	 * Obtiene la lista con los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional(readOnly=true)
	public RespuestaDTO list(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta=validateList(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			List<ConfiguracionesFamiliaDTO> configuracionesFamiliaRespuesta = configuracionesFamiliaDAO.list(entrada.getPaginaActual(), entrada.getTamanioPagina());
			Object[] objetoRespuesta = configuracionesFamiliaRespuesta.toArray();
			respuesta.setObjetoRespuesta(objetoRespuesta);
		}
		return respuesta;
	}
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	private RespuestaDTO validateEdit(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null || entrada.getObjetoEntrada()==null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(!(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO)){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TIPO_ENTRADA_NO_ESPERADA);
			return respuesta;
		}
//		ConfiguracionesFamiliaDTO configuracionesFamilia = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
//		if(configuracionesFamilia.getNombre() == null ||configuracionesFamilia.getNombre().isEmpty() ){
//			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_NOMBRE_NO_INFORMADO);
//			return respuesta;
//		}
//		FiltroConfiguracionesFamilia filtro = new FiltroConfiguracionesFamilia();
//		filtro.setNombre(configuracionesFamilia.getNombre());
//		if(configuracionesFamilia.getId() != null){//es una actualizacion y hay que excluirlo
//			filtro.setIdExcluir(configuracionesFamilia.getId());
//		}
//		List<ConfiguracionesFamiliaDTO> configuracionesFamiliaList = configuracionesFamiliaDAO.search(filtro, 0L, 1L);
//				if(configuracionesFamiliaList !=null && configuracionesFamiliaList.size() >0){
//					respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_NOMBRE_DUPLICADO);
//					return respuesta;
//				}
		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	/**
	 * Crea o actualiza un registro en la base de datos del tipo de la entrada seleccionada
	 * @param entrada con el objeto a crear o actualizar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional
	public RespuestaDTO edit(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta = validateEdit(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			ConfiguracionesFamiliaDTO configuracionesFamiliaEntrada = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
			if (configuracionesFamiliaEntrada.getId() !=null){
				ConfiguracionesFamiliaDTO infoBD = configuracionesFamiliaDAO.listById(configuracionesFamiliaEntrada.getId());
				configuracionesFamiliaEntrada.setActivo(infoBD.getActivo());
				configuracionesFamiliaEntrada.setFechaAlta(infoBD.getFechaAlta());
				configuracionesFamiliaEntrada.setFechaBaja(infoBD.getFechaBaja());
				
				configuracionesFamiliaDAO.remove(configuracionesFamiliaEntrada.getId());
			}
			
			configuracionesFamiliaEntrada.setNombre(configuracionesFamiliaEntrada.getFamiliaInstalacion().getNombre());
			
			ConfiguracionesFamiliaDTO configuracionesFamiliaRespuesta = configuracionesFamiliaDAO.saveOrUpdate(configuracionesFamiliaEntrada);
			//Damos persistencia a los hijos 
			configuracionesFamiliaRespuesta = configuracionesFamiliaDAO.saveOrUpdateConfiguracionElementosFamilia(configuracionesFamiliaRespuesta);
			Object[] objetoRespuesta={configuracionesFamiliaRespuesta};
			respuesta.setObjetoRespuesta(objetoRespuesta);
		}
		return respuesta;
	}
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	private RespuestaDTO validateReactivar(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null || entrada.getObjetoEntrada()==null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(!(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO)){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TIPO_ENTRADA_NO_ESPERADA);
			return respuesta;
		}
		ConfiguracionesFamiliaDTO configuracionesFamilia = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
		if(configuracionesFamilia.getId() == null ){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ID_NO_INFORMADO);
			return respuesta;
		}
		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	/**
	 * Reactiva un registro en la base de datos del tipo de la entrada seleccionada
	 * @param entrada con el objeto a reactivar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se puede producir durante el proceso de validacion.
	 */
	@Transactional
	public RespuestaDTO reactivar(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta = validateReactivar(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			ConfiguracionesFamiliaDTO configuracionesFamiliaEntrada = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
			ConfiguracionesFamiliaDTO configuracionesFamiliaRespuesta = configuracionesFamiliaDAO.reactivar(configuracionesFamiliaEntrada.getId());
			Object[] objetoRespuesta={configuracionesFamiliaRespuesta};
			respuesta.setObjetoRespuesta(objetoRespuesta);
		}
		return respuesta;
	}
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 */
	private RespuestaDTO validateDelete(EntradaDTO entrada) {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null || entrada.getObjetoEntrada()==null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(!(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO)){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TIPO_ENTRADA_NO_ESPERADA);
			return respuesta;
		}
		ConfiguracionesFamiliaDTO configuracionesFamilia = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
		if (configuracionesFamilia.getId() == null) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ID_NO_INFORMADO);
			return respuesta;
		}
		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	/**
	 * Elimina un registro en la base de datos del tipo de entrada seleccionada
	 * @param entrada con el objeto a eliminar
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional
	public RespuestaDTO delete(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta=validateDelete(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			ConfiguracionesFamiliaDTO configuracionesFamiliaEntrada = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
			configuracionesFamiliaDAO.delete(configuracionesFamiliaEntrada.getId());
		}
		return respuesta;
	}
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 */
	private RespuestaDTO validateSearch(EntradaDTO entrada) {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null || entrada.getObjetoEntrada() == null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(entrada.getTamanioPagina() == null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TAMANIO_NO_INFORMADO);
			return respuesta;
		}
		if (entrada.getPaginaActual() == null) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_PAGINA_ACTUAL_NO_INFORMADA);
			return respuesta;
		}
		if(!(entrada.getObjetoEntrada() instanceof FiltroConfiguracionesFamilia)) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TIPO_ENTRADA_NO_ESPERADA);
			return respuesta;
		}
		
		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	
	/**
	 * Obtiene la lista con los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional(readOnly=true)
	public RespuestaDTO search(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta = validateSearch(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			List<ConfiguracionesFamiliaDTO> regionListRespuesta = configuracionesFamiliaDAO.search( (FiltroConfiguracionesFamilia) entrada.getObjetoEntrada(), entrada.getTamanioPagina(), entrada.getPaginaActual());
			Object[] objetoRespuesta = regionListRespuesta.toArray();
			respuesta.setObjetoRespuesta(objetoRespuesta);
		}
		return respuesta;
	}
	/**
	 * Obtiene cantidad de objetos que verifican los criterios de entrada solicitada
	 * @param entrada con los criterios de busqueda
	 * @return la respuesta con el codigo respuesta y la entrada como objeto respuesta
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional(readOnly=true)
	public RespuestaDTO getCantidad(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta = validateSearch(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {
			Long cantidadRespuesta = configuracionesFamiliaDAO.getCantidad( (FiltroConfiguracionesFamilia) entrada.getObjetoEntrada(), entrada.getTamanioPagina(), entrada.getPaginaActual());
			Object[] objetoRespuesta = {cantidadRespuesta};
			respuesta.setObjetoRespuesta(objetoRespuesta);
		}
		return respuesta;
	}
	/**
	 * Valida que la entrada sea correcta
	 * @param entrada a validar
	 * @return respuesta con el codigo respuesta y el objeto a validar
	 */
	private RespuestaDTO validateChangeStatus(EntradaDTO entrada) {
		RespuestaDTO respuesta= new RespuestaDTO();
		if(entrada == null || entrada.getObjetoEntrada()==null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ENTRADA_NO_INFORMADA);
			return respuesta;
		}
		if(!(entrada.getObjetoEntrada() instanceof ConfiguracionesFamiliaDTO)){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_TIPO_ENTRADA_NO_ESPERADA);
			return respuesta;
		}
		if(entrada.getAccion() == null) {
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ACCION_NO_INFORMADA);
			return respuesta;
		}


		ConfiguracionesFamiliaDTO configuracionesFamilia = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
		if(!entrada.getAccion().equals(AccionesCatalogosConstans.CREACION) && configuracionesFamilia.getId()==null){
			respuesta.setCodigoRespuesta(CodigosRespuestaConstants.ERROR_ID_NO_INFORMADO);
			return respuesta;
		}

		respuesta.setCodigoRespuesta(CodigosRespuestaConstants.RESPUESTA_OK);
		return respuesta;
	}
	/**
	 * Actuliza el estado de un registro de la base de datos del tipo de entrada seleccionada
	 * @param entrada con el objeto a actualizar el estado
	 * @return la respuesta con el codigo respuesta y el objeto actualizado como objeto respuesta.
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	@Transactional
	public RespuestaDTO changeStatus(EntradaDTO entrada) throws Exception {
		return changeStatusImpl(entrada);
	}
	/**
	 * Implementacion de la maquina de estados del tipo de entrada seleccionada.
	 * @param entrada La entrada con la informacion a actualizar el estado.
	 * @return La respuesta con el codigo respuesta y el objeto actualizado como objeto respuesta.
	 * @throws Exception Excepcion que se pueda producir en el proceso de recuperacion de datos
	 */
	private RespuestaDTO changeStatusImpl(EntradaDTO entrada) throws Exception {
		RespuestaDTO respuesta = validateChangeStatus(entrada);
		if (respuesta.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)) {

			ConfiguracionesFamiliaDTO configuracionesFamiliaEntrada = (ConfiguracionesFamiliaDTO) entrada.getObjetoEntrada();
			if(entrada.getAccion().equals(AccionesCatalogosConstans.CREACION) ||entrada.getAccion().equals(AccionesCatalogosConstans.REACTIVACION)){
				configuracionesFamiliaEntrada.setActivo(AccionesCatalogosConstans.ACTIVO_SI);
				configuracionesFamiliaEntrada.setFechaBaja(null);
			}
			if(entrada.getAccion().equals(AccionesCatalogosConstans.ELIMINACION)){
				configuracionesFamiliaEntrada.setActivo(AccionesCatalogosConstans.ACTIVO_NO);
				configuracionesFamiliaEntrada.setFechaBaja(new Date());
			}
			
			ConfiguracionesFamiliaDTO configuracionesFamiliaRespuesta = configuracionesFamiliaDAO.saveOrUpdate(configuracionesFamiliaEntrada);

			Object[] objetoRespuesta={configuracionesFamiliaRespuesta};
			respuesta.setObjetoRespuesta(objetoRespuesta);
		}

		return respuesta;
	}
}
