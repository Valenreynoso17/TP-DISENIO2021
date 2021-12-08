package main.java.interfaces.CU05;

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

import main.java.enums.EstadoHabitacion;
import main.java.enums.TipoMensaje;
import main.java.excepciones.ContieneFechasReservadasException;
import main.java.excepciones.FechaInvalidaException;
import main.java.excepciones.InputVacioException;
import main.java.excepciones.RangoNoSeleccionadoException;
import main.java.interfaces.CU17.PanelOcuparHabitacionConPasajeros;
import main.java.interfaces.MenuPrincipal.PanelMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.MensajeYaExistenReservas;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.frames.FramePrincipal;

public class PanelMostrarEstadoHabitaciones extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelMostrarEstadoHabitacionesGroupBox panelMostrarEstadoHabitacionesGroupBox = new PanelMostrarEstadoHabitacionesGroupBox();
	private PanelResultadosDeBusquedaHabitacionesGroupBox panelResultadosDeBusquedaHabitacionesGroupBox = new PanelResultadosDeBusquedaHabitacionesGroupBox();
	
	private JButton buscar;
	private JButton siguiente;
	private JButton cancelar;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoNoExistenHabitacionesEnPeriodo = "<html><p>No existen habitaciones disponibles para el período seleccionado.</p><html>";
	private Mensaje mensajeNoExistenHabitacionesEnPeriodo = new Mensaje(2, textoNoExistenHabitacionesEnPeriodo, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoRangoNoSeleccionado = "<html><p>Por favor, seleccione al menos un rango de fechas para ocupar una habitación.</p><html>";
	private Mensaje mensajeRangoNoSeleccionado = new Mensaje(3, textoRangoNoSeleccionado, TipoMensaje.ERROR, "Aceptar", null);
	
	private MensajeYaExistenReservas mensajeYaExistenReservas = new MensajeYaExistenReservas(4);
	
	private Mensaje mensajeHabitacionConOcupacionOFS = new Mensaje(5, "", TipoMensaje.ERROR, "Aceptar", null);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,15,0,15);
	
	private FramePrincipal frameActual;
	private PanelMenuPrincipal panelAnterior;
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelMostrarEstadoHabitaciones(final FramePrincipal frame, PanelMenuPrincipal panelAnterior) {
		
		this.frameActual = frame;
		this.panelAnterior = panelAnterior;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelBusqueda;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelMostrarEstadoHabitacionesGroupBox, c);
		
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
					siguiente.setEnabled(false);
				
					panelResultadosDeBusquedaHabitacionesGroupBox.desactivarTabla();
					
//					panelResultadosDeBusquedaHabitacionesGroupBox.activarTabla();	//TODO: Cuando terminemos de probar, sacar estas dos lineas y descomentar las de abajo
//					siguiente.setEnabled(true);
				
					this.panelMostrarEstadoHabitacionesGroupBox.inputNoEsVacia();
					this.panelMostrarEstadoHabitacionesGroupBox.inputEsValida();
					
					panelResultadosDeBusquedaHabitacionesGroupBox.activarTabla(panelMostrarEstadoHabitacionesGroupBox.getFechaDesde(), panelMostrarEstadoHabitacionesGroupBox.getFechaHasta());
					
					siguiente.setEnabled(true);
			}
			catch(InputVacioException exc) {
				
				//this.panelMostrarEstadoHabitacionesGroupBox.colocarLabelVacio(exc.getInputsVacios());
				this.panelMostrarEstadoHabitacionesGroupBox.colocarLabelVacio();
			}
			catch (FechaInvalidaException exc) {

				//this.panelMostrarEstadoHabitacionesGroupBox.colocarLabelInvalido(exc.getFechasInvalidas());
				this.panelMostrarEstadoHabitacionesGroupBox.colocarLabelInvalido();
			}	
			
		});
		c.anchor = GridBagConstraints.CENTER;		//c.insets = new Insets(0,60,10,0);
		c.gridx = 1; c.gridy = 1;
		this.add(buscar, c);
		
		c.insets = insetPanelTabla;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 2;	c.gridwidth = 3;
		c.weightx = 0.8; c.weighty = 0.8;			this.add(panelResultadosDeBusquedaHabitacionesGroupBox, c);
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
		siguiente.setEnabled(false);			//Se habilita cuando se aprieta Buscar con campos validos
		siguiente.setMinimumSize(dimensionBoton);
		siguiente.setPreferredSize(dimensionBoton);
		siguiente.setBackground(Color.decode("#E0E0E0"));
		siguiente.setFont(fuenteBoton);
		siguiente.setBorder(bordeBoton);
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						panelResultadosDeBusquedaHabitacionesGroupBox.seleccionoUnRango();
					
						panelResultadosDeBusquedaHabitacionesGroupBox.validacionContieneFechasReservadas();
						
						frame.setNuevoPanel(new PanelOcuparHabitacionConPasajeros(frame, (PanelMostrarEstadoHabitaciones) getPanel(),
											panelResultadosDeBusquedaHabitacionesGroupBox.getHabitacion(), 
											panelResultadosDeBusquedaHabitacionesGroupBox.getFechaDesde(), 
											panelResultadosDeBusquedaHabitacionesGroupBox.getFechaHasta().plusDays(1)));
				}
				catch (RangoNoSeleccionadoException exc) {
					mensajeRangoNoSeleccionado.mostrar(getPanel(), frame);
				}
				catch (ContieneFechasReservadasException exc) {
					mensajeYaExistenReservas.mostrar(getPanel(), frame, exc.getReservasSeleccionadas());
				}
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
			frameActual.setNuevoPanel(panelAnterior);
			break;
		case 2:	//Si la habitación no existe, simplemente muestra el mensaje
			break;
		case 3:	//Si la habitación no posee facturas, simplemente muestra el mensaje
			break;		
		case 4:	//Si quiere OcuparIgualmente aunque existan reservas, se le pasa al panel los datos que necesita (y se sobreescribe la reserva)
			frameActual.setNuevoPanel(new PanelOcuparHabitacionConPasajeros(frameActual, this,
									  panelResultadosDeBusquedaHabitacionesGroupBox.getHabitacion(), 
									  panelResultadosDeBusquedaHabitacionesGroupBox.getFechaDesde(), 
									  panelResultadosDeBusquedaHabitacionesGroupBox.getFechaHasta().plusDays(1)));
			break;
		case 5:	//Si la habitacion está ocupada o fuera de servicio, simplemente mostrar el mensaje
			break;	
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		switch(idMensaje) {	//El unico que hace una acción al presionar el botón de la izquierda es el "MensajeYaExistenReservas"
		case 4:
			this.panelResultadosDeBusquedaHabitacionesGroupBox.deseleccionarPeriodo();
			break;
		}
	}
	
	public void setTextoMensajeHabitacionConOcupacionOFS (EstadoHabitacion estado) {

		if(estado.equals(EstadoHabitacion.OCUPADA)) {
			this.mensajeHabitacionConOcupacionOFS.setTextoMensaje("La habitacion esta ocupada. Cambiar este texto");
		}
		else if (estado.equals(EstadoHabitacion.FUERA_DE_SERVICIO)) {
			this.mensajeHabitacionConOcupacionOFS.setTextoMensaje("La habitacion esta fuera de servicio. Cambiar este texto");
		}
		
		this.mensajeHabitacionConOcupacionOFS.mostrar(getPanel(), frameActual);
	}

}