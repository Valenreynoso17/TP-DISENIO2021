package main.java.daos;


import main.java.clases.Habitacion;
import main.java.clases.TipoHabitacion;

public interface HabitacionDAO {
	public Integer guardar(Habitacion habitacion);
	public TipoHabitacion buscarTipoHabitacion(Integer id);
}
