package main.java.excepciones;

public class FechaInvalidaException extends Exception {
	
	public String fechasInvalidas;
	
	public FechaInvalidaException(String inputs) {
		super();
		this.fechasInvalidas = inputs;
	}
	
	public String getFechasInvalidas() {
		return this.fechasInvalidas;
	}

}
