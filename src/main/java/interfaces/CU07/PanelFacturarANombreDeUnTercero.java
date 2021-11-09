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

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import main.java.enums.TipoMensaje;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelFacturarANombreDeUnTercero extends JPanel implements PanelPermiteMensajes{
	
	private JLabel label;
	
	private JTextField CUITDeUnTercero;
	private JTextField razonSocial = new JTextField();
	
	private JButton aceptar;
	private JButton cancelar;
	
	private String textoMensajeCUITVacio = "<html><p>El CUIT ingresado es vacío. ¿Desea dar de alta un nuevo responsable de pago?</p><html>";
	private Mensaje mensajeCUITVacio = new Mensaje(1, textoMensajeCUITVacio, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeNoExisteResponsable = "<html><p>El CUIT ingresado es inválido/No coincide con ningún responsable"
													+ " de pago del sistema. ¿Desea dar de alta un nuevo responsable de pago?</p><html>";
	private Mensaje mensajeNoExisteResponsable = new Mensaje(2, textoMensajeNoExisteResponsable, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private Insets insetLabel = new Insets(0,30,0,0);
	private Insets insetCampo = new Insets(0,0,0,30);
	private Insets insetCancelar = new Insets(0,30,0,0);
	private Insets insetAceptar = new Insets(0,0,0,30);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private Dimension dimensionCampo = new Dimension(150, 30);	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	private Color colorFondoCampoNoEditable = Color.decode("#f5f5f5");
	
	private FrameFacturar frameAnterior;
	private FrameFacturarANombreDeUnTercero frameActual;
	private FrameMenuPrincipal frameSiguiente;	//TODO: Cambiar
	
	TextPrompt fondoJTextField;
	
	public PanelFacturarANombreDeUnTercero(FrameFacturarANombreDeUnTercero frame, FrameFacturar frameA) {
		
		this.frameActual = frame;
		this.frameAnterior = frameA;
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Facturar a nombre de un tercero", 0, 1, fuenteGroupBox));
		
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
					  
					  if(CUITDeUnTercero.getText().equals("11-11111111-1")) {
						  //Si es igual a algun CUIT de un responsable de pago, muestra el nombre. Sino, cuando apriete "Aceptar" salta la validacion
						  System.out.println("IGUAL");
						  
						  razonSocial.setText("Juan Perez");
					  }
					  else {
						  
						  razonSocial.setText("");
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
		
		razonSocial.setFont(fuenteLabelCampo);	razonSocial.setBorder(bordeCampo);	razonSocial.setEditable(false);
		razonSocial.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; razonSocial.setMinimumSize(dimensionCampo);	razonSocial.setPreferredSize(dimensionCampo);	
		this.add(razonSocial, c); 	
		
		c.fill = GridBagConstraints.NONE;
		
		cancelar = new JButton("Cancelar");
		cancelar.setMinimumSize(dimensionBoton);
		cancelar.setPreferredSize(dimensionBoton);
		cancelar.setBackground(Color.decode("#E0E0E0"));
		cancelar.setFont(fuenteBoton);
		cancelar.setBorder(bordeBoton);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameAnterior.setEnabled(true);
				frameActual.dispose();
			}
		});
		c.anchor = GridBagConstraints.WEST;		c.insets = insetCancelar;
		c.gridx = 0; c.gridy = 2;
		this.add(cancelar, c);
		
		aceptar = new JButton("Aceptar");
		aceptar.setMinimumSize(dimensionBoton);
		aceptar.setPreferredSize(dimensionBoton);
		aceptar.setBackground(Color.decode("#E0E0E0"));
		aceptar.setFont(fuenteBoton);
		aceptar.setBorder(bordeBoton);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//mensajeCancelar.mostrar(getPanel(), frame);
				frameActual.dispose();
				frameAnterior.dispose();
				frameSiguiente = new FrameMenuPrincipal();
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = insetAceptar;
		c.gridx = 1; c.gridy = 2;
		this.add(aceptar, c);

	}

	@Override
	public void confirmoElMensaje(Integer idMensaje) {

		switch(idMensaje) {
		case 1:
		case 2:	//En ambos mensajes al apretar "Si" debería ir a "Dar de alta un nuevo responsable de pago"
			System.out.println("Dar de alta responsable de pago");
			break;	
		}
		
	}

	@Override
	public void confirmoCancelar(Integer idMensaje) {
		
		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}

}
