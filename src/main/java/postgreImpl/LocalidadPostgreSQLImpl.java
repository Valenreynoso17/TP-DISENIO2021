package main.java.postgreImpl;

import org.hibernate.SessionFactory;

import main.java.daos.LocalidadDAO;

public class LocalidadPostgreSQLImpl implements LocalidadDAO {
	private SessionFactory sessionFactory;
	
	public LocalidadPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
}
