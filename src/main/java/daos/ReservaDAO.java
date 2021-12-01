package main.java.daos;

import java.time.LocalDate;
import java.util.List;

import main.java.clases.Reserva;

public interface ReservaDAO {
	public List<Reserva> buscar(LocalDate fechaDesde, LocalDate fechaHasta);
}
