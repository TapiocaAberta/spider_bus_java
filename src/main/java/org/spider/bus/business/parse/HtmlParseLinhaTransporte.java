package org.spider.bus.business.parse;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.spider.bus.constantes.IdElementos;
import org.spider.bus.pojo.HoraItinerarioOnibus;

public class HtmlParseLinhaTransporte {

	private Document pagina;
	private boolean ehAlternativo = false;

	public HtmlParseLinhaTransporte(String numeroLinha) {

		if ( numeroLinha.length() == 2 ) {
			ehAlternativo = true;
		}

		geraDocumentPorURL(numeroLinha);
	}

	public List<HoraItinerarioOnibus> montaConteudoHorarioItinerario() throws Exception {

		try {
			HashSet<String> urls = buscaURL();
			HtmlParseHorarioItinerario horaEItinerario = new HtmlParseHorarioItinerario(urls);
			List<HoraItinerarioOnibus> dados = horaEItinerario.montaDados();

			return dados;

		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("erro ao buscar linha: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {

		HtmlParseLinhaTransporte parseDados = null;

		for ( int i = 10; i <= 40; i++ ) {
			parseDados = new HtmlParseLinhaTransporte(i + "");
			parseDados.buscaURL();
		}

	}

	protected HashSet<String> buscaURL() throws Exception {
		String urlHorario = "http://www.sjc.sp.gov.br/secretarias/transportes/horario-e-itinerario.aspx?acao=d&id_linha=";
		Elements linksHora = buscaUrlsParaTratar();

		HashSet<String> linhas = new HashSet<String>();
		if ( linksHora != null ) {
			for ( Element element : linksHora ) {
				String link = element.attr("href");

				Matcher subMatcher = Pattern.compile("\\d+").matcher(link);

				while ( subMatcher.find() ) {
					int tamanhoTotalLink = link.length();
					int start = subMatcher.start();
					String urlLinha = link.substring(start, tamanhoTotalLink);

					linhas.add(urlHorario + urlLinha);

				}

			}
		}

		return linhas;
	}

	private Elements buscaUrlsParaTratar() throws Exception {
		Elements liksHora = null;
		try {
			if ( ehAlternativo ) {
				System.out.println("Aqui");
				liksHora = pagina.getElementById(IdElementos.ID_DIV_ALTERNATIVO).select("a[href]");
			} else {
				System.out.println("Aqui BUs");
				liksHora = pagina.getElementById(IdElementos.ID_DIV_ONIBUS).select("a[href]");
			}

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return liksHora;
	}

	protected void geraDocumentPorURL(String numeroLinha) {
		System.out.println("linha: " + numeroLinha);
		String urlBuscaPorLinha = "http://www.sjc.sp.gov.br/secretarias/transportes/horario-e-itinerario.aspx?acao=p&opcao=0&txt=" + numeroLinha;

		try {
			pagina = Jsoup.connect(urlBuscaPorLinha).get();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
