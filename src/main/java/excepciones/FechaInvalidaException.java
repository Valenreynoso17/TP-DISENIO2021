package main.java.excepciones;

public class FechaInvalidaException extends Exception {
	
	public String inputsInvalidos;
	
	public FechaInvalidaException(String inputs) {
		super();
		this.inputsInvalidos = inputs;
	}

}
