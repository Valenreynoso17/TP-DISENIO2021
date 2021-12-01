package main.java.daos;


import java.util.List;

import main.java.clases.Habitacion;
import main.java.clases.TipoHabitacion;

public interface HabitacionDAO {
	public Habitacion buscarHabitacion(Integer id);
	public Integer guardar(Habitacion habitacion);
	public TipoHabitacion buscarTipoHabitacion(Integer id);
	public List<Habitacion> buscarHabitaciones();
}
