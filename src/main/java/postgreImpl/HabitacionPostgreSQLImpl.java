package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Habitacion;
import main.java.clases.TipoHabitacion;
import main.java.daos.HabitacionDAO;

public class HabitacionPostgreSQLImpl implements HabitacionDAO {
	private SessionFactory sessionFactory;
	
	public HabitacionPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
	
	@Override
	public Integer guardar(Habitacion habitacion) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(habitacion);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return habitacion.getId();
	}

	@Override
	public TipoHabitacion buscarTipoHabitacion(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		TipoHabitacion tipo = sesion.load(TipoHabitacion.class, id);
		
		sesion.close();
		
		return tipo;
	}

	@Override
	public List<Habitacion> buscarHabitaciones() {
		Session sesion = sessionFactory.openSession();
		
		String stringQuery = "SELECT h FROM Habitacion h ";	
		
		TypedQuery<Habitacion> q = sesion.createQuery(stringQuery, Habitacion.class);
		List<Habitacion> habitaciones = q.getResultList();
		
		sesion.close();
		
		return habitaciones;
	}

}
