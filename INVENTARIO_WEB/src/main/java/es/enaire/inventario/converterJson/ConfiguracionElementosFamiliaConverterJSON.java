package es.enaire.inventario.converterJson;

import java.util.ArrayList;
import java.util.List;

import es.enaire.inventario.business.ConfiguracionElementosFamiliaJsonDTO;
import es.enaire.inventario.dtos.ConfiguracionElementosFamiliaDTO;

public class ConfiguracionElementosFamiliaConverterJSON {
	/**
	 * Hace una conversion de ConfiguracionElementosFamiliaJsonDTO a ConfiguracionElementosFamiliaDTO
	 * @return ConfiguracionElementosFamiliaDTO Object
	 */
	public static ConfiguracionElementosFamiliaDTO convertirJsonDTOToDTO(ConfiguracionElementosFamiliaJsonDTO configuracionElementosFamiliaJson) {
		ConfiguracionElementosFamiliaDTO configuracionElementosFamilia = new ConfiguracionElementosFamiliaDTO();
		List<ConfiguracionElementosFamiliaDTO> hijos = new ArrayList<ConfiguracionElementosFamiliaDTO>();
		if(configuracionElementosFamiliaJson.getHijos() != null) {
			for(int i=0; i<configuracionElementosFamiliaJson.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosFamiliaConverterJSON.convertirJsonDTOToDTO(configuracionElementosFamiliaJson.getHijos().get(i)));
			}
		}

		configuracionElementosFamilia.setId(configuracionElementosFamiliaJson.getId());
		configuracionElementosFamilia.setFamiliaElemento(FamiliaElementoConverterJSON.convertirJsonDTOToDTO(configuracionElementosFamiliaJson.getFamiliaElemento()));
		configuracionElementosFamilia.setHijos(hijos);
		configuracionElementosFamilia.setActivo(null);
		configuracionElementosFamilia.setObservaciones(null);
		configuracionElementosFamilia.setEstadoMenu(null);
		configuracionElementosFamilia.setFechaAlta(null);
		configuracionElementosFamilia.setFechaBaja(null);
		
		return configuracionElementosFamilia;
	}
	
	/**
	 * Hace una conversion de ConfiguracionElementosFamiliaDTO a ConfiguracionElementosFamiliaJsonDTO
	 * @return ConfiguracionElementosFamiliaJsonDTO Object
	 */
	public static ConfiguracionElementosFamiliaJsonDTO convertirDTOToJsonDTO(ConfiguracionElementosFamiliaDTO configuracionElementosFamilia) {
		ConfiguracionElementosFamiliaJsonDTO configuracionElementosFamiliaJson = new ConfiguracionElementosFamiliaJsonDTO();
		List<ConfiguracionElementosFamiliaJsonDTO> hijos = new ArrayList<ConfiguracionElementosFamiliaJsonDTO>();
		if(configuracionElementosFamilia.getHijos() != null) {
			for(int i=0; i<configuracionElementosFamilia.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosFamiliaConverterJSON.convertirDTOToJsonDTO(configuracionElementosFamilia.getHijos().get(i)));
			}
		}

		configuracionElementosFamiliaJson.setId(configuracionElementosFamilia.getId());
		configuracionElementosFamiliaJson.setFamiliaElemento(FamiliaElementoConverterJSON.convertirDTOToJsonDTO(configuracionElementosFamilia.getFamiliaElemento()));
		configuracionElementosFamiliaJson.setHijos(hijos);
		
		return configuracionElementosFamiliaJson;
	}
}
