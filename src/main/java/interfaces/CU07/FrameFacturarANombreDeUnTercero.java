package main.java.interfaces.CU07;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FrameFacturarANombreDeUnTercero extends JFrame{

	private JPanel contentPane;

	public FrameFacturarANombreDeUnTercero(FrameFacturar frame) {
		super("Sistema Hotel Premier");
		setBounds(100, 100, 500, 250);
		contentPane = new PanelFacturarANombreDeUnTercero(this, frame);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
