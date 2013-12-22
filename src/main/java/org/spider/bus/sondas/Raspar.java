package org.spider.bus.sondas;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;
import org.slf4j.Logger;
import org.spider.bus.business.parse.HtmlParseLinhaTransporte;
import org.spider.bus.dao.LinhaDao;
import org.spider.bus.model.onibus.Linha;

@Singleton
@Startup
@TransactionTimeout(3000)
public class Raspar {

	@Inject
	private LinhaDao dao;

	@Inject
	protected Logger log;

	private HtmlParseLinhaTransporte parseDados;

	public void rasparDados() throws Exception { // RANGE 101 ao 360

		log.info("###################### ATIVANDO SONDA ######################");
		log.info("###################### ALTERNATIVO ######################");

		Integer linhaAlternativo;
		Integer numeroMaximoAlternativo = 40;

		for ( linhaAlternativo = 10; linhaAlternativo <= numeroMaximoAlternativo; linhaAlternativo++ ) {
			parseDados = new HtmlParseLinhaTransporte(linhaAlternativo.toString());

			List<Linha> linhas = parseDados.montaConteudoHorarioItinerario();
			dao.salvarLista(linhas);
		}

		log.info("###################### ONIBUS ######################");

		Integer linhaOnibus;
		Integer numeroMaximoOnibus = 360;

		for ( linhaOnibus = 101; linhaOnibus <= numeroMaximoOnibus; linhaOnibus++ ) {
			parseDados = new HtmlParseLinhaTransporte(linhaOnibus.toString());

			List<Linha> linhas = parseDados.montaConteudoHorarioItinerario();
			dao.salvarLista(linhas);
		}
		log.info("###################### FIM SONDA ######################");
	}

	@Schedule(dayOfWeek = "Sun", hour = "11", minute = "44")
	public void execute() {
		log.info("** Executando o JOB para coleta dos dados ***");
		try {
			rasparDados();
		} catch ( Exception e ) {
			log.error("Erro grave durante coleta dos dados: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
