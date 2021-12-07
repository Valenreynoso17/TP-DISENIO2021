package main.java.interfaces.CU07;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.dtos.OcupacionDTO;
import main.java.interfaces.frames.FramePrincipal;

public class FrameFacturarANombreDeUnTercero extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private PanelFacturarANombreDeUnTercero contentPane;
	
	private JFrame frameAnterior;

	public FrameFacturarANombreDeUnTercero(FramePrincipal frame) {
		
		super("Sistema Hotel Premier");
		
		frameAnterior = frame;
		
		setBounds(100, 100, 500, 250);
		contentPane = new PanelFacturarANombreDeUnTercero(this, frame);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void ocupacionSeleccionada(OcupacionDTO o) {
		
		this.contentPane.ocupacionSeleccionada(o);
	}
	 
	protected void processWindowEvent(WindowEvent e) {	//Para que al cerrarse con la cruz (arriba a la derecha) no se cierren todos los frames
		
		super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
        	
            this.dispose();
            frameAnterior.toFront();	
            frameAnterior.setEnabled(true);
        }
	}     
}
