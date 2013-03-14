package br.com.andreilima.tcd.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	public  EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lojacd");
		return factory.createEntityManager();
	}
}
