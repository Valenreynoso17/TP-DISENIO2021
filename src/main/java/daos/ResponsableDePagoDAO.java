package main.java.daos;

import main.java.clases.Pasajero;
import main.java.clases.ResponsableDePago;

public interface ResponsableDePagoDAO {
	public ResponsableDePago buscar(Integer id);
	public ResponsableDePago buscarPorPasajero(Integer idPasajero);
	public Integer guardar(ResponsableDePago responsable);
}
