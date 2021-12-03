package main.java.gestores;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import main.java.clases.Pasajero;
import main.java.clases.ResponsableDePago;
import main.java.daos.PasajeroDAO;
import main.java.daos.ResponsableDePagoDAO;
import main.java.dtos.PasajeroDTO;
import main.java.dtos.ResponsableDePagoDTO;
import main.java.excepciones.NoExisteResponsableCuitException;
import main.java.excepciones.ReponsablePagoMenorDeEdadException;
import main.java.postgreImpl.PasajeroPostgreSQLImpl;
import main.java.postgreImpl.ResponsableDePagoPostgreSQLImpl;

public class GestorResponsableDePago {
	private static GestorResponsableDePago instance;
	private ResponsableDePagoDAO responsableDAO;
	private PasajeroDAO pasajeroDAO;
	
	private GestorResponsableDePago() {
		responsableDAO = new ResponsableDePagoPostgreSQLImpl();
		pasajeroDAO = new PasajeroPostgreSQLImpl();
	}
	
	public static GestorResponsableDePago getInstance() {
		if (instance == null) instance = new GestorResponsableDePago();
		
		return instance;
	}
	
	public ResponsableDePagoDTO obtenerResponsableDePagoDTO(PasajeroDTO seleccionado) throws ReponsablePagoMenorDeEdadException {
		
		Integer edad = Period.between(seleccionado.getFechaNacimiento(), LocalDate.now()).getYears();
		
		if(edad < 18) {
			throw new ReponsablePagoMenorDeEdadException();
		}
		
		Optional<ResponsableDePago> optResponsable = Optional.ofNullable(responsableDAO.buscarResponsableAsociadoAPasajero(seleccionado.getId()));
		
		if(optResponsable.isEmpty()) {
			Pasajero pasajero = pasajeroDAO.buscar(seleccionado.getId());
			
			ResponsableDePago responsable = new ResponsableDePago(pasajero);
			
			responsableDAO.guardar(responsable);
			
			optResponsable = Optional.of(responsable);
		}
		
		ResponsableDePagoDTO responsableDTO = new ResponsableDePagoDTO(optResponsable.get());
		
		return responsableDTO;
	}
	
	public ResponsableDePagoDTO buscarResponsableDePago(String cuit) throws NoExisteResponsableCuitException {
		
		Optional<ResponsableDePago> optResponsable = Optional.ofNullable(responsableDAO.buscarPorCuit(cuit));
		
		if(optResponsable.isEmpty()) {
			throw new NoExisteResponsableCuitException();
		}
		
		return new ResponsableDePagoDTO(optResponsable.get());
	}
}
