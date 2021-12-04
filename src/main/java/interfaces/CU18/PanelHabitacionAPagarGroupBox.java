package main.java.interfaces.CU18;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelHabitacionAPagarGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JLabel labelCampoVacio;
	
	private JTextField campo;
	
	private Insets insetLabel = new Insets(0,0,0,20);
	private Insets insetCampo = new Insets(0,20,0,0);
	private Insets insetLabelError = new Insets(0,30,0,0);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private Dimension dimensionCampo = new Dimension(350, 30);
	
	TextPrompt fondoJTextField;
	
	public PanelHabitacionAPagarGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Habitación a pagar", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.EAST;	c.gridx = 0;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;
	
		label = new JLabel("Nro. Habitación");	label.setFont(fuenteLabelCampo); c.gridy = 0;	this.add(label, c);
			
		c.anchor = GridBagConstraints.NORTH; c.insets = insetLabelError;
		
		labelCampoVacio = new JLabel();	labelCampoVacio.setFont(fuenteLabelError); 
		labelCampoVacio.setText(" Campo incompleto ");
		labelCampoVacio.setOpaque(true);	
		labelCampoVacio.setBackground(Color.decode("#cc0000"));
		labelCampoVacio.setForeground(Color.WHITE);
		c.gridx = 1;
		this.add(labelCampoVacio, c);
		
		labelCampoVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;  c.insets = insetCampo;	
		
		campo = new JTextField(); campo.setFont(fuenteLabelCampo);	campo.setBorder(bordeCampo);	campo.setDocument(new JTextFieldLimitado(5));
		campo.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  labelCampoVacio.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  labelCampoVacio.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  labelCampoVacio.setVisible(false);
			  }
		});
		fondoJTextField = new TextPrompt("Ingrese un nro de habitación",campo); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 1;	campo.setMinimumSize(dimensionCampo);	campo.setPreferredSize(dimensionCampo);	campo.setMaximumSize(dimensionCampo);
		this.add(campo, c);

	}

	public void inputNoEsVacia() throws InputVacioException{

		if(this.campo.getText().isEmpty()) {

			throw new InputVacioException();
		}
		
	}

	public void colocarLabelIncompleto() {
		
		this.labelCampoVacio.setVisible(true);
	}
}