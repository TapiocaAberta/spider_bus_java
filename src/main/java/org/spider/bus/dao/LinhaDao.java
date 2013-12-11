package org.spider.bus.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.spider.bus.model.Linha;

@Stateless
public class LinhaDao {

	@Inject
	protected Logger log;

	@Inject
	protected EntityManager em;

	public void salvar(Linha linha) {
		try {
			em.persist(linha);
			log.info("Linha salva com sucesso! ");
		} catch ( Exception e ) {
			log.info("Erro ao salvar Linha: " + e.getStackTrace());
		}
	}
}
