package main.java.excepciones;

public class FechaInvalidaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public String fechasInvalidas;
	
	public FechaInvalidaException(String inputs) {
		super();
		this.fechasInvalidas = inputs;
	}
	
	public String getFechasInvalidas() {
		return this.fechasInvalidas;
	}

}
