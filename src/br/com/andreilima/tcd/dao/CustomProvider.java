package br.com.andreilima.tcd.dao;

import br.com.caelum.vraptor.ComponentRegistry;
import br.com.caelum.vraptor.ioc.spring.SpringProvider;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;
import br.com.caelum.vraptor.util.jpa.JPATransactionInterceptor;

public class CustomProvider extends SpringProvider {
	@Override
    protected void registerCustomComponents(ComponentRegistry registry) {
        registry.register(EntityManagerCreator.class, EntityManagerCreator.class);
        registry.register(EntityManagerFactoryCreator.class, EntityManagerFactoryCreator.class);
        registry.register(JPATransactionInterceptor.class, JPATransactionInterceptor.class);
    }
}
