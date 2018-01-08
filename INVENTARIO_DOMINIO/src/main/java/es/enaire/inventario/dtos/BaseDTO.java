package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.List;

public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3260707415110219756L;
	
	private List<?> modificacionesList;

	public List<?> getModificacionesList() {
		return modificacionesList;
	}

	public void setModificacionesList(List<?> modificacionesList) {
		this.modificacionesList = modificacionesList;
	}
	

}
