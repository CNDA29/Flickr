package Dominio;

public class Publicacion {
	private String etiqueta, mensaje, usuario, id;

	public Publicacion() {
		
	}
	
	@Override
	public String toString() {
		return "Publicacion "+id+ " [etiqueta=" + etiqueta + ", mensaje=" + mensaje + ", usuario=" + usuario + "]";
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
