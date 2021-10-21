package main.java.interfaces.CU11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.dtos.DireccionDTO;
import main.java.dtos.PaisDTO;
import main.java.dtos.PasajeroDTO;
import main.java.enums.PosicionFrenteIva;
import main.java.enums.TipoDocumento;
import main.java.enums.TipoMensaje;
import main.java.excepciones.InputVacioException;
import main.java.gestores.GestorPaisProvincia;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelAltaPasajeroDatos extends JPanel{
	
	private FrameAltaPasajero frameActual;
	
	private GestorPaisProvincia gestorPP;
	
	private JComboBox tipoDocumento;
	private JComboBox<PaisDTO> pais;
	private JComboBox provincia;
	private JComboBox localidad;
	private JComboBox nacionalidad;
	private JComboBox posicionIVA;
	
	private JLabel label;
	
	private JLabel labelApellidoVacio;				//Muestran mensaje "Campo incompleto"
	private JLabel labelNombreVacio;
	private JLabel labelNumeroDocumentoVacio;
	private JLabel labelEmailVacio;
	private JLabel labelTelefonoVacio;
	private JLabel labelOcupacionVacio;
	private JLabel labelDireccionVacio;
	private JLabel labelCodigoPostalVacio;
	private JLabel labelCuitVacio;
	
	private JLabel labelApellidoFormatoInvalido;	//Muestran mensaje "Formato inválido"
	private JLabel labelNombreFormatoInvalido;
	private JLabel labelNumeroDocumentoFormatoInvalido;
	private JLabel labelEmailFormatoInvalido;
	private JLabel labelTelefonoFormatoInvalido;
	private JLabel labelOcupacionFormatoInvalido;
	private JLabel labelDireccionFormatoInvalido;
	private JLabel labelDepartamentoFormatoInvalido;
	private JLabel labelPisoFormatoInvalido;
	private JLabel labelCodigoPostalFormatoInvalido;
	private JLabel labelCuitFormatoInvalido;
	
	private JTextField apellido;					//Campos de texto
	private JTextField nombre;
	private JTextField numeroDocumento;
	private JTextField fechaNacimiento;
	private JTextField email;
	private JTextField telefono;
	private JTextField ocupacion;
	private JTextField direccion;
	private JTextField departamento;
	private JTextField piso;
	private JTextField codigoPostal;
	private JTextField cuit;
	
	private Insets insetLabel = new Insets(0,70,0,0);	//Espacios en blanco para acomodar los componentes
	private Insets insetLabelDobleIzq = new Insets(0,70,0,10);
	private Insets insetLabelDobleDer = new Insets(0,10,0,70);
	private Insets insetCampo = new Insets(0,70,0,70);
	private Insets insetCampoDobleIzq = new Insets(0,70,0,10);
	private Insets insetCampoDobleDer = new Insets(0,10,0,70);
	//private Insets insetCampoUltimos = new Insets(0,70,20,70);
	private Insets insetLabelError = new Insets(0,0,0,20);
	private Insets insetLabelVacioCP = new Insets(0,60,0,0);
	private Insets insetLabelDepartamentoInvalido = new Insets(0,100,0,0);
	
	private double pesoXLabel = 0.3;
	private double pesoYLabel = 0.5;
	private double pesoXCampo = 0.3;
	private double pesoYCampo = 0.1;
	
	private double pesoXLabelDoble = 0.3;
	private double pesoXCampoDoble = 0.3;
	
	private Dimension dimensionCampo = new Dimension(180, 30);
	
	private Font fuenteLabelFinal =new Font("SourceSansPro", Font.ITALIC, 13);	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.decode("#BDBDBD"));
	
//	private ComboBoxRenderer rendererListasDesplegables = new ComboBoxRenderer();
//	
//	ComboBoxEditor renderer = new ComboBoxEditor();
	
	private TextPrompt fondoJTextField;
	
//	private GestorEstacion gestorEstacion = GestorEstacion.getInstance();
//	
//	private EstacionGestionar frameAnterior;
//	private EstacionAltaGrafo frameSiguiente;
	
	public PanelAltaPasajeroDatos(FrameAltaPasajero frame) {
		
		this.frameActual = frame;
		gestorPP =  GestorPaisProvincia.getInstance();
		
		this.setBackground(Color.WHITE);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Dar de alta pasajero", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Apellido*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelApellidoVacio = new JLabel(" Campo incompleto ");	labelApellidoVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 0; 
		labelApellidoVacio.setOpaque(true);	labelApellidoVacio.setBackground(Color.decode("#cc0000")); labelApellidoVacio.setForeground(Color.WHITE);
		this.add(labelApellidoVacio, c); labelApellidoVacio.setVisible(false);	//Empieza invisible
		
		labelApellidoFormatoInvalido = new JLabel(" Formato inválido ");	labelApellidoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 0; 
		labelApellidoFormatoInvalido.setOpaque(true);	labelApellidoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelApellidoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelApellidoFormatoInvalido, c); labelApellidoFormatoInvalido.setVisible(false);	//Empieza invisible

			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		apellido = new JTextField(10); apellido.setFont(fuenteLabelCampo);	apellido.setBorder(bordeCampo);	
		apellido.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelApellidoVacio.setVisible(false);
				  labelApellidoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelApellidoVacio.setVisible(false);
				  labelApellidoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelApellidoVacio.setVisible(false);
				  labelApellidoFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese un apellido",apellido); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 1;	apellido.setMinimumSize(dimensionCampo);	apellido.setPreferredSize(dimensionCampo);	
		this.add(apellido, c); 		
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nombre*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelNombreVacio = new JLabel(" Campo incompleto ");	labelNombreVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 0; 
		labelNombreVacio.setOpaque(true);	labelNombreVacio.setBackground(Color.decode("#cc0000")); labelNombreVacio.setForeground(Color.WHITE);
		this.add(labelNombreVacio, c); labelNombreVacio.setVisible(false);	//Empieza invisible
		
		labelNombreFormatoInvalido = new JLabel(" Formato inválido ");	labelNombreFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 0; 
		labelNombreFormatoInvalido.setOpaque(true);	labelNombreFormatoInvalido.setBackground(Color.decode("#cc0000")); labelNombreFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelNombreFormatoInvalido, c); labelNombreFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nombre = new JTextField();	nombre.setFont(fuenteLabelCampo);	nombre.setBorder(bordeCampo);
		nombre.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelNombreVacio.setVisible(false);
				  labelNombreFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelNombreVacio.setVisible(false);
				  labelNombreFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelNombreVacio.setVisible(false);
				  labelNombreFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese un nombre", nombre); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 1;	nombre.setMinimumSize(dimensionCampo);	nombre.setPreferredSize(dimensionCampo);	this.add(nombre, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Tipo de documento*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 2;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		tipoDocumento = new JComboBox();	tipoDocumento.setFont(fuenteLabelCampo);	tipoDocumento.setBackground(Color.white);	
//		tipoDocumento.addItem("--Seleccione");
		this.cargarComboBoxDesdeEnum(tipoDocumento, TipoDocumento.values());
		
		c.gridx = 0; c.gridy = 3;	tipoDocumento.setMinimumSize(dimensionCampo);	tipoDocumento.setPreferredSize(dimensionCampo);
		this.add(tipoDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Número de documento*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 2;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelNumeroDocumentoVacio = new JLabel(" Campo incompleto ");	labelNumeroDocumentoVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 2; 
		labelNumeroDocumentoVacio.setOpaque(true);	labelNumeroDocumentoVacio.setBackground(Color.decode("#cc0000")); labelNumeroDocumentoVacio.setForeground(Color.WHITE);
		this.add(labelNumeroDocumentoVacio, c); labelNumeroDocumentoVacio.setVisible(false);	//Empieza invisible
		
		labelNumeroDocumentoFormatoInvalido = new JLabel(" Formato inválido ");	labelNumeroDocumentoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 2; 
		labelNumeroDocumentoFormatoInvalido.setOpaque(true);	labelNumeroDocumentoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelNumeroDocumentoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelNumeroDocumentoFormatoInvalido, c); labelNumeroDocumentoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		numeroDocumento = new JTextField();	numeroDocumento.setFont(fuenteLabelCampo);	numeroDocumento.setBorder(bordeCampo);
		numeroDocumento.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelNumeroDocumentoVacio.setVisible(false);
				  labelNumeroDocumentoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelNumeroDocumentoVacio.setVisible(false);
				  labelNumeroDocumentoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelNumeroDocumentoVacio.setVisible(false);
				  labelNumeroDocumentoFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el número de documento", numeroDocumento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 3;	numeroDocumento.setMinimumSize(dimensionCampo);	numeroDocumento.setPreferredSize(dimensionCampo);	this.add(numeroDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Fecha de nacimiento*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 4;	this.add(label, c);
		
//			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
//			
//		labelApellidoVacio = new JLabel(" Campo incompleto ");	labelApellidoVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 0; 
//		labelApellidoVacio.setOpaque(true);	labelApellidoVacio.setBackground(Color.decode("#cc0000")); labelApellidoVacio.setForeground(Color.WHITE);
//		this.add(labelApellidoVacio, c); labelApellidoVacio.setVisible(false);	//Empieza invisible
//	
//			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;

		fechaNacimiento = new JTextField();	fechaNacimiento.setFont(fuenteLabelCampo);	fechaNacimiento.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("dd/mm/aaaa", fechaNacimiento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 5;	fechaNacimiento.setMinimumSize(dimensionCampo);	fechaNacimiento.setPreferredSize(dimensionCampo);	this.add(fechaNacimiento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Email*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelEmailVacio = new JLabel(" Campo incompleto ");	labelEmailVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 4; 
		labelEmailVacio.setOpaque(true);	labelEmailVacio.setBackground(Color.decode("#cc0000")); labelEmailVacio.setForeground(Color.WHITE);
		this.add(labelEmailVacio, c); labelEmailVacio.setVisible(false);	//Empieza invisible
		
		labelEmailFormatoInvalido = new JLabel(" Formato inválido ");	labelEmailFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 4; 
		labelEmailFormatoInvalido.setOpaque(true);	labelEmailFormatoInvalido.setBackground(Color.decode("#cc0000")); labelEmailFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelEmailFormatoInvalido, c); labelEmailFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		email = new JTextField();	email.setFont(fuenteLabelCampo);	email.setBorder(bordeCampo);
		email.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelEmailVacio.setVisible(false);
				  labelEmailFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelEmailVacio.setVisible(false);
				  labelEmailFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelEmailVacio.setVisible(false);
				  labelEmailFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el correo electrónico", email); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 5;	email.setMinimumSize(dimensionCampo);	email.setPreferredSize(dimensionCampo);	this.add(email, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Teléfono*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 6;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelTelefonoVacio = new JLabel(" Campo incompleto ");	labelTelefonoVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 6; 
		labelTelefonoVacio.setOpaque(true);	labelTelefonoVacio.setBackground(Color.decode("#cc0000")); labelTelefonoVacio.setForeground(Color.WHITE);
		this.add(labelTelefonoVacio, c); labelTelefonoVacio.setVisible(false);	//Empieza invisible
		
		labelTelefonoFormatoInvalido = new JLabel(" Formato inválido ");	labelTelefonoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 6; 
		labelTelefonoFormatoInvalido.setOpaque(true);	labelTelefonoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelTelefonoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelTelefonoFormatoInvalido, c); labelTelefonoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		telefono = new JTextField();	telefono.setFont(fuenteLabelCampo);	telefono.setBorder(bordeCampo);
		telefono.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelTelefonoVacio.setVisible(false);
				  labelTelefonoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelTelefonoVacio.setVisible(false);
				  labelTelefonoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelTelefonoVacio.setVisible(false);
				  labelTelefonoFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese número de teléfono", telefono); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 7;	telefono.setMinimumSize(dimensionCampo);	telefono.setPreferredSize(dimensionCampo);	this.add(telefono, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Ocupación*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 6;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelOcupacionVacio = new JLabel(" Campo incompleto ");	labelOcupacionVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 6; 
		labelOcupacionVacio.setOpaque(true);	labelOcupacionVacio.setBackground(Color.decode("#cc0000")); labelOcupacionVacio.setForeground(Color.WHITE);
		this.add(labelOcupacionVacio, c); labelOcupacionVacio.setVisible(false);	//Empieza invisible
		
		labelOcupacionFormatoInvalido = new JLabel(" Formato inválido ");	labelOcupacionFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 6; 
		labelOcupacionFormatoInvalido.setOpaque(true);	labelOcupacionFormatoInvalido.setBackground(Color.decode("#cc0000")); labelOcupacionFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelOcupacionFormatoInvalido, c); labelOcupacionFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		ocupacion = new JTextField();	ocupacion.setFont(fuenteLabelCampo);	ocupacion.setBorder(bordeCampo);
		ocupacion.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelOcupacionVacio.setVisible(false);
				  labelOcupacionFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelOcupacionVacio.setVisible(false);
				  labelOcupacionFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelOcupacionVacio.setVisible(false);
				  labelOcupacionFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese ocupación", ocupacion); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 7;	ocupacion.setMinimumSize(dimensionCampo);	ocupacion.setPreferredSize(dimensionCampo);	this.add(ocupacion, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Dirección*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 8;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelDireccionVacio = new JLabel(" Campo incompleto ");	labelDireccionVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 8; 
		labelDireccionVacio.setOpaque(true);	labelDireccionVacio.setBackground(Color.decode("#cc0000")); labelDireccionVacio.setForeground(Color.WHITE);
		this.add(labelDireccionVacio, c); labelDireccionVacio.setVisible(false);	//Empieza invisible
		
		labelDireccionFormatoInvalido = new JLabel(" Formato inválido ");	labelDireccionFormatoInvalido.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 8; 
		labelDireccionFormatoInvalido.setOpaque(true);	labelDireccionFormatoInvalido.setBackground(Color.decode("#cc0000")); labelDireccionFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelDireccionFormatoInvalido, c); labelDireccionFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		direccion = new JTextField();	direccion.setFont(fuenteLabelCampo);	direccion.setBorder(bordeCampo);
		direccion.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelDireccionVacio.setVisible(false);
				  labelDireccionFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelDireccionVacio.setVisible(false);
				  labelDireccionFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelDireccionVacio.setVisible(false);
				  labelDireccionFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el domicilio (calle y número)", direccion); fondoJTextField.setForeground(Color.GRAY);
//		direccion.setPreferredSize(new Dimension(400,20));
//		direccion.setMinimumSize(new Dimension(400,20));
		c.gridx = 0; c.gridy = 9;	direccion.setMinimumSize(dimensionCampo);	direccion.setPreferredSize(dimensionCampo);	this.add(direccion, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Departamento");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 8;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST; c.insets = insetLabelDepartamentoInvalido;
			
//		labelApellidoVacio = new JLabel(" Campo incompleto ");	labelApellidoVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 0; 
//		labelApellidoVacio.setOpaque(true);	labelApellidoVacio.setBackground(Color.decode("#cc0000")); labelApellidoVacio.setForeground(Color.WHITE);
//		this.add(labelApellidoVacio, c); labelApellidoVacio.setVisible(false);	//Empieza invisible
			
		labelDepartamentoFormatoInvalido = new JLabel(" Formato inv ");	labelDepartamentoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 2; c.gridy = 8; 
		labelDepartamentoFormatoInvalido.setOpaque(true);	labelDepartamentoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelDepartamentoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelDepartamentoFormatoInvalido, c); labelDepartamentoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		departamento = new JTextField();	departamento.setFont(fuenteLabelCampo);	departamento.setBorder(bordeCampo);
		departamento.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelDepartamentoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelDepartamentoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelDepartamentoFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el dpto.", departamento); fondoJTextField.setForeground(Color.GRAY);
//		departamento.setPreferredSize(new Dimension(180,20));
//		departamento.setMinimumSize(new Dimension(180,20));
		c.gridx = 2; c.gridy = 9;	departamento.setMinimumSize(dimensionCampo);	departamento.setPreferredSize(dimensionCampo);	this.add(departamento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleDer;
		
		label = new JLabel("Piso");	label.setFont(fuenteLabelCampo);	c.gridx = 3; c.gridy = 8;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
//		labelApellidoVacio = new JLabel(" Campo incompleto ");	labelApellidoVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 0; 
//		labelApellidoVacio.setOpaque(true);	labelApellidoVacio.setBackground(Color.decode("#cc0000")); labelApellidoVacio.setForeground(Color.WHITE);
//		this.add(labelApellidoVacio, c); labelApellidoVacio.setVisible(false);	//Empieza invisible
		
		labelPisoFormatoInvalido = new JLabel(" Formato inválido ");	labelPisoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 8; 
		labelPisoFormatoInvalido.setOpaque(true);	labelPisoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelPisoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelPisoFormatoInvalido, c); labelPisoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		piso = new JTextField();	piso.setFont(fuenteLabelCampo);	piso.setBorder(bordeCampo);
		piso.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelPisoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelPisoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelPisoFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el piso", piso); fondoJTextField.setForeground(Color.GRAY);
//		piso.setPreferredSize(new Dimension(180,20));
//		piso.setMinimumSize(new Dimension(180,20));
		c.gridx = 3; c.gridy = 9;	piso.setMinimumSize(dimensionCampo);	piso.setPreferredSize(dimensionCampo);	this.add(piso, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("País*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 10;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		List<PaisDTO> paises = gestorPP.buscarPaises();//.toArray((new PaisDTO[paises.size()]);
		pais = new JComboBox<PaisDTO>(paises.toArray(new PaisDTO[paises.size()]));	
		pais.setFont(fuenteLabelCampo);	pais.setBackground(Color.white);	
		c.gridx = 0; c.gridy = 11;	pais.setMinimumSize(dimensionCampo);	pais.setPreferredSize(dimensionCampo);
//		pais.addItem("--Seleccione");	
//		estaciones.toArray(new Estacion[estaciones.size()])
		pais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		this.add(pais, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;

		label = new JLabel("Provincia*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 10;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		provincia = new JComboBox<String>();	provincia.setFont(fuenteLabelCampo);	provincia.setBackground(Color.white);	
		c.gridx = 2; c.gridy = 11;	provincia.setMinimumSize(dimensionCampo);	provincia.setPreferredSize(dimensionCampo);
		provincia.addItem("--Seleccione");	
		this.add(provincia, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Código postal*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 12;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST; c.insets = insetLabelVacioCP;
			
		labelCodigoPostalVacio = new JLabel(" Campo inc ");	labelCodigoPostalVacio.setFont(fuenteLabelError); c.gridx = 0; c.gridy = 12; 
		labelCodigoPostalVacio.setOpaque(true);	labelCodigoPostalVacio.setBackground(Color.decode("#cc0000")); labelCodigoPostalVacio.setForeground(Color.WHITE);
		this.add(labelCodigoPostalVacio, c); labelCodigoPostalVacio.setVisible(false);	//Empieza invisible
		
		labelCodigoPostalFormatoInvalido = new JLabel(" Formato inv ");	labelCodigoPostalFormatoInvalido.setFont(fuenteLabelError); c.gridx = 0; c.gridy = 12; 
		labelCodigoPostalFormatoInvalido.setOpaque(true);	labelCodigoPostalFormatoInvalido.setBackground(Color.decode("#cc0000")); labelCodigoPostalFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelCodigoPostalFormatoInvalido, c); labelCodigoPostalFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		codigoPostal = new JTextField();	codigoPostal.setFont(fuenteLabelCampo);	codigoPostal.setBorder(bordeCampo);
		codigoPostal.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelCodigoPostalVacio.setVisible(false);
				  labelCodigoPostalFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelCodigoPostalVacio.setVisible(false);
				  labelCodigoPostalFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelCodigoPostalVacio.setVisible(false);
				  labelCodigoPostalFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el CP", codigoPostal); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 13;	codigoPostal.setMinimumSize(dimensionCampo);	codigoPostal.setPreferredSize(dimensionCampo);	this.add(codigoPostal, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleDer; c.gridwidth = 1;
		
		label = new JLabel("Localidad*");	label.setFont(fuenteLabelCampo);	c.gridx = 1; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		localidad = new JComboBox<String>();	localidad.setFont(fuenteLabelCampo);	localidad.setBackground(Color.white);	
		//localidad.setBorder(bordeCampo);
		c.gridx = 1; c.gridy = 13;	localidad.setMinimumSize(dimensionCampo);	localidad.setPreferredSize(dimensionCampo);
		localidad.addItem("--Seleccione");	
		this.add(localidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nacionalidad*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nacionalidad = new JComboBox<String>();	
		nacionalidad.setFont(fuenteLabelCampo);	nacionalidad.setBackground(Color.white);	
		//nacionalidad.setBorder(bordeCampo);
		c.gridx = 2; c.gridy = 13;	nacionalidad.setMinimumSize(dimensionCampo);	nacionalidad.setPreferredSize(dimensionCampo);
		nacionalidad.addItem("--Seleccione");	
		this.add(nacionalidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("CUIT");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 14;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelCuitVacio = new JLabel(" Campo incompleto ");	labelCuitVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 14; 
		labelCuitVacio.setOpaque(true);	labelCuitVacio.setBackground(Color.decode("#cc0000")); labelCuitVacio.setForeground(Color.WHITE);
		this.add(labelCuitVacio, c); labelCuitVacio.setVisible(false);	//Empieza invisible
		
		labelCuitFormatoInvalido = new JLabel(" Formato inválido ");	labelCuitFormatoInvalido.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 14; 
		labelCuitFormatoInvalido.setOpaque(true);	labelCuitFormatoInvalido.setBackground(Color.decode("#cc0000")); labelCuitFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelCuitFormatoInvalido, c); labelCuitFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;

		cuit = new JTextField();	cuit.setFont(fuenteLabelCampo);	cuit.setBorder(bordeCampo);
		cuit.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelCuitVacio.setVisible(false);
				  labelCuitFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelCuitVacio.setVisible(false);
				  labelCuitFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelCuitVacio.setVisible(false);
				  labelCuitFormatoInvalido.setVisible(false);
//				  cuit.setText(cuit.getText()+"-");
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese los 11 dígitos del número de CUIT", cuit); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 15;	cuit.setMinimumSize(dimensionCampo);	cuit.setPreferredSize(dimensionCampo);	this.add(cuit, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Posición frente al IVA*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 14;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		posicionIVA = new JComboBox();	posicionIVA.setFont(fuenteLabelCampo);	posicionIVA.setBackground(Color.white);	
		
		c.gridx = 2; c.gridy = 15;	posicionIVA.setMinimumSize(dimensionCampo);	posicionIVA.setPreferredSize(dimensionCampo);	
		this.cargarComboBoxDesdeEnum(posicionIVA, PosicionFrenteIva.values());	
		this.add(posicionIVA, c);
		
			c.fill = GridBagConstraints.NONE; c.weighty = pesoYLabel; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
		
		label = new JLabel("*campo obligatorio");	label.setFont(fuenteLabelFinal);	c.gridx = 2; c.gridy = 16;	this.add(label, c);
	}

	private void cargarComboBoxDesdeEnum(JComboBox comboBox, Object[] values) {
		
		for(Object o : values){
			
			comboBox.addItem(String.valueOf(o).replaceAll("_", " ")); //Para que no aparezcan los guiones bajos
		}
}

	public boolean inputEsNoVacio() {
		
		boolean resultado = true;
		
		if(apellido.getText().isEmpty()) {
			resultado = false;
			labelApellidoVacio.setVisible(true);
		}
		if(nombre.getText().isEmpty()) {
			resultado = false;
			labelNombreVacio.setVisible(true);
		}
		if(numeroDocumento.getText().isEmpty()) {
			resultado = false;
			labelNumeroDocumentoVacio.setVisible(true);
		}
		if(email.getText().isEmpty()) {
			resultado = false;
			labelEmailVacio.setVisible(true);
		}
		if(telefono.getText().isEmpty()) {
			resultado = false;
			labelTelefonoVacio.setVisible(true);
		}
		if(ocupacion.getText().isEmpty()) {
			resultado = false;
			labelOcupacionVacio.setVisible(true);
		}
		if(direccion.getText().isEmpty()) {
			resultado = false;
			labelDireccionVacio.setVisible(true);
		}
		if(codigoPostal.getText().isEmpty()) {
			resultado = false;
			labelCodigoPostalVacio.setVisible(true);
		}
		if(cuit.getText().isEmpty()) {
			resultado = false;
			labelCuitVacio.setVisible(true);
		}
		
	
	return resultado;
	}
	
	public boolean inputTieneFormatoValido() {

		boolean resultado = true;
		
		if(!esValidoApellidoONombre(apellido)) {
			resultado = false;
			labelApellidoFormatoInvalido.setVisible(true);
		}
		if(!esValidoApellidoONombre(nombre)) {
			resultado = false;
			labelNombreFormatoInvalido.setVisible(true);
		}
		if(!esValidoNumeroDeDocumento(numeroDocumento)) {
			resultado = false;
			labelNumeroDocumentoFormatoInvalido.setVisible(true);
		}
		if(!esValidoEmail(email)) {
			resultado = false;
			labelEmailFormatoInvalido.setVisible(true);
		}
		if(!esValidoTelefono(telefono)) {
			resultado = false;
			labelTelefonoFormatoInvalido.setVisible(true);
		}
		if(!esValidoOcupacion(ocupacion)) {
			resultado = false;
			labelOcupacionFormatoInvalido.setVisible(true);
		}
		if(!esValidoDireccion(direccion)) {
			resultado = false;
			labelDireccionFormatoInvalido.setVisible(true);
		}
		if(!esValidoDepartamento(departamento)) {
			resultado = false;
			labelDepartamentoFormatoInvalido.setVisible(true);
		}
		if(!esValidoPiso(piso)) {
			resultado = false;
			labelPisoFormatoInvalido.setVisible(true);
		}
		if(!esValidoCodigoPostal(codigoPostal)) {
			resultado = false;
			labelCodigoPostalFormatoInvalido.setVisible(true);
		}
		if(!esValidoCuit(cuit)) {
			resultado = false;
			labelCuitFormatoInvalido.setVisible(true);
		}
		
	
	return resultado;
	}

	private boolean esValidoCuit(JTextField cuit2) {	//Para todos: Si es válido devuelve TRUE / Si NO es válido devuelve FALSE
		
		if(cuit2.getText().length() > 13) {	//TODO: Validar
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoCodigoPostal(JTextField codigoPostal2) {
		
		if(codigoPostal2.getText().length() > 10) {	//TODO: Habria que validar que sean todos digitos?
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoPiso(JTextField piso2) {
		
		if(piso2.getText().length() > 3) {	
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoDepartamento(JTextField departamento2) {
		
		if(departamento2.getText().length() > 5) {	
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoDireccion(JTextField direccion2) {
		
		if(direccion2.getText().length() > 60) {	
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoOcupacion(JTextField ocupacion2) {
		
		if(ocupacion2.getText().length() > 40) {	
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoTelefono(JTextField telefono2) {
		
		if(telefono2.getText().length() > 15) {	//TODO: Validar otras cosas
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoEmail(JTextField email2) {
		
		if(email2.getText().length() > 80) {	//TODO: Dominio (@)
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoNumeroDeDocumento(JTextField numeroDocumento2) {

		if(numeroDocumento2.getText().length() > 15) {	//TODO: Habría que validar el tema de que sean numeros? Y en un pasaporte?
			
			return false;
		}
		
		return true;
	}

	private boolean esValidoApellidoONombre(JTextField apellidoONombre) {

		if(apellidoONombre.getText().length() > 40 || contieneUnNumero(apellidoONombre.getText())) {
			
			return false;
		}
		
		return true;
	}
	
	public boolean contieneUnNumero(String s) {
	    boolean resultado = false;

	        for (char c : s.toCharArray()) {
	            if (resultado = Character.isDigit(c)) {
	                break;
	            }
	        }

	    return resultado;
	}
	
	public PasajeroDTO crearDTOS() {
		
		/*TODO: 
		 * Ver el tema del id localidad
		 * Aca cpz se deberian pasar todos los datos, porque sino dsp no se cuando los pasas
		 */
		
		DireccionDTO direccionDto = new DireccionDTO(null,direccion.getText(), departamento.getText(), piso.getText(), null, Integer.valueOf(codigoPostal.getText()));
		
		PasajeroDTO pasajeroDto = new PasajeroDTO(null, apellido.getText(), nombre.getText(), TipoDocumento.valueOf(tipoDocumento.getSelectedItem().toString()), numeroDocumento.getText(), direccionDto, null); 
		
		return pasajeroDto;
	}
}
//
//	private void inputNoEsVacia() throws InputVacioException{
//		
//		if(apellido.getText().isEmpty() || nombre.getText().isEmpty()) { //Faltan los otros campos
//			throw new InputVacioException();
//		}
//	} 
//
//	public JPanel getPanel() {
//		return this;
//	}
//}


	
//	
//	public boolean validarHora(JTextField field) { //Retorna false si no es integer o si no se encuentra en el rango [0, 23]
//		
//		try {
//			Integer hora = Integer.parseInt(field.getText());
//			
//			if(hora > -1 && hora < 24) {
//				
//				return true; 
//			}
//			else {
//				
//				return false;
//			}
//			
//		} catch(NumberFormatException e) {
//			
//			return false;
//		}
//	}
//	
//	public boolean validarMinuto(JTextField field) { //Retorna false si no es integer o si no se encuentra en el rango [0, 59]
//		
//		try {
//			
//			Integer minuto = Integer.parseInt(field.getText());
//			
//			if(minuto > -1 && minuto < 60) {
//				
//				return true; 
//			}
//			else {
//				
//				return false;
//			}
//			
//		} catch(NumberFormatException e) {
//			
//			return false;
//		}
//	}
//	
//	public boolean validarNombre(JTextField field) { //Retorna false si la longitud del string es mayor a 30
//		
//		if(field.getText().length() > 30)
//			return false;
//		
//		return true;
//	}
//	
//	public boolean horasValidas(JTextField horaApertura, JTextField minutoApertura, JTextField horaCierre, JTextField minutoCierre) {
//		
//		Integer horaA = Integer.parseInt(horaApertura.getText());
//		Integer horaC = Integer.parseInt(horaCierre.getText());
//		Integer minutoA = Integer.parseInt(minutoApertura.getText());
//		Integer minutoC = Integer.parseInt(minutoCierre.getText());
//		
//		if(horaC > horaA) {	//Si la hora de cierre es mayor, los minutos no importan (horas validas)
//			return true;
//		}
//		else if(horaC == horaA){	//Si las horas son iguales, debo comparar minutos
//			
//			if(minutoC > minutoA) { //Minuto de cierre mayor (horas validas)
//				return true;
//			}
//			else {
//				return false;	//Minuto de apertura mayor (horas invalidas)
//			}
//		}
//		else {	//Si la hora de apertura es mayor a la de cierre, los minutos no importan (horas invalidas)
//			return false;
//		}
//	}
//
//}


