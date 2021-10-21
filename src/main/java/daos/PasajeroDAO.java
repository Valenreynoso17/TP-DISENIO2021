package main.java.daos;

import java.util.List;

import javax.swing.SortOrder;

import main.java.clases.Pasajero;
import main.java.dtos.PasajeroDTO;
import main.java.enums.ColumnaBuscarPasajeros;
import main.java.enums.TipoDocumento;

public interface PasajeroDAO {
	public Integer guardar(Pasajero pasajero);
	public List<Pasajero> buscarPasajerosPaginado(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina, ColumnaBuscarPasajeros atributoOrden, SortOrder orden);
	public List<Pasajero> buscarPorDocumento(TipoDocumento tipoDoc, String numeroDoc);

}
