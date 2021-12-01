package main.java.postgreImpl;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Factura;
import main.java.clases.ItemFactura;
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
	public Integer guardar(Factura factura) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(factura);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return factura.getId();
		
	}

	@Override
	public Factura buscarConItems(Integer id) {
		String stringQuery = 	"SELECT f FROM Factura f "
							+	"	LEFT JOIN FETCH f.items "
							+ 	"WHERE f.id = :id ";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Factura> query = sesion.createQuery(stringQuery, Factura.class);
		
		query.setParameter("id", id);
		
		Factura factura = query.getSingleResult();
		
		sesion.close();
		
		return factura;
	}

	/*@Override
	public List<ItemFactura> buscarItems() {
		String stringQuery = 	"SELECT i FROM ItemFactura i ";

		Session sesion = sessionFactory.openSession();
		
		TypedQuery<ItemFactura> query = sesion.createQuery(stringQuery, ItemFactura.class);

		List<ItemFactura> items = query.getResultList();

		sesion.close();

		return items;
	}*/
	
	
	
	

}
