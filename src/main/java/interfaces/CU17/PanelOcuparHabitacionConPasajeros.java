package main.java.interfaces.CU17;

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
import javax.swing.JPanel;

import main.java.dtos.PasajeroDTO;
import main.java.enums.TipoMensaje;
import main.java.excepciones.InputInvalidaException;
import main.java.excepciones.SinResultadosException;
import main.java.gestores.GestorPasajero;
import main.java.interfaces.CU02.PanelGestionarPasajeroTabla;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelOcuparHabitacionConPasajeros extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	// en este panel estan los botones y los dos otros paneles
	private PanelOcuparHabitacionBusqueda panelOcuparHabitacionBusqueda;
	private PanelOcuparHabitacionTabla panelOcuparHabitacionTabla;
	
	private PanelGestionarPasajeroTabla prueba;
	
	private PanelPasajerosSeleccionadosGroupBox panelPasajerosSeleccionadosGroupBox;
	private PanelInformacionGroupBox panelInformacionGroupBox;

	private JFrame frameActual;
	
	public GestorPasajero gestorPasajero;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeNoExistePasajeroBuscar = "<html><p>No existe ningún pasajero con los criterios de búsqueda"
														+ " seleccionados. ¿Desea agregar un nuevo pasajero?</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeNoExistePasajeroBuscar = new Mensaje(2, textoMensajeNoExistePasajeroBuscar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoModificarPasajero = 	"<html><p>Modificar pasajero en proximas versiones</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeModificarPasajero = new Mensaje(4, textoModificarPasajero, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoMensajeNoExistePasajeroSiguiente = "<html><p>No seleccionó ningún pasajero. ¿Desea agregar un nuevo pasajero?</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeNoExistePasajeroSiguiente = new Mensaje(3, textoMensajeNoExistePasajeroSiguiente, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private JButton buscar;
	private JButton cancelar;
	private JButton siguiente;

	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,30,0,0);
	private Insets insetPanelPasajerosSeleccionados = new Insets(0,0,0,10);
	private Insets insetPanelInformacion = new Insets(0,0,0,10);
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	public PanelOcuparHabitacionConPasajeros(final FrameOcuparHabitacionConPasajeros frame) {
		gestorPasajero = GestorPasajero.getInstance();
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		panelOcuparHabitacionBusqueda = new PanelOcuparHabitacionBusqueda(frame);
		c.insets = insetPanelBusqueda;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelOcuparHabitacionBusqueda, c);
		
		c.weightx = 0.1; c.weighty = 0.0;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
		buscar = new JButton("Buscar");
		buscar.setMinimumSize(dimensionBoton);
		buscar.setPreferredSize(dimensionBoton);
		buscar.setBackground(Color.decode("#E0E0E0"));
		buscar.setFont(fuenteBoton);
		buscar.setBorder(bordeBoton);
		buscar.addActionListener(e -> {
			
				//panelOcuparHabitacionTabla.activarTabla();
				//panelPasajerosSeleccionadosGroupBox.activarTabla();
			PasajeroDTO filtros = panelOcuparHabitacionBusqueda.getFiltros();
			try{
				gestorPasajero.validarDatosBusqueda(filtros);
				Integer cantResultados = gestorPasajero.buscarCantidadPasajeros(filtros);
				
				//prueba.buscarResultados(filtros, cantResultados);
				panelOcuparHabitacionTabla.buscarResultados(filtros, cantResultados);
				
			}
			catch (InputInvalidaException exc) {
				// TODO falta mensaje de error
				exc.printStackTrace();
			}
			catch (SinResultadosException exc) {
				mensajeNoExistePasajeroBuscar.mostrar(getPanel(), frameActual);
			}	
		});
		c.anchor = GridBagConstraints.CENTER;		c.insets = new Insets(0,60,0,0);
		c.gridx = 1; c.gridy = 1;
		this.add(buscar, c);
		
		//TODO: Pasarle la habitacion (para numero y capacidad)
		panelPasajerosSeleccionadosGroupBox = new PanelPasajerosSeleccionadosGroupBox();	//Los cambie de orden para que al panel de abajo se le pase un panel no null
		c.insets = insetPanelPasajerosSeleccionados;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 2; c.gridy = 2;
		c.weightx = 0.3; c.weighty = 0.6;			this.add(panelPasajerosSeleccionadosGroupBox, c);	
		c.weightx = 0.1; c.weighty = 0.1;	
		c.fill = GridBagConstraints.NONE;
		
		panelOcuparHabitacionTabla = new PanelOcuparHabitacionTabla(panelPasajerosSeleccionadosGroupBox);	
		c.insets = insetPanelTabla;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 2;	c.gridwidth = 2;	c.gridheight = 2;
		c.weightx = 0.8; c.weighty = 0.8;			this.add(panelOcuparHabitacionTabla, c);
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;	c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		
		panelInformacionGroupBox = new PanelInformacionGroupBox();
		c.insets = insetPanelInformacion;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 2; c.gridy = 3;
		c.weightx = 0.3; c.weighty = 0.2;			this.add(panelInformacionGroupBox, c);
		c.weightx = 0.1; c.weighty = 0.1;	
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
		c.gridx = 0; c.gridy = 4;
		this.add(cancelar, c);

		siguiente = new JButton("Siguiente");
		siguiente.setMinimumSize(dimensionBoton);
		siguiente.setPreferredSize(dimensionBoton);
		siguiente.setBackground(Color.decode("#E0E0E0"));
		siguiente.setFont(fuenteBoton);
		siguiente.setBorder(bordeBoton);
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameActual.setEnabled(false);	//Por si quiere cagar otro pasajero
				new FrameMenuOcuparHabitacion(frame);
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 2; c.gridy = 4;
		this.add(siguiente, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	

	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si cancela, vuelve al frame anterior
			frameActual.dispose();
			new FrameOcuparHabitacion();	//TODO: Ver si no es mejor mostrar el frame anterior en vez de uno nuevo
			break;
		case 2:	//Si no se encontro ningún pasajero, va a la pantalla de AltaPasajero
			//frameActual.dispose();
			//frameAltaPasajero = new FrameAltaPasajero();
			break;
		case 3:	//Si no se seleccionó ningún pasajero, va a la pantalla de AltaPasajero
			//frameActual.dispose();
			//frameAltaPasajero = new FrameAltaPasajero();	
			break;		
		}
	}
	
	public void confirmoCancelar(Integer idMensaje) {
		
		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}
}
