package main.java.excepciones;

import java.util.ArrayList;
import java.util.List;

import main.java.dtos.ReservaDTO;

public class ContieneFechasReservadasException extends Exception {

	private static final long serialVersionUID = 1L;
	
	List<ReservaDTO> reservasSeleccionadas;
	
	public ContieneFechasReservadasException(List<ReservaDTO> reservas) {
		super();
		this.reservasSeleccionadas = reservas;
	}
	
	public List<ReservaDTO> getReservasSeleccionadas(){
		return this.reservasSeleccionadas;
	}
}
