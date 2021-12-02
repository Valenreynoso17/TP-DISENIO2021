package main.java.interfaces.CU07;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.dtos.OcupacionDTO;

public class FrameFacturarANombreDeUnTercero extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public FrameFacturarANombreDeUnTercero(FrameFacturar frame, OcupacionDTO ocupacionDTO) {
		super("Sistema Hotel Premier");
		setBounds(100, 100, 500, 250);
		contentPane = new PanelFacturarANombreDeUnTercero(this, frame, ocupacionDTO);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
