package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import main.java.dtos.OcupacionDTO;
import main.java.dtos.ResponsableDePagoDTO;
import main.java.excepciones.InputVacioException;
import main.java.excepciones.NoExisteResponsableCuitException;
import main.java.gestores.GestorResponsableDePago;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.clasesExtra.TextPrompt;

public class PanelFacturarANombreDeUnTerceroGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private JLabel label;
	
	private JTextField CUITDeUnTercero;
	private JTextField razonSocial = new JTextField();
	
	private Insets insetLabel = new Insets(0,30,0,0);
	private Insets insetCampo = new Insets(0,0,0,30);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 16);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private Dimension dimensionCampo = new Dimension(150, 25);	
	
	private Color colorFondoCampoNoEditable = Color.decode("#f5f5f5");
	
	TextPrompt fondoJTextField;
	
	private GestorResponsableDePago gestorResponsable;
	
	private ResponsableDePagoDTO responsablePagoDTO;
	
	boolean excepcionNoExisteResponsable = false;
	
	public PanelFacturarANombreDeUnTerceroGroupBox() {
		
		gestorResponsable = GestorResponsableDePago.getInstance();
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.decode("#e0e0e0"), 1), " Facturar a nombre de un tercero", 0, 1, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.WEST;	c.gridy = 0;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;	
		
		label = new JLabel("CUIT de un tercero");	label.setFont(fuenteLabelCampo);	c.gridx = 0; this.add(label, c);
		
			c.fill = GridBagConstraints.HORIZONTAL;	c.insets = insetCampo; c.anchor = GridBagConstraints.CENTER;
		
		//CUITDeUnTercero = new JTextField(); 
			try {
				MaskFormatter mascaraCuit = new MaskFormatter("##'-########'-#");
				mascaraCuit.setValueContainsLiteralCharacters(false);
				CUITDeUnTercero = new JFormattedTextField(mascaraCuit);
		    	
		    }catch (ParseException e) {
		    	e.printStackTrace();
		    }
		CUITDeUnTercero.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
				  public void changedUpdate(DocumentEvent e) {
				  }
				  public void removeUpdate(DocumentEvent e)  {
				  }
				  public void insertUpdate(DocumentEvent e) {
					  
					  if(!CUITDeUnTercero.getText().contains(" ")) {	//Si no contiene espacios, el CUIT esta escrito completamente
						  
						  try {
							excepcionNoExisteResponsable = false;  
							  
							responsablePagoDTO = gestorResponsable.buscarResponsableDePago(CUITDeUnTercero.getText());
							
							razonSocial.setText(responsablePagoDTO.getRazonSocial());
							
							System.out.println(responsablePagoDTO.getCuit());
							
						} catch (NoExisteResponsableCuitException e1) {
							
							excepcionNoExisteResponsable = true;
						}
						 
					  }
					  else {
						  
						  razonSocial.setText("");
						  excepcionNoExisteResponsable = false;
					  }
				  }
			});
		CUITDeUnTercero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		CUITDeUnTercero.setFont(fuenteLabelCampo);	CUITDeUnTercero.setBorder(bordeCampo);	
		fondoJTextField = new TextPrompt("Ingrese los 11 dígitos del número de CUIT", CUITDeUnTercero); fondoJTextField.setForeground(Color.GRAY);
		c.gridx = 1; 	CUITDeUnTercero.setMinimumSize(dimensionCampo);	CUITDeUnTercero.setPreferredSize(dimensionCampo);
		this.add(CUITDeUnTercero, c); 		
		
			c.insets = insetLabel;	c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.NONE;
			
		label = new JLabel("Razón social");	label.setFont(fuenteLabelCampo);	c.gridx = 0;	c.gridy = 1; this.add(label, c);
		
			c.insets = insetCampo;	c.fill = GridBagConstraints.HORIZONTAL;	c.anchor = GridBagConstraints.CENTER;
		
		razonSocial.setFont(fuenteLabelCampo);	razonSocial.setBorder(bordeCampo);	razonSocial.setEditable(false);		razonSocial.setFocusable(false);
		razonSocial.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; razonSocial.setMinimumSize(dimensionCampo);	razonSocial.setPreferredSize(dimensionCampo);	
		this.add(razonSocial, c); 	

	}
	
	public void CUITNoEsVacio() throws InputVacioException{
		
		if(this.CUITDeUnTercero.getText().contains(" "))	//Por el formato que tiene
		{
			throw new InputVacioException();
		}
	}
	
	public String getCUIT(){
		return this.CUITDeUnTercero.getText();
	}
	
	public void setRazonSocial(String s) {
		
		this.razonSocial.setText(s);
	}
	
	public boolean getExcepcionNoExisteResponsable() {
		return this.excepcionNoExisteResponsable;
	}

	public ResponsableDePagoDTO getResponsableDePago() {
		
		return this.responsablePagoDTO;
	}
}
