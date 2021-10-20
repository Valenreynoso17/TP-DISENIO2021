package main.java.gestores;

import java.util.ArrayList;
import java.util.List;

import main.java.clases.Pasajero;
import main.java.dtos.PasajeroDTO;
import main.java.excepciones.InputInvalidaException;

public class GestorPasajero {
	private static GestorPasajero instance;
	
	private GestorPasajero() {
		
	}
	
	public static GestorPasajero getInstance() {
		if (instance == null) instance = new GestorPasajero();
		
		return instance;
	}
	
	public List<PasajeroDTO> buscar(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina) throws InputInvalidaException {
		validarDatosBusqueda(filtros);
		
		
		return null;
	}
	
	private void validarDatosBusqueda(PasajeroDTO pasajeroDTO) throws InputInvalidaException{
		List<String> camposInvalidos = new ArrayList<String>();
		
		if (pasajeroDTO.getNombre() != null && !nombreApellidoValido(pasajeroDTO.getNombre())) camposInvalidos.add("Nombre");
		if (pasajeroDTO.getApellido() != null && !nombreApellidoValido(pasajeroDTO.getApellido())) camposInvalidos.add("Apellido");
		
		if (!camposInvalidos.isEmpty()) throw new InputInvalidaException(camposInvalidos);
		
	}
	
	private boolean nombreApellidoValido(String apellido) {
		return apellido.matches("[A-Z¡…Õ”⁄‹—«]+( [A-Z¡…Õ”⁄‹—«]+)+");
	}
	
	// Crea un PasajeroDTO con no todos los datos a partir de un pasajero
	private PasajeroDTO crearPasajeroDTOAcotado(Pasajero pasajero) {
		PasajeroDTO pasajeroDTO = new PasajeroDTO();
		
		pasajeroDTO.setId(pasajero.getId());
		pasajeroDTO.setNombre(pasajero.getNombre());
		pasajeroDTO.setApellido(pasajero.getApellido());
		pasajeroDTO.setTipoDocumento(pasajero.getTipoDocumento());
		pasajeroDTO.setNumeroDoc(pasajero.getDocumento());
		
		return pasajeroDTO;
	}
	
	
}
