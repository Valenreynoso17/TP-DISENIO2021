package main.java.interfaces.CU04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import main.java.interfaces.clasesExtra.RoundedBorder;


public class PanelNroHabitacionGroupBox extends JPanel{
	
	private JLabel label;
	
	private JTextField tipoHabitacion;					//Campos de texto (no editables)
	private JTextField ingreso;
	private JTextField egreso;
	
	private double pesoXLabel = 0.0;
	private double pesoYLabel = 0.0;
	private double pesoXCampo = 0.0;
	private double pesoYCampo = 0.0;
	
	private Insets insetLabel = new Insets(0,20,0,0);	//Espacios en blanco para acomodar los componentes
	private Insets insetCampo = new Insets(0,10,10,10);
	
	private Dimension dimensionCampo = new Dimension(330, 30);
	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 15);
	
	private Color colorFondoCampoNoEditable = Color.decode("#f5f5f5");
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	public PanelNroHabitacionGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Nro. Habitación: xxxx", 0, 0, fuenteGroupBox));	//TODO: Poner el nro de la habitacion
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
	
		label = new JLabel("Tipo Habitación");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		tipoHabitacion = new JTextField(); 	tipoHabitacion.setText("TipoHabitacion");	tipoHabitacion.setEditable(false);
		tipoHabitacion.setFont(fuenteLabelCampo);	tipoHabitacion.setBorder(bordeCampo);	tipoHabitacion.setBackground(colorFondoCampoNoEditable);
		c.gridy = 1;	tipoHabitacion.setMinimumSize(dimensionCampo);	tipoHabitacion.setPreferredSize(dimensionCampo);
		this.add(tipoHabitacion, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Ingreso");	label.setFont(fuenteLabelCampo);	c.gridy = 2;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		ingreso = new JTextField(); ingreso.setText("nombreDia, dd/mm/aaaa, 12:00hs");	ingreso.setEditable(false);
		ingreso.setFont(fuenteLabelCampo);	ingreso.setBorder(bordeCampo);		ingreso.setBackground(colorFondoCampoNoEditable);
		c.gridy = 3;	ingreso.setMinimumSize(dimensionCampo);	ingreso.setPreferredSize(dimensionCampo);
		this.add(ingreso, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Egreso");	label.setFont(fuenteLabelCampo);	c.gridy = 4;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		egreso = new JTextField(); egreso.setText("nombreDia, dd/mm/aaaa, 12:00hs");	egreso.setEditable(false);
		egreso.setFont(fuenteLabelCampo);	egreso.setBorder(bordeCampo);	egreso.setBackground(colorFondoCampoNoEditable);
		c.gridy = 5;	egreso.setMinimumSize(dimensionCampo);	egreso.setPreferredSize(dimensionCampo);
		this.add(egreso, c); 		
		
	}

}