package es.enaire.inventario.actions;

import java.io.InputStream;


public class VisualizarArchivoAction<T> extends BaseAction<T> {


	private static final long serialVersionUID = -5771168819554524338L;
	/**
	 * Identificador del fichero.
	 */
	private Long idFichero;
	/**
	 * Stream para enviar el resultado.
	 */
	private InputStream inputStream;
	/**
	 * Nombre del fichero.
	 */
	private String nombreArchivo;
	/**
	 * Tamaño del fichero en bytes.
	 */
	private long bytesArchivo;

	
	/**
	 * Obtiene el valor de la variable inputStream para fichero JSON.
	 * @return inputStream El valor de la variable inputStream para fichero JSON.
	 */
	public InputStream getInputStream() {
        return inputStream;
    }

	/**
	 * Obtiene el identificador del fichero.
	 * @return El identificador del fichero.
	 */
	public Long getIdFichero() {
		return idFichero;
	}

	/**
	 * Establece el identificador del fichero.
	 * @param idFichero El identificador del fichero.
	 */
	public void setIdFichero(Long idFichero) {
		this.idFichero = idFichero;
	}

	/**
	 * Obtiene el nombre del fichero.
	 * @return El nombre del fichero.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Obtiene el tamanio del fichero.
	 * @return El tamanio del fichero.
	 */
	public long getBytesArchivo() {
	    return bytesArchivo;
	}
	
	/**
	 * Metodo para aplicar los menus de acciones en funcion del estado del elemento.
	 * @param resultado El resultado del listado para aplicar el estado del menu.
	 */
	@Override
	public void aplicarEstadoMenu(T resultado) {
	}
}
