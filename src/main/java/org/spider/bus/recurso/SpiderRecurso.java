package org.spider.bus.recurso;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.spider.bus.business.recurso.SpiderBusiness;

@Path("buscar")
public class SpiderRecurso {

	private SpiderBusiness spiderBusiness;

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	// TODOS independente do tipo
	public Response buscarTodos() {
		spiderBusiness = new SpiderBusiness();
		return spiderBusiness.buscarTodos();
	}

	@GET
	@Path("onibus/")
	@Produces("application/json; charset=UTF-8")
	// TODOS do tipo onibus
	public Response buscarTodosOnibus() {
		spiderBusiness = new SpiderBusiness();
		return spiderBusiness.buscarTodosOnibus();
	}

	@GET
	@Path("alternativo/")
	@Produces("application/json; charset=UTF-8")
	// TODOS do tipo alternativo
	public Response buscarTodosAltenativo() {
		spiderBusiness = new SpiderBusiness();
		return spiderBusiness.buscarTodosAlternativos();
	}

	@GET
	@Path("/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero independente do tipo
	public Response buscarPorNumero(@PathParam("valor") String valor) {
		spiderBusiness = new SpiderBusiness();
		return spiderBusiness.buscar(valor);
	}

	@GET
	@Path("onibus/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero por onuibus
	public Response buscarOnibusPorNumero(@PathParam("valor") String valor) {
		spiderBusiness = new SpiderBusiness();
		return spiderBusiness.buscarOnibus(valor);
	}

	@GET
	@Path("alternativo/{valor}")
	@Produces("application/json; charset=UTF-8")
	// por rua ou por numero independente do tipo
	public Response buscarAlternativoPorNumero(@PathParam("valor") String valor) {
		spiderBusiness = new SpiderBusiness();
		return spiderBusiness.buscarAlternativo(valor);
	}

}
