package main.java.daos;

import main.java.clases.ResponsableDePago;

public interface ResponsableDePagoDAO {
	public ResponsableDePago buscar(Integer id);
	public ResponsableDePago buscarResponsableAsociadoAPasajero(Integer idPasajero);
	public ResponsableDePago buscarPorCuit(String cuit);
	public Integer guardar(ResponsableDePago responsable);
}
