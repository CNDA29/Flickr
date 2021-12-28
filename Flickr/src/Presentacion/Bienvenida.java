package Presentacion;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;

public class Bienvenida extends JPanel {
	private JLabel lblMensaje;
	private JLabel lblFondo;

	/**
	 * Create the panel.
	 */
	public Bienvenida() {
		setBounds(new Rectangle(0, 0, 483, 500));
		setLayout(null);
		{
			lblMensaje = new JLabel();
			lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
			lblMensaje.setForeground(Color.BLACK);
			lblMensaje.setText("<html><body>Bienvenid@ a Flickr<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A la izquierda &nbsp;&nbsp;puedes encontrar &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nuestras &nbsp;&nbsp;&nbsp;&nbsp;funcionalidades</body></html>");
			lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblMensaje.setBounds(109, 57, 255, 277);
			add(lblMensaje);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(-394, -125, 1280, 701);
			try {
				Image imagenOriginal = ImageIO.read(PrincipalApp.class.getResource("/Recursos/Fondo.jpg"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(lblFondo.getWidth(),
						lblFondo.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				lblFondo.setIcon(iconoLabel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(lblFondo);
		}

	}

}
