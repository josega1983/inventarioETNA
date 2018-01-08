package es.enaire.inventario.constants;

/**
 * Constantes que representan las acciones que realiza el usuario sobre las empresas para moverlas de estado.
 */
public class AccionesEmpresaConstants {
	/**
	 * Accion de creacion de una empresa.
	 */
	public static final Integer CREACION = 0;
	
	/**
	 * Accion de recepcion de codigo de una empresa.
	 */
	public static final Integer RECEPCION_CODIGO = 1;
	
	/**
	 * Accion de modificacion de datos de empresa.
	 */
	public static final Integer MODIFICACION = 2;
	
	/**
	 * Accion de eliminacion de empresa.
	 */
	public static final Integer ELIMINACION = 3;
	
	/**
	 * Accion de reactivar una empresa dada de baja.
	 */
	public static final Integer REACTIVACION = 4;
	
	/**
	 * Accion de rechazar.
	 */
	public static final Integer RECHAZAR = 5;
	
	/**
	 * Accion de aprobar.
	 */
	public static final Integer APROBAR = 6;
	
	/**
	 * Accion de cancelar.
	 */
	public static final Integer CANCELAR = 7;
}
