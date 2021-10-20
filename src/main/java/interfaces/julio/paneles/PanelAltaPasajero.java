package main.java.interfaces.julio.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.enmus.TipoMensaje;
import main.java.interfaces.julio.frames.FrameAltaPasajero;
import main.java.interfaces.julio.frames.FrameMenuPrincipal;
import main.java.interfaces.julio.otros.Mensaje;
import main.java.interfaces.julio.otros.PanelPermiteMensajes;
import main.java.interfaces.julio.otros.RoundedBorder;
import main.java.interfaces.nati.frames.FrameGestionarPasajero;

public class PanelAltaPasajero extends JPanel implements PanelPermiteMensajes{
	
	private PanelAltaPasajeroDatos panelDarAltaPasajero;
	
	private JButton cancelar;
	private JButton siguiente;

	private Insets insetPanel = new Insets(30,30,10,30);
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private FrameGestionarPasajero frameAnterior;
	private FrameAltaPasajero frameActual;
	private FrameMenuPrincipal frameMenuprincipal;
	
	public PanelAltaPasajero(final FrameAltaPasajero frame) {
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		panelDarAltaPasajero  = new PanelAltaPasajeroDatos(frame);
		c.insets = insetPanel;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 2;
		c.weightx = 0.5; c.weighty = 0.5;			this.add(panelDarAltaPasajero, c);
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
		cancelar = new JButton("Cancelar");
		cancelar.setMinimumSize(dimensionBoton);
		cancelar.setPreferredSize(dimensionBoton);
		cancelar.setBackground(Color.decode("#E0E0E0"));
		cancelar.setFont(fuenteBoton);
		cancelar.setBorder(bordeBoton);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pregunta = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
				Mensaje m = new Mensaje(getPanel(), frame, TipoMensaje.CONFIRMACION, pregunta, "Si", "No");
			}
		});
		c.anchor = GridBagConstraints.WEST;		c.insets = new Insets(0,60,10,0);
		c.gridx = 0; c.gridy = 1;
		this.add(cancelar, c);

		siguiente = new JButton("Siguiente");
		siguiente.setMinimumSize(dimensionBoton);
		siguiente.setPreferredSize(dimensionBoton);
		siguiente.setBackground(Color.decode("#E0E0E0"));
		siguiente.setFont(fuenteBoton);
		siguiente.setBorder(bordeBoton);
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(panelDarAltaPasajero.validar()) {
					
					String texto = "<html><p>El pasajero se agregó al sistema correctamente.</p><html>";
					Mensaje m = new Mensaje(getPanel(), frame, TipoMensaje.EXITO, texto, "Aceptar", null);
				}
				
				String texto = "<html><p>¡CUIDADO! El tipo y número de documento ya existen en el sistema.</p><html>";
				Mensaje m2 = new Mensaje(getPanel(), frame, TipoMensaje.EXITO, texto, "Aceptar Igualmente", "Corregir");
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 1; c.gridy = 1;
		this.add(siguiente, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}

	public void confirmoElMensaje() {
		frameActual.dispose();
		frameMenuprincipal = new FrameMenuPrincipal();	//Si se crea el pasajero, vuelve al MenuPrincipal
	}

	public void confirmoCancelar() {
		
		frameActual.dispose();
		frameAnterior = new FrameGestionarPasajero();	//Si cancela, vuelve a GestionarPasajero
	}

}

