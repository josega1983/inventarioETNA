package es.enaire.inventario.converterJson;

import es.enaire.inventario.business.FamiliaElementoJsonDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;

public class FamiliaElementoConverterJSON {
	/**
	 * Hace una conversion de FamiliaElementoJsonDTO a FamiliaElementoDTO
	 * @return FamiliaElementoDTO Object
	 */
	public static FamiliaElementoDTO convertirJsonDTOToDTO(FamiliaElementoJsonDTO familiaInstalacionJson) {
		FamiliaElementoDTO familiaElemento = new FamiliaElementoDTO();

		familiaElemento.setId(familiaInstalacionJson.getId());
		familiaElemento.setNombre(familiaInstalacionJson.getNombre());
		familiaElemento.setActivo(null);
		familiaElemento.setDescripcion(null);
		familiaElemento.setObservaciones(null);
		familiaElemento.setEstadoMenu(null);
		familiaElemento.setFechaAlta(null);
		familiaElemento.setFechaBaja(null);
		
		return familiaElemento;
	}
	
	/**
	 * Hace una conversion de FamiliaInstalacionDTO a FamiliaElementoJsonDTO
	 * @return FamiliaElementoJsonDTO Object
	 */
	public static FamiliaElementoJsonDTO convertirDTOToJsonDTO(FamiliaElementoDTO familiaInstalacion) {
		FamiliaElementoJsonDTO familiaElementoJson = new FamiliaElementoJsonDTO();

		familiaElementoJson.setId(familiaInstalacion.getId());
		familiaElementoJson.setNombre(familiaInstalacion.getNombre());
		
		return familiaElementoJson;
	}
}
