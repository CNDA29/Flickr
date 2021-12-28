package Presentacion;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Rectangle;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.json.JSONObject;

import Dominio.GestorPublicaciones;
import Dominio.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class NuevaPublicacion extends JPanel {
	private JTextArea textArea;
	private JButton btnLimpiar;
	private JButton btnPublicar;
	private Usuario usuario;
	private JComboBox cbEtiqueta;
	private JLabel lblEtiqueta;
	private JLabel lblFondo;
	/**
	 * Create the panel.
	 */
	public NuevaPublicacion(Usuario u) {
		setBounds(new Rectangle(0, 0, 608, 400));
		setLayout(null);
		usuario = u;
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 463, 191);
		add(panel);
		panel.setLayout(null);
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 463, 123);
		panel.add(textArea);
		textArea.setLineWrap(true);
		textArea.addMouseListener(new TextAreaMouseListener());
		textArea.setText("Escriba aqui su nueva publicacion....");
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(10, 133, 99, 40);
		panel.add(btnLimpiar);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.addActionListener(new BtnLimpiarActionListener());
		btnLimpiar.setToolTipText("Borra la publicacion");
		
		btnPublicar = new JButton("Publicar");
		btnPublicar.setBounds(354, 133, 99, 40);
		panel.add(btnPublicar);
		btnPublicar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbEtiqueta = new JComboBox();
		cbEtiqueta.setBounds(170, 152, 117, 22);
		panel.add(cbEtiqueta);
		cbEtiqueta.setModel(new DefaultComboBoxModel(new String[] {"Deportes", "Ocio", "Presentacion", "Comida"}));
		
		lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setBounds(170, 133, 79, 13);
		panel.add(lblEtiqueta);
		lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPublicar.addActionListener(new BtnPublicarActionListener());
		
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


	private class BtnLimpiarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textArea.setText("");
		}
	}
	private class TextAreaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			textArea.setText("");
		}
	}

	private class BtnPublicarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (textArea.getText().length() < 168) {
				JSONObject JSONPublicaciones = GestorPublicaciones.leerPublicaciones();
				JSONObject jPublicacion = new JSONObject();
				jPublicacion.put("etiqueta", ((String) cbEtiqueta.getSelectedItem()).toLowerCase());
				jPublicacion.put("usuario", usuario.getNombre());
				jPublicacion.put("mensaje", textArea.getText());
				int n = JSONPublicaciones.getInt("numPublicaciones");
				JSONPublicaciones.getJSONObject("publicaciones").put(String.valueOf(n), jPublicacion);
				JSONPublicaciones.put("numPublicaciones", n + 1);

				String rutaescritura = System.getProperty("user.dir") + "\\src\\Recursos";
				String file = "publicaciones.json";
				FileWriter fw;
				try {
					fw = new FileWriter(new File(rutaescritura, file));
					fw.write(JSONPublicaciones.toString());
					fw.close();
					JOptionPane.showMessageDialog(null, "Se ha publicado con éxito.");
					textArea.setText("");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "La publicación no puede superar los 168 caracteres.\n\nNúmero actual de caracteres: " +textArea.getText().length());
			}
		}
	}
}