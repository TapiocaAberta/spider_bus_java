package org.spider.bus.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.spider.bus.model.onibus.Linha;

@Stateless
@Default
public class LinhaDao {

	@Inject
	protected Logger log;

	@Inject
	protected EntityManager em;

	public void salvar(Linha linha) {
		try {
			em.persist(linha);
		} catch ( Exception e ) {
			e.printStackTrace();
			log.info("Erro ao salvar Linha: " + e.getCause());
		}
	}

	public void salvarLista(List<Linha> linhas) {
		
		try {
			for ( Linha linha : linhas ) {
				em.persist(linha);
			}
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

	@SuppressWarnings("unchecked")
	public List<Linha> buscarTodosPorTipo(String tipoConducao) {

		List<Linha> linhas = null;
		try {
			FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
			QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Linha.class).get();
			org.apache.lucene.search.Query query = qb.keyword().onField("tipo").matching(tipoConducao).createQuery();
			javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Linha.class);
			linhas = persistenceQuery.getResultList();

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return linhas;
	}

	public List<Linha> buscarPorNumeroLinha(String numero, String tipoConducao) {
		List<Linha> linhas = null;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Linha> criteria = builder.createQuery(Linha.class);
		Root<Linha> from = criteria.from(Linha.class);
		Predicate predicate = builder.and();
		try {

			if ( !numero.equals("") ) {
				predicate = builder.and(predicate, builder.like(from.<String> get("numero"), "%" + numero + "%"));
			}

			if ( !tipoConducao.equals("") ) {
				predicate = builder.and(predicate, builder.like(from.<String> get("tipo"), "%" + tipoConducao + "%"));
			}

			criteria.select(from);
			criteria.where(predicate);
			linhas = em.createQuery(criteria).getResultList();

		} catch ( Exception e ) {
			log.info("");
		}

		return linhas;
	}

	public List<Linha> buscarPorRua(String rua, String tipoConducao) {

		List<Linha> linhas = null;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Linha> criteria = builder.createQuery(Linha.class);
		Root<Linha> from = criteria.from(Linha.class);
		Predicate predicate = builder.and();
		try {

			if ( !rua.equals("") ) {
				predicate = builder.and(predicate, builder.like(from.<String> get("itinerario"), "%" + rua + "%"));
			}

			if ( !tipoConducao.equals("") ) {
				predicate = builder.and(predicate, builder.like(from.<String> get("tipo"), "%" + tipoConducao + "%"));
			}

			criteria.select(from);
			criteria.where(predicate);
			linhas = em.createQuery(criteria).getResultList();

		} catch ( Exception e ) {
			log.info("");
		}

		return linhas;
	}
}
