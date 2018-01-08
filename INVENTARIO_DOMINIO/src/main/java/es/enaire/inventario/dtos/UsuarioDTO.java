package es.enaire.inventario.dtos;

import java.io.Serializable;
import java.util.Date;

import es.enaire.inventario.annotations.IdTarget;

/**
 * Clase de mapeo del formulario de acceso
 *
 */
public class UsuarioDTO implements Serializable {
	/**
	 * Identificador de serializacion.
	 */
	private static final long serialVersionUID = -2552200756585917797L;

	/**
	 * Indicador del numero maximo de intentos fallidos en la autenticacion de un usuario.
	 */
	public static final Integer MAXIMO_INTENTOS_FALLIDOS = 5;

	/**
	 * Id de Usuario
	 */
	@IdTarget
	private Long id;

	/**
	 * Nombre completo del usuario.
	 */
	private String nombreCompleto;
	/**
	 * Nombre de usuario
	 */
	private String username;
	/**
	 * Contraseña de usuario
	 */
	private String password;
	/**
	 * Email del usuario
	 */
	private String email;
	/**
	 * Cuenta activa: <i>0</i> en caso negativo <i>1</i> en caso afirmativo
	 */
	private boolean cuentaActiva;
	/**
	 * Numero de accesos incorrectos
	 */
	private Integer numeroAccesos;
    /**
    * Indica si se trata de una actualizacion del Backend
    */
   private Boolean actualizacion;
   /**
    * Indica si se trata de una busqueda por nombre  del Backend
    */
   private Boolean byName = false;
   
   /**
	 * Fecha de creacion de la empresa
	 */
	private Date fechaCreacion;
   
   
   /**
    * Obtiene el listado de perfiles en formato string separados por |.
    * @return El listado de perfiles en formato string separados por |.
    */
   public String getPerfilesString(){
	   String perfilesString = "";
	   return perfilesString;
   }

   /**
	 * Obtiene el Id de Usuario
	 * @return el Id de Usuario
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Establece el Id de Usuario
	 * @param id el Id de Usuario
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el Nombre de usuario
	 * @return el Nombre de usuario
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Establece el Nombre de usuario
	 * @param username el Nombre de usuario
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Obtiene la Contraseña de usuario
	 * @return la Contraseña de usuario
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Establece la Contraseña de usuario
	 * @param password la Contraseña de usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Obtiene el email
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Establece el email
	 * @param email el email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Obtiene la Cuenta Activa <i>0</i> en caso negativo <i>1</i> en caso afirmativo
	 * @return la Cuenta Activa
	 */
	public boolean getCuentaActiva() {
		return cuentaActiva;
	}
	/**
	 * Establece la Cuenta Activa <i>0</i> en caso negativo <i>1</i> en caso afirmativo
	 * @param cuentaActiva la Cuenta Activa
	 */
	public void setCuentaActiva(boolean cuentaActiva) {
		this.cuentaActiva = cuentaActiva;
	}
	/**
	 * Obtiene el numero de accesos incorrectos
	 * @return el numero de accesos incorrectos
	 */
	public Integer getNumeroAccesos() {
		return numeroAccesos;
	}
	/**
	 * Establece el numero de accesos incorrectos
	 * @param numeroAccesos el numero de accesos incorrectos
	 */
	public void setNumeroAccesos(Integer numeroAccesos) {
		this.numeroAccesos = numeroAccesos;
	}
	 /**
     * Obtiene si se trata de una actualizacion del Backend
     * @return si se trata de una actualizacion del Backend
     */
    public Boolean getActualizacion() {
		return actualizacion;
	}
    /**
     * Establece si se trata de una actualizacion del Backend
     * @param actualizacion si se trata de una actualizacion del Backend
     */
	public void setActualizacion(Boolean actualizacion) {
		this.actualizacion = actualizacion;
	}
	/**
	 * Obtiene si se trata de una busqueda por Nombre del Backend
	 * @return  si se trata de una busqueda por Nombre del Backend
	 */
	public Boolean getByName() {
		return byName;
	}
	/**
	 * Establece  si se trata de una busqueda por Nombre del Backend
	 * @param byName si se trata de una busqueda por Nombre del Backend
	 */
	public void setByName(Boolean byName) {
		this.byName = byName;
	}

	/**
	 * Obtiene el nombre completo del usuario.
	 * @return El nombre completo del usuario.
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Establece el nombre completo del usuario.
	 * @param nombreCompleto El nombre completo del usuario.
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Obtiene la fecha de creación del usuario
	 * @return La fehca de creación del usuario
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * La fecha de creación a establecer
	 * @param fechaCreacion La fecha de creación a establecer
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
