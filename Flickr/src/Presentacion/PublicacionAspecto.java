package Presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import Dominio.GestorNotificaciones;
import Dominio.GestorPublicaciones;
import Dominio.GestorUsuario;
import Dominio.Usuario;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PublicacionAspecto extends JPanel {
	private JLabel imFoto;
	private JTextArea textArea;
	private JLabel lblEtiqueta;
	private JLabel lblNombre;
	private JLabel imMeGusta;
	private Usuario usuarioLogged;
	private boolean estadoFav;
	private String idPubli;
	/*/*//*
	 * COMO MUCHO 168 CARACTERES
	 */
	
	
	
	/**
	 * Create the panel.
	 */
	public PublicacionAspecto() {
		estadoFav = false;
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
			textArea.setEditable(false);
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textArea.setLineWrap(true);
			textArea.setBounds(120, 38, 331, 90);
			add(textArea);
		}
		{
			lblEtiqueta = new JLabel("Etiqueta");
			lblEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblEtiqueta.setBounds(120, 129, 173, 21);
			add(lblEtiqueta);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBounds(120, 15, 173, 21);
			add(lblNombre);
		}
		{
			imMeGusta = new JLabel("");
			imMeGusta.setBounds(406, 132, 45, 30);
			try {
				Image imagenOriginal = ImageIO.read(PublicacionAspecto.class.getResource("/Recursos/MeGustaGris.png"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(imMeGusta.getWidth(),
						imMeGusta.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				imMeGusta.setIcon(iconoLabel);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			add(imMeGusta);
			imMeGusta.addMouseListener(new ImMeGustaMouseListener());
		}
	}
	
	public void setPropiedades(String nombreAux, String etiquetaAux, String mensajeAux, Usuario usuario,String idPubli) {
		this.lblNombre.setText(nombreAux);
		this.lblEtiqueta.setText(etiquetaAux);
		this.textArea.setText(filtroLenguaje(mensajeAux));
		this.idPubli = idPubli;
		usuarioLogged = usuario;
		if(GestorPublicaciones.buscarPubliMGIcono(GestorUsuario.leerUsuarios(),usuarioLogged.getId(),idPubli)) {
			try {
				Image imagenOriginal = ImageIO.read(PublicacionAspecto.class.getResource("/Recursos/MeGustaRojo.png"));
				Image imagenEscalada = imagenOriginal.getScaledInstance(imMeGusta.getWidth(),
						imMeGusta.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
				imMeGusta.setIcon(iconoLabel);
				estadoFav = true;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public String filtroLenguaje(String texto) {
		String[] parts = texto.split(" ");
		String palabraAux ="";
		String palabra = "";
		for(int i = 0;i<parts.length;i++) {
			palabraAux = parts[i];
			switch (palabraAux) {
			case "inutil":
				palabraAux = "****";
				break;
			case "imbecil":
				palabraAux = "****";
				break;
			case "subnormal":
				palabraAux = "****";
				break;
			case "retrasado":
				palabraAux = "****";
				break;
			case "estupido":
				palabraAux = "****";
			}
			palabra += palabraAux+ " ";
		}
		return palabra;
	}
	private class ImMeGustaMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			estadoFav = !estadoFav;
			if(estadoFav == true) {
				try {
					Image imagenOriginal = ImageIO.read(PublicacionAspecto.class.getResource("/Recursos/MeGustaRojo.png"));
					Image imagenEscalada = imagenOriginal.getScaledInstance(imMeGusta.getWidth(),
							imMeGusta.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
					imMeGusta.setIcon(iconoLabel);
					GestorPublicaciones.meGusta(usuarioLogged,lblEtiqueta.getText(),lblNombre.getText(), estadoFav,idPubli);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					Image imagenOriginal = ImageIO.read(PublicacionAspecto.class.getResource("/Recursos/MeGustaGris.png"));
					Image imagenEscalada = imagenOriginal.getScaledInstance(imMeGusta.getWidth(),
							imMeGusta.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon iconoLabel = new ImageIcon(imagenEscalada);
					imMeGusta.setIcon(iconoLabel);
					GestorPublicaciones.meGusta(usuarioLogged,lblEtiqueta.getText(),lblNombre.getText(), estadoFav,idPubli);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	

	
}
