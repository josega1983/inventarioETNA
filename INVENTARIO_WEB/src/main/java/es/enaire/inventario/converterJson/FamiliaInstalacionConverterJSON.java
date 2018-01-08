package es.enaire.inventario.converterJson;

import es.enaire.inventario.business.FamiliaInstalacionJsonDTO;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;

public class FamiliaInstalacionConverterJSON {
	/**
	 * Hace una conversion de FamiliaInstalacionJsonDTO a FamiliaInstalacionDTO
	 * @return ConfiguracionesFamiliaDTO Object
	 */
	public static FamiliaInstalacionDTO convertirJsonDTOToDTO(FamiliaInstalacionJsonDTO familiaInstalacionJson) {
		FamiliaInstalacionDTO familiaInstalacion = new FamiliaInstalacionDTO();

		familiaInstalacion.setId(familiaInstalacionJson.getId());
		familiaInstalacion.setNombre(familiaInstalacionJson.getNombre());
		familiaInstalacion.setObservaciones(null);
		familiaInstalacion.setActivo(null);
		familiaInstalacion.setArea(null);
		familiaInstalacion.setEstadoMenu(null);
		familiaInstalacion.setFechaAlta(null);
		familiaInstalacion.setFechaBaja(null);
		
		return familiaInstalacion;
	}
	
	/**
	 * Hace una conversion de FamiliaInstalacionDTO a FamiliaInstalacionJsonDTO
	 * @return ConfiguracionesFamiliaJsonDTO Object
	 */
	public static FamiliaInstalacionJsonDTO convertirDTOToJsonDTO(FamiliaInstalacionDTO familiaInstalacion) {
		FamiliaInstalacionJsonDTO familiaInstalacionJson = new FamiliaInstalacionJsonDTO();

		familiaInstalacionJson.setId(familiaInstalacion.getId());
		familiaInstalacionJson.setNombre(familiaInstalacion.getNombre());
		
		return familiaInstalacionJson;
	}
}
