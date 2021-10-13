package main.java.interfaces.julio.paneles;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.interfaces.julio.frames.FrameAltaPasajero;



public class PanelAltaPasajero extends JPanel{
	
	private PanelAltaPasajeroDatos panelDarAltaPasajero = new PanelAltaPasajeroDatos();
	
	private JButton cancelar;
	private JButton siguiente;

	private Insets i = new Insets(20,10,10,10);
//	private GestorEstacion gestorEstacion = GestorEstacion.getInstance();
//	
//	private EstacionGestionar frameAnterior;
//	private EstacionAltaGrafo frameSiguiente;
	
	public PanelAltaPasajero(final FrameAltaPasajero frame) {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = i;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 2;
		c.weightx = 0.5; c.weighty = 0.5;			this.add(panelDarAltaPasajero, c);
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
//				frameAnterior = new EstacionGestionar();
			}
		});
		c.anchor = GridBagConstraints.CENTER;
													c.gridx = 0; c.gridy = 1;
													this.add(cancelar, c);

		siguiente = new JButton("Siguiente");
		c.anchor = GridBagConstraints.CENTER;
													c.gridx = 1; c.gridy = 1;
													this.add(siguiente, c);
	}
}

