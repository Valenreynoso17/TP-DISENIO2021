package main.java.gestores;

import main.java.clases.Pasajero;
import main.java.dtos.PasajeroDTO;

public class GestorPasajero {
	private static GestorPasajero instance;
	
	private GestorPasajero() {
		
	}
	
	public static GestorPasajero getInstance() {
		if (instance == null) instance = new GestorPasajero();
		
		return instance;
	}
	
	// Crea un PasajeroDTO con no todos los datos a partir de un pasajero
	// TODO Hay que ver si pasar tambien el ID
	private PasajeroDTO crearPasajeroDTOAcotado(Pasajero pasajero) {
		PasajeroDTO pasajeroDTO = new PasajeroDTO();
		
		pasajeroDTO.setNombre(pasajero.getNombre());
		pasajeroDTO.setApellido(pasajero.getApellido());
		pasajeroDTO.setTipoDocumento(pasajero.getTipoDocumento());
		pasajeroDTO.setNumeroDoc(pasajero.getDocumento());
		
		return pasajeroDTO;
	}
}
