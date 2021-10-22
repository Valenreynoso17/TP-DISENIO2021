package main.java.gestores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SortOrder;

import main.java.clases.Pasajero;
import main.java.daos.PasajeroDAO;
import main.java.dtos.PasajeroDTO;
import main.java.enums.ColumnaBuscarPasajeros;
import main.java.clases.Direccion;
import main.java.clases.Pais;
import main.java.excepciones.DocumentoRepetidoException;
import main.java.excepciones.InputInvalidaException;
import main.java.postgreImpl.PasajeroPostgreSQLImpl;

public class GestorPasajero {
	private static GestorPasajero instance;
	
	private PasajeroDAO pasajeroDAO;
	private GestorDireccion gestorDireccion;
	private GestorPaisProvincia gestorPaisProvincia;
	
	private GestorPasajero() {
		pasajeroDAO = new PasajeroPostgreSQLImpl();
	}
	
	public static GestorPasajero getInstance() {
		if (instance == null) instance = new GestorPasajero();
		
		return instance;
	}
	
	// Busca en la BD los pasajeros que cumplen con los filtro y devuelve la cantidad de resultados
	public Integer buscarCantidadPasajeros(PasajeroDTO filtros) {		
		return pasajeroDAO.cantidadPasajeros(filtros);
	}
	
	public List<PasajeroDTO> buscarPaginadoSinValidar(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina, ColumnaBuscarPasajeros columna, SortOrder orden) {
		List<Pasajero> pasajeros = pasajeroDAO.buscarPasajerosPaginado(filtros, tamPagina, nroPagina, columna, orden);
		
		List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
		
		for (Pasajero p : pasajeros) {
			pasajerosDTO.add(crearPasajeroDTOAcotado(p));
		}
		
		return pasajerosDTO;
	}
	
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
		gestorPaisProvincia = GestorPaisProvincia.getInstance();
		
		Direccion direccion = gestorDireccion.crearDireccion(pasajeroDTO.getDireccion());
		Pais pais = gestorPaisProvincia.buscarPaisPorId(pasajeroDTO.getIdNacionalidad());
		
		Pasajero pasajero = new Pasajero(pasajeroDTO, direccion, pais);
		
		pasajeroDAO.guardar(pasajero);
	}
	
	
}
