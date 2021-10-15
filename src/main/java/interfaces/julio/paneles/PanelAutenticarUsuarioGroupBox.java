package main.java.interfaces.julio.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.interfaces.TextPrompt;
import main.java.interfaces.julio.otros.RoundedBorder;

public class PanelAutenticarUsuarioGroupBox extends JPanel{
	
	private JLabel label;
	
	private JTextField nombre;
	private JTextField contrasenia;
	
	
	private Insets insetLabel = new Insets(0,88,8,80);
	private Insets insetCampo = new Insets(0,80,30,80);
	
	private Font fuenteGroupBox = new Font("Iniciar Sesión", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	
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
		
			c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL;  c.insets = insetCampo;
		
		nombre = new JTextField(); nombre.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt("Ingrese el Nombre del Usuario",nombre); fondoJTextField.setForeground(Color.GRAY); fondoJTextField.setFont(fuenteLabelCampo);
		nombre.setPreferredSize(new Dimension(350, 30));
		c.gridy = 1;	this.add(nombre, c);
		
			c.anchor = GridBagConstraints.SOUTHWEST;	c.fill = GridBagConstraints.NONE;  c.insets = insetLabel;
		
		label = new JLabel("Contraseña");	label.setFont(fuenteLabelCampo);	c.gridy = 2;	this.add(label, c);
		
			c.anchor = GridBagConstraints.CENTER; c.fill = GridBagConstraints.HORIZONTAL; c.insets = new Insets(0,80,50,80);
		
		contrasenia = new JTextField();	contrasenia.setBorder(bordeCampo);
		fondoJTextField = new TextPrompt(" Ingrese la contraseña", contrasenia); fondoJTextField.setForeground(Color.GRAY); fondoJTextField.setFont(fuenteLabelCampo);
		contrasenia.setPreferredSize(new Dimension(350, 30));
		c.gridy = 3;	this.add(contrasenia, c);

	}

	public boolean validar() { //Temporalmente va a validar que no sean vacios
		if(!nombre.getText().isEmpty() && !contrasenia.getText().isEmpty()) {
				return true;
			}
		
		return false;
	}
}
