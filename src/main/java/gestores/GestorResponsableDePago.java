package main.java.gestores;

public class GestorResponsableDePago {
	private static GestorResponsableDePago instance;
	
	private GestorResponsableDePago() {
		
	}
	
	public static GestorResponsableDePago getInstance() {
		if (instance == null) instance = new GestorResponsableDePago();
		
		return instance;
	}
}
