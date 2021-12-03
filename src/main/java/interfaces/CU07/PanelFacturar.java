package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.dtos.OcupacionDTO;
import main.java.dtos.ResponsableDePagoDTO;
import main.java.enums.TipoMensaje;
import main.java.excepciones.InputInvalidaException;
import main.java.excepciones.InputVacioException;
import main.java.excepciones.OcupacionYaFacturadaException;
import main.java.excepciones.PasajeroNoSeleccionadoException;
import main.java.excepciones.ReponsablePagoMenorDeEdadException;
import main.java.gestores.GestorOcupacion;
import main.java.gestores.GestorResponsableDePago;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelFacturar extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelFacturarGroupBox panelFacturarGroupBox = new PanelFacturarGroupBox();
	private PanelResultadosDeBusquedaFacturarGroupBox panelResultadosDeBusquedaFacturarGroupBox;
	
	private JButton buscar;
	private JButton siguiente;
	private JButton cancelar;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoPasajeroMenorDeEdad = "<html><p>La persona ingresada como 'Responsable de pago' es menor de edad."
											+ " Por favor, seleccione otra persona.</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajePasajeroMenorDeEdad = new Mensaje(2, textoPasajeroMenorDeEdad, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoHabitacionSinDeudaAsociada = "<html><p>La habitación " + "Luego se reemplaza" + " no tiene ninguna deuda asociada.</p><html>";
	private Mensaje mensajeHabitacionSinDeudaAsociada = new Mensaje(3, textoHabitacionSinDeudaAsociada, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoHabitacionInexsistente = "<html><p>La habitación " + "Luego se reemplaza" + " no existe. Por favor, ingrese un número de una habitación"
											   + " existente en el sistema.</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeHabitacionInexsistente = new Mensaje(4, textoHabitacionInexsistente, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoResponsableNoSeleccionado = "<html><p>No ha seleccionado ningún pasajero como responsable de pago. Por favor, "
			   + "seleccione uno de la tabla y luego presione 'Siguiente'.</p><html>";
	private Mensaje mensajeResponsableNoSeleccionado = new Mensaje(5, textoResponsableNoSeleccionado, TipoMensaje.ERROR, "Aceptar", null);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,30,0,30);
	
	private FrameFacturar frameActual;
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	private GestorOcupacion gestorOcupacion;
	
	private GestorResponsableDePago gestorResponsablePago;
	
	private OcupacionDTO ocupacionDTO;
	
	public PanelFacturar(final FrameFacturar frame) {
		
		this.frameActual = frame;
		
		gestorOcupacion = GestorOcupacion.getInstance();
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelBusqueda;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelFacturarGroupBox, c);
		
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
						this.panelFacturarGroupBox.inputNoEsVacia();
						this.panelFacturarGroupBox.inputEsValida();
						
						Integer numeroHabitacion = Integer.parseInt(this.panelFacturarGroupBox.getNumeroHabitacion());
						LocalTime horaSalida = LocalTime.parse(this.panelFacturarGroupBox.getHoraSalida());
						
						LocalDateTime fechaHora = LocalDateTime.of(LocalDate.now(), horaSalida);
						
						ocupacionDTO = gestorOcupacion.buscarUltimaOcupacionDTO(numeroHabitacion, fechaHora);
						
						panelResultadosDeBusquedaFacturarGroupBox.ocupacionSeleccionada(ocupacionDTO);
						
						siguiente.setEnabled(true);
				}
				catch(InputVacioException exc) {
					
					this.panelFacturarGroupBox.colocarLabelVacio(exc.getInputsVacios());
				}
				catch (InputInvalidaException exc) {
					
					this.panelFacturarGroupBox.colocarLabelInvalido(exc.getInputsInvalidos());
				}
				catch(OcupacionYaFacturadaException exc) {
					
					//Si la habitación no tiene ninguna deuda asociada, PEDIR EL NUMERO DE HABITACION y mostrar:
					mensajeHabitacionSinDeudaAsociada.setTextoMensaje("<html><p>La habitación " + this.panelFacturarGroupBox.getNumeroHabitacion() + " no tiene ninguna deuda asociada.</p><html>");
					mensajeHabitacionSinDeudaAsociada.mostrar(getPanel(), frame);
				}
		});
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1; c.gridy = 1;
		this.add(buscar, c);
		
		panelResultadosDeBusquedaFacturarGroupBox = new PanelResultadosDeBusquedaFacturarGroupBox(frame);
		c.insets = insetPanelTabla;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 2;	c.gridwidth = 3;
		c.weightx = 0.8; c.weighty = 0.8;			this.add(panelResultadosDeBusquedaFacturarGroupBox, c);
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
					//PasajeroDTO pasajero = panelGestionarPasajeroTabla.pasajeroSeleccionado();	//Esto estaba en el GESTIONAR PASAJERO que hizo FEDE
					
					panelResultadosDeBusquedaFacturarGroupBox.seleccionoUnPasajero();
					
					ResponsableDePagoDTO responsablePagoDTO = gestorResponsablePago.obtenerResponsableDePagoDTO(null); // TODO se le tieen que pasar un pasajeroDTO
					
					
					frameActual.dispose();
					new FrameFacturarConsumos(ocupacionDTO, responsablePagoDTO);
				}
				catch (PasajeroNoSeleccionadoException exc) {
					mensajeResponsableNoSeleccionado.mostrar(getPanel(), frame);
				}
				catch (ReponsablePagoMenorDeEdadException exc) {
					//Si la persona seleccionada es menor de edad, se debe mostrar (descomentar):
					mensajePasajeroMenorDeEdad.mostrar(getPanel(), frame);
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
			frameActual.dispose();
			new FrameMenuPrincipal();	
			break;
		case 2:	//Si el responsable es menor de edad, simplemente muestra el mensaje
			break;
		case 3:	//Si la habitación no posee deudas, simplemente muestra el mensaje
			break;		
		case 4:	//Si la habitación no existe, simplemente muestra el mensaje
			break;	
		case 5:	//Si no selecciona un pasajero, simplemente muestra el mensaje
			break;	
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}

}