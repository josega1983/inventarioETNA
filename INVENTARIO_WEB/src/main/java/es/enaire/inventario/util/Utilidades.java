package es.enaire.inventario.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.enaire.inventario.backend.IFacadeMIBackend;
import es.enaire.inventario.business.FiltroFamiliaElemento;
import es.enaire.inventario.constants.CodigosRespuestaConstants;
import es.enaire.inventario.constants.TipoEntradaConstants;
import es.enaire.inventario.dtos.ConfiguracionParametroFuncionalDTO;
import es.enaire.inventario.dtos.EntradaDTO;
import es.enaire.inventario.dtos.FamiliaElementoDTO;
import es.enaire.inventario.dtos.RespuestaDTO;


/**
 * Clase que tiene funcionalidades utiles de conversion o formateo de campos.
 *
 */
public class Utilidades {
	/**
	 * Indicador para almacenar en las entidades el valor TRUE de los campos booleanos de la entidad.
	 */
	public final static String SI = "SI";

	/**
	 * Indicador para almacenar en las entidades el valor FALSE de los campos booleanos de la entidad.
	 */
	public final static String NO = "NO";
	/**
	 * Convierte un fichero a un array de bytes.
	 * @param archivo El fichero a convertir.
	 * @return La lista de bytes que representan al fichero.
	 */
	public static byte[] convertFileToBytes(File archivo) {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		if (archivo !=null) {
			try {
				bytesArray = new byte[(int) archivo.length()];
				fileInputStream = new FileInputStream(archivo);
				fileInputStream.read(bytesArray);
			} catch (IOException e) {
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
		return bytesArray;
	}
	/**
	 * Metodo que nos da una fecha en formato "DD/MM/YYYY HH:mm:ss" introducida una fecha de tipo Date
	 */
	public static String convertDate(Date date) {
	    String pattern = "dd/MM/YYYY HH:mm:ss";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String dateSalida = format.format(date);
	    return dateSalida;
	}
	/**
	 * Metodo que convierte un archivo en un strin reader
	 * @param file archivo a convertir
	 * @return string reader convertido
	 * @throws Exception Excepcion producida en el tratamiento de archivos
	 */
	@SuppressWarnings("resource")
	public static StringReader convertFileToStringReader(File file) throws Exception {
		StringReader stringReader = null;
		BufferedReader rd = new BufferedReader(new FileReader(file));
		String inputLine = null;
		StringBuilder builder = new StringBuilder();
        //Store the contents of the file to the StringBuilder.
        while((inputLine = rd.readLine()) != null){
        	builder.append(inputLine).append("\n");
        }
        //Create a new tokenizer based on the StringReader class instance.
        stringReader = new StringReader(builder.toString());

		return stringReader;
	}


	/**
	 * Convierte un array en un String
	 * @param objetos
	 * @return
	 */
	public static String convertArrayToString(Object [] objetos){
		String cadena = "";
		if (objetos != null && objetos.length>0) {
			for (Object objeto : objetos) {
				cadena += objeto.toString() + " ,";
			}
			cadena = cadena.substring(0, cadena.length() - 2); // Eliminamos la ultima coma
		}
		return cadena;
	}


	/**
	 * Convierte un array en un listado
	 * @param <T>
	 * @param objetos
	 * @return
	 */
	public static <T> List<T> convertArrayToList(Object [] objetos){
		List <T> listado = new ArrayList<T>();
		if (objetos != null && objetos.length>0) {
			for (Object objeto : objetos) {
				listado.add((T) objeto);
			}
		}
		return listado;
	}


	/**
	 * Obtiene el listado de familia de elementos
	 * @param facadeMIBackend Instancia del metodo fachada
	 */
	public static final List<FamiliaElementoDTO> getListadoFamiliaElementos(IFacadeMIBackend facadeMIBackend) {
		List<FamiliaElementoDTO> list = new ArrayList<FamiliaElementoDTO>();
		FiltroFamiliaElemento filtroFamiliaElemento = new FiltroFamiliaElemento();
		filtroFamiliaElemento.setActivo(SI);
		RespuestaDTO respuestaFamiliaElemento = facadeMIBackend.search(new EntradaDTO(filtroFamiliaElemento, 0L, 100L, TipoEntradaConstants.FAMILIA_ELEMENTO, null));
		if(respuestaFamiliaElemento.getCodigoRespuesta().equals(CodigosRespuestaConstants.RESPUESTA_OK)){
			Object[] objetoRespuesta = respuestaFamiliaElemento.getObjetoRespuesta();
			if(objetoRespuesta != null){
				for (Object object : objetoRespuesta) {
					FamiliaElementoDTO familiaElemento = (FamiliaElementoDTO) object;
					list.add(familiaElemento);
				}
			}
		}
		return list;
	}
	/**
	 * Transforma una lista de campos de parametros funcionales en un cadena separada por comas
	 * @param configuracionParametroFuncionalList introducida 
	 * @return cadena 
	 */
	public static String convertConfiguracionParametroFuncionalToString(List<ConfiguracionParametroFuncionalDTO> configuracionParametroFuncionalList) {
		String cadena = "";
		if(configuracionParametroFuncionalList != null && configuracionParametroFuncionalList.size()>0){
			for (ConfiguracionParametroFuncionalDTO configuracionParametroFuncional : configuracionParametroFuncionalList) {
				cadena += configuracionParametroFuncional.getNombreCampo()+",";
			}
			cadena = cadena.substring(0, cadena.length() - 1); // Eliminamos la ultima coma
		}
		return cadena; 
	}
}
