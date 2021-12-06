package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

import main.java.enums.TipoMensaje;

public class Mensaje extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private Integer id;
	private String texto;
	private ImageIcon icono;
	private String textoOpcionAceptar;
	private String textoOpcionCancelar;
	
	private JLabel labelTexto;
	private JLabel labelIcono;
	private JButton botonDerecho;
	private JButton botonIzquierdo;
	
	private Insets insetTexto = new Insets(0,15,20,0);
	private Insets insetIcono1Opc = new Insets(0,40,10,0);
	private Insets insetIcono2Opc = new Insets(0,15,10,0);
	private Insets insetOpcionAceptar = new Insets(0,80,10,0);
	private Insets insetOpcionCancelar = new Insets(0,25,10,0);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private JFrame frameActual;

	public Mensaje(Integer id, String texto, TipoMensaje tipo, String opcionAceptar, final String opcionCancelar) {	
		super();
		this.setTitle("Sistema Hotel Premier");
		this.id = id;
		this.texto = texto;
		this.elegirIconoSegunTipoMensaje(tipo);
		this.textoOpcionAceptar = opcionAceptar;
		this.textoOpcionCancelar = opcionCancelar;
	}
	
	public void mostrar(final PanelPermiteMensajes panel, JFrame frame) {
		
		frameActual = frame;
		
		frame.setEnabled(false);	//Para que solo se pueda clickear el mensaje
		
		setBounds(100, 100, 400, 240);
		contentPane = new JPanel();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		labelIcono = new JLabel();	c.weightx = 0.1; c.weighty = 0.1;
		labelIcono.setIcon(icono);
		c.insets = ((textoOpcionCancelar != null) ? insetIcono2Opc : insetIcono1Opc); //Depende de las opciones el inset
		c.anchor = GridBagConstraints.CENTER;
			c.gridx = 0; c.gridy = 0;
		contentPane.add(labelIcono, c);
		
		labelTexto = new JLabel(texto);	c.weightx = 0.2; c.weighty = 0.5;	c.insets = insetTexto;
		labelTexto.setFont(fuenteBoton);
		labelTexto.setPreferredSize(new Dimension(200, 100));
		c.anchor = GridBagConstraints.CENTER;	
			c.gridx = 1; c.gridy = 0;
		contentPane.add(labelTexto, c);
		
		botonDerecho = new JButton(textoOpcionAceptar); c.weightx = 0.0; c.weighty = 0.0;
		botonDerecho.setFont(fuenteBoton);
		botonDerecho.setBorder(bordeBoton);
		botonDerecho.setBackground(Color.decode("#E0E0E0"));
		if(textoOpcionAceptar.length() > 10) {	//Por ejemplo, si el texto es "Aceptar igualmente" necesita que el boton cubra todo el texto
			botonDerecho.setPreferredSize(new Dimension(textoOpcionAceptar.length()+140, 35));
		}
		else {
			botonDerecho.setPreferredSize(new Dimension(100, 35));
		}
		botonDerecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setEnabled(true);
				dispose();
				panel.confirmoElMensaje(id);
			}
		});
		c.anchor = GridBagConstraints.CENTER;	c.insets = insetOpcionAceptar;
			c.gridx = 1; c.gridy = 1;
		contentPane.add(botonDerecho, c);
		
		if(textoOpcionCancelar != null) {
			botonIzquierdo = new JButton(textoOpcionCancelar);
			botonIzquierdo.setFont(fuenteBoton);
			botonIzquierdo.setBorder(bordeBoton);
			botonIzquierdo.setBackground(Color.decode("#E0E0E0"));
			botonIzquierdo.setPreferredSize(new Dimension(100, 35));
			botonIzquierdo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.setEnabled(true);
					dispose();
					panel.confirmoCancelar(id);
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
	
	public void setTextoMensaje(String s) {
		this.texto = s;
	}
	
	protected void processWindowEvent(WindowEvent e) {	//Para que al cerrarse con la cruz (arriba a la derecha) no se cierren todos los frames
		
		super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
        	
            this.dispose();
            frameActual.toFront();	
            frameActual.setEnabled(true);
        }
	} 
	
}
