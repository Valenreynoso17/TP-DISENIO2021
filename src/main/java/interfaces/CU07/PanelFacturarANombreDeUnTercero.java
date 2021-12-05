package main.java.interfaces.CU07;

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

import main.java.dtos.OcupacionDTO;
import main.java.dtos.ResponsableDePagoDTO;
import main.java.enums.TipoMensaje;
import main.java.excepciones.InputVacioException;
import main.java.excepciones.NoExisteResponsableCuitException;
import main.java.gestores.GestorResponsableDePago;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelFacturarANombreDeUnTercero extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private OcupacionDTO ocupacion;
	
	private PanelFacturarANombreDeUnTerceroGroupBox panelGroupBox = new PanelFacturarANombreDeUnTerceroGroupBox();;
	
	private JButton aceptar;
	private JButton cancelar;
	
	private String textoMensajeCUITVacio = "<html><p>El CUIT ingresado es vacío. ¿Desea dar de alta un nuevo responsable de pago?</p><html>";
	private Mensaje mensajeCUITVacio = new Mensaje(1, textoMensajeCUITVacio, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeNoExisteResponsable = "<html><p>El CUIT ingresado es inválido/No coincide con ningún responsable"
													+ " de pago del sistema. ¿Desea dar de alta un nuevo responsable de pago?</p><html>";
	private Mensaje mensajeNoExisteResponsable = new Mensaje(2, textoMensajeNoExisteResponsable, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoAltaResponsableDePago = "<html><p>El CU13 'Dar de alta nuevo Responsable de Pago' no se implementa en esta etapa.</p><html>";
	private Mensaje mensajeAltaResponsableDePago = new Mensaje(3, textoAltaResponsableDePago, TipoMensaje.ERROR, "Aceptar", null);

	private Insets insetPanelGroupBox = new Insets(0,0,0,0);
	private Insets insetCancelar = new Insets(0,30,0,0);
	private Insets insetAceptar = new Insets(0,0,0,30);

	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	private FrameFacturar frameAnterior;
	private FrameFacturarANombreDeUnTercero frameActual;
	
	private GestorResponsableDePago gestorResponsablePago;
	
	public PanelFacturarANombreDeUnTercero(FrameFacturarANombreDeUnTercero frame, FrameFacturar frameA) {
		
		gestorResponsablePago = GestorResponsableDePago.getInstance();
		
		this.frameActual = frame;
		this.frameAnterior = frameA;
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;	c.fill = GridBagConstraints.BOTH;	c.gridwidth = 2;
		c.gridy = 0;	c.weightx = 0.4; c.weighty = 0.4;	c.insets = insetPanelGroupBox;	
	
		this.add(panelGroupBox, c);
	
		c.fill = GridBagConstraints.NONE;	c.gridwidth = 1;	c.weightx = 0.1; c.weighty = 0.1;
		
		cancelar = new JButton("Cancelar");
		cancelar.setMinimumSize(dimensionBoton);
		cancelar.setPreferredSize(dimensionBoton);
		cancelar.setBackground(Color.decode("#E0E0E0"));
		cancelar.setFont(fuenteBoton);
		cancelar.setBorder(bordeBoton);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameAnterior.setEnabled(true);
				frameActual.dispose();
			}
		});
		c.anchor = GridBagConstraints.WEST;		c.insets = insetCancelar;
		c.gridx = 0; c.gridy = 1;
		this.add(cancelar, c);
		
		aceptar = new JButton("Aceptar");
		aceptar.setMinimumSize(dimensionBoton);
		aceptar.setPreferredSize(dimensionBoton);
		aceptar.setBackground(Color.decode("#E0E0E0"));
		aceptar.setFont(fuenteBoton);
		aceptar.setBorder(bordeBoton);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					panelGroupBox.CUITNoEsVacio();
					
					ResponsableDePagoDTO responsablePagoDTO = gestorResponsablePago.buscarResponsableDePago(panelGroupBox.getCUIT());
					
					panelGroupBox.setRazonSocial(responsablePagoDTO.getRazonSocial());
					
					frameAnterior.dispose();
					frameActual.dispose();
					new FrameFacturarConsumos(ocupacion, responsablePagoDTO);
				}
				catch(InputVacioException exc) {
					
					mensajeCUITVacio.mostrar(getPanel(), frameActual);
				}
				catch(NoExisteResponsableCuitException exc) {
					
					mensajeNoExisteResponsable.mostrar(getPanel(), frameA);
				}
				
				
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = insetAceptar;
		c.gridx = 1; c.gridy = 1;
		this.add(aceptar, c);

	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}

	@Override
	public void confirmoElMensaje(Integer idMensaje) {

		switch(idMensaje) {
		case 1:
		case 2:	//En ambos mensajes al apretar "Si" debería ir a "Dar de alta un nuevo responsable de pago"
			mensajeAltaResponsableDePago.mostrar(getPanel(), frameActual);
			break;	
		case 3:		//Si quiere dar de alta un nuevo responsable de pago, simplemente se muestra el mensaje
			break;
		}
		
	}

	@Override
	public void confirmoCancelar(Integer idMensaje) {
		
		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}

	public void ocupacionSeleccionada(OcupacionDTO o) {
		
		this.ocupacion = o;
	}

}
