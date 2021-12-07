package main.java.excepciones;

public class ContraseniaIncorrectaExcepcion extends Exception {
	public ContraseniaIncorrectaExcepcion() {
		super("La contrasenia ingresada es incorrecta");
	}
}
