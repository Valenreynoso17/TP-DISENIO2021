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
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelIngresarDatosPago extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelDatosFacturaGroupBox panelDatosFacturaGroupBox = new PanelDatosFacturaGroupBox();
	private PanelDatosDelPagoGroupBox panelDatosDelPagoGroupBox = new PanelDatosDelPagoGroupBox();
	
	private JButton atras;
	private JButton siguiente;
	private JButton cancelar;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelDatosFactura = new Insets(20,30,0,30);
	private Insets insetPanelDatosDelPago = new Insets(10,30,20,30);
	
	private FrameIngresarDatosPago frameActual;
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelIngresarDatosPago(final FrameIngresarDatosPago frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelDatosFactura;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.4; c.weighty = 0.2;			this.add(panelDatosFacturaGroupBox, c);
		
		c.insets = insetPanelDatosDelPago;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 1;	c.gridwidth = 3;
		c.weightx = 0.4; c.weighty = 0.6;			this.add(panelDatosDelPagoGroupBox, c);
		
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
		c.gridx = 0; c.gridy = 2;
		this.add(cancelar, c);
		
		atras = new JButton("Atrás");
		atras.setMinimumSize(dimensionBoton);
		atras.setPreferredSize(dimensionBoton);
		atras.setBackground(Color.decode("#E0E0E0"));
		atras.setFont(fuenteBoton);
		atras.setBorder(bordeBoton);
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new FrameBuscarFacturasHabitacion();
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
		c.anchor = GridBagConstraints.CENTER;		c.insets = new Insets(0,0,10,0);
		c.gridx = 2; c.gridy = 2;
		this.add(atras, c);

		siguiente = new JButton("Pagar");
		siguiente.setMinimumSize(dimensionBoton);
		siguiente.setPreferredSize(dimensionBoton);
		siguiente.setBackground(Color.decode("#E0E0E0"));
		siguiente.setFont(fuenteBoton);
		siguiente.setBorder(bordeBoton);
//		siguiente.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
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
//		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 2; c.gridy = 2;
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
//		case 2:	//Si no se encontro ningún pasajero, va a la pantalla de AltaPasajero
//			frameActual.dispose();
//			frameAltaPasajero = new FrameAltaPasajero();
//			break;
//		case 3:	//Si no se seleccionó ningún pasajero, va a la pantalla de AltaPasajero
//			frameActual.dispose();
//			frameAltaPasajero = new FrameAltaPasajero();	
//			break;		
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}
}
