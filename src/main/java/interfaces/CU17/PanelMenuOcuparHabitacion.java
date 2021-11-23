package main.java.interfaces.CU17;

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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelMenuOcuparHabitacion extends JPanel{
	
	private JButton cargarOtroPasajero;
	private JButton cargarOtraHabitacion;
	private JButton salir;
	
	private JLabel label;
	
	//private FrameAutenticarUsuario frameAnterior;
	private FrameMenuOcuparHabitacion frameActual;
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Dimension dimensionBoton = new Dimension(200, 50);
	
	public PanelMenuOcuparHabitacion(FrameMenuOcuparHabitacion frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Ocupar Habitaci�n", 1, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("<html>Operaci�n exitosa. La habitaci�n<br/> ha sido ocupada por todos los<br/> pasajeros seleccionados.<html>");
		label.setFont(fuenteBoton);
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0; c.gridy = 0;
		c.weightx = 0.1; c.weighty = 0.2;			
		this.add(label, c);
		 c.weighty = 0.1;	
	
		cargarOtroPasajero = new JButton("Cargar otro pasajero");
		cargarOtroPasajero.setMinimumSize(dimensionBoton);
		cargarOtroPasajero.setPreferredSize(dimensionBoton);
		cargarOtroPasajero.setBackground(Color.decode("#E0E0E0"));
		cargarOtroPasajero.setFont(fuenteBoton);
		cargarOtroPasajero.setBorder(bordeBoton);
		cargarOtroPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		c.anchor = GridBagConstraints.CENTER;	
		c.gridy = 1;
		this.add(cargarOtroPasajero, c);
		
		cargarOtraHabitacion = new JButton("Cargar otra habitaci�n");
		cargarOtraHabitacion.setMinimumSize(dimensionBoton);
		cargarOtraHabitacion.setPreferredSize(dimensionBoton);
		cargarOtraHabitacion.setBackground(Color.decode("#E0E0E0"));
		cargarOtraHabitacion.setFont(fuenteBoton);
		cargarOtraHabitacion.setBorder(bordeBoton);
		cargarOtraHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 2;
		this.add(cargarOtraHabitacion, c);
		
		salir = new JButton("Salir");
		salir.setMinimumSize(dimensionBoton);
		salir.setPreferredSize(dimensionBoton);
		salir.setBackground(Color.decode("#E0E0E0"));
		salir.setFont(fuenteBoton);
		salir.setBorder(bordeBoton);
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		c.anchor = GridBagConstraints.CENTER;	c.insets = new Insets(0,0,30,0);
		c.gridy = 3;
		this.add(salir, c);
		
	}
}