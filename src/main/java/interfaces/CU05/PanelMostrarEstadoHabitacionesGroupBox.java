package main.java.interfaces.CU05;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
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

import main.java.excepciones.FechaInvalidaException;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.clasesExtra.TextPrompt;

public class PanelMostrarEstadoHabitacionesGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JLabel labelFechaDesdeVacio;		//Muestran mensaje "Campo incompleto"
	private JLabel labelFechaHastaVacio;
	
	private JLabel labelFechaDesdeInvalida;		//Muestran mensaje "Fecha inválida"
	private JLabel labelFechaHastaInvalida;
	
	private JTextField fechaDesde;
	private JTextField fechaHasta;
	
	private Insets insetLabel = new Insets(0,0,0,20);
	private Insets insetCampo = new Insets(0,20,0,0);
	private Insets insetLabelError = new Insets(0,0,0,60);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	private Font fuenteLabelError = new Font("SourceSansPro", Font.PLAIN, 10);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private Dimension dimensionCampo = new Dimension(250, 30);
	
	TextPrompt fondoJTextField;
	
	public PanelMostrarEstadoHabitacionesGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Mostrar Estado Habitaciones", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.EAST;	c.gridx = 0;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;
	
		label = new JLabel("Fecha desde");	label.setFont(fuenteLabelCampo); c.gridy = 0;	this.add(label, c);
			
			c.anchor = GridBagConstraints.NORTHEAST; c.gridx = 1;	c.insets = insetLabelError;	
		
				labelFechaDesdeVacio = new JLabel();	labelFechaDesdeVacio.setFont(fuenteLabelError); 
				labelFechaDesdeVacio.setText(" Campo incompleto ");
				labelFechaDesdeVacio.setOpaque(true);	
				labelFechaDesdeVacio.setBackground(Color.decode("#cc0000"));
				labelFechaDesdeVacio.setForeground(Color.WHITE);
				this.add(labelFechaDesdeVacio, c);
				
				labelFechaDesdeVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
				
				labelFechaDesdeInvalida = new JLabel();	labelFechaDesdeInvalida.setFont(fuenteLabelError); 
				labelFechaDesdeInvalida.setText(" Fecha inválida ");
				labelFechaDesdeInvalida.setOpaque(true);	
				labelFechaDesdeInvalida.setBackground(Color.decode("#cc0000"));
				labelFechaDesdeInvalida.setForeground(Color.WHITE);
				this.add(labelFechaDesdeInvalida, c);
				
				labelFechaDesdeInvalida.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;  c.insets = insetCampo;	
		
		try {
			MaskFormatter mascaraFecha = new MaskFormatter("##'/##'/####");
			fechaDesde = new JFormattedTextField(mascaraFecha);
	    	
	    }catch (ParseException e) {
	    	e.printStackTrace();
	    }
		fechaDesde.setFont(fuenteLabelCampo);	fechaDesde.setBorder(bordeCampo);
		fechaDesde.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				labelFechaDesdeVacio.setVisible(false);
				labelFechaDesdeInvalida.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				labelFechaDesdeVacio.setVisible(false);
				labelFechaDesdeInvalida.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				labelFechaDesdeVacio.setVisible(false);
				labelFechaDesdeInvalida.setVisible(false);
			  }
		});
		c.gridx = 1;	fechaDesde.setMinimumSize(dimensionCampo);	fechaDesde.setPreferredSize(dimensionCampo);	fechaDesde.setMaximumSize(dimensionCampo);
		this.add(fechaDesde, c);
		
			c.anchor = GridBagConstraints.EAST;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;
		
		label = new JLabel("Fecha hasta");	label.setFont(fuenteLabelCampo); c.gridx = 2;;	this.add(label, c);
			
			c.anchor = GridBagConstraints.NORTHEAST; c.gridx = 3;	c.insets = insetLabelError;	
		
				labelFechaHastaVacio = new JLabel();	labelFechaHastaVacio.setFont(fuenteLabelError); 
				labelFechaHastaVacio.setText(" Campo incompleto ");
				labelFechaHastaVacio.setOpaque(true);	
				labelFechaHastaVacio.setBackground(Color.decode("#cc0000"));
				labelFechaHastaVacio.setForeground(Color.WHITE);
				this.add(labelFechaHastaVacio, c);
				
				labelFechaHastaVacio.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
				
				labelFechaHastaInvalida = new JLabel();	labelFechaHastaInvalida.setFont(fuenteLabelError); 
				labelFechaHastaInvalida.setText(" Fecha inválida ");
				labelFechaHastaInvalida.setOpaque(true);	
				labelFechaHastaInvalida.setBackground(Color.decode("#cc0000"));
				labelFechaHastaInvalida.setForeground(Color.WHITE);
				this.add(labelFechaHastaInvalida, c);
				
				labelFechaHastaInvalida.setVisible(false);	//No se va a mostrar a menos que el metodo validar lo indique
		
			c.anchor = GridBagConstraints.WEST;  c.insets = insetCampo;	
		
		//fechaHasta = new JTextField(); 
			try {
				MaskFormatter mascaraFecha = new MaskFormatter("##'/##'/####");
				fechaHasta = new JFormattedTextField(mascaraFecha);
		    	
		    }catch (ParseException e) {
		    	e.printStackTrace();
		    }
		fechaHasta.setFont(fuenteLabelCampo);	fechaHasta.setBorder(bordeCampo);	
		fechaHasta.getDocument().addDocumentListener(new DocumentListener() {	//Para que desaparezca el mensaje al presionar una tecla
			  public void changedUpdate(DocumentEvent e) {
				labelFechaHastaVacio.setVisible(false);
				labelFechaHastaInvalida.setVisible(false);
			  }
			  public void removeUpdate(DocumentEvent e)  {
				labelFechaHastaVacio.setVisible(false);
				labelFechaHastaInvalida.setVisible(false);
			  }
			  public void insertUpdate(DocumentEvent e) {
				labelFechaHastaVacio.setVisible(false);
				labelFechaHastaInvalida.setVisible(false);
			  }
		});
		c.gridx = 3;	fechaHasta.setMinimumSize(dimensionCampo);	fechaHasta.setPreferredSize(dimensionCampo);	fechaHasta.setMaximumSize(dimensionCampo);
		this.add(fechaHasta, c);

	}
	

	public void inputNoEsVacia() throws InputVacioException{
		
		labelFechaDesdeVacio.setVisible(false);
		labelFechaHastaVacio.setVisible(false);
		
		String inputsVacios = "";
		boolean alMenosUnoVacio = false;

		if(this.fechaDesde.getText().contains(" ")) {	//Por el formato que tiene
			inputsVacios += "d";
			alMenosUnoVacio = true;
		}
			
		if(this.fechaHasta.getText().contains(" ")) {	//Por el formato que tiene
			inputsVacios += "h";
			alMenosUnoVacio = true;
		}
		
		if(alMenosUnoVacio) {
			throw new InputVacioException(inputsVacios);
		}
	}
	
	public void inputEsValida() throws FechaInvalidaException{
		
		labelFechaDesdeInvalida.setVisible(false);
		labelFechaHastaInvalida.setVisible(false);

		String inputsInvalidos = "";
		boolean alMenosUnoInvalido = false;

		if(!this.esValidaFecha(fechaDesde)) {
			inputsInvalidos += "d";
			alMenosUnoInvalido = true;
		}
			
		if(!this.esValidaFecha(fechaHasta)) {
			inputsInvalidos += "h";
			alMenosUnoInvalido = true;
		}
		if(!this.fechaHastaMayorAFechaDesde()) {	
			
			inputsInvalidos = "dh";
			alMenosUnoInvalido = true;
		}
		
		if(alMenosUnoInvalido) {
			throw new FechaInvalidaException(inputsInvalidos);
		}
	}
	
	private boolean fechaHastaMayorAFechaDesde() {
		
		boolean resultado = true;
		String fechaDesdeString = fechaDesde.getText();
		String fechaHastaString = fechaHasta.getText();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		try {
			
			LocalDate localDateDesde = LocalDate.parse(fechaDesdeString, formatter);	
			LocalDate localDateHasta = LocalDate.parse(fechaHastaString, formatter);	
			
			//Si DESDE es MAYOR a HASTA --> Está mal // Si el periodo entre las fechas es mayor a 2 meses -> Está mal
			if(localDateDesde.isAfter(localDateHasta) || Period.between(localDateDesde, localDateHasta).getMonths() >= 2) {	
				
				resultado = false;
			}
			
		}
		catch(DateTimeParseException e) {
			
			resultado = false;
		}

		return resultado;
	}
	
	private boolean esValidaFecha(JTextField fecha) {
		
		boolean resultado = true;
		String fechaString = fecha.getText();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

		try {
			
			LocalDate localDate = LocalDate.parse(fechaString, formatter);	//Si se puede convertir a LocalDate, es una fecha válida
			
			if(!fechaEnRango(localDate)) {
				
				resultado = false;
			}
			
		}
		catch(DateTimeParseException e) {
			resultado = false;
		}
		
		return resultado;
	}
	
	private boolean fechaEnRango(LocalDate fecha) {

			LocalDate fechaMinima = LocalDate.now().minusDays(1);	//Fecha mínima (hoy)
		return (fecha.isAfter(fechaMinima));
	}

	public void colocarLabelVacio(String inputs) {
		
		if(inputs.contains("d")) {
			labelFechaDesdeVacio.setVisible(true);
		}
		if(inputs.contains("h")) {
			labelFechaHastaVacio.setVisible(true);
		}
		
	}
	
	public void colocarLabelInvalido(String inputs) {
		
		if(inputs.contains("d")) {
			labelFechaDesdeInvalida.setVisible(true);
		}
		if(inputs.contains("h")) {
			labelFechaHastaInvalida.setVisible(true);
		}
	
	}
	
	public LocalDate getFechaDesde() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDate = LocalDate.parse(this.fechaDesde.getText(), formatter);
		
		return localDate;
	}
	
	public LocalDate getFechaHasta() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDate = LocalDate.parse(this.fechaHasta.getText(), formatter);
		
		return localDate;
	}

}