package main.java.interfaces.CU18;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelDatosDelPagoGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	//private List<PaisDTO> paises;
	//private List<ProvinciaDTO> provincias;
	//private List<LocalidadDTO> localidades;
	
	@SuppressWarnings("unused")
	private DefaultComboBoxModel<String> medioDePagoModel = new DefaultComboBoxModel<String>();	
	//private DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
	
	private JComboBox<String> medioDePago;
	private JComboBox<String> moneda;
	private JComboBox<String> tarjetaDebito;
	private JComboBox<String> tarjetaCredito;
	private JComboBox<String> banco;
	
	private JLabel label;
	
	private JTextField montoAdeudado;					//Campos de texto (no editables)
	private JTextField vuelto;
	private JTextField importe;
	private JTextField cotizacion;
	private JTextField nroDeCheque;
	private JTextField fechaCobro;
	private JTextField plaza;
	
	private double pesoXLabel = 0.3;
	private double pesoYLabel = 0.1;
	private double pesoXCampo = 0.3;
	private double pesoYCampo = 0.3;
	
	private Insets insetLabel = new Insets(0,30,0,0);	//Espacios en blanco para acomodar los componentes
	private Insets insetCampo = new Insets(0,10,0,10);
	
	private Dimension dimensionCampo = new Dimension(200, 30);
	
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);

	private Color colorFondoCampoNoEditable = Color.decode("#f5f5f5");
	private Color colorLetraListaDesplegable = Color.decode("#616161");
	
	public PanelDatosDelPagoGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Datos del pago", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER; c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
	
		label = new JLabel("Monto adeudado");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		montoAdeudado = new JTextField(); 	montoAdeudado.setText("$XXX.XX");	montoAdeudado.setEditable(false);
		montoAdeudado.setFont(fuenteLabelCampo);	montoAdeudado.setBorder(bordeCampo);	montoAdeudado.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; c.gridy = 0;	montoAdeudado.setMinimumSize(dimensionCampo);	montoAdeudado.setPreferredSize(dimensionCampo);	montoAdeudado.setMaximumSize(dimensionCampo);
		this.add(montoAdeudado, c); 
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Medio de pago");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
			
		medioDePago = new JComboBox<String>();	
		medioDePago.setFont(fuenteLabelCampo);	medioDePago.setForeground(colorLetraListaDesplegable);	medioDePago.setBackground(Color.WHITE);	
		medioDePago.addItemListener(event -> {
			
			//labelNumeroDocumentoFormatoInvalido.setVisible(false);
	    });
		medioDePago.addItem("-Seleccione");
		
		c.gridx = 3; c.gridy = 0;	medioDePago.setMinimumSize(dimensionCampo);	medioDePago.setPreferredSize(dimensionCampo);
		this.add(medioDePago, c);
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Vuelto");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		vuelto = new JTextField(); vuelto.setText("$0.00");	vuelto.setEditable(false);
		vuelto.setFont(fuenteLabelCampo);	vuelto.setBorder(bordeCampo);	vuelto.setBackground(colorFondoCampoNoEditable);
		c.gridx = 1; c.gridy = 1;	vuelto.setMinimumSize(dimensionCampo);	vuelto.setPreferredSize(dimensionCampo);	vuelto.setMaximumSize(dimensionCampo);
		this.add(vuelto, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Razón social");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 1;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		importe = new JTextField(); 
		importe.setFont(fuenteLabelCampo);	importe.setBorder(bordeCampo);	
		c.gridx = 3; c.gridy = 1;	importe.setMinimumSize(dimensionCampo);	importe.setPreferredSize(dimensionCampo);	importe.setMaximumSize(dimensionCampo);
		this.add(importe, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Moneda");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 2;	this.add(label, c);
		
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; 
			
		moneda = new JComboBox<String>();	
		moneda.setFont(fuenteLabelCampo);	moneda.setForeground(colorLetraListaDesplegable);	moneda.setBackground(Color.WHITE);	
		moneda.addItemListener(event -> {
			
			//labelNumeroDocumentoFormatoInvalido.setVisible(false);
	    });
		moneda.addItem("-Seleccione");
		
		c.gridx = 1; c.gridy = 2;	moneda.setMinimumSize(dimensionCampo);	moneda.setPreferredSize(dimensionCampo);
		this.add(moneda, c);
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Cotización");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 2;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		cotizacion = new JTextField(); 
		cotizacion.setFont(fuenteLabelCampo);	cotizacion.setBorder(bordeCampo);	
		c.gridx = 3; c.gridy = 2;	cotizacion.setMinimumSize(dimensionCampo);	cotizacion.setPreferredSize(dimensionCampo);	cotizacion.setMaximumSize(dimensionCampo);
		this.add(cotizacion, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Tarjeta débito");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 3;	this.add(label, c);
		
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
			
		tarjetaDebito = new JComboBox<String>();	
		tarjetaDebito.setFont(fuenteLabelCampo);	tarjetaDebito.setForeground(colorLetraListaDesplegable);	tarjetaDebito.setBackground(Color.WHITE);	
		tarjetaDebito.addItemListener(event -> {
			
			//labelNumeroDocumentoFormatoInvalido.setVisible(false);
	    });
		tarjetaDebito.addItem("-Seleccione");
		
		c.gridx = 1; c.gridy = 3;	tarjetaDebito.setMinimumSize(dimensionCampo);	tarjetaDebito.setPreferredSize(dimensionCampo);
		this.add(tarjetaDebito, c);
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Tarjeta crédito");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 3;	this.add(label, c);
		
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
			
		tarjetaCredito = new JComboBox<String>();	
		tarjetaCredito.setFont(fuenteLabelCampo);	tarjetaCredito.setForeground(colorLetraListaDesplegable);	tarjetaCredito.setBackground(Color.WHITE);	
		tarjetaCredito.addItemListener(event -> {
			
			//labelNumeroDocumentoFormatoInvalido.setVisible(false);
	    });
		tarjetaCredito.addItem("-Seleccione");
		
		c.gridx = 3; c.gridy = 3;	tarjetaCredito.setMinimumSize(dimensionCampo);	tarjetaCredito.setPreferredSize(dimensionCampo);
		this.add(tarjetaCredito, c);
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Nro. de cheque");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 4;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		nroDeCheque = new JTextField(); 
		nroDeCheque.setFont(fuenteLabelCampo);	nroDeCheque.setBorder(bordeCampo);	
		c.gridx = 1; c.gridy = 4;	nroDeCheque.setMinimumSize(dimensionCampo);	nroDeCheque.setPreferredSize(dimensionCampo);	nroDeCheque.setMaximumSize(dimensionCampo);
		this.add(nroDeCheque, c); 
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Banco");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
			
		banco = new JComboBox<String>();	
		banco.setFont(fuenteLabelCampo);	banco.setForeground(colorLetraListaDesplegable);	banco.setBackground(Color.WHITE);	
		banco.addItemListener(event -> {
			
			//labelNumeroDocumentoFormatoInvalido.setVisible(false);
	    });
		banco.addItem("-Seleccione");
		
		c.gridx = 3; c.gridy = 4;	banco.setMinimumSize(dimensionCampo);	banco.setPreferredSize(dimensionCampo);
		this.add(banco, c);
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Fecha cobro");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 5;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		//fechaCobro = new JTextField(); 
		try {
			MaskFormatter mascaraFechaNacimiento = new MaskFormatter("##'/##'/####");
			fechaCobro = new JFormattedTextField(mascaraFechaNacimiento);
	    	
	    }catch (ParseException e) {
	    	e.printStackTrace();
	    }
		fechaCobro.setFont(fuenteLabelCampo);	fechaCobro.setBorder(bordeCampo);
		fechaCobro.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				  //labelFechaNacimientoVacio.setVisible(false);
				  //labelFechaNacimientoFormatoInvalido.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				  //labelFechaNacimientoVacio.setVisible(false);
				  //labelFechaNacimientoFormatoInvalido.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  //labelFechaNacimientoVacio.setVisible(false);
				  //labelFechaNacimientoFormatoInvalido.setVisible(false);
			  }
		});
		fechaCobro.setFont(fuenteLabelCampo);	fechaCobro.setBorder(bordeCampo);	
		c.gridx = 1; c.gridy = 5;	fechaCobro.setMinimumSize(dimensionCampo);	fechaCobro.setPreferredSize(dimensionCampo);	fechaCobro.setMaximumSize(dimensionCampo);
		this.add(fechaCobro, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Plaza");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 5;	this.add(label, c);
	
			c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		plaza = new JTextField(); 
		plaza.setFont(fuenteLabelCampo);	plaza.setBorder(bordeCampo);	
		c.gridx = 3; c.gridy = 5;	plaza.setMinimumSize(dimensionCampo);	plaza.setPreferredSize(dimensionCampo);	plaza.setMaximumSize(dimensionCampo);
		this.add(plaza, c); 	
		
	}

}
