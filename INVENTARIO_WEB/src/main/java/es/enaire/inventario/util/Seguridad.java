package es.enaire.inventario.util;

import es.enaire.inventario.constants.PerfilesConstants;
import es.enaire.inventario.dtos.UsuarioDTO;

public class Seguridad {

	private static boolean compruebaEmpresaAccion(String nameAction, UsuarioDTO usuarioAutenticado) {
		if(usuarioAutenticado.getPerfilesString().contains(PerfilesConstants.TEXTO_MI_CONSULTA)) {
			if(nameAction.equals(ConstantesMigasPan.ACCION_VER_MAS)
					|| nameAction.equals(ConstantesMigasPan.ACCION_LISTAR)
					|| nameAction.equals(ConstantesMigasPan.ACCION_MORE_RESULTS)
					|| nameAction.equals(ConstantesMigasPan.ACCION_VOLVER_LISTAR)
					|| nameAction.equals(ConstantesMigasPan.ACCION_EDITAR)
					|| nameAction.equals(ConstantesMigasPan.ACCION_GUARDAR)
					|| nameAction.equals(ConstantesMigasPan.ACCION_ELIMINAR)) {
				return true;
			}
		}

		if(usuarioAutenticado.getPerfilesString().contains(PerfilesConstants.TEXTO_MI_ADMINISTRADOR)) {
			if(nameAction.equals(ConstantesMigasPan.ACCION_VER_MAS)
					|| nameAction.equals(ConstantesMigasPan.ACCION_LISTAR)
					|| nameAction.equals(ConstantesMigasPan.ACCION_MORE_RESULTS)
					|| nameAction.equals(ConstantesMigasPan.ACCION_VOLVER_LISTAR)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Metodo para determinar si ese usuario tiene autorizacion para ejecutar ese accion o no.
	 * @param nameSpace El nombre del espacio de la accion a realizar la comprobacion de autorizacion.
	 * @param nameAction El nombre de la accion a realizar la comprobacion de autorizacion.
	 * @return Devuelve true si pasa la autorizacion o false en caso contrario.
	 */
	public static boolean autorizarAccion(String nameSpace, String nameAction, UsuarioDTO usuarioAutenticado) {

		if(nameSpace.equals(ConstantesMigasPan.ESPACIO_RAIZ)) {
			return true;//Al principal todos tienen permisos.
		}
		else if(nameSpace.equals(ConstantesMigasPan.ESPACIO_MIGAS)) {
			return true;//A las migas todos tienen permisos.
		}
		else if(nameSpace.equals(ConstantesMigasPan.ESPACIO_EMPRESA)) {
			return compruebaEmpresaAccion (nameAction, usuarioAutenticado);
		}
		//TODO AÑADIR CADA UNA DE LAS SEGURIDADES PARA CADA UNO DE LOS ESPACIOS DE EMPRESA.
		
		
		return false;
	}

}
