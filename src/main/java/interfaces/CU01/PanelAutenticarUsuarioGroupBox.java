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

import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelAutenticarUsuarioGroupBox extends JPanel{
	
	private JLabel label;
	
	private JLabel labelNombreVacio;
	private JLabel labelContraseniaVacio;
	
	private JTextField nombre;
	private JPasswordField contrasenia;
	
	
	private Insets insetLabel = new Insets(0,88,8,80);
	private Insets insetCampo = new Insets(0,80,30,80);
	
	private Insets insetLabelError = new Insets(0,0,2,90);

	
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.decode("#BDBDBD"));
	
	TextPrompt fondoJTextField;
	
//	private GestorEstacion gestorEstacion = GestorEstacion.getInstance();
//	
//	private EstacionGestionar frameAnterior;
//	private EstacionAltaGrafo frameSiguiente;
	
	public PanelAutenticarUsuarioGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Iniciar Sesión", 0, 0, fuenteGroupBox));
		
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
		
		label = new JLabel("Contraseña");	label.setFont(fuenteLabelCampo);	c.gridy = 2;	this.add(label, c);
		
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
		fondoJTextField = new TextPrompt(" Ingrese la contraseña", contrasenia); fondoJTextField.setForeground(Color.GRAY); fondoJTextField.setFont(fuenteLabelCampo);
		contrasenia.setPreferredSize(new Dimension(350, 30));
		c.gridy = 3;	this.add(contrasenia, c);

	}

	public boolean inputEsNoVacio() {
		
//		labelNombreVacio.setVisible(false);	//Se resetean cuando el usuario vuelve a presionar "Iniciar Sesión"
//		labelContraseniaVacio.setVisible(false);	//Pareciera que se puede sacar, igualmente por si acaso lo dejo comentado
		
		boolean resultado = true;
		
		if(nombre.getText().isEmpty()) {
			
			resultado = false;
			labelNombreVacio.setVisible(true);
		}
			
		if(contrasenia.getPassword().length == 0){
				
			resultado = false;
			labelContraseniaVacio.setVisible(true);
		}
		
	
	return resultado;
	}
}
