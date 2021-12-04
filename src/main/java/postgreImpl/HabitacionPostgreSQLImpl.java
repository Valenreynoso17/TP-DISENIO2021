package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Habitacion;
import main.java.clases.Ocupacion;
import main.java.clases.TipoHabitacion;
import main.java.daos.HabitacionDAO;
import main.java.excepciones.DataBaseException;

public class HabitacionPostgreSQLImpl implements HabitacionDAO {
	private SessionFactory sessionFactory;
	
	public HabitacionPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}


	@Override
	public Habitacion buscarHabitacion(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		Habitacion habitacion = sesion.get(Habitacion.class, id);
		
		sesion.close();
		
		return habitacion;
	}
	
	@Override
	public Habitacion buscarHabitacionPorNro(Integer nroHabitacion) {
		Habitacion habitacion = null;
		
		String stringQuery = 	"SELECT h FROM Habitacion h "
							+ 	"WHERE h.numero = :nroHabitacion ";

		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Habitacion> q = sesion.createQuery(stringQuery, Habitacion.class);
		
		q.setParameter("nroHabitacion", nroHabitacion);
		
		List<Habitacion> habitaciones= q.getResultList();
		
		sesion.close();
		
		if (habitaciones.size() == 1) habitacion = habitaciones.get(0); 
		else if (habitaciones.size() > 1) throw new DataBaseException("Existen varias habitaciones con el mismo numero");
				
		return habitacion;
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
		
		TipoHabitacion tipo = sesion.get(TipoHabitacion.class, id);
		
		sesion.close();
		
		return tipo;
	}

	@Override
	public List<Habitacion> buscarHabitaciones() {
		Session sesion = sessionFactory.openSession();
		
		String stringQuery = "SELECT h FROM Habitacion h ORDER BY h.numero";	
		
		TypedQuery<Habitacion> q = sesion.createQuery(stringQuery, Habitacion.class);
		List<Habitacion> habitaciones = q.getResultList();
		
		sesion.close();
		
		return habitaciones;
	}

}
