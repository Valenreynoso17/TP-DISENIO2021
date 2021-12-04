package main.java.daos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import main.java.clases.FueraDeServicio;

public interface FueraDeServicioDAO {
	public List<FueraDeServicio> buscar(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
}
