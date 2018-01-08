package es.enaire.inventario.util;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UtilidadesEmail {
	/**
	 * Envia el email desde soporte automatico a traves del servidor de email configurado.
	 * @param informacionEmail Informacion del email a crear y enviar.
	 * @throws Exception Excepcion que se puede producir durante el proceso de envio del email.
	 */
	public static void sendEmailFromSoporteAutomatico(InfoEmail informacionEmail) throws Exception {
		ConfiguracionEmailServer configuracionServidor = GestionPropiedadesEmail.getConfiguracionEmailServer();
		Properties properties = System.getProperties();
		properties.setProperty(GestionPropiedadesEmail.MAIL_SMTP_HOST, configuracionServidor.getHost());
		properties.setProperty(GestionPropiedadesEmail.MAIL_SMTP_PORT, configuracionServidor.getPort());
		if(configuracionServidor.isAutenticacion()) {
			properties.setProperty(GestionPropiedadesEmail.MAIL_USER, configuracionServidor.getUser());
			properties.setProperty(GestionPropiedadesEmail.MAIL_PASS, configuracionServidor.getPassword());
		}

		Session sesion = Session.getDefaultInstance(properties);

		MimeMessage email = new MimeMessage(sesion);
		email.setFrom(new InternetAddress(configuracionServidor.getEmailSoporteAutomatico()));
		email.addRecipient(Message.RecipientType.TO, new InternetAddress(informacionEmail.getDestino()));
		email.setSubject(informacionEmail.getAsunto());
//		email.setText(informacionEmail.getTexto());
		email.setContent(informacionEmail.getTexto(), "text/html");

		Transport.send(email);
	}
	/**
	 * Metodo que nos construye el texto de un email solicitando la creaccion de Tipo de Instalacion
	 * @param familiaInstalacionNombre el nombre de la familia de instalacion
	 * @param marca a crear
	 * @param modelo a crear
	 * @return el texto del email
	 */
	public static String getTextoEmailTipoInstacion(String familiaInstalacionNombre, String marca, String modelo){
		String texto = "<!DOCTYPE html><html><head><meta http-equiv=" +"Content-Type"+" content="+"text/html;charset=utf-8/>";
		texto = texto + "<title>"+"Solicitud de Nueva Marca y/o Modelo"+"</title></head><body>";
		texto = texto +"Estimado Administrador,<br/> ";
		texto = texto +"Se le solicita que, si asi lo estima oportuno, cree un Tipo de Instalación con los siguientes datos: <br/>";
		texto = texto + "Familia Instación: " + (familiaInstalacionNombre !=null ? familiaInstalacionNombre:"") +"<br/>";
		texto = texto + "Marca: "+(marca != null ? marca : "") +"<br/>";
		texto = texto + "Modelo: "+(modelo != null ? modelo : "")+"<br/>";
		texto = texto + "Muchas gracias"+"<br/>";
		texto = texto + "</body></html>";
		
		return texto;
	}
}
