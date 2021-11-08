package main.java.interfaces.CU17;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.CU04.FrameConfirmarDatosHabitacion;
import main.java.interfaces.CU05.PanelMostrarEstadoHabitaciones;
import main.java.interfaces.clasesExtra.FrameMuestraEstadoHabitaciones;

public class FrameOcuparHabitacion extends FrameMuestraEstadoHabitaciones {

	private JPanel contentPane;

	public FrameOcuparHabitacion() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelMostrarEstadoHabitaciones(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void apretoSiguiente() {
		
		this.dispose();
		//frameConfirmarDatosHabitacion = new FrameConfirmarDatosHabitacion();
	}

}
