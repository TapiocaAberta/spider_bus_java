package org.spider.bus.business.parse;

import static org.spider.bus.constantes.DiasSemana.DOMINGOS;
import static org.spider.bus.constantes.DiasSemana.NENHUM;
import static org.spider.bus.constantes.DiasSemana.SABADOS;
import static org.spider.bus.constantes.DiasSemana.SABADO_DOMINGO;
import static org.spider.bus.constantes.DiasSemana.SEGUNDA_DOMINGO;
import static org.spider.bus.constantes.DiasSemana.SEGUNDA_SABADO;
import static org.spider.bus.constantes.DiasSemana.SEGUNDA_SABADO_DOMINGO;
import static org.spider.bus.constantes.DiasSemana.SEGUNDA_SEXTA;
import static org.spider.bus.constantes.DiasSemana.SEGUNDA_SEXTA_DOMINGO;
import static org.spider.bus.constantes.DiasSemana.SEGUNDA_SEXTA_SABADO;
import static org.spider.bus.constantes.DiasSemana.TODOS;
import static org.spider.bus.constantes.IdElementos.ID_HORARIO;
import static org.spider.bus.constantes.IdElementos.ID_ITINERARIO;
import static org.spider.bus.constantes.IdElementos.ID_NOME_LINHA;
import static org.spider.bus.constantes.IdElementos.ID_NUMERO;
import static org.spider.bus.constantes.IdElementos.ID_SENTIDO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.spider.bus.constantes.DiasSemana;
import org.spider.bus.constantes.TipoConducao;
import org.spider.bus.model.horario.AosSabados;
import org.spider.bus.model.horario.DomingosEFeriados;
import org.spider.bus.model.horario.Horarios;
import org.spider.bus.model.horario.SegundaADomingo;
import org.spider.bus.model.horario.SegundaASabado;
import org.spider.bus.model.horario.SegundaASexta;
import org.spider.bus.model.onibus.Linha;

public class HtmlParseHorarioItinerario {

	private Document pagina;
	private HashSet<String> urls;

	public HtmlParseHorarioItinerario() {
	}

	public HtmlParseHorarioItinerario(HashSet<String> urls) {
		this.urls = urls;
	}

	public List<Linha> montaDados() {
		List<Linha> linhas = new ArrayList<Linha>();

		for ( String url : urls ) {

			try {
				pagina = Jsoup.connect(url).get();

				Horarios horarios = separaPorPeriodoEPegaObservacao(pagina.getElementById(ID_HORARIO).select("span").get(0).text());

				String sentido = pagina.getElementById(ID_SENTIDO).select("span").get(0).text();
				String itinerario = pagina.getElementById(ID_ITINERARIO).select("span").get(0).text();

				String nomeLinha = pagina.getElementById(ID_NOME_LINHA).select("span").get(0).text();
				String numero = pagina.getElementById(ID_NUMERO).select("span").get(0).text();

				String tipo = verificaTipoConducao(numero);

				Linha linha = new Linha(tipo, numero, nomeLinha, sentido, itinerario, horarios);

				linhas.add(linha);

			} catch ( IOException e ) {
				System.out.println("Erro quando carregando URL: " + url);
			}
		}

		return linhas;
	}

	private String verificaTipoConducao(String numero) {
		String tipo = "";
		if ( numero.length() == 2 ) {
			tipo = TipoConducao.ALTERNATIVO;
		} else {
			tipo = TipoConducao.ONIBUS;
		}
		return tipo;
	}

	protected Horarios separaPorPeriodoEPegaObservacao(String horarios) {

		horarios = remove0as6h6as12hEtc(horarios);

		List<SegundaASexta> horarioSegSex = new ArrayList<SegundaASexta>();
		List<SegundaASabado> horarioSegSab = new ArrayList<SegundaASabado>();
		List<AosSabados> sabados = new ArrayList<AosSabados>();
		List<DomingosEFeriados> domingosFeriados = new ArrayList<DomingosEFeriados>();
		List<SegundaADomingo> segADom = new ArrayList<SegundaADomingo>();

		String observacoes = "";

		DiasSemana diasSemanaComHorarios = determinaDiasSemana(horarios);

		String[] horariosSeparadosPorDiaSemana = horarios.split("[|]");

		if ( diasSemanaComHorarios.equals(SEGUNDA_DOMINGO) ) {
			segADom = new SegundaADomingo().converteParaLista(horariosSeparadosPorDiaSemana[1]);
		}

		if ( diasSemanaComHorarios.equals(TODOS) ) {
			horarioSegSex = new SegundaASexta().converteParaLista(horariosSeparadosPorDiaSemana[1]);
			sabados = new AosSabados().converteParaLista(horariosSeparadosPorDiaSemana[2]);
			domingosFeriados = new DomingosEFeriados().converteParaLista(horariosSeparadosPorDiaSemana[3]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SEXTA_SABADO) ) {
			horarioSegSex = new SegundaASexta().converteParaLista(horariosSeparadosPorDiaSemana[1]);
			sabados = new AosSabados().converteParaLista(horariosSeparadosPorDiaSemana[2]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SEXTA_DOMINGO) ) {
			horarioSegSex = new SegundaASexta().converteParaLista(horariosSeparadosPorDiaSemana[1]);
			domingosFeriados = new DomingosEFeriados().converteParaLista(horariosSeparadosPorDiaSemana[2]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SABADO_DOMINGO) ) {
			horarioSegSab = new SegundaASabado().converteParaLista(horariosSeparadosPorDiaSemana[1]);
			domingosFeriados = new DomingosEFeriados().converteParaLista(horariosSeparadosPorDiaSemana[2]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SEXTA) ) {
			horarioSegSex = new SegundaASexta().converteParaLista(horariosSeparadosPorDiaSemana[1]);
		}

		if ( diasSemanaComHorarios.equals(SABADOS) ) {
			sabados = new AosSabados().converteParaLista(horariosSeparadosPorDiaSemana[1]);
		}
		if ( diasSemanaComHorarios.equals(DOMINGOS) ) {
			domingosFeriados = new DomingosEFeriados().converteParaLista(horariosSeparadosPorDiaSemana[1]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SABADO) ) {
			horarioSegSab = new SegundaASabado().converteParaLista(horariosSeparadosPorDiaSemana[1]);
		}

		boolean contenObservacoes = Pattern.compile("([(]\\d[)]( *))").matcher(horarios).find();

		if ( contenObservacoes ) {
			observacoes = montaObs(horarios);
		}

		return new Horarios(horarioSegSex, horarioSegSab, segADom, sabados, domingosFeriados, observacoes);
	}

	protected DiasSemana determinaDiasSemana(String horarios) {

		boolean contemSegSex = horarios.contains("De segunda-feira a sexta-feira");
		boolean contemSabado = horarios.contains("Aos sábados");
		boolean contemDomingo = horarios.contains("Aos domingos e feriados");
		boolean contemSegASab = horarios.contains("De segunda-feira a sábado");
		boolean contemSegADom = horarios.contains("De segunda-feira a domingo e feriados");

		if ( contemSegADom ) {
			return SEGUNDA_DOMINGO;
		}

		if ( contemSegASab && contemDomingo ) {
			return SEGUNDA_SABADO_DOMINGO;
		} else if ( contemSegASab ) {
			return SEGUNDA_SABADO;
		}

		if ( contemSegSex && contemSabado && contemDomingo ) {
			return TODOS;
		} else if ( contemSegSex && contemSabado ) {
			return SEGUNDA_SEXTA_SABADO;
		} else if ( contemSegSex && contemDomingo ) {
			return SEGUNDA_SEXTA_DOMINGO;
		} else if ( contemSabado && contemDomingo ) {
			return SABADO_DOMINGO;
		} else if ( contemSabado ) {
			return SABADOS;
		} else if ( contemDomingo ) {
			return DOMINGOS;
		} else if ( contemSegSex ) {
			return SEGUNDA_SEXTA;
		}

		return NENHUM;
	}

	protected String remove0as6h6as12hEtc(String horarioTratar) {
		String horarioTratado = horarioTratar.replaceAll("(( *)0 às 6h 6 às 12h 12 às 18h 18 às 24h( *))", "|");
		return horarioTratado;
	}

	protected String montaObs(String horariosSemTratar) {
		String observacao = "";

		int tamanhoString = horariosSemTratar.length();
		Matcher horariosMatcher = Pattern.compile("(( )([(]\\d{1,2}[)])(( +)|())(([A-Z]+)| +))").matcher(horariosSemTratar);

		while ( horariosMatcher.find() ) {
			observacao = horariosSemTratar.substring(horariosMatcher.start(), tamanhoString);
			break;
		}

		return observacao;

	}
}
