package main.java.gestores;

public class GestorPaisProvincia {
	private static GestorPaisProvincia instance;
	
	private GestorPaisProvincia() {
		
	}
	
	public static GestorPaisProvincia getInstance() {
		if (instance == null) instance = new GestorPaisProvincia();
		
		return instance;
	}
}
