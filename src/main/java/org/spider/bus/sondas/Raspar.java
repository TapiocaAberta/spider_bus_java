package org.spider.bus.sondas;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.spider.bus.business.parse.HtmlParseLinhaTransporte;
import org.spider.bus.constantes.TipoConducao;
import org.spider.bus.model.LinhaModel;
import org.spider.bus.pojo.HoraItinerarioOnibus;

public class Raspar implements Job {

	private LinhaModel linhaModel;
	private HtmlParseLinhaTransporte parseDados;

	public void rasparDados() throws Exception { // RANGE 101 ao 360

		System.out.println("###################### ATIVANDO SONDA ######################");

		linhaModel = new LinhaModel();
		linhaModel.removerTodos(); // Limpar Collection para entrada de novos dados

		Integer linhaOnibus;
		Integer numeroMaximoOnibus = 360;

		for ( linhaOnibus = 101; linhaOnibus <= numeroMaximoOnibus; linhaOnibus++ ) {
			parseDados = new HtmlParseLinhaTransporte(linhaOnibus.toString());

			List<HoraItinerarioOnibus> linhas = parseDados.montaConteudoHorarioItinerario();
			linhaModel.salvarLinha(linhas, TipoConducao.ONIBUS);
		}

		System.out.println("###################### FIM SONDA ######################");
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			rasparDados();
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}
}
