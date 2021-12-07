package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import main.java.excepciones.InputInvalidaException;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.clasesExtra.TextPrompt;

public class PanelFacturarGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	
	private JLabel labelNumeroDeHabitacionVacio;				//Muestran mensaje "Campo incompleto"
	private JLabel labelHoraDeSalidaVacio;
	
	private JLabel labelNumeroDeHabitacionFormatoInvalido;		//Muestran mensaje "Formato inválido"
	private JLabel labelHoraDeSalidaFormatoInvalido;
	
	private JTextField numeroDeHabitacion;
	private JTextField horaDeSalida;
	
	private Insets insetLabel = new Insets(0,0,0,20);
	private Insets insetCampo = new Insets(0,0,0,60);
	private Insets insetLabelError = new Insets(0,30,0,0);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private Dimension dimensionCampo = new Dimension(150, 30);
	
	TextPrompt fondoJTextField;
	
	public PanelFacturarGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Facturar", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.EAST;	c.gridy = 0;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;	
		
		label = new JLabel("Número de habitación");	label.setFont(fuenteLabelCampo);	c.gridx = 0; this.add(label, c);
		
			c.insets = insetLabelError;	c.anchor = GridBagConstraints.NORTH;
			
		labelNumeroDeHabitacionVacio = new JLabel(" Campo incompleto ");	labelNumeroDeHabitacionVacio.setFont(fuenteLabelError); c.gridx = 1;
		labelNumeroDeHabitacionVacio.setOpaque(true);	labelNumeroDeHabitacionVacio.setBackground(Color.decode("#cc0000")); labelNumeroDeHabitacionVacio.setForeground(Color.WHITE);
		this.add(labelNumeroDeHabitacionVacio, c); labelNumeroDeHabitacionVacio.setVisible(false);	//Empieza invisible
		
		labelNumeroDeHabitacionFormatoInvalido = new JLabel(" Formato inválido ");	labelNumeroDeHabitacionFormatoInvalido.setFont(fuenteLabelError); c.gridx = 1; 
		labelNumeroDeHabitacionFormatoInvalido.setOpaque(true);	labelNumeroDeHabitacionFormatoInvalido.setBackground(Color.decode("#cc0000")); labelNumeroDeHabitacionFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelNumeroDeHabitacionFormatoInvalido, c); labelNumeroDeHabitacionFormatoInvalido.setVisible(false);	//Empieza invisible
		
			c.fill = GridBagConstraints.HORIZONTAL;	c.insets = insetCampo; c.anchor = GridBagConstraints.CENTER;
		
		numeroDeHabitacion = new JTextField(); numeroDeHabitacion.setFont(fuenteLabelCampo);	numeroDeHabitacion.setBorder(bordeCampo);	
		numeroDeHabitacion.setDocument(new JTextFieldLimitado(5));
		numeroDeHabitacion.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelNumeroDeHabitacionVacio.setVisible(false);
				  labelNumeroDeHabitacionFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelNumeroDeHabitacionVacio.setVisible(false);
				  labelNumeroDeHabitacionFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelNumeroDeHabitacionVacio.setVisible(false);
				  labelNumeroDeHabitacionFormatoInvalido.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese nro. de habitación",numeroDeHabitacion); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 1; 	numeroDeHabitacion.setMinimumSize(dimensionCampo);	numeroDeHabitacion.setPreferredSize(dimensionCampo);
		this.add(numeroDeHabitacion, c); 		
		
			c.insets = insetLabel;	c.anchor = GridBagConstraints.EAST;	c.fill = GridBagConstraints.NONE;
			
		label = new JLabel("Hora de salida");	label.setFont(fuenteLabelCampo);	c.gridx = 2;	this.add(label, c);
		
			c.insets = insetLabelError;	c.fill = GridBagConstraints.NONE;	c.anchor = GridBagConstraints.NORTH;
			
		labelHoraDeSalidaVacio = new JLabel(" Campo incompleto ");	labelHoraDeSalidaVacio.setFont(fuenteLabelError); c.gridx = 3;
		labelHoraDeSalidaVacio.setOpaque(true);	labelHoraDeSalidaVacio.setBackground(Color.decode("#cc0000")); labelHoraDeSalidaVacio.setForeground(Color.WHITE);
		this.add(labelHoraDeSalidaVacio, c); labelHoraDeSalidaVacio.setVisible(false);	//Empieza invisible
		
		labelHoraDeSalidaFormatoInvalido = new JLabel(" Formato inválido ");	labelHoraDeSalidaFormatoInvalido.setFont(fuenteLabelError); c.gridx = 3; 
		labelHoraDeSalidaFormatoInvalido.setOpaque(true);	labelHoraDeSalidaFormatoInvalido.setBackground(Color.decode("#cc0000")); labelHoraDeSalidaFormatoInvalido.setForeground(Color.WHITE);
		this.add(labelHoraDeSalidaFormatoInvalido, c); labelHoraDeSalidaFormatoInvalido.setVisible(false);	//Empieza invisible
		
			c.insets = insetCampo;	c.fill = GridBagConstraints.HORIZONTAL;	c.anchor = GridBagConstraints.CENTER;
 
		try {
			MaskFormatter mascaraHoraSalida = new MaskFormatter("##':##");
			horaDeSalida = new JFormattedTextField(mascaraHoraSalida);
	    	
	    }catch (ParseException e) {
	    	e.printStackTrace();
	    }
		horaDeSalida.setFont(fuenteLabelCampo);	horaDeSalida.setBorder(bordeCampo);	
		horaDeSalida.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelHoraDeSalidaVacio.setVisible(false);
				  labelHoraDeSalidaFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelHoraDeSalidaVacio.setVisible(false);
				  labelHoraDeSalidaFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelHoraDeSalidaVacio.setVisible(false);
				  labelHoraDeSalidaFormatoInvalido.setVisible(false);
			  }
		});
		c.gridx = 3; horaDeSalida.setMinimumSize(dimensionCampo);	horaDeSalida.setPreferredSize(dimensionCampo);	
		this.add(horaDeSalida, c); 	

	}

	

	public void inputNoEsVacia() throws InputVacioException{
		
		String inputsVacios = "";
		boolean alMenosUnoInvalido = false;

		if(this.numeroDeHabitacion.getText().isEmpty()) {	
			inputsVacios += "n";
			alMenosUnoInvalido = true;
		}
			
		if(this.horaDeSalida.getText().contains(" ")) {	//Por el formato que tiene
			inputsVacios += "h";
			alMenosUnoInvalido = true;
		}
		
		if(alMenosUnoInvalido) {
			throw new InputVacioException(inputsVacios);
		}
	}
	
	public void inputEsValida() throws InputInvalidaException{

		String inputsInvalidos = "";
		boolean alMenosUnoInvalido = false;

		if(!this.esTotalmenteNumero(numeroDeHabitacion)) {
			inputsInvalidos += "n";
			alMenosUnoInvalido = true;
		}
			
		if(!this.esValidaHora(horaDeSalida)) {
			inputsInvalidos += "h";
			alMenosUnoInvalido = true;
		}
		
		if(alMenosUnoInvalido) {
			throw new InputInvalidaException(inputsInvalidos);
		}
	}
	
	private boolean esValidaHora(JTextField hora) {
		
		boolean resultado = true;
		String horaString = hora.getText();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

		try {
			
			@SuppressWarnings("unused")
			LocalTime localTime = LocalTime.parse(horaString, formatter);	//Si se puede convertir a LocalTime, es una hora válida [00:00, 23:59]
			
		}
		catch(DateTimeParseException e) {
			resultado = false;
		}
		
		return resultado;
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

	public void colocarLabelVacio(String inputs) {
		
		if(inputs.contains("n")) {
			labelNumeroDeHabitacionVacio.setVisible(true);
		}
		if(inputs.contains("h")) {
			labelHoraDeSalidaVacio.setVisible(true);
		}
		
	}
	
	public void colocarLabelInvalido(String inputs) {

		if(inputs.contains("n")) {
			labelNumeroDeHabitacionFormatoInvalido.setVisible(true);
		}
		if(inputs.contains("h")) {
			labelHoraDeSalidaFormatoInvalido.setVisible(true);
		}
	
	}



	public String getNumeroHabitacion() {
		return this.numeroDeHabitacion.getText();
	}
	
	public String getHoraSalida() {
		return this.horaDeSalida.getText();
	}
}