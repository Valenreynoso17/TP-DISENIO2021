package main.java.interfaces.CU04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import main.java.dtos.LocalidadDTO;
import main.java.dtos.PaisDTO;
import main.java.dtos.ProvinciaDTO;
import main.java.enums.TipoDocumento;
import main.java.interfaces.TextPrompt;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelConfirmarDatosHabitacionGroupBox extends JPanel{
	
	//private PanelConDatosDeHabitaciones panelConDatosDeHabitaciones = new PanelConDatosDeHabitaciones();
	
	private JLabel labelInvisible = new JLabel("");
	
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox2 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox3 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox4 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox5 = new PanelNroHabitacionGroupBox();
	private PanelNroHabitacionGroupBox panelNroHabitacionGroupBox6 = new PanelNroHabitacionGroupBox();
	
	private Insets insetPanelesDatosHabitacion = new Insets(10,10,10,10);
	
	public PanelConfirmarDatosHabitacionGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelesDatosHabitacion;;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL; 	
		c.weightx = 0.1;	c.weighty = 0.1;
		
		c.gridx = 0; c.gridy = 0;
		this.add(panelNroHabitacionGroupBox, c);
		
		c.gridx = 1; c.gridy = 0;
		this.add(panelNroHabitacionGroupBox2, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 0; c.gridy = 1;
		this.add(panelNroHabitacionGroupBox3, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 1; c.gridy = 1;
		this.add(panelNroHabitacionGroupBox4, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 0; c.gridy = 2;
		this.add(panelNroHabitacionGroupBox5, c);
		//this.add(labelInvisible, c);
		
		c.gridx = 1; c.gridy = 2;
		//this.add(panelNroHabitacionGroupBox6, c);
		//this.add(labelInvisible, c);
		
	}

}
