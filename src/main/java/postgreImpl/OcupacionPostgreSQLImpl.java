package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Ocupacion;
import main.java.clases.Pasajero;
import main.java.daos.OcupacionDAO;

public class OcupacionPostgreSQLImpl implements OcupacionDAO {
	private SessionFactory sessionFactory;
	
	public OcupacionPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
	
	@Override
	public Integer guardar(Ocupacion ocupacion) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(ocupacion);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return ocupacion.getId();
		
	}

	@Override
	public Ocupacion buscar(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		Ocupacion ocupacion = sesion.get(Ocupacion.class, id);
		
		sesion.close();
		
		return ocupacion;
	}

	@Override
	public Ocupacion buscarExtendido(Integer id) {
		
		String stringQuery = 	"SELECT o FROM Ocupacion o "
							+ 	"	LEFT JOIN FETCH o.pasajeros "
							+ 	"WHERE o.id = :id";							
		
			
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Ocupacion> q = sesion.createQuery(stringQuery, Ocupacion.class);
			
		q.setParameter("id", id);
		
		Ocupacion ocupacion = q.getSingleResult();
		
		sesion.close();
		
		return ocupacion;
	}

	@Override
	public Ocupacion buscarUltimaOcupacion(Integer nroHabitacion) {
		
		String stringQuery = 	"SELECT o FROM Ocupacion o "
							+ 	"WHERE o.idHabitacion = :nroHabitacion "
							+ 	"AND o.horaYFechaSalidaReal IS NULL;";


		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Ocupacion> q = sesion.createQuery(stringQuery, Ocupacion.class);
		
		q.setParameter("id", nroHabitacion);
		
		Ocupacion ocupacion = q.getSingleResult();
		
		sesion.close();
		
		return ocupacion;
	}
	
	

}
