package main.java.interfaces.CU17;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.dtos.HabitacionDTO;

public class PanelInformacionGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel texto;
	private JLabel numeroHabitacion;
	private JLabel personasSeleccionadas;
	
	private String textoInfo = "<html>La primera persona seleccionada se convierte<br/> en el responsable de la habitación.<html>";
	
	private Insets insetLabel = new Insets(0,20,0,0);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 20);	
	private Font fuenteLabelCampo = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private HabitacionDTO habitacion;
	
	public PanelInformacionGroupBox(HabitacionDTO habitacion) {
		this.habitacion = habitacion;
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Información", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.WEST;	c.gridx = 0;	c.weightx = 0.1; c.weighty = 0.1;	c.insets = insetLabel;	
		
		texto = new JLabel();	
		texto.setText(textoInfo);
		texto.setFont(fuenteLabelCampo);	c.gridy = 0; this.add(texto, c);
		
		numeroHabitacion = new JLabel();	
		numeroHabitacion.setText("Habitación número: " + habitacion.getNroHabitacion());	
		numeroHabitacion.setFont(fuenteLabelCampo);	c.gridy = 1; this.add(numeroHabitacion, c);
		
		personasSeleccionadas = new JLabel();
		personasSeleccionadas.setText("X/" + habitacion.getTipo().getCapacidad() + " personas seleccionadas");	
		personasSeleccionadas.setFont(fuenteLabelCampo);	c.gridy = 2; this.add(personasSeleccionadas, c);
	}
}
