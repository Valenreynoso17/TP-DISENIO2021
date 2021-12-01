package main.java.interfaces.CU04;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.enums.TipoMensaje;
import main.java.interfaces.CU05.PanelMostrarEstadoHabitaciones;
import main.java.interfaces.CU11.PanelAltaPasajero;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.FrameMuestraEstadoHabitaciones;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;

public class FrameReservarHabitacion extends FrameMuestraEstadoHabitaciones implements PanelPermiteMensajes{

	private JPanel contentPane;
	
	private String textoMensajeCUNoImplementado = "<html><p>El CU04 'Reservar habitación' fue removido de esta entrega.</p><html>";
	private Mensaje mensajeCUNoImplementado = new Mensaje(0, textoMensajeCUNoImplementado, TipoMensaje.ERROR, "Aceptar", null); 
	
	private FrameConfirmarDatosHabitacion frameConfirmarDatosHabitacion;

	public FrameReservarHabitacion() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelMostrarEstadoHabitaciones(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		mensajeCUNoImplementado.mostrar(getPanel(), this);
	}
	
	@Override
	public void apretoSiguiente() {
		
		
		this.dispose();
		frameConfirmarDatosHabitacion = new FrameConfirmarDatosHabitacion();
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	
	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 0:	//Al apretar aceptar, debe cerrarse la ventana e ir al Menú Principal
			this.dispose();
			new FrameMenuPrincipal();	
			break;	
		}
	}

	public void confirmoCancelar(Integer idMensaje) {
	}

}
