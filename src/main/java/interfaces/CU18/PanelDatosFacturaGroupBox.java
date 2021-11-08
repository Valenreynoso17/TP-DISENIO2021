package main.java.interfaces.CU18;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.enums.TipoMensaje;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.CU02.FrameGestionarPasajero;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;



public class PanelDatosFacturaGroupBox extends JPanel{
	
	private JLabel label;
	
	private JTextField nroFactura;					//Campos de texto (no editables)
	private JTextField cuit;
	private JTextField razonSocial;
	private JTextField fechaFacturacion;
	private JTextField tipoFactura;
	private JTextField totalAPagar;
	
	private double pesoXLabel = 0.3;
	private double pesoYLabel = 0.1;
	private double pesoXCampo = 0.3;
	private double pesoYCampo = 0.3;
	
	private Insets insetLabel = new Insets(0,30,0,0);	//Espacios en blanco para acomodar los componentes
	private Insets insetCampo = new Insets(0,10,0,10);
	
	private Dimension dimensionCampo = new Dimension(200, 30);
	private Dimension dimensionCampoTipoFactura = new Dimension(22, 30);
	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);
	
	private Color colorFondoCampoNoEditable = Color.decode("#f5f5f5");
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	public PanelDatosFacturaGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Datos factura", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
	
		label = new JLabel("Nro. factura");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		nroFactura = new JTextField(); 	nroFactura.setText("REEMPLAZAR");	nroFactura.setEditable(false);
		nroFactura.setFont(fuenteLabelCampo);	nroFactura.setBorder(bordeCampo);	nroFactura.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; c.gridy = 0;	nroFactura.setMinimumSize(dimensionCampo);	nroFactura.setPreferredSize(dimensionCampo);
		this.add(nroFactura, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("CUIT");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		cuit = new JTextField(); cuit.setText("REEMPLAZAR");	cuit.setEditable(false);
		cuit.setFont(fuenteLabelCampo);	cuit.setBorder(bordeCampo);		cuit.setBackground(colorFondoCampoNoEditable);
		c.gridx = 3; c.gridy = 0;	cuit.setMinimumSize(dimensionCampo);	cuit.setPreferredSize(dimensionCampo);
		this.add(cuit, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Razón social");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		razonSocial = new JTextField(); razonSocial.setText("REEMPLAZAR");	razonSocial.setEditable(false);
		razonSocial.setFont(fuenteLabelCampo);	razonSocial.setBorder(bordeCampo);	razonSocial.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; c.gridy = 1;	razonSocial.setMinimumSize(dimensionCampo);	razonSocial.setPreferredSize(dimensionCampo);
		this.add(razonSocial, c); 		
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Fecha facturación");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 1;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		fechaFacturacion = new JTextField(); fechaFacturacion.setText("REEMPLAZAR");	fechaFacturacion.setEditable(false);
		fechaFacturacion.setFont(fuenteLabelCampo);	fechaFacturacion.setBorder(bordeCampo);		fechaFacturacion.setBackground(colorFondoCampoNoEditable);
		c.gridx = 3; c.gridy = 1;	fechaFacturacion.setMinimumSize(dimensionCampo);	fechaFacturacion.setPreferredSize(dimensionCampo);	
		this.add(fechaFacturacion, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Tipo factura");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 2;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
			c.anchor = GridBagConstraints.WEST; c.fill = GridBagConstraints.NONE;
		tipoFactura = new JTextField(); tipoFactura.setText("A");	tipoFactura.setEditable(false);
		tipoFactura.setFont(fuenteLabelCampo);	tipoFactura.setBorder(bordeCampo);		tipoFactura.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; c.gridy = 2;	tipoFactura.setMinimumSize(dimensionCampoTipoFactura);	tipoFactura.setPreferredSize(dimensionCampoTipoFactura);	tipoFactura.setMaximumSize(dimensionCampoTipoFactura);
		this.add(tipoFactura, c); 		
		
			c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Total a pagar");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 2;	this.add(label, c);
	
			 c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		totalAPagar = new JTextField(); totalAPagar.setText("REEMPLAZAR");	totalAPagar.setEditable(false);
		totalAPagar.setFont(fuenteLabelCampo);	totalAPagar.setBorder(bordeCampo);	totalAPagar.setBackground(colorFondoCampoNoEditable);
		c.gridx = 3; c.gridy = 2;	totalAPagar.setMinimumSize(dimensionCampo);	totalAPagar.setPreferredSize(dimensionCampo);	
		this.add(totalAPagar, c); 	
		
	}

}