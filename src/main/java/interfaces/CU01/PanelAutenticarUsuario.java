package main.java.interfaces.CU01;

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
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelAutenticarUsuario extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelAutenticarUsuarioGroupBox panelAutenticarUsuarioGroupBox = new PanelAutenticarUsuarioGroupBox();
	
	private JButton cancelar;
	private JButton iniciarSesion;
	
	private JLabel label;
	
	private String textoMensajeSalir = "<html><p>¿Está seguro que desea salir del programa?</p><html>";
	private Mensaje mensajeSalir = new Mensaje(1, textoMensajeSalir, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoMensajeInputInvalido = "<html><p>La contraseña y/o el nombre ingresados son inválidos. Por favor, vuelva a ingresarlos.</p><html>";
	@SuppressWarnings("unused")
	private Mensaje mensajeInputInvalido = new Mensaje(2, textoMensajeInputInvalido, TipoMensaje.ERROR, "Aceptar", null);
	
	private Font fuenteTitulo = new Font("SourceSansPro", Font.PLAIN, 46);	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private FrameAutenticarUsuario frameActual;
	
	public PanelAutenticarUsuario(final FrameAutenticarUsuario frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("Hotel Premier");
		label.setFont(fuenteTitulo);
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(30,0,0,0);
		c.gridwidth = 3;
		c.gridx = 0; c.gridy = 0;
		c.weightx = 0.5; c.weighty = 0.2;			
		this.add(label, c);
		
		
		c.insets = new Insets(25,230,25,230);
		c.fill = GridBagConstraints.HORIZONTAL; 		c.gridx = 0; c.gridy = 1;	c.gridwidth = 3;
		c.weightx = 0.5; c.weighty = 0.5;			this.add(panelAutenticarUsuarioGroupBox, c);
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		
		c.insets = new Insets(0,100,30,0);
		cancelar = new JButton("Salir");
		cancelar.setFont(fuenteBoton);
		cancelar.setBackground(Color.decode("#E0E0E0"));
		cancelar.setBorder(bordeBoton);
		cancelar.setPreferredSize(new Dimension(140, 30));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeSalir.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.WEST;
													c.gridx = 0; c.gridy = 2;
													this.add(cancelar, c);

		c.insets = new Insets(0,0,30,100);
		iniciarSesion = new JButton("Iniciar Sesión");
		iniciarSesion.setFont(fuenteBoton);
		iniciarSesion.setBackground(Color.decode("#E0E0E0"));
		iniciarSesion.setBorder(bordeBoton);
		iniciarSesion.setPreferredSize(new Dimension(140, 30));
		iniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(panelAutenticarUsuarioGroupBox.inputEsNoVacio()) {
					frame.dispose();
					new FrameMenuPrincipal();
				}
				else {
					//mensajeInputInvalido.mostrar(getPanel(), frame);
				}
			}
		});
		c.anchor = GridBagConstraints.EAST;
													c.gridx = 2; c.gridy = 2;
													this.add(iniciarSesion, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	
	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si sale, cierra el programa
			frameActual.dispose();	
			break;
		case 2:	//No pasa nada
			
			break;		
		}
		

	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una función si se presiona el botón de la izquierda
	}

}