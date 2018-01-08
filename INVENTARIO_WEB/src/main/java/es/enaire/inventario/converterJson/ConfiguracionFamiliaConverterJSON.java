package es.enaire.inventario.converterJson;

import java.util.ArrayList;
import java.util.List;

import es.enaire.inventario.dtos.ConfiguracionElementosFamiliaDTO;
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
import es.enaire.inventario.business.ConfiguracionElementosFamiliaJsonDTO;
import es.enaire.inventario.business.ConfiguracionesFamiliaJsonDTO;

public class ConfiguracionFamiliaConverterJSON {
	/**
	 * Hace una conversion de ConfiguracionesFamiliaJsonDTO a ConfiguracionesFamiliaDTO
	 * @return ConfiguracionesFamiliaDTO Object
	 */
	public static ConfiguracionesFamiliaDTO convertirJsonDTOToDTO(ConfiguracionesFamiliaJsonDTO configuracionFamiliaJson) {
		ConfiguracionesFamiliaDTO configuracionFamilia = new ConfiguracionesFamiliaDTO();
		List<ConfiguracionElementosFamiliaDTO> hijos = new ArrayList<ConfiguracionElementosFamiliaDTO>();
		if(configuracionFamiliaJson.getHijos() != null) {
			for(int i=0; i<configuracionFamiliaJson.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosFamiliaConverterJSON.convertirJsonDTOToDTO(configuracionFamiliaJson.getHijos().get(i)));
			}
		}

		configuracionFamilia.setId(configuracionFamiliaJson.getId());
		configuracionFamilia.setActivo(configuracionFamiliaJson.getActivo());
		configuracionFamilia.setObservaciones(configuracionFamiliaJson.getObservaciones());
		configuracionFamilia.setFamiliaInstalacion(FamiliaInstalacionConverterJSON.convertirJsonDTOToDTO(configuracionFamiliaJson.getFamiliaInstalacion()));
		configuracionFamilia.setHijos(hijos);
		configuracionFamilia.setEstadoMenu(null);
		configuracionFamilia.setFechaAlta(null);
		configuracionFamilia.setFechaBaja(null);
		configuracionFamilia.setNombre(null);
		
		return configuracionFamilia;
	}
	
	/**
	 * Hace una conversion de ConfiguracionesFamiliaDTO a ConfiguracionesFamiliaJsonDTO
	 * @return ConfiguracionesFamiliaJsonDTO Object
	 */
	public static ConfiguracionesFamiliaJsonDTO convertirDTOToJsonDTO(ConfiguracionesFamiliaDTO configuracionesFamilia) {
		ConfiguracionesFamiliaJsonDTO configuracionFamiliaJson = new ConfiguracionesFamiliaJsonDTO();
		List<ConfiguracionElementosFamiliaJsonDTO> hijos = new ArrayList<ConfiguracionElementosFamiliaJsonDTO>();
		if(configuracionesFamilia.getHijos() != null) {
			for(int i=0; i<configuracionesFamilia.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosFamiliaConverterJSON.convertirDTOToJsonDTO(configuracionesFamilia.getHijos().get(i)));
			}
		}

		configuracionFamiliaJson.setId(configuracionesFamilia.getId());
		configuracionFamiliaJson.setActivo(configuracionesFamilia.getActivo());
		configuracionFamiliaJson.setObservaciones(configuracionesFamilia.getObservaciones());
		configuracionFamiliaJson.setFamiliaInstalacion(FamiliaInstalacionConverterJSON.convertirDTOToJsonDTO(configuracionesFamilia.getFamiliaInstalacion()));
		configuracionFamiliaJson.setHijos(hijos);
		
		return configuracionFamiliaJson;
	}
	
}
