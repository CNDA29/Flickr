package Dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorPublicaciones {
	public static Publicacion[] leerPublicacionesExplorar() throws JSONException {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\src\\Recursos\\publicaciones.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numPublicaciones");
			obj.getJSONObject("publicaciones");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo publicaciones.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo publicaciones.json no es valido.");
			return null;
		}
		Publicacion[] publicaciones = new Publicacion[obj.getInt("numPublicaciones")];
		for (int i = 0; i < obj.getInt("numPublicaciones"); i++) {
			JSONObject pAux = obj.getJSONObject("publicaciones").getJSONObject(String.valueOf(i));
				Publicacion publicacionAux = new Publicacion();
				publicacionAux.setEtiqueta(pAux.getString("etiqueta"));
				publicacionAux.setMensaje(pAux.getString("mensaje"));
				publicacionAux.setUsuario(pAux.getString("usuario"));
				publicacionAux.setId(String.valueOf(i));
				publicaciones[i] = publicacionAux;
		}
		return publicaciones;
	}
	
	public static JSONObject leerPublicaciones() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\src\\Recursos\\publicaciones.json"));
			obj = new JSONObject(tokener);
			obj.getInt("numPublicaciones");
			obj.getJSONObject("publicaciones");
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo publicaciones.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo publicaciones.json no es valido.");
			return null;
		}
		return obj;
	}

	public static void meGusta(Usuario usuarioLogged, String etiqueta, String nombreUsuarioPubli, boolean estadoFav, String idPubli) {
		JSONObject JSONUsuarios = GestorUsuario.leerUsuarios();

		int n = JSONUsuarios.getJSONObject("usuarios").getJSONObject(usuarioLogged.getId()).getJSONObject("tagFav")
				.getInt(etiqueta.toLowerCase());
		if (estadoFav) {
			JSONUsuarios.getJSONObject("usuarios").getJSONObject(usuarioLogged.getId()).getJSONObject("tagFav")
					.put(etiqueta.toLowerCase(), n + 1);
			JSONUsuarios.getJSONObject("usuarios").getJSONObject(usuarioLogged.getId()).getJSONArray("publicacionesMG")
					.put(idPubli);
			modificarJsonNotificaciones(nombreUsuarioPubli,usuarioLogged);
		} else {
			JSONUsuarios.getJSONObject("usuarios").getJSONObject(usuarioLogged.getId()).getJSONObject("tagFav")
					.put(etiqueta.toLowerCase(), n - 1);
			int indexMG = buscarPublicacionMG(JSONUsuarios, usuarioLogged.getId(),idPubli);
			JSONUsuarios.getJSONObject("usuarios").getJSONObject(usuarioLogged.getId()).getJSONArray("publicacionesMG")
					.remove(indexMG);
			//borrarJsonNotificaciones(nombreUsuarioPubli, usuarioLogged);
		}
		escribirJsonUsuarios(JSONUsuarios);

	}
	
	public static void escribirJsonUsuarios(JSONObject JSONUsuarios) {
		String rutaescritura = System.getProperty("user.dir") + "\\src\\Recursos";
		String file = "usuarios.json";
		FileWriter fw;
		try {
			fw = new FileWriter(new File(rutaescritura, file));
			fw.write(JSONUsuarios.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void modificarJsonNotificaciones(String nombre, Usuario usuarioLogged) {
		JSONObject JSONNotificaciones = GestorNotificaciones.leerNotificaciones();
		int n = JSONNotificaciones.getJSONObject(nombre).getInt("numNotificaciones");
		JSONObject JSONNotificacion = new JSONObject();
		JSONNotificacion.put("usuario", usuarioLogged.getNombre());
		JSONNotificacion.put("notificacion", "Le ha dado Me Gusta a una de tus publicaciones");
		JSONNotificaciones.getJSONObject(nombre).getJSONObject("notificaciones").put(String.valueOf(n), JSONNotificacion);
		JSONNotificaciones.getJSONObject(nombre).put("numNotificaciones",n+1);
		escribirJsonNotificaciones(JSONNotificaciones);
	}
	
	public static void borrarJsonNotificaciones(String nombre, Usuario usuarioLogged) {
		JSONObject JSONNotificaciones = GestorNotificaciones.leerNotificaciones();
		int n = JSONNotificaciones.getJSONObject(nombre).getInt("numNotificaciones");
		Iterator<String> keys = JSONNotificaciones.getJSONObject(nombre).getJSONObject("notificaciones").keys();
		while(keys.hasNext()) {
			String id = keys.next();
			if(JSONNotificaciones.getJSONObject(nombre).getJSONObject("notificaciones").getJSONObject(id).getString("usuario").equals(usuarioLogged.getNombre())) {
				JSONNotificaciones.getJSONObject(nombre).getJSONObject("notificaciones").remove(id);
				JSONNotificaciones.getJSONObject(nombre).put("numNotificaciones",n-1);
				break;
			}
		}
		escribirJsonNotificaciones(JSONNotificaciones);
	}
	
	public static void escribirJsonNotificaciones(JSONObject JSONNotificaciones) {
		String rutaescritura = System.getProperty("user.dir") + "\\src\\Recursos";
		String file = "notificaciones.json";
		FileWriter fw;
		try {
			fw = new FileWriter(new File(rutaescritura, file));
			fw.write(JSONNotificaciones.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int buscarPublicacionMG(JSONObject JSONUsuarios, String idUser, String idPubli) {
		JSONArray publiMG =JSONUsuarios.getJSONObject("usuarios").getJSONObject(idUser).getJSONArray("publicacionesMG");
		int index = 0;
		for (int i = 0; i < publiMG.length(); i++) {
			if (idPubli.equals(publiMG.getString(i))) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static boolean buscarPubliMGIcono(JSONObject JSONUsuarios, String idUser, String idPubli) {
		JSONArray publiMG =JSONUsuarios.getJSONObject("usuarios").getJSONObject(idUser).getJSONArray("publicacionesMG");
		boolean encontrado = false;
		for (int i = 0; i < publiMG.length(); i++) {
			if (idPubli.equals(publiMG.getString(i))) {
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}

}
