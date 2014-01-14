package org.spider.bus.recurso;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.spider.bus.business.recurso.SpiderBusiness;

@Path("onibus")
public class Onibus {
	@Inject
	private SpiderBusiness spiderBusiness;

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	// TODOS do tipo onibus
	public Response buscarTodosOnibus() {
		return spiderBusiness.buscarTodosOnibus();
	}

	@GET
	@Path("/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero por onuibus
	public Response buscarOnibusPorNumero(@PathParam("valor") String valor) {
		return spiderBusiness.buscarOnibus(valor);
	}
}
