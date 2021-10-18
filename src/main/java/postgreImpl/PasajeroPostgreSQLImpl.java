package main.java.postgreImpl;

import org.hibernate.SessionFactory;

import main.java.daos.PasajeroDAO;

public class PasajeroPostgreSQLImpl implements PasajeroDAO {
	private SessionFactory sessionFactory;
	
	public PasajeroPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
}
