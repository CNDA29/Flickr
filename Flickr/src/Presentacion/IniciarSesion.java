package Presentacion;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.json.JSONException;

import Dominio.GestorUsuario;
import Dominio.Usuario;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class IniciarSesion extends JPanel {
	private JLabel lblLogo;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnIniciarSesion;
	private Usuario usuario;
	
	PrincipalApp principalApp;
	private JLabel lblFondo;
	/**
	 * Create the panel.
	 */
	public IniciarSesion(PrincipalApp pApp) {
		principalApp = pApp;
		this.setBounds(0, 0, 1280, 720);
		setLayout(null);
		{
			lblLogo = new JLabel("");
			lblLogo.setBounds(521, 104, 225, 225);
			try {
				Image imagenOriginal = ImageIO.read(IniciarSesion.class.getResource("/Recursos/Logo.png"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(lblLogo.getWidth(),
						lblLogo.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				lblLogo.setIcon(iconoLabel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(lblLogo);
		}
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(Color.BLACK);
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblUsuario.setBounds(351, 350, 213, 50);
			add(lblUsuario);
		}
		{
			lblContrasena = new JLabel("Contraseña");
			lblContrasena.setForeground(Color.BLACK);
			lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
			lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblContrasena.setBounds(351, 413, 213, 50);
			add(lblContrasena);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtUsuario.setBounds(574, 350, 250, 50);
			add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			txtContrasena = new JPasswordField();
			txtContrasena.setBorder(new LineBorder(Color.BLACK, 1, true));
			txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtContrasena.setBounds(574, 413, 250, 50);
			add(txtContrasena);
		}
		{
			btnIniciarSesion = new JButton("Iniciar sesión");
			btnIniciarSesion.setForeground(Color.BLACK);
			btnIniciarSesion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnIniciarSesion.addActionListener(new BtnIniciarSesionActionListener());
			btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnIniciarSesion.setBounds(675, 473, 149, 42);
			add(btnIniciarSesion);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 1280, 720);
			try {
				Image imagenOriginal = ImageIO.read(IniciarSesion.class.getResource("/Recursos/Fondo.jpg"));
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
	private class BtnIniciarSesionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!txtUsuario.getText().isEmpty() && txtContrasena.getPassword().length != 0) {
				try {
					usuario = GestorUsuario.comprobarUsuario(txtUsuario.getText().toString(), String.valueOf(txtContrasena.getPassword()));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(usuario == null) {
					JOptionPane.showMessageDialog(null, "Datos incorrectos.");
				} else {
					setVisible(false);
					try {
						principalApp.setUsuario(usuario);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					principalApp.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe rellenar los campos anteriores.");
			}
		}
	}
}
