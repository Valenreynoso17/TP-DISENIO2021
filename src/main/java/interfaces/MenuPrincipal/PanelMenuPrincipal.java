package main.java.interfaces.MenuPrincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.enums.TipoMensaje;
import main.java.interfaces.CU01.FrameAutenticarUsuario;
import main.java.interfaces.CU01.PanelAutenticarUsuarioGroupBox;
import main.java.interfaces.CU02.FrameGestionarPasajero;
import main.java.interfaces.CU04.FrameReservarHabitacion;
import main.java.interfaces.CU07.FrameFacturar;
import main.java.interfaces.CU17.FrameOcuparHabitacion;
import main.java.interfaces.CU18.FrameBuscarFacturasHabitacion;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;


public class PanelMenuPrincipal extends JPanel implements PanelPermiteMensajes{
	
	private PanelAutenticarUsuarioGroupBox panelAutenticarUsuarioGroupBox = new PanelAutenticarUsuarioGroupBox();
	
	private JButton cerrarSesion;
	private JButton button;
	
	private JLabel label;

	private Insets der = new Insets(0,25,0,0);
	private Insets izq = new Insets(0,0,0,25);
	private Insets derF = new Insets(0,25,70,0);
	private Insets izqF = new Insets(0,0,70,25);
	
	private Font fuenteTitulo = new Font("SourceSansPro", Font.PLAIN, 46);	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 15);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	private RoundedBorder bordeSalir = new RoundedBorder(10, Color.DARK_GRAY);
	
	private String textoMensajeCerrarSesion = "<html><p>�Est� seguro que desea cerrar sesi�n? Deber� volver a inciar sesi�n.</p><html>";
	private Mensaje mensajeCerrarSesion = new Mensaje(1, textoMensajeCerrarSesion, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeCU03 = "<html><p>El CU03 'Gestionar Responsable de Pago' no debe ser implementado.</p><html>";
	private Mensaje mensajeCU03 = new Mensaje(2, textoMensajeCU03, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoMensajeCU06 = "<html><p>El CU06 'Cancelar Reserva' no debe ser implementado.</p><html>";
	private Mensaje mensajeCU06 = new Mensaje(3, textoMensajeCU06, TipoMensaje.ERROR, "Aceptar", null); 

	private String textoMensajeCU09 = "<html><p>El CU09 'Gestionar Listados' no debe ser implementado.</p><html>";
	private Mensaje mensajeCU09 = new Mensaje(4, textoMensajeCU09, TipoMensaje.ERROR, "Aceptar", null); 
	
	private String textoMensajeCU21 = "<html><p>El CU21 'Ingresar Nota de Cr�dito' no debe ser implementado.</p><html>";
	private Mensaje mensajeCU21 = new Mensaje(5, textoMensajeCU21, TipoMensaje.ERROR, "Aceptar", null); 
	
	private FrameAutenticarUsuario frameAnterior;
	private FrameMenuPrincipal frameActual;
	
	private FrameGestionarPasajero frameGestionarPasajero;
	private FrameReservarHabitacion frameReservarHabitacion;
	private FrameOcuparHabitacion frameOcuparHabitacion;
	private FrameFacturar frameFacturar;
	private FrameBuscarFacturasHabitacion frameIngresarPago;
	
	public PanelMenuPrincipal(final FrameMenuPrincipal frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("Hotel Premier");
		label.setFont(fuenteTitulo);
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 2;
			c.gridx = 0; c.gridy = 0;
		c.weightx = 0.5; c.weighty = 0.25;			
		this.add(label, c);
		
		c.gridwidth = 1;	c.weighty = 0.1;
		
		button = new JButton("Gestionar Pasajero");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				frameGestionarPasajero = new FrameGestionarPasajero();
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = izq;
			c.gridx = 0; c.gridy = 1;
		this.add(button, c);
		
		button = new JButton("Gestionar Responsable de Pago");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeCU03.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.WEST; c.insets = der;
			c.gridx = 1; c.gridy = 1;
		this.add(button, c);
		
		button = new JButton("Reservar Habitaci�n");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				frameReservarHabitacion = new FrameReservarHabitacion();
			}
		});
		c.anchor = GridBagConstraints.EAST; c.insets = izq;
			c.gridx = 0; c.gridy = 2;
		this.add(button, c);
		
		button = new JButton("Ocupar Habitaci�n");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				frameOcuparHabitacion = new FrameOcuparHabitacion();
			}
		});
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.WEST; c.insets = der;
			c.gridx = 1; c.gridy = 2;
		this.add(button, c);
		
		button = new JButton("Cancelar Reserva");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeCU06.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = izq;
			c.gridx = 0; c.gridy = 3;
		this.add(button, c);
		
		button = new JButton("Gestionar Listados");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeCU09.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.WEST;	c.insets = der;
			c.gridx = 1; c.gridy = 3;
		this.add(button, c);
		
		button = new JButton("Facturar");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameActual.dispose();
				frameFacturar = new FrameFacturar();
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = izq;
			c.gridx = 0; c.gridy = 4;
		this.add(button, c);
		
		button = new JButton("Ingresar Nota de Cr�dito");
		button.setFont(fuenteBoton);
		button.setBorder(bordeBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeCU21.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.WEST;	c.insets = der;
			c.gridx = 1; c.gridy = 4;
		this.add(button, c);
		
		cerrarSesion = new JButton("Cerrar Sesi�n");
		cerrarSesion.setFont(fuenteBoton);
		cerrarSesion.setBackground(Color.GRAY);
		cerrarSesion.setBorder(bordeSalir);
		cerrarSesion.setPreferredSize(new Dimension(350, 40));
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeCerrarSesion.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = izqF;
			c.gridx = 0; c.gridy = 5;
		this.add(cerrarSesion, c);

		button = new JButton("Ingresar Pago");
		button.setFont(fuenteBoton);
		button.setBorder(bordeBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				frameIngresarPago = new FrameBuscarFacturasHabitacion();
			}
		});
		c.anchor = GridBagConstraints.WEST; c.insets = derF;
			c.gridx = 1; c.gridy = 5;
		this.add(button, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	
	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si cancela, vuelve a AutenticarUsuario
			frameActual.dispose();
			frameAnterior = new FrameAutenticarUsuario();	
			break;
		case 2:	//No pasa nada
		case 3:	//No pasa nada
		case 4:	//No pasa nada
		case 5:	//No pasa nada
			break;	
		}
		

	}

	public void confirmoCancelar(Integer idMensaje) {
		
		//Ninguno de los mensajes tiene una funci�n si se presiona el bot�n de la izquierda
	}
}
