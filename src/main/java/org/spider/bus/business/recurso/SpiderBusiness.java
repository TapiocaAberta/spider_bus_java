package org.spider.bus.business.recurso;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.spider.bus.model.LinhaModel;
import org.spider.bus.pojo.HoraItinerarioOnibus;
import org.spider.bus.util.NumeroUtil;

public class SpiderBusiness {

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
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorNumeroLinha(numero);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibusPorNumero(String numero) {
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarOnibusPorNumeroLinha(numero);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativoPorNumero(String numero) {
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarAltenativoPorNumeroLinha(numero);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarPorRua(String rua) {
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarPorRua(rua);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibusPorRua(String rua) {
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarOnibusPorRua(rua);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativoPorRua(String rua) {
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarAlternativoPorRua(rua);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodos() {
		linhaModel = new LinhaModel();
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
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarTodosOnibus();
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodosAlternativos() {
		linhaModel = new LinhaModel();
		List<HoraItinerarioOnibus> linhas;
		try {
			linhas = linhaModel.buscarTodosAltenativos();
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}
}
