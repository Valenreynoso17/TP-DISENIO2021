package main.java.interfaces.CU04;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FrameReservaANombreDe extends JFrame{

	private JPanel contentPane;

	public FrameReservaANombreDe(FrameConfirmarDatosHabitacion frameA) {
		
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new PanelReservaANombreDe(this, frameA);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
