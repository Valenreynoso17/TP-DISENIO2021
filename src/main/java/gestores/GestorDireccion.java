package main.java.gestores;

public class GestorDireccion {
	private static GestorDireccion instance;
	
	private GestorDireccion() {
		
	}
	
	public static GestorDireccion getInstance() {
		if (instance == null) instance = new GestorDireccion();
		
		return instance;
	}
}
