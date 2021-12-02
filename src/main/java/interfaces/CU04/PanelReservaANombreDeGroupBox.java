package main.java.interfaces.CU04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelReservaANombreDeGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	
	private JTextField apellido;					//Campos de texto
	private JTextField nombre;
	private JTextField telefono;
	
	private JLabel labelApellidoVacio;				//Muestran mensaje "Campo incompleto"
	private JLabel labelNombreVacio;
	private JLabel labelTelefonoVacio;
	
	private JLabel labelApellidoFormatoInvalido;	//Muestran mensaje "Formato inválido"
	private JLabel labelNombreFormatoInvalido;
	private JLabel labelTelefonoFormatoInvalido;
	
	private double pesoXLabel = 0.0;
	private double pesoYLabel = 0.0;
	private double pesoXCampo = 0.0;
	private double pesoYCampo = 0.0;
	
	private Insets insetLabel = new Insets(0,20,0,0);	//Espacios en blanco para acomodar los componentes
	private Insets insetCampo = new Insets(0,10,10,10);
	private Insets insetLabelError = new Insets(0,0,0,20);
	private Insets insetLabelFinal = new Insets(0,40,0,0);
	
	private Dimension dimensionCampo = new Dimension(330, 30);
	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	private Font fuenteLabelFinal =new Font("SourceSansPro", Font.ITALIC, 13);	
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private TextPrompt fondoJTextField;
	
	public PanelReservaANombreDeGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Reserva a nombre de", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.SOUTHWEST;
		
		c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	c.gridx = 0; 
	
		label = new JLabel("Apellido*");	label.setFont(fuenteLabelCampo);	c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelApellidoVacio = new JLabel(" Campo incompleto ");	labelApellidoVacio.setFont(fuenteLabelError); c.gridy = 0; 
		labelApellidoVacio.setOpaque(true);	labelApellidoVacio.setBackground(Color.decode("#cc0000")); labelApellidoVacio.setForeground(Color.WHITE);
		this.add(labelApellidoVacio, c); labelApellidoVacio.setVisible(false);	//Empieza invisible
		
		labelApellidoFormatoInvalido = new JLabel(" Formato inválido ");	labelApellidoFormatoInvalido.setFont(fuenteLabelError); c.gridy = 0; 
		labelApellidoFormatoInvalido.setOpaque(true);	labelApellidoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelApellidoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelApellidoFormatoInvalido, c); labelApellidoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
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
		c.gridy = 1;	apellido.setMinimumSize(dimensionCampo);	apellido.setPreferredSize(dimensionCampo);	
		this.add(apellido, c); 		
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; 
		
		label = new JLabel("Nombre*");	label.setFont(fuenteLabelCampo);	c.gridy = 2;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelNombreVacio = new JLabel(" Campo incompleto ");	labelNombreVacio.setFont(fuenteLabelError); c.gridy = 2; 
		labelNombreVacio.setOpaque(true);	labelNombreVacio.setBackground(Color.decode("#cc0000")); labelNombreVacio.setForeground(Color.WHITE);
		this.add(labelNombreVacio, c); labelNombreVacio.setVisible(false);	//Empieza invisible
		
		labelNombreFormatoInvalido = new JLabel(" Formato inválido ");	labelNombreFormatoInvalido.setFont(fuenteLabelError); c.gridy = 2; 
		labelNombreFormatoInvalido.setOpaque(true);	labelNombreFormatoInvalido.setBackground(Color.decode("#cc0000")); labelNombreFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelNombreFormatoInvalido, c); labelNombreFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
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
		c.gridy = 3;	nombre.setMinimumSize(dimensionCampo);	nombre.setPreferredSize(dimensionCampo);	this.add(nombre, c);
		
		c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;
		
		label = new JLabel("Teléfono*");	label.setFont(fuenteLabelCampo);	c.gridy = 4;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.insets = insetLabelError;
			
		labelTelefonoVacio = new JLabel(" Campo incompleto ");	labelTelefonoVacio.setFont(fuenteLabelError); c.gridy = 4; 
		labelTelefonoVacio.setOpaque(true);	labelTelefonoVacio.setBackground(Color.decode("#cc0000")); labelTelefonoVacio.setForeground(Color.WHITE);
		this.add(labelTelefonoVacio, c); labelTelefonoVacio.setVisible(false);	//Empieza invisible
		
		labelTelefonoFormatoInvalido = new JLabel(" Formato inválido ");	labelTelefonoFormatoInvalido.setFont(fuenteLabelError); c.gridy = 4; 
		labelTelefonoFormatoInvalido.setOpaque(true);	labelTelefonoFormatoInvalido.setBackground(Color.decode("#cc0000")); labelTelefonoFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelTelefonoFormatoInvalido, c); labelTelefonoFormatoInvalido.setVisible(false);	//Empieza invisible
	
			c.anchor = GridBagConstraints.SOUTHWEST;
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
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
		c.gridy = 5;	telefono.setMinimumSize(dimensionCampo);	telefono.setPreferredSize(dimensionCampo);	this.add(telefono, c);
		
			c.fill = GridBagConstraints.NONE; c.weighty = pesoYLabel; c.anchor = GridBagConstraints.WEST; c.insets = insetLabelFinal;
			
		label = new JLabel("*campo obligatorio");	label.setFont(fuenteLabelFinal);	c.gridy = 6;	this.add(label, c);
		
//			c.fill = GridBagConstraints.HORIZONTAL;
//			c.anchor = GridBagConstraints.CENTER; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
//	
//		label = new JLabel("Tipo Habitación");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
//	
//			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
//		
//		tipoHabitacion = new JTextField(); 	tipoHabitacion.setText("TipoHabitacion");	tipoHabitacion.setEditable(false);
//		tipoHabitacion.setFont(fuenteLabelCampo);	tipoHabitacion.setBorder(bordeCampo);	tipoHabitacion.setBackground(colorFondoCampoNoEditable);
//		c.gridy = 1;	tipoHabitacion.setMinimumSize(dimensionCampo);	tipoHabitacion.setPreferredSize(dimensionCampo);
//		this.add(tipoHabitacion, c); 	
//		
//			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
//			
//		label = new JLabel("Ingreso");	label.setFont(fuenteLabelCampo);	c.gridy = 2;	this.add(label, c);
//	
//			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
//		
//		ingreso = new JTextField(); ingreso.setText("nombreDia, dd/mm/aaaa, 12:00hs");	ingreso.setEditable(false);
//		ingreso.setFont(fuenteLabelCampo);	ingreso.setBorder(bordeCampo);		ingreso.setBackground(colorFondoCampoNoEditable);
//		c.gridy = 3;	ingreso.setMinimumSize(dimensionCampo);	ingreso.setPreferredSize(dimensionCampo);
//		this.add(ingreso, c); 	
//		
//			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
//			
//		label = new JLabel("Egreso");	label.setFont(fuenteLabelCampo);	c.gridy = 4;	this.add(label, c);
//	
//			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
//		
//		egreso = new JTextField(); egreso.setText("nombreDia, dd/mm/aaaa, 12:00hs");	egreso.setEditable(false);
//		egreso.setFont(fuenteLabelCampo);	egreso.setBorder(bordeCampo);	egreso.setBackground(colorFondoCampoNoEditable);
//		c.gridy = 5;	egreso.setMinimumSize(dimensionCampo);	egreso.setPreferredSize(dimensionCampo);
//		this.add(egreso, c); 		
		
	}
	
public void inputEsNoVacio() {
		
		boolean unoVacio = false;
		
		if(apellido.getText().isEmpty()) {
			unoVacio = true;
			labelApellidoVacio.setVisible(true);
		}
		if(nombre.getText().isEmpty()) {
			unoVacio = true;
			labelNombreVacio.setVisible(true);
		}
		if(telefono.getText().isEmpty()) {
			unoVacio = true;
			labelTelefonoVacio.setVisible(true);
		}
		
		if(unoVacio) {
			
		}
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
		if(!esTotalmenteNumero(telefono)) {
			resultado = false;
			labelTelefonoFormatoInvalido.setVisible(true);
		}
		
	
		return resultado;
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

}
