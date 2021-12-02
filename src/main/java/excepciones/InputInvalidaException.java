package main.java.excepciones;

import java.util.List;

public class InputInvalidaException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> camposInvalidos;
	
	private String inputsInvalidos;
	
	public InputInvalidaException(List<String> camposInvalidos) {	//Para "GestorPasajero"
		super();
		this.camposInvalidos = camposInvalidos;
	}
	
	public InputInvalidaException(String inputs) {	//Para el panel "PanelFacturar"
		super();
		this.inputsInvalidos = inputs;
	}
	
	public List<String> getCamposInvalidos() {
		return this.camposInvalidos;
	}
	
	public String getInputsInvalidos() {
		return this.inputsInvalidos;
	}
}
