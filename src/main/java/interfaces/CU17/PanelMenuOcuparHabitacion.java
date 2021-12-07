package main.java.interfaces.CU17;

import java.awt.BasicStroke;
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

import main.java.dtos.OcupacionDTO;
import main.java.gestores.GestorOcupacion;
import main.java.interfaces.CU05.PanelMostrarEstadoHabitaciones;
import main.java.interfaces.MenuPrincipal.PanelMenuPrincipal;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.frames.FramePrincipal;

public class PanelMenuOcuparHabitacion extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private GestorOcupacion gestorOcupacion;
	
	private JButton cargarOtroPasajero;
	private JButton cargarOtraHabitacion;
	private JButton salir;
	
	private JLabel label;
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Dimension dimensionBoton = new Dimension(200, 50);
	
	public PanelMenuOcuparHabitacion(FramePrincipal frame, PanelOcuparHabitacionConPasajeros panelAnterior, OcupacionDTO ocupacion) {
		this.gestorOcupacion = GestorOcupacion.getInstance();
		
		this.setBackground(Color.white);
		
		//this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Ocupar Habitación", 1, 0, fuenteGroupBox));
		this.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(
						javax.swing.BorderFactory.createEmptyBorder(10, 20, 20, 20),
						javax.swing.BorderFactory.createTitledBorder(new LineBorder (Color.black, 1), " Ocupar Habitación", 1, 0, fuenteGroupBox)
						)
		);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("<html>Operación exitosa. La habitación<br/> ha sido ocupada por todos los<br/> pasajeros seleccionados.<html>");
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
				
				frame.setNuevoPanel(panelAnterior);
			}
		});
		c.anchor = GridBagConstraints.CENTER;	
		c.gridy = 1;
		this.add(cargarOtroPasajero, c);
		
		cargarOtraHabitacion = new JButton("Cargar otra habitación");
		cargarOtraHabitacion.setMinimumSize(dimensionBoton);
		cargarOtraHabitacion.setPreferredSize(dimensionBoton);
		cargarOtraHabitacion.setBackground(Color.decode("#E0E0E0"));
		cargarOtraHabitacion.setFont(fuenteBoton);
		cargarOtraHabitacion.setBorder(bordeBoton);
		cargarOtraHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorOcupacion.guardarOcupacion(ocupacion);
				
				frame.setNuevoPanel(new PanelMostrarEstadoHabitaciones(frame, new PanelMenuPrincipal(frame)));
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
				gestorOcupacion.guardarOcupacion(ocupacion);
				
				frame.setNuevoPanel(new PanelMenuPrincipal(frame));
			}
		});
		c.anchor = GridBagConstraints.CENTER;	c.insets = new Insets(0,0,30,0);
		c.gridy = 3;
		this.add(salir, c);
		
	}
}
