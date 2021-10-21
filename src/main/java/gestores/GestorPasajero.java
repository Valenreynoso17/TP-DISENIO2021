package main.java.gestores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SortOrder;

import main.java.clases.Pasajero;
import main.java.daos.PasajeroDAO;
import main.java.dtos.PasajeroDTO;
import main.java.enmus.ColumnaBuscarPasajeros;
import main.java.excepciones.InputInvalidaException;
import main.java.postgreImpl.PasajeroPostgreSQLImpl;

public class GestorPasajero {
	private static GestorPasajero instance;
	
	private PasajeroDAO pasajeroDAO;
	
	private GestorPasajero() {
		pasajeroDAO = new PasajeroPostgreSQLImpl();
	}
	
	public static GestorPasajero getInstance() {
		if (instance == null) instance = new GestorPasajero();
		
		return instance;
	}
	
	public List<PasajeroDTO> buscarPaginado(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina) throws InputInvalidaException {
		validarDatosBusqueda(filtros);
		
		List<Pasajero> pasajeros = pasajeroDAO.buscarPasajerosPaginado(filtros, tamPagina, nroPagina, ColumnaBuscarPasajeros.NOMBRE, SortOrder.DESCENDING);
		
		List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
		
		for (Pasajero p : pasajeros) {
			pasajerosDTO.add(crearPasajeroDTOAcotado(p));
		}
		
		return pasajerosDTO;
	}
	
	/*public List<PasajeroDTO> buscarPaginadoSinValidar(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina, String columna, String orden) {
		
	}*/
	
	private void validarDatosBusqueda(PasajeroDTO pasajeroDTO) throws InputInvalidaException{
		List<String> camposInvalidos = new ArrayList<String>();
		
		if (pasajeroDTO.getNombre() != null && !nombreApellidoValido(pasajeroDTO.getNombre())) camposInvalidos.add("Nombre");
		if (pasajeroDTO.getApellido() != null && !nombreApellidoValido(pasajeroDTO.getApellido())) camposInvalidos.add("Apellido");
		
		if (!camposInvalidos.isEmpty()) throw new InputInvalidaException(camposInvalidos);
		
	}
	
	private boolean nombreApellidoValido(String apellido) {
		return apellido.matches("[A-Z¡…Õ”⁄‹—«]+( [A-Z¡…Õ”⁄‹—«]+)*");
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
