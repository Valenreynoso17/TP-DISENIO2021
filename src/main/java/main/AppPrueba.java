package main.java.main;

import java.awt.Frame;
import java.util.List;

import main.java.clases.Factura;
import main.java.daos.FacturaDAO;
import main.java.daos.PasajeroDAO;
import main.java.dtos.PasajeroDTO;
import main.java.excepciones.InputInvalidaException;
import main.java.gestores.GestorPasajero;
import main.java.postgreImpl.FacturaPostgreSQLImpl;
import main.java.postgreImpl.PasajeroPostgreSQLImpl;

public class AppPrueba {
	public static void main(String[] args) {
		FacturaDAO dao = new FacturaPostgreSQLImpl();
		
		Factura f = dao.buscarConItems(1);
		
		//FramePrueba prueba = new FramePrueba();
		/*PasajeroDAO dao = new PasajeroPostgreSQLImpl();
		
		GestorPasajero gestor = GestorPasajero.getInstance();
		
		PasajeroDTO filtros = new PasajeroDTO();
		
		System.out.println(dao.cantidadPasajeros(filtros));*/
		
		//FrameGestionarPasajero frame = new FrameGestionarPasajero();
		
		/*try{
			List<PasajeroDTO> pasajeros = gestor.buscarPaginado(filtros, 5, 1);
			
			for (PasajeroDTO p : pasajeros) {
				System.out.println(p.getId() + " " + p.getNombre() + " " + p.getApellido());
			}
		}
		catch (InputInvalidaException e) {
			e.printStackTrace();
		}*/
		
	}
}
