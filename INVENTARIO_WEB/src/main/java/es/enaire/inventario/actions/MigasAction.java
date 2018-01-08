package es.enaire.inventario.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import es.enaire.inventario.business.MigaPan;
import es.enaire.inventario.util.ConstantesMigasPan;

/**
 * Accion para la gestion de migas de pan.
 * @param <T>
 *
 */
public class MigasAction<T> extends BaseAction<T> implements SessionAware {

	/**
	 * Identificador unico de serializacion.
	 */
	private static final long serialVersionUID = -7635556350699017759L;

	/**
	 * Datos de la sesion del usuario.
	 */
	private Map<String, Object> session;

	/**
	 * Número máximo de migas fijadas que se pueden tener.
	 */
	private static final int MAXIMO_MIGAS_FIJAS = 3;

	/**
	 * Clave para almacenar la lista de migas y poder recuperarlas.
	 */
	private static final String MIGAS_PAN = "Redirecciones_migas_pan";

	/**
	 * Clave para almacenar la lista de migas fijas y poder acceder a dicha ventana.
	 */
	private static final String MIGAS_PAN_FIJAS = "Redirecciones_migas_pan_fijas";

	/**
	 * Indicador de la miga seleccionada sobre la que se hace clic.
	 */
	private int idMigaSeleccionada;

	/**
	 * La miga de pan seleccionada.
	 */
	private MigaPan migaPanSeleccionada;

	/**
	 * El resultado de realizar las operaciones sobre las migas fijas.
	 */
	private InputStream inputStream;

	/**
	 * Inserta una nueva miga fija al listado de las migas fijas.
	 * @return Devuelve success como resultado de añadir una nueva miga fija.
	 */
	@SuppressWarnings("unchecked")
	public String addNewMigaFija() {

		String json = "{\"estado\": \"KO\"}";
		List<List<MigaPan>> migasFijas = new ArrayList<List<MigaPan>>();
		if(session.containsKey(MIGAS_PAN_FIJAS)) {
			 migasFijas = (List<List<MigaPan>>) session.get(MIGAS_PAN_FIJAS);
		}

		if(session.containsKey(MIGAS_PAN) && migasFijas.size() < MAXIMO_MIGAS_FIJAS) {
			//Mientras estemos dentro del limite se podran añadir migas fijas.

			int posicionNuevaMigaFijada = migasFijas.size();
			String etiqueta = getLastMiga(session).getEtiqueta();

			//Hacemos clonado de las migas de pan.
			List<MigaPan> nuevaMigaFijada = new ArrayList<MigaPan>();
			List<MigaPan> migaSeleccionada = (List<MigaPan>) session.get(MIGAS_PAN);
			for(MigaPan migaPan: migaSeleccionada) {
				MigaPan migaPanClonada = new MigaPan(migaPan.getEtiqueta(), migaPan.getEspacioNombre(), migaPan.getAccionNombre(), migaPan.getParametros());
				nuevaMigaFijada.add(migaPanClonada);
			}
			migasFijas.add(nuevaMigaFijada);

			session.put(MIGAS_PAN_FIJAS, migasFijas);

			json = "{\"estado\": \"OK\", \"posicion\": \"" + posicionNuevaMigaFijada + "\", \"accion\": \"/ETNAJ/MI/migas/clickMigaFija?idMigaSeleccionada=" + posicionNuevaMigaFijada + "\", \"etiqueta\": \"" + etiqueta + "\"}";
		}

		inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

		return SUCCESS;
	}

	/**
	 * Elimina la miga fija seleccionada del listado de las migas fijas.
	 * @return Devuelve success como resultado de eliminar una miga fija.
	 */
	@SuppressWarnings("unchecked")
	public String removeMigaFija() {
		String json = "{\"estado\": \"KO\", \"mensaje\": \"No borrado\"}";

		if(idMigaSeleccionada >= 0 && session.containsKey(MIGAS_PAN_FIJAS)) {
			List<List<MigaPan>> migasFijas = (List<List<MigaPan>>) session.get(MIGAS_PAN_FIJAS);
			migasFijas.remove(idMigaSeleccionada);

			session.put(MIGAS_PAN_FIJAS, migasFijas);
			json = "{\"estado\": \"OK\", \"mensaje\": \"Borrado correctamente\"}";
		}

		inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

		return SUCCESS;
	}

	/**
	 * Accion cuando se hace click sobre la miga fijada de pan seleccionada.
	 * @return Redirecciona a la pagina correspondiente de la miga de pan.
	 */
	@SuppressWarnings("unchecked")
	public String clickMigaFija() {
		try {
			if(session.containsKey(MIGAS_PAN_FIJAS)) {
				List<List<MigaPan>> migas = (List<List<MigaPan>>) session.get(MIGAS_PAN_FIJAS);
				List<MigaPan> migaFijaSeleccinada =  migas.get(idMigaSeleccionada);
				session.put(MIGAS_PAN, migaFijaSeleccinada);

				migaPanSeleccionada = getLastMiga(session);
			}
		}catch(Exception e) {
			return "errorSesionExpired";
		}

		return SUCCESS;
	}

	/**
	 * Accion cuando se hace click sobre la miga de pan seleccionada.
	 * @return Redirecciona a la pagina correspondiente de la miga de pan.
	 */
	@SuppressWarnings("unchecked")
	public String clickMiga() {
		try {
			if(session.containsKey(MIGAS_PAN)) {

				List<MigaPan> migas = (List<MigaPan>) session.get(MIGAS_PAN);
				migaPanSeleccionada = migas.get(idMigaSeleccionada);

				List<MigaPan> nuevasMigas = new ArrayList<MigaPan>();
				for(MigaPan miga: migas.subList(0, idMigaSeleccionada + 1)) {
					nuevasMigas.add(miga);
				}

				session.put(MIGAS_PAN, nuevasMigas);
			}
		}catch(Exception e) {
			return "errorSesionExpired";
		}

		return SUCCESS;
	}

	/**
	 * Accion cuando se pulsa el volver a la accion anterior.
	 * @return Redirecciona a la pagina correspondiente de la miga de pan anterior.
	 */
	@SuppressWarnings("unchecked")
	public String volverBack() {
		try {
			removeLastMiga(session);

			if(session.containsKey(MIGAS_PAN)) {
				List<MigaPan> migas = new ArrayList<MigaPan>();
				if(session.containsKey(MIGAS_PAN)) {
					 migas = (List<MigaPan>) session.get(MIGAS_PAN);
				}


				if(migas.size() == 0) {
					migaPanSeleccionada = new MigaPan("", "/", ConstantesMigasPan.ACCION_WELCOME);
				}
				else {
					migaPanSeleccionada = migas.get(migas.size()-1);
				}
			}
		}catch(Exception e) {
			return "errorSesionExpired";
		}

		return SUCCESS;
	}

	/**
	 * Inserta una nueva miga al listado de las migas de pan.
	 * @param sesion La sesion para recuperar el listado de migas.
	 * @param nuevaMiga La nueva miga a insertar.
	 */
	@SuppressWarnings("unchecked")
	public static void addNewMiga(Map<String, Object> sesion, MigaPan nuevaMiga) {

		List<MigaPan> migas = new ArrayList<MigaPan>();
		if(sesion.containsKey(MIGAS_PAN)) {
			 migas = (List<MigaPan>) sesion.get(MIGAS_PAN);
		}

		//if(!isLastMiga(sesion, nuevaMiga.getAccionNombre())) {
			migas.add(nuevaMiga);

		sesion.put(MIGAS_PAN, migas);
	}

	/**
	 * Elimina la ultima miga de pan del listado de las migas de pan.
	 * @param sesion La sesion para recuperar el listado de migas.
	 */
	@SuppressWarnings("unchecked")
	public static void removeLastMiga(Map<String, Object> sesion) {

		if(sesion.containsKey(MIGAS_PAN)) {

			List<MigaPan> nuevasMigas = new ArrayList<MigaPan>();
			List<MigaPan> migas = (List<MigaPan>) sesion.get(MIGAS_PAN);
			for(MigaPan miga: migas.subList(0, migas.size()-1)) {
				nuevasMigas.add(miga);
			}

			sesion.put(MIGAS_PAN, nuevasMigas);
		}
	}

	/**
	 * Devuelve el listado de migas fijas para poder visualizarlas.
	 * @param sesion La sesion para recuperar el listado de migas fijas.
	 * @return la lista con las migas anidadas
	 */
	@SuppressWarnings("unchecked")
	public static List<List<MigaPan>> getMigasFijas(Map<String, Object> sesion) {
		List<List<MigaPan>> migas = new ArrayList<List<MigaPan>>();
		if(sesion.containsKey(MIGAS_PAN_FIJAS)) {
			 migas = (List<List<MigaPan>>) sesion.get(MIGAS_PAN_FIJAS);
		}

		return migas;
	}

	/**
	 * Devuelve el listado de migas para poder visualizar la miga.
	 * @param sesion La sesion para recuperar el listado de migas.
	 * @return la lista con las migas
	 */
	@SuppressWarnings("unchecked")
	public static List<MigaPan> getMigas(Map<String, Object> sesion) {
		List<MigaPan> migas = new ArrayList<MigaPan>();
		if(sesion.containsKey(MIGAS_PAN)) {
			 migas = (List<MigaPan>) sesion.get(MIGAS_PAN);
		}

		return migas;
	}

	/**
	 * Obtiene de la sesion la miga de pan si esta coincide con el identificador de la accion especificado.
	 * @param sesion La sesion para recuperar el listado de migas.
	 * @param identificadorAccion El identificador de la accion a obtener.
	 * @return La miga de pan obtenida en caso de existir o null en el contrario.
	 */
	@SuppressWarnings("unchecked")
	public static MigaPan getMiga(Map<String, Object> sesion, String identificadorAccion) {
		MigaPan migaObtenida = null;
		List<MigaPan> migas = new ArrayList<MigaPan>();
		if(sesion.containsKey(MIGAS_PAN)) {
			 migas = (List<MigaPan>) sesion.get(MIGAS_PAN);
			 for(MigaPan miga: migas) {
				 if(miga.getAccionNombre().equals(identificadorAccion)) {
					 migaObtenida = miga;
					 break;
				 }
			 }
		}

		return migaObtenida;
	}

	/**
	 * Obtiene de la sesion la ultima miga de pan ultima.
	 * @param sesion La sesion para recuperar el listado de migas.
	 * @return La ultima miga de pan.
	 */
	@SuppressWarnings("unchecked")
	public static MigaPan getLastMiga(Map<String, Object> sesion) {
		MigaPan migaObtenida = null;
		List<MigaPan> migas = new ArrayList<MigaPan>();
		if(sesion.containsKey(MIGAS_PAN)) {
			 migas = (List<MigaPan>) sesion.get(MIGAS_PAN);
			 migaObtenida = migas.get(migas.size()-1);
		}

		return migaObtenida;
	}

	/**
	 * Extrae de la sesion la miga de pan ultima si coincide con el identificador de la accion especificado.
	 * @param sesion La sesion para recuperar el listado de migas.
	 * @param identificadorAccion El identificador de la accion a extraer.
	 * @return La miga de pan extraida en caso de existir o null en el contrario.
	 */
	@SuppressWarnings("unchecked")
	public static MigaPan extraerMiga(Map<String, Object> sesion, String identificadorAccion) {
		MigaPan migaExtraida = null;
		List<MigaPan> migas = new ArrayList<MigaPan>();
		if(sesion.containsKey(MIGAS_PAN)) {
			 migas = (List<MigaPan>) sesion.get(MIGAS_PAN);

			 if(migas.get(migas.size()-1).getAccionNombre().equals(identificadorAccion)) {
				 migaExtraida = migas.get(migas.size()-1);
				 removeLastMiga(sesion);
			 }
		}

		return migaExtraida;
	}

	/**
	 * Devuelve true si la ultima miga coincide con el identificador de la accion.
	 * @param sesion La sesion para recuperar el listado de migas.
	 * @param identificadorAccion El identificador de la accion a evaluar.
	 * @return True si la ultima miga coincide con el identificador de la accion.
	 */
	@SuppressWarnings("unchecked")
	public static boolean isLastMiga(Map<String, Object> sesion, String identificadorAccion) {
		boolean resultado = false;
		List<MigaPan> migas = new ArrayList<MigaPan>();
		if(sesion.containsKey(MIGAS_PAN)) {
			 migas = (List<MigaPan>) sesion.get(MIGAS_PAN);

			 if(migas.size() != 0 && migas.get(migas.size()-1).getAccionNombre().equals(identificadorAccion)) {
				 resultado = true;
			 }
		}

		return resultado;
	}

	/**
	 * Borra el listado de migas antiguas.
	 * @param sesion La sesion para actualizar el listado borrado de las migas.
	 */
	public static void clearMigas(Map<String, Object> sesion) {
		sesion.put(MIGAS_PAN,  new ArrayList<MigaPan>());
	}

	/**
	 * Devuelve el indicador de la miga seleccionada.
	 * @return El indicador de la miga seleccionada.
	 */
	public int getIdMigaSeleccionada() {
		return idMigaSeleccionada;
	}

	/**
	 * Establece el indicador de la miga seleccionada.
	 * @param idMigaSeleccionada El indicador de la miga seleccionada.
	 */
	public void setIdMigaSeleccionada(int idMigaSeleccionada) {
		this.idMigaSeleccionada = idMigaSeleccionada;
	}

	/**
	 * Devuelve la miga de pan seleccionada.
	 * @return La miga de pan seleccionada.
	 */
	public MigaPan getMigaPanSeleccionada() {
		return migaPanSeleccionada;
	}

	/**
	 * Establece la miga de pan seleccionada.
	 * @param migaPanSeleccionada La miga de pan seleccionada.
	 */
	public void setMigaPanSeleccionada(MigaPan migaPanSeleccionada) {
		this.migaPanSeleccionada = migaPanSeleccionada;
	}

	/**
	 * @param session the session to set
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * Obtiene el valor de la variable inputStream para fichero JSON.
	 * @return inputStream El valor de la variable inputStream para fichero JSON.
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * Establece el valor de la variable inputStream para fichero JSON.
	 * @param inputStream El valor de la variable inputStream.
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * Metodo para aplicar los menus de acciones en funcion del estado del elemento.
	 * @param resultado El resultado del listado para aplicar el estado del menu.
	 */
	@Override
	public void aplicarEstadoMenu(T resultado) {
	}
}
