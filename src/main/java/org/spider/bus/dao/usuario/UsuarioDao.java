package org.spider.bus.dao.usuario;

import org.mongodb.morphia.Datastore;
import org.spider.bus.dao.Conexao;
import org.spider.bus.pojo.usuario.UsuarioPojo;

public class UsuarioDao {

	public void salvar(UsuarioPojo usuario) throws Exception {
		Datastore dataStore = Conexao.getInstance().dataStore;

		dataStore.ensureIndexes(); // creates all defined with @Indexed
		dataStore.ensureCaps(); // creates all collections for @Entity(cap=@CappedAt(...))

		dataStore.save(usuario);

	}
}
