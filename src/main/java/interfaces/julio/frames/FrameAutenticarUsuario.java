package main.java.interfaces.julio.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.julio.paneles.PanelAltaPasajero;
import main.java.interfaces.julio.paneles.PanelAutenticarUsuario;

public class FrameAutenticarUsuario extends JFrame {

	private JPanel contentPane;

	public FrameAutenticarUsuario() {
		super("Sistema de Gestión de Transporte Multimodal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelAutenticarUsuario(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
