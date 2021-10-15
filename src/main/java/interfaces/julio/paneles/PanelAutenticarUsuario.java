package main.java.interfaces.julio.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.enmus.TipoMensaje;
import main.java.interfaces.julio.frames.FrameAutenticarUsuario;
import main.java.interfaces.julio.frames.FrameMenuPrincipal;
import main.java.interfaces.julio.otros.Mensaje;
import main.java.interfaces.julio.otros.RoundedBorder;



public class PanelAutenticarUsuario extends JPanel{
	
	private PanelAutenticarUsuarioGroupBox panelAutenticarUsuarioGroupBox = new PanelAutenticarUsuarioGroupBox();
	
	private JButton cancelar;
	private JButton iniciarSesion;
	
	private JLabel label;
	
	private Font fuenteTitulo = new Font("SourceSansPro", Font.PLAIN, 46);	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private FrameMenuPrincipal frameSiguiente;
	
	public PanelAutenticarUsuario(final FrameAutenticarUsuario frame) {
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("Hotel Premier");
		label.setFont(fuenteTitulo);
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(30,0,0,0);
		c.gridwidth = 3;
		c.gridx = 0; c.gridy = 0;
		c.weightx = 0.5; c.weighty = 0.2;			
		this.add(label, c);
		
		
		c.insets = new Insets(25,230,25,230);
		c.fill = GridBagConstraints.HORIZONTAL; 		c.gridx = 0; c.gridy = 1;	c.gridwidth = 3;
		c.weightx = 0.5; c.weighty = 0.5;			this.add(panelAutenticarUsuarioGroupBox, c);
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		
		c.insets = new Insets(0,100,30,0);
		cancelar = new JButton("Salir");
		cancelar.setFont(fuenteBoton);
		cancelar.setBackground(Color.decode("#E0E0E0"));
		cancelar.setBorder(bordeBoton);
		cancelar.setPreferredSize(new Dimension(140, 30));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		c.anchor = GridBagConstraints.WEST;
													c.gridx = 0; c.gridy = 2;
													this.add(cancelar, c);

		c.insets = new Insets(0,0,30,100);
		iniciarSesion = new JButton("Iniciar Sesión");
		iniciarSesion.setFont(fuenteBoton);
		iniciarSesion.setBackground(Color.decode("#E0E0E0"));
		iniciarSesion.setBorder(bordeBoton);
		iniciarSesion.setPreferredSize(new Dimension(140, 30));
		iniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(panelAutenticarUsuarioGroupBox.validar()) {
					frame.dispose();
					frameSiguiente = new FrameMenuPrincipal();
				}
				else {
					//String pregunta = "<html>Hola <br/> como <br/> andas<html>";
					//String pregunta3 = "<html><p>¿Quiere hacer esto y lo otro y que se yo que de lo otro y otro y muchos otros y aaaa lo otroo eso era?</p><html>";
					String pregunta = "<html><p>Escriba algo en usuario y algo en contraseña</p><html>";
					//Mensaje m = new Mensaje(frame, TipoMensaje.ADVERTENCIA, pregunta3, "Aceptar", "Cancelar");
					Mensaje m2 = new Mensaje(frame, TipoMensaje.ERROR, pregunta, "Aceptar", null);
				}
			}
		});
		c.anchor = GridBagConstraints.EAST;
													c.gridx = 2; c.gridy = 2;
													this.add(iniciarSesion, c);
	}

}