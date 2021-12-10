package main.java.interfaces.CU17;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import main.java.dtos.PasajeroDTO;
import main.java.enums.TipoDocumento;
import main.java.excepciones.InputInvalidaException;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.clasesExtra.TextPrompt;
import main.java.interfaces.clasesExtra.UppercaseDocumentFilter;
import main.java.interfaces.frames.FramePrincipal;

public class PanelOcuparHabitacionBusqueda extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<TipoDocumento> tipoDocumento;
	
	private JLabel label;
	
	private JLabel labelApellidoInvalido;
	private JLabel labelNombreInvalido;
	private JLabel labelDocumentoInvalido;
	
	private JTextField apellido;
	private JTextField nombre;
	private JTextField numeroDocumento;
	
	private Insets insetLabel = new Insets(0,20,0,0);
	private Insets insetCampo = new Insets(0,0,0,30);
	private Insets insetLabelError = new Insets(0,0,0,35);
	
	private double pesoXLabel = 0.3;
	private double pesoYLabel = 0.5;
	private double pesoXCampo = 0.3;
	private double pesoYCampo = 0.1;
	
	private Dimension dimensionCampo = new Dimension(180, 30);
	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private TextPrompt fondoJTextField;
	//cuadradito de arriba

	public PanelOcuparHabitacionBusqueda(FramePrincipal frame) {
		
		this.setBackground(Color.WHITE);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), "Ocupar Habitación", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		
		DocumentFilter filter = new UppercaseDocumentFilter();
		
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.WEST;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Apellido");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.NORTHEAST; c.gridx = 1;	c.insets = insetLabelError;	
			
				labelApellidoInvalido = new JLabel();	labelApellidoInvalido.setFont(fuenteLabelError); 
				labelApellidoInvalido.setText(" Formato inválido ");
				labelApellidoInvalido.setOpaque(true);	
				labelApellidoInvalido.setBackground(Color.decode("#cc0000"));
				labelApellidoInvalido.setForeground(Color.WHITE);
				this.add(labelApellidoInvalido, c);
				
				labelApellidoInvalido.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
	
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		// Configuracion apellido textField
		apellido = new JTextField(); apellido.setFont(fuenteLabelCampo);	apellido.setBorder(bordeCampo);	
		
		apellido.setDocument(new JTextFieldLimitado(40));
		apellido.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelApellidoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {;
			  labelApellidoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelApellidoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese un apellido",apellido); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 1; c.gridy = 0;	apellido.setMinimumSize(dimensionCampo);	apellido.setPreferredSize(dimensionCampo);	this.add(apellido, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;
		
		label = new JLabel("Nombre");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.NORTHEAST; c.gridx = 3;	c.insets = insetLabelError;	
				
				labelNombreInvalido = new JLabel();	labelNombreInvalido.setFont(fuenteLabelError); 
				labelNombreInvalido.setText(" Formato inválido ");
				labelNombreInvalido.setOpaque(true);	
				labelNombreInvalido.setBackground(Color.decode("#cc0000"));
				labelNombreInvalido.setForeground(Color.WHITE);
				this.add(labelNombreInvalido, c);
				
				labelNombreInvalido.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		// Configuracion nombre textField
		nombre = new JTextField();	nombre.setFont(fuenteLabelCampo);	nombre.setBorder(bordeCampo);
		
		nombre.setDocument(new JTextFieldLimitado(40));
		nombre.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelNombreInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelNombreInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelNombreInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese un nombre", nombre); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 3; c.gridy = 0;	nombre.setMinimumSize(dimensionCampo);	nombre.setPreferredSize(dimensionCampo);	this.add(nombre, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel; 
		
		label = new JLabel("Tipo de documento");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
		
		c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
	
		tipoDocumento = new JComboBox<TipoDocumento>();	tipoDocumento.setFont(fuenteLabelCampo);	tipoDocumento.setBackground(Color.white);		
		c.gridx = 1; c.gridy = 1;	tipoDocumento.setMinimumSize(dimensionCampo);	tipoDocumento.setPreferredSize(dimensionCampo);
		//tipoDocumento.addItem("--Seleccione");
		this.cargarComboBoxDesdeEnum(tipoDocumento);
		this.add(tipoDocumento, c);
		
			c.fill = GridBagConstraints.NONE; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;
		
		label = new JLabel("Número de documento");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 1;	this.add(label, c);
		
			c.anchor = GridBagConstraints.NORTHEAST; c.gridx = 3;	c.gridy = 1;	c.insets = insetLabelError;	
			
				labelDocumentoInvalido = new JLabel();	labelDocumentoInvalido.setFont(fuenteLabelError); 
				labelDocumentoInvalido.setText(" Formato inválido ");
				labelDocumentoInvalido.setOpaque(true);	
				labelDocumentoInvalido.setBackground(Color.decode("#cc0000"));
				labelDocumentoInvalido.setForeground(Color.WHITE);
				this.add(labelDocumentoInvalido, c);
				
				labelDocumentoInvalido.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.HORIZONTAL; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		numeroDocumento = new JTextField();	numeroDocumento.setFont(fuenteLabelCampo);	numeroDocumento.setBorder(bordeCampo);
		
		numeroDocumento.setDocument(new JTextFieldLimitado(20));
		numeroDocumento.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelDocumentoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelDocumentoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelDocumentoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese el número de documento", numeroDocumento); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 3; c.gridy = 1;	numeroDocumento.setMinimumSize(dimensionCampo);	numeroDocumento.setPreferredSize(dimensionCampo);	this.add(numeroDocumento, c);
		
	
	}
	
	public PasajeroDTO getFiltros() {
		PasajeroDTO dto = new PasajeroDTO();
		
		if (!nombre.getText().isBlank()) dto.setNombre(nombre.getText());
		if (!apellido.getText().isBlank()) dto.setApellido(apellido.getText());
		dto.setTipoDocumento((TipoDocumento) tipoDocumento.getSelectedItem());
		if (!numeroDocumento.getText().isBlank()) dto.setNumeroDoc(numeroDocumento.getText());
		
		return dto;
	}
	
	private void cargarComboBoxDesdeEnum(JComboBox<TipoDocumento> comboBox) {
		comboBox.addItem(null);
		for(TipoDocumento o : TipoDocumento.values()){
			
			comboBox.addItem(o); //Para que no aparezcan los guiones bajos
		}
	}
	
	public void inputEsValida() throws InputInvalidaException{
		
		this.labelApellidoInvalido.setVisible(false);
		this.labelNombreInvalido.setVisible(false);
		this.labelDocumentoInvalido.setVisible(false);

		List<String> inputsInvalidos = new ArrayList<String>();
		boolean alMenosUnoInvalido = false;
		if(!apellido.getText().isEmpty())
			if(contieneCaracteresEspeciales(apellido) || contieneUnNumero(apellido)) {
				inputsInvalidos.add("Apellido");
				alMenosUnoInvalido = true;
			}
		if(!nombre.getText().isEmpty())	
			if(contieneCaracteresEspeciales(nombre) || contieneUnNumero(nombre)) {
				inputsInvalidos.add("Nombre");
				alMenosUnoInvalido = true;
			}
		
		if(!numeroDocumento.getText().isEmpty())
			if(!esValidoNumeroDocumento(numeroDocumento)) {	
				
				inputsInvalidos.add("Documento");
				alMenosUnoInvalido = true;
			}
		
		if(alMenosUnoInvalido) {
			throw new InputInvalidaException(inputsInvalidos);
		}
	}
	
	public void colocarLabelInvalido(List<String> inputsInvalidos) {

		if(inputsInvalidos.contains("Apellido"))
			this.labelApellidoInvalido.setVisible(true);

		if(inputsInvalidos.contains("Nombre"))
			this.labelNombreInvalido.setVisible(true);
				
		if(inputsInvalidos.contains("Documento"))
			this.labelDocumentoInvalido.setVisible(true);
	}
	
	private boolean esValidoNumeroDocumento(JTextField numeroDocumento2) {
		
		//Si es DNI, se ve si es completamente numero y si tiene como máximo 8 caracteres, si es NULL quiere decir que no seleccionó tipo de documento
		if(tipoDocumento.getSelectedItem() != null && tipoDocumento.getSelectedItem().equals("DNI")) {	
			return (esTotalmenteNumero(numeroDocumento2) && numeroDocumento2.getText().length() < 9);
		}
		else {
			
			return !contieneCaracteresEspeciales(numeroDocumento2);	//Si no es DNI, se valida si tiene o no caracteres especiales
		}
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
