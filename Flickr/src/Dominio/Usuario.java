package Dominio;

public class Usuario {
	private String nombre, correo, telefono, contrasena,id;

	public Usuario(String nombre, String correo, String telefono, String contrasena,String id) {
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.contrasena = contrasena;
		this.id = id;
	}

	public Usuario() {

	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + ", contrasena="
				+ contrasena + "]";
	}
}
