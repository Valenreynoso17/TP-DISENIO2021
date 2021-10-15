package main.java.postgreImpl;

import org.hibernate.SessionFactory;

import main.java.daos.PersonaJuridicaDAO;

public class PersonaJuridicaPostgreSQLImpl implements PersonaJuridicaDAO {
	private SessionFactory sessionFactory;
	
	public PersonaJuridicaPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
}
