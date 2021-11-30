package main.java.daos;

import java.util.List;

import main.java.clases.Factura;
import main.java.clases.ItemFactura;

public interface FacturaDAO {
	public Factura buscar(Integer id);
	public Integer guardar(Factura factura);
	public Factura buscarConItems(Integer id);
}
