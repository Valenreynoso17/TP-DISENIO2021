package main.java.interfaces.CU02;

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
import main.java.interfaces.CU11.FrameAltaPasajero;
import main.java.interfaces.CU11.PanelAltaPasajeroDatos;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelGestionarPasajero extends JPanel implements PanelPermiteMensajes{
	// en este panel estan los botones y los dos otros paneles
	private PanelGestionarPasajeroBusqueda panelGestionarPasajeroBusqueda;
	private PanelGestionarPasajeroTabla panelGestionarPasajeroTabla;
	
	private FrameMenuPrincipal frameAnterior;
	private JFrame frameActual;
	private FrameAltaPasajero frameAltaPasajero;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeNoExistePasajeroBuscar = "<html><p>No existe ningún pasajero con los criterios de búsqueda"
														+ " seleccionados. ¿Desea agregar un nuevo pasajero?</p><html>";
	private Mensaje mensajeNoExistePasajeroBuscar = new Mensaje(2, textoMensajeNoExistePasajeroBuscar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeNoExistePasajeroSiguiente = "<html><p>No seleccionó ningún pasajero. ¿Desea agregar un nuevo pasajero?";
	private Mensaje mensajeNoExistePasajeroSiguiente = new Mensaje(3, textoMensajeNoExistePasajeroSiguiente, TipoMensaje.CONFIRMACION, "Si", "No");
	
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
				
				mensajeNoExistePasajeroBuscar.mostrar(getPanel(), frame);
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
				
				mensajeCancelar.mostrar(getPanel(), frame);
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
				
				mensajeNoExistePasajeroSiguiente.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 2; c.gridy = 3;
		this.add(siguiente, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	

	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si cancela, vuelve a MenuPrincipal
			frameActual.dispose();
			frameAnterior = new FrameMenuPrincipal();	
			break;
		case 2:	//Si no se encontro ningún pasajero, va a la pantalla de AltaPasajero
			frameActual.dispose();
			frameAltaPasajero = new FrameAltaPasajero();
			break;
		case 3:	//Si no se seleccionó ningún pasajero, va a la pantalla de AltaPasajero
			frameActual.dispose();
			frameAltaPasajero = new FrameAltaPasajero();	
			break;		
		}
	}
	
	public void confirmoCancelar(Integer idMensaje) {
		
		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}
}
