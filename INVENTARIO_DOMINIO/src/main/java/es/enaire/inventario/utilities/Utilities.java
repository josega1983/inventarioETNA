package es.enaire.inventario.utilities;

import java.lang.reflect.Field;
import es.enaire.inventario.annotations.IdTarget;

public class Utilities {
	/**
	 * Establece el identificador de la entidad al identificador del DTO.
	 * @param dto La instancia del DTO.
	 * @param entity La instancia de la entidad de modelo.
	 */
	public static void setIdDTO(Object dto, Long id) {
		try {
			Field[] camposDTO = dto.getClass().getDeclaredFields();

			Field campoIdDto = null;
			for (Field campo: camposDTO) {
				if (campo.isAnnotationPresent(IdTarget.class)) {
					campoIdDto = campo;
					break;
		        }
			}

			//Establecemos el id de la entidad en el dto.
			if(campoIdDto != null ) {
				campoIdDto.setAccessible(true);
				campoIdDto.set(dto, id);
			}
			else {
				throw new Exception("No se pudo establecer el id del dto.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
