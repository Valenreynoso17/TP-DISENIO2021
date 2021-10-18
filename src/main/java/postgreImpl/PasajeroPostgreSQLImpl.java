package main.java.postgreImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Pasajero;
import main.java.daos.PasajeroDAO;

public class PasajeroPostgreSQLImpl implements PasajeroDAO {
	private SessionFactory sessionFactory;
	
	public PasajeroPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}

	public Integer guardar(Pasajero pasajero) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(pasajero.getDireccion());
		sesion.saveOrUpdate(pasajero);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return pasajero.getId();
	}
	
	
}
