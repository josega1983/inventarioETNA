package es.enaire.inventario.util;


/**
 * Clase que representa la informacion del email a enviar.
 *
 */
public class InfoEmail {
	/**
	 * La direccion de email destino que va a recibir el email.
	 */
	private String destino;

	/**
	 * El asunto del email.
	 */
	private String asunto;

	/**
	 * El texto del email.
	 */
	private String texto;


	/**
	 * Devuelve la direccion de email destino que va a recibir el email.
	 * @return La direccion de email destino que va a recibir el email.
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Establece la direccion de email destino que va a recibir el email.
	 * @param destino La direccion de email destino que va a recibir el email.
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * Devuelve el asunto del email.
	 * @return El asunto del email.
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * Establece el asunto del email.
	 * @param asunto El asunto del email.
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * Devuelve el texto del email.
	 * @return El texto del email.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Establece el texto del email.
	 * @param texto El texto del email.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
