package org.spider.bus.business.recurso;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.spider.bus.constantes.TipoConducao;
import org.spider.bus.model.LinhaModel;
import org.spider.bus.pojo.HoraItinerarioOnibus;
import org.spider.bus.util.NumeroUtil;

public class SpiderBusiness {

	@Inject
	private LinhaModel linhaModel;

	public Response buscar(String value) {
		try {

			if ( NumeroUtil.ehNumerico(value) ) {
				return buscarPorNumero(value);
			}

			return buscarPorRua(value);

		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibus(String value) {
		try {

			if ( NumeroUtil.ehNumerico(value) ) {
				return buscarOnibusPorNumero(value);
			}

			return buscarOnibusPorRua(value);

		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativo(String value) {
		try {

			if ( NumeroUtil.ehNumerico(value) ) {
				return buscarAlternativoPorNumero(value);
			}

			return buscarAlternativoPorRua(value);

		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarPorNumero(String numero) {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorNumeroLinha(numero, TipoConducao.TODOS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibusPorNumero(String numero) {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorNumeroLinha(numero, TipoConducao.ONIBUS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativoPorNumero(String numero) {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorNumeroLinha(numero, TipoConducao.ALTERNATIVO);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarPorRua(String rua) {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorRua(rua, TipoConducao.TODOS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibusPorRua(String rua) {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorRua(rua, TipoConducao.ONIBUS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativoPorRua(String rua) {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorRua(rua, TipoConducao.ALTERNATIVO);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodos() {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarTodos();
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodosOnibus() {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarTodosPorTipo(TipoConducao.ONIBUS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodosAlternativos() {
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarTodosPorTipo(TipoConducao.ALTERNATIVO);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}
}
