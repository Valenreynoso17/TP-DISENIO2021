package main.java.excepciones;

public class InputVacioException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public String inputsVacios;
	
	public InputVacioException() {
		super();
	}
	
	public InputVacioException(String inputs) {
		super();
		this.inputsVacios = inputs;		
	}
	
	public String getInputsVacios() {
		return this.inputsVacios;
	}

}
