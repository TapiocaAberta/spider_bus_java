package org.spider.bus.recurso;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.spider.bus.business.recurso.SpiderBusiness;

@Path("alternativo")
public class Alternativo {
	@Inject
	private SpiderBusiness spiderBusiness;

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	// TODOS do tipo alternativo
	public Response buscarTodosAltenativo() {
		return spiderBusiness.buscarTodosAlternativos();
	}

	@GET
	@Path("/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero independente do tipo
	public Response buscarAlternativoPorNumero(@PathParam("valor") String valor) {
		return spiderBusiness.buscarAlternativo(valor);
	}
}
