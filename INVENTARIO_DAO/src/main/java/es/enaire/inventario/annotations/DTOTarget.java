package es.enaire.inventario.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

/**
 * Anotacion utilizada para obtener la correspondencia entre la entidad y el DTO.
 *
 */
public @interface DTOTarget {
	/**
	 * Atributo que la anotacion que contiene la clase que tiene la implementacion del DTO.
	 * @return Devuelve el nombre de la clase que tiene la implementacion del DTO.
	 */
	String claseImplementacionDTO() default "";
}
