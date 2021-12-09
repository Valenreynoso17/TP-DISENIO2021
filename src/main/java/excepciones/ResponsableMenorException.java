package main.java.excepciones;

public class ResponsableMenorException extends Exception {
	public ResponsableMenorException() {
		super("El pasajero seleccionada como responsable de la habitacion es menor de edad");
	}
	
}
