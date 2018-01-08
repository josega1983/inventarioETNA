package es.enaire.inventario.converterJson;

import java.util.ArrayList;
import java.util.List;

import es.enaire.inventario.business.ConfiguracionElementosTipoJsonDTO;
import es.enaire.inventario.business.ConfiguracionesTipoJsonDTO;
import es.enaire.inventario.dtos.ConfiguracionElementosTipoDTO;
import es.enaire.inventario.dtos.ConfiguracionesTipoDTO;

public class ConfiguracionTipoConverterJSON {
	/**
	 * Hace una conversion de ConfiguracionesTipoJsonDTO a ConfiguracionesTipoDTO
	 * @return ConfiguracionesTipoDTO Object
	 */
	public static ConfiguracionesTipoDTO convertirJsonDTOToDTO(ConfiguracionesTipoJsonDTO configuracionTipoJson) {
		ConfiguracionesTipoDTO configuracionTipo = new ConfiguracionesTipoDTO();
		List<ConfiguracionElementosTipoDTO> hijos = new ArrayList<ConfiguracionElementosTipoDTO>();
		if(configuracionTipoJson.getHijos() != null) {
			for(int i=0; i<configuracionTipoJson.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosTipoConverterJSON.convertirJsonDTOToDTO(configuracionTipoJson.getHijos().get(i)));
			}
		}

		configuracionTipo.setId(configuracionTipoJson.getId());
		configuracionTipo.setActivo(configuracionTipoJson.getActivo());
		configuracionTipo.setObservaciones(configuracionTipoJson.getObservaciones());
		configuracionTipo.setTipoInstalacion(TipoInstalacionConverterJSON.convertirJsonDTOToDTO(configuracionTipoJson.getTipoInstalacion()));
		configuracionTipo.setHijos(hijos);
		configuracionTipo.setEstadoMenu(null);
		configuracionTipo.setFechaAlta(null);
		configuracionTipo.setFechaBaja(null);
		configuracionTipo.setNombre(null);
		
		return configuracionTipo;
	}
	
	/**
	 * Hace una conversion de ConfiguracionesTipoDTO a ConfiguracionesTipoJsonDTO
	 * @return ConfiguracionesTipoJsonDTO Object
	 */
	public static ConfiguracionesTipoJsonDTO convertirDTOToJsonDTO(ConfiguracionesTipoDTO configuracionesTipo) {
		ConfiguracionesTipoJsonDTO configuracionTipoJson = new ConfiguracionesTipoJsonDTO();
		List<ConfiguracionElementosTipoJsonDTO> hijos = new ArrayList<ConfiguracionElementosTipoJsonDTO>();
		if(configuracionesTipo.getHijos() != null) {
			for(int i=0; i<configuracionesTipo.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosTipoConverterJSON.convertirDTOToJsonDTO(configuracionesTipo.getHijos().get(i)));
			}
		}

		configuracionTipoJson.setId(configuracionesTipo.getId());
		configuracionTipoJson.setActivo(configuracionesTipo.getActivo());
		configuracionTipoJson.setObservaciones(configuracionesTipo.getObservaciones());
		configuracionTipoJson.setTipoInstalacion(TipoInstalacionConverterJSON.convertirDTOToJsonDTO(configuracionesTipo.getTipoInstalacion()));
		configuracionTipoJson.setHijos(hijos);
		
		return configuracionTipoJson;
	}
}
