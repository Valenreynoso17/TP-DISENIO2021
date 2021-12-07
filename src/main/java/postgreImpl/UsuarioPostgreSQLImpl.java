package main.java.postgreImpl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.clases.Usuario;
import main.java.daos.UsuarioDAO;

public class UsuarioPostgreSQLImpl implements UsuarioDAO {
	private SessionFactory sessionFactory;
	
	public UsuarioPostgreSQLImpl() {
		sessionFactory = HibernateManager.getInstance();
	}

	@Override
	public Usuario buscar(String usuario) {
		Usuario user = null;
		String stringQuery = 	"SELECT u FROM Usuario u "
							+ 	"WHERE u.usuario = :usuario ";
		
		Session sesion = sessionFactory.openSession();
		
		TypedQuery<Usuario> query = sesion.createQuery(stringQuery, Usuario.class);
		
		query.setParameter("usuario", usuario);
		
		try {
			user = query.getSingleResult();
		}
		catch (NoResultException e) {
			user = null;
		}
		finally {
			sesion.close();
		}
		
		return user;
		
		
	}
	

}
