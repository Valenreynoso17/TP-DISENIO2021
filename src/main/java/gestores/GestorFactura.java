package main.java.gestores;

import main.java.daos.FacturaDAO;
import main.java.dtos.FacturaDTO;
import main.java.dtos.OcupacionDTO;
import main.java.postgreImpl.FacturaPostgreSQLImpl;

public class GestorFactura {
private static GestorFactura instance;
	
	private FacturaDAO facturaDAO;
	
	private GestorFactura() {
		facturaDAO = new FacturaPostgreSQLImpl();
	}
	
	public static GestorFactura getInstance() {
		if (instance == null) instance = new GestorFactura();
		
		return instance;
	}
	
	public void crearFactura(FacturaDTO facturaDTO, OcupacionDTO ocupacionDTO) {
		
	}
}
