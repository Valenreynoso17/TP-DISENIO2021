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
		sessionFactory = HibernateManager.getInstance();
	}
	
	@Override
	public List<Reserva> buscar(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
		String stringQuery = 	"SELECT r FROM Reserva r "
							+ 	"WHERE r.ingreso BETWEEN :desde AND :hasta "
							+ 	"	OR r.egreso BETWEEN :desde AND :hasta "
							+ 	"	OR (:desde BETWEEN r.ingreso AND r.egreso "
							+ 	"		AND :hasta BETWEEN r.ingreso AND r.egreso) ";

		Session sesion = sessionFactory.openSession();

		TypedQuery<Reserva> query = sesion.createQuery(stringQuery, Reserva.class);

		query.setParameter("desde", fechaDesde);
		query.setParameter("hasta", fechaHasta);
		/*query.setParameter("desde2", fechaDesde);
		query.setParameter("hasta2", fechaHasta);
		query.setParameter("desde3", fechaDesde);
		query.setParameter("hasta3", fechaHasta);*/

		List<Reserva> reservas = query.getResultList();

		sesion.close();

		return reservas;
	}

}
