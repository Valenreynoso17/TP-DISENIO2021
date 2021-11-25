package main.java.postgreImpl;

import org.hibernate.Session;

import main.java.clases.Ocupacion;
import main.java.daos.OcupacionDAO;

public class OcupacionPostgreSQLImpl implements OcupacionDAO {

	@Override
	public void guardar(Ocupacion ocupacion) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		//sesion.saveOrUpdate(pasajero.getDireccion());
		sesion.saveOrUpdate(pasajero);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return pasajero.getId();
		
	}

}
