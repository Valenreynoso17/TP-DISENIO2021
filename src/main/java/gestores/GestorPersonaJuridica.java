package main.java.gestores;

public class GestorPersonaJuridica {
	private static GestorPersonaJuridica instance;
	
	private GestorPersonaJuridica() {
		
	}
	
	public static GestorPersonaJuridica getInstance() {
		if (instance == null) instance = new GestorPersonaJuridica();
		
		return instance;
	}
}
