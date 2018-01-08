package es.enaire.inventario.util;

import java.util.ArrayList;
import java.util.List;

import es.enaire.inventario.dtos.CentroDTO;
import es.enaire.inventario.dtos.ConfiguracionesFamiliaDTO;
import es.enaire.inventario.dtos.ConfiguracionesTipoDTO;
import es.enaire.inventario.dtos.EmplazamientoDTO;
import es.enaire.inventario.dtos.EstadoElementoDTO;
import es.enaire.inventario.dtos.EstadoInstalacionDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;
import es.enaire.inventario.dtos.FamiliaInstalacionDTO;
import es.enaire.inventario.dtos.FilaExcelDTO;
import es.enaire.inventario.dtos.InformeExcelDTO;
import es.enaire.inventario.dtos.LocalizacionDTO;
import es.enaire.inventario.dtos.ParametroFuncionalDTO;
import es.enaire.inventario.dtos.PropietarioActivoDTO;
import es.enaire.inventario.dtos.RegionDTO;
import es.enaire.inventario.dtos.ResponsableMantenimientoDTO;
import es.enaire.inventario.dtos.SectorDTO;
import es.enaire.inventario.dtos.TipoEmplazamientoDTO;
import es.enaire.inventario.dtos.TipoInstalacionDTO;
import es.enaire.inventario.dtos.UbicacionFisicaDTO;
import es.enaire.inventario.dtos.UbicacionLogicaDTO;
import es.enaire.inventario.dtos.UbicacionLogicaFisicaDTO;
import es.enaire.inventario.dtos.UnidadMantenimientoDTO;

/**
 * Clase util que dado un DTO nos lo convierte en un InformeExcelDTO
 *
 */
public class ConverterInformeExcel {
	/**
	 * Metodo que convierte un listado de centroDTO en un informe excel DTO
	 * @param centroList una lista de centros
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromCentros(List<CentroDTO> centroList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoCentros");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (centroList != null && centroList.size() >0) {
			for(CentroDTO centro: centroList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = centro.getId().toString();
				fila[1]= centro.getNombre();
				fila[2] =centro.getObservaciones();
				fila[3] = centro.getActivo();
				if(centro.getFechaBaja() != null) {
					fila[4] = centro.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de regionDTO en un informe excel DTO
	 * @param regionList una lista de regions
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromRegiones(List<RegionDTO> regionList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoRegions");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (regionList != null && regionList.size() >0) {
			for(RegionDTO region: regionList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = region.getId().toString();
				fila[1]= region.getNombre();
				fila[2] =region.getObservaciones();
				fila[3] = region.getActivo();
				if(region.getFechaBaja() != null) {
					fila[4] = region.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de sectorDTO en un informe excel DTO
	 * @param sectorList una lista de sectors
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromSectores(List<SectorDTO> sectorList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoSectors");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (sectorList != null && sectorList.size() >0) {
			for(SectorDTO sector: sectorList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = sector.getId().toString();
				fila[1]= sector.getNombre();
				fila[2] =sector.getObservaciones();
				fila[3] = sector.getActivo();
				if(sector.getFechaBaja() != null) {
					fila[4] = sector.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de tipoEmplazamientoDTO en un informe excel DTO
	 * @param tipoEmplazamientoList una lista de tipoEmplazamientos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromTipoEmplazamientos(List<TipoEmplazamientoDTO> tipoEmplazamientoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoTipoEmplazamientos");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (tipoEmplazamientoList != null && tipoEmplazamientoList.size() >0) {
			for(TipoEmplazamientoDTO tipoEmplazamiento: tipoEmplazamientoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = tipoEmplazamiento.getId().toString();
				fila[1]= tipoEmplazamiento.getNombre();
				fila[2] =tipoEmplazamiento.getObservaciones();
				fila[3] = tipoEmplazamiento.getActivo();
				if(tipoEmplazamiento.getFechaBaja() != null) {
					fila[4] = tipoEmplazamiento.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de tipoEmplazamientoDTO en un informe excel DTO
	 * @param tipoEmplazamientoList una lista de tipoEmplazamientos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromEmplazamientos(List<EmplazamientoDTO> emplazamientoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoTipoEmplazamientos");
		String[] cabecera ={"Id","Nombre","Tipo Emplazamiento","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (emplazamientoList != null && emplazamientoList.size() >0) {
			for(EmplazamientoDTO emplazamiento: emplazamientoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = emplazamiento.getId().toString();
				fila[1]= emplazamiento.getNombre();
				fila[2]= emplazamiento.getTipoEmplazamiento().getNombre();
				fila[3] =emplazamiento.getObservaciones();
				fila[4] = emplazamiento.getActivo();
				if(emplazamiento.getFechaBaja() != null) {
					fila[5] = emplazamiento.getFechaBaja().toString();
				}
				else {
					fila[5] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de propietarioActivoDTO en un informe excel DTO
	 * @param propietarioActivoList una lista de propietarioActivos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromPropietarioActivos(List<PropietarioActivoDTO> propietarioActivoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoPropietarioActivos");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (propietarioActivoList != null && propietarioActivoList.size() >0) {
			for(PropietarioActivoDTO propietarioActivo: propietarioActivoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = propietarioActivo.getId().toString();
				fila[1]= propietarioActivo.getNombre();
				fila[2] =propietarioActivo.getObservaciones();
				fila[3] = propietarioActivo.getActivo();
				if(propietarioActivo.getFechaBaja() != null) {
					fila[4] = propietarioActivo.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de responsableMantenimientoDTO en un informe excel DTO
	 * @param responsableMantenimientoList una lista de responsableMantenimientos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromResponsableMantenimientos(List<ResponsableMantenimientoDTO> responsableMantenimientoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoResponsableMantenimientos");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (responsableMantenimientoList != null && responsableMantenimientoList.size() >0) {
			for(ResponsableMantenimientoDTO responsableMantenimiento: responsableMantenimientoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = responsableMantenimiento.getId().toString();
				fila[1]= responsableMantenimiento.getNombre();
				fila[2] =responsableMantenimiento.getObservaciones();
				fila[3] = responsableMantenimiento.getActivo();
				if(responsableMantenimiento.getFechaBaja() != null) {
					fila[4] = responsableMantenimiento.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de unidadMantenimientoDTO en un informe excel DTO
	 * @param unidadMantenimientoList una lista de unidadMantenimientos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromUnidadMantenimientos(List<UnidadMantenimientoDTO> unidadMantenimientoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoUnidadMantenimientos");
		String[] cabecera ={"Id","Nombre","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (unidadMantenimientoList != null && unidadMantenimientoList.size() >0) {
			for(UnidadMantenimientoDTO unidadMantenimiento: unidadMantenimientoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = unidadMantenimiento.getId().toString();
				fila[1]= unidadMantenimiento.getNombre();
				fila[2] =unidadMantenimiento.getObservaciones();
				fila[3] = unidadMantenimiento.getActivo();
				if(unidadMantenimiento.getFechaBaja() != null) {
					fila[4] = unidadMantenimiento.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de localizacionDTO en un informe excel DTO
	 * @param localizacionList una lista de localizacions
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromLocalizacions(List<LocalizacionDTO> localizacionList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoLocalizacions");
		String[] cabecera ={"Id","Nombre","Código AIP","Observaciones","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (localizacionList != null && localizacionList.size() >0) {
			for(LocalizacionDTO localizacion: localizacionList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = localizacion.getId().toString();
				fila[1]= localizacion.getNombre();
				fila[2]=localizacion.getCodigoAIP();
				fila[3] =localizacion.getObservaciones();
				fila[4] = localizacion.getActivo();
				if(localizacion.getFechaBaja() != null) {
					fila[5] = localizacion.getFechaBaja().toString();
				}
				else {
					fila[5] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de estadoElementoDTO en un informe excel DTO
	 * @param estadoElementoList una lista de estadoElementos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromEstadoElementos(List<EstadoElementoDTO> estadoElementoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoEstadoElementos");
		String[] cabecera ={"Id","Nombre","Activo","Fecha Alta","Fecha de Baja","Observaciones"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (estadoElementoList != null && estadoElementoList.size() >0) {
			for(EstadoElementoDTO estadoElemento: estadoElementoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = estadoElemento.getId().toString();
				fila[1]= estadoElemento.getNombre();
				fila[2] = estadoElemento.getActivo();
				if(estadoElemento.getFechaAlta() !=null){
					fila[3] = estadoElemento.getFechaAlta().toString();
				}
				else{
					fila[3] = "";
				}
				if(estadoElemento.getFechaBaja() != null) {
					fila[4] = estadoElemento.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				fila[5] =estadoElemento.getObservaciones();
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de estadoInstalacionDTO en un informe excel DTO
	 * @param estadoInstalacionList una lista de estadoInstalacions
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromEstadoInstalacions(List<EstadoInstalacionDTO> estadoInstalacionList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoEstadoInstalacions");
		String[] cabecera ={"Id","Nombre","Activo","Fecha Alta","Fecha de Baja","Observaciones"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (estadoInstalacionList != null && estadoInstalacionList.size() >0) {
			for(EstadoInstalacionDTO estadoInstalacion: estadoInstalacionList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = estadoInstalacion.getId().toString();
				fila[1]= estadoInstalacion.getNombre();
				fila[2] = estadoInstalacion.getActivo();
				if(estadoInstalacion.getFechaAlta() !=null){
					fila[3] = estadoInstalacion.getFechaAlta().toString();
				}
				else{
					fila[3] = "";
				}
				if(estadoInstalacion.getFechaBaja() != null) {
					fila[4] = estadoInstalacion.getFechaBaja().toString();
				}
				else {
					fila[4] = "";
				}
				fila[5] =estadoInstalacion.getObservaciones();
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de ubicacionFisicaDTO en un informe excel DTO
	 * @param ubicacionFisicaList una lista de ubicacionFisicas
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromUbicacionFisicas(List<UbicacionFisicaDTO> ubicacionFisicaList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoUbicacionFisicas");
		String[] cabecera ={"Id","Nombre","Region","Localizacion", "Emplazamiento","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (ubicacionFisicaList != null && ubicacionFisicaList.size() >0) {
			for(UbicacionFisicaDTO ubicacionFisica: ubicacionFisicaList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = ubicacionFisica.getId().toString();
				fila[1]= ubicacionFisica.getNombre();
				fila[2] = ubicacionFisica.getRegion().getNombre();
				fila[3] = ubicacionFisica.getLocalizacion().getNombre();
				fila[4] = ubicacionFisica.getEmplazamiento().getNombre();
				fila[5] = ubicacionFisica.getActivo();
				if(ubicacionFisica.getFechaBaja() != null) {
					fila[6] = ubicacionFisica.getFechaBaja().toString();
				}
				else {
					fila[6] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de ubicacionLogicaDTO en un informe excel DTO
	 * @param ubicacionLogicaList una lista de ubicacionLogicas
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromUbicacionLogicas(List<UbicacionLogicaDTO> ubicacionLogicaList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoUbicacionLogicas");
		String[] cabecera ={"Id","Nombre","Region","Sector", "Centro","Unidad Mantenimiento","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (ubicacionLogicaList != null && ubicacionLogicaList.size() >0) {
			for(UbicacionLogicaDTO ubicacionLogica: ubicacionLogicaList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = ubicacionLogica.getId().toString();
				fila[1]= ubicacionLogica.getNombre();
				fila[2] = ubicacionLogica.getRegion().getNombre();
				fila[3] = ubicacionLogica.getSector().getNombre();
				fila[4] = ubicacionLogica.getCentro().getNombre();
				fila[5] = ubicacionLogica.getUnidadMantenimiento().getNombre();
				fila[6] = ubicacionLogica.getActivo();
				if(ubicacionLogica.getFechaBaja() != null) {
					fila[7] = ubicacionLogica.getFechaBaja().toString();
				}
				else {
					fila[7] = "";
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de ubicacionLogicaFisicaDTO en un informe excel DTO
	 * @param ubicacionLogicaFisicaList una lista de ubicacionLogicaFisicas
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromUbicacionLogicaFisicas(List<UbicacionLogicaFisicaDTO> ubicacionLogicaFisicaList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoUbicacionLogicaFisicas");
		String[] cabecera ={"Id","Ubicación Física","Ubicación Lógica","Observaciones"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (ubicacionLogicaFisicaList != null && ubicacionLogicaFisicaList.size() >0) {
			for(UbicacionLogicaFisicaDTO ubicacionLogicaFisica: ubicacionLogicaFisicaList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = ubicacionLogicaFisica.getId().toString();
				fila[1] = "";
				if(ubicacionLogicaFisica.getUbicacionFisica() != null){
					UbicacionFisicaDTO ubicacionFisica = ubicacionLogicaFisica.getUbicacionFisica();
					fila[1]= ubicacionFisica.getRegion().getNombre() + "-"+ubicacionFisica.getEmplazamiento().getNombre() + "-"+ubicacionFisica.getLocalizacion().getNombre();
				}

				fila[2] = "";
				if(ubicacionLogicaFisica.getUbicacionLogica() != null){
					UbicacionLogicaDTO ubicacionLogica = ubicacionLogicaFisica.getUbicacionLogica();
					fila[2] = ubicacionLogica.getRegion().getNombre() + "-"+ubicacionLogica.getSector().getNombre()+"-"+
							ubicacionLogica.getCentro().getNombre()+"-"+ubicacionLogica.getUnidadMantenimiento().getNombre();
				}
				fila[3] = ubicacionLogicaFisica.getObservaciones();
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de familiaInstalacionDTO en un informe excel DTO
	 * @param familiaInstalacionList una lista de familiaInstalacions
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromFamiliaInstalacions(List<FamiliaInstalacionDTO> familiaInstalacionList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoFamiliaInstalacions");
		String[] cabecera ={"Id","Nombre","Area","Activo","Fecha Alta","Fecha de Baja", "Observaciones"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (familiaInstalacionList != null && familiaInstalacionList.size() >0) {
			for(FamiliaInstalacionDTO familiaInstalacion: familiaInstalacionList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = familiaInstalacion.getId().toString();
				fila[1]= familiaInstalacion.getNombre();
				fila[2] =familiaInstalacion.getArea();
				fila[3] = familiaInstalacion.getActivo();
				fila[4] = "";
				if(familiaInstalacion.getFechaAlta() != null){
					fila[4] = familiaInstalacion.getFechaAlta().toString();
				}
				fila[5] = "";
				if(familiaInstalacion.getFechaBaja() != null) {
					fila[5] = familiaInstalacion.getFechaBaja().toString();
				}
				fila[6]= familiaInstalacion.getObservaciones();
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de familiaElementoDTO en un informe excel DTO
	 * @param familiaElementoList una lista de familiaElementos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromFamiliaElementos(List<FamiliaElementoDTO> familiaElementoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoFamiliaElementos");
		String[] cabecera ={"Id","Nombre","Descripcion","Activo","Fecha Alta","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (familiaElementoList != null && familiaElementoList.size() >0) {
			for(FamiliaElementoDTO familiaElemento: familiaElementoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = familiaElemento.getId().toString();
				fila[1]= familiaElemento.getNombre();
				fila[2] =familiaElemento.getDescripcion();
				fila[3] = familiaElemento.getActivo();
				fila[4] = "";
				if(familiaElemento.getFechaAlta() != null){
					fila[4] = familiaElemento.getFechaAlta().toString();
				}
				fila[5] = "";
				if(familiaElemento.getFechaBaja() != null) {
					fila[5] = familiaElemento.getFechaBaja().toString();
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de tipoInstalacionDTO en un informe excel DTO
	 * @param tipoInstalacionList una lista de tipoInstalacion
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromTipoInstalacion(List<TipoInstalacionDTO> tipoInstalacionList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoTipoInstalacion");
		String[] cabecera ={"Id","Familia Instalacion","Marca","Modelo","Activo","Fecha Alta","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (tipoInstalacionList != null && tipoInstalacionList.size() >0) {
			for(TipoInstalacionDTO tipoInstalacion: tipoInstalacionList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = tipoInstalacion.getId().toString();
				fila[1]= tipoInstalacion.getFamiliaInstalacion().getNombre();
				fila[2] =tipoInstalacion.getMarca();
				fila[3] = tipoInstalacion.getModelo();
				fila[4] = tipoInstalacion.getActivo();
				fila[5] = "";
				if(tipoInstalacion.getFechaAlta() != null){
					fila[5] = tipoInstalacion.getFechaAlta().toString();
				}
				fila[6] = "";
				if(tipoInstalacion.getFechaBaja() != null) {
					fila[6] = tipoInstalacion.getFechaBaja().toString();
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de parametroFuncionalDTO en un informe excel DTO
	 * @param parametroFuncionalList una lista de parametroFuncionals
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromParametroFuncionals(List<ParametroFuncionalDTO> parametroFuncionalList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoParametroFuncionals");
		String[] cabecera ={"Id","Nombre","Descripcion","Campos","Activo","Fecha Alta"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (parametroFuncionalList != null && parametroFuncionalList.size() >0) {
			for(ParametroFuncionalDTO parametroFuncional: parametroFuncionalList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = parametroFuncional.getId().toString();
				fila[1]= parametroFuncional.getNombre();
				fila[2] =parametroFuncional.getDescripcion();
				fila[3] = Utilidades.convertConfiguracionParametroFuncionalToString(parametroFuncional.getConfiguracionParametroFuncionalList());
				fila[4] = parametroFuncional.getActivo();
				fila[5] = "";
				if(parametroFuncional.getFechaAlta() != null){
					fila[5] = parametroFuncional.getFechaAlta().toString();
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de configuracionFamiliaDTO en un informe excel DTO
	 * @param configuracionFamiliaList una lista de configuracionFamilias
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromConfiguracionFamilias(List<ConfiguracionesFamiliaDTO> configuracionFamiliaList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoConfiguracionFamilias");
		String[] cabecera ={"Id","Nombre","Familia Instalación","Observaciones","Fecha Alta","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (configuracionFamiliaList != null && configuracionFamiliaList.size() >0) {
			for(ConfiguracionesFamiliaDTO configuracionFamilia: configuracionFamiliaList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = configuracionFamilia.getId().toString();
				fila[1]= configuracionFamilia.getNombre();
				fila[2]=configuracionFamilia.getFamiliaInstalacion().getNombre();
				fila[3] =configuracionFamilia.getObservaciones();
				fila[4] = "";
				if(configuracionFamilia.getFechaAlta() != null){
					fila[4] = configuracionFamilia.getFechaAlta().toString();
				}
				fila[5] = configuracionFamilia.getActivo();
				fila[6] = "";
				if(configuracionFamilia.getFechaBaja() != null) {
					fila[6] = configuracionFamilia.getFechaBaja().toString();
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
	/**
	 * Metodo que convierte un listado de configuracionTipoDTO en un informe excel DTO
	 * @param configuracionTipoList una lista de configuracionTipos
	 * @return informe Excel
	 */
	public static InformeExcelDTO  convertFromConfiguracionTipos(List<ConfiguracionesTipoDTO> configuracionTipoList) {
		InformeExcelDTO informeExcel = new InformeExcelDTO();
		informeExcel.setNombre("ListadoConfiguracionTipos");
		String[] cabecera ={"Id","Familia Instalacion","Marca","Modelo","Fecha Alta","Activo","Fecha de Baja"};
		informeExcel.setCabecera(cabecera);
		List<FilaExcelDTO> filas = new ArrayList<FilaExcelDTO>();
		if (configuracionTipoList != null && configuracionTipoList.size() >0) {
			for(ConfiguracionesTipoDTO configuracionTipo: configuracionTipoList){
				FilaExcelDTO filaExcel = new FilaExcelDTO();
				String[] fila = new String[cabecera.length];
				fila[0] = configuracionTipo.getId().toString();
				fila[1] = "";
				fila[2] = "";
				fila[3] = "";
				if(configuracionTipo.getTipoInstalacion() != null){
					TipoInstalacionDTO tipoInstalacion = configuracionTipo.getTipoInstalacion();
					fila[1] = tipoInstalacion.getFamiliaInstalacion().getNombre();
					fila[2] = tipoInstalacion.getMarca();
					fila[3] = tipoInstalacion.getModelo();
				}
				fila[4] = "";
				if(configuracionTipo.getFechaAlta() != null){
					fila[4] = configuracionTipo.getFechaAlta().toString();
				}
				fila[5] = configuracionTipo.getActivo();
				fila[6] = "";
				if(configuracionTipo.getFechaBaja() != null) {
					fila[6] = configuracionTipo.getFechaBaja().toString();
				}
				filaExcel.setFila(fila);
				filas.add(filaExcel);
			}

		}
		informeExcel.setFilas(filas);
		return informeExcel;
	}
}
