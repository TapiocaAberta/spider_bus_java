package org.spider.bus.model;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.spider.bus.constantes.MongoDB;
import org.spider.bus.constantes.TipoConducao;
import org.spider.bus.pojo.HoraItinerarioOnibus;
import org.spider.bus.pojo.Horario;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class LinhaModel {
	private DBCollection collection;

	public LinhaModel() {
		try {

			// MongoClient mongo = new MongoClient(MongoDB.URL_LOCAL, MongoDB.PORTA);
			MongoClient mongo = new MongoClient(MongoDB.URL_PROD, MongoDB.PORTA); // APENAS PARA PRODUCAO

			DB dataBase = mongo.getDB(MongoDB.DB);

			dataBase.authenticate(MongoDB.USUARIO, MongoDB.SENHA.toCharArray()); // APENAS PARA PRODUCAO

			this.collection = dataBase.getCollection("linha");

		} catch ( UnknownHostException e ) {
			e.printStackTrace();
		}
	}

	public void removerTodos() throws Exception {
		DBCursor cursor = collection.find();
		String loading = "";
		while ( cursor.hasNext() ) {
			collection.remove(cursor.next());
			loading = loading + " . ";
			System.out.println(loading);
		}
	}

	public void salvarLinha(List<HoraItinerarioOnibus> dadosDaLinha, String tipo) throws Exception {

		for ( HoraItinerarioOnibus horaItinerarioOnibus : dadosDaLinha ) {

			BasicDBObject horarios = montaListaComHorarios(horaItinerarioOnibus);

			BasicDBObject linha = new BasicDBObject();
			linha.put("data_criacao", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
			linha.put("ativo", true);
			linha.put("tipo", tipo);
			linha.put("numero", horaItinerarioOnibus.getNumero());
			linha.put("nome", horaItinerarioOnibus.getNome());
			linha.put("sentido", horaItinerarioOnibus.getSentido());
			linha.put("itinerario", horaItinerarioOnibus.getItinerario());
			linha.put("horarios", horarios);

			collection.insert(linha);
		}

	}

	protected BasicDBObject montaListaComHorarios(HoraItinerarioOnibus horaItinerarioOnibus) {
		Horario horariosPojo = horaItinerarioOnibus.getHorarios();
		BasicDBObject horarios = new BasicDBObject();

		if ( !horariosPojo.getDeSegundaSexta().isEmpty() ) {
			horarios.put("deSegundaSexta", horariosPojo.getDeSegundaSexta());
		}

		if ( !horariosPojo.getAosSabados().isEmpty() ) {
			horarios.put("aosSabados", horariosPojo.getAosSabados());
		}

		if ( !horariosPojo.getDomingosEFeriado().isEmpty() ) {
			horarios.put("domingosEFeriado", horariosPojo.getDomingosEFeriado());
		}

		if ( !horariosPojo.getDeSegundaSab().isEmpty() ) {
			horarios.put("deSegundaSab", horariosPojo.getDeSegundaSab());
		}

		if ( !horariosPojo.getDeSegundaDom().isEmpty() ) {
			horarios.put("deSegundaDom", horariosPojo.getDeSegundaDom());
		}

		if ( !horariosPojo.getObservacao().equals("") ) {
			horarios.put("observacao", horariosPojo.getObservacao());
		}
		return horarios;
	}

	public List<HoraItinerarioOnibus> buscarPorRua(String rua) throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("itinerario", Pattern.compile(rua.toUpperCase()));

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarAlternativoPorRua(String rua) throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("itinerario", Pattern.compile(rua.toUpperCase()));
		searchQuery.put("tipo", TipoConducao.ALTERNATIVO);

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarOnibusPorRua(String rua) throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("itinerario", Pattern.compile(rua.toUpperCase()));
		searchQuery.put("tipo", TipoConducao.ONIBUS);

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarPorNumeroLinha(String numero) throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("numero", Pattern.compile(numero));

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarAltenativoPorNumeroLinha(String numero) throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("numero", Pattern.compile(numero));
		searchQuery.put("tipo", TipoConducao.ALTERNATIVO);

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarOnibusPorNumeroLinha(String numero) throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("numero", Pattern.compile(numero));
		searchQuery.put("tipo", TipoConducao.ONIBUS);

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarTodos() throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();
		DBCursor cursor = collection.find();

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarTodosOnibus() throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("tipo", TipoConducao.ONIBUS);

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}

	public List<HoraItinerarioOnibus> buscarTodosAltenativos() throws Exception {

		List<HoraItinerarioOnibus> linhaDados = new ArrayList<HoraItinerarioOnibus>();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("tipo", TipoConducao.ALTERNATIVO);

		DBCursor cursor = collection.find(searchQuery);

		ObjectMapper mapper = new ObjectMapper();
		while ( cursor.hasNext() ) {
			HoraItinerarioOnibus horariosItinerario = mapper.readValue(cursor.next().toString(), HoraItinerarioOnibus.class);
			linhaDados.add(horariosItinerario);
		}

		return linhaDados;
	}
}
