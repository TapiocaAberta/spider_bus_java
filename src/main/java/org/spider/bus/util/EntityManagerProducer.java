package org.spider.bus.util;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
	@Produces
	@Default
	@PersistenceContext
	private EntityManager em;
}
