package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Pasajero;
import main.java.clases.ResponsableDePago;
import main.java.daos.ResponsableDePagoDAO;

public class ResponsableDePagoPostgreSQLImpl implements ResponsableDePagoDAO {
	private SessionFactory sessionFactory;
	
	public ResponsableDePagoPostgreSQLImpl() {
		sessionFactory = HibernateManager.getInstance();
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
		ResponsableDePago responsable = null;
		
		String stringQuery = "SELECT r FROM ResponsableDePago r WHERE idpasajero = :id";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<ResponsableDePago> query = sesion.createQuery(stringQuery, ResponsableDePago.class);
		
		List<ResponsableDePago> responsables = query.setParameter("id", idPasajero).getResultList();
		
		sesion.close();
		
		if (responsables.size() == 1) responsable = responsables.get(0);
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

	@Override
	public ResponsableDePago buscarPorCuit(String cuit) {
		ResponsableDePago responsable = null;
		
		String stringQuery = "SELECT r FROM ResponsableDePago r WHERE r.personaJuridica.cuit = :cuit";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<ResponsableDePago> query = sesion.createQuery(stringQuery, ResponsableDePago.class);
		
		List<ResponsableDePago> responsables = query.setParameter("cuit", cuit).getResultList();
		
		sesion.close();
		
		if (responsables.size() == 1) responsable = responsables.get(0);
		
		return responsable;
	}
	
	
}
