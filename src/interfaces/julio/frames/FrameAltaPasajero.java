package interfaces.julio.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import interfaces.julio.paneles.PanelAltaPasajero;

public class FrameAltaPasajero extends JFrame {

	private JPanel contentPane;

	public FrameAltaPasajero() {
		super("Sistema de Gestión de Transporte Multimodal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelAltaPasajero(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
