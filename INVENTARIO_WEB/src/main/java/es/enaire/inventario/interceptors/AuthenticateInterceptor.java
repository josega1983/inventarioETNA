package es.enaire.inventario.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import es.enaire.inventario.actions.BaseAction;
import es.enaire.inventario.dtos.UsuarioDTO;
import es.enaire.inventario.util.GestionPropiedadesConfiguracion;
import es.enaire.inventario.util.Seguridad;

public class AuthenticateInterceptor  extends AbstractInterceptor {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = -6488055367538146358L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		Map<String, Object> session = invocation.getInvocationContext().getSession();
//
//
//	    BaseAction action = (BaseAction) invocation.getAction();
//	    if(session == null || session.get(BaseAction.USUARIO_AUTENTICADO) == null)
//	    	return "errorLogin";
//
//
//	    String nameSpace = invocation.getProxy().getNamespace();
//	    String nameAction = invocation.getProxy().getActionName();
//	    try{
//	    	UsuarioDTO usuarioAutenticado = (UsuarioDTO) session.get(BaseAction.USUARIO_AUTENTICADO);
//	    	action.setUsuarioAutenticado(usuarioAutenticado);
//
//			if(!isModeDebug()){
//				if(!Seguridad.autorizarAccion(nameSpace, nameAction, usuarioAutenticado)){
//		    		return "sinPermisos";
//		    	}
//			}
//	    }
//	    catch (ClassCastException e){
//	    	// Cuando se republica estaba dando un error aqui. Forzamos al usuario
//	    	// que se loguee de nuevo
//	    	return "errorLogin";
//	    }
//
//	    action.setSession(session);
		return invocation.invoke();

    }

	/**
	 * Devuelve el valor de la variable debug del Properties.
	 * @return el valor de la variable debug del properties.
	 */
	public boolean isModeDebug() {
		return GestionPropiedadesConfiguracion.getModeDebug();
	}

}