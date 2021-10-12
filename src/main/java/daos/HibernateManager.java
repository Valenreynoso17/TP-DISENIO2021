package main.java.daos;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateManager {
	public static SessionFactory Configure() {
		SessionFactory sessionFactory = null;
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		
		try {
			sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
		
		return sessionFactory;
	}
}
