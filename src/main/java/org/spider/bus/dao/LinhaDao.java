package org.spider.bus.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
			log.info("Linha salva com sucesso! ");
		} catch ( Exception e ) {
			e.printStackTrace();
			log.info("Erro ao salvar Linha: " + e.getCause());
		}
	}

	public void salvarLista(List<Linha> linhas) {
		try {
			for ( Linha linha : linhas ) {
				em.persist(linha);
				log.info("Linha salva com sucesso! ");
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			log.info("Erro ao salvar Linha: " + e.getCause());
		}
	}

	public List<Linha> buscarPorRua(String rua, String tipoConducao) {

		List<Linha> linhas = new ArrayList<Linha>();
		try {

		} catch ( Exception e ) {
			log.info("");
		}

		return linhas;
	}
}
