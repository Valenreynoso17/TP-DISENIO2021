package main.java.interfaces.CU17;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FrameOcuparHabitacionConPasajeros extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public FrameOcuparHabitacionConPasajeros() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelOcuparHabitacionConPasajeros(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
