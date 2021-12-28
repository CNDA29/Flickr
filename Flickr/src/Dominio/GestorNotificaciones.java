package Dominio;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorNotificaciones {
	public static JSONObject leerNotificaciones() {
		JSONObject obj;
		try {
			JSONTokener tokener = new JSONTokener(
					new FileReader(System.getProperty("user.dir") + "\\src\\Recursos\\notificaciones.json"));
			obj = new JSONObject(tokener);
		} catch (FileNotFoundException e) {
			System.out.println("\nERROR: El archivo publicaciones.json no se ha encontrado.");
			return null;
		} catch (JSONException e) {
			System.out.println("\nERROR: El formato del archivo publicaciones.json no es valido.");
			return null;
		}
		return obj;
	}
}
