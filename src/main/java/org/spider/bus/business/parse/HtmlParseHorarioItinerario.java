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

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.spider.bus.constantes.DiasSemana;
import org.spider.bus.pojo.HoraItinerarioOnibus;
import org.spider.bus.pojo.Horario;

public class HtmlParseHorarioItinerario {

	private Document pagina;
	private HashSet<String> urls;
	
	@Inject
	protected Logger log;

	public HtmlParseHorarioItinerario() {
	}

	public HtmlParseHorarioItinerario(HashSet<String> urls) {
		this.urls = urls;
	}

	public List<HoraItinerarioOnibus> montaDados() {
		List<HoraItinerarioOnibus> horaEItinerario = new ArrayList<HoraItinerarioOnibus>();

		for ( String url : urls ) {

			try {
				pagina = Jsoup.connect(url).get();

				Horario horario = separaPorPeriodoEPegaObservacao(pagina.getElementById(ID_HORARIO).select("span").get(0).text());

				String sentido = pagina.getElementById(ID_SENTIDO).select("span").get(0).text();
				String itinerario = pagina.getElementById(ID_ITINERARIO).select("span").get(0).text();

				String nomeLinha = pagina.getElementById(ID_NOME_LINHA).select("span").get(0).text();
				String numero = pagina.getElementById(ID_NUMERO).select("span").get(0).text();

				HoraItinerarioOnibus horaEItinerarioPojo = new HoraItinerarioOnibus(numero, nomeLinha, sentido, itinerario, horario);
				horaEItinerario.add(horaEItinerarioPojo);

			} catch ( IOException e ) {
				log.warn("Erro quando carregando URL: "+url);
				e.printStackTrace();
			}
		}

		return horaEItinerario;
	}

	protected Horario separaPorPeriodoEPegaObservacao(String horarios) {

		horarios = remove0as6h6as12hEtc(horarios);

		List<String> horarioSegSex = new ArrayList<String>();
		List<String> horarioSegSab = new ArrayList<String>();
		List<String> sabados = new ArrayList<String>();
		List<String> domingosFeriados = new ArrayList<String>();
		List<String> segADom = new ArrayList<String>();

		String observacoes = "";

		DiasSemana diasSemanaComHorarios = determinaDiasSemana(horarios);

		String[] horariosSeparadosPorDiaSemana = horarios.split("[|]");

		if ( diasSemanaComHorarios.equals(SEGUNDA_DOMINGO) ) {
			segADom = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
		}

		if ( diasSemanaComHorarios.equals(TODOS) ) {
			horarioSegSex = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
			sabados = montaListaComHorarios(horariosSeparadosPorDiaSemana[2]);
			domingosFeriados = montaListaComHorarios(horariosSeparadosPorDiaSemana[3]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SEXTA_SABADO) ) {
			horarioSegSex = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
			sabados = montaListaComHorarios(horariosSeparadosPorDiaSemana[2]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SEXTA_DOMINGO) ) {
			horarioSegSex = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
			domingosFeriados = montaListaComHorarios(horariosSeparadosPorDiaSemana[2]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SABADO_DOMINGO) ) {
			horarioSegSab = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
			domingosFeriados = montaListaComHorarios(horariosSeparadosPorDiaSemana[2]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SEXTA) ) {
			horarioSegSex = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
		}

		if ( diasSemanaComHorarios.equals(SABADOS) ) {
			sabados = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
		}
		if ( diasSemanaComHorarios.equals(DOMINGOS) ) {
			domingosFeriados = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
		}

		if ( diasSemanaComHorarios.equals(SEGUNDA_SABADO) ) {
			horarioSegSab = montaListaComHorarios(horariosSeparadosPorDiaSemana[1]);
		}

		boolean contenObservacoes = Pattern.compile("([(]\\d[)]( *))").matcher(horarios).find();

		if ( contenObservacoes ) {
			observacoes = montaObs(horarios);
		}

		return new Horario(horarioSegSex, horarioSegSab, segADom, sabados, domingosFeriados, observacoes);
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

	protected List<String> montaListaComHorarios(String horario) {
		List<String> horarios = new ArrayList<String>();
		Matcher horariosMatcher = Pattern.compile("((\\d{2})[:](\\d{2})[(]\\d[)]|(\\d{2})[:](\\d{2}))").matcher(horario);

		while ( horariosMatcher.find() ) {
			horarios.add(horariosMatcher.group());
		}

		return horarios;
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
