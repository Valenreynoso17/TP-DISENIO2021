package main.java.postgreImpl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Pais;
import main.java.daos.PaisDAO;

public class PaisPostgreSQLImpl implements PaisDAO {
	private SessionFactory sessionFactory;
	
	public PaisPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}

	public List<Pais> buscar() {
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Pais> query = sesion.createQuery("SELECT pais FROM Pais pais", Pais.class);
		List<Pais> listaPaises = query.getResultList();
		
		sesion.close();
		
		return listaPaises;
	}

	public Pais buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
