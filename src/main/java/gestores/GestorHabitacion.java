package main.java.gestores;

import main.java.clases.Habitacion;
import main.java.daos.HabitacionDAO;
import main.java.dtos.HabitacionDTO;
import main.java.dtos.TipoHabitacionDTO;
import main.java.postgreImpl.HabitacionPostgreSQLImpl;

public class GestorHabitacion {
	private static GestorHabitacion instance;
	
	private HabitacionDAO habitacionDao;
	
	private GestorHabitacion() {
		habitacionDao = new HabitacionPostgreSQLImpl();
	}
	
	public static GestorHabitacion getInstance() {
		if (instance == null) instance = new GestorHabitacion();
		
		return instance;
	}
	
	
	public HabitacionDTO buscarHabitacionDTO(Integer id) {
		Habitacion habitacion = this.buscarHabitacion(id);
		
		return this.crearDTO(habitacion);
	}
	
	
	public Habitacion buscarHabitacion(Integer id) {
		return habitacionDao.buscarHabitacion(id);
	}
	
	
	public HabitacionDTO crearDTO(Habitacion h) {
		TipoHabitacionDTO tipo = new TipoHabitacionDTO();
		tipo.setId(h.getTipo().getId());
		tipo.setTipo(h.getTipo().getTipo());
		tipo.setCapacidad(h.getTipo().getCapacidad());
		tipo.setCostoPorNoche(h.getTipo().getCostoPorNoche());
		
		
		HabitacionDTO dto = new HabitacionDTO();
		dto.setId(h.getId());
		dto.setNroHabitacion(h.getNumero());
		dto.setTipo(tipo);
		
		return dto;
	}
}
