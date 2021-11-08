package main.java.interfaces.CU05;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import main.java.enums.TipoMensaje;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.CU02.FrameGestionarPasajero;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;



public class PanelMostrarEstadoHabitacionesGroupBox extends JPanel{
	
	private JLabel label;
	private JLabel labelFechaDesdeVacio;
	private JLabel labelFechaHastaVacio;
	
	private JTextField fechaDesde;
	private JTextField fechaHasta;
	
	private Insets insetLabel = new Insets(0,0,0,20);
	private Insets insetCampo = new Insets(0,20,0,0);
	private Insets insetLabelError = new Insets(0,30,0,0);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private Dimension dimensionCampo = new Dimension(250, 30);
	
	TextPrompt fondoJTextField;
	
	public PanelMostrarEstadoHabitacionesGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Mostrar Estado Habitaciones", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.EAST;	c.gridx = 0;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;
	
		label = new JLabel("Fecha desde");	label.setFont(fuenteLabelCampo); c.gridy = 0;	this.add(label, c);
			
		c.anchor = GridBagConstraints.NORTH; c.insets = insetLabelError;
		
//		labelCampoVacio = new JLabel();	labelCampoVacio.setFont(fuenteLabelError); 
//		labelCampoVacio.setText(" Campo incompleto ");
//		labelCampoVacio.setOpaque(true);	
//		labelCampoVacio.setBackground(Color.decode("#cc0000"));
//		labelCampoVacio.setForeground(Color.WHITE);
//		c.gridx = 1;
//		this.add(labelCampoVacio, c);
//		
//		labelCampoVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;  c.insets = insetCampo;	
		
		//fechaDesde = new JTextField();
		try {
			MaskFormatter mascaraFechaNacimiento = new MaskFormatter("##'/##'/####");
			fechaDesde = new JFormattedTextField(mascaraFechaNacimiento);
	    	
	    }catch (ParseException e) {
	    	e.printStackTrace();
	    }
		fechaDesde.setFont(fuenteLabelCampo);	fechaDesde.setBorder(bordeCampo);
//		fechaDesde.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
//			  public void changedUpdate(DocumentEvent e) {
//				  labelCampoVacio.setVisible(false);
//			  }
//			  public void removeUpdate(DocumentEvent e)  {
//				  labelCampoVacio.setVisible(false);
//			  }
//			  public void insertUpdate(DocumentEvent e) {
//				  labelCampoVacio.setVisible(false);
//			  }
//		});
//		fondoJTextField = new TextPrompt("Ingrese un nro de habitación",fechaDesde); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 1;	fechaDesde.setMinimumSize(dimensionCampo);	fechaDesde.setPreferredSize(dimensionCampo);	fechaDesde.setMaximumSize(dimensionCampo);
		this.add(fechaDesde, c);
		
		c.anchor = GridBagConstraints.EAST;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;
		
		label = new JLabel("Fecha hasta");	label.setFont(fuenteLabelCampo); c.gridx = 2;;	this.add(label, c);
			
		c.anchor = GridBagConstraints.NORTH; c.insets = insetLabelError;
		
//		labelCampoVacio = new JLabel();	labelCampoVacio.setFont(fuenteLabelError); 
//		labelCampoVacio.setText(" Campo incompleto ");
//		labelCampoVacio.setOpaque(true);	
//		labelCampoVacio.setBackground(Color.decode("#cc0000"));
//		labelCampoVacio.setForeground(Color.WHITE);
//		c.gridx = 1;
//		this.add(labelCampoVacio, c);
//		
//		labelCampoVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;  c.insets = insetCampo;	
		
		//fechaHasta = new JTextField(); 
			try {
				MaskFormatter mascaraFechaNacimiento = new MaskFormatter("##'/##'/####");
				fechaHasta = new JFormattedTextField(mascaraFechaNacimiento);
		    	
		    }catch (ParseException e) {
		    	e.printStackTrace();
		    }
		fechaHasta.setFont(fuenteLabelCampo);	fechaHasta.setBorder(bordeCampo);	
//		fechaHasta.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
//			  public void changedUpdate(DocumentEvent e) {
//				  labelCampoVacio.setVisible(false);
//			  }
//			  public void removeUpdate(DocumentEvent e)  {
//				  labelCampoVacio.setVisible(false);
//			  }
//			  public void insertUpdate(DocumentEvent e) {
//				  labelCampoVacio.setVisible(false);
//			  }
//		});
//		fondoJTextField = new TextPrompt("Ingrese un nro de habitación",fechaHasta); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 3;	fechaHasta.setMinimumSize(dimensionCampo);	fechaHasta.setPreferredSize(dimensionCampo);	fechaHasta.setMaximumSize(dimensionCampo);
		this.add(fechaHasta, c);

	}

	public void inputNoEsVacia() throws InputVacioException{

		if(this.fechaDesde.getText().isEmpty() || this.fechaHasta.getText().isEmpty()) {

			throw new InputVacioException();
		}
		
	}

	public void colocarLabelIncompleto() {
		
		//this.labelCampoVacio.setVisible(true);
	}
}