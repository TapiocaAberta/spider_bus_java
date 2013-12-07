package org.spider.bus.sondas;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.spider.bus.business.parse.HtmlParseLinhaTransporte;
import org.spider.bus.dao.LinhaDao;

@Singleton
@Startup
public class Raspar {

	@Inject
	private LinhaDao linhaModel;

	private HtmlParseLinhaTransporte parseDados;

	@Inject
	protected Logger log;

	public void rasparDados() throws Exception { // RANGE 101 ao 360

		// log.info("###################### ATIVANDO SONDA ######################");
		//
		// linhaModel.removerTodos(); // Limpar Collection para entrada de novos dados
		//
		// log.info("###################### ALTERNATIVO ######################");
		//
		// Integer linhaAlternativo;
		// Integer numeroMaximoAlternativo = 40;
		//
		// for ( linhaAlternativo = 10; linhaAlternativo <= numeroMaximoAlternativo; linhaAlternativo++ ) {
		// parseDados = new HtmlParseLinhaTransporte(linhaAlternativo.toString());
		//
		// List<HoraItinerarioOnibus> linhas = parseDados.montaConteudoHorarioItinerario();
		// linhaModel.salvarLinha(linhas, TipoConducao.ALTERNATIVO);
		// }
		//
		// log.info("###################### ONIBUS ######################");
		//
		// Integer linhaOnibus;
		// Integer numeroMaximoOnibus = 360;
		//
		// for ( linhaOnibus = 101; linhaOnibus <= numeroMaximoOnibus; linhaOnibus++ ) {
		// parseDados = new HtmlParseLinhaTransporte(linhaOnibus.toString());
		//
		// List<HoraItinerarioOnibus> linhas = parseDados.montaConteudoHorarioItinerario();
		// linhaModel.salvarLinha(linhas, TipoConducao.ONIBUS);
		// }
		// linhaModel.fecharConexao();
		// log.info("###################### FIM SONDA ######################");
	}

	@Schedule(dayOfWeek = "Sun", hour = "1")
	public void execute() {
		log.info("** Executando o JOB para coleta dos dados ***");
		try {
			// rasparDados();
		} catch ( Exception e ) {
			log.error("Erro grave durante coleta dos dados: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
