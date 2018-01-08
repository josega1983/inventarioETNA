package es.enaire.inventario.converterJson;

import es.enaire.inventario.business.TipoInstalacionJsonDTO;
import es.enaire.inventario.dtos.TipoInstalacionDTO;

public class TipoInstalacionConverterJSON {
	/**
	 * Hace una conversion de TipoInstalacionJsonDTO a TipoInstalacionDTO
	 * @return TipoInstalacionDTO Object
	 */
	public static TipoInstalacionDTO convertirJsonDTOToDTO(TipoInstalacionJsonDTO tipoInstalacionJson) {
		TipoInstalacionDTO tipoInstalacion = new TipoInstalacionDTO();

		tipoInstalacion.setId(tipoInstalacionJson.getId());
		tipoInstalacion.setFamiliaInstalacion(FamiliaInstalacionConverterJSON.convertirJsonDTOToDTO(tipoInstalacionJson.getFamiliaInstalacion()));
		tipoInstalacion.setMarca(tipoInstalacionJson.getMarca());
		tipoInstalacion.setModelo(tipoInstalacionJson.getModelo());
		tipoInstalacion.setActivo(null);
		tipoInstalacion.setObservaciones(null);
		tipoInstalacion.setEstadoMenu(null);
		tipoInstalacion.setFechaAlta(null);
		tipoInstalacion.setFechaBaja(null);
		
		return tipoInstalacion;
	}
	
	/**
	 * Hace una conversion de TipoInstalacionDTO a TipoInstalacionJsonDTO
	 * @return TipoInstalacionJsonDTO Object
	 */
	public static TipoInstalacionJsonDTO convertirDTOToJsonDTO(TipoInstalacionDTO tipoInstalacion) {
		TipoInstalacionJsonDTO tipoInstalacionJson = new TipoInstalacionJsonDTO();

		tipoInstalacionJson.setId(tipoInstalacion.getId());
		tipoInstalacionJson.setFamiliaInstalacion(FamiliaInstalacionConverterJSON.convertirDTOToJsonDTO(tipoInstalacion.getFamiliaInstalacion()));
		tipoInstalacionJson.setMarca(tipoInstalacion.getMarca());
		tipoInstalacionJson.setModelo(tipoInstalacion.getModelo());
		
		return tipoInstalacionJson;
	}
}
