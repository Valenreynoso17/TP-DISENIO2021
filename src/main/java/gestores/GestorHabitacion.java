package main.java.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.java.clases.Habitacion;
import main.java.daos.HabitacionDAO;
import main.java.dtos.FueraDeServicioDTO;
import main.java.dtos.HabitacionDTO;
import main.java.dtos.OcupacionDTO;
import main.java.dtos.ReservaDTO;
import main.java.dtos.TipoHabitacionDTO;
import main.java.postgreImpl.HabitacionPostgreSQLImpl;

public class GestorHabitacion {
	private static GestorHabitacion instance;
	
	private GestorOcupacion gestorOcupacion;
	private GestorReserva gestorReserva;
	private GestorFueraDeServicio gestorFueraDeServicio;
	
	private HabitacionDAO habitacionDao;
	
	private GestorHabitacion() {
		habitacionDao = new HabitacionPostgreSQLImpl();
		
		gestorOcupacion = GestorOcupacion.getInstance();
		gestorReserva = GestorReserva.getInstance();
		gestorFueraDeServicio = GestorFueraDeServicio.getInstance();
	}
	
	public static GestorHabitacion getInstance() {
		if (instance == null) instance = new GestorHabitacion();
		
		return instance;
	}
	
	
	public HabitacionDTO buscarHabitacionDTO(Integer id) {
		Habitacion habitacion = this.buscarHabitacion(id);
		
		return this.crearDTOMostrarEstado(habitacion);
	}
	
	
	public Habitacion buscarHabitacion(Integer id) {
		return habitacionDao.buscarHabitacion(id);
	}
	
	public Habitacion buscarHabitacionPorNro(Integer nroHabitacion) {
		return habitacionDao.buscarHabitacionPorNro(nroHabitacion);
	}
	
	public Map<TipoHabitacionDTO, List<HabitacionDTO>> buscarHabitaciones() {
		Map<TipoHabitacionDTO, List<HabitacionDTO>> mapTipoHabitacion = new TreeMap<>((t1, t2) -> t1.getCostoPorNoche().compareTo(t2.getCostoPorNoche()));
		
		List<Habitacion> habitaciones = habitacionDao.buscarHabitaciones();
		
		for (Habitacion h : habitaciones) {

			TipoHabitacionDTO tipoHabitacionDTO = new TipoHabitacionDTO(h.getTipo());
			
			if (!mapTipoHabitacion.containsKey(tipoHabitacionDTO)) mapTipoHabitacion.put(tipoHabitacionDTO, new ArrayList<>());			
				
				mapTipoHabitacion.get(tipoHabitacionDTO).add(this.crearDTOMostrarEstado(h));	
		}
		
		return mapTipoHabitacion;
	}
	
	// TODO probar si anda 
	public Map<String, List<HabitacionDTO>> buscarEstadoHabitaciones(LocalDate fechaDesde, LocalDate fechaHasta) {
		Map<String, List<HabitacionDTO>> mapTipoHabitacion = new HashMap<>();
		Map<Integer, HabitacionDTO> mapHabitaciones = new HashMap<>();
		
		List<Habitacion> habitaciones = habitacionDao.buscarHabitaciones();
		
		for (Habitacion h : habitaciones) {
			mapHabitaciones.put(h.getId(), crearDTOMostrarEstado(h));
		}
		
		List<OcupacionDTO> ocupaciones = gestorOcupacion.buscarOcupaciones(fechaDesde, fechaHasta);
		List<ReservaDTO> reservas = gestorReserva.buscarReservas(LocalDateTime.of(fechaDesde, LocalTime.of(0, 0)), LocalDateTime.of(fechaHasta, LocalTime.of(0, 0)));
		List<FueraDeServicioDTO> fuerasDeServicio = gestorFueraDeServicio.buscarFuerasDeServicio(LocalDateTime.of(fechaDesde, LocalTime.of(0, 0)), LocalDateTime.of(fechaHasta, LocalTime.of(0, 0)));
		
		
		
		for (OcupacionDTO o : ocupaciones) {
			mapHabitaciones.get(o.getIdHabitacion()).agregarOcupacion(o);
		}
		
		for (ReservaDTO r : reservas) {
			mapHabitaciones.get(r.getIdHabitacion()).agregarReserva(r);
		}
		
		for (FueraDeServicioDTO f : fuerasDeServicio) {
			mapHabitaciones.get(f.getIdHabitacion()).agregarFueraDeServicio(f);
		}
		
		
		for (Integer id : mapHabitaciones.keySet()) {
			String tipo = mapHabitaciones.get(id).getTipo().getTipo();
			
			
			if (!mapTipoHabitacion.containsKey(tipo)) mapTipoHabitacion.put(tipo, new ArrayList<>());			
			
			mapTipoHabitacion.get(tipo).add(mapHabitaciones.get(id));
			
			
		}
		return mapTipoHabitacion;
	}
	
//	public HabitacionDTO crearDTOMostrarEstado(Habitacion h) {
//		TipoHabitacionDTO tipo = new TipoHabitacionDTO();
//		tipo.setId(h.getTipo().getId());
//		tipo.setTipo(h.getTipo().getTipo());
//		
//		HabitacionDTO dto = new HabitacionDTO();
//		dto.setId(h.getId());
//		dto.setNroHabitacion(h.getNumero());
//		dto.setTipo(tipo);
//		
//		return dto;
//	}
	
	
	public HabitacionDTO crearDTOMostrarEstado(Habitacion h) {
		TipoHabitacionDTO tipo = new TipoHabitacionDTO(h.getTipo().getId(), h.getTipo().getTipo(), h.getTipo().getCostoPorNoche(), h.getTipo().getCapacidad());
		
//		tipo.setId(h.getTipo().getId());
//		tipo.setTipo(h.getTipo().getTipo());
//		tipo.setCapacidad(h.getTipo().getCapacidad());
//		tipo.setCostoPorNoche(h.getTipo().getCostoPorNoche());
		
		
		HabitacionDTO dto = new HabitacionDTO();
		dto.setId(h.getId());
		dto.setNroHabitacion(h.getNumero());
		dto.setTipo(tipo);
		
		return dto;
	}
	
	
}
