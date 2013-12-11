package org.spider.bus.util;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class EntityManagerProducer {
	@Produces
	@Default
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager em;
}
