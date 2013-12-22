package org.spider.bus.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.spider.bus.model.onibus.Linha;

@Stateless
public class LinhaDao {

	@Inject
	protected Logger log;

	@Inject
	EntityManager em;

	public void salvarLista(List<Linha> linhas) {

		try {
			for ( Linha linha : linhas ) {
				em.persist(linha);
				em.flush();
			}
			log.info("Salvo com sucesso! ");
		} catch ( Exception e ) {
			e.printStackTrace();
			log.info("Erro ao salvar Linha: " + e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Linha> buscarTodos() {
		List<Linha> linhas = null;
		try {
			FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
			QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Linha.class).get();
			org.apache.lucene.search.Query query = qb.all().createQuery();
			javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Linha.class);
			linhas = persistenceQuery.getResultList();

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return linhas;

	}

	public List<Linha> buscarTodosPorTipo(String tipoConducao) {

		return null;
	}

	public List<Linha> buscarPorNumeroLinha(String numero, String tipoConducao) {
		return null;
	}

	public List<Linha> buscarPorRua(String rua, String tipoConducao) {
		return null;
	}
}
