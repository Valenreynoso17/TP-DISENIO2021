package main.java.excepciones;

public class YaAsociadoConPersonaJuridicaException extends RuntimeException {
	public YaAsociadoConPersonaJuridicaException() {
		super("Este responsable de pago ya esta asociado a una persona jurídica");
	}
}
