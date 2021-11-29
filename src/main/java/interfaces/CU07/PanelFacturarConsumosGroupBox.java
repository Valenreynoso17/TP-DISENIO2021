package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.interfaces.TextPrompt;
import main.java.interfaces.CU02.PanelPaginacion;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.JTextFieldLimitado;
import main.java.interfaces.clasesExtra.ModeloTablaConsumos;
import main.java.interfaces.clasesExtra.ModeloTablaPasajeros;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelFacturarConsumosGroupBox extends JPanel{
	
	private JLabel label;
	
	private JTextField razonSocial;
	private JTextField fecha;
	private JTextField cuit;
	private JTextField posicionFrenteIVA;
	private JTextField tipoFactura;
	
	private JTextField subtotal;
	private JTextField IVA;
	private JTextField totalAPagar;
	
	private JTable tabla;
	private ModeloTablaConsumos miModelo;
	
	private Vector filaSeleccionada = null;
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(0, 0, 0, 0);	//Espacios en blanco para acomodar los componentes
	private Insets insetLabel = new Insets(0,0,0,0);	
	private Insets insetCampo = new Insets(0,0,0,0);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	
	private double pesoXLabel = 0.1;
	private double pesoYLabel = 0.1;
	private double pesoXCampo = 0.1;
	private double pesoYCampo = 0.1;
	
	private Dimension dimensionCampo = new Dimension(120, 25);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	public PanelFacturarConsumosGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Facturar", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.CENTER;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Razón social");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		razonSocial = new JTextField(); 
		razonSocial.setText("REEMPLAZAR");
		razonSocial.setFont(fuenteLabelCampo);	razonSocial.setBorder(bordeCampo);	razonSocial.setEditable(false);
		c.gridx = 1; c.gridy = 0;	razonSocial.setMinimumSize(dimensionCampo); razonSocial.setPreferredSize(dimensionCampo);	
		this.add(razonSocial, c); 
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Fecha");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;;
		
		fecha = new JTextField(); 
		fecha.setText("REEMPLAZAR");
		fecha.setFont(fuenteLabelCampo);	fecha.setBorder(bordeCampo);	fecha.setEditable(false);
		c.gridx = 3; c.gridy = 0;	fecha.setMinimumSize(dimensionCampo);	fecha.setPreferredSize(dimensionCampo);	
		this.add(fecha, c); 	
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("CUIT");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		cuit = new JTextField(); 
		cuit.setText("REEMPLAZAR");
		cuit.setFont(fuenteLabelCampo);	cuit.setBorder(bordeCampo);	cuit.setEditable(false);
		c.gridx = 1; c.gridy = 1;	cuit.setMinimumSize(dimensionCampo);	cuit.setPreferredSize(dimensionCampo);	
		this.add(cuit, c); 
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Pos frente al IVA");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 1;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		posicionFrenteIVA = new JTextField(); 
		posicionFrenteIVA.setText("REEMPLAZAR");
		posicionFrenteIVA.setFont(fuenteLabelCampo);	posicionFrenteIVA.setBorder(bordeCampo);	posicionFrenteIVA.setEditable(false);
		c.gridx = 3; c.gridy = 1;	posicionFrenteIVA.setMinimumSize(dimensionCampo);	posicionFrenteIVA.setPreferredSize(dimensionCampo);	
		this.add(posicionFrenteIVA, c); 
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Tipo de factura");	label.setFont(fuenteLabelCampo);	c.gridx = 1; c.gridy = 2;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo; 
		
		tipoFactura = new JTextField(); 
		tipoFactura.setText("REEMPLAZAR");
		tipoFactura.setFont(fuenteLabelCampo);	tipoFactura.setBorder(bordeCampo);	tipoFactura.setEditable(false);
		c.gridx = 2; c.gridy = 2;	tipoFactura.setMinimumSize(dimensionCampo);	tipoFactura.setPreferredSize(dimensionCampo);	
		this.add(tipoFactura, c); 
		
		//Aca empiezan las configuraciones para la tabla
		
//		miModelo = new ModeloTablaConsumos();
//		
//		tabla = new JTable(miModelo);
//		tableContainer = new JScrollPane(tabla);
//		
//		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
//		
//		tabla.setRowSelectionAllowed(true);
//		tabla.setColumnSelectionAllowed(false);
//		
//		tabla.setFocusable(false); //Para que no seleccione una sola columna
//		
//		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tamaño de las columnas
//		
//		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		
//		tabla.setAutoCreateRowSorter(true);	//Para que se ordenen
//		
//		tabla.addMouseListener(new MouseAdapter() {
//			public void mouseReleased(MouseEvent e) {				
//				filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
//				nroFilaSeleccionada = tabla.getSelectedRow();
//			}
//		});
//		
//		//PARA CENTRAR
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setBOTHAlignment( JLabel.CENTER );
//		tabla.setDefaultRenderer(Object.class, centerRenderer);
//		
//		tabla.setBackground(Color.white);
//		tabla.setGridColor(Color.white);
//		//this.add(tableContainer, BorderLayout.CENTER);
//		c.fill = GridBagConstraints.BOTH;
//		//c.anchor = GridBagConstraints.CENTER;
//		c.insets = insetTabla;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
//		c.gridwidth = 4;
//		c.gridx = 0;
//		c.gridy = 3;
//		this.add(tableContainer, c);
//		
//		c.weightx = 0.1;
//		c.weighty = 0.1;
//		c.gridwidth = 1;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Subtotal");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		subtotal = new JTextField(); 
		subtotal.setText("REEMPLAZAR");
		subtotal.setFont(fuenteLabelCampo);	subtotal.setBorder(bordeCampo);	subtotal.setEditable(false);
		c.gridx = 3; c.gridy = 4;	subtotal.setMinimumSize(dimensionCampo);	subtotal.setPreferredSize(dimensionCampo);	
		this.add(subtotal, c); 
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("IVA (21%)");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 5;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		IVA = new JTextField(); 
		IVA.setText("REEMPLAZAR");
		IVA.setFont(fuenteLabelCampo);	IVA.setBorder(bordeCampo);	IVA.setEditable(false);
		c.gridx = 3; c.gridy = 5;	IVA.setMinimumSize(dimensionCampo);	IVA.setPreferredSize(dimensionCampo);	
		this.add(IVA, c); 
	
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Total a pagar");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 6;	this.add(label, c);
		
			c.fill = GridBagConstraints.BOTH; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampo;
		
		totalAPagar = new JTextField(); 
		totalAPagar.setText("REEMPLAZAR");
		totalAPagar.setFont(fuenteLabelCampo);	totalAPagar.setBorder(bordeCampo);	totalAPagar.setEditable(false);
		c.gridx = 3; c.gridy = 6;	totalAPagar.setMinimumSize(dimensionCampo);	totalAPagar.setPreferredSize(dimensionCampo);	
		this.add(totalAPagar, c); 
	}

}
