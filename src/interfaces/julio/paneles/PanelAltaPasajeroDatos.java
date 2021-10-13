package interfaces.julio.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import interfaces.TextPrompt;
import interfaces.TextPrompt.Show;

public class PanelAltaPasajeroDatos extends JPanel{
	
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
	private Font fuente =new Font("Monospaced", Font.ITALIC, 12);	
	
	TextPrompt fondoJTextField;
	
//	private GestorEstacion gestorEstacion = GestorEstacion.getInstance();
//	
//	private EstacionGestionar frameAnterior;
//	private EstacionAltaGrafo frameSiguiente;
	
	public PanelAltaPasajeroDatos() {
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Dar de alta pasajero"));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Apellido*");	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		apellido = new JTextField(10); fondoJTextField = new TextPrompt("Ingrese un apellido",apellido); fondoJTextField.setForeground(Color.GRAY);		
		c.gridx = 0; c.gridy = 1;	this.add(apellido, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nombre*");	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nombre = new JTextField();	fondoJTextField = new TextPrompt("Ingrese un nombre", nombre); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 1;	this.add(nombre, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Tipo de documento*");	c.gridx = 0; c.gridy = 2;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		tipoDocumento = new JComboBox<String>();	c.gridx = 0; c.gridy = 3;	
		tipoDocumento.addItem("--Seleccione");		this.add(tipoDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Número de documento*");	c.gridx = 2; c.gridy = 2;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		numeroDocumento = new JTextField();	fondoJTextField = new TextPrompt("Ingrese el número de documento", numeroDocumento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 3;	this.add(numeroDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Fecha de nacimiento*");	c.gridx = 0; c.gridy = 4;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		fechaNacimiento = new JTextField();	fondoJTextField = new TextPrompt("dd/mm/aaaa", fechaNacimiento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 5;	this.add(fechaNacimiento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Email*");	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		email = new JTextField();	fondoJTextField = new TextPrompt("Ingrese el correo electrónico", email); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 5;	this.add(email, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Teléfono*");	c.gridx = 0; c.gridy = 6;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		telefono = new JTextField();	fondoJTextField = new TextPrompt("Ingrese número de teléfono", telefono); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 7;	this.add(telefono, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Ocupación*");	c.gridx = 2; c.gridy = 6;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		ocupacion = new JTextField();	fondoJTextField = new TextPrompt("Ingrese ocupación", ocupacion); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 2; c.gridy = 7;	this.add(ocupacion, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Dirección*");	c.gridx = 0; c.gridy = 8;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		direccion = new JTextField();	fondoJTextField = new TextPrompt("Ingrese el domicilio (calle y número)", direccion); fondoJTextField.setForeground(Color.GRAY);
		direccion.setPreferredSize(new Dimension(400,20));
		direccion.setMinimumSize(new Dimension(400,20));
		c.gridx = 0; c.gridy = 9;	this.add(direccion, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Departamento");	c.gridx = 2; c.gridy = 8;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		departamento = new JTextField();	fondoJTextField = new TextPrompt("Ingrese el dpto.", departamento); fondoJTextField.setForeground(Color.GRAY);
		departamento.setPreferredSize(new Dimension(180,20));
		departamento.setMinimumSize(new Dimension(180,20));
		c.gridx = 2; c.gridy = 9;	this.add(departamento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleDer;
		
		label = new JLabel("Piso");	c.gridx = 3; c.gridy = 8;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		piso = new JTextField();	fondoJTextField = new TextPrompt("Ingrese el piso", piso); fondoJTextField.setForeground(Color.GRAY);
		piso.setPreferredSize(new Dimension(180,20));
		piso.setMinimumSize(new Dimension(180,20));
		c.gridx = 3; c.gridy = 9;	this.add(piso, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("País*");	c.gridx = 0; c.gridy = 10;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		pais = new JComboBox<String>();	c.gridx = 0; c.gridy = 11;
		pais.addItem("--Seleccione");	this.add(pais, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;

		label = new JLabel("Provincia*");	c.gridx = 2; c.gridy = 10;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		provincia = new JComboBox<String>();	c.gridx = 2; c.gridy = 11;
		provincia.addItem("--Seleccione");	this.add(provincia, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabelDobleIzq; c.gridwidth = 1;
		
		label = new JLabel("Código postal*");	c.gridx = 0; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampoDoble; c.weighty = pesoYCampo; c.insets = insetCampoDobleIzq;
		
		codigoPostal = new JTextField();	fondoJTextField = new TextPrompt("Ingrese el CP", codigoPostal); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 13;	this.add(codigoPostal, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabelDoble; c.weighty = pesoYLabel; c.insets = insetLabelDobleDer; c.gridwidth = 1;
		
		label = new JLabel("Localidad*");	c.gridx = 1; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDobleDer;
		
		localidad = new JComboBox<String>();	c.gridx = 1; c.gridy = 13;
		localidad.addItem("--Seleccione");	this.add(localidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Nacionalidad*");	c.gridx = 2; c.gridy = 12;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		nacionalidad = new JComboBox<String>();	c.gridx = 2; c.gridy = 13;
		nacionalidad.addItem("--Seleccione");	this.add(nacionalidad, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("CUIT");	c.gridx = 0; c.gridy = 14;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		cuit = new JTextField();	fondoJTextField = new TextPrompt("Ingrese los 11 dígitos del número de CUIT", cuit); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 0; c.gridy = 15;	this.add(cuit, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; c.gridwidth = 1;
		
		label = new JLabel("Posición frente al IVA*");	c.gridx = 2; c.gridy = 14;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; c.gridwidth = 2;
		
		posicionIVA = new JComboBox<String>();	c.gridx = 2; c.gridy = 15;
		posicionIVA.addItem("CONSUMIDOR FINAL");	this.add(posicionIVA, c);
		
			c.fill = GridBagConstraints.NONE; c.weighty = pesoYLabel; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
		
		label = new JLabel("*campo obligatorio");	label.setFont(fuente);	c.gridx = 2; c.gridy = 16;	this.add(label, c);
		
		
		
		
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


