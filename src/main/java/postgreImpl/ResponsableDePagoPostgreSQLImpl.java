package main.java.postgreImpl;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Pasajero;
import main.java.clases.ResponsableDePago;
import main.java.daos.ResponsableDePagoDAO;

public class ResponsableDePagoPostgreSQLImpl implements ResponsableDePagoDAO {
	private SessionFactory sessionFactory;
	
	public ResponsableDePagoPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}

	@Override
	public ResponsableDePago buscar(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		ResponsableDePago responsable = sesion.get(ResponsableDePago.class, id);
		
		sesion.close();
		
		return responsable;
	}

	@Override
	public ResponsableDePago buscarResponsableAsociadoAPasajero(Integer idPasajero) {
		String stringQuery = "SELECT r FROM ResponsableDePago r WHERE idpasajero = :id";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<ResponsableDePago> query = sesion.createQuery(stringQuery, ResponsableDePago.class);
		
		ResponsableDePago responsable = query.setParameter("id", idPasajero).getSingleResult();
		
		sesion.close();
		
		return responsable;
	}

	@Override
	public Integer guardar(ResponsableDePago responsable) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(responsable);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return responsable.getId();
	}
	
	
}
