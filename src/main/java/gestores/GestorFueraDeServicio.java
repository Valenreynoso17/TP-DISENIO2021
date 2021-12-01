package main.java.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.clases.FueraDeServicio;
import main.java.clases.Reserva;
import main.java.daos.FueraDeServicioDAO;
import main.java.dtos.FueraDeServicioDTO;
import main.java.dtos.ReservaDTO;
import main.java.postgreImpl.FueraDeServicioPostgreSQLImpl;

public class GestorFueraDeServicio {
	private static GestorFueraDeServicio instance;
	
	private FueraDeServicioDAO fueraDeServicioDAO;
	
	private GestorFueraDeServicio() {
		super();
		
		fueraDeServicioDAO = new FueraDeServicioPostgreSQLImpl();
	}
	
	public static GestorFueraDeServicio getInstance() {
		if (instance == null) instance = new GestorFueraDeServicio();
		
		return instance;
	}
	
	public List<FueraDeServicioDTO> buscarFuerasDeServicio(LocalDate fechaDesde, LocalDate fechaHasta) {
		List<FueraDeServicio> fuerasDeServicio = fueraDeServicioDAO.buscar(fechaDesde, fechaHasta);
		List<FueraDeServicioDTO> fuerasDeServicioDTO = new ArrayList<>();
		
		for (FueraDeServicio f : fuerasDeServicio) {
			FueraDeServicioDTO dto = new FueraDeServicioDTO();
			dto.setId(f.getId());
			dto.setFechaInicio(f.getFechaInicio());
			dto.setFechaFin(f.getFechaFin());
			
			fuerasDeServicioDTO.add(dto);
		}
		
		return fuerasDeServicioDTO;
	}
}
