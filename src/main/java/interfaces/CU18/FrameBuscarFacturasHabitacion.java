package main.java.interfaces.CU18;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import main.java.enums.TipoMensaje;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;

public class FrameBuscarFacturasHabitacion extends JFrame implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private String textoMensajeCUNoImplementado = "<html><p>El CU18 'Ingresar Pago' fue removido de esta entrega.</p><html>";
	private Mensaje mensajeCUNoImplementado = new Mensaje(0, textoMensajeCUNoImplementado, TipoMensaje.ERROR, "Aceptar", null); 

	public FrameBuscarFacturasHabitacion() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelBuscarFacturasHabitacion(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		mensajeCUNoImplementado.mostrar(getPanel(), this);
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
