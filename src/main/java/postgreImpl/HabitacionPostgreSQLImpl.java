package main.java.postgreImpl;

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
		
		sesion.saveOrUpdate(habitacion);
		
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

}
