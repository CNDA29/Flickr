package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import Dominio.GestorUsuario;
import Dominio.Usuario;

public class NotificacionAspecto extends JPanel {
	private JLabel lblNombre;
	private JLabel imFoto;
	private JTextArea textArea;
	private Usuario usuarioLogged;
	private String idNotificacion;

	/**
	 * Create the panel.
	 */
	public NotificacionAspecto() {
		setPreferredSize(new Dimension(465, 172));
		setMinimumSize(new Dimension(465, 172));
		setMaximumSize(new Dimension(465, 172));
		setSize(new Dimension(465, 172));
		setBounds(new Rectangle(0, 0, 465, 172));
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setLayout(null);
		{
			imFoto = new JLabel("");
			imFoto.setBounds(10, 38, 100, 90);
			try {
				Image imagenOriginal = ImageIO.read(PublicacionAspecto.class.getResource("/Recursos/fotoPerfil.png"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(imFoto.getWidth(),
						imFoto.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				imFoto.setIcon(iconoLabel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(imFoto);
		}
		{
			textArea = new JTextArea();
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textArea.setLineWrap(true);
			textArea.setText("Ha dado Me Gusta a una de tus publicaciones");
			textArea.setBounds(120, 38, 331, 90);
			add(textArea);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBounds(120, 15, 173, 21);
			add(lblNombre);
		}
	}
	
	public void setPropiedades(String nombreUsuarioOrigen, Usuario usuario,String idNotificacion) {
		this.lblNombre.setText(nombreUsuarioOrigen);
		this.idNotificacion = idNotificacion;
		usuarioLogged = usuario;
	}

}
