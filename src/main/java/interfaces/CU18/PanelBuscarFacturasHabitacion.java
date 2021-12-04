package main.java.interfaces.CU18;

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
import main.java.enums.TipoMensaje;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelBuscarFacturasHabitacion extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelHabitacionAPagarGroupBox panelHabitacionAPagarGroupBox = new PanelHabitacionAPagarGroupBox();
	private PanelResultadosDeBusquedaGroupBox panelResultadosDeBusquedaGroupBox = new PanelResultadosDeBusquedaGroupBox();
	
	private JButton buscar;
	private JButton siguiente;
	private JButton cancelar;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoHabitacionInexistente = "<html><p>El número de habitación no se corresponde con ninguna habitación en el sistema.</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeHabitacionInexistente = new Mensaje(2, textoHabitacionInexistente, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoHabitacionSinFacturasPendientes = "<html><p>La habitación no tiene facturas pendientes de pago.</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeHabitacionSinFacturasPendientes = new Mensaje(3, textoHabitacionSinFacturasPendientes, TipoMensaje.ERROR, "Aceptar", null);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,30,0,30);
	
	private FrameBuscarFacturasHabitacion frameActual;
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelBuscarFacturasHabitacion(final FrameBuscarFacturasHabitacion frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelBusqueda;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelHabitacionAPagarGroupBox, c);
		
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
		buscar.addActionListener(e -> {
			
			try{
					this.panelHabitacionAPagarGroupBox.inputNoEsVacia();
////				gestorPasajero.validarDatosBusqueda(filtros);	//TODO: Buscar si la habitacion tiene facturas y conforme a eso tirar las excepciones
////				Integer cantResultados = gestorPasajero.buscarCantidadPasajeros(filtros);
////				
////				panelGestionarPasajeroTabla.buscarResultados(filtros, cantResultados);
//				
			}
			catch(InputVacioException exc) {
				
				this.panelHabitacionAPagarGroupBox.colocarLabelIncompleto();
			}
//			catch (HabitacionInexistenteException exc) {
//				mensajeHabitacionInexistente.mostrar(getPanel(), frame);
//				exc.printStackTrace();
//			}
//			catch (HabitacionSinFacturasPendientesException exc) {
//				mensajeHabitacionSinFacturasPendientes.mostrar(getPanel(), frameActual);
//			}	
		});
		c.anchor = GridBagConstraints.CENTER;		//c.insets = new Insets(0,60,10,0);
		c.gridx = 1; c.gridy = 1;
		this.add(buscar, c);
		
		c.insets = insetPanelTabla;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 2;	c.gridwidth = 3;
		c.weightx = 0.8; c.weighty = 0.8;			this.add(panelResultadosDeBusquedaGroupBox, c);
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
				frame.dispose();
				new FrameIngresarDatosPago();
			}
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
//			}
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
			new FrameMenuPrincipal();	
			break;
		case 2:	//Si la habitación no existe, simplemente muestra el mensaje
			break;
		case 3:	//Si la habitación no posee facturas, simplemente muestra el mensaje
			break;		
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}

}