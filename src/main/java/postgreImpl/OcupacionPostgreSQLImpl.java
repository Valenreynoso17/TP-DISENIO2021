package main.java.postgreImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;

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
		
		String stringQuery1 = 	"SELECT DISTINCT o " 
							+	"FROM Ocupacion o " 	
							+	"	LEFT JOIN FETCH o.pasajeros " 
							+	"WHERE o.id = :id";		
		String stringQuery2 = 	"SELECT DISTINCT o " 
							+	"FROM Ocupacion o " 	
							+	"	LEFT JOIN FETCH o.itemsOcupacion " 
							+	"WHERE o.id = :id";	
		String stringQuery3 = 	"SELECT DISTINCT o " 
							+	"FROM Ocupacion o " 	
							+	"	LEFT JOIN FETCH o.consumos " 
							+	"WHERE o.id = :id";	
		
			
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Ocupacion> query1 = sesion.createQuery(stringQuery1, Ocupacion.class);
		TypedQuery<Ocupacion> query2 = sesion.createQuery(stringQuery2, Ocupacion.class);
		TypedQuery<Ocupacion> query3 = sesion.createQuery(stringQuery3, Ocupacion.class);
			
		query1.setParameter("id", id);
		query2.setParameter("id", id);
		query3.setParameter("id", id);
		
		query1.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		query2.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		query3.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		
		List<Ocupacion> ocupaciones = query1.getResultList();
		ocupaciones = query2.getResultList();
		ocupaciones = query3.getResultList();
		
		sesion.close();
		
		System.out.println(ocupaciones);
		
		Ocupacion ocupacion = ocupaciones.get(0);
		
		return ocupacion;
	}

	@Override
	public Ocupacion buscarUltimaOcupacion(Integer nroHabitacion) {
		
		System.out.println("1 "+nroHabitacion);	
		
		String stringQuery = 	"SELECT o FROM Ocupacion o "
							+	"	JOIN Habitacion h " 
							+ 	"WHERE o.idHabitacion = h.id "
							+ 	"AND h.numero = :nroHabitacion "
							+ 	"AND o.horaYFechaSalidaReal IS NULL";

		System.out.println("2");
		
		Session sesion = sessionFactory.openSession();
		
		System.out.println("3");
		
		TypedQuery<Ocupacion> q = sesion.createQuery(stringQuery, Ocupacion.class);
		
		System.out.println("4");
		
		q.setParameter("id", nroHabitacion);
		
		System.out.println("5");
		
		Ocupacion ocupacion = q.getSingleResult();
		
		System.out.println("6");
		
		sesion.close();
		
		return ocupacion;
	}

	@Override
	public List<Ocupacion> buscar(LocalDate fechaDesde, LocalDate fechaHasta) {
		String stringQuery = 	"SELECT o FROM Ocupacion o "
							+ 	"WHERE o.ingreso BETWEEN :desde1 AND :hasta1 "
							+ 	"	OR o.egreso BETWEEN :desde2 AND :hasta2 ";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Ocupacion> query = sesion.createQuery(stringQuery, Ocupacion.class);
		
		query.setParameter("desde1", fechaDesde);
		query.setParameter("hasta1", fechaHasta);
		query.setParameter("desde2", fechaDesde);
		query.setParameter("hasta2", fechaHasta);
		
		List<Ocupacion> ocupaciones = query.getResultList();
		
		sesion.close();
		
		return ocupaciones;
	}
	
	

}
