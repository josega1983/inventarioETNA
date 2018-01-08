package es.enaire.inventario.dtos;

import java.io.File;
import java.io.Serializable;

import es.enaire.inventario.annotations.IdTarget;

public class FicheroDTO implements Serializable {
	/**
	 * Identificador de serializacion
	 */
	private static final long serialVersionUID = 4688379525743290573L;
	/**
	 * El identificador del area.
	 */
	@IdTarget
	private Long id;
	/**
	 * Fichero
	 */
	private File file;
	/**
	 * ContentType
	 */
    private String fileContentType;
    /**
     * Nombre del fichero
     */
	private String fileFileName;
	/**
	 * Obtiene el identificador del fichero.
	 * @return id El identificador del fichero.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Establece el identificador del fichero.
	 * @param id El identificador del fichero.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Obtiene el fichero
	 * @return el fichero
	 */
    public File getFile() {
		return file;
	}
    /**
     * Establece el fichero
     * @param file el fichero
     */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * Obtiene el ContentType
	 * @return el ContentType
	 */
	public String getFileContentType() {
		return fileContentType;
	}
	/**
	 * Establece el ContentType
	 * @param fileContentType el ContentType
	 */ 
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	/**
	 * Obtiene el nombre del fichero
	 * @return el nombre del fichero
	 */
	public String getFileFileName() {
		return fileFileName;
	}
	/**
	 * Establece el nombre del fichero
	 * @param fileFileName el nombre del fichero
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
