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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class MensajeYaExistenReservas extends JFrame{
	
	private static final long serialVersionUID = 1L;

		private JPanel contentPane;
		
		private JTextArea textoReservasExistentes;
		private JScrollPane scrollPaneTexto;
		
		private int filasTexto = 3;	//Ver para alargar si hay mas reservas involucradas
		private int columnasTexto = 15;	//Idem
		
		private JLabel labelTexto;
		private JLabel labelIcono;
		private JButton botonDerecho;
		private JButton botonIzquierdo;
		
		private Insets insetTexto = new Insets(0, 0,20,0);
		private Insets insetIcono2Opc = new Insets(0,15,10,0);
		private Insets insetOpcionAceptar = new Insets(0,80,10,0);
		private Insets insetOpcionCancelar = new Insets(0,25,10,0);
		private Insets insetTextoReservasExistentes = new Insets(0,20,0,20);
		
		private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
		
		private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
		
		private Dimension dimensionBotonDerecho = new Dimension(155, 35);
		private Dimension dimensionTextoReservasExistentes = new Dimension(200,60);

		public MensajeYaExistenReservas(String reservasExistentes) {
			super("Sistema Hotel Premier");
			//final PanelPermiteMensajes panel, JFrame frame
			//frame.setEnabled(false);	//Para que solo se pueda clickear el mensaje
			
			//System.out.println(reservasExistentes.length());
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 240);
			contentPane = new JPanel();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			
			contentPane.setBackground(Color.WHITE);
			
			contentPane.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			labelIcono = new JLabel();	c.weightx = 0.01; c.weighty = 0.01;
			labelIcono.setIcon(new ImageIcon(getClass().getResource("Advertencia.PNG")));
			//labelIcono.setPreferredSize(new Dimension(20, 10));
			c.insets = insetIcono2Opc;
			c.anchor = GridBagConstraints.CENTER;
				c.gridx = 0; c.gridy = 0;
			contentPane.add(labelIcono, c);

			labelTexto = new JLabel("<html>En el período seleccionado existen ya las <br/> siguientes reservas:</html>");	
			c.weightx = 0.2; c.weighty = 0.3;	c.insets = insetTexto;
			labelTexto.setFont(fuenteBoton);
			c.anchor = GridBagConstraints.SOUTHWEST;	c.weightx = 0.4; c.weighty = 0.1;
				c.gridx = 1; c.gridy = 0;
			contentPane.add(labelTexto, c);

			textoReservasExistentes = new JTextArea(reservasExistentes, filasTexto, columnasTexto);
			textoReservasExistentes.setEditable(false); 
			textoReservasExistentes.setPreferredSize(dimensionTextoReservasExistentes);
			scrollPaneTexto = new JScrollPane(textoReservasExistentes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPaneTexto.setBackground(Color.WHITE);
			scrollPaneTexto.setPreferredSize(dimensionTextoReservasExistentes);
			scrollPaneTexto.setEnabled(true);
			c.insets = insetTextoReservasExistentes;
			c.anchor = GridBagConstraints.NORTH;	
			c.gridwidth = 2;	c.weightx = 0.3; c.weighty = 0.3; c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0; c.gridy = 1;
			contentPane.add(scrollPaneTexto, c);
			
			c.gridwidth = 1;	c.weightx = 0.1; c.weighty = 0.1;	c.fill = GridBagConstraints.NONE;
			
			botonDerecho = new JButton("Ocupar igualmente");
			botonDerecho.setFont(fuenteBoton);
			botonDerecho.setBorder(bordeBoton);
			botonDerecho.setBackground(Color.decode("#E0E0E0"));
			botonDerecho.setPreferredSize(dimensionBotonDerecho);
			botonDerecho.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//frame.setEnabled(true);
					//dispose();
					//panel.confirmoElMensaje(id);
				}
			});
			c.anchor = GridBagConstraints.CENTER;	c.insets = insetOpcionAceptar;
				c.gridx = 1; c.gridy = 2;
			contentPane.add(botonDerecho, c);
			
			botonIzquierdo = new JButton("Volver");
			botonIzquierdo.setFont(fuenteBoton);
			botonIzquierdo.setBorder(bordeBoton);
			botonIzquierdo.setBackground(Color.decode("#E0E0E0"));
			botonIzquierdo.setPreferredSize(new Dimension(100, 35));
			botonIzquierdo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//frame.setEnabled(true);
					//dispose();
					//panel.confirmoCancelar(id);
				}
			});
			c.anchor = GridBagConstraints.CENTER;	c.insets = insetOpcionCancelar;
				c.gridx = 0; c.gridy = 2;
			contentPane.add(botonIzquierdo, c);

			setContentPane(contentPane);
		}
															//TODO: VER
		protected void processWindowEvent(WindowEvent e) {	//Para que al cerrarse con la cruz (arriba a la derecha) no se cierren todos los frames
			
			super.processWindowEvent(e);
	        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
	        	
	            this.dispose();
//	            frameActual.toFront();	
//	            frameActual.setEnabled(true);
	        }
		} 
		
	}
