package main.java.postgreImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Ocupacion;
import main.java.daos.OcupacionDAO;

public class OcupacionPostgreSQLImpl implements OcupacionDAO {
	private SessionFactory sessionFactory;
	
	public OcupacionPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
	
	@Override
	public Integer guardar(Ocupacion ocupacion) {
		Session sesion = sessionFactory.openSession();
		
		sesion.saveOrUpdate(ocupacion);
		
		sesion.close();
		
		return ocupacion.getId();
		
	}

}
