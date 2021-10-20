package main.java.interfaces.MenuPrincipal;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.CU11.PanelAltaPasajero;

public class FrameMenuPrincipal extends JFrame {

	private JPanel contentPane;

	public FrameMenuPrincipal() {
		super("Sistema Hotel Premier");
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