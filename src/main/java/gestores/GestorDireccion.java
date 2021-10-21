package main.java.gestores;

import main.java.clases.Direccion;
import main.java.clases.Localidad;
import main.java.dtos.DireccionDTO;

public class GestorDireccion {
	private static GestorDireccion instance;
	private GestorPaisProvincia gestorPaisProvincia;
	
	private GestorDireccion() {
		
	}
	
	public static GestorDireccion getInstance() {
		if (instance == null) instance = new GestorDireccion();
		
		return instance;
	}
	
	public Direccion crearDireccion(DireccionDTO direccionDTO) {
		
		gestorPaisProvincia = GestorPaisProvincia.getInstance();
		
		Localidad localidad = gestorPaisProvincia.buscarLocalidadPorID(direccionDTO.getIdLocalidad());
		
		Direccion direccion = new Direccion(direccionDTO);
		
		direccion.setLocalidad(localidad);
		
		return direccion;
	}
}
