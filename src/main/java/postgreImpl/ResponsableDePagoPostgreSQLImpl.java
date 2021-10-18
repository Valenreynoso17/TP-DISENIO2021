package main.java.postgreImpl;

import org.hibernate.SessionFactory;

import main.java.daos.ResponsableDePagoDAO;

public class ResponsableDePagoPostgreSQLImpl implements ResponsableDePagoDAO {
	private SessionFactory sessionFactory;
	
	public ResponsableDePagoPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
}
