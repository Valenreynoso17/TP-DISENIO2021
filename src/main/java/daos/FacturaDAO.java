package main.java.daos;

import main.java.clases.Factura;

public interface FacturaDAO {
	public Factura buscar(Integer id);
	public Integer guardar(Factura factura);
	public Factura buscarConItems(Integer id);
}
