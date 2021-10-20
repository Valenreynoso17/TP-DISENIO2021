package main.java.interfaces.nati.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.enmus.TipoMensaje;
import main.java.interfaces.julio.frames.FrameAltaPasajero;
import main.java.interfaces.julio.frames.FrameMenuPrincipal;
import main.java.interfaces.julio.otros.Mensaje;
import main.java.interfaces.julio.otros.PanelPermiteMensajes;
import main.java.interfaces.julio.otros.RoundedBorder;
import main.java.interfaces.julio.paneles.PanelAltaPasajeroDatos;
import main.java.interfaces.nati.frames.FrameGestionarPasajero;

public class PanelGestionarPasajero extends JPanel implements PanelPermiteMensajes{
	// en este panel estan los botones y los dos otros paneles
	private PanelGestionarPasajeroBusqueda panelGestionarPasajeroBusqueda;
	private PanelGestionarPasajeroTabla panelGestionarPasajeroTabla;
	
	private FrameMenuPrincipal frameAnterior;
	private JFrame frameActual;
	private FrameAltaPasajero frameAltaPasajero;
	
	private JButton buscar;
	private JButton cancelar;
	private JButton siguiente;

	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,30,0,30);
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	public PanelGestionarPasajero(final FrameGestionarPasajero frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		panelGestionarPasajeroBusqueda = new PanelGestionarPasajeroBusqueda(frame);
		c.insets = insetPanelBusqueda;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelGestionarPasajeroBusqueda, c);
		
		c.weightx = 0.0; c.weighty = 0.0;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
		buscar = new JButton("Buscar");
		buscar.setMinimumSize(dimensionBoton);
		buscar.setPreferredSize(dimensionBoton);
		buscar.setBackground(Color.decode("#E0E0E0"));
		buscar.setFont(fuenteBoton);
		buscar.setBorder(bordeBoton);
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String texto = "<html><p>No existe ningún pasajero con los criterios de búsqueda"
						+ " seleccionados. ¿Desea agregar un nuevo pasajero?</p><html>";
				Mensaje m = new Mensaje(getPanel(), frame, TipoMensaje.CONFIRMACION, texto, "Si", "No");
			}
		});
		c.anchor = GridBagConstraints.CENTER;		//c.insets = new Insets(0,60,10,0);
		c.gridx = 1; c.gridy = 1;
		this.add(buscar, c);
		
		panelGestionarPasajeroTabla = new PanelGestionarPasajeroTabla(frame);
		c.insets = insetPanelTabla;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 2;	c.gridwidth = 3;
		c.weightx = 0.8; c.weighty = 0.8;			this.add(panelGestionarPasajeroTabla, c);
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
				
				String pregunta = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
				Mensaje m = new Mensaje(getPanel(), frame, TipoMensaje.CONFIRMACION, pregunta, "Si", "No");
			}
		});
		c.anchor = GridBagConstraints.WEST;		c.insets = new Insets(0,60,10,0);
		c.gridx = 0; c.gridy = 3;
		this.add(cancelar, c);

		siguiente = new JButton("Siguiente");
		siguiente.setMinimumSize(dimensionBoton);
		siguiente.setPreferredSize(dimensionBoton);
		siguiente.setBackground(Color.decode("#E0E0E0"));
		siguiente.setFont(fuenteBoton);
		siguiente.setBorder(bordeBoton);
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				if(panelDarAltaPasajero.validar()) {
//					
//					String texto = "<html><p>El pasajero se agregó al sistema correctamente.</p><html>";
//					//Mensaje m = new Mensaje(frame, TipoMensaje.ADVERTENCIA, pregunta3, "Aceptar", "Cancelar");
//					Mensaje m2 = new Mensaje(frame, TipoMensaje.EXITO, texto, "Aceptar", null);
//				}
				
//				frame.dispose();
//				frameAnteriorTrucho = new FrameMenuPrincipal();
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 2; c.gridy = 3;
		this.add(siguiente, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	

	public void confirmoElMensaje() {
		
		frameActual.dispose();
		frameAltaPasajero = new FrameAltaPasajero();
	}
	
	public void confirmoCancelar() {
		
		frameActual.dispose();
		frameAnterior = new FrameMenuPrincipal();
	}
}
