package com.services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Connection {
	private static SessionFactory sessionFactory;
	

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration config = new Configuration();
			config.addAnnotatedClass(com.albany.foodOnWheels.model.User.class);
			config.addAnnotatedClass(com.albany.foodOnWheels.model.FoodTruckOwner.class);
			config.configure();
			ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(servReg);
		}

		
		return sessionFactory;
	}
}
