package main.java.interfaces.CU18;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.CU11.PanelAltaPasajero;

public class FrameBuscarFacturasHabitacion extends JFrame {

	private JPanel contentPane;

	public FrameBuscarFacturasHabitacion() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelBuscarFacturasHabitacion(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
