package main.java.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.clases.Ocupacion;
import main.java.clases.Reserva;
import main.java.daos.OcupacionDAO;
import main.java.daos.ReservaDAO;
import main.java.dtos.OcupacionDTO;
import main.java.dtos.ReservaDTO;
import main.java.postgreImpl.OcupacionPostgreSQLImpl;
import main.java.postgreImpl.ReservaPostgreSQLImpl;

public class GestorReserva {

	private static GestorReserva instance;
		
	private ReservaDAO reservaDAO;
	
	private GestorReserva() {
		reservaDAO = new ReservaPostgreSQLImpl();
	}
	
	public static GestorReserva getInstance() {

		if (instance == null) instance = new GestorReserva();
		
		return instance;
	}
	
	public List<ReservaDTO> buscarReservas(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
		List<Reserva> reservas = reservaDAO.buscar(fechaDesde, fechaHasta);
		List<ReservaDTO> reservasDTO = new ArrayList<>();
		
		for (Reserva r : reservas) {
			ReservaDTO dto = new ReservaDTO();
			dto.setId(r.getId());
			dto.setIngreso(r.getIngreso());
			dto.setEgreso(r.getEgreso());
			dto.setIdHabitacion(r.getHabitacion().getId());
			
			reservasDTO.add(dto);
		}
		
		return reservasDTO;
	}
}
