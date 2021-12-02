package main.java.daos;

import java.time.LocalDate;
import java.util.List;

import main.java.clases.FueraDeServicio;

public interface FueraDeServicioDAO {
	public List<FueraDeServicio> buscar(LocalDate fechaDesde, LocalDate fechaHasta);
}
