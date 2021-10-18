package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Localidad;
import main.java.daos.LocalidadDAO;

public class LocalidadPostgreSQLImpl implements LocalidadDAO {
	private SessionFactory sessionFactory;
	
	public LocalidadPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}

	public Localidad buscar(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		Localidad localidad = sesion.get(Localidad.class, id);
		
		sesion.close();
		
		return localidad;
	}
	
	public List<Localidad> buscarPorProvincia(Integer idProvincia) {
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Localidad> query = sesion.createQuery("SELECT l FROM Localidad l WHERE idprovincia = :id", Localidad.class);
		query.setParameter("id", idProvincia);
		List<Localidad> lista = query.getResultList();
		
		sesion.close();
		
		return lista;
	}
	
	
}
