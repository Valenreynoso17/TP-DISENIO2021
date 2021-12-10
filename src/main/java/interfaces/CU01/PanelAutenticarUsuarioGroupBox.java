package main.java.interfaces.CU01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.java.excepciones.InputVacioException;
import main.java.excepciones.UsuarioOContraseniaIncorrectaException;
import main.java.gestores.GestorUsuario;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.clasesExtra.TextPrompt;

public class PanelAutenticarUsuarioGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	
	private JLabel labelNombreVacio;
	private JLabel labelContraseniaVacio;
	
	private JTextField nombre;
	private JPasswordField contrasenia;
	
	
	private Insets insetLabel = new Insets(0,88,8,80);
	private Insets insetCampo = new Insets(0,80,30,80);
	
	private Insets insetLabelError = new Insets(0,0,2,90);
	
	private GestorUsuario gestorUsuario;

	
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.decode("#BDBDBD"));
	
	TextPrompt fondoJTextField;
	
	public PanelAutenticarUsuarioGroupBox() {
		gestorUsuario = GestorUsuario.getInstance();
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Iniciar Sesi�n", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.SOUTHWEST;	c.gridx = 0;	//c.weightx = 0.1; c.weighty = 0.1;
		
			c.insets = new Insets(30,88,8,80);	
		
		label = new JLabel("Nombre");	label.setFont(fuenteLabelCampo); c.gridy = 0;	this.add(label, c);
				
			c.anchor = GridBagConstraints.SOUTHEAST; c.insets = insetLabelError;
			
		labelNombreVacio = new JLabel();	labelNombreVacio.setFont(fuenteLabelError); 
		labelNombreVacio.setText(" Campo incompleto ");
		labelNombreVacio.setOpaque(true);
		c.gridy = 0;	
		labelNombreVacio.setBackground(Color.decode("#cc0000"));
		labelNombreVacio.setForeground(Color.WHITE);
		this.add(labelNombreVacio, c);
		
		labelNombreVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL;  c.insets = insetCampo;
		
		nombre = new JTextField(); nombre.setBorder(bordeCampo);
		nombre.setDocument(new JTextFieldLimitado(40));
		nombre.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelNombreVacio.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelNombreVacio.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelNombreVacio.setVisible(false);
			  }
		});
		
		fondoJTextField = new TextPrompt("Ingrese el Nombre del Usuario",nombre); fondoJTextField.setForeground(Color.GRAY); fondoJTextField.setFont(fuenteLabelCampo);
		nombre.setPreferredSize(new Dimension(350, 30));
		c.gridy = 1;	this.add(nombre, c);
		
			c.anchor = GridBagConstraints.SOUTHWEST;	c.fill = GridBagConstraints.NONE;  c.insets = insetLabel;
		
		label = new JLabel("Contrase�a");	label.setFont(fuenteLabelCampo);	c.gridy = 2;	this.add(label, c);
		
			c.anchor = GridBagConstraints.SOUTHEAST; c.insets = insetLabelError;
		
		labelContraseniaVacio = new JLabel();	labelContraseniaVacio.setFont(fuenteLabelError); 
		labelContraseniaVacio.setText(" Campo incompleto ");
		labelContraseniaVacio.setOpaque(true);
		c.gridy = 2;	
		labelContraseniaVacio.setBackground(Color.decode("#cc0000"));
		labelContraseniaVacio.setForeground(Color.WHITE);
		this.add(labelContraseniaVacio, c);
		
		labelContraseniaVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL; c.insets = new Insets(0,80,50,80);
		
		contrasenia = new JPasswordField();	contrasenia.setBorder(bordeCampo);
		contrasenia.setDocument(new JTextFieldLimitado(40));
		contrasenia.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelContraseniaVacio.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelContraseniaVacio.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelContraseniaVacio.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt(" Ingrese la contrase�a", contrasenia); fondoJTextField.setForeground(Color.GRAY); fondoJTextField.setFont(fuenteLabelCampo);
		contrasenia.setPreferredSize(new Dimension(350, 30));
		c.gridy = 3;	this.add(contrasenia, c);

	}

	public void inputEsNoVacio() throws InputVacioException{
		
		labelNombreVacio.setVisible(false);
		labelContraseniaVacio.setVisible(false);
		
		String inputsVacios = "";
		boolean alMenosUnoVacio = false;
		
		if(nombre.getText().isEmpty()) {
			inputsVacios += "n";
			alMenosUnoVacio = true;
		}
			
		if(contrasenia.getPassword().length == 0){
			inputsVacios += "c";
			alMenosUnoVacio = true;
		}
		
		if(alMenosUnoVacio) {
			throw new InputVacioException(inputsVacios);
		}
	}
	
	public void colocarLabelVacio(String inputs) {
		
		if(inputs.contains("n")) {
			labelNombreVacio.setVisible(true);
		}
		if(inputs.contains("c")) {
			labelContraseniaVacio.setVisible(true);
		}
		
	}
	
	public void validarUsuarioContrasenia() throws UsuarioOContraseniaIncorrectaException {
		gestorUsuario.autentificar(nombre.getText(), contrasenia.getPassword());
	}
	
//	public void colocarLabelInvalido(String inputs) {
//		
//		if(inputs.contains("d")) {
//			labelFechaDesdeInvalida.setVisible(true);
//		}
//		if(inputs.contains("h")) {
//			labelFechaHastaInvalida.setVisible(true);
//		}
//	
//	}
}
