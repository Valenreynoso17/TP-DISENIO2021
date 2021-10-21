package main.java.postgreImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.swing.SortOrder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Pasajero;
import main.java.daos.PasajeroDAO;
import main.java.dtos.PasajeroDTO;
import main.java.enums.ColumnaBuscarPasajeros;
import main.java.enums.TipoDocumento;

public class PasajeroPostgreSQLImpl implements PasajeroDAO {
	private SessionFactory sessionFactory;
	
	public PasajeroPostgreSQLImpl() {
		sessionFactory = HibernateManager.Configure();
	}

	public Integer guardar(Pasajero pasajero) {
		Session sesion = sessionFactory.openSession();
		
		sesion.beginTransaction();
		
		sesion.saveOrUpdate(pasajero.getDireccion());
		sesion.saveOrUpdate(pasajero);
		
		sesion.getTransaction().commit();
		
		sesion.close();
		
		return pasajero.getId();
	}

	// TODO Hacer la parte de buscar segun cierto orden
	public List<Pasajero> buscarPasajerosPaginado(PasajeroDTO filtros, Integer tamPagina, Integer nroPagina, ColumnaBuscarPasajeros atributoOrden, SortOrder orden) {
		String stringQuery = 	"SELECT p FROM Pasajero p ";								
		int nroFiltros = 0;
		
		// Agrega los filtros necesarios a la consulta
		if (filtros.getNombre() != null) {
			stringQuery += "WHERE p.nombre LIKE :nombre ";
			
			nroFiltros++;
		}
		if (filtros.getApellido() != null) {
			if (nroFiltros == 0) stringQuery += "WHERE p.apellido LIKE :apellido ";
			else stringQuery += "AND p.apellido LIKE :apellido ";
			
			nroFiltros++;
		}
		if (filtros.getTipoDocumento() != null) {
			if (nroFiltros == 0) stringQuery += "WHERE p.tipoDocumento = :tipodoc ";
			else stringQuery += "AND p.tipoDocumento = :tipodoc ";
			
			nroFiltros++;
		}
		if (filtros.getNumeroDoc() != null) {
			if (nroFiltros == 0) stringQuery += "WHERE p.documento = :documento ";
			else stringQuery += "AND p.documento = :documento ";
		}		
		stringQuery += "ORDER BY :ordenResultados";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Pasajero> q = sesion.createQuery(stringQuery, Pasajero.class);
		
		// Setea los parametros necesarios para hacer la busqueda
		if (filtros.getNombre() != null) q.setParameter("nombre", filtros.getNombre() + "%");
		if (filtros.getApellido() != null) q.setParameter("apellido", filtros.getApellido() + "%"); 
		if (filtros.getTipoDocumento() != null) q.setParameter("tipodoc", TipoDocumento.DNI);
		if (filtros.getNumeroDoc() != null) q.setParameter("documento", filtros.getNumeroDoc());
		
		// Se setea el orden
		switch (orden) {
			case ASCENDING:
				q.setParameter("ordenResultados", atributoOrden.getNombreAtributo() + " ASC");
				System.out.println("p." + atributoOrden.getNombreAtributo() + " ASC");
				break;
			case DESCENDING:
				q.setParameter("ordenResultados", atributoOrden.getNombreAtributo() + " DESC");
				System.out.println("p." + atributoOrden.getNombreAtributo() + " DESC");
				break;
			default:
				break;
		}
		
		// Setea la cantidad de resultados y el numero de primer resultado
		q.setMaxResults(tamPagina);
		q.setFirstResult((nroPagina - 1)*tamPagina);
		
		List<Pasajero> pasajeros = q.getResultList();
		
		sesion.close();
		
		return pasajeros;
	}

	
	@Override
	public List<Pasajero> buscarPorDocumento(TipoDocumento tipoDoc, String numeroDoc) {
		
		String stringQuery = 	"SELECT p FROM Pasajero p "
							  + "WHERE  p.tipoDocumento = :tipodoc "
							  + "AND 	p.documento = :documento";								
		
			
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Pasajero> q = sesion.createQuery(stringQuery, Pasajero.class);
		
		// Setea los parametros necesarios para hacer la busqueda
		q.setParameter("tipodoc", tipoDoc);
		q.setParameter("documento", numeroDoc);		
		
		List<Pasajero> pasajeros = q.getResultList();
		
		sesion.close();
		
		return pasajeros;
	}
	
	
}
