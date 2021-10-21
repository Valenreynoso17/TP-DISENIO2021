package main.java.excepciones;

import java.util.List;

public class InputInvalidaException extends Exception {
	private List<String> camposInvalidos;
	
	public InputInvalidaException(List<String> camposInvalidos) {
		super();
		this.camposInvalidos = camposInvalidos;
	}
	
	public List<String> getCamposInvalidos() {
		return camposInvalidos;
	}
}
