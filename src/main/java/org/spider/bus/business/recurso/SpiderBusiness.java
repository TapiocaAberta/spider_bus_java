package org.spider.bus.business.recurso;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.spider.bus.constantes.TipoConducao;
import org.spider.bus.dao.LinhaDao;
import org.spider.bus.model.onibus.Linha;
import org.spider.bus.util.NumeroUtil;

public class SpiderBusiness {

	@Inject
	private LinhaDao dao;

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
		List<Linha> linhas;
		try {
			linhas = dao.buscarPorNumeroLinha(numero, TipoConducao.TODOS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibusPorNumero(String numero) {
		List<Linha> linhas;
		try {
			linhas = dao.buscarPorNumeroLinha(numero, TipoConducao.ONIBUS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativoPorNumero(String numero) {
		List<Linha> linhas;
		try {
			linhas = dao.buscarPorNumeroLinha(numero, TipoConducao.ALTERNATIVO);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarPorRua(String rua) {
		List<Linha> linhas;
		try {
			linhas = dao.buscarPorRua(rua, TipoConducao.TODOS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarOnibusPorRua(String rua) {
		List<Linha> linhas;
		try {
			linhas = dao.buscarPorRua(rua, TipoConducao.ONIBUS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarAlternativoPorRua(String rua) {
		List<Linha> linhas;
		try {
			linhas = dao.buscarPorRua(rua, TipoConducao.ALTERNATIVO);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodos() {
		List<Linha> linhas;
		try {
			linhas = dao.buscarTodos();
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodosOnibus() {
		List<Linha> linhas;
		try {
			linhas = dao.buscarTodosPorTipo(TipoConducao.ONIBUS);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}

	public Response buscarTodosAlternativos() {
		List<Linha> linhas;
		try {
			linhas = dao.buscarTodosPorTipo(TipoConducao.ALTERNATIVO);
			return Response.status(Status.OK).entity(linhas).build();
		} catch ( Exception e ) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();

		}
	}
}
