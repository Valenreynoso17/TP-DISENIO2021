package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Provincia;
import main.java.daos.ProvinciaDAO;

public class ProvinciaPostgreSQLImpl implements ProvinciaDAO {
	private SessionFactory sessionFactory;
	
	public ProvinciaPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}

	public Provincia buscar(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		Provincia provincia = sesion.get(Provincia.class, id);		
		
		sesion.close();
		
		return provincia;
	}

	public List<Provincia> buscarPorPais(Integer idPais) {
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Provincia> query = sesion.createQuery("SELECT p FROM Provincia p WHERE idpais = :id", Provincia.class);
		query.setParameter("id", idPais);
		List<Provincia> lista = query.getResultList();
		
		sesion.close();
		
		return lista;
	}
}
