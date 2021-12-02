package main.java.interfaces.CU11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import main.java.dtos.DireccionDTO;
import main.java.dtos.LocalidadDTO;
import main.java.dtos.PaisDTO;
import main.java.dtos.PasajeroDTO;
import main.java.dtos.ProvinciaDTO;
import main.java.enums.PosicionFrenteIva;
import main.java.enums.TipoDocumento;
import main.java.gestores.GestorPaisProvincia;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelAltaPasajeroDatos extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private GestorPaisProvincia gestorPP;
	
	private List<PaisDTO> paises;
	private List<ProvinciaDTO> provincias;
	private List<LocalidadDTO> localidades;
	
	private DefaultComboBoxModel<ProvinciaDTO> provinciaModel = new DefaultComboBoxModel<ProvinciaDTO>();	//Pais y Nacionalidad no necesitan
	private DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
	
	private JComboBox<TipoDocumento> tipoDocumento;
	private JComboBox<PaisDTO> pais;
	private JComboBox<ProvinciaDTO> provincia;
	private JComboBox<LocalidadDTO> localidad;
	private JComboBox<PaisDTO> nacionalidad;	
	private JComboBox<PosicionFrenteIva> posicionIVA;
	
	private JLabel label;
	
	private JLabel labelApellidoVacio;				//Muestran mensaje "Campo incompleto"
	private JLabel labelNombreVacio;
	private JLabel labelNumeroDocumentoVacio;
	private JLabel labelFechaNacimientoVacio;
	private JLabel labelTelefonoVacio;
	private JLabel labelOcupacionVacio;
	private JLabel labelDireccionVacio;
	private JLabel labelPaisVacio;
	private JLabel labelProvinciaVacio;
	private JLabel labelCodigoPostalVacio;
	private JLabel labelLocalidadVacio;
	private JLabel labelNacionalidadVacio;
	private JLabel labelCuitVacio;
	
	private boolean seleccionoResponsableInscripto = false;	//Para que deba, o no, ingresar el cuit
	
	private JLabel labelApellidoFormatoInvalido;	//Muestran mensaje "Formato inválido"
	private JLabel labelNombreFormatoInvalido;
	private JLabel labelNumeroDocumentoFormatoInvalido = new JLabel(" Formato inválido ");	
	private JLabel labelFechaNacimientoFormatoInvalido;
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
	private Insets insetLabelMasChico = new Insets(0,60,0,0);
	private Insets insetLabelDepartamentoInvalido = new Insets(0,60,0,0);
	private Insets insetLabelErrorPiso = new Insets(0,120,0,0);
	
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
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private TextPrompt fondoJTextField;
	
	public PanelAltaPasajeroDatos(FrameAltaPasajero frame) {
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
		
		apellido = new JTextField(); apellido.setFont(fuenteLabelCampo);	apellido.setBorder(bordeCampo);	apellido.setDocument(new JTextFieldLimitado(40));
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
		
		nombre = new JTextField();	nombre.setFont(fuenteLabelCampo);	nombre.setBorder(bordeCampo);	nombre.setDocument(new JTextFieldLimitado(40));
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
		
		tipoDocumento = new JComboBox<TipoDocumento>();	tipoDocumento.setFont(fuenteLabelCampo);	
		tipoDocumento.setBackground(Color.white);	
		tipoDocumento.addItemListener(event -> {
			
			labelNumeroDocumentoFormatoInvalido.setVisible(false);
        });
		this.cargarComboBoxDesdeEnum(tipoDocumento, TipoDocumento.values());
		
		c.gridx = 0; c.gridy = 3;	tipoDocumento.setMinimumSize(dimensionCampo);	tipoDocumento.setPreferredSize(dimensionCampo);
		this.add(tipoDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Número de documento*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 2;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelNumeroDocumentoVacio = new JLabel(" Campo incompleto ");	labelNumeroDocumentoVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 2; 
		labelNumeroDocumentoVacio.setOpaque(true);	labelNumeroDocumentoVacio.setBackground(Color.decode("#cc0000")); labelNumeroDocumentoVacio.setForeground(Color.WHITE);
		this.add(labelNumeroDocumentoVacio, c); labelNumeroDocumentoVacio.setVisible(false);	//Empieza invisible
		
		labelNumeroDocumentoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 2; 
		labelNumeroDocumentoFormatoInvalido.setOpaque(true);	labelNumeroDocumentoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelNumeroDocumentoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelNumeroDocumentoFormatoInvalido, c); labelNumeroDocumentoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		numeroDocumento = new JTextField();	numeroDocumento.setFont(fuenteLabelCampo);	numeroDocumento.setBorder(bordeCampo);	numeroDocumento.setDocument(new JTextFieldLimitado(20));
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
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelFechaNacimientoVacio = new JLabel(" Campo incompleto ");	labelFechaNacimientoVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 4; 
		labelFechaNacimientoVacio.setOpaque(true);	labelFechaNacimientoVacio.setBackground(Color.decode("#cc0000")); labelFechaNacimientoVacio.setForeground(Color.WHITE);
		this.add(labelFechaNacimientoVacio, c); labelFechaNacimientoVacio.setVisible(false);	//Empieza invisible
		
		labelFechaNacimientoFormatoInvalido = new JLabel(" Formato inválido ");	labelFechaNacimientoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 4; 
		labelFechaNacimientoFormatoInvalido.setOpaque(true);	labelFechaNacimientoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelFechaNacimientoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelFechaNacimientoFormatoInvalido, c); labelFechaNacimientoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;

		//fechaNacimiento = new JTextField();
		try {
			MaskFormatter mascaraFechaNacimiento = new MaskFormatter("##'/##'/####");
			fechaNacimiento = new JFormattedTextField(mascaraFechaNacimiento);
	    	
	    }catch (ParseException e) {
	    	e.printStackTrace();
	    }
		fechaNacimiento.setFont(fuenteLabelCampo);	fechaNacimiento.setBorder(bordeCampo);
		fechaNacimiento.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelFechaNacimientoVacio.setVisible(false);
				  labelFechaNacimientoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelFechaNacimientoVacio.setVisible(false);
				  labelFechaNacimientoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelFechaNacimientoVacio.setVisible(false);
				  labelFechaNacimientoFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("dd/mm/aaaa", fechaNacimiento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 5;	fechaNacimiento.setMinimumSize(dimensionCampo);	fechaNacimiento.setPreferredSize(dimensionCampo);	
		this.add(fechaNacimiento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Email");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
		
		labelEmailFormatoInvalido = new JLabel(" Formato inválido ");	labelEmailFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 4; 
		labelEmailFormatoInvalido.setOpaque(true);	labelEmailFormatoInvalido.setBackground(Color.decode("#cc0000")); labelEmailFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelEmailFormatoInvalido, c); labelEmailFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		email = new JTextField();	email.setFont(fuenteLabelCampo);	email.setBorder(bordeCampo);	email.setDocument(new JTextFieldLimitado(80));
		email.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelEmailFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelEmailFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
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
		
		telefono = new JTextField();	telefono.setFont(fuenteLabelCampo);	telefono.setBorder(bordeCampo);	telefono.setDocument(new JTextFieldLimitado(15)); 
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
		
		ocupacion = new JTextField();	ocupacion.setFont(fuenteLabelCampo);	ocupacion.setBorder(bordeCampo);	ocupacion.setDocument(new JTextFieldLimitado(40)); 
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
		
		direccion = new JTextField();	direccion.setFont(fuenteLabelCampo);	direccion.setBorder(bordeCampo);	direccion.setDocument(new JTextFieldLimitado(60)); 
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
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetCampoDobleIzq;	c.gridwidth = 1;
		
		label = new JLabel("Piso");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 8;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelErrorPiso;
		
		labelPisoFormatoInvalido = new JLabel(" Formato inválido ");	labelPisoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 2; c.gridy = 8; 
		labelPisoFormatoInvalido.setOpaque(true);	labelPisoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelPisoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelPisoFormatoInvalido, c); labelPisoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		piso = new JTextField();	piso.setFont(fuenteLabelCampo);	piso.setBorder(bordeCampo);	piso.setDocument(new JTextFieldLimitado(3));
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
		c.gridx = 2; c.gridy = 9;	piso.setMinimumSize(dimensionCampo);	piso.setPreferredSize(dimensionCampo);	this.add(piso, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetCampoDobleDer; c.gridwidth = 1;
		
		label = new JLabel("Departamento");	label.setFont(fuenteLabelCampo);	c.gridx = 3; c.gridy = 8;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelDepartamentoInvalido;
			
		labelDepartamentoFormatoInvalido = new JLabel(" Formato inv ");	labelDepartamentoFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 8; 
		labelDepartamentoFormatoInvalido.setOpaque(true);	labelDepartamentoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelDepartamentoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelDepartamentoFormatoInvalido, c); labelDepartamentoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		departamento = new JTextField();	departamento.setFont(fuenteLabelCampo);	departamento.setBorder(bordeCampo);	departamento.setDocument(new JTextFieldLimitado(5)); 
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
		c.gridx = 3; c.gridy = 9;	departamento.setMinimumSize(dimensionCampo);	departamento.setPreferredSize(dimensionCampo);	this.add(departamento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("País*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 10;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
		
		labelPaisVacio = new JLabel(" Campo incompleto ");	labelPaisVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 10; 
		labelPaisVacio.setOpaque(true);	labelPaisVacio.setBackground(Color.decode("#cc0000")); labelPaisVacio.setForeground(Color.WHITE);
		this.add(labelPaisVacio, c); labelPaisVacio.setVisible(false);	//Empieza invisible

			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;

		paises = gestorPP.buscarPaises();
		pais = new JComboBox<PaisDTO>(paises.toArray(new PaisDTO[paises.size()]));
		pais.insertItemAt(null, 0); pais.setSelectedIndex(0); //Para que el primero estÃ© vacÃ­o
		pais.setFont(fuenteLabelCampo);	pais.setBackground(Color.white);	
		c.gridx = 0; c.gridy = 11;	pais.setMinimumSize(dimensionCampo);	pais.setPreferredSize(dimensionCampo);
//		pais.addItem("--Seleccione");	
		pais.addItemListener(event -> {
			
			labelPaisVacio.setVisible(false);
			provinciaModel.removeAllElements();	//Remueve todos los elementos de la lista
			
			if(pais.getSelectedItem() != null) {	//No es tan necesario, pero puede llegar a arreglar errores
				 provincias = gestorPP.buscarProviciasPorPais(((PaisDTO) pais.getSelectedItem()).getId());
				 provinciaModel.addAll(provincias);
			}
   
        });
		this.add(pais, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;

		label = new JLabel("Provincia*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 10;	this.add(label, c);
		
				c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
			labelProvinciaVacio = new JLabel(" Campo incompleto ");	labelProvinciaVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 10; 
			labelProvinciaVacio.setOpaque(true);	labelProvinciaVacio.setBackground(Color.decode("#cc0000")); labelProvinciaVacio.setForeground(Color.WHITE);
			this.add(labelProvinciaVacio, c); labelProvinciaVacio.setVisible(false);	//Empieza invisible
	
				c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
			
		provincia = new JComboBox<ProvinciaDTO>(provinciaModel);	//provincias.toArray(new ProvinciaDTO[provincias.size()])
		provincia.setFont(fuenteLabelCampo);	provincia.setBackground(Color.white);		
		provincia.addItemListener(event -> {
			
			labelProvinciaVacio.setVisible(false);
			localidadModel.removeAllElements();
			
			if(provincia.getSelectedItem() != null) {	//Cuando se cambia de Pais la provincia queda nula y sin provincia el metodo getSelectedItem() da null
            localidades = gestorPP.buscarLocalidadesPorProvincia(((ProvinciaDTO) provincia.getSelectedItem()).getId());
            localidadModel.addAll(localidades);
			}

        });
		c.gridx = 2; c.gridy = 11;	provincia.setMinimumSize(dimensionCampo);	provincia.setPreferredSize(dimensionCampo);
		//provincia.addItem("--Seleccione");	
		this.add(provincia, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Código postal*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 12;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST; c.insets = insetLabelMasChico;
			
		labelCodigoPostalVacio = new JLabel(" Campo inc ");	labelCodigoPostalVacio.setFont(fuenteLabelError); c.gridx = 0; c.gridy = 12; 
		labelCodigoPostalVacio.setOpaque(true);	labelCodigoPostalVacio.setBackground(Color.decode("#cc0000")); labelCodigoPostalVacio.setForeground(Color.WHITE);
		this.add(labelCodigoPostalVacio, c); labelCodigoPostalVacio.setVisible(false);	//Empieza invisible
		
		labelCodigoPostalFormatoInvalido = new JLabel(" Formato inv ");	labelCodigoPostalFormatoInvalido.setFont(fuenteLabelError); c.gridx = 0; c.gridy = 12; 
		labelCodigoPostalFormatoInvalido.setOpaque(true);	labelCodigoPostalFormatoInvalido.setBackground(Color.decode("#cc0000")); labelCodigoPostalFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelCodigoPostalFormatoInvalido, c); labelCodigoPostalFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		codigoPostal = new JTextField();	codigoPostal.setFont(fuenteLabelCampo);	codigoPostal.setBorder(bordeCampo);	codigoPostal.setDocument(new JTextFieldLimitado(10));
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
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelMasChico;
			
		labelLocalidadVacio = new JLabel(" Campo inc ");	labelLocalidadVacio.setFont(fuenteLabelError); c.gridx = 1; c.gridy = 12; 
		labelLocalidadVacio.setOpaque(true);	labelLocalidadVacio.setBackground(Color.decode("#cc0000")); labelLocalidadVacio.setForeground(Color.WHITE);
		this.add(labelLocalidadVacio, c); labelLocalidadVacio.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		localidad = new JComboBox<LocalidadDTO>(localidadModel);
		localidad.setFont(fuenteLabelCampo);	localidad.setBackground(Color.white);	
		localidad.addItemListener(event -> {
			
			labelLocalidadVacio.setVisible(false);

        });
		c.gridx = 1; c.gridy = 13;	localidad.setMinimumSize(dimensionCampo);	localidad.setPreferredSize(dimensionCampo);	
		this.add(localidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nacionalidad*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 12;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelNacionalidadVacio = new JLabel(" Campo incompleto ");	labelNacionalidadVacio.setFont(fuenteLabelError); c.gridx = 3; c.gridy = 12; 
		labelNacionalidadVacio.setOpaque(true);	labelNacionalidadVacio.setBackground(Color.decode("#cc0000")); labelNacionalidadVacio.setForeground(Color.WHITE);
		this.add(labelNacionalidadVacio, c); labelNacionalidadVacio.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nacionalidad = new JComboBox<PaisDTO>(paises.toArray(new PaisDTO[paises.size()]));	//Misma lista que Pais
		nacionalidad.setFont(fuenteLabelCampo);	nacionalidad.setBackground(Color.white);	
		nacionalidad.addItemListener(event -> {
			
			labelNacionalidadVacio.setVisible(false);

        });
		nacionalidad.insertItemAt(null, 0); nacionalidad.setSelectedIndex(0); //Para que el primero estÃ© vacÃ­o
		c.gridx = 2; c.gridy = 13;	nacionalidad.setMinimumSize(dimensionCampo);	nacionalidad.setPreferredSize(dimensionCampo);
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

		//cuit = new JTextField();	
		try {
			MaskFormatter mascaraCuit = new MaskFormatter("##'-########'-#");
			mascaraCuit.setValueContainsLiteralCharacters(false);
			cuit = new JFormattedTextField(mascaraCuit);
	    	
	    }catch (ParseException e) {
	    	e.printStackTrace();
	    }
		cuit.setFont(fuenteLabelCampo);	cuit.setBorder(bordeCampo);
		cuit.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelCuitFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelCuitFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelCuitFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese los 11 dígitos del número de CUIT", cuit); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 15;	cuit.setMinimumSize(dimensionCampo);	cuit.setPreferredSize(dimensionCampo);	this.add(cuit, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Posición frente al IVA*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 14;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		posicionIVA = new JComboBox<PosicionFrenteIva>();	
		posicionIVA.setFont(fuenteLabelCampo);	posicionIVA.setBackground(Color.white);		
		posicionIVA.addItemListener(event -> {
			if(posicionIVA.getSelectedItem().equals("RESPONSABLE INSCRIPTO")) {	//Cuando se cambia de Pais la provincia queda nula y sin provincia el metodo getSelectedItem() da null
	            seleccionoResponsableInscripto = true;
	            cuit.setEnabled(true);
			}
			else if(posicionIVA.getSelectedItem().equals("CONSUMIDOR FINAL")) {	
				seleccionoResponsableInscripto = false;
				labelCuitFormatoInvalido.setVisible(false);
				labelCuitVacio.setVisible(false);
	            cuit.setEnabled(false);
	            cuit.setText("");
			}

        });
		
		c.gridx = 2; c.gridy = 15;	posicionIVA.setMinimumSize(dimensionCampo);	posicionIVA.setPreferredSize(dimensionCampo);	
		this.cargarComboBoxDesdeEnum(posicionIVA, PosicionFrenteIva.values());	
		this.add(posicionIVA, c);
		
			c.fill = GridBagConstraints.NONE; c.weighty = pesoYLabel; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
		
		label = new JLabel("*campo obligatorio");	label.setFont(fuenteLabelFinal);	c.gridx = 2; c.gridy = 16;	this.add(label, c);
	}

	@SuppressWarnings("unchecked")
	private void cargarComboBoxDesdeEnum(@SuppressWarnings("rawtypes") JComboBox comboBox, Object[] values) {
		
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
		if(fechaNacimiento.getText().contains(" ")) {	//Por el formato que tiene
			resultado = false;
			labelFechaNacimientoVacio.setVisible(true);
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
		if(pais.getSelectedItem() == null) {			//Por ser lista desplegable
			resultado = false;
			labelPaisVacio.setVisible(true);
		}
		if(provincia.getSelectedItem() == null) {		//Por ser lista desplegable
			resultado = false;
			labelProvinciaVacio.setVisible(true);
		}
		if(codigoPostal.getText().isEmpty()) {
			resultado = false;
			labelCodigoPostalVacio.setVisible(true);
		}
		if(localidad.getSelectedItem() == null) {		//Por ser lista desplegable
			resultado = false;
			labelLocalidadVacio.setVisible(true);
		}
		if(nacionalidad.getSelectedItem() == null) {	//Por ser lista desplegable
			resultado = false;
			labelNacionalidadVacio.setVisible(true);
		}
		if(seleccionoResponsableInscripto) {
			if(cuit.getText().contains(" ")) {	//Por el formato que tiene
				resultado = false;
				labelCuitVacio.setVisible(true);
			}
		}
		
	
	return resultado;
	}
	
	public boolean inputTieneFormatoValido() {

		boolean resultado = true;
		
		if(contieneCaracteresEspeciales(apellido) || contieneUnNumero(apellido)) {
			resultado = false;
			labelApellidoFormatoInvalido.setVisible(true);
		}
		if(contieneCaracteresEspeciales(nombre) || contieneUnNumero(nombre)) {
			resultado = false;
			labelNombreFormatoInvalido.setVisible(true);
		}
		if(!esValidoNumeroDocumento(numeroDocumento)) {
			resultado = false;
			labelNumeroDocumentoFormatoInvalido.setVisible(true);
		}
		if(!esValidoFechaNacimiento(fechaNacimiento)) {
			resultado = false;
			labelFechaNacimientoFormatoInvalido.setVisible(true);
		}
		if(!esValidoEmail(email)) {
			resultado = false;
			labelEmailFormatoInvalido.setVisible(true);
		}
		if(!esTotalmenteNumero(telefono)) {
			resultado = false;
			labelTelefonoFormatoInvalido.setVisible(true);
		}
		if(contieneCaracteresEspeciales(ocupacion)) {
			resultado = false;
			labelOcupacionFormatoInvalido.setVisible(true);
		}
		if(contieneCaracteresEspeciales(direccion)) {
			resultado = false;
			labelDireccionFormatoInvalido.setVisible(true);
		}
		if(contieneCaracteresEspecialesYGuiones(departamento)) {
			resultado = false;
			labelDepartamentoFormatoInvalido.setVisible(true);
		}
		if(contieneCaracteresEspecialesYGuiones(piso)) {
			resultado = false;
			labelPisoFormatoInvalido.setVisible(true);
		}
		if(contieneCaracteresEspeciales(codigoPostal)) {
			resultado = false;
			labelCodigoPostalFormatoInvalido.setVisible(true);
		}
		if(seleccionoResponsableInscripto) {
			if(!esValidoCuit(cuit)) {
				resultado = false;
				labelCuitFormatoInvalido.setVisible(true);
			}
		}
		
	
	return resultado;
	}

	private boolean esValidoNumeroDocumento(JTextField numeroDocumento2) {
		
		if(tipoDocumento.getSelectedItem().equals("DNI")) {	//Si es DNI, se ve si es completamente numero y si tiene como máximo 8 caracteres
			return (esTotalmenteNumero(numeroDocumento2) && numeroDocumento2.getText().length() < 9);
		}
		else {
			
			return !contieneCaracteresEspeciales(numeroDocumento2);	//Si no es DNI, se valida si tiene o no caracteres especiales
		}
	}

	private boolean esValidoCuit(JTextField cuit2) {
			
		boolean resultado = false;
		
			try {
				if(cuit2.getText(3, 8).equals(numeroDocumento.getText())) {	//Si coincide con el número de documento
						resultado = true;
				}
				if(!tipoDocumento.getSelectedItem().equals("DNI")) {	//Si no es DNI, entonces no importa qué ponga, siempre será válido
						resultado = true;
				}
				
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		return resultado;
	}

	private boolean esValidoFechaNacimiento(JTextField fechaNacimiento2) {
		
		boolean resultado = true;
		
		String fecha = fechaNacimiento2.getText();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

		try {
			
			LocalDate localDate = LocalDate.parse(fecha, formatter);	//Si se puede convertir a LocalDate, es una fecha válida
			
			if(!fechaEnRango(localDate)) {
				
				resultado = false;
			}
			
		}
		catch(DateTimeParseException e) {
			
			resultado = false;
		}
		
		
		return resultado;
	}
	
	private boolean fechaEnRango(LocalDate fecha) {
		
		LocalDate fechaMaxima = LocalDate.of(1900,1,1);	//Fecha de nacimiento máxima
		LocalDate fechaMinima = LocalDate.now().minusDays(1);	//Fecha de nacimiento mínima (ayer)
		   return (fecha.isBefore(fechaMaxima) && fecha.isAfter(fechaMinima));
		}
	
	private boolean contieneCaracteresEspecialesYGuiones(JTextField field) {	//TRUE: La cadena tiene caracteres especiales / FALSE: La cadena NO tiene caracteres especiales

		Pattern p = Pattern.compile("[^a-z0-9-. ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(field.getText());
		boolean b = m.find();

		if (b) {
			return true;
		}

	return false;
}

	private boolean contieneCaracteresEspeciales(JTextField field) {	//TRUE: La cadena tiene caracteres especiales / FALSE: La cadena NO tiene caracteres especiales

			Pattern p = Pattern.compile("[^a-z0-9. ]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(field.getText());
			boolean b = m.find();

			if (b) {
				return true;
			}
	
		return false;
	}
	
	private boolean esTotalmenteNumero(JTextField field) {	//TRUE: La cadena es totalmente numérica / FALSE: La cadena tiene al menos 1 caracter que no es número

		    boolean resultado = true;
	
	        for (char c : field.getText().toCharArray()) {
	            if (!Character.isDigit(c)) {
	            	resultado = false;
	                break;
	            }
	        }
	
	    return resultado;
	}


	private boolean esValidoEmail(JTextField email2) {	//TRUE: Email es válido (cuenta con texto + @ + texto + .com) / FALSE: Email no es válido
		
		String regex = "^[A-Z0-9!#$%&’+/=?^_`{|}~-]+(?:.[A-Z0-9!#$%&’+/=?^_`{|}~-]+)@(?:[A-Z0-9](?:[a-z0-9-][a-z0-9])?.)+(?:[A-Z]{2}|COM|ORG|NET|GOV|MIL|BIZ|INFO|MOBI|NAME|AERO|JOBS|MUSEUM|AR)$"; //"^(.+)@(.+)$";	//"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
		 
		Pattern patron = Pattern.compile(regex);

		Matcher matcher = patron.matcher(email.getText());
		
		return matcher.matches();
	}
	
	public boolean contieneUnNumero(JTextField field) {	//TRUE: La cadena cuenta con al menos 1 número / FALSE: La cadena no tiene números
	    boolean resultado = false;

	        for (char c : field.getText().toCharArray()) {
	            if (Character.isDigit(c)) {
	            	resultado = true;
	                break;
	            }
	        }

	    return resultado;
	}
	
public PasajeroDTO crearDTOS() {
		
		DireccionDTO direccionDto = new DireccionDTO(null,direccion.getText(), departamento.getText(), piso.getText(), ((LocalidadDTO) localidad.getSelectedItem()).getId(), codigoPostal.getText());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento.getText(), formatter);
		
		PasajeroDTO pasajeroDto = new PasajeroDTO(null, apellido.getText(), nombre.getText(), TipoDocumento.valueOf(tipoDocumento.getSelectedItem().toString()), numeroDocumento.getText(), cuit.getText(), PosicionFrenteIva.valueOf(posicionIVA.getSelectedItem().toString().replace(" ", "_")), email.getText(), telefono.getText(), fechaNac, ocupacion.getText(), direccionDto, ((PaisDTO) nacionalidad.getSelectedItem()).getId()); 
		
		return pasajeroDto;
	}

public void centrarDocumento() {
	this.numeroDocumento.requestFocusInWindow();
}
}
	
	




