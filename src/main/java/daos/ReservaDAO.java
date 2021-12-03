package main.java.daos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import main.java.clases.Reserva;

public interface ReservaDAO {
	public List<Reserva> buscar(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
}
