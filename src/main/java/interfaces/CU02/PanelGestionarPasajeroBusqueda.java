package main.java.interfaces.CU02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.enums.TipoDocumento;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelGestionarPasajeroBusqueda extends JPanel{
	
	private FrameGestionarPasajero frameActual;
	
	private JComboBox tipoDocumento;
	
	private JLabel label;
	
	private JTextField apellido;
	private JTextField nombre;
	private JTextField numeroDocumento;
	
	private Insets insetLabel = new Insets(0,20,0,0);
	private Insets insetCampo = new Insets(0,0,0,30);
	
	private double pesoXLabel = 0.3;
	private double pesoYLabel = 0.5;
	private double pesoXCampo = 0.3;
	private double pesoYCampo = 0.1;
	
	private Dimension dimensionCampo = new Dimension(180, 30);
	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.decode("#BDBDBD"));
	
//	private ComboBoxRenderer rendererListasDesplegables = new ComboBoxRenderer();
//	
//	ComboBoxEditor renderer = new ComboBoxEditor();
	
	private TextPrompt fondoJTextField;
	//cuadradito de arriba

	public PanelGestionarPasajeroBusqueda(FrameGestionarPasajero frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Gestionar Pasajero", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.WEST;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Apellido");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		apellido = new JTextField(); apellido.setFont(fuenteLabelCampo);	apellido.setBorder(bordeCampo);	
		fondoJTextField = new TextPrompt("Ingrese un apellido",apellido); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 1; c.gridy = 0;	apellido.setMinimumSize(dimensionCampo);	apellido.setPreferredSize(dimensionCampo);	this.add(apellido, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;
		
		label = new JLabel("Nombre");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		nombre = new JTextField();	nombre.setFont(fuenteLabelCampo);	nombre.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese un nombre", nombre); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 3; c.gridy = 0;	nombre.setMinimumSize(dimensionCampo);	nombre.setPreferredSize(dimensionCampo);	this.add(nombre, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; 
		
		label = new JLabel("Tipo de documento");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
		
		c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
	
		tipoDocumento = new JComboBox<String>();	tipoDocumento.setFont(fuenteLabelCampo);	tipoDocumento.setBackground(Color.white);	
		c.gridx = 1; c.gridy = 1;	tipoDocumento.setMinimumSize(dimensionCampo);	tipoDocumento.setPreferredSize(dimensionCampo);
		tipoDocumento.addItem("--Seleccione");
		this.cargarComboBoxDesdeEnum(tipoDocumento, TipoDocumento.values());
		this.add(tipoDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;
		
		label = new JLabel("Número de documento");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 1;	this.add(label, c);
		
			c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		numeroDocumento = new JTextField();	numeroDocumento.setFont(fuenteLabelCampo);	numeroDocumento.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese el número de documento", numeroDocumento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 3; c.gridy = 1;	numeroDocumento.setMinimumSize(dimensionCampo);	numeroDocumento.setPreferredSize(dimensionCampo);	this.add(numeroDocumento, c);
		
	
	}
	
	private void cargarComboBoxDesdeEnum(JComboBox comboBox, Object[] values) {
		
		for(Object o : values){
			
			comboBox.addItem(String.valueOf(o).replaceAll("_", " ")); //Para que no aparezcan los guiones bajos
		}
}
}
