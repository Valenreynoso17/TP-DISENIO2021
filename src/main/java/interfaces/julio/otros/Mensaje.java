package main.java.interfaces.julio.otros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.enmus.TipoMensaje;
import main.java.interfaces.nati.paneles.PanelGestionarPasajero;

public class Mensaje extends JFrame{
	
	private Integer resultado = 0;

	private JPanel contentPane;
	
	private JLabel labelTexto;
	//private int icono;
	private JButton botonDerecho;
	private JButton botonIzquierdo;
	
	private Insets insetTexto = new Insets(0,20,20,0);
	//private Insets insetIcono = new Insets(0,10,20,0);
	private Insets insetOpcionAceptar = new Insets(0,130,10,0);
	private Insets insetOpcionCancelar = new Insets(0,15,10,0);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));

	public Integer mostrarMensaje(JFrame frame, TipoMensaje tipo, String texto, String opcionAceptar, String opcionCancelar) {
		//super("Sistema Hotel Premier");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 240);
		contentPane = new JPanel();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//this.elegirIconoSegunTipoMensaje(tipo);
		
		labelTexto = new JLabel(" ");	c.weightx = 0.1; c.weighty = 0.1;
		labelTexto.setFont(fuenteBoton);
		c.anchor = GridBagConstraints.CENTER;	//c.insets = izq;
			c.gridx = 0; c.gridy = 0;
		contentPane.add(labelTexto, c);
		
		labelTexto = new JLabel(texto);	c.weightx = 0.2; c.weighty = 0.3;
		labelTexto.setFont(fuenteBoton);
		labelTexto.setPreferredSize(new Dimension(200, 100));
		c.anchor = GridBagConstraints.CENTER;	
			c.gridx = 1; c.gridy = 0;
		contentPane.add(labelTexto, c);
		
		botonDerecho = new JButton(opcionAceptar); c.weightx = 0.0; c.weighty = 0.0;
		botonDerecho.setFont(fuenteBoton);
		botonDerecho.setBorder(bordeBoton);
		botonDerecho.setBackground(Color.decode("#E0E0E0"));
		botonDerecho.setPreferredSize(new Dimension(100, 35));
		botonDerecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resultado = 1;
				dispose();
			}
		});
		c.anchor = GridBagConstraints.CENTER;	c.insets = insetOpcionAceptar;
			c.gridx = 1; c.gridy = 1;
		contentPane.add(botonDerecho, c);
		
		if(opcionCancelar != null) {
			botonIzquierdo = new JButton(opcionCancelar);
			botonIzquierdo.setFont(fuenteBoton);
			botonIzquierdo.setBorder(bordeBoton);
			botonIzquierdo.setBackground(Color.decode("#E0E0E0"));
			botonIzquierdo.setPreferredSize(new Dimension(100, 35));
			botonIzquierdo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					resultado = 0;
					dispose();
				}
			});
			c.anchor = GridBagConstraints.CENTER;	c.insets = insetOpcionCancelar;
				c.gridx = 0; c.gridy = 1;
			contentPane.add(botonIzquierdo, c);
		}
		
		setContentPane(contentPane);
		
		return resultado;
	}

//	private void elegirIconoSegunTipoMensaje(TipoMensaje tipo) {
//
//		switch(tipo) {
//			case ADVERTENCIA:
//				this.icono = JOptionPane.WARNING_MESSAGE;
//			case CONFIRMACION:
//				this.icono = JOptionPane.QUESTION_MESSAGE;
//			case ERROR:
//				this.icono = JOptionPane.ERROR_MESSAGE;
//			case EXITO:
//				this.icono = JOptionPane.INFORMATION_MESSAGE; 						//VERR
//		
//		}
//		
//	}
	
}
