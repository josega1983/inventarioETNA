/**
 *
 */
package es.enaire.inventario.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.Id;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import es.enaire.inventario.annotations.DTOTarget;
import es.enaire.inventario.annotations.IdTarget;
import es.enaire.inventario.model.IBaseEntity;

/**
 * Factoria para crear la entidad correspondiente a traves de su objeto DTO.
 */
public class EntityFactory {
    /**
     * Obtencion de la clase de la entidad que mapea la clase del DTO.
     * @param claseDTO La clase del DTO.
     * @return La clase de la entidad.
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Class<? extends IBaseEntity> getEntityClassFromDTO(Class claseDTO){
		try {
			final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
			provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

			final Set<BeanDefinition> classes = provider.findCandidateComponents("es.enaire.inventario.model");

			for (BeanDefinition bean: classes) {
			    Class<? extends IBaseEntity> claseEntidad = (Class<? extends IBaseEntity>) Class.forName(bean.getBeanClassName());
			    if(claseEntidad.getAnnotation(DTOTarget.class) != null && ((DTOTarget)claseEntidad.getAnnotation(DTOTarget.class)).claseImplementacionDTO().equals(claseDTO.getTypeName())) {
					return claseEntidad;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	
	/**
	 * Crea una instancia de la entidad con la informacion que transporta el DTO.
	 * @param claseEntidad La clase que representa la entidad.
	 * @param infoDTO La informacion del DTO.
	 * @return La instancia de la entidad con la informacion del DTO.
	 */
	@SuppressWarnings("rawtypes")
	public static Object getEntityObjectFromDTO(Class<? extends IBaseEntity> claseEntidad, Object infoDTO) {
		try {
			Constructor<?> constructor = claseEntidad.getConstructor(infoDTO.getClass());
			return constructor.newInstance(infoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	/**
	 * Establece el identificador de la entidad al identificador del DTO.
	 * @param dto La instancia del DTO.
	 * @param entity La instancia de la entidad de modelo.
	 */
	public static void setIdDTOFromIdEntity(Object dto, Object entity) {
		try {
			Field[] camposDTO = dto.getClass().getDeclaredFields();
			
			Field campoIdDto = null;
			for (Field campo: camposDTO) {
				if (campo.isAnnotationPresent(IdTarget.class)) {
					campoIdDto = campo;
					break;
		        }
			}
			
			Object idEntidad = null;
			Method[] metodosEntity = entity.getClass().getDeclaredMethods();
			for (Method metodo: metodosEntity) {
				if (metodo.isAnnotationPresent(Id.class)) {
					idEntidad = metodo.invoke(entity);
					break;
		        }
			}
			
			//Establecemos el id de la entidad en el dto.
			if(campoIdDto != null && idEntidad != null) {
				campoIdDto.setAccessible(true);
				campoIdDto.set(dto, idEntidad);
			}
			else {
				throw new Exception("No se pudo establecer el id de la entidad al dto.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene el valor del identificador del DTO.
	 * @param dto La instancia del DTO.
	 * @return El valor del identificador del DTO.
	 */
	public static Object getIdDTOValue(Object dto) {
		try {
			Field[] camposDTO = dto.getClass().getDeclaredFields();
			
			for (Field campo: camposDTO) {
				if (campo.isAnnotationPresent(IdTarget.class)) {
					campo.setAccessible(true);
					return campo.get(dto);
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}