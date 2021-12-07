package main.java.excepciones;

public class UsuarioIncorrectoException extends Exception {
	public UsuarioIncorrectoException() {
		super("Nombre de usuario invalido");
	}
}
