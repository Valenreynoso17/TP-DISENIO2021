package main.java.daos;

import java.util.List;

import javax.swing.SortOrder;

import main.java.clases.Pasajero;
import main.java.dtos.PasajeroDTO;
import main.java.enmus.ColumnaBuscarPasajeros;

public interface PasajeroDAO {
	public Integer guardar(Pasajero pasajero);
	
}
