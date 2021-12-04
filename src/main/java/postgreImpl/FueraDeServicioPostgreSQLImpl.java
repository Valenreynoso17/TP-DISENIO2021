package main.java.postgreImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.FueraDeServicio;
import main.java.daos.FueraDeServicioDAO;

public class FueraDeServicioPostgreSQLImpl implements FueraDeServicioDAO {

	private SessionFactory sessionFactory;
	
	public FueraDeServicioPostgreSQLImpl() {
		sessionFactory = HibernateManager.getInstance();
	}
	
	@Override
	public List<FueraDeServicio> buscar(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
		String stringQuery = 	"SELECT f FROM FueraDeServicio f "
							+ 	"WHERE f.fechaInicio BETWEEN :desde1 AND :hasta1 "
							+ 	"	OR f.fechaFin BETWEEN :desde2 AND :hasta2 ";

		Session sesion = sessionFactory.openSession();

		TypedQuery<FueraDeServicio> query = sesion.createQuery(stringQuery, FueraDeServicio.class);

		query.setParameter("desde1", fechaDesde);
		query.setParameter("hasta1", fechaHasta);
		query.setParameter("desde2", fechaDesde);
		query.setParameter("hasta2", fechaHasta);

		List<FueraDeServicio> fuerasDeServicio = query.getResultList();

		sesion.close();

		return fuerasDeServicio;
	}

}
