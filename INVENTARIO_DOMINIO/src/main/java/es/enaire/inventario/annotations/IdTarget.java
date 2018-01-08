package es.enaire.inventario.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

/**
 * Anotacion utilizada para obtener el campo que representa el ID del DTO.
 *
 */
public @interface IdTarget {
}
