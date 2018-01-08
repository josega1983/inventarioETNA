package es.enaire.inventario.business;

import java.io.Serializable;

public class FiltroUsuario extends BaseFiltro implements Serializable{

	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = 5900147646150094634L;

	/**
	 * Nombre de usuario
	 */
	private String username;

	/**
	 * Email del usuario
	 */
	private String email;


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Obtiene el email del filtro
	 * @return El mail del filtro de usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el mail del filtro de usuario
	 * @param email El mail del filtro de usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve un string con todos los campos del filtro activos separados por comas para su representacion
	 * @return listCamposFiltro
	 */
	public String generateStringCamposFiltro(){
		String listCamposFiltro = "";

		if(getId() != null){
			listCamposFiltro = listCamposFiltro.concat("Id").concat(", ");
		}
		if(getUsername() != null){
			listCamposFiltro = listCamposFiltro.concat("Username").concat(", ");
		}
		if(getEmail() != null){
			listCamposFiltro = listCamposFiltro.concat("Email").concat(", ");
		}

		return finalizarGenerateStringCamposFiltro(listCamposFiltro);
	}
}


