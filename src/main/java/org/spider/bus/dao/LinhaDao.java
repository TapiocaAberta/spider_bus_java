package org.spider.bus.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.spider.bus.model.onibus.Linha;

@Stateless
@Default
public class LinhaDao {

	@Inject
	protected Logger log;

	@Inject
	EntityManager em;

	@PersistenceUnit
	EntityManagerFactory emf;

	@Resource
	UserTransaction utx;

	public void salvarLista(List<Linha> linhas) {

		try {
			for ( Linha linha : linhas ) {
				utx.begin();
				em = emf.createEntityManager();
				em.persist(linha);
				utx.commit();
				log.info("Salvo com sucesso! ");
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			log.info("Erro ao salvar Linha: " + e.getCause());
		}
	}

	// @SuppressWarnings("unchecked")
	// public List<Linha> buscarTodos() {
	// List<Linha> linhas = null;
	// try {
	// FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	// QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Linha.class).get();
	// org.apache.lucene.search.Query query = qb.all().createQuery();
	// javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Linha.class);
	// linhas = persistenceQuery.getResultList();
	//
	// } catch ( Exception e ) {
	// e.printStackTrace();
	// }
	// return linhas;
	//
	// }
	//
	// @SuppressWarnings("unchecked")
	// public List<Linha> buscarTodosPorTipo(String tipoConducao) {
	//
	// List<Linha> linhas = null;
	// try {
	// FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
	// QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Linha.class).get();
	// org.apache.lucene.search.Query query = qb.keyword().onField("tipo").matching(tipoConducao).createQuery();
	// javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Linha.class);
	// linhas = persistenceQuery.getResultList();
	//
	// } catch ( Exception e ) {
	// e.printStackTrace();
	// }
	// return linhas;
	// }
	//
	// public List<Linha> buscarPorNumeroLinha(String numero, String tipoConducao) {
	// List<Linha> linhas = null;
	// CriteriaBuilder builder = em.getCriteriaBuilder();
	// CriteriaQuery<Linha> criteria = builder.createQuery(Linha.class);
	// Root<Linha> from = criteria.from(Linha.class);
	// Predicate predicate = builder.and();
	// try {
	//
	// if ( !numero.equals("") ) {
	// predicate = builder.and(predicate, builder.like(from.<String> get("numero"), "%" + numero + "%"));
	// }
	//
	// if ( !tipoConducao.equals("") ) {
	// predicate = builder.and(predicate, builder.like(from.<String> get("tipo"), "%" + tipoConducao + "%"));
	// }
	//
	// criteria.select(from);
	// criteria.where(predicate);
	// linhas = em.createQuery(criteria).getResultList();
	//
	// } catch ( Exception e ) {
	// log.info("");
	// }
	//
	// return linhas;
	// }
	//
	// public List<Linha> buscarPorRua(String rua, String tipoConducao) {
	//
	// List<Linha> linhas = null;
	// CriteriaBuilder builder = em.getCriteriaBuilder();
	// CriteriaQuery<Linha> criteria = builder.createQuery(Linha.class);
	// Root<Linha> from = criteria.from(Linha.class);
	// Predicate predicate = builder.and();
	// try {
	//
	// if ( !rua.equals("") ) {
	// predicate = builder.and(predicate, builder.like(from.<String> get("itinerario"), "%" + rua + "%"));
	// }
	//
	// if ( !tipoConducao.equals("") ) {
	// predicate = builder.and(predicate, builder.like(from.<String> get("tipo"), "%" + tipoConducao + "%"));
	// }
	//
	// criteria.select(from);
	// criteria.where(predicate);
	// linhas = em.createQuery(criteria).getResultList();
	//
	// } catch ( Exception e ) {
	// log.info("");
	// }
	//
	// return linhas;
	// }
}
