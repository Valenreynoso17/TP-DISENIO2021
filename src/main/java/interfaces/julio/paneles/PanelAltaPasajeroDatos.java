package main.java.interfaces.julio.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.enmus.TipoMensaje;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.TextPrompt.Show;
import main.java.interfaces.julio.frames.FrameAltaPasajero;
import main.java.interfaces.julio.otros.Mensaje;
import main.java.interfaces.julio.otros.RoundedBorder;

public class PanelAltaPasajeroDatos extends JPanel{
	
	private FrameAltaPasajero frameActual;
	
	private JComboBox<String> tipoDocumento;
	private JComboBox<String> pais;
	private JComboBox<String> provincia;
	private JComboBox<String> localidad;
	private JComboBox<String> nacionalidad;
	private JComboBox<String> posicionIVA;
	
	private JButton button;
	private JLabel label;
	
	private JTextField apellido;
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
	
	private Insets insetLabel = new Insets(0,70,0,0);
	private Insets insetLabelDobleIzq = new Insets(0,70,0,10);
	private Insets insetLabelDobleDer = new Insets(0,10,0,70);
	private Insets insetCampo = new Insets(0,70,0,70);
	private Insets insetCampoDobleIzq = new Insets(0,70,0,10);
	private Insets insetCampoDobleDer = new Insets(0,10,0,70);
	private Insets insetCampoUltimos = new Insets(0,70,20,70);
	
	private double pesoXLabel = 0.3;
	private double pesoYLabel = 0.5;
	private double pesoXCampo = 0.3;
	private double pesoYCampo = 0.1;
	
	private double pesoXLabelDoble = 0.3;
	private double pesoXCampoDoble = 0.3;
	
	private Dimension dimensionCampo = new Dimension(180, 30);
	
	private Font fuenteLabelFinal =new Font("SourceSansPro", Font.ITALIC, 13);	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("Iniciar Sesión", Font.PLAIN, 18);	
	
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
		
		this.setBackground(Color.WHITE);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Dar de alta pasajero", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Apellido*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		apellido = new JTextField(10); apellido.setFont(fuenteLabelCampo);	apellido.setBorder(bordeCampo);	
		fondoJTextField = new TextPrompt("Ingrese un apellido",apellido); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 1;	apellido.setMinimumSize(dimensionCampo);	apellido.setPreferredSize(dimensionCampo);	this.add(apellido, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nombre*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nombre = new JTextField();	nombre.setFont(fuenteLabelCampo);	nombre.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese un nombre", nombre); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 1;	nombre.setMinimumSize(dimensionCampo);	nombre.setPreferredSize(dimensionCampo);	this.add(nombre, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Tipo de documento*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 2;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		tipoDocumento = new JComboBox<String>();	tipoDocumento.setFont(fuenteLabelCampo);	tipoDocumento.setBackground(Color.white);	
		//tipoDocumento.setBorder(bordeCampo);	
		c.gridx = 0; c.gridy = 3;	tipoDocumento.setMinimumSize(dimensionCampo);	tipoDocumento.setPreferredSize(dimensionCampo);
		tipoDocumento.addItem("--Seleccione");		this.add(tipoDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Número de documento*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 2;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		numeroDocumento = new JTextField();	numeroDocumento.setFont(fuenteLabelCampo);	numeroDocumento.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese el número de documento", numeroDocumento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 3;	numeroDocumento.setMinimumSize(dimensionCampo);	numeroDocumento.setPreferredSize(dimensionCampo);	this.add(numeroDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Fecha de nacimiento*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 4;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		fechaNacimiento = new JTextField();	fechaNacimiento.setFont(fuenteLabelCampo);	fechaNacimiento.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("dd/mm/aaaa", fechaNacimiento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 5;	fechaNacimiento.setMinimumSize(dimensionCampo);	fechaNacimiento.setPreferredSize(dimensionCampo);	this.add(fechaNacimiento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Email*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		email = new JTextField();	email.setFont(fuenteLabelCampo);	email.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese el correo electrónico", email); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 5;	email.setMinimumSize(dimensionCampo);	email.setPreferredSize(dimensionCampo);	this.add(email, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Teléfono*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 6;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		telefono = new JTextField();	telefono.setFont(fuenteLabelCampo);	telefono.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese número de teléfono", telefono); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 7;	telefono.setMinimumSize(dimensionCampo);	telefono.setPreferredSize(dimensionCampo);	this.add(telefono, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Ocupación*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 6;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		ocupacion = new JTextField();	ocupacion.setFont(fuenteLabelCampo);	ocupacion.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese ocupación", ocupacion); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 7;	ocupacion.setMinimumSize(dimensionCampo);	ocupacion.setPreferredSize(dimensionCampo);	this.add(ocupacion, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Dirección*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 8;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		direccion = new JTextField();	direccion.setFont(fuenteLabelCampo);	direccion.setBorder(bordeCampo);	
		fondoJTextField = new TextPrompt("Ingrese el domicilio (calle y número)", direccion); fondoJTextField.setForeground(Color.GRAY);
//		direccion.setPreferredSize(new Dimension(400,20));
//		direccion.setMinimumSize(new Dimension(400,20));
		c.gridx = 0; c.gridy = 9;	direccion.setMinimumSize(dimensionCampo);	direccion.setPreferredSize(dimensionCampo);	this.add(direccion, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Departamento");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 8;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		departamento = new JTextField();	departamento.setFont(fuenteLabelCampo);	departamento.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese el dpto.", departamento); fondoJTextField.setForeground(Color.GRAY);
//		departamento.setPreferredSize(new Dimension(180,20));
//		departamento.setMinimumSize(new Dimension(180,20));
		c.gridx = 2; c.gridy = 9;	departamento.setMinimumSize(dimensionCampo);	departamento.setPreferredSize(dimensionCampo);	this.add(departamento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleDer;
		
		label = new JLabel("Piso");	label.setFont(fuenteLabelCampo);	c.gridx = 3; c.gridy = 8;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		piso = new JTextField();	piso.setFont(fuenteLabelCampo);	piso.setBorder(bordeCampo);	//piso.setMinimumSize(dimensionCampo);	piso.setPreferredSize(dimensionCampo);
		fondoJTextField = new TextPrompt("Ingrese el piso", piso); fondoJTextField.setForeground(Color.GRAY);
//		piso.setPreferredSize(new Dimension(180,20));
//		piso.setMinimumSize(new Dimension(180,20));
		c.gridx = 3; c.gridy = 9;	piso.setMinimumSize(dimensionCampo);	piso.setPreferredSize(dimensionCampo);	this.add(piso, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("País*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 10;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		pais = new JComboBox<String>();	pais.setFont(fuenteLabelCampo);	pais.setBackground(Color.white);	
		//pais.setBorder(bordeCampo);
		c.gridx = 0; c.gridy = 11;	pais.setMinimumSize(dimensionCampo);	pais.setPreferredSize(dimensionCampo);
		pais.addItem("--Seleccione");	this.add(pais, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;

		label = new JLabel("Provincia*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 10;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		provincia = new JComboBox<String>();	provincia.setFont(fuenteLabelCampo);	provincia.setBackground(Color.white);	
		//provincia.setBorder(bordeCampo);
		c.gridx = 2; c.gridy = 11;	provincia.setMinimumSize(dimensionCampo);	provincia.setPreferredSize(dimensionCampo);
		provincia.addItem("--Seleccione");	this.add(provincia, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Código postal*");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		codigoPostal = new JTextField();	codigoPostal.setFont(fuenteLabelCampo);	codigoPostal.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese el CP", codigoPostal); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 13;	codigoPostal.setMinimumSize(dimensionCampo);	codigoPostal.setPreferredSize(dimensionCampo);	this.add(codigoPostal, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleDer; c.gridwidth = 1;
		
		label = new JLabel("Localidad*");	label.setFont(fuenteLabelCampo);	c.gridx = 1; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		localidad = new JComboBox<String>();	localidad.setFont(fuenteLabelCampo);	localidad.setBackground(Color.white);	
		//localidad.setBorder(bordeCampo);
		c.gridx = 1; c.gridy = 13;	localidad.setMinimumSize(dimensionCampo);	localidad.setPreferredSize(dimensionCampo);
		localidad.addItem("--Seleccione");	this.add(localidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nacionalidad*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nacionalidad = new JComboBox<String>();	
		nacionalidad.setFont(fuenteLabelCampo);	nacionalidad.setBackground(Color.white);	
		//nacionalidad.setBorder(bordeCampo);
		c.gridx = 2; c.gridy = 13;	nacionalidad.setMinimumSize(dimensionCampo);	nacionalidad.setPreferredSize(dimensionCampo);
		nacionalidad.addItem("--Seleccione");	this.add(nacionalidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("CUIT");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 14;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		cuit = new JTextField();	cuit.setFont(fuenteLabelCampo);	cuit.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese los 11 dígitos del número de CUIT", cuit); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 15;	cuit.setMinimumSize(dimensionCampo);	cuit.setPreferredSize(dimensionCampo);	this.add(cuit, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Posición frente al IVA*");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 14;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		posicionIVA = new JComboBox<String>();	posicionIVA.setFont(fuenteLabelCampo);	posicionIVA.setBackground(Color.white);	
		//posicionIVA.setBorder(bordeCampo);
		c.gridx = 2; c.gridy = 15;	posicionIVA.setMinimumSize(dimensionCampo);	posicionIVA.setPreferredSize(dimensionCampo);	
		posicionIVA.addItem("CONSUMIDOR FINAL");	this.add(posicionIVA, c);
		
			c.fill = GridBagConstraints.NONE; c.weighty = pesoYLabel; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
		
		label = new JLabel("*campo obligatorio");	label.setFont(fuenteLabelFinal);	c.gridx = 2; c.gridy = 16;	this.add(label, c);
		
		
		
		
//		button = new JButton("Volver");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				frame.dispose();
////				frameAnterior = new EstacionGestionar();
//			}
//		});
//		c.anchor = GridBagConstraints.CENTER;
//		c.weightx = 0.5;
//													c.gridx = 0; c.gridy = 4;
//		this.add(button, c);
//
//		button = new JButton("Siguiente");
////		button.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e) {
////				try {
////					
////						inputEstaVacia();
////						inputEsValida(); 
////						nombreEstaRepetido();
////						
////						Estacion futuraEstacion = gestorEstacion.crearEstacion(nombre.getText(),
////													LocalTime.of(Integer.parseInt(horaApertura.getText()), Integer.parseInt(minutoApertura.getText())),
////													LocalTime.of(Integer.parseInt(horaCierre.getText()), Integer.parseInt(minutoCierre.getText())),
////													EstadoEstacion.valueOf(comboBox.getSelectedItem().toString()));						
////						
////						frame.dispose();
////						frameSiguiente = new EstacionAltaGrafo(frame, futuraEstacion);
////					
////				}catch (InputVacioException IVE) {
////					
////					JOptionPane.showMessageDialog(frame,
////							"Faltan completar los siguientes campos:\n\n"+IVE.getMessage(),
////						    "Error",
////						    JOptionPane.ERROR_MESSAGE);
////				}catch (InputInvalidaException IIE) {
////					
////					JOptionPane.showMessageDialog(frame,
////							IIE.getMessage()+"- El nombre puede tener como máximo 30 caracteres de longitud. \n"+
////									  "- La hora debe encontrarse en el intervalo [0, 23]. \n"+
////									  "- Los minutos deben encontrarse en el intervalo [0,59].\n"+
////									  "- La hora de cierre debe ser mayor a la hora de inicio.\n",	
////						    "Error",
////						    JOptionPane.ERROR_MESSAGE);
////				}catch (NombreEstacionRepetidoException NERE) {
////					
////					JOptionPane.showMessageDialog(frame,
////							NERE.getMessage(),
////						    "Error",
////						    JOptionPane.ERROR_MESSAGE);
////				}
////				
////			}
////		});
//		c.anchor = GridBagConstraints.CENTER;
//		c.weightx = 0.5;
//		c.gridx = 5;
//		c.gridy = 4;
//		this.add(button, c);
//		
	}

	public boolean validar() {
		try {
			
			inputNoEsVacia();
			
			return true;
		}
		catch(InputVacioException e1) {
			
			String pregunta = "<html><p>Faltan datos</p><html>";
			//Mensaje m = new Mensaje(frame, TipoMensaje.ADVERTENCIA, pregunta3, "Aceptar", "Cancelar");
			Mensaje m2 = new Mensaje(frameActual, TipoMensaje.ERROR, pregunta, "Aceptar", null);
		}
		
		return false;
	}

	private void inputNoEsVacia() throws InputVacioException{
		
		if(apellido.getText().isEmpty() || nombre.getText().isEmpty()) { //Faltan los otros campos
			throw new InputVacioException();
		}
	} 

}
	
//	public void inputEstaVacia() throws InputVacioException{
//		String error = "";
//		boolean algunoVacio = false;
//		
//		if(nombre.getText().isEmpty()) {
//			error += "- Nombre\n";
//			algunoVacio = true;
//		}
//		
//		if(horaApertura.getText().isEmpty() || minutoApertura.getText().isEmpty()) {
//			error += "- Hora de apertura\n";
//			algunoVacio = true;
//		}
//		
//		if(horaCierre.getText().isEmpty() || minutoCierre.getText().isEmpty()) {
//			error += "- Hora de cierre\n";
//			algunoVacio = true;
//		}
//		
//		if(algunoVacio) {
//			
//			throw new InputVacioException(error);
//		}
//			
//				
//	}
//	
//	public void inputEsValida() throws InputInvalidaException{
//		
//		if(!validarHora(horaApertura) || !validarMinuto(minutoApertura) ||
//		   !validarHora(horaCierre)   || !validarMinuto(minutoCierre)   || !validarNombre(nombre)
//		   || !horasValidas(horaApertura, minutoApertura, horaCierre, minutoCierre))
//
//				throw new InputInvalidaException();
//	}
//	
//	public void nombreEstaRepetido() throws NombreEstacionRepetidoException{ 
//		
//		for(Estacion e : gestorEstacion.getEstaciones()) {
//			
//			if(e.getNombre().equals(nombre.getText()))
//				throw new NombreEstacionRepetidoException();
//		}
//	}
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


