package es.enaire.inventario.model;

import java.util.Date;

/**
 * Interfaz para la entidad obtener el objeto que va a contener la informacion de la entidad para ser transportado entre las diferentes capas.
 *
 */
public interface IBaseEntity<T>  {
	/**
	 * Crea el objeto que va a transportar la informacion entre las capas con la informacion de la entidad.
	 * @return El objeto que va a transportar la informacion entre las capas con la informacion de la entidad.
	 */
	public T crearDTO();
	/**
	 * Establece el flag de activo de una entradas
	 * @param string con el contenido del flag
	 */
	public void setActivo(String string);
	/**
	 * Establece la fecha de baja de una entrada
	 * @param date con la fecha introducida
	 */
	public void setFechaBaja(Date date);
}
