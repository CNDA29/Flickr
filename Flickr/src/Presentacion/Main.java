package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.json.*;

import java.awt.CardLayout;
import java.awt.Toolkit;

public class Main {

	private JFrame frmJawapp;
	
	PrincipalApp principalApp;
	IniciarSesion iniciarSesion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmJawapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JSONException 
	 */
	public Main() throws JSONException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws JSONException 
	 */
	private void initialize() throws JSONException {
		principalApp = new PrincipalApp();
		iniciarSesion = new IniciarSesion(principalApp);
		frmJawapp = new JFrame();
		frmJawapp.setResizable(false);
		frmJawapp.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Recursos/Logo.png")));
		frmJawapp.setTitle("FLICKR");
		frmJawapp.setBounds(100, 100, 1280, 720);
		frmJawapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJawapp.getContentPane().setLayout(new CardLayout(0, 0));
		frmJawapp.getContentPane().add(iniciarSesion);
		frmJawapp.getContentPane().add(principalApp);
	}

}
