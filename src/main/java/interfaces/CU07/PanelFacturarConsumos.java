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

import main.java.enums.TipoMensaje;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelFacturarConsumos extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelFacturarConsumosGroupBox panelFacturarConsumosGroupBox = new PanelFacturarConsumosGroupBox();
	
	private JButton aceptar;
	private JButton cancelar;
	
	private String textoMensajeCancelar = "<html><p>¿Está seguro que desea cancelar la operación?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoNadaSeleccionado = "<html><p>Por favor, seleccione al menos un consumo para ser adherido a la factura.</p><html>";
	private Mensaje mensajeNadaSeleccionado = new Mensaje(2, textoNadaSeleccionado, TipoMensaje.ERROR, "Aceptar", null);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelFacturarConsumos = new Insets(30,30,20,30);
	
	private FrameFacturarConsumos frameActual;
	//TODO: Imprimir
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelFacturarConsumos(final FrameFacturarConsumos frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelFacturarConsumos;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 2;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelFacturarConsumosGroupBox, c);
		
		c.weightx = 0.0; c.weighty = 0.0;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
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
		c.anchor = GridBagConstraints.WEST;		c.insets = new Insets(0,60,20,0);
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
				
				//TODO: Imprimir	
				
				//Si no seleccionó ningún consumo hay que mostrar: 
				mensajeNadaSeleccionado.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,20,60);
		c.gridx = 1; c.gridy = 1;
		this.add(aceptar, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	
	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si cancela, vuelve al frame anterior
			frameActual.dispose();
			new FrameFacturar();	
			break;
		case 2:	//Si no selecciona ningún consumo a facturar, simplemente muestra el mensaje
			break;
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}
}
