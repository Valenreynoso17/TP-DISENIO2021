package main.java.postgreImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Factura;
import main.java.daos.FacturaDAO;

public class FacturaPostgreSQLImpl implements FacturaDAO {
	private SessionFactory sessionFactory;
	
	public FacturaPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}
	
	@Override
	public Factura buscar(Integer id) {
		Session sesion = sessionFactory.openSession();
		
		Factura factura = sesion.get(Factura.class, id);
		
		sesion.close();
		
		return factura;
	}

	@Override
	public Integer cargar(Factura factura) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(factura);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return factura.getId();
		
	}

}
