package main.java.interfaces.julio.otros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.enmus.TipoMensaje;

public class Mensaje extends JFrame{

	private JPanel contentPane;
	
	private JLabel labelTexto;
	private JLabel labelIcono;
	private JButton botonDerecho;
	private JButton botonIzquierdo;
	
	private ImageIcon icono = new ImageIcon(getClass().getResource("Advertencia.PNG"));
	
	private Insets insetTexto = new Insets(0,15,20,0);
	private Insets insetIcono1Opc = new Insets(0,40,10,0);
	private Insets insetIcono2Opc = new Insets(0,15,10,0);
	private Insets insetOpcionAceptar = new Insets(0,80,10,0);
	private Insets insetOpcionCancelar = new Insets(0,25,10,0);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));

	public Mensaje(final PanelPermiteMensajes panel, JFrame frame, TipoMensaje tipo, String texto, String opcionAceptar, final String opcionCancelar) {
		super("Sistema Hotel Premier");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 240);
		contentPane = new JPanel();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.elegirIconoSegunTipoMensaje(tipo);
		
		labelIcono = new JLabel();	c.weightx = 0.1; c.weighty = 0.1;
		labelIcono.setIcon(icono);
		c.insets = ((opcionCancelar != null) ? insetIcono2Opc : insetIcono1Opc); //Depende de las opciones el inset
		c.anchor = GridBagConstraints.CENTER;
			c.gridx = 0; c.gridy = 0;
		contentPane.add(labelIcono, c);
		
		labelTexto = new JLabel(texto);	c.weightx = 0.2; c.weighty = 0.3;	c.insets = insetTexto;
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

				dispose();
				panel.confirmoElMensaje();
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

					dispose();
					panel.confirmoCancelar();
				}
			});
			c.anchor = GridBagConstraints.CENTER;	c.insets = insetOpcionCancelar;
				c.gridx = 0; c.gridy = 1;
			contentPane.add(botonIzquierdo, c);
		}
		
		setContentPane(contentPane);
		
	}

	private void elegirIconoSegunTipoMensaje(TipoMensaje tipo) {

		switch(tipo) {
			case ADVERTENCIA:
				this.icono = new ImageIcon(getClass().getResource("Advertencia.PNG"));
				break;
			case CONFIRMACION:
				this.icono = new ImageIcon(getClass().getResource("Confirmacion.PNG"));
				break;
			case ERROR:
				this.icono = new ImageIcon(getClass().getResource("Error.PNG"));
				break;
			case EXITO:
				this.icono = new ImageIcon(getClass().getResource("Exito.PNG"));
				break;
		}
	}
	
}
