package main.java.postgreImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Reserva;
import main.java.daos.ReservaDAO;

public class ReservaPostgreSQLImpl implements ReservaDAO {
	
	private SessionFactory sessionFactory;
	
	public ReservaPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
	
	@Override
	public List<Reserva> buscar(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
		String stringQuery = 	"SELECT r FROM Reserva r "
							+ 	"WHERE r.ingreso BETWEEN :desde1 AND :hasta1 "
							+ 	"	OR r.egreso BETWEEN :desde2 AND :hasta2 ";

		Session sesion = sessionFactory.openSession();

		TypedQuery<Reserva> query = sesion.createQuery(stringQuery, Reserva.class);

		query.setParameter("desde1", fechaDesde);
		query.setParameter("hasta1", fechaHasta);
		query.setParameter("desde2", fechaDesde);
		query.setParameter("hasta2", fechaHasta);

		List<Reserva> reservas = query.getResultList();

		sesion.close();

		return reservas;
	}

}
