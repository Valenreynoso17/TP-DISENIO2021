package main.java.excepciones;

public class YaAsociadoConPasajeroException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public YaAsociadoConPasajeroException() {
		super("Este responsable de pago ya esta asociado a un pasajero");
	}
}
