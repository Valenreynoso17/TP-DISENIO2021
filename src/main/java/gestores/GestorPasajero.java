package main.java.gestores;

import java.util.ArrayList;
import java.util.List;

import main.java.clases.Direccion;
import main.java.clases.Pasajero;
import main.java.daos.PasajeroDAO;
import main.java.dtos.PasajeroDTO;
import main.java.excepciones.DocumentoRepetidoException;
import main.java.excepciones.InputInvalidaException;
import main.java.postgreImpl.PasajeroPostgreSQLImpl;

public class GestorPasajero {
	private static GestorPasajero instance;
	
	private PasajeroDAO pasajeroDAO;
	private GestorDireccion gestorDireccion; // No se si deberia instanciarse en el constructor y sÛlo cuando
											 // se necesita, por ahora elegÌ la segunda
	
	private GestorPasajero() {
		pasajeroDAO = new PasajeroPostgreSQLImpl();
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
	
	public void validarDatosPasajero(PasajeroDTO pasajeroDTO) throws DocumentoRepetidoException {
		
		// En principio los datos ya tendrÌan que ser v·lidos porque se validaron en la interfaz
		
		// Ver si ya existe en el sistema
		List<Pasajero> listaPasajeros = pasajeroDAO.buscarPorDocumento(pasajeroDTO.getTipoDocumento(), pasajeroDTO.getNumeroDoc());
		
		// Ojo con el isEmpty, cpz tendrÌa que ser == null
		if (!listaPasajeros.isEmpty()) {
			throw new DocumentoRepetidoException();
		}
		
		
	}
	
	public void crearPasajero(PasajeroDTO pasajeroDTO) {
		
		gestorDireccion = GestorDireccion.getInstance();
		
		Direccion direccion = gestorDireccion.crearDireccion(pasajeroDTO.getDireccion());
		
		Pasajero pasajero = new Pasajero(pasajeroDTO, direccion);
		
		pasajeroDAO.guardar(pasajero);
	}
	
	
}
