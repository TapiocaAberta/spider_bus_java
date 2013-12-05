package org.spider.bus.recurso;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.spider.bus.business.recurso.SpiderBusiness;

@Path("buscar")
public class SpiderRecurso {

	@Inject
	private SpiderBusiness spiderBusiness;

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	// TODOS independente do tipo
	public Response buscarTodos() {
		return spiderBusiness.buscarTodos();
	}

	@GET
	@Path("onibus/")
	@Produces("application/json; charset=UTF-8")
	// TODOS do tipo onibus
	public Response buscarTodosOnibus() {
		return spiderBusiness.buscarTodosOnibus();
	}

	@GET
	@Path("alternativo/")
	@Produces("application/json; charset=UTF-8")
	// TODOS do tipo alternativo
	public Response buscarTodosAltenativo() {
		return spiderBusiness.buscarTodosAlternativos();
	}

	@GET
	@Path("/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero independente do tipo
	public Response buscarPorNumero(@PathParam("valor") String valor) {
		return spiderBusiness.buscar(valor);
	}

	@GET
	@Path("onibus/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero por onuibus
	public Response buscarOnibusPorNumero(@PathParam("valor") String valor) {
		return spiderBusiness.buscarOnibus(valor);
	}

	@GET
	@Path("alternativo/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero independente do tipo
	public Response buscarAlternativoPorNumero(@PathParam("valor") String valor) {
		return spiderBusiness.buscarAlternativo(valor);
	}

}
