package org.spider.bus.dao.linha;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.spider.bus.constantes.TipoConducao;
import org.spider.bus.dao.Conexao;
import org.spider.bus.pojo.HoraItinerarioOnibus;
import org.spider.bus.pojo.Horario;

public class LinhaDao {

	@Inject
	protected Logger log;

	public void removerTodos() throws Exception {
		Datastore dataStore = Conexao.getInstance().dataStore;

		Query<Horario> horarios = dataStore.createQuery(Horario.class);
		dataStore.delete(horarios);

		Query<HoraItinerarioOnibus> linhas = dataStore.createQuery(HoraItinerarioOnibus.class);
		dataStore.delete(linhas);
	}

	public void salvarLinha(List<HoraItinerarioOnibus> dadosDaLinha) throws Exception {

		Datastore dataStore = Conexao.getInstance().dataStore;

		for ( HoraItinerarioOnibus horaItinerarioOnibus : dadosDaLinha ) {
			log.info("Salvando Linha: " + horaItinerarioOnibus.getNumero());

			dataStore.ensureIndexes();
			dataStore.ensureCaps();

			Horario horarios = horaItinerarioOnibus.getHorarios();
			dataStore.save(horarios);
			dataStore.save(horaItinerarioOnibus);

		}

	}

	public List<HoraItinerarioOnibus> buscarPorRua(String rua, String tipoConducao) {

		Datastore dataStore = Conexao.getInstance().dataStore;
		Query<HoraItinerarioOnibus> query = dataStore.createQuery(HoraItinerarioOnibus.class);

		Pattern regexp = Pattern.compile(rua);

		query.filter("itinerario", regexp);

		if ( !tipoConducao.equals(TipoConducao.TODOS) ) {
			query.filter("tipo", tipoConducao);
		}

		query.order("numero");

		return query.asList();

	}

	public List<HoraItinerarioOnibus> buscarPorNumeroLinha(String numero, String tipoConducao) {
		Datastore dataStore = Conexao.getInstance().dataStore;
		Query<HoraItinerarioOnibus> query = dataStore.createQuery(HoraItinerarioOnibus.class);

		Pattern regexp = Pattern.compile(numero);

		query.filter("numero", regexp);

		if ( !tipoConducao.equals(TipoConducao.TODOS) ) {
			query.filter("tipo", tipoConducao);
		}

		query.order("numero");

		return query.asList();
	}

	public List<HoraItinerarioOnibus> buscarTodos() {
		Datastore dataStore = Conexao.getInstance().dataStore;
		List<HoraItinerarioOnibus> linhas = dataStore.createQuery(HoraItinerarioOnibus.class).order("numero").asList();
		return linhas;
	}

	public List<HoraItinerarioOnibus> buscarTodosPorTipo(String tipoConducao) {
		Datastore dataStore = Conexao.getInstance().dataStore;
		Query<HoraItinerarioOnibus> query = dataStore.createQuery(HoraItinerarioOnibus.class);
		query.filter("tipo", tipoConducao).order("numero");
		return query.asList();
	}
}
