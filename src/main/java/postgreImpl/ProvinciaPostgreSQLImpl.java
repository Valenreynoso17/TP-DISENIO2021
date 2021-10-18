package main.java.postgreImpl;

import org.hibernate.SessionFactory;

import main.java.daos.ProvinciaDAO;

public class ProvinciaPostgreSQLImpl implements ProvinciaDAO {
	private SessionFactory sessionFactory;
	
	public ProvinciaPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
}
