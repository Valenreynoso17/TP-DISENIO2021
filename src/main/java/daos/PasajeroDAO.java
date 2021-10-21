package main.java.daos;

import java.util.List;

import main.java.clases.Pasajero;
import main.java.dtos.PasajeroDTO;

public interface PasajeroDAO {
	public Integer guardar(Pasajero pasajero);
	public List<Pasajero> buscarPasajerosPaginado(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina);
}
