package main.java.interfaces.CU04;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class PanelConfirmarDatosHabitacionGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	//private PanelConDatosDeHabitaciones panelConDatosDeHabitaciones = new PanelConDatosDeHabitaciones();
	
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox2 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox3 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox4 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox5 = new PanelNroHabitacionGroupBox();
	
	private Insets insetPanelesDatosHabitacion = new Insets(10,10,10,10);
	
	public PanelConfirmarDatosHabitacionGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelesDatosHabitacion;;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL; 	
		c.weightx = 0.1;	c.weighty = 0.1;
		
		c.gridx = 0; c.gridy = 0;
		this.add(panelNroHabitacionGroupBox, c);
		
		c.gridx = 1; c.gridy = 0;
		this.add(panelNroHabitacionGroupBox2, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 0; c.gridy = 1;
		this.add(panelNroHabitacionGroupBox3, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 1; c.gridy = 1;
		this.add(panelNroHabitacionGroupBox4, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 0; c.gridy = 2;
		this.add(panelNroHabitacionGroupBox5, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 1; c.gridy = 2;
		//this.add(panelNroHabitacionGroupBox6, c);
		//this.add(labelInvisible, c);
		
	}

}
