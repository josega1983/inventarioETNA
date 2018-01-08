package es.enaire.inventario.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import es.enaire.inventario.actions.BaseAction;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.FicheroDTO;

public class FileInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5181695586141855508L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		BaseAction action = (BaseAction) invocation.getAction();
		FicheroDTO fichero = (FicheroDTO) action.getElemento();
		if(fichero != null && fichero.getFileContentType() !=null){
		String[] contentType = fichero.getFileContentType().split("/");
		String tipo =contentType[1];
		EntradaDTO entradaDTO = new EntradaDTO();
		entradaDTO.setTipoEntrada(TipoEntradaConstants.PROPIEDAD_FICHERO);
		entradaDTO.setObjetoEntrada(tipo);
		
		
		}
		return invocation.invoke();
	}
}
