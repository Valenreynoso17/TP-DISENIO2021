package main.java.main;

import main.java.interfaces.CU05.PanelMostrarEstadoHabitaciones;
import main.java.interfaces.frames.FramePrincipal;

public class App{

	public static void main(String[] args) {
			
			FramePrincipal frame = new FramePrincipal();
			
			frame.setNuevoPanel(new PanelMostrarEstadoHabitaciones(frame));
		
	}
}

