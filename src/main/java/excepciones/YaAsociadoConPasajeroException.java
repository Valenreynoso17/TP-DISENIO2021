package main.java.excepciones;

public class YaAsociadoConPasajeroException extends RuntimeException {
	public YaAsociadoConPasajeroException() {
		super("Este responsable de pago ya esta asociado a un pasajero");
	}
}
