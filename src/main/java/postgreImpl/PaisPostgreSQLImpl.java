package main.java.postgreImpl;

import org.hibernate.SessionFactory;

import main.java.daos.PaisDAO;

public class PaisPostgreSQLImpl implements PaisDAO {
	private SessionFactory sessionFactory;
	
	public PaisPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
}
