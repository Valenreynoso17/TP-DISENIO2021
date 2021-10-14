package main.java.gestores;

public class GestorPasajero {
	private static GestorPasajero instance;
	
	private GestorPasajero() {
		
	}
	
	public static GestorPasajero getInstance() {
		if (instance == null) instance = new GestorPasajero();
		
		return instance;
	}
}
