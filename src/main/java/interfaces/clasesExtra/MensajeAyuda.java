package main.java.interfaces.clasesExtra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import main.java.interfaces.frames.FrameODialog;

public class MensajeAyuda extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String texto;
	
	private JLabel labelTexto;
	private JButton boton;
	
	private Insets insetTexto = new Insets(0,0,0,0);
	private Insets insetOpcionAceptar = new Insets(0,0,15,50);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private FrameODialog frameActual;

	public MensajeAyuda(String texto) {	
		this.setTitle("Sistema Hotel Premier");
		this.texto = texto;
	}
	
	public void mostrar(final PanelPermiteMensajes panel, FrameODialog frame) {
		
		frameActual = frame;
		
		frame.setEnabled(false);	//Para que solo se pueda clickear el mensaje
		
		setBounds(100, 100, 800, 400);
		
		contentPane = new JPanel();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		labelTexto = new JLabel(texto);	c.weightx = 0.2; c.weighty = 0.2;	c.insets = insetTexto;
		labelTexto.setFont(fuenteBoton);
		labelTexto.setPreferredSize(new Dimension(770, 400));
		c.anchor = GridBagConstraints.CENTER;	
		c.fill = GridBagConstraints.BOTH;
		contentPane.add(labelTexto, c);
		
		c.fill = GridBagConstraints.NONE;
		boton = new JButton("Aceptar"); c.weightx = 0.0; c.weighty = 0.0;
		boton.setFont(fuenteBoton);
		boton.setBorder(bordeBoton);
		boton.setBackground(Color.decode("#E0E0E0"));
		boton.setPreferredSize(new Dimension(100, 35));
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setEnabled(true);
				dispose();
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = insetOpcionAceptar;
			c.gridy = 1;
		contentPane.add(boton, c);
		
		setContentPane(contentPane);
		
		this.setVisible(true);
	}

	protected void processWindowEvent(WindowEvent e) {	//Para que al cerrarse con la cruz (arriba a la derecha) no se cierren todos los frames
		
		super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
        	
            this.dispose();
            frameActual.toFront();	
            frameActual.setEnabled(true);
        }
	} 
	
}
