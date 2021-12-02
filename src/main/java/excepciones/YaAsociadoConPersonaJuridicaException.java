package main.java.excepciones;

public class YaAsociadoConPersonaJuridicaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public YaAsociadoConPersonaJuridicaException() {
		super("Este responsable de pago ya esta asociado a una persona jurídica");
	}
}
