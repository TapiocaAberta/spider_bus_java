package org.spider.bus.dao;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.spider.bus.constantes.MongoDB;

import com.mongodb.MongoClient;

public class Conexao {

	private static Conexao instance;
	public Datastore dataStore;

	private Conexao() {
		try {
			Morphia morphia = new Morphia();
			// MongoClient mongo = new MongoClient(MongoDB.URL_LOCAL, MongoDB.PORTA);

			MongoClient mongo = new MongoClient(MongoDB.URL_PROD, MongoDB.PORTA); // APENAS PARA PRODUCAO
			mongo.getDB(MongoDB.DB).authenticate(MongoDB.USUARIO, MongoDB.SENHA.toCharArray()); // APENAS PARA PRODUCAO

			this.dataStore = morphia.createDatastore(mongo, MongoDB.DB);
		} catch ( UnknownHostException e ) {
			e.printStackTrace();
		}
	}

	public static synchronized Conexao getInstance() {
		if ( instance == null ) {
			instance = new Conexao();
		}

		return instance;
	}
}
