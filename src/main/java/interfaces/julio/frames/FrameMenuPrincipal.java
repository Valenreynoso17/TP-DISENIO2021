package main.java.interfaces.julio.frames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.julio.paneles.PanelAltaPasajero;
import main.java.interfaces.julio.paneles.PanelMenuPrincipal;

public class FrameMenuPrincipal extends JFrame {

	private JPanel contentPane;

	public FrameMenuPrincipal() {
		super("Sistema de Gestión de Transporte Multimodal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelMenuPrincipal(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}