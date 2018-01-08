package es.enaire.inventario.converterJson;

import java.util.ArrayList;
import java.util.List;

import es.enaire.inventario.business.ConfiguracionElementosTipoJsonDTO;
import es.enaire.inventario.dtos.ConfiguracionElementosTipoDTO;

public class ConfiguracionElementosTipoConverterJSON {
	/**
	 * Hace una conversion de configuracionElementosTipoJsonDTO a configuracionElementosTipoDTO
	 * @return configuracionElementosTipoDTO Object
	 */
	public static ConfiguracionElementosTipoDTO convertirJsonDTOToDTO(ConfiguracionElementosTipoJsonDTO configuracionElementosTipoJson) {
		ConfiguracionElementosTipoDTO configuracionElementosTipo = new ConfiguracionElementosTipoDTO();
		List<ConfiguracionElementosTipoDTO> hijos = new ArrayList<ConfiguracionElementosTipoDTO>();
		if(configuracionElementosTipoJson.getHijos() != null) {
			for(int i=0; i<configuracionElementosTipoJson.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosTipoConverterJSON.convertirJsonDTOToDTO(configuracionElementosTipoJson.getHijos().get(i)));
			}
		}
		
		configuracionElementosTipo.setId(configuracionElementosTipoJson.getId());
		configuracionElementosTipo.setFamiliaElemento(FamiliaElementoConverterJSON.convertirJsonDTOToDTO(configuracionElementosTipoJson.getFamiliaElemento()));
		configuracionElementosTipo.setHijos(hijos);
		configuracionElementosTipo.setActivo(null);
		configuracionElementosTipo.setObservaciones(null);
		configuracionElementosTipo.setEstadoMenu(null);
		configuracionElementosTipo.setFechaAlta(null);
		configuracionElementosTipo.setFechaBaja(null);
		
		return configuracionElementosTipo;
	}
	
	/**
	 * Hace una conversion de configuracionElementosTipoDTO a configuracionElementosTipoJsonDTO
	 * @return configuracionElementosTipoJsonDTO Object
	 */
	public static ConfiguracionElementosTipoJsonDTO convertirDTOToJsonDTO(ConfiguracionElementosTipoDTO configuracionElementosTipo) {
		ConfiguracionElementosTipoJsonDTO configuracionElementosTipoJson = new ConfiguracionElementosTipoJsonDTO();
		List<ConfiguracionElementosTipoJsonDTO> hijos = new ArrayList<ConfiguracionElementosTipoJsonDTO>();
		if(configuracionElementosTipo.getHijos() != null) {
			for(int i=0; i<configuracionElementosTipo.getHijos().size(); i++) {
				hijos.add(ConfiguracionElementosTipoConverterJSON.convertirDTOToJsonDTO(configuracionElementosTipo.getHijos().get(i)));
			}
		}

		configuracionElementosTipoJson.setId(configuracionElementosTipo.getId());
		configuracionElementosTipoJson.setFamiliaElemento(FamiliaElementoConverterJSON.convertirDTOToJsonDTO(configuracionElementosTipo.getFamiliaElemento()));
		configuracionElementosTipoJson.setHijos(hijos);
		
		return configuracionElementosTipoJson;
	}
}
