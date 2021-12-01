package main.java.daos;

import java.time.LocalDate;
import java.util.List;

import main.java.clases.Ocupacion;

public interface OcupacionDAO {
	public Integer guardar(Ocupacion ocupacion);
	public Ocupacion buscar(Integer id);
	public Ocupacion buscarExtendido(Integer id);
	public Ocupacion buscarUltimaOcupacion(Integer nroHabitacion);
	public List<Ocupacion> buscar(LocalDate fechaDesde, LocalDate fechaHasta);
}
