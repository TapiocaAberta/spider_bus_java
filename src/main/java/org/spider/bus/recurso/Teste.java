package org.spider.bus.recurso;

import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.spider.bus.dao.LinhaDao;
import org.spider.bus.model.horario.Horarios;
import org.spider.bus.model.horario.SegundaASexta;
import org.spider.bus.model.onibus.Linha;

@Path("teste")
public class Teste {

	@Inject
	private LinhaDao dao;

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	public void buscarTodos() {

		SegundaASexta segSex = new SegundaASexta();
		segSex.setHorario("SEG-SEXT");

		Horarios hoararios = new Horarios(Arrays.asList(segSex), null, null, null, null, null);

		Linha linha = new Linha("ONIBUS", "200", "TESTE", "Sentido", "asdadasdadadas", hoararios);

		dao.salvarLista(Arrays.asList(linha));

	}
}
