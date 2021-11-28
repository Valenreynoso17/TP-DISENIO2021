package main.java.interfaces.CU04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.java.enums.TipoMensaje;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelReservaANombreDe extends JPanel{
	
	private PanelReservaANombreDeGroupBox panelReservaANombreDeGroupBox = new PanelReservaANombreDeGroupBox();
	
	private JButton aceptar;
	private JButton cancelar;
	
//	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
//	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanel = new Insets(30,40,0,40);
	
	private FrameReservaANombreDe frameActual;
	private FrameMenuPrincipal frameSiguiente;
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelReservaANombreDe(final FrameReservaANombreDe frame, final FrameConfirmarDatosHabitacion frameAnterior) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanel;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 2;
		c.weightx = 0.4; c.weighty = 0.6;			this.add(panelReservaANombreDeGroupBox, c);
		
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;
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
		c.anchor = GridBagConstraints.WEST;		c.insets = new Insets(0,60,10,0);
		c.gridx = 0; c.gridy = 1;
		this.add(cancelar, c);

		aceptar = new JButton("Aceptar");
		aceptar.setMinimumSize(dimensionBoton);
		aceptar.setPreferredSize(dimensionBoton);
		aceptar.setBackground(Color.decode("#E0E0E0"));
		aceptar.setFont(fuenteBoton);
		aceptar.setBorder(bordeBoton);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameAnterior.dispose();
				frameActual.dispose();
				frameSiguiente = new FrameMenuPrincipal();
//				try {
//					PasajeroDTO pasajero = panelGestionarPasajeroTabla.pasajeroSeleccionado();
//					
//					mensajeModificarPasajero.mostrar(getPanel(), frame);
//				}
//				catch (PasajeroNoSeleccionadoException exc) {
//					mensajeNoExistePasajeroSiguiente.mostrar(getPanel(), frame);
//				}
//				
//				//mensajeNoExistePasajeroSiguiente.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 1; c.gridy = 1;
		this.add(aceptar, c);
	}

}
